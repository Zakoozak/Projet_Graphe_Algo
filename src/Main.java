import javax.swing.*;
import java.util.Arrays;
import java.util.Vector;





public class Main {

    public static void main(String[] args) {
        // Tests Fs Aps
        Vector<Integer> fs = new Vector<>(Arrays.asList(13, 2, 3, 4, 0, 2, 3, 4, 0, 5, 0, 0, 4, 0));
        Vector<Integer> aps = new Vector<>(Arrays.asList(5, 1, 5, 9, 11, 12));
        /*
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

        //Test Dijkstra
        int [][]p = new int[10][10];
        int [] pr;
        int [] d;
        int [] t;
        int s = 1; /*Sommet de depart*/

        Dikjstra(fs, aps, p, s);


    }

    private static void Dikjstra(Vector<Integer> fs, Vector<Integer> aps, int[][] p, int s) {
        int MAXPOIDS = 100;
        int ind;
        int i, j = 0, k, v;
        int n = aps.elementAt(0);
        int m = fs.elementAt(0);
        int [] pr = new int[n + 1];
        int [] d = new int[n + 1];
        int[] inS = new int[n + 1]; // sert a dire quels sont les sommets qui restent a traiter inS[i] = 0 ou 1

        // initialisation des tableaux d, pr et inS
        for (i = 1; i <= n; i++) {
            d[i] = p[s][i];
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
                    v = d[j] + p[j][fs.elementAt(k)];
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

}
