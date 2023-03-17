public class Arrete {

    private Sommet debut;
    private Sommet fin;
    private int poids;

    public Arrete(Sommet debut, Sommet fin, int poids) {
        this.debut = debut;
        this.fin = fin;
        this.poids = poids;
    }

    public Arrete(Sommet debut, Sommet fin) {
        this(debut,fin,0);
    }
    public Sommet getDebut() {
        return debut;
    }

    public Sommet getFin() {
        return fin;
    }

    public int getPoids() {
        return poids;
    }

    public void setDebut(Sommet debut) {
        this.debut = debut;
    }

    public void setFin(Sommet fin) {
        this.fin = fin;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        return "debut = " + debut +
                ", fin = " + fin +
                ", poids = " + poids;
    }
}
