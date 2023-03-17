import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class Graphe {
    private final Vector<Sommet> sommets;
    private final Vector<Arrete> arretes;
    private Vector<Integer> fs;
    private Vector<Integer> aps;
    private int[][] matAdj;
    private boolean estOriente;

    public Graphe(Vector<Integer> fs, Vector<Integer> aps, boolean estOriente, Vector<Sommet> sommets, Vector<Arrete> arretes) {
        this.fs = fs;
        this.aps = aps;
        this.matAdj = null; // ??
        this.sommets = sommets;
        this.arretes = arretes;
        this.estOriente = estOriente;
    }

    public Graphe(boolean estOriente, Vector<Sommet> sommets, Vector<Arrete> arretes) {
        int nbSommets = sommets.size();
        int nbArretes = arretes.size();
        this.fs = new Vector<Integer>(nbArretes);
        this.aps = new Vector<Integer>(nbSommets + 1);
        this.matAdj = null;
        this.sommets = sommets;
        this.arretes = arretes;
        this.estOriente = estOriente;
    }

    public Graphe(Vector<Integer> fs, Vector<Integer> aps, boolean estOriente) {
        this.fs = fs;
        this.aps = aps;
        this.matAdj = null; // Je sais pas si c'est bon
        this.sommets = null;
        this.arretes = null;
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
    public void addArrete(Arrete arrete) {

    }

    public int getNombreSommets() {
        return this.sommets.size();
    }

    public int getNombreArretes() {
        return this.arretes.size();
    }

    public Vector<Sommet> getSommets() {
        return this.sommets;
    }

    public Vector<Arrete> getArretes() {
        return this.arretes;
    }

    public void setArretes(int indice, Arrete arrete) {
        if (indice < 0 || indice > arretes.size()) {
            System.out.println("arrete de te foutre de ma gueule stp");
            return;
        } else {
            arretes.set(indice, arrete);
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

    public int[][] getMatAdj() {
        return this.matAdj;
    }

    public void setMatAdj(int[][] matAdj) {
        this.matAdj = matAdj;
    }

    @Override
    public String toString() {
        return
                " sommets" + sommets +
                "\n, arretes=" + arretes +
                "\n, fs=" + fs +
                "\n, aps=" + aps +
                "\n, matAdj=" + Arrays.toString(matAdj) +
                "\n, estOriente=" + estOriente +
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
