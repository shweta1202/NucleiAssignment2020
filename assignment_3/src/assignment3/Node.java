package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int nodeID;
    private String nodeName;
    private List<Node> IncomingNodes = new ArrayList<>();
    private List<Node> OutGoingNodes = new ArrayList<>();

    public int getNodeID() {
        return nodeID;
    }
    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void addIncomingNode(Node node){
        IncomingNodes.add(node);
    }
    public void addOutGoingNode(Node node){
        OutGoingNodes.add(node);
    }
    public List<Node> getIncomingNodes(){
        return IncomingNodes;
    }
    public List<Node> getOutGoingNodes(){
        return OutGoingNodes;
    }
    public void setIncomingNodes(List<Node> IncomingNodes) {
        this.IncomingNodes = IncomingNodes;
    }

    public void setOutGoingNodes(List<Node> OutGoingNodes) {
        this.OutGoingNodes = OutGoingNodes;
    }

    public Node(int nodeID, String nodeName){
        this.nodeID = nodeID;
        this.nodeName = nodeName;
    }
    public Node(){

    }
    @Override
    public String toString() {
        return ("" + nodeID);
    }
}
