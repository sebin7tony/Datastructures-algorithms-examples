package com.sebin.interview.graph;

import java.util.*;

public class Graph {

    private Map<Node, Set<Node>> adjacencyList = new HashMap<>();

    public boolean addEdge(Node fromVert,Node toVert){
        if(fromVert == null || toVert == null){
            return false;
        }
        if(!adjacencyList.containsKey(fromVert)){
            Set<Node> edgeList = new HashSet<>();
            adjacencyList.put(fromVert,edgeList);
        }
        Set<Node> curEdgeList = adjacencyList.get(fromVert);
        curEdgeList.add(toVert);
        return true;
    }

    public Set<Node> getEdges(Node vert){
        if(vert == null){
            return null;
        }

        if(adjacencyList.containsKey(vert)){
            return adjacencyList.get(vert);
        }else {
            return null;
        }
    }

    public Graph createDummyGraph(){
        Graph graph = new Graph();
        /*graph.addEdge(new Node(0),new Node(1));
        graph.addEdge(new Node(1),new Node(3));
        graph.addEdge(new Node(1),new Node(2));
        graph.addEdge(new Node(2),new Node(0));
        graph.addEdge(new Node(3),new Node(3));*/
        graph.addEdge(new Node(0),new Node(1));
        graph.addEdge(new Node(0),new Node(2));
        graph.addEdge(new Node(1),new Node(2));
        graph.addEdge(new Node(2),new Node(0));
        graph.addEdge(new Node(2),new Node(3));
        graph.addEdge(new Node(3),new Node(3));

        return graph;
    }

    public class Node{
        private Integer value;

        public Node(Integer value){
            this.value = value;
        }
        public Integer getValue(){
            return this.value;
        }
        public void setValue(Integer value){
            this.value = value;
        }

        @Override
        public boolean equals(Object node){
            if(this == node){
                return true;
            }
            if(!(node instanceof Node) || node == null){
                return false;
            }
            if(((Node) node).getValue() == this.value){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
