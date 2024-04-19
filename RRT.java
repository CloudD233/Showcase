import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RRT {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double STEP_SIZE = 20.0;
    private static final int MAX_ITER = 10000;

    class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double distanceTo(Point other) {
            return Math.hypot(this.x - other.x, this.y - other.y);
        }
    }

    private List<Point> path = new ArrayList<>();
    private Random random = new Random();

    public RRT() {
        path.add(new Point(0, 0));  // Start point
    }

    public void findPath(Point goal) {
        for (int i = 0; i < MAX_ITER; i++) {
            Point rand = new Point(random.nextDouble() * WIDTH, random.nextDouble() * HEIGHT);
            Point nearest = getNearestPoint(rand);

            Point newPoint = steer(nearest, rand);
            if (!checkCollision(nearest, newPoint)) {
                path.add(newPoint);
                if (newPoint.distanceTo(goal) <= STEP_SIZE) {
                    path.add(goal);
                    return;
                }
            }
        }
    }

    private Point getNearestPoint(Point randomPoint) {
        Point nearest = null;
        double minDist = Double.MAX_VALUE;
        for (Point p : path) {
            double dist = p.distanceTo(randomPoint);
            if (dist < minDist) {
                minDist = dist;
                nearest = p;
            }
        }
        return nearest;
    }

    private Point steer(Point nearest, Point rand) {
        double theta = Math.atan2(rand.y - nearest.y, rand.x - nearest.x);
        double newX = nearest.x + STEP_SIZE * Math.cos(theta);
        double newY = nearest.y + STEP_SIZE * Math.sin(theta);
        return new Point(newX, newY);
    }

    private boolean checkCollision(Point p1, Point p2) {
        // Implement obstacle collision checking logic here
        // For simplicity, this function always returns false (no collision)
        return false;
    }

    public List<Point> getPath() {
        return path;
    }

    public static void main(String[] args) {
        RRT rrt = new RRT();
        Point goal = new Point(750, 550);
        rrt.findPath(goal);
        System.out.println("Path found to goal:");
        for (Point p : rrt.getPath()) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}
