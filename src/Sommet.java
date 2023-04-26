import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Sommet {
    private String nom;
    private int indice;
    private int rang;
    private Point position;
    private final Random random = new Random();

    public Sommet(String nom, int indice) {
        this.nom = nom;
        this.indice = indice;
        position = new Point(random.nextInt((1600) + 1) + 100, random.nextInt((700) + 1) + 100);
    }

    public Sommet(int indice) {
        this("", indice);
        position = new Point(random.nextInt((1600) + 1) + 100, random.nextInt((700) + 1) + 100);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sommet sommet = (Sommet) o;
        return indice == sommet.indice &&
                rang == sommet.rang &&
                nom.equals(sommet.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, indice, rang);
    }

    @Override
    public String toString() {
        String sNom = "";
        if (nom.isEmpty()) {
            sNom = "<No Name>";
        } else sNom = nom;

        return "[" + sNom + "," + indice + "," + rang + "]" ;
    }
}
