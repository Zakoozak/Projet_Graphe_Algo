import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class MatAdj extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textAreaMatrice;
    private JTextField textFieldName;
    private JButton copierButton;
    private JCheckBox checkBoxOriente;
    private boolean okPressed;

    public MatAdj(JFrame mainWindow) {
        super(mainWindow,"Création via Matrice d'adjacence", true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        copierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaMatrice.setText("""
                        5 8 0 0 0 0
                        0 0 1 1 1 0
                        0 0 1 1 1 0
                        0 0 0 0 0 1
                        0 0 0 0 0 0
                        0 0 0 0 1 0""");
                pack();
            }
        });

        textAreaMatrice.getDocument().addDocumentListener(new DocumentListener() {
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
        textAreaMatrice.addComponentListener(new ComponentAdapter() {
        });
        textAreaMatrice.addContainerListener(new ContainerAdapter() {
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
        MatAdj dialog = new MatAdj(new JFrame());
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

    public String getName() {
        return textFieldName.getText();
    }

    public String getMat() {
        return textAreaMatrice.getText();
    }

    public boolean getOriente() {
        return checkBoxOriente.isSelected();
    }

}
