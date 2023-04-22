import java.awt.*;

public class Vertex {
    private String name;
    private Point position;
    private Point force;

    public Vertex(String name, Point position) {
        this.name = name;
        this.position = position;
        force = new Point();
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public Point getForce() {
        return force;
    }

    public void resetForce() {
        force.setLocation(0, 0);
    }

    public void applyCoulombForce(Vertex targetVertex, double k) {
        Point direction = new Point(targetVertex.getPosition().x - position.x,
                targetVertex.getPosition().y - position.y);
        double distanceSquared = Math.pow(direction.x, 2) + Math.pow(direction.y, 2);
        double force = k / (distanceSquared + 0.01);
        direction.setLocation(direction.x / Math.sqrt(distanceSquared), direction.y / Math.sqrt(distanceSquared));
        force = force / 2;
        this.force.setLocation(this.force.x - (int) (direction.x * force), this.force.y - (int) (direction.y * force));
        targetVertex.getForce().setLocation(targetVertex.getForce().x + (int) (direction.x * force),
                targetVertex.getForce().y + (int) (direction.y * force));
    }

    public void applyHookeForce(double k) {
        Point direction = new Point(force);
        double distance = Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2));
        double force = k * distance;
        direction.setLocation(direction.x / distance, direction.y / distance);
        this.force.setLocation(this.force.x + (int) (direction.x * force), this.force.y + (int) (direction.y * force));
    }

    public void updatePosition(double dampingFactor) {
        position.setLocation(position.x + (int) (force.x * dampingFactor), position.y + (int) (force.y * dampingFactor));
    }
}
