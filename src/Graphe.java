import java.io.FileWriter;
import java.io.IOException;
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
        MatAdjToFsAps();
        MatAdjToLists();
    }

    // Via Listes
    public Graphe(boolean estOriente, Vector<Sommet> sommets, Vector<Arete> arretes) {
        int nbSommets = sommets.size();
        int nbAretes = arretes.size();
        this.fs = new Vector<Integer>(nbAretes);
        this.aps = new Vector<Integer>(nbSommets + 1);
        this.matAdj = null;
        this.sommets = sommets;
        this.aretes = arretes;
        this.estOriente = estOriente;
    }

    // Via Fs et Aps
    public Graphe(Vector<Integer> fs, Vector<Integer> aps, boolean estOriente) {
        this.fs = fs;
        this.aps = aps;
        this.matAdj = null;
        this.sommets = null;
        this.aretes = null;
        this.estOriente = estOriente;
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

    //TODO
    void fsApsToMatAdj() {
        int n = aps.get(0);

    }

    void MatAdjToFsAps() {

    }

    void MatAdjToLists() {

    }

    //TODO
    public void suppSommet(int indice) {

    }

    //TODO
    public void addSommet(Sommet sommet) {

    }

    //TODO
    public void suppArrete(int indice) {

    }

    //TODO
    public void addArrete(Arete arrete) {

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
                "sommets" + sommets +
                "\narretes=" + aretes +
                "\nfs=" + fs +
                "\naps=" + aps +
                "\nmatAdj=" + matAdj.toString() +
                "\nestOriente=" + estOriente +
                '}';
    }

    public void enregistrer(String nomFichier) {
        try {
            FileWriter writer = new FileWriter(nomFichier+".txt");
            writer.write(this.toString());
            writer.close();
            System.out.println("Le graphe a été enregistré dans le fichier " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement dans le fichier " + nomFichier);
            e.printStackTrace();
        }
    }

    //TODO
    boolean SaveGraphe() {
        return false;
    }


}
