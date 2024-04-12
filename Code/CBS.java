import java.util.*;

public class CBS {
    PriorityQueue<ConstraintTree> openList = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

    public void findPaths(List<Agent> agents) {
        ConstraintTree root = new ConstraintTree(new ArrayList<>(), new HashMap<>());
        for (Agent agent : agents) {
            root.paths.put(agent.id, findPath(agent.start, agent.goal, root.constraints));
        }
        openList.add(root);

        while (!openList.isEmpty()) {
            ConstraintTree current = openList.poll();
            List<Conflict> conflicts = findConflicts(current.paths);
            if (conflicts.isEmpty()) {
                System.out.println("Solution found!");
                for (Map.Entry<Integer, List<Node>> entry : current.paths.entrySet()) {
                    System.out.println("Agent " + entry.getKey() + ": " + entry.getValue());
                }
                return;
            }
            for (Conflict conflict : conflicts) {
                for (Constraint constraint : createConstraints(conflict)) {
                    ConstraintTree child = new ConstraintTree(new ArrayList<>(current.constraints), new HashMap<>(current.paths));
                    child.constraints.add(constraint);
                    child.paths.put(constraint.agentId, findPath(agents.get(constraint.agentId - 1).start, agents.get(constraint.agentId - 1).goal, child.constraints));
                    openList.add(child);
                }
            }
        }
        System.out.println("No solution found.");
    }

    private List<Node> findPath(Node start, Node goal, List<Constraint> constraints) {
        return new ArrayList<>();
    }

    private List<Conflict> findConflicts(Map<Integer, List<Node>> paths) {
        return new ArrayList<>();
    }

    private List<Constraint> createConstraints(Conflict conflict) {
        return new ArrayList<>();
    }
}

class ConstraintTree {
    List<Constraint> constraints;
    Map<Integer, List<Node>> paths;
    int cost;

    public ConstraintTree(List<Constraint> constraints, Map<Integer, List<Node>> paths) {
        this.constraints = constraints;
        this.paths = paths;
        this.cost = calculateCost();
    }

    private int calculateCost() {
        return 0;
    }
}

class Conflict {
}
