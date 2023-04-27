import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class Listes extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox checkBoxOriente;
    private JTextField textFieldSommets;
    private JTextArea textAreaAretes;
    private JTextField textFieldNom;
    private JButton copierDataButton;
    private boolean okPressed;

    public Listes(JFrame mainWindow) {
        super(mainWindow,"Création via Listes", true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        copierDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldSommets.setText("Paris Marseille Lyon Toulouse Nice Nantes Strasbourg Montpellier Bordeaux");
                textAreaAretes.setText("""
                        1/775/2
                        1/400/3
                        1/680/4
                        1/930/5
                        1/385/6
                        1/480/7
                        1/765/8
                        1/580/9
                        2/320/3
                        2/320/4
                        2/650/5
                        2/200/7
                        2/160/8
                        3/530/4
                        3/320/5
                        3/690/6
                        3/390/7
                        3/300/8
                        3/500/9
                        4/600/5
                        4/520/6
                        4/240/8
                        4/240/9
                        5/570/8
                        6/300/9
                        8/550/9""");
                pack();
            }
        });

        textAreaAretes.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                pack();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                pack();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                pack();
            }
        });
    }

    private void onOK() {
        okPressed = true;
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        Listes dialog = new Listes(new JFrame());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public boolean isOkPressed() {
        return okPressed;
    }

    public void setOkPressed(boolean okPressed) {
        this.okPressed = okPressed;
    }

    public boolean getOriente() {
        return checkBoxOriente.isSelected();
    }

    public String getSommets() {
        return textFieldSommets.getText();
    }

    public String getAretes() {
        return textAreaAretes.getText();
    }

    public String getName() {
        return textFieldNom.getText();
    }
}
