import Arrete.Arrete;
import Sommet.Sommet;

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

    //TODO
    boolean SaveGraphe() {
        return false;
    }


}
