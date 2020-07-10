package com.sebin.interview.graphAlgo;

import java.util.*;

public class PrimsImpl {

    static class Graph{

        private Map<Vertex, List<Edge>> adjacencyList = new HashMap<>();

        public void addEdge(Edge edge){
            if(!adjacencyList.containsKey(edge.src)){
                List<Edge> newEdgeList = new ArrayList<>();
                adjacencyList.put(edge.src,newEdgeList);
            }
            List<Edge> edgeList = adjacencyList.get(edge.src);
            edgeList.add(edge);
        }

        public Set<Vertex> getAllVertex(){
            return adjacencyList.keySet();
        }

        public List<Edge> getOutgoingEdges(Vertex src){
            if(adjacencyList.containsKey(src)){
                return adjacencyList.get(src);
            }else{
                return new ArrayList<>();
            }
        }

        public void addUndirectedEdge(Edge edge){
            addEdge(edge);
            //Edge reverse = new Edge(new Vertex(edge.dest.value),new Vertex(edge.src.value),edge.weight);
            Edge reverse = new Edge(edge.dest,edge.src,edge.weight);
            addEdge(reverse);
        }

        public void print(){
            for(Vertex v : adjacencyList.keySet()){
                System.out.print(v.value+" -> ");
                List<Edge> edges = adjacencyList.get(v);
                for(Edge edge : edges){
                    System.out.print(edge.dest.value+"["+edge.weight+"] - ");
                }
                System.out.println("");
            }
        }

    }

    static class Vertex {
        private Integer value;

        public Vertex(Integer value){
            this.value = value;
        }

        public boolean equals(Object o){
            if(this == o){
                return true;
            }
            if(this.value == ((Vertex)o).value){
                return true;
            }
            return false;
        }

        public int hashCode() {
            return value.hashCode();
        }
    }
    static class Edge{
        public Vertex src;
        public Vertex dest;
        public int weight;

        public Edge(Vertex src,Vertex dest,int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public Graph getUndirectedGraph(){
        Graph graph = new Graph();
        Edge edge1 = new Edge(new Vertex(0),new Vertex(1),4);
        Edge edge2 = new Edge(new Vertex(0),new Vertex(2),3);
        Edge edge3 = new Edge(new Vertex(1),new Vertex(2),1);
        Edge edge4 = new Edge(new Vertex(1),new Vertex(3),2);
        Edge edge5 = new Edge(new Vertex(2),new Vertex(3),4);
        Edge edge6 = new Edge(new Vertex(3),new Vertex(4),2);
        Edge edge7 = new Edge(new Vertex(4),new Vertex(5),6);

        graph.addUndirectedEdge(edge1);
        graph.addUndirectedEdge(edge2);
        graph.addUndirectedEdge(edge3);
        graph.addUndirectedEdge(edge4);
        graph.addUndirectedEdge(edge5);
        graph.addUndirectedEdge(edge6);
        graph.addUndirectedEdge(edge7);

        return graph;
    }

    static class HeapNode {
        Vertex vertex;
        int keyValue;

        public HeapNode(Vertex vertex,int keyValue){
            this.keyValue = keyValue;
            this.vertex = vertex;
        }
    }

    static class Result{
        Vertex parentVertex;
        int weight;

        public Result(Vertex parentVertex,int weight){
            this.parentVertex = parentVertex;
            this.weight = weight;
        }
    }

    public void prims(Graph graph,Vertex srcVertex){

        Set<Vertex> presentInMST = new HashSet<>();
        Map<Vertex,Result> resultMap = new HashMap<>();
        Map<Vertex,Integer> keyMap = new HashMap<>();

        Comparator<HeapNode> pqComparator = (node1, node2) -> node1.keyValue - node2.keyValue;
        PriorityQueue<HeapNode> pq = new PriorityQueue(pqComparator);
        HeapNode srcnode = new HeapNode(srcVertex,0);
        keyMap.put(srcVertex,0);
        Result result = new Result(null,0);
        resultMap.put(srcVertex,result);
        pq.add(srcnode);

        Set<Vertex> vertexSet = graph.getAllVertex();
        //vertexSet.remove(srcVertex);
        for(Vertex vertex : vertexSet){
            //HeapNode node = new HeapNode(vertex,Integer.MAX_VALUE);
            keyMap.put(vertex,Integer.MAX_VALUE);
            //pq.add(node);
            result = new Result(null,0);
            resultMap.put(vertex,result);
        }

        //decreaseKey(pq, srcVertex, 0);


        Iterator<HeapNode> iter = pq.iterator();
        while(iter.hasNext()){
            HeapNode node1 = iter.next();
            System.out.print(node1.vertex.value +" value "+node1.keyValue+"-->");
        }

        while(!pq.isEmpty()){
            HeapNode currentNode = pq.poll();
            presentInMST.add(currentNode.vertex);

            List<Edge> edges = graph.getOutgoingEdges(currentNode.vertex);
            for(Edge edge : edges){
                if(!presentInMST.contains(edge.dest)) {
                    if (keyMap.containsKey(edge.dest) && edge.weight < keyMap.get(edge.dest)) {
                        keyMap.put(edge.dest, edge.weight);
                        //decreaseKey(pq, edge.dest, edge.weight);

                        result = new Result(currentNode.vertex, edge.weight);
                        resultMap.put(edge.dest, result);
                        printResult(resultMap);

                        HeapNode node = new HeapNode(edge.dest,edge.weight);
                        pq.add(node);
                    }
                }
            }
        }

    }

    private void printResult(Map<Vertex,Result> resultMap){
        System.out.println("---------------------");
        for(Vertex v : resultMap.keySet()){
            System.out.print(v.value+" -> ");
            Result result = resultMap.get(v);
            System.out.println(((result.parentVertex != null) ? result.parentVertex.value : "null") +" | "+result.weight);
        }
    }

    private void decreaseKey(PriorityQueue pq, Vertex vertex, Integer newKey){
        Iterator<HeapNode> iterator = pq.iterator();
        while(iterator.hasNext()){
            HeapNode curNode = iterator.next();
            if(curNode.vertex.equals(vertex)){
                iterator.remove();
                break;
            }
        }
        pq.add(new HeapNode(vertex,newKey));
    }

    public static void main(String[] args) {
        PrimsImpl prims = new PrimsImpl();
        Graph graph = prims.getUndirectedGraph();
        graph.print();
        prims.prims(graph,new Vertex(0));
    }
}
