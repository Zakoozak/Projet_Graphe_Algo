import javax.swing.*;
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

        // Test Dijkstra
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
        Dikjstra(fs, aps, p, 1);

        // Test Prüfer
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
        prufer(graphePrufer.getMatAdj());

    }

    private static void Dikjstra(Vector<Integer> fs, Vector<Integer> aps, int[][] poids, int s) {
        int MAXPOIDS = 100;
        int ind;
        int i, j = 0, k, v;
        int n = aps.elementAt(0);
        int m = fs.elementAt(0);
        int[] pr = new int[n];
        int[] d = new int[n];
        int[] inS = new int[n + 1]; // sert a dire quels sont les sommets qui restent a traiter inS[i] = 0 ou 1

        // initialisation des tableaux d, pr et inS
        for (i = 1; i < n; i++) {
            d[i] = poids[s][i];
            inS[i] = 1;
            pr[i] = -1;
        }

        d[s] = 0;
        pr[s] = 0;
        inS[s] = 0; // on supprime le sommet s (ici 1) car on traite le sommet 1 en premier
        ind = n - 1;

        while (ind > 0) {
            // calcul du minimum selon d des sommets de inS
            m = MAXPOIDS;
            for (i = 1; i <= n; i++){
                if (inS[i] == 1) {
                    if (d[i] < m) {
                        m = d[i];
                        j = i;
                    }
                }
            }
            if (m == MAXPOIDS) {
                return;
            }

            inS[j] = 0;
            ind--;
            k = aps.elementAt(j);

            while (fs.elementAt(k) != 0) {
                if (inS[fs.elementAt(k)] == 1) {
                    v = d[j] + poids[j][fs.elementAt(k)];
                    if (v < d[fs.elementAt(k)]) {
                        d[fs.elementAt(k)] = v;
                        pr[fs.elementAt(k)] = j;
                    }
                }
                k++;
            }
        }
        System.out.println(Arrays.toString(pr));
        System.out.println(Arrays.toString(d));
    }

    public static int[] prufer(Vector<Vector<Integer>> a) {
        System.out.println(a);
        int nbSom = a.elementAt(0).elementAt(0);
        int[] prf = new int[nbSom-1];
        prf[0] = nbSom - 2;
        int k = 1;
        while (k <= nbSom - 2) {
            int i = 1;
            System.out.println(a.elementAt(2).elementAt(0));
            while ((a.elementAt(i).elementAt(0)) != 1) {
                i++;
            }
            int j = 1;
            while ((a.get(i).get(j)) != 1) j++;
            prf[k++] = j;
            a.elementAt(i).setElementAt(0, j);
            a.elementAt(j).setElementAt(0, i);
            a.elementAt(i).setElementAt(0, 0);
            // a.get(j).setElementAt(0, a.get(j).get(0) - 1);
        }

        return prf;
    }

}
