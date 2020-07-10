package com.sebin.interview.graph;

import java.util.*;

public class BFSExample {

    private Graph graphInstance;

    BFSExample(){
        Graph g = new Graph();
        graphInstance = g.createDummyGraph();
    }

    public void printBFSTraversal(Graph.Node source){
        LinkedList<Graph.Node> queue = new LinkedList<>();
        Map<Graph.Node,Boolean> isVisited = new HashMap<>();

        queue.add(source);
        while(!queue.isEmpty()){
            Graph.Node curNode = queue.poll();
            System.out.print(curNode.getValue()+" ");
            isVisited.put(curNode,true);
            Set<Graph.Node> edges = graphInstance.getEdges(curNode);
            for(Graph.Node edge : edges){
                if(!isVisited.containsKey(edge)){
                    queue.add(edge);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFSExample bfsExample = new BFSExample();
        Graph g = new Graph();
        Graph.Node source = g.new Node(1);
        bfsExample.printBFSTraversal(source);
    }
}
