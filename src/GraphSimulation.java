import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class GraphSimulation extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Random random = new Random();
    private int iterationCount = 0;
    private final int MAX_ITERATIONS = 1000;
    private final double COULOMB_CONSTANT = 50000.0;
    private final double SPRING_CONSTANT = 0.1;
    private final double DAMPING_FACTOR = 0.9;

    public GraphSimulation() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        // Initialize vertices
        vertices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Vertex vertex = new Vertex("" + i, new Point(random.nextInt(800), random.nextInt(600)));
            vertices.add(vertex);
        }

        // Initialize edges
        edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex sourceVertex = vertices.get(i);
            for (int j = i + 1; j < vertices.size(); j++) {
                Vertex targetVertex = vertices.get(j);
                Edge edge = new Edge(sourceVertex, targetVertex);
                edges.add(edge);
            }
        }

        JButton regenerateButton = new JButton("Regenerate");
        regenerateButton.addActionListener(e -> regenerateGraph());
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(regenerateButton, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.WEST);
    }

    private void regenerateGraph() {
        this.simulate(); // Ne change rien → fais la simulation, mais pas de facteur changeant dans la simulation les facteurs changeant sont avant la sim
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Edge edge : edges) {
            Vertex source = edge.getSource();
            Vertex target = edge.getTarget();
            g.drawLine(source.getPosition().x, source.getPosition().y, target.getPosition().x, target.getPosition().y);
        }
        for (Vertex vertex : vertices) {
            g.setColor(Color.BLUE);
            g.fillOval(vertex.getPosition().x - 20, vertex.getPosition().y - 20, 40, 40);
            g.setColor(Color.WHITE);
            g.drawString(vertex.getName(), vertex.getPosition().x - (g.getFontMetrics().stringWidth(vertex.getName()) / 2), vertex.getPosition().y);
        }

        /*
        // Draw edges
        for (Edge edge : edges) {
            g.drawLine(edge.getSource().getPosition().x, edge.getSource().getPosition().y,
                    edge.getTarget().getPosition().x, edge.getTarget().getPosition().y);
        }

        // Draw vertices
        for (Vertex vertex : vertices) {
            g.setColor(Color.BLUE);
            g.fillOval(vertex.getPosition().x - 5, vertex.getPosition().y - 5, 10, 10);
            g.setColor(Color.BLACK);
            g.drawOval(vertex.getPosition().x - 5, vertex.getPosition().y - 5, 10, 10);
        }
         */
    }

    public void simulate() {
        while (iterationCount < MAX_ITERATIONS) {
            for (Vertex vertex : vertices) {
                vertex.resetForce();
            }

            // Apply Coulomb's Law to all vertices
            for (int i = 0; i < vertices.size(); i++) {
                Vertex sourceVertex = vertices.get(i);
                for (int j = i + 1; j < vertices.size(); j++) {
                    Vertex targetVertex = vertices.get(j);
                    sourceVertex.applyCoulombForce(targetVertex, COULOMB_CONSTANT);
                }
            }

            // Apply Hooke's Law to connected vertices
            for (Edge edge : edges) {
                edge.applyHookeForce(SPRING_CONSTANT);
            }

            // Move vertices
            for (Vertex vertex : vertices) {
                vertex.updatePosition(DAMPING_FACTOR);
            }

            // Repaint
            repaint();

            iterationCount++;
        }
    }
}