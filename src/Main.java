import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Sommet s1 = new Sommet("Paris", 1);
        System.out.println(s1);

        GraphSimulation graphSimulation = new GraphSimulation();
        JFrame frame = new JFrame("Graph Simulation");
        frame.getContentPane().add(graphSimulation);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        graphSimulation.simulate();
    }

}
