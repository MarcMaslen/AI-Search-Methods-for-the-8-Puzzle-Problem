package ApplicationProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class GameState {
    public static List<String> getNodeSuccessors(String state) {
        List<String> successfullNodes = new ArrayList<String>();

        //----------------------------------------------------------------------------------
        //
        // I have chosen to use a switch chase to determine where to node can move too
        //     Becuase a 8 puzzle piece is set up in a 3x3 box this is crucial
        //
        //-------------------------------------------------------------------------------------

        switch (state.indexOf("0")) {
            case 0: {
                successfullNodes.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successfullNodes.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            case 1: {
                successfullNodes.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successfullNodes.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successfullNodes.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            case 2: {

                successfullNodes.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successfullNodes.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }
            case 3: {
                successfullNodes.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                successfullNodes.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                successfullNodes.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            case 4: {
                successfullNodes.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                successfullNodes.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                successfullNodes.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successfullNodes.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }
            case 5: {
                successfullNodes.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                successfullNodes.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successfullNodes.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            case 6: {
                successfullNodes.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                successfullNodes.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                break;

            }
            case 7: {
                successfullNodes.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                successfullNodes.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successfullNodes.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            case 8: {
                successfullNodes.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                successfullNodes.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }

        return successfullNodes; // this retuned the successors of what the nodes moved
    }

    // ----------------------------------------------------------------------
    //
    // This prints to the txt file the moves from initial state to goal state
    //
    //----------------------------------------------------------------------
    public static void printSolution(Node goalNode, Set<String> visitedNodes, Node root, int time) {

        int unexpanded = 0; // sets total to 0

        Stack<Node> path = new Stack<Node>(); // This takes the stack of nodes to find a solution
        path.push(goalNode); // this puushes the stack to find the goal state
        while (!goalNode.getState().equals(root.getState())) {
            path.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        String sourceState = root.getState(); // creates source states and takes it from the root
        String destination;
        int cost = 0;
        for (int i = path.size() - 1; i >= 0; i--) { // for loop for getting to the destination
            destination = path.get(i).getState();
            if (!sourceState.equals(destination)) {
                cost = Character.getNumericValue(destination.charAt(sourceState.indexOf('0'))); // this gets the nodes that where exapned
                unexpanded += cost; // this defines the unexpanded nodes
            }

            sourceState = destination;
            System.out.println( path.get(i).getState().substring(0, 9) );


        }

        //prints the results
        System.out.println("----------------------------------------------------------------------");
        System.out.println((visitedNodes.size()) + " Nodes Expanded");
        System.out.println(unexpanded + " Nodes unexpanded");
        System.out.println( "This was " + cost + " moves" );
        System.out.println("It took " + time + " milliseconds");


    }

}
