package com.sebin.interview.graph;

import java.util.*;

public class DFSExample {

    private Graph graphInstance;

    public DFSExample(){
        Graph graph = new Graph();
        graphInstance = graph.createDummyGraph();
    }

    public void printTraversal(Graph.Node source) throws IllegalArgumentException{
        if (source == null) {
            throw new IllegalArgumentException("Source node passed is null");
        }

        Map<Graph.Node, Boolean> isVisted = new HashMap<>();
        //DFS(graphInstance,isVisted,source);
        DFSStack(graphInstance,isVisted,source);
    }

    public void printPath(){
        Map<Graph.Node, Boolean> isVisted = new HashMap<>();
        //DFS(graphInstance,isVisted,source);
        findPathInGraph(graphInstance,isVisted,graphInstance.new Node(2),graphInstance.new Node(3));
    }

    private void DFS(Graph graph,Map<Graph.Node, Boolean> isVisted, Graph.Node source){
        System.out.print(source.getValue()+" ");
        isVisted.put(source,true);
        if(graph == null){
            System.out.print("graph is null ");
        }
        Set<Graph.Node> edges = graph.getEdges(source);
        for(Graph.Node edge : edges){
            if(!isVisted.containsKey(edge)){
                DFS(graph,isVisted,edge);
            }
        }
    }

    private void DFSStack(Graph graph,Map<Graph.Node, Boolean> isVisted, Graph.Node source){
        Stack<Graph.Node> stack = new Stack<>();
        Graph.Node curNode = source;
        stack.push(curNode);

        while(!stack.isEmpty()){
            curNode = stack.pop();
            if(!isVisted.containsKey(curNode)){
                System.out.print(curNode.getValue()+" ");
                isVisted.put(curNode,true);
                Set<Graph.Node> edges = graph.getEdges(curNode);
                stack.addAll(edges);
            }
        }
    }

    private Stack<Graph.Node> findPathInGraph(Graph graph,Map<Graph.Node, Boolean> isVisted, Graph.Node source,Graph.Node target){
        Stack<Graph.Node> stack = new Stack<>();
        stack.push(source);
        Stack<Graph.Node> pathStack = new Stack<>();

        Graph.Node curNode;
        while(!stack.isEmpty()){
            curNode = stack.peek();
            if(!isVisted.containsKey(curNode)){
                pathStack.push(curNode);
                System.out.print(curNode.getValue());
                if(curNode.equals(target)){
                    break;
                }
                isVisted.put(curNode,true);
                Set<Graph.Node> edges = graph.getEdges(curNode);
                int edgeVisitedCount = 0;
                for(Graph.Node node : edges){
                    if(isVisted.containsKey(node)){
                        edgeVisitedCount++;
                    }else{
                        stack.push(node);
                    }
                }
                // all the edges are visited
                if(edgeVisitedCount == edges.size()){
                    stack.pop();
                    pathStack.pop();
                    isVisted.put(curNode,false);
                }
            }else{
                stack.pop();
            }
        }
        return stack.isEmpty() ? null : pathStack;
    }

    public static void main(String[] args) {
        DFSExample dfsExample = new DFSExample();
        Graph graph = new Graph();
        //dfsExample.printTraversal(graph.new Node(1));
        dfsExample.printPath();
    }

}
