import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	
	int V;
	int E;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
    	String[] input = readFromFile(filename);
    	double[][] paths = parseInputTo2DArray(input);
    	double longestPath = timeRequiredforCompetition(paths);
    	
    	System.out.println((int)Math.ceil(longestPath * 1000 / sA));
        //TODO
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public double timeRequiredforCompetition(double[][] paths){
        //TO DO
    	paths = floydWarshall(paths);
    	double longestPath = 0.0;
    	for(int i =0; i < V; i++) {
    		for(int j =0; j < V; j++) {
    			if(paths[i][j] > longestPath) {
    				longestPath = paths[i][j];
    			}
    		}
    	}
        return longestPath;
    }
    
    
    private double[][] floydWarshall(double[][] graph) {
            int i, j, k; 
            
            for(i =0; i < V; i++) {
            	for(j =0; j < V; j++) {
            		if(graph[i][j] == 0 && i != j) {
            			graph[i][j] = Integer.MAX_VALUE;
            		}
            	}
            }

            for (k = 0; k < V; k++) { 
                for (i = 0; i < V; i++) { 
                    for (j = 0; j < V; j++) { 
                        if (graph[i][k] + graph[k][j] < graph[i][j]) 
                        	graph[i][j] = graph[i][k] + graph[k][j]; 
                    } 
                } 
            } 
            return graph;
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
    
    
    private double[][] parseInputTo2DArray(String[] input) {
    	double[][] values = new double[V][V];
    	
    	for(int i =0; i < E; i++) {
    		//input type: from, to, value
    		//values[from][to] = value
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
    		int from = Integer.parseInt(arr[0]);
    		int to = Integer.parseInt(arr[1]);
    		double weight = Double.parseDouble(arr[2]);
    		
    		values[from][to] = weight;
    	}
    	return values;
    }

}
