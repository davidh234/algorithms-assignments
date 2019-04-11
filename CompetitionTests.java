//import org.junit.Test;

public class CompetitionTests {

   // @Test
    public void testDijkstraConstructor() {

        //TODO
    }

   // @Test
    public void testFWConstructor() {
        //TODO
    	
    }
    
    public static void main(String[] args) {
    	CompetitionDijkstra cd = new CompetitionDijkstra("input-E.txt",51,70,88); 	
    	CompetitionFloydWarshall cF = new CompetitionFloydWarshall("input-E.txt",51,70,88); 	
    	
    	int val1 = cd.timeRequiredforCompetition();
    	int val2 = cF.timeRequiredforCompetition();
    	
    	System.out.println("Dijkstra:" + val1 + "\n" + "Floyd-Warshall:" + val2 + "\n");
    }
}
