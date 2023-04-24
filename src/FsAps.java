import javax.swing.*;
import java.awt.event.*;

public class FsAps extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldFs;
    private JTextField textFieldAps;
    private JButton copierButton;
    private JTextField textFieldName;
    private JCheckBox checkBoxOriente;
    private boolean okPressed;

    public FsAps(JFrame mainWindow) {
        super(mainWindow,"Création via Fs et Aps", true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        copierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldFs.setText("13 2 3 4 0 2 3 4 0 5 0 0 4 0");
                textFieldAps.setText("5 1 5 9 11 12");
            }
        });

        // Action Listener OK
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // Action Listener Cancel
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
    }

    private void onOK() {
        okPressed = true;
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public String getFs() {
        return textFieldFs.getText();
    }

    public String getAps() {
        return textFieldAps.getText();
    }

    public boolean getOriente() {
        return checkBoxOriente.isSelected();
    }

    public String getName() {
        return textFieldName.getText();
    }

    public static void main(String[] args) {
        FsAps dialog = new FsAps(new JFrame());
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
}
