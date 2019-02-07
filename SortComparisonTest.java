import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.
    
    
    @Test
    public void testSelectionSort() {
    	double[] test = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	
    	SortComparison s = new SortComparison();
    	
    	printArrayValues(test);

    	test = s.selectionSort(test);
    	printArrayValues(test);
    	
    }
    
    public void printArrayValues(double[] a) {
    	for(int i =0; i < a.length-1; i++) {
    		System.out.print(a[i] + ", ");
    	}
    	System.out.println(a[a.length-1]);
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        //TODO: implement this method
    }

}
