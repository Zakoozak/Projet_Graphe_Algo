import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSecondaire extends JFrame {
    private JPanel panneauMenuSecondaire;
    private JButton menuPrincipalButton;
    private JButton rangsButton;
    private JButton tarjanButton;
    private JButton ordonnancementButton;
    private JButton kruskalButton;
    private JButton pruferButton;
    private JButton dijkstraButton;
    private JButton dantzigButton;
    private JPanel panneauDessinGraph;
    private JButton calculDistancesButton;

    public MenuSecondaire(MenuPrincipal mainFrame, int x, int y, int width, int height) {
        // Options de base de la fenêtre
        setContentPane(panneauMenuSecondaire);
        setLocation(x, y);
        setSize(width, height);
        setTitle("Menu Secondaire");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = getLocation().x;
                int y = getLocation().y;
                int width = getWidth();
                int height = getHeight();
                dispose();
                mainFrame.setSize(width, height);
                mainFrame.setLocation(x, y);
                mainFrame.setVisible(true);

            }
        });
    }
}
