import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FloydWarshall extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JEditorPane editorPanePB;
    private JButton obtenirButton;
    private JEditorPane editorPaneRES;
    private JTextPane textPaneRes;

    public FloydWarshall(Graphe grapheCourant) {
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
                // Pas de conditions à vérifier, car notre graphe est techniquement toujours pondéré (avec des poids de 0 si pas définis).
                Vector<Vector<Integer>> matDist = Algo.FloydWarshall(grapheCourant);
                StringBuilder sMat = new StringBuilder();
                for (Vector<Integer> vec : matDist) {
                    for (int i = 0; i < vec.size(); i++) {
                        String infinite;
                        if (vec.get(i) == Integer.MAX_VALUE) infinite = "∞";
                        else infinite = String.valueOf(vec.get(i));
                        sMat.append(infinite).append(" ");
                        if (i == vec.size()-1)
                            sMat.append('\n');
                    }
                }

                editorPaneRES.setText(String.valueOf(sMat));
                FloydWarshall.this.pack();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
