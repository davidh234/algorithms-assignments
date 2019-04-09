import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	
	private int V;	//number of vertices
	private int E;  //number of edges
	private int sA, sB, sC;
	Bag<Double> bag = new Bag<Double>();
	Bag[] Vertices;
	
	//directed edge for creation of graph
	private class Edge {
		Edge next = null;
		int to, from;
		double weight;
		
		public Edge(int from, int to, double weight) {
			this.to = to;
			this.from = from;
			this.weight = weight;
		}
	}
	
	private class Node implements Comparable<Node>{
		int node;
		double cost;
		
		public Node(int node, double cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if(o.cost != this.cost) {
				return this.cost > o.cost ? 1 : -1;
			}
			return 0;
		}
	}


    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	//gets back strings of each edge and also inits V and E
    	String[] input = readFromFile(filename);
    	Vertices = new Bag[V];
    	
    	//initialise each bag item in the vertices array
    	for(int i =0; i < Vertices.length; i++) {
    		Vertices[i] = new Bag<Double>();
    	}
    	
    	parseInputToGraph(input);
    	
    	double result = timeRequiredforCompetition();
    	
    	
    	//take the slowest of the three and use
    	System.out.println((int)Math.ceil((result * 1000 / sA)));
    }


    /**
    * @return int: minimum minutes that will pa
    * ss before the three contestants can meet
     */
    public double timeRequiredforCompetition(){

        //TO DO
    	
    	//given some unknown destination node, what is the longest time it'll take to get there
    	//we're looking for worst case scenario using shortest paths 
    	//don't need to do all three, just find the overall worst, divided by the overall slowest
    	
    	
    	double longestPath = 0.0;
    	double[] shortestPaths;
    	//return value?? array?
    	for(int i =0; i < Vertices.length; i++) {
    		shortestPaths = dijkstraValue(i);

    		for(int j =0; j < shortestPaths.length; j++) {
    			if(longestPath < shortestPaths[j]) {
    				longestPath = shortestPaths[j];
    			}
    		}
    	}
        return longestPath;
    }
    
    
    double[] dists;
    Edge[] edges;
	PriorityQueue<Node> pq = new PriorityQueue<Node>();
	
    private double[] dijkstraValue(int source) {   	
    	dists  = new double[V];
    	edges = new Edge[V];
    	for(int i =0; i < dists.length; i++) {
    		dists[i] = Integer.MAX_VALUE;
    	}
    	dists[source] = 0;
    	pq.add((new Node(source, 0)));
    	
    	while(!pq.isEmpty()) {
    		 Node tmp = pq.peek();
    		 //System.out.println(tmp.node);
    		 pq.remove();
    		 dists = relax(Vertices[tmp.node], tmp.node);
    	}
    	return dists;
    }
    
    
    private double[] relax(Bag<Double> graph, int index) {
    	for(Edge e : graph.adj()) {
    		int w = e.to;
    		if(dists[w] > dists[index] + e.weight) {
    			dists[w] = dists[index] + e.weight;
    			edges[w] = e;
    			if(!pq.contains(w)) {
    				pq.add(new Node(w,dists[w]) );
    			} else {
    				int tmp = pq.remove().node;
    				pq.add(new Node(tmp, dists[w]));
    			}
    		}
    	}
    	return dists;
    }
    
    private void parseInputToGraph(String[] input) {
    	for(int i =0; i < E; i++) {

    		String current = input[i];
    		String[] arr = new String[3];
    		String[] tmp = current.split(" ");
    		int count =0;

    		//handling spaces that are present in larger input file
    		for(int j =0; j < tmp.length;j++) {
    			//System.out.print("J:"+j+ " = " + tmp[j]);
    			if(tmp[j] != " " && !tmp[j].isEmpty()) {
    				if(count < 3) {
    					arr[count] = tmp[j];
        				count++;			
    				}
    			}
    		}
    		//System.out.print("\n");
    		
    		int from = Integer.parseInt(arr[0]);
    		int to = Integer.parseInt(arr[1]);
    		double weight = Double.parseDouble(arr[2]);
    		
    		// add it to the bag for the 'from' index
    		//System.out.println("Node: "+i+ " " + from + "->" + to + " weight:" + weight + "\n");
    		
    		Vertices[from].add(from, to, weight);
    	}
    }
    
    
    private String[] readFromFile(String filename) {
		BufferedReader br;
		FileReader fr;
		
		String[] lines = null;

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String current;
			current = br.readLine();
			int numVertices =Integer.parseInt(current);
			current = br.readLine();
			int numEdges = Integer.parseInt(current);
			
			V = numVertices;
			E = numEdges;
			
			lines = new String[numEdges];
			
			int i = 0;
			while ((current = br.readLine()) != null) {
				lines[i] = current;
				i++;
			}		
			br.close();
			fr.close();

		} catch (IOException e) {

			e.printStackTrace();

		} 
		return lines;
	}
    
    //Linked List implementation of a bag
    private class Bag<Item>{
    	Edge head;
    	
    	public void add(int from, int to, double weight) {
    		Edge edge = new Edge(from, to, weight);
    		
    		if(head != null) {
    			edge.next = head;
    			head = edge;
    		}
    		else {
    			head = edge;
    		}
    	}
    	
    	public boolean isEmpty() {
    		return (head == null);
    	}
    	
    	public int size() {
    		int count = 0;
    		if(head != null) {
	    		Edge current = head;
	    		count++;
	    		while(current.next != null) {
	    			current = current.next;
	    			count++;
	    		}
    		}
    		return count;
    	}
    	
    	
    	public Edge[] adj() {
    		if(head != null) {
    			int size = size();
    			Edge[] edges = new Edge[size];
    			Edge current = head;
    			int i =0;
    			while(current != null && i < size) {
    				edges[i] = current;
    				current = current.next;
    				i++;
    			}
    			return edges;
    		}
    		else return null;
    	}
    	
    	public String toString() {
    		String output = "";
    		int i = 0;
    		if(head != null) {
    			Edge current = head;
	    		while(current != null) {
	    			output = output + "Edge " + i + ", " + current.from + " -> " + current.to + ",  weight: " + current.weight + "\n";
	    			current = current.next;
	    			i++;
	    		}
    		}
    		return output;
    	}
    	
    }
}
