package ApplicationProgramming;

import java.io.FileNotFoundException;
import java.util.*;


public class SearchMethods {
    private Node noderoot;
    private String goal_State;
    final static private String Inital = "876543210";
    final static private String GOAL_STATE = "123456780";



    //used to declare the initial start and goal state
    public SearchMethods(Node noderoot, String goal_State) {
        this.noderoot = noderoot;
        this.goal_State = goal_State;


    }


    //-------------------------------------------------------------------------------------------------------
    //    breadthFirstSearch() find the goal state using Breadth First Search algorithm
    //    we start with the initial state. check if it is goal or not and expand its children if it is not the goal.
    //    pop the first element of the queue and check if it is goal node. if not add its children to the queue.
    //    repeat the process until the solution is found
    //-------------------------------------------------------------------------------------------------------

    public void breadthFirstSearch() throws FileNotFoundException {
        //PrintWriter output = new PrintWriter(new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming/bfs.txt"));
        Set<String> stateSets = new HashSet<String>(); // creates a hashset for the statesets already visted
        int time = 0;
        Node node = new Node(noderoot.getState()); // creates a new node
        Queue<Node> queue = new LinkedList<Node>(); // creates the queue for the nodes
        //  reportSolutionBreadth(node, output); // write solution to a file and terminate search
        Node currentNode = node;
        while (!currentNode.getState().equals(goal_State)) { // while loop to cycle through
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = GameState.getNodeSuccessors(currentNode.getState()); // creates a list of the node successors
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                int newCost = currentNode.getUnexpanded() + 1;
                stateSets.add(n); // games node succors to statesets
                Node child = new Node(n); //new node
                currentNode.addChild(child);
                child.setParent(currentNode);
                queue.add(child);

            }
            currentNode = queue.poll();
            time += 1; // adds 1 second while its running

        }

        GameState.printSolution(currentNode, stateSets, noderoot, time); // calls the game state class
    }

//====================================================================================================

    //-------------------------------------------------------------
    //
    //  Uniform Cost Search takes operator costs into account.
    //     (It is not breath-first or depth-first search!)
    //
    //-------------------------------------------------------------

    public void uniformCostSearch() {
        Set<String> stateSets = new HashSet<String>(); // creates a hashset for the statesets already visted
        int time = 0;
        Node node = new Node(noderoot.getState()); // new  node
        node.setCost(0);
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator(); //  uses the comparator to see comapre the unexpaded nodes to pritority queue
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator); // This creates a queue that has nodes and thier unexpanded sorted
        // reportSolutionUniform(node, output2); // write solution to a file and terminate search
        Node currentNode = node;
        while (!currentNode.getState().equals(goal_State)) { // while loop for the search
            stateSets.add(currentNode.getState());// adds the current node to the statesets
            List<String> nodeSuccessors = GameState.getNodeSuccessors(currentNode.getState()); // uses node successors from game state class
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n); // adds node successors to statesets
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                child.setTotalCost(currentNode.getUnexpanded() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), 0);
                nodePriorityQueue.add(child); // adds the child node to the node prioritu queue

            }

            currentNode = nodePriorityQueue.poll();
            time += 1;
        }
        int newCost = currentNode.getUnexpanded() + 1;
        GameState.printSolution(currentNode, stateSets, noderoot, time); //calls the gamestae class in order to print the solution

    }



    //==============================================================================


    //----------------------------------------------------------
    //
    // The behaviour of	A* Search depends on the heuristic used.
    //    In practice, admissible heuristics are often used.
    //
    //---------------------------------------------------------

    public void AStar(Heuristic heuristic) {
        Set<String> stateSets = new HashSet<String>(); // creates a hashset for the statesets already visted
        int time = 0;
        Node node = new Node(noderoot.getState()); // new node based of node states
        node.setUnexpanded(0);
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator(); //  uses the comparator to see comapre the unexpaded nodes to pritority queue
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator); // This creates a queue that has nodes and thier unexpanded sorted
        Node currentNode = node;
        while (!currentNode.getState().equals(goal_State)) { // for loop for a star search
            stateSets.add(currentNode.getState()); // uses current node and adds to statesets
            List<String> nodeSuccessors = GameState.getNodeSuccessors(currentNode.getState());  //// uses node successors from game state class
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n); // adds node successors to satesets
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                if (heuristic == Heuristic.H_ONE) // this uses the heristic one class in the a star search
                    child.setTotalCost(currentNode.getUnexpanded() +
                            Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicOne(child.getState(), goal_State));
                int newCost = currentNode.getUnexpanded() + 1;
                nodePriorityQueue.add(child); // adds node child to priority queue

            }
            currentNode = nodePriorityQueue.poll();
            time += 1;
        }
        GameState.printSolution(currentNode, stateSets, noderoot, time); //calls the gamestae class in order to print the solution
    }


    //--------------------------------------------------------------------------------------------------------------
    //
    // This heuristic estimates the cost to the goal from current state by counting the number of misplaced tiles
    //
    //--------------------------------------------------------------------------------------------------------------


    private int heuristicOne(String currentState, String goalSate) {

        int difference = 0; // sets the diffrence to 0
        for (int i = 0; i < currentState.length(); i += 1) // initiates for loop for the length
            if (currentState.charAt(i) != goalSate.charAt(i)) // gets thge current state of the character to reach goal sate
                difference += 1;
        return difference;
    }
}
