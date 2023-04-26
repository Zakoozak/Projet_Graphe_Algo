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
}
