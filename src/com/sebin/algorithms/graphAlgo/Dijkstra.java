package com.sebin.interview.graphAlgo;

import com.sun.tools.javac.Main;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {

        Dijkstra dijkstra = new Dijkstra();
        Graph newGraph = Graph.createGraph();
        dijkstra.shortestPath(newGraph,0);
    }



    public void shortestPath(Graph graph,Integer src){

        Map<Integer,Parent> parent = new HashMap();
        Comparator<HeapNode> comp = (n1,n2) -> n1.distance - n2.distance;
        PriorityQueue<HeapNode> pq = new PriorityQueue(comp);
        Map<Integer,Integer> distValue = new HashMap<>();
        Set<Integer> isVisited = new HashSet<>();
        HeapNode srcNode = new HeapNode(src,0);
        pq.add(srcNode);

        Set<Integer> vertices = graph.getVertices();
        System.out.println("vertices "+Arrays.toString(vertices.toArray()));
        for(Integer vertex : vertices){
            distValue.put(vertex,Integer.MAX_VALUE);
        }
        distValue.put(src,0);

        while(!pq.isEmpty()){
            HeapNode curNode = pq.poll();
            System.out.println("curNode "+curNode.vertex+" curnode dist"+curNode.distance);
            if(isVisited.contains(curNode.vertex)){
                continue;
            }
            isVisited.add(curNode.vertex);

            List<Edge> edges = graph.getEdges(curNode.vertex);

            for(Edge edge : edges){
                if(!isVisited.contains(edge.dest)){
                    Integer curDistance = distValue.get(edge.dest);
//                    System.out.println(curNode.vertex+" "+curNode.distance);
                    System.out.println("curNode "+curNode.vertex+" edge -> "+edge.dest+" weight "+edge.weight+" curDistance "+curDistance);
                    if((/*curNode.distance + */edge.weight) < curDistance){
                        curDistance = /*curNode.distance + */edge.weight;
                        distValue.put(edge.dest,curDistance);

                        HeapNode heapNode = new HeapNode(edge.dest,curDistance);
                        pq.add(heapNode);

                        Parent parent1 = new Parent(curNode.vertex,curDistance);
                        parent.put(edge.dest,parent1);
                    }

                }
            }
        }

        for(Integer key : parent.keySet()){
            System.out.println(" Node "+key+" parent "+parent.get(key).parent+ " distance " +parent.get(key).distance);
        }

    }

    class Parent{
        Integer parent;
        Integer distance;

        Parent(Integer parent, Integer distance){
            this.parent = parent;
            this.distance = distance;
        }
    }

    class HeapNode{
        Integer vertex;
        Integer distance;

        HeapNode(Integer vertex,Integer distance){
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    static class Graph{
        Map<Integer, List<Edge>> edges;

        Graph(){
            edges = new HashMap<>();
        }

        void addEdge(Integer src,Integer dest,Integer weight){
            if(!edges.containsKey(src)){
                List<Edge> list = new ArrayList<>();
                edges.put(src,list);
            }
            Edge edge = new Edge(src,dest,weight);
            edges.get(src).add(edge);
        }


        public List<Edge> getEdges(Integer vertex){
            if(edges.containsKey(vertex)){
                return edges.get(vertex);
            }else{
                return new ArrayList<>();
            }
        }

        public Set<Integer> getVertices(){
            return edges.keySet();
        }

        public static Graph createGraph(){
            Graph graph = new Graph();
            graph.addEdge(0,1,4);
            graph.addEdge(0,7,8);
            graph.addEdge(1,0,4);
            graph.addEdge(1,7,11);
            graph.addEdge(1,2,8);
            graph.addEdge(2,1,8);
            graph.addEdge(2,8,2);
            graph.addEdge(2,5,4);
            graph.addEdge(2,3,7);
            graph.addEdge(3,2,7);
            graph.addEdge(3,5,14);
            graph.addEdge(3,4,9);
            graph.addEdge(4,3,9);
            graph.addEdge(4,5,10);
            graph.addEdge(5,4,10);
            graph.addEdge(5,3,14);
            graph.addEdge(5,2,4);
            graph.addEdge(5,6,2);
            graph.addEdge(6,5,2);
            graph.addEdge(6,8,6);
            graph.addEdge(6,7,1);
            graph.addEdge(7,6,1);
            graph.addEdge(7,8,7);
            graph.addEdge(7,1,11);
            graph.addEdge(7,0,8);
            graph.addEdge(8,7,7);
            graph.addEdge(8,2,2);
            graph.addEdge(8,6,6);

            return graph;
        }

    }

    static class Edge{
        Integer src;
        Integer dest;
        Integer weight;

        Edge(Integer src,Integer dest,Integer weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
