package ApplicationProgramming;

import java.util.Comparator;

//----------------------------------------------
//
// This determines what node to prioritise
//
//------------------------------------------

public class NodePriorityComparator implements Comparator<Node> {

    @Override
    public int compare(Node A, Node B) {
        if (A.getUnexpanded() < B.getUnexpanded()) { // if node A is less then B then -1
            return -1;
        }
        if (A.getUnexpanded() > B.getUnexpanded()) { // if node A is greater then B then +1
            return 1;
        }
        return 0;
    }
}