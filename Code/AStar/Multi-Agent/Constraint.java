public class Constraint {
    int time;
    Node node;
    int agentId;

    public Constraint(int agentId, Node node, int time) {
        this.agentId = agentId;
        this.node = node;
        this.time = time;
    }
}
