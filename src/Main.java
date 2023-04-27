import java.util.ArrayList;
import java.util.Arrays;
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
        //System.out.println(gDijkstra);
        //Dikjstra(fs, aps, p,1);

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
        /* #######################################################
        #                                                        #
        #                   DONE                                 #
        #                                                        #
        ##########################################################
         */
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
        g.creerP();
        // System.out.println(Arrays.deepToString(g.getP()));
        // Calculer la matrice de distances
        // calculeDistance(g);

        // Test Kruskal ####################################### CHALLENGE 100% IMPOSSIBLE
        Vector<Sommet> sommetsKruskal = new Vector<>(Arrays.asList(new Sommet(1), new Sommet(2),
                new Sommet(3), new Sommet(4), new Sommet(5), new Sommet(6), new Sommet(7), new Sommet(8)));

        Vector<Arete> aretesKruskal = new Vector<>(Arrays.asList(
                new Arete(sommetsKruskal.elementAt(0), sommetsKruskal.elementAt(2), 1),
                new Arete(sommetsKruskal.elementAt(0), sommetsKruskal.elementAt(4), 1),
                new Arete(sommetsKruskal.elementAt(0), sommetsKruskal.elementAt(6), 1),
                new Arete(sommetsKruskal.elementAt(1), sommetsKruskal.elementAt(3), 1),
                new Arete(sommetsKruskal.elementAt(2), sommetsKruskal.elementAt(4), 1),
                new Arete(sommetsKruskal.elementAt(3), sommetsKruskal.elementAt(5), 1),
                new Arete(sommetsKruskal.elementAt(5), sommetsKruskal.elementAt(7), 1),
                new Arete(sommetsKruskal.elementAt(6), sommetsKruskal.elementAt(7), 1),
                new Arete(sommetsKruskal.elementAt(0), sommetsKruskal.elementAt(1), 2),
                new Arete(sommetsKruskal.elementAt(2), sommetsKruskal.elementAt(3), 2),
                new Arete(sommetsKruskal.elementAt(3), sommetsKruskal.elementAt(4), 2),
                new Arete(sommetsKruskal.elementAt(5), sommetsKruskal.elementAt(6), 2),
                new Arete(sommetsKruskal.elementAt(1), sommetsKruskal.elementAt(4), 3),
                new Arete(sommetsKruskal.elementAt(3), sommetsKruskal.elementAt(7), 3),
                new Arete(sommetsKruskal.elementAt(2), sommetsKruskal.elementAt(5), 5)
        ));

        Graphe gKruskal = new Graphe(false, sommetsKruskal, aretesKruskal);
        Graphe gResKruskal = new Graphe(false, sommetsKruskal, aretesKruskal);
        int[][] poidsKruskal = gKruskal.creerP();
        int[] prem = new int[gKruskal.getNombreSommets()+1];
        int[] pilch = new int[gKruskal.getNombreSommets()+1];
        int[] cfc = new int[gKruskal.getNombreSommets()+1];
        int[] NbElem = new int[gKruskal.getNombreSommets()+1];
        for (int i = 1; i <= gKruskal.getNombreSommets(); i++) {
            prem[i] = i;
            pilch[i] = 0;
            cfc[i] = i;
            NbElem[i] = 1;
        }
        // System.out.println(gKruskal.getAretes());
        // gKruskal.trierAretes();
        // KruskalProf(gKruskal, gResKruskal, prem, pilch, cfc, NbElem);
        //kruskal(gKruskal, gResKruskal);
        // System.out.println(gResKruskal);

        // Test Rang ####################################### CHALLENGE 100% POSSIBLE
        /* #######################################################
        #                                                        #
        #                   DONE                                 #
        #                                                        #
        ##########################################################
         */
        Vector<Integer> fsRang = new Vector<>(Arrays.asList(24, 2, 3, 0, 4, 0, 4, 5, 0, 0, 4, 6, 0, 4, 7, 9, 0, 4, 9, 0, 2, 4, 0, 4, 0));
        Vector<Integer> apsRang = new Vector<>(Arrays.asList(9, 1, 4, 6, 9, 10, 13, 17, 20, 23));


        int[] rang = new int[apsRang.get(0)+1];
        // rang(fsRang, apsRang, rang);

    }

    public static Vector<Vector<Integer>> calculeDistance(Graphe graphe) {
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
        return matDist; // le premier tableau est faux, mais le reste est juste , pourquoi ?ceci est une bonne question

    }

    public static void fusionnerProf(int i, int j, int[] prem, int[] pilch, int[] cfc, int[] NbElem) {
        // i et j sont les numéros des composantes à fusionner
        // en une seule composante qui portera le numéro le plus petit des deux
        if (NbElem[i] < NbElem[j]) {
            int aux = i;
            i = j;
            j = aux;
        }
        int s = prem[j];
        cfc[s] = i;
        while (pilch[s] != 0) {
            s = pilch[s];
            cfc[s] = i;
        }
        pilch[s] = prem[i];
        prem[i] = prem[j];
        NbElem[i] += NbElem[j];
        System.out.println(Arrays.toString(cfc));
    }

    public static void KruskalProf(Graphe g, Graphe t, int[] prem, int[] pilch, int[] cfc, int[] NbElem) {
        for (int i = 0; i < g.getNombreArretes(); i++) {
            t.setArete(i, new Arete(g.getArete(i).getDebut(), g.getArete(i).getFin(), g.getArete(i).getPoids()));
        }

        int x; //respectivement le numéro de composante de la 1ère extrémité de l'arête courante
        int y; //respectivement le numéro de composante de la 2ème extrémité de l'arête courante
        int i = 0, j = 0; //respectivement indice dans g et t
        while (j < g.getNombreSommets()-1) {
            System.out.println(i);
            Arete ar = g.getArete(i);
            x = cfc[ar.getDebut().getIndice()];
            y = cfc[ar.getFin().getIndice()];
            System.out.println(x + " " + y);
            if (x != y) {
                // t.a[j++] = g.a[i];
                t.setAreteKruskal(j++, ar);
                fusionnerProf(x, y, prem, pilch, cfc, NbElem);
            }
            i++;
        }
        t.setSommets(new Vector<>(g.getNombreSommets()));
        t.setArete(new Vector<>(g.getNombreSommets()-1));
    }

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

    public static void rang(Vector<Integer> fs, Vector<Integer> aps, int[] rang) {
        int n = aps.elementAt(0);
        int taillefs = fs.elementAt(0), s, h, t;
        System.out.println(taillefs);
        System.out.println(n);
        int[] ddi = new int[n + 1];
        int[] pilch = new int[n + 1];
        int[] prem = new int[n + 1];

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
        System.out.println(Arrays.toString(ddi));

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

        System.out.println(Arrays.toString(prem));
        System.out.println(Arrays.toString(pilch));
        System.out.println(Arrays.toString(rang));
    }

    static void empiler (int x, int[] pilch) // avec x dans {1, ... , n}
    {
        pilch[x] = pilch[0];
        pilch[0] = x;
    }
}
