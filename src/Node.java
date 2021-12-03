package ApplicationProgramming;

import java.util.ArrayList;

// ----------------------------------------------------------------------
//
// This just initialises the nodes basically adding getters and setters
//
// -----------------------------------------------------------------------

public class Node {
    private String state;
    private ArrayList<Node> children;
    Node parent;
    private int cost;
    private int unexpanded;

    public int getUnexpanded() {
        return unexpanded;
    }

    public void setUnexpanded(int unexpanded) {
        this.unexpanded = unexpanded;
    }

    public void setTotalCost(int cost, int estimatedCost) {
        this.unexpanded = cost + estimatedCost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


    // Constructor
    public Node(String state) {
        this.state = state;
        children = new ArrayList<Node>();
    }

    // Properties
    public String getState() {
        return state;
    }

    // Public interface
    public void addChild(Node child) {
        children.add(child);
    }
}
