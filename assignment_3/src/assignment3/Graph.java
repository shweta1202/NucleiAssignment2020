package assignment3;

import exception.NoSuchNodeException;
import exception.NodeAlreadyExistsException;
import exception.CyclicDependencyException;
import constants.*;

import java.util.*;

public class Graph {
    public Map<Integer, Node> nodes = new HashMap<>();

    //Returns true if ID already exists in Graph.
    private boolean ifContainsID(Integer nodeID){
        return nodes.containsKey(nodeID);

    }

    //Add a node to the graph if not exists.
    public Node addNode(int nodeID, String nodeName) throws Exception {
        Node node = new Node(nodeID, nodeName);
        // todo explore Optional or any other library to avoid check or Asserts
//        Optional.ofNullable(nodes.get(nodeID))
//                .orElseThrow(() -> new NodeAlreadyExistsException(StringConstants.NODE_ALREADY_EXISTS_EXCEPTION));
        if(!ifContainsID(nodeID))
            nodes.put(nodeID, node);
        else throw new NodeAlreadyExistsException(StringConstants.NODE_ALREADY_EXISTS_EXCEPTION);
        return node;
    }

    public void addDependency(int source, int destination) throws CyclicDependencyException, NoSuchNodeException {
        if (!ifContainsID(source) || !ifContainsID(destination)) {
            throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);
        }
        boolean isCycleExists = false;
        List<Node> ancestors = getAncestors(source);

        for (Node v : ancestors) {
            if (v.getNodeID() == destination) {
                isCycleExists = true;
                break;
            }
        }
        if (isCycleExists) throw new CyclicDependencyException(StringConstants.CYCLIC_DEPENDENCY_EXCEPTION);
        if (nodes.containsKey(source) && nodes.containsKey((destination)) && !isCycleExists) {
            (nodes.get(source)).addOutGoingNode(nodes.get(destination));
            (nodes.get(destination)).addIncomingNode(nodes.get(source));
        }
    }

    public void deleteDependency(int parentID, int childID) throws NoSuchNodeException {
        if (!ifContainsID(parentID) || !ifContainsID(childID))
            throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);

        nodes.get(parentID).getOutGoingNodes().remove(nodes.get(childID));
        nodes.get(childID).getIncomingNodes().remove(nodes.get(parentID));
    }

    public List<Node> getImmediateParents(int nodeID) throws NoSuchNodeException {
        if (!ifContainsID(nodeID)) throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);
        return nodes.get(nodeID).getIncomingNodes();
    }

    public List<Node> getImmediateChildren(int nodeID) throws NoSuchNodeException {
        if (!ifContainsID(nodeID)) throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);
        return nodes.get(nodeID).getOutGoingNodes();
    }

    public List<Node> getDescendants(int nodeID) throws NoSuchNodeException {
        if (!ifContainsID(nodeID)) throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);
        List<Node> descendants = new ArrayList<>();

        Queue<Node> temp = new LinkedList<>(); // todo check suitable implementation for queue
        temp.add(nodes.get(nodeID)); // todo declare and define queue in one line
        while (!temp.isEmpty()) {  // todo headNode and temp ?
            Node headNode = temp.remove();
            descendants.addAll(headNode.getOutGoingNodes());
            temp.addAll(headNode.getOutGoingNodes());

        }
        Set<Node> descendant = new HashSet<Node>(descendants);
        descendants = new ArrayList<>(descendant);
        return descendants;
    }

    public List<Node> getAncestors(int nodeID) throws NoSuchNodeException {
        if (!ifContainsID(nodeID)) throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);
        List<Node> ancestors = new ArrayList<>();

        Queue<Node> temp = new LinkedList<>();
        temp.add(nodes.get(nodeID));
        while (!temp.isEmpty()) {
            Node headNode = temp.remove();
            ancestors.addAll(headNode.getIncomingNodes());
            temp.addAll(headNode.getIncomingNodes());

        }
        return ancestors;
    }

    public void deleteNode(int nodeID) throws NoSuchNodeException {
        if (!ifContainsID(nodeID)) throw new NoSuchNodeException(StringConstants.NO_SUCH_NODE_EXCEPTION);
        Node node = nodes.get(nodeID);
        for (int key : nodes.keySet()) {
            Node temp = nodes.get(key);

            List<Node> IncomingNodes = temp.getIncomingNodes();
            if (IncomingNodes != null && IncomingNodes.contains((node))) {
                IncomingNodes.remove(node);
            }
            temp.setIncomingNodes(IncomingNodes);

            List<Node> OutGoingNodes = temp.getOutGoingNodes();
            if (OutGoingNodes != null) {
                OutGoingNodes.remove(node);
            }
            temp.setOutGoingNodes(OutGoingNodes);
        }
        nodes.remove(nodeID, node);
    }

    public void display() {
        for (int v : nodes.keySet()) {
            System.out.print(v + ":");
            System.out.println(nodes.get(v).getOutGoingNodes());
        }
    }
}