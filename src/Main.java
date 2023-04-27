import javax.swing.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        /*
        // Tests Fs Aps
        Vector<Integer> fs = new Vector<>(Arrays.asList(13, 2, 3, 4, 0, 2, 3, 4, 0, 5, 0, 0, 4, 0));
        Vector<Integer> aps = new Vector<>(Arrays.asList(5, 1, 5, 9, 11, 12));
        Graphe graphe = new Graphe(fs, aps, true);

        System.out.println(graphe + "\n##############");

        // Tests Mat Adj
        Vector<Vector<Integer>> A;
        A = graphe.getMatAdj();
        Graphe graphe1 = new Graphe(A, true);
        System.out.println(graphe1 + "\n##############");

        // Tests Lists
        Graphe graphe2 = new Graphe(true, graphe1.getSommets(), graphe1.getAretes());
        System.out.println(graphe2);
         */

        // Test Dijkstra ###############################
        Vector<Integer> fs = new Vector<>(Arrays.asList(14, 2, 5, 0, 1, 3, 0, 5, 0, 1, 3, 5, 0, 2, 0));
        Vector<Integer> aps = new Vector<>(Arrays.asList(5, 1, 4, 7, 9, 13));
        Vector<Sommet> sommets = new Vector<>(Arrays.asList(new Sommet(1), new Sommet(2), new Sommet(3), new Sommet(4), new Sommet(5)));
        Vector<Arete> aretes = new Vector<>(Arrays.asList(
                new Arete(sommets.elementAt(0), sommets.elementAt(4), 1),
                new Arete(sommets.elementAt(0), sommets.elementAt(1), 0),
                new Arete(sommets.elementAt(1), sommets.elementAt(0), 0),
                new Arete(sommets.elementAt(1), sommets.elementAt(2), 1),
                new Arete(sommets.elementAt(2), sommets.elementAt(4), 0),
                new Arete(sommets.elementAt(3), sommets.elementAt(2), 3),
                new Arete(sommets.elementAt(3), sommets.elementAt(4), 0),
                new Arete(sommets.elementAt(3), sommets.elementAt(0), 1),
                new Arete(sommets.elementAt(4), sommets.elementAt(1), 2))
        );

        Graphe gDijkstra = new Graphe(true, sommets, aretes);
        int [][]p = gDijkstra.creerP();
        //System.out.println(Arrays.deepToString(p));
        System.out.println(gDijkstra);
        Dikjstra(fs, aps, p,1);

        // Test Prüfer ###########################################
        /* #######################################################
        #                                                        #
        #                   DONE                                 #
        #                                                        #
        ##########################################################
         */
        Vector<Sommet> sommetsP = new Vector<>(Arrays.asList(new Sommet(1), new Sommet(2),
                new Sommet(3), new Sommet(4), new Sommet(5), new Sommet(6),
                new Sommet(7), new Sommet(8), new Sommet(9)));
        Vector<Arete> aretesP = new Vector<>(Arrays.asList(
                new Arete(sommetsP.elementAt(0), sommetsP.elementAt(1)),
                new Arete(sommetsP.elementAt(2), sommetsP.elementAt(1)),
                new Arete(sommetsP.elementAt(3), sommetsP.elementAt(1)),
                new Arete(sommetsP.elementAt(1), sommetsP.elementAt(4)),
                new Arete(sommetsP.elementAt(4), sommetsP.elementAt(5)),
                new Arete(sommetsP.elementAt(4), sommetsP.elementAt(6)),
                new Arete(sommetsP.elementAt(7), sommetsP.elementAt(5)),
                new Arete(sommetsP.elementAt(8), sommetsP.elementAt(6))
                )
        );

        Graphe graphePrufer = new Graphe(false, sommetsP, aretesP);
        // System.out.println(graphePrufer);
        //prufer(graphePrufer.getMatAdj());


        ////////// TEST CALCULE DISTANCE

        // graphe non-orienté avec 5 sommets et 7 arêtes
        Vector<Sommet> sommetsCD = new Vector<>(Arrays.asList(new Sommet(1), new Sommet(2),
                new Sommet(3), new Sommet(4), new Sommet(5)));

        Vector<Arete> aretesCD = new Vector<>(Arrays.asList(
            new Arete(sommetsCD.elementAt(0), sommetsCD.elementAt(1), 4),
            new Arete(sommetsCD.elementAt(0), sommetsCD.elementAt(2), 1),
            new Arete(sommetsCD.elementAt(1), sommetsCD.elementAt(2), 2),
            new Arete(sommetsCD.elementAt(1), sommetsCD.elementAt(3), 5),
            new Arete(sommetsCD.elementAt(2), sommetsCD.elementAt(3), 1),
            new Arete(sommetsCD.elementAt(2), sommetsCD.elementAt(4), 3),
            new Arete(sommetsCD.elementAt(3), sommetsCD.elementAt(4), 7)
            )
        );
        Graphe g = new Graphe(false, sommetsCD,aretesCD);
        // Calculer la matrice de distances
        //calculeDistance(g);
    }

    public static Vector<Vector<Integer>> calculeDistance(Graphe graphe) {
        Vector<Vector<Integer>> matDist = new Vector<Vector<Integer>>();
        int n = graphe.getNombreSommets();

        // Initialisation de la matrice de distances avec des 0
        for (int i = 0; i < n; i++) {
            Vector<Integer> ligne = new Vector<Integer>();
            for (int j = 0; j < n; j++) {
                ligne.add(0);
            }
            matDist.add(ligne);
        }

        // Remplissage de la matrice de distances avec les poids des arêtes
        Vector<Vector<Integer>> matAdj = graphe.getMatAdj();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matDist.get(i).set(j, 0);  // distance d'un sommet à lui-même
                } else if (matAdj.get(i).get(j) != 0) {
                    matDist.get(i).set(j, matAdj.get(i).get(j));  // poids de l'arête entre les deux sommets
                } else {
                    matDist.get(i).set(j, Integer.MAX_VALUE);  // distance infinie s'il n'y a pas d'arête entre les deux sommets
                }
            }
        }

        // Calcul des distances minimales via l'algorithme de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int distIj = matDist.get(i).get(j);
                    int distIk = matDist.get(i).get(k);
                    int distKj = matDist.get(k).get(j);
                    if (distIk != Integer.MAX_VALUE && distKj != Integer.MAX_VALUE && distIk + distKj < distIj) {
                        matDist.get(i).set(j, distIk + distKj);
                    }
                }
            }
        }
        System.out.println(matDist);
        return matDist;
    } // le premier tableau est faux, mais le reste est juste , pourquoi ?ceci est une bonne question



    public static void Dikjstra(Vector<Integer> fs, Vector<Integer> aps, int[][] poids, int s) {
        final int MAXPOIDS = 100;
        int n = aps.get(0);
        int m = fs.get(0);
        int[] pr = new int[n + 1];
        int[] d = new int[n + 1];
        int[] inS = new int[n + 1]; // sert a dire quels sont les sommets qui restent a traiter inS[i] = 0 ou 1

        /*
        System.out.println(n); // 5
        System.out.println(m); // 14
        System.out.println(Arrays.toString(pr)); // full 0
        System.out.println(Arrays.toString(d)); // full 0
        System.out.println(Arrays.toString(inS)); // full 0
         */

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

        /*
        System.out.println(Arrays.toString(pr));
        System.out.println(Arrays.toString(d));
        System.out.println(Arrays.toString(inS));
         */

        d[s] = 0;
        pr[s] = 0;
        inS[s] = 0; // on supprime le sommet s (ici 1) car on traite le sommet 1 en premier
        int ind = n - 1;

        System.out.println(ind); // 4 sommets a traiter
        System.out.println(Arrays.toString(pr)); // 0 -1 -1 -1 -1 --> censé etre 0 1 -1 -1 1
        System.out.println(Arrays.toString(d)); // 0 0 100 100 1 --> Bon d
        System.out.println(Arrays.toString(inS)); // 0 1 1 1 1 --> Bon car on a encore 2,3,4,5 a traiter

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
            if (m == MAXPOIDS) // Si m est infini, ce qui sous entend que dans d, les sommets ne sont plus traitables
                return;

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
            System.out.println(Arrays.toString(pr));
            System.out.println(Arrays.toString(d));
        }
    }

    public static int[] prufer(Vector<Vector<Integer>> a) {
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
}
