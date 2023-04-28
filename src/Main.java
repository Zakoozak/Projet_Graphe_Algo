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
        System.out.println(graphePrufer);
        // Prufer(graphePrufer.getMatAdj());

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
}
