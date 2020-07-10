package com.sebin.interview.graphAlgo;

import java.sql.SQLOutput;
import java.util.*;

public class Graph {

    // Map<Integer,List<Edge>> adjList;
    //

    public class Edge{
        Integer src;
        Integer dest;
        Integer weight;
    }

    public class HeapNode{
        Integer key;
        Integer value;

        public HeapNode(Integer key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    public void shortestPath(Integer source,Graph graph){

        Map<Integer,Integer> values = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer,Integer> parent = new HashMap<>();

        Comparator<HeapNode> comparator = (o1,o2) -> o1.value - o2.value;
        PriorityQueue<HeapNode> priorityQueue = new PriorityQueue(comparator);

        for(Integer vertex : graph.getVertices()){
            values.put(vertex,Integer.MAX_VALUE);
        }

        priorityQueue.add(new HeapNode(source,0));

        while(!priorityQueue.isEmpty()){
            HeapNode currentVertex = priorityQueue.poll();
            if(visited.contains(currentVertex.key)){
                continue;
            }
            visited.add(currentVertex.key);

            List<Edge> edges = graph.getEdges(currentVertex.key);

            for(Edge edge : edges){
                if(!visited.contains(edge.dest)){
                    
                    if(values.get(currentVertex.key) + edge.weight < values.get(edge.dest)){
                        values.put(edge.dest,values.get(currentVertex.key) + edge.weight);
                        parent.put(edge.dest,currentVertex.key);

                        HeapNode node = new HeapNode(edge.dest,values.get(currentVertex.key) + edge.weight);
                        priorityQueue.add(node);
                    }
                }
            }
        }

        Integer dest = 5;
        Integer distance = -1;
        while(distance != Integer.MAX_VALUE){
            distance = values.get(dest);
            System.out.print(distance + dest);
            dest = parent.get(dest);
        }
    }
}
