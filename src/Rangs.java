import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Rangs extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JEditorPane editorPanePB;
    private JButton obtenirButton;
    private JEditorPane editorPaneRES;

    public Rangs(Graphe grapheCourant) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        obtenirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                if (grapheCourant.contientCycle()) {
                    editorPaneRES.setText("Le graphe contient un cycle ou n'est pas orienté, impossible de déterminer les rangs des sommets dans ces conditions !");
                    Rangs.this.pack();
                } else {
                }

                 */

                int[][] resRang = Algo.Rangs(grapheCourant.getFs(), grapheCourant.getAps());
                System.out.println(Arrays.deepToString(resRang));
                StringBuilder s = new StringBuilder();

                s.append("Tableau prem : \n");
                for (int i = 0; i < resRang[0].length; i++) {
                    s.append(resRang[0][i]).append(" ");
                }

                s.append("\nTableau pilch : \n");
                for (int i = 0; i < resRang[1].length; i++) {
                    s.append(resRang[1][i]).append(" ");
                }

                s.append("\nTableau des rangs : \n");
                for (int i = 0; i < resRang[2].length; i++) {
                    s.append(resRang[2][i]).append(" ");
                }

                editorPaneRES.setText(s.toString());
                Rangs.this.pack();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
