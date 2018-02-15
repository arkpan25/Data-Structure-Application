package project5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *Class that implements Kruskal's algorithm to find a minimum spanning tree of a graph.
 * @author Fangzhou Pan
 * Grade 100/100
 */
public class Kruskals {

	private int numVertices;
	private List<String> graph;
	private DisjSets ds;
	private PriorityQueue<Edge> pq;
	//map to store the cities' name and their corresponding integer label used in disjoint set.
	private Map<String,Integer> cityLabel;
	//map to store the city vertex and their edges(road) connected to it.
	private Map<String,ArrayList<Edge>> minSpTree;

	/**
	 * Construct the Kruskals object.
	 * @param fname the name of the file which contains the graph information.
	 */
	public Kruskals(String fname) {
		numVertices = 0;
		graph = new ArrayList<>();
		pq = new PriorityQueue<>();
		cityLabel = new HashMap<>();
		minSpTree = new HashMap<>();

		this.loadGraph(fname);
		this.kruskal();
	}

	/**
	 * Method to read the csv file and load the contents of the file
	 * line by line in the graph list.
	 * @param fname the name of the file which contains the graph information.
	 */
	public void loadGraph(String fname){
		 File csv = new File(fname);  // CSV file path
		 BufferedReader br = null;
		 try{
		     br = new BufferedReader(new FileReader(csv));
		 }
		 catch (FileNotFoundException e){
		        e.printStackTrace();
		 }
		 String line = "";
		 try {
		      while ((line = br.readLine()) != null)
		      {
		           graph.add(line);
		      }


		 }
		 catch (IOException e){
		        e.printStackTrace();
		    }

	}

	/**
	 * Method that implements Kruskal's algorithm to find the minimum spanning tree.
	 */
	public void kruskal(){
		ds = new DisjSets(graph.size());
		this.setupGraph();
		for (int edgesAccepted = 0; edgesAccepted<this.numVertices-1;){
			Edge minEdge = pq.poll();
			Integer sc = this.cityLabel.get(minEdge.startCity);
			Integer dc = this.cityLabel.get(minEdge.destCity);
			int uset = ds.find(sc);
			int vset = ds.find(dc);
	        if (uset != vset)    // if not same set (not yet connected)
	        {
	              // accept the edge
	              edgesAccepted++;
	              ds.union(uset, vset); // connect them
	              minSpTree.get(minEdge.startCity).add(minEdge);
	              //minSpTree.get(minEdge.destCity).add(minEdge);
	         }

		}

	}
    /**
     * Method that outputs each edge of the minimum spanning tree as the names of the two
     * cities and the distance between them.And the sum of all of the distances in the tree.
     */
	public void printMinSpTree(){
		int totDistance = 0;
		for (String city: this.minSpTree.keySet()){
			for(Edge e: this.minSpTree.get(city)){
				System.out.println(e);
				totDistance += e.length;
			}
		}
		System.out.print("Sum of all of the distances in the tree: " + totDistance);
	}

	/**
	 * Internal method to parse the graph and set up the cityLabel map(store the cities' name
	 * and their corresponding integer label used in disjoint set.) and the minSpTree(store the city
	 * vertex as string  and their edges(road) stored in ArrayList connected to it.Add the edges in
	 * the PriorityQueue pq in the end.
	 */
	private void setupGraph(){
		for (String line: graph){
			this.numVertices++;
			ArrayList<Edge> Edges = new ArrayList<>();
			String[] msg = line.split(",");
			String start = msg[0];
			this.minSpTree.put(start, Edges);
			this.cityLabel.put(start, this.numVertices-1);
			for (int idx = 1; idx<msg.length;idx++){
				String dest = msg[idx];
				int length = Integer.parseInt(msg[++idx]);
				Edge edge = new Edge(start,dest,length);
				//only store the edge once between two cities for the undirected graph.
				if (!cityLabel.containsKey(dest)) pq.add(edge);
			}
		}
	}

	 private static class Edge implements Comparable<Edge>{

		 String startCity;
		 String destCity;
		 int length;

		 public Edge(String sc,String dc, int l){
			 this.startCity = sc;
			 this.destCity = dc;
			 this.length = l;
		 }


		@Override
		public int compareTo(Edge e) {
			if (this.length < e.length) return -1;
			if (this.length> e.length) return 1;
			return 0;
		}

		public String toString(){
			StringBuilder sb = new StringBuilder(startCity);
			sb.append(" ---- ");
			sb.append(destCity);
			sb.append(",  ");
			sb.append(Integer.toString(length));
			return sb.toString();
		}
	 }



	public static void main(String[] args) {
		String fname = "src/project5/assn9_data.csv";
		Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the path of the file contains the graph: ");
        fname = sc.nextLine();
		Kruskals kr = new Kruskals(fname);
        kr.printMinSpTree();

	}

}
