import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author David Hooban
 *  @version HT 2019
 */

//------------------------------------------------------------------------
/* Experiment Results:
 * Average result of 3 attempts
 * 
 * Values recorded in nano-seconds then converted to milliseconds to get more accurate values
 * 
 * 	File type           |  Insertion |	Quick  |  Merge Recursive   |  Merge Iterative   |  Selection
 * ------------------------------------------------------------------------------------------------------
 * 10 Random            |  0.00164   | 0.014058|    0.004376        |     0.035145       | 0.016273
 * ------------------------------------------------------------------------------------------------------
 * 100 Random           |  0.119521  | 0.099008|    0.073436        |     0.013812       | 0.081093
 * ------------------------------------------------------------------------------------------------------
 * 1000 Random          |  1.896341  | 0.573538|    0.087288        |     0.145777       | 2.037059
 * ------------------------------------------------------------------------------------------------------
 * 1000 few unique      |  1.942768  | 0.144684|    0.072479        |     0.11559        | 0.306995
 * ------------------------------------------------------------------------------------------------------
 * 1000 nearly ordered  |  0.010837  | 0.119111|    0.055841        |     0.081248       | 0.275418
 * ------------------------------------------------------------------------------------------------------
 * 1000 reverse order   |  0.177418  | 0.266205|    0.057299        |     0.071906       | 0.367452
 * ------------------------------------------------------------------------------------------------------
 * 1000 sorted          |  0.002325  | 0.120341|    0.037606        |     0.097367       | 0.582769
 * 
 * 
 * Questions:
 * A. Which of the sorting algorithms does the order of input have an impact on? Why?
 * 
 * 	Answer: Insertion sort is most impacted by the order of input. For example with the random 1000 numbers it took approx. 
 * 1.9 milliseconds, and when the array was sorted, only took 0.002 milliseconds. The drastic difference is because
 * of how insertion sort works, where the most work done is inserting the next element into a sorted position. as this may
 * require shifting all elements in the sorted array, this may be O(N) work, and for N elements, this gives us O(N^2) for
 * standard insertion sort. But if the array is already sorted, then each element will be 'inserted' into its own position,
 * and there will be no need to shift any elements, reducing the runtime to O(N), hence the major speed gain.
 * 
 *B. Which algorithm has the biggest difference between the best and worst performance, based
 *on the type of input, for the input of size 1000? Why?
 *
 *Answer: As outlined in answer A, insertion sort will have the biggest difference between worst and best performance as sorted
 *input gives us O(N) run-time, where as with random input we will have O(N^2) performance. By shuffling the input for
 *Quicksort we reduce the chance of the worst-case run time of O(N^2). This means insertion sort is the most affected 
 *by input type.
 *	   
 *C. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 *based on the input size? Please consider only input files with random order for this answer.
 *
 *Answer: For my experiments, insertion sort has the worst scalability, as it had the biggest range between the smallest and 
 *biggest input sizes. Recursive merge-sort had the best scalability, as it Had the smallest range in times between 
 *the smallest and biggest input.
 *	   
 *D. Did you observe any difference between iterative and recursive implementations of merge sort?
 *
 *Answer: To break down the different merge sort implementations, there is the dividing portion and the merging portion. Since
 *both implementations use the same 'merge' method there is no difference. The real difference is whether how we divide
 *the input array. Recursion starts with the overall array and recursively splits the array into two, and then 'rebuilds'
 *the array, in order, making use of the merge method. The iterative approach iterates over the array, starting at 
 *two elements and taking larger portions (2^i items, where 1 <= i <= array.length) of the arrays and sorting them as it goes, 
 *such that larger sorted portions of the array are merging into each	other each iteration.
 *			
 *	   
 *E. Which algorithm is the fastest for each of the 7 input files? 
 *Answer:
 *10Random:               Insertion Sort
 *100Random:              Merge sort Iterative
 *1000Random:             Merge sort recursive
 *1000 few unique:        Merge sort recursive
 *1000 nearly ordered:    Merge sort recursive
 *1000 reverse order:     Merge sort recursive
 *1000 sorted:            Insertion sort
 *
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
    	double[] control = Arrays.copyOf(test, test.length);
    	
    	SortComparison s = new SortComparison();
    	
    	test = s.shuffle(test);
    	
    	int count =0;
    	for(int i =0; i < test.length; i++) {
    		if(test[i] == control[i]){
    			count++;
    		}
    	}
    	if(count == test.length-1){
    		assert false;
    	} else {
    		assert true;
    	}
    }
    
    
    //Helper method for printing all values of an array for testing
    public static void printArrayValues(double[] a) {
    	for(int i =0; i < 10; i++) {
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
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbers10.txt",10, "numbers10");
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbers100.txt",100, "numbers100");
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbers1000.txt",1000, "numbers1000");
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbers1000Duplicates.txt",1000,"numbers1000Duplicates");
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbersNearlyOrdered1000.txt",1000, "numbersNearlyOrdered1000");
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbersReverse1000.txt",1000, "numbersReverse1000");
    	runExperiment("C:\\Users\\david\\workspace\\dsanda\\src\\numbersSorted1000.txt",1000,"numbersSorted1000");
    }
    
    //using double array, compare start and elapsed times to determine run time of different sort methods
    private static void runExperiment(String filename, int entrySize, String fileType) {
    	//read in file
    	double[] values = getValuesFromFile(filename, entrySize);
    	
    	//make duplicate of values array so we don't have to re-read from file for each sort method
    	double[] current = Arrays.copyOf(values, values.length);
    	
    	//list to store each average time we record
    	ArrayList<Long> times = new ArrayList<Long>();	
    	
    	//instance of our sort comparison class
    	SortComparison s = new SortComparison();
    	
    	long startTime; 		//system time at beginning of sort method
    	long elapsedTime; 		//system time at end of sort method
    	long dist; 				//elapsedTime - startTime
    	long currentTime = 0;   //average dist for 3 trials of the 
    	//run experiment for each sort.
    	
    	printArrayValues(current);
    	for(int i =0; i < 3; i++) {
	        startTime = System.nanoTime();
	        current = s.insertionSort(current);
	    	elapsedTime = System.nanoTime();
	    	dist = elapsedTime - startTime;	
	    	currentTime = currentTime + dist;
    	}
    	currentTime = currentTime / 3;
    	times.add(currentTime);
    	
    	currentTime = 0;
    	printArrayValues(current);
    	for(int i =0; i < 3; i++) {
        	current = Arrays.copyOf(values, values.length);
	        startTime = System.nanoTime();
	        current = s.quickSort(current);
	    	elapsedTime = System.nanoTime();
	    	dist = elapsedTime - startTime;	
	    	currentTime = currentTime + dist;
    	}
    	currentTime = currentTime / 3;
    	times.add(currentTime);
    	
    	currentTime = 0;
    	printArrayValues(current);
    	for(int i =0; i < 3; i++) {
        	current = Arrays.copyOf(values, values.length);
	        startTime = System.nanoTime();
	        current = s.mergeSortRecursive(current);
	    	elapsedTime = System.nanoTime();
	    	dist = elapsedTime - startTime;	
	    	currentTime = currentTime + dist;
    	}
    	currentTime = currentTime / 3;
    	times.add(currentTime);
    	
    	currentTime = 0;
    	printArrayValues(current);
    	for(int i =0; i < 3; i++) {
        	current = Arrays.copyOf(values, values.length);
	        startTime = System.nanoTime();
	        current = s.mergeSortIterative(current);
	    	elapsedTime = System.nanoTime();
	    	dist = elapsedTime - startTime;	
	    	currentTime = currentTime + dist;
    	}
    	currentTime = currentTime / 3;
    	times.add(currentTime);
    	
    	currentTime = 0;
    	printArrayValues(current);
    	for(int i =0; i < 3; i++) {
        	current = Arrays.copyOf(values, values.length);
	        startTime = System.nanoTime();
	    	current = s.selectionSort(current);
	    	elapsedTime = System.nanoTime();
	    	dist = elapsedTime - startTime;	
	    	currentTime = currentTime + dist;
    	}
    	currentTime = currentTime / 3;
    	times.add(currentTime);
    	
    	System.out.println("File size: " + entrySize + " filename: " + fileType+"\n" + 
    					   "Insertion sort run time: " + times.get(0) + "\n" +
    					   "quick sort run time: " + times.get(1) + "\n" +
    					   "Merge sort recursive run time: " + times.get(2) + "\n" +
    					   "Merge sort iterative run time: " + times.get(3) + "\n" +
    					   "Selection sort run time: " + times.get(4) + "\n");
    }
    
    //read values from file and return double array of values
    private static double[] getValuesFromFile(String filename, int entrySize) {
    	double[] values = new double[entrySize];
    	try {
    		FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			for(int i =0; i < entrySize; i++){
				try {
					values[i] = Double.parseDouble(br.readLine());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return values;
    }

}
