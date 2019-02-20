import static org.junit.Assert.*;

import java.util.Arrays;

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
    
    // ----------------------------------------------------------
    /**
     * 
     */
    
    @Test
    public void testQuickSort() {
    	double[] test = {1005, 11.5, 13, 6, 2, 29, 4, 44, 444, 102, 33, 106.5, 98.46};
    	double[] control = {1005, 11.5, 13, 6, 2, 29, 4, 44, 444, 102, 33, 106.5, 98.46};
    	
    	SortComparison a = new SortComparison();
    	
    	a.quickSort(test);
    	Arrays.sort(control);
    	
    	assertArrayEquals(control, test, 0);

    }
    
    
    // ----------------------------------------------------------
    /**
     * 
     */
    
    @Test
    public void testMergeSortRecursive() {
    	double[] test = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	double[] control = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	
    	SortComparison a = new SortComparison();
    	
    	a.mergeSortRecursive(test);
    	Arrays.sort(control);
    	
    	assertArrayEquals(control, test, 0);
    }
    
    // ----------------------------------------------------------
    /**
     * 
     */
    
    @Test
    public void testMergeSortIterative() {
    	double[] test = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	double[] control = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	
    	SortComparison s = new SortComparison();

    	test = s.mergeSortIterative(test);  
    	Arrays.sort(control);
    	
    	assertArrayEquals(control, test, 0);
    }
    
    
    // ----------------------------------------------------------
    /**
     * Check that the insertion sort method is valid
     */
    
    @Test
    public void testInsertionSort() {
    	double[] test = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	double[] control = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	
    	SortComparison s = new SortComparison();

    	test = s.insertionSort(test);  
    	Arrays.sort(control);
    	
    	assertArrayEquals(control, test, 0);
    }
    
    // ----------------------------------------------------------
    /**
     * Check that the selection sort method is valid
     */
    
    @Test
    public void testSelectionSort() {
    	double[] test = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	double[] control = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	
    	SortComparison s = new SortComparison();
    	
    	test = s.selectionSort(test);
    	Arrays.sort(control);
    	
    	assertArrayEquals(control, test, 0);

    }
    
    @Test
    public void testShuffle() {
    	double[] test = {15, 4, 3, 7, 8, 6, 5, 11, 1, 2, 8};
    	
    	SortComparison s = new SortComparison();
    	
    	test = s.shuffle(test);
    	
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
