import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
                        // Elle permet aussi de savoir de quel type d'enregistrement il s'agit.
                        // Si la première ligne n'est pas égale à 1 ou 0, mais à D, il s'agit d'un enregistrement détaillé. (On ne va donc pas lire le fichier de la même manière)
                        String lineEstOriente = br.readLine();
                        if (lineEstOriente.equals("1") || lineEstOriente.equals("0")) { // Lecture à partir d'un enregistrement simple (matrice d'adjacence)
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

                            // Créer l'objet Graphe courant à partir des données du fichier
                            boolean estOriente;
                            estOriente = !lineEstOriente.equals("0");
                            grapheCourant = new Graphe(data, estOriente);
                            System.out.println(grapheCourant);

                        } else if (lineEstOriente.equals("D")) { // Lecture à partir d'un enregistrement détaillé (listes)
                            String vraieLineEstOriente = br.readLine();
                            Vector<Sommet> dataSommets = new Vector<>();
                            Vector<Arete> dataAretes = new Vector<>();

                            String lineSommets = br.readLine();
                            String[] sSommets = lineSommets.split("\\|");
                            for (String s : sSommets) {
                                String[] data = s.split(" ");
                                dataSommets.add(new Sommet(data[0], Integer.parseInt(data[1])));
                            }

                            String lineAretes = br.readLine();
                            String[] sAretes = lineAretes.split("\\|");
                            for (String a : sAretes) {
                                String[] data = a.split(" ");
                                dataAretes.add(new Arete(dataSommets.get(Integer.parseInt(data[0])-1), dataSommets.get(Integer.parseInt(data[1])-1), Integer.parseInt(data[2])));
                            }

                            boolean estOriente;
                            estOriente = !vraieLineEstOriente.equals("0");
                            grapheCourant = new Graphe(estOriente, dataSommets, dataAretes);
                            System.out.println(grapheCourant);
                        }
                        br.close();
                        reader.close();

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
                MatAdj dialog = new MatAdj(MenuPrincipal.this);
                dialog.pack();
                dialog.setVisible(true);

                if (dialog.isOkPressed()) {
                    String sMat = dialog.getMat();
                    // System.out.println(mat);
                    String[] rows = sMat.split("\n");
                    // System.out.println(Arrays.toString(rows));

                    Vector<Vector<Integer>> mat = new Vector<>();
                    boolean estOriente = dialog.getOriente();
                    // parcourir chaque ligne de la chaîne
                    for (String row : rows) {
                            String[] elements = row.split(" ");
                            Vector<Integer> vectorRow = new Vector<>();

                            for (String element : elements) {
                                vectorRow.add(Integer.parseInt(element));
                            }
                            mat.add(vectorRow);
                    }

                    String name = dialog.getName();
                    Graphe nouveauGrapheCourant = new Graphe(mat, estOriente);
                    setGrapheCourant(nouveauGrapheCourant);

                    String sName = nouveauGrapheCourant.enregistrer(name); // Enregistrement du graphe dans le dossier "Graphes" à la racine du projet
                    labelNomGrapheCourant.setText(sName);

                    System.out.println(grapheCourant);
                    dialog.setOkPressed(false);

                }
            }
        });

        // Action Listener Bouton de création via Listes
        viaListesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Listes dialog = new Listes(MenuPrincipal.this);
                dialog.pack();
                dialog.setVisible(true);

                if (dialog.isOkPressed()) {
                    // Récupération et traduction des sommets
                    String sSommets = dialog.getSommets();
                    String[] tabSommets = sSommets.split(" ");
                    Vector<Sommet> sommets = new Vector<>();

                    for (int i = 0; i < tabSommets.length; i++) {
                        sommets.add(new Sommet(tabSommets[i], i+1));
                    }

                    // Récupération et traduction des arêtes
                    String sAretes = dialog.getAretes();
                    String[] tabAretes = sAretes.split("\n");
                    Vector<Arete> aretes = new Vector<>();

                    for (int i = 0; i < tabAretes.length; i++) {
                        String[] arete = tabAretes[i].split("-");
                        aretes.add(new Arete(sommets.get(Integer.parseInt(arete[0])-1), sommets.get(Integer.parseInt(arete[2])-1), Integer.parseInt(arete[1])));
                    }

                    String name = dialog.getName();
                    boolean estOriente = dialog.getOriente();
                    Graphe nouveauGrapheCourant = new Graphe(estOriente, sommets, aretes);
                    setGrapheCourant(nouveauGrapheCourant);

                    String sName = nouveauGrapheCourant.enregistrerDetails(name); // Enregistrement du graphe dans le dossier "Graphes" à la racine du projet
                    labelNomGrapheCourant.setText(sName);

                    dialog.setOkPressed(false);
                }

            }
        });

        voirLesAlgorithmesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = getLocation().x;
                int y = getLocation().y;
                int width = getWidth();
                int height = getHeight();

                MenuSecondaire menuSecondaire = new MenuSecondaire(MenuPrincipal.this, x, y, width, height, grapheCourant);
                dispose();
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
