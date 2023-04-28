import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Algo {
    public static Vector<Vector<Integer>> FloydWarshall(Graphe graphe) {
        graphe.creerP();
        int n = graphe.getP().length;
        Vector<Vector<Integer>> matDist = new Vector<Vector<Integer>>();

        // Initialisation de la matrice de distances avec des 0
        for (int i = 0; i < n; i++) {
            Vector<Integer> ligne = new Vector<Integer>();
            for (int j = 0; j < n; j++) {
                ligne.add(0);
            }
            matDist.add(ligne);
        }

        // Remplissage de la matrice de distances avec les poids des arêtes
        int[][] matPoids = graphe.getP();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i == j) {
                    matDist.get(i).set(j, 0);
                } else if (matPoids[i][j] != -1) {
                    matDist.get(i).setElementAt(matPoids[i][j], j);
                } else {
                    matDist.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }

        // Calcul des distances minimales via l'algorithme de Floyd-Warshall
        for (int k = 1; k < n; k++) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (matDist.get(i).get(k) != Integer.MAX_VALUE
                            && matDist.get(k).get(j) != Integer.MAX_VALUE
                            && matDist.get(i).get(j) > matDist.get(i).get(k) + matDist.get(k).get(j)) {
                        matDist.get(i).set(j, matDist.get(i).get(k) +  matDist.get(k).get(j));
                    }
                }
            }
        }

        for (Vector<Integer> vec : matDist) {
            System.out.println(vec);
        }

        return matDist;

    }

    public static Vector<Vector<Integer>> Dikjstra(Vector<Integer> fs, Vector<Integer> aps, int[][] poids, int s) {
        final int MAXPOIDS = Integer.MAX_VALUE;
        int n = aps.get(0);
        int m = fs.get(0);
        Vector<Integer> prToReturn = new Vector<>(Collections.nCopies(n + 1, 0));
        int[] pr = new int[n + 1];
        Vector<Integer> dToReturn = new Vector<>(Collections.nCopies(n + 1, 0));
        int[] d = new int[n + 1];
        int[] inS = new int[n + 1]; // sert a dire quels sont les sommets qui restent a traiter inS[i] = 0 ou 1

        // initialisation des tableaux d, pr et inS
        for (int i = 1; i <= n; i++) {
            if(poids[s][i] == -1) {
                d[i] = MAXPOIDS;
                pr[i] = -1;
            }
            else {
                d[i] = poids[s][i];
                pr[i] = s;
            }
            inS[i] = 1;
        }

        d[s] = 0;
        pr[s] = 0;
        inS[s] = 0; // on supprime le sommet s (ici 1) car on traite le sommet 1 en premier
        int ind = n - 1;

        // System.out.println(ind); // 4 sommets a traiter
        // System.out.println(Arrays.toString(pr)); // 0 -1 -1 -1 -1 --> censé etre 0 1 -1 -1 1
        // System.out.println(Arrays.toString(d)); // 0 0 100 100 1 --> Bon d
        // System.out.println(Arrays.toString(inS)); // 0 1 1 1 1 --> Bon car on a encore 2,3,4,5 a traiter

        while (ind > 0) {
            // calcul du minimum selon d des sommets de inS
            m = MAXPOIDS;
            int j = 0;
            for (int i = 1; i <= n; i++) {
                if (inS[i] == 1) { // Si le sommet i est a traiter
                    if (d[i] < m) { // Si la distance n'est pas infini
                        m = d[i]; // m = la distance du nouveau sommet a traiter
                        j = i; // j = l'indice du sommet a traiter
                    }
                }
            }
            if (m == MAXPOIDS) {
                // Si m est infini, ce qui sous entend que dans d, les sommets ne sont plus traitables
                Vector<Vector<Integer>> returnValues = new Vector<>();
                returnValues.add(dToReturn);
                returnValues.add(prToReturn);
                return returnValues;
            }

            inS[j] = 0; // on supprime le sommet qu'on vient de traiter
            ind--; // on decremente le nombre de sommets a traiter
            int k = aps.get(j); // k = aps du sommet a traiter --> la position qu'on va utiliser pour voir vers quels sommets il va

            while (fs.get(k) != 0) { // tant que le sommet qu'on traite a des successeurs
                if (inS[fs.get(k)] == 1) { // Si le successeur du sommet qu'on traite n'est pas deja traité
                    int v = d[j] + poids[j][fs.get(k)]; // alors dans v on met la distance du sommet + le poids
                    if (v < d[fs.get(k)]) { // et si v est inferieur a la distance qu'on avait deja enregistré
                        d[fs.get(k)] = v; // alors on met la nouvelle distance
                        pr[fs.get(k)] = j; // et le nouveau sommet de depart qui donne le chemin le plus court
                    }
                }
                k++; // puis on incremente k pour trouver la distance minimale avec les autres sommets pas traités
            }

            System.out.println("d : " + Arrays.toString(d));
            System.out.println("pr : " + Arrays.toString(pr));

            for (int i = 0; i < n + 1; i++) {
                prToReturn.set(i, pr[i]);
                dToReturn.set(i, d[i]);
            }

        }
        Vector<Vector<Integer>> returnValues = new Vector<>();
        returnValues.add(dToReturn);
        returnValues.add(prToReturn);
        return returnValues;

    }

    public static int[] Prufer(Vector<Vector<Integer>> a) {
        System.out.println(a);
        int nbSom = a.elementAt(0).elementAt(0);
        int[] prf = new int[nbSom-1];
        prf[0] = nbSom - 2;
        int k = 1;
        while (k <= nbSom - 2) {
            int i = 1;
            while ((a.elementAt(i).elementAt(0)) != 1) i++;
            int j = 1;
            while ((a.elementAt(i).elementAt(j)) != 1) j++;
            prf[k++] = j;
            a.elementAt(i).setElementAt(0, j);
            a.elementAt(j).setElementAt(0, i);
            a.elementAt(i).setElementAt(0, 0);
            a.get(j).setElementAt(a.get(j).get(0) - 1, 0);
        }

        System.out.println(Arrays.toString(prf));
        return prf;
    }

    public static int[][] Rangs(Vector<Integer> fs, Vector<Integer> aps) {
        int n = aps.elementAt(0);
        int taillefs = fs.elementAt(0), s, h, t;
        System.out.println(taillefs);
        System.out.println(n);
        int[] ddi = new int[n + 1];
        int[] pilch = new int[n + 1];
        int[] prem = new int[n + 1];
        int[] rang = new int[n + 1];
        int[][] res = new int[3][n + 1];

        for (int i = 1; i <= n; i++) {
            ddi[i] = 0;
        }

        // calcul de ddi
        for (int i = 1; i <= taillefs; i++) {
            s = fs.elementAt(i);
            if (s > 0) {
                ddi[s]++;
            }
        }
        System.out.println("ddi : " + Arrays.toString(ddi));

        // calcul du rang
        pilch[0] = 0;
        for (s = 1; s <= n; s++) {
            rang[s] = -1; // n : nombre de sommets de G represente l'infini
            if (ddi[s] == 0) {
                empiler(s, pilch);
            }
        }

        int k = -1;
        s = pilch[0];
        prem[0] = s;

        while (pilch[0] > 0) {
            k++;
            pilch[0] = 0;

            while (s > 0) {
                rang[s] = k;
                h = aps.elementAt(s);
                t = fs.elementAt(h);

                while (t > 0) {
                    ddi[t]--;
                    if (ddi[t] == 0) {
                        empiler(t, pilch);
                    }
                    h++;
                    t = fs.elementAt(h);
                }

                s = pilch[s];
            }

            s = pilch[0];
            prem[k + 1] = s;
        }

        System.out.println("prem : " + Arrays.toString(prem));
        System.out.println("pilch : " + Arrays.toString(pilch));
        System.out.println("rang : " + Arrays.toString(rang));

        System.arraycopy(prem, 0, res[0], 0, prem.length);

        System.arraycopy(pilch, 0, res[1], 0, pilch.length);

        System.arraycopy(rang, 0, res[2], 0, rang.length);

        return res;
    }

    static void empiler (int x, int[] pilch) // avec x dans {1, ... , n}
    {
        pilch[x] = pilch[0];
        pilch[0] = x;
    }

}
