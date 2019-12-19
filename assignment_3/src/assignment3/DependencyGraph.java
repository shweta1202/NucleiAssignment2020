package assignment3;

import exception.CyclicDependencyException;
import exception.NoSuchNodeException;
import exception.NodeAlreadyExistsException;
import exception.InvalidDataTypeException;
import java.util.Scanner;

public class DependencyGraph {
    public void run() throws InvalidDataTypeException, NoSuchNodeException {
        Scanner s = new Scanner(System.in);
        InputValidation obj = new InputValidation();
        Graph g = new Graph();
        int choice;
        while(true){
            System.out.println("------------------Menu---------------------");
            System.out.println("Please Select an option :");
            System.out.println("1. Get Immediate Parent of a node : ");
            System.out.println("2. Get Immediate Children of a node : ");
            System.out.println("3. Get Ancestors of a node : ");
            System.out.println("4. Get Descendants of a node : ");
            System.out.println("5. Delete a dependency :");
            System.out.println("6. Delete a node :");
            System.out.println("7. Add a dependency :");
            System.out.println("8. Add a Node :");
            System.out.println("9. Print the Graph : ");
            System.out.println("10. Exit");
            choice = s.nextInt();
            switch (choice){
                case 1:
                    System.out.println(("Enter the node id:"));
                    try {
                        System.out.println(g.getImmediateParents(obj.isValidID(s.next())));
                    } catch (NoSuchNodeException | InvalidDataTypeException e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    System.out.println("Enter the node id:");
                    try {
                        System.out.println(g.getImmediateChildren(obj.isValidID(s.next())));
                    } catch (NoSuchNodeException | InvalidDataTypeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Enter the node ID:");
                    try {
                        System.out.println(g.getAncestors(obj.isValidID(s.next())));
                    } catch (NoSuchNodeException | InvalidDataTypeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Enter the node id:");
                    try {
                        System.out.println(g.getDescendants(obj.isValidID(s.next())));
                    } catch (NoSuchNodeException | InvalidDataTypeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    int parentID;
                    int childID;
                    try {
                        System.out.println("Enter the parent ID:");
                        parentID = obj.isValidID(s.next());
                        System.out.println("Enter the child ID");
                        childID = obj.isValidID(s.next());
                        g.deleteDependency(parentID,childID);
                    }
                    catch (InvalidDataTypeException | NoSuchNodeException e){
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("Enter the node ID");
                    try {
                        g.deleteNode(obj.isValidID(s.next()));
                    } catch (NoSuchNodeException | InvalidDataTypeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    int parentID1;
                    int childID1;

                    try {
                        System.out.println("Enter the parent ID:");
                        parentID1 = obj.isValidID(s.next());
                        System.out.println("Enter the child ID");
                        childID1 = obj.isValidID(s.next());
                        g.addDependency(parentID1,childID1);
                    } catch (CyclicDependencyException | InvalidDataTypeException | NoSuchNodeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    Node node = new Node();

                    try {
                        System.out.println("Enter the node ID:");
                        int nodeID = obj.isValidID(s.next());
                        System.out.println("Enter the node name:");
                        String nodeName = s.next();
                        g.addNode(nodeID,nodeName);
                    } catch (NodeAlreadyExistsException | InvalidDataTypeException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    g.display();
                    break;
                case 10:
                    break;
                default:
                    System.out.println("You have Entered a wrong choice!! Please try again");
            }

        }
    }
}
