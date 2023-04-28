import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Vector;

public class Tarjan {


    private int[] fs, aps, prem, pilch, cfc, pred, num, ro;
    private Deque<Integer> tarj = new ArrayDeque<Integer>();
    private boolean[] entarj;
    private int p, n, cfcCount;

    public Tarjan(Graphe g) {
        this.fs = convertirVectorEnTableau(g.getFs());
        this.aps = convertirVectorEnTableau(g.getAps());
        p = 0;
        cfcCount = 0;

        // n = nb sommet du graphe
        n = aps[0];

        // Initialisation des tableaux
        prem = new int[n + 1];
        pilch = new int[n + 1];
        cfc = new int[n + 1];
        pred = new int[n + 1];
        entarj = new boolean[n + 1];
        num = new int[n + 1];
        ro = new int[n + 1];
    }

    public void fortconnexe() {
        int k = 0;
        for (int i = 1; i <= n; i++) {
            num[i] = 0;
            pred[i] = 0;
            ro[i] = 0;
            entarj[i] = false;// t
        }

        pilch[0] = 0;

        for (int s = 1; s <= aps[0]; s++)
            if (num[s] == 0)
                traversee(s);
        prem[0] = k;
    }

    // s = un sommet
    public void traversee(int s) {
        int t;
        p++;
        num[s] = p; // numérote s et initialise num[s]
        ro[s] = p; // numérote s et initialise ro[s]
        tarj.push(s); // On ajoute le sommet qu'on traite à la pile tarj
        entarj[s] = true;

        for (int k = aps[s]; (t = fs[k]) != 0; k++) {
            // si t n'est pas encore numéroté
            if (num[t] == 0) {
                pred[t] = s;
                traversee(t);
                if (ro[t] < ro[s])
                    ro[s] = ro[t];
            } else {
                if ((num[t] < ro[s]) && entarj[t])
                    ro[s] = num[t];
            }
        }

        if (ro[s] == num[s]) {
            cfcCount++;

            int u;
            do {
                u = tarj.pop();
                entarj[u] = false;
                empiler(u);
                cfc[u] = cfcCount;
            } while (u != s);

            prem[cfcCount] = pilch[0];
            pilch[0] = 0;
        }
    }

    private void empiler(int valeur) {
        pilch[valeur] = pilch[0];
        pilch[0] = valeur;
    }

    public void afficherTableaux() {

        System.out.println("\n\n--------- PRED ---------");
        for (int value : pred)
            System.out.print(value + " ");

        System.out.println("\n\n--------- PREM ---------");
        for (int value : prem)
            System.out.print(value + " ");

        System.out.println("\n\n--------- PILCH ---------");
        for (int value : pilch)
            System.out.print(value + " ");

        System.out.println("\n\n--------- CFC ---------");
        for (int value : cfc)
            System.out.print(value + " ");

    }

    public int[] convertirVectorEnTableau(Vector<Integer> vector) {
        int[] tableau = new int[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            tableau[i] = vector.get(i);
        }
        return tableau;
    }
}
