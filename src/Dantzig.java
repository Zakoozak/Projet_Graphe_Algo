import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dantzig extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JEditorPane editorPanePB;
    private JButton obtenirButton;
    private JEditorPane editorPaneRES;

    public Dantzig(Graphe grapheCourant) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
