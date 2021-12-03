package ApplicationProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Solver {
    final static private String Inital = "876543210"; //this is the start state
    final static private String GOAL_STATE = "123456780"; // this is the goal state we want to achieve



    //----------------------------------------------------------------
    //
    // This is the intro to the program and to tell the lecturer what it is.
    //
    //----------------------------------------------------------------



    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in); // scanner for user input
        int choice; // this declared the choice
        String rootState = Inital; // calls the start state
        long Time = System.currentTimeMillis(); // measures the time in milli seconds
       // ZoneId d = ZoneId.of("GMT") ;
       // ZonedDateTime dt = ZonedDateTime.now(d);
        //File breadthfirstsearch = new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming");
        //File UniformCostSearch = new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming");
        //File AstarSearch= new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming");


        //This is whats displayed in the console to the user in order to let them choice an search method
        SearchMethods search = new SearchMethods(new Node(rootState), GOAL_STATE);
        System.out.println(" ");
        System.out.println("When program have finished please check the TXT file of the search method chosen,");
        System.out.println("Please type the number from the following list");
        System.out.println("for Breadth first search type - '1'  ");
        System.out.println("for Unifrom cost search type - '2'  ");
        System.out.println("for A star search type - '3'  ");
        System.out.println("Please choose what method you would like :");


        //-----------------------------------------------------------------------
        //
        // This if else statements allow the user to choose there search method
        // then it streams into a text file instread of the console to be saved.
        //
        //---------------------------------------------------------------------

        choice = Integer.parseInt(scan.nextLine());

        //This choice is for Breadth First Search, I know we didn't need to implement this but the provided code started
        // at breadth first search so I kept it in for more options and to see the difference
        //between BFS and Uniform cost search

        if (choice == 1){
            PrintStream BFS = new PrintStream(new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming/outputbfs.txt"));
            System.setOut(BFS);
            search.breadthFirstSearch();
        } else if (choice == 2) {
            PrintStream UFS = new PrintStream(new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming/outputUniCost.txt"));
            System.setOut(UFS);
            search.uniformCostSearch();
        } else if (choice == 3) {
            PrintStream Astar = new PrintStream(new File("/Users/marcmaslen/IdeaProjects/CE213_1902710_assignment1/src/ApplicationProgramming/outputAstar.txt"));
            System.setOut(Astar);
            search.AStar(Heuristic.H_ONE);
        }


    }
}