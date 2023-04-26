import java.util.Arrays;
import java.util.Vector;

public class Algo {



    public static int[][] CalculeDistance(Graphe g) {
        // Convertis la matrice d'adjacence en représentation FS-APS
        g.matAdjToLists();

        // Initialiser la matrice de distances avec les val de la matrice d'adjacence
        int[][] dist = new int[g.getNombreSommets()+1][g.getNombreSommets()+1];
        for (int i = 0; i < g.getNombreSommets(); i++) {
            for (int j = 0; j < g.getNombreSommets(); j++) {
                dist[i][j] = g.getMatAdj().get(i).get(j);
            }
        }
        // Appliquer l'algorithme de Floyd-Warshall
        for (int k = 0; k < g.getNombreSommets(); k++) {
            for (int i = 0; i < g.getNombreSommets(); i++) {
                for (int j = 0; j < g.getNombreSommets(); j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        // Retourner la matrice de distances
        //
        System.out.println("MAtrice des distances");
        System.out.println(Arrays.deepToString(dist));
        return dist;
    }

    public void Dijkstra(Vector<Integer> fs, Vector<Integer> aps, int[][] p, int s) {
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
    }

}
