import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MenuSecondaire extends JFrame {
    private Graphe grapheCourant;
    private JPanel panneauMenuSecondaire;
    private JButton menuPrincipalButton;
    private JButton rangsButton;
    private JButton tarjanButton;
    private JButton ordonnancementButton;
    private JButton kruskalButton;
    private JButton pruferButton;
    private JButton dijkstraButton;
    private JButton dantzigButton;
    private JButton floydWarshallButton;
    private JPanel panneauDessinGraphe;
    private JButton generateNewButton;
    private DessinGraphe dessinGraphe;

    public MenuSecondaire(MenuPrincipal mainFrame, int x, int y, int width, int height, Graphe grapheCourant) {
        this.grapheCourant = grapheCourant;
        dessinGraphe.setSommets(this.grapheCourant.getSommets());
        dessinGraphe.setAretes(this.grapheCourant.getAretes());
        dessinGraphe.setEstOriente(this.grapheCourant.estOriente());
        dessinGraphe.repaint();
        // Options de base de la fenêtre
        setContentPane(panneauMenuSecondaire);
        setLocation(x, y);
        setSize(width, height);
        setTitle("Menu Secondaire");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        generateNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                // Générer de nouvelles positions de départ pour les sommets du graphe courant
                for (Sommet s : MenuSecondaire.this.grapheCourant.getSommets()) {
                    s.setPosition(new Point(random.nextInt((1600) + 1) + 100, random.nextInt((700) + 1) + 100));
                }

                dessinGraphe.setSommets(MenuSecondaire.this.grapheCourant.getSommets());
                dessinGraphe.repaint(); // On redessine le graphe avec les nouvelles coordonnées des sommets
            }
        });

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

        rangsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        tarjanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ordonnancementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        pruferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Prufer dialog = new Prufer(MenuSecondaire.this.grapheCourant);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        kruskalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dijkstra dialog = new Dijkstra(MenuSecondaire.this.grapheCourant);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        dantzigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        floydWarshallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FloydWarshall dialog = new FloydWarshall(MenuSecondaire.this.grapheCourant);
                dialog.pack();
                dialog.setVisible(true);

            }
        });
    }

    public void setGrapheCourant(Graphe grapheCourant) {
        this.grapheCourant = grapheCourant;
    }

    public Graphe getGrapheCourant() {
        return grapheCourant;
    }
}
