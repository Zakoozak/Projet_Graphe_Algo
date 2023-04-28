import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prufer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JEditorPane editorPanePB;
    private JButton obtenirButton;
    private JEditorPane editorPaneRES;

    public Prufer(Graphe grapheCourant) {
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
                // Il faudrait faire une fonction booléenne sur si le graphe est un arbre !!
                int[] prf = Algo.Prufer(grapheCourant.getMatAdj());
                StringBuilder s = new StringBuilder("Séquence de Prüfer : \n");
                for (int j : prf) {
                    s.append(j).append(" ");
                }
                editorPaneRES.setText(s.toString());
                Prufer.this.pack();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
