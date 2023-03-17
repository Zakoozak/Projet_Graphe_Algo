public class Sommet {
    private String nom;
    private int indice;
    private int rang;

    public Sommet(String nom, int indice) {
        this.nom = nom;
        this.indice = indice;
    }

    public Sommet(int indice) {
        this("", indice);
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

    @Override
    public String toString() {
        return "[" + nom + "," + indice + "," + rang + "]" ;
    }
}
