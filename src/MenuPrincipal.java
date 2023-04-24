import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class MenuPrincipal extends JFrame {
    private Graphe grapheCourant;
    private JButton viaUnFichierButton;
    private JButton viaListesButton;
    private JButton viaFsEtApsButton;
    private JButton viaMatriceDAdjacenceButton;
    private JButton voirLesAlgorithmesButton;
    private JPanel panneauMenuPrincipal;
    private JLabel labelNomGrapheCourant;

    public MenuPrincipal() {
        // Options de base de la fenêtre
        setContentPane(panneauMenuPrincipal);
        setTitle("Menu Principal");
        setSize(800, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Affichage d'un msg si pas de graphe courant dans l'application
        if (grapheCourant == null) {
            labelNomGrapheCourant.setText("Pas de graphe courant, veuillez en importer ou en créer un !");
        }

        // Action Listener Bouton d'importation
        viaUnFichierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(MenuPrincipal.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Récupérer le fichier sélectionné
                    java.io.File file = fileChooser.getSelectedFile();
                    labelNomGrapheCourant.setText(file.getAbsolutePath());

                    try {
                        // Lire le contenu du fichier
                        FileReader reader = new FileReader(file);
                        BufferedReader br = new BufferedReader(reader);
                        // La première ligne nous indique si le graphe est orienté ou non ==> 1 = Oui / 0 = Non
                        String lineEstOriente = br.readLine();
                        // Créer un tableau 2D de vecteurs qui va contenir les données de notre matrice d'adjacence
                        Vector<Vector<Integer>> data = new Vector<>();
                        String linesMat;
                        while ((linesMat = br.readLine()) != null) {
                            // Créer un vecteur pour la ligne courante et y stocker les données
                            Vector<Integer> lineCourante = new Vector<Integer>();
                            StringTokenizer st = new StringTokenizer(linesMat, " ");
                            while (st.hasMoreTokens()) {
                                lineCourante.add(Integer.valueOf(st.nextToken()));
                            }
                            // Rajouter les données à la matrice
                            data.add(lineCourante);
                        }
                        br.close();
                        reader.close();

                        // Créer l'objet Graphe courant à partir des données du fichier
                        boolean estOriente;
                        estOriente = !lineEstOriente.equals("0");
                        grapheCourant = new Graphe(data, estOriente);
                        System.out.println(grapheCourant);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Action Listener Bouton de création via Fs et Aps
        viaFsEtApsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FsAps dialog = new FsAps(MenuPrincipal.this);
                dialog.pack();
                dialog.setVisible(true);

                if (dialog.isOkPressed()) {
                    boolean estOriente = dialog.getOriente();
                    String sFs = dialog.getFs();
                    String sAps = dialog.getAps();
                    String name = dialog.getName();

                    Vector<Integer> fs = stringToVector(sFs);
                    Vector<Integer> aps = stringToVector(sAps);

                    Graphe nouveauGrapheCourant = new Graphe(fs, aps, estOriente);
                    setGrapheCourant(nouveauGrapheCourant);

                    String sName = nouveauGrapheCourant.enregistrer(name); // Enregistrement du graphe dans le dossier "Graphes" à la racine du projet
                    labelNomGrapheCourant.setText(sName);

                    System.out.println(grapheCourant);
                    dialog.setOkPressed(false);
                }
            }
        });

        // Action Listener Bouton de création via Matrice d'adjacence
        viaMatriceDAdjacenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Action Listener Bouton de création via Listes
        viaListesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static Vector<Integer> stringToVector(String input) {
        Vector<Integer> output = new Vector<Integer>();
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            output.add(Integer.parseInt(token));
        }
        return output;
    }

    public void setGrapheCourant(Graphe grapheCourant) {
        this.grapheCourant = grapheCourant;
    }

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
    }
}
