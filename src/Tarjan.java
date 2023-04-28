import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Vector;

public class Tarjan {


    private final int[] fs;
    private final int[] aps;
    private final int[] prem;
    private final int[] pilch;
    private final int[] cfc;
    private final int[] pred;
    private final int[] num;
    private final int[] ro;
    private final Deque<Integer> tarj = new ArrayDeque<Integer>();
    private final boolean[] dansTarj;
    private int p;
    private final int n;
    private int cfcCount;

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
        dansTarj = new boolean[n + 1];
        num = new int[n + 1];
        ro = new int[n + 1];
    }

    public void fortconnexe() {
        int k = 0;
        for (int i = 1; i <= n; i++) {
            num[i] = 0;
            pred[i] = 0;
            ro[i] = 0;
            dansTarj[i] = false;// t
        }

        pilch[0] = 0;

        for (int s = 1; s <= aps[0]; s++)
            if (num[s] == 0)
                traversee(s);
        prem[0] = k;
    }

    // s = un sommet
    public void traversee(int sommet) {
        int t;
        p++;
        ro[sommet] = p; // numérote s et initialise ro[sommet]
        num[sommet] = p; // mm chose pour num[sommet]
        tarj.push(sommet); // Ajoute le sommet courant dans pile Tarj
        dansTarj[sommet] = true;

        for (int k = aps[sommet]; (t = fs[k]) != 0; k++) {
            // si t n'est pas encore numéroté
            if (num[t] == 0) {
                pred[t] = sommet;
                traversee(t);
                if (ro[t] < ro[sommet])
                    ro[sommet] = ro[t];
            } else {
                if ((num[t] < ro[sommet]) && dansTarj[t])
                    ro[sommet] = num[t];
            }
        }

        if (ro[sommet] == num[sommet]) {
            cfcCount++;
            int u;
            do {
                u = tarj.pop();
                dansTarj[u] = false;
                empiler(u);
                cfc[u] = cfcCount;
            } while (u != sommet);

            prem[cfcCount] = pilch[0];
            pilch[0] = 0;
        }
    }

    private void empiler(int valeur) {
        pilch[valeur] = pilch[0];
        pilch[0] = valeur;
    }

    public void afficherTableaux() {

        System.out.println("\nTableau pred :");
        System.out.println(Arrays.toString(getPred()));

        System.out.println("\nTableau Prem :");
        System.out.println(Arrays.toString(getPrem()));

//        System.out.println("\n\n Tableau pilch");
//        System.out.println(Arrays.toString(pilch));

        System.out.println("\n Tableau CFC : ");
        System.out.println(Arrays.toString(getCfc()));
    }

    public int[] getFs() {
        return fs;
    }

    public int[] getAps() {
        return aps;
    }

    public int[] getPrem() {
        return prem;
    }

    public int[] getPilch() {
        return pilch;
    }

    public int[] getCfc() {
        return cfc;
    }

    public int[] getPred() {
        return pred;
    }

    public int[] getNum() {
        return num;
    }

    public int[] getRo() {
        return ro;
    }

    public Deque<Integer> getTarj() {
        return tarj;
    }

    public boolean[] getDansTarj() {
        return dansTarj;
    }

    public int getP() {
        return p;
    }

    public int getN() {
        return n;
    }

    public int getCfcCount() {
        return cfcCount;
    }

    public int[] convertirVectorEnTableau(Vector<Integer> vector) {
        int[] tableau = new int[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            tableau[i] = vector.get(i);
        }
        return tableau;
    }
}
