import java.util.Vector;

public class Algo {

    public int[][] calculeDistance(Graphe g) { // avec Floyd-Warshall
        int n = g.getNombreSommets();
        int[][] distance = new int[n][n];

        // Initialisation de la matrice de distance
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Remplissage de la matrice de distance avec les arêtes du graphe
        Vector<Arete> aretes = g.getAretes();
        for (Arete a : aretes) {
            int u = a.getDebut().getIndice();
            int v = a.getFin().getIndice();
            int poids = a.getPoids();
            distance[u][v] = poids;
            if (!g.estOriente()) {
                distance[v][u] = poids;
            }
        }

        // Calcul des distances minimales avec l'algorithme de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE
                            && distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        return distance;
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
