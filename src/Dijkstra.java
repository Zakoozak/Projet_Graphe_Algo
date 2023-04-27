import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Dijkstra extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JEditorPane editorPanePB;
    private JButton obtenirButton;
    private JEditorPane editorPaneRES;
    private JComboBox<Integer> comboBoxSommet;

    public Dijkstra(Graphe grapheCourant) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for (Sommet s : grapheCourant.getSommets()) {
            comboBoxSommet.addItem(s.getIndice());
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        obtenirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSommet = (int) comboBoxSommet.getSelectedItem();
                if (grapheCourant.possedeAreteNegative()) {
                    editorPaneRES.setText("Le graphe possède une ou plusieurs arêtes avec des pondération négative. Impossible d'appliquer Dijkstra !");
                    Dijkstra.this.pack();
                } else {
                    grapheCourant.creerP();
                    Vector<Vector<Integer>> res = Algo.Dikjstra(grapheCourant.getFs(), grapheCourant.getAps(), grapheCourant.getP(), indiceSommet);
                    System.out.println(res);
                    StringBuilder s = new StringBuilder();
                    Vector<Integer> d = res.get(0);
                    Vector<Integer> pr = res.get(1);
                    s.append("Tableau des distances : \n");
                    for (int dist : d) {
                        String infinite;
                        if (dist == Integer.MAX_VALUE) infinite = "∞";
                        else infinite = String.valueOf(dist);
                        s.append(infinite).append(" ");
                    }
                    s.append("\n\nTableau des prédécesseurs : \n");
                    for (int pred : pr) {
                        s.append(pred).append(" ");
                    }

                    editorPaneRES.setText(s.toString());
                    Dijkstra.this.pack();
                }

            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
