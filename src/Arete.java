import java.awt.*;
import java.util.Objects;

public class Arete {
    private Sommet debut;
    private Sommet fin;
    private int poids;

    public Arete(Sommet debut, Sommet fin, int poids) {
        this.debut = debut;
        this.fin = fin;
        this.poids = poids;
    }

    public Arete(Sommet debut, Sommet fin) {
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
        return "(" + debut + " - " + fin + ", " + poids + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arete arete = (Arete) o;
        return poids == arete.poids &&
                debut.equals(arete.debut) &&
                fin.equals(arete.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debut, fin, poids);
    }
}
