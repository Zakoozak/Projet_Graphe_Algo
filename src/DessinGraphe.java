import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Vector;

public class DessinGraphe extends JPanel {
    private Vector<Sommet> sommets;
    private Vector<Arete> aretes;
    private boolean estOriente;

    public DessinGraphe() {
        setPreferredSize(new Dimension(1800, 800));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Arete arete : aretes) {
            Sommet debut = arete.getDebut();
            Sommet fin = arete.getFin();
            ((Graphics2D) g).setStroke(new BasicStroke(2)); // Pinceau + large
            g.drawLine(debut.getPosition().x, debut.getPosition().y, fin.getPosition().x, fin.getPosition().y);

        }

        for (Sommet sommet : sommets) {
            g.setColor(Color.BLUE);
            g.fillOval(sommet.getPosition().x - 20, sommet.getPosition().y - 20, 60, 60);
            g.setColor(Color.WHITE);

            String s;
            if (sommet.getNom().equals(""))
                s = String.valueOf(sommet.getIndice());
            else
                s = sommet.getNom();

            ((Graphics2D) g).setStroke(new BasicStroke(1)); // Pinceau - large
            g.drawString(s, sommet.getPosition().x - (g.getFontMetrics().stringWidth(s) / 2) + 10, sommet.getPosition().y + 14);
        }
    }

    public void setSommets(Vector<Sommet> sommets) {
        this.sommets = sommets;
    }

    public void setAretes(Vector<Arete> aretes) {
        this.aretes = aretes;
    }

    public void setEstOriente(boolean estOriente) {
        this.estOriente = estOriente;
    }

    public boolean estOriente() {
        return estOriente;
    }
}
