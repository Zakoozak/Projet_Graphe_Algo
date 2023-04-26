import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class Graphe {
    private Vector<Sommet> sommets;
    private Vector<Arete> aretes;
    private Vector<Integer> fs;
    private Vector<Integer> aps;
    private Vector<Vector<Integer>> matAdj;
    private boolean estOriente;

    // CONSTRUCTEURS
    // Via Matrice d'adjacence
    public Graphe(Vector<Vector<Integer>> matAdj, boolean estOriente) {
        this.matAdj = matAdj;
        this.estOriente = estOriente;
        // Intégrité des données, on génère fs et aps mais également les listes des sommets et des arêtes du graphe
        matAdjToFsAps();
        matAdjToLists();
    }

    // Via Listes
    public Graphe(boolean estOriente, Vector<Sommet> sommets, Vector<Arete> aretes) {
        int nbSommets = sommets.size();
        int nbAretes = aretes.size();
        this.fs = new Vector<Integer>(nbAretes);
        this.aps = new Vector<Integer>(nbSommets + 1);
        this.matAdj = null;
        this.sommets = sommets;
        this.aretes = aretes;
        this.estOriente = estOriente;
        // Intégrité des données
        listsToMatAdj();
        matAdjToFsAps();
    }

    // Via Fs et Aps
    public Graphe(Vector<Integer> fs, Vector<Integer> aps, boolean estOriente) {
        this.fs = fs;
        this.aps = aps;
        this.matAdj = null;
        this.sommets = null;
        this.aretes = null;
        this.estOriente = estOriente;
        // Intégrité des données
        fsApsToMatAdj();
        matAdjToLists();
    }

    public void setSommets(int indice, Sommet sommet) {
        if (indice < 0 || indice > sommets.size()) {
            System.out.println("arrete de te foutre de ma gueule stp");
        } else {
            sommets.set(indice, sommet);
        }
    }

    public void setEstOriente(boolean estOriente) {
        this.estOriente = estOriente;
    }

    // Fonction d'intégrité des données
    void fsApsToMatAdj() {
        int n = aps.elementAt(0);
        // Initialisation de la matrice
        matAdj = new Vector<>();
        for (int i = 0; i < n+1; i++) {
            Vector<Integer> innerVector = new Vector<>(Collections.nCopies(n+1, 0));
            matAdj.add(innerVector);
        }
        // Remplissage
        int i = 1;
        for (int k = 1; k < fs.elementAt(0); k++) {
            if (fs.elementAt(k) != 0) {
                matAdj.elementAt(i).setElementAt(1, fs.elementAt(k));
            } else {
                i++;
            }
        }
        // n et m
        matAdj.elementAt(0).setElementAt(aps.elementAt(0), 0); // n = nombre de sommets

        // Compter le nombre de 1 dans la matrice → somme du tableau ddi
        Vector<Integer> ddi = detDdiFsAps();
        int sommeDdi = ddi.stream().mapToInt(Integer::intValue).sum(); // Calcul de la somme
        sommeDdi = sommeDdi - ddi.elementAt(0); // On enlève la valeur du premier élément de ddi puisqu'il s'agit de la taille de DDI (rien à voir avec le nombre d'arêtes)
        matAdj.elementAt(0).setElementAt(sommeDdi, 1); // m
    }

    Vector<Integer> detDdiFsAps() {
        int n = aps.elementAt(0);
        Vector<Integer> ddi = new Vector<>(Collections.nCopies(n+1, 0));
        for (int i = 1; i < fs.elementAt(0); i++) {
            ddi.setElementAt(ddi.elementAt(fs.elementAt(i)) + 1, fs.elementAt(i));
        }
        return ddi;
    }

    void matAdjToFsAps() {
        int n = matAdj.elementAt(0).elementAt(0); // Nombre de sommet
        int m = matAdj.elementAt(0).elementAt(1); // Nombre d'arc

        fs = new Vector<>(Collections.nCopies(n + m + 1, 0));
        fs.setElementAt(n + m, 0);

        aps = new Vector<>(Collections.nCopies(n + 1, 0));
        aps.setElementAt(n, 0);

        int k = 1;
        for (int i = 1; i <= n; i++) {
            aps.setElementAt(k, i);
            for (int j = 1; j <= n; j++) {
                if (matAdj.elementAt(i).elementAt(j) != 0) {
                    fs.setElementAt(j, k);
                    k++;
                }
            }
            fs.setElementAt(0, k);
            k++;
        }
    }

    void matAdjToLists() {
        int n = matAdj.elementAt(0).elementAt(0); // Nombre de sommet

        // Remplissage liste des sommets
        sommets = new Vector<>();
        for (int i = 1; i < n + 1; i++) {
            sommets.add(new Sommet(i));
        }

        // Remplissage liste des arêtes
        aretes = new Vector<>();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (matAdj.elementAt(i).elementAt(j) == 1) {
                    Sommet s1 = null;
                    Sommet s2 = null;

                    for (int k = 0; k < sommets.size(); k++) {
                        if (sommets.elementAt(k).getIndice() == i)
                            s1 = sommets.elementAt(k);
                        if (sommets.elementAt(k).getIndice() == j)
                            s2 = sommets.elementAt(k);
                    }
                    aretes.add(new Arete(s1, s2));

                }
            }
        }
    }

    void listsToMatAdj() {
        int nbSommet = sommets.size();
        int nbAretes = aretes.size();

        matAdj = new Vector<>();
        for (int i = 0; i < nbSommet+1; i++) {
            Vector<Integer> innerVector = new Vector<>(Collections.nCopies(nbSommet+1, 0));
            matAdj.add(innerVector);
        }

        // Insertion du nombre de sommets et d'arêtes dans la matrice
        matAdj.elementAt(0).setElementAt(nbSommet, 0); // n = nombre de sommets
        matAdj.elementAt(0).setElementAt(nbAretes, 1); // n = nombre de sommets

        // Remplissage du reste
        for (Arete a : aretes) {
            matAdj.elementAt(a.getDebut().getIndice()).setElementAt(1, a.getFin().getIndice());
        }
    }

    // AUTRES FONCTIONS
    //TODO
    public void suppSommet(int indice) {
        sommets.remove(indice);        // Supprimer le sommet de la liste des sommets


        // Supprimer les arrêttes qui sont reliées au sommet à supprimzr
        for (int i = 0; i < aretes.size(); i++) {
            Arete a = aretes.get(i);
            if (a.getDebut().getIndice() == indice || a.getFin().getIndice() == indice) {
                aretes.remove(i);
                i--;
            }
        }

        // Réindexe les sommets restants
        for (int i = indice; i < sommets.size(); i++) {
            Sommet s = sommets.get(i);
            s.setIndice(s.getIndice() - 1);
        }
    }

    //TODO
    public void addSommet(Sommet sommet) {
        // Ajouter le sommet à la liste des sommets
        sommets.add(sommet);

        // Ajouter une ligne et une colonne dans la matrice d'adjacence  /// PEUT ETRE FAUX JSP
        int n = sommets.size();
        Vector<Integer> ligne = new Vector<Integer>(n);
        for (int i = 0; i < n; i++) {
            ligne.add(0);
        }
        matAdj.add(ligne);
        for (int i = 0; i < n-1; i++) {
            matAdj.get(i).add(0);
        }

        // Ajouter une entrée dans le vecteur fs
        fs.add(0);

    }

    //TODO
    public void suppArrete(int indice) {
        // Supprimer l'arête de la liste des arêtes
        aretes.remove(indice);

        // Mettre à jour la matrice d'adjacence  /// PEUT ETRE FAUX JSP
        Arete a = aretes.get(indice);
        int i = a.getDebut().getIndice();
        int j = a.getFin().getIndice();
        if (estOriente) {
            matAdj.get(i).set(j, 0);
        } else {
            matAdj.get(i).set(j, 0);
            matAdj.get(j).set(i, 0);
        }

    }

    //TODO
    public void addArrete(Arete arrete) {
        // Ajouter l'arête à la liste des arêtes
        aretes.add(arrete);

        // Mettre à jour la matrice d'adjacence /// PEUT ETRE FAUX JSP
        int i = arrete.getDebut().getIndice();
        int j = arrete.getFin().getIndice();
        if (estOriente) {
            matAdj.get(i).set(j, 1);
        } else {
            matAdj.get(i).set(j, 1);
            matAdj.get(j).set(i, 1);
        }
    }

    public int getNombreSommets() {
        return this.sommets.size();
    }

    public int getNombreArretes() {
        return this.aretes.size();
    }

    public Vector<Sommet> getSommets() {
        return this.sommets;
    }

    public Vector<Arete> getAretes() {
        return this.aretes;
    }

    public void setArretes(int indice, Arete arrete) {
        if (indice < 0 || indice > aretes.size()) {
            System.out.println("arrete de te foutre de ma gueule stp");
            return;
        } else {
            aretes.set(indice, arrete);
        }
    }

    public boolean estOriente() {
        return this.estOriente;
    }

    public void setOriente(Boolean bool) {
        this.estOriente = bool;
    }

    public Vector<Integer> getFs() {
        return this.fs;
    }

    public void setFs(Vector<Integer> fs) {
        this.fs = fs;
    }

    public Vector<Integer> getAps() {
        return this.aps;
    }

    public void setAps(Vector<Integer> aps) {
        this.aps = aps;
    }

    public Vector<Vector<Integer>> getMatAdj() {
        return this.matAdj;
    }

    public void setMatAdj(Vector<Vector<Integer>> matAdj) {
        this.matAdj = matAdj;
    }

    @Override
    public String toString() {
        return
                "sommets=" + sommets +
                "\narretes=" + aretes +
                "\nfs=" + fs +
                "\naps=" + aps +
                "\nmatAdj=" + matAdj.toString() +
                "\nestOriente=" + estOriente +
                '}';
    }

    public String enregistrer(String nomFichier) {
        String absolutePath = "";
        try {
            File file = new File("Graphes/" + nomFichier);
            absolutePath = file.getAbsolutePath();
            FileWriter writer = new FileWriter(file);

            if (estOriente)
                writer.write("1\n");
            else
                writer.write("0\n");

            for (int i = 0; i < matAdj.size(); i++) {
                for (int j = 0; j < matAdj.size(); j++) {
                    writer.write(matAdj.elementAt(i).elementAt(j) + " ");
                    if (j == matAdj.size()-1)
                        writer.write('\n');
                }
            }
            writer.close();
            System.out.println("Le graphe a été enregistré dans le fichier " + nomFichier);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement dans le fichier " + nomFichier);
            e.printStackTrace();
        }

        return absolutePath;
    }

    public String enregistrerDetails(String nomFichier) {
        String absolutePath = "";
        try {
            File file = new File("Graphes/" + nomFichier);
            absolutePath = file.getAbsolutePath();
            FileWriter writer = new FileWriter(file);
            writer.write("D\n");

            if (estOriente)
                writer.write("1\n");
            else
                writer.write("0\n");

            int i = 0;
            for (Sommet s : sommets) {
                writer.write(s.getNom() + " " + s.getIndice());
                if (i == sommets.size()-1) {
                    writer.write('\n');
                } else {
                    writer.write('|');
                }
                i++;
            }

            int j = 0;
            for (Arete a : aretes) {
                writer.write(a.getDebut().getIndice() + " " + a.getFin().getIndice() + " " + a.getPoids());
                if (!(j == aretes.size()-1)) writer.write('|');
                j++;
            }

            writer.close();
            System.out.println("Le graphe a été enregistré dans le fichier " + nomFichier);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement dans le fichier " + nomFichier);
            e.printStackTrace();
        }

        return absolutePath;
    }

}
