import java.awt.*;

public class Edge {
    private Vertex source;
    private Vertex target;

    public Edge(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void applyHookeForce(double k) {
        Point direction = new Point(target.getPosition().x - source.getPosition().x,
                target.getPosition().y - source.getPosition().y);
        double distance = Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
        double force = k * (distance - 100);
        direction.setLocation(direction.x / distance, direction.y / distance);
        source.getForce().setLocation(source.getForce().x + (int) (direction.x * force),
                source.getForce().y + (int) (direction.y * force));
        target.getForce().setLocation(target.getForce().x - (int) (direction.x * force),
                target.getForce().y - (int) (direction.y * force));
    }
}