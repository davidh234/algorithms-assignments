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
 * 	File type			|  Insertion |	Quick  |  Merge Recursive	|	Merge Iterative	 |   Selection
 * ------------------------------------------------------------------------------------------------------
 * 10 Random			|  0.005197	 | 0.03459 |    0.01011967	 	|     0.03104234	 | 0.00437634
 * ------------------------------------------------------------------------------------------------------
 * 100 Random			|  0.084786	 | 0.06769 |    0.059487		|     0.0477267		 | 0.086701
 * ------------------------------------------------------------------------------------------------------
 * 1000 Random			|  7.896065	 | 0.99063 | 	0.35268367		|	  0.15658067	 | 3.520546
 * ------------------------------------------------------------------------------------------------------
 * 1000 few unique		|  2.943178	 | 0.25969 |	0.07124767		|	  0.10611934	 | 0.424068
 * ------------------------------------------------------------------------------------------------------
 * 1000 nearly ordered	|  0.155077	 | 0.20362 |    0.0597603		|	  0.304957		 | 2.51035767
 * ------------------------------------------------------------------------------------------------------
 * 1000 reverse order	|  0.8138113 | 0.22209 |	0.0618123		|	  0.0842393		 | 0.31493967
 * ------------------------------------------------------------------------------------------------------
 * 1000 sorted			|  0.0042393 | 0.15248 |	0.0541543		|	  0.083829		 | 0.328615
 * 
 * 
 * Questions:
 * 	a. Which of the sorting algorithms does the order of input have an impact on? Why?
 * 	Answer: Insertion sort is most impacted by the order of input. For example with the random 1000 numbers it took approx. 
 * 			7.9 milliseconds, and when the array was sorted, only took 0.004 milliseconds. The drastic difference is because
 * 			of how insertion sort works, where the most work done is inserting the next element into a sorted position. as this may
 *          require shifting all elements in the sorted array, this may be O(N) work, and for N elements, this gives us O(N^2) for
 *          standard insertion sort. But if the array is already sorted, then each element will be 'inserted' into its own position,
 *          and there will be no need to shift any elements, reducing the runtime to O(N), hence the major speed gain.
 * 
 *	b. Which algorithm has the biggest difference between the best and worst performance, based
 *	   on the type of input, for the input of size 1000? Why?
 *  Answer: As outlined in answer A, insertion sort will have the biggest difference between worst and best performance as sorted
 *  		input gives us O(N) run-time, where as with random input we will have O(N^2) performance. By shuffling the input for
 *  		Quicksort we reduce the chance of the worst-case run time. This means insertion sort is the most affected by input type.
 *	   
 *	c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 *	   based on the input size? Please consider only input files with random order for this answer.
 *	Answer: For my experiments, insertion sort has the worst scalability, as it had the biggest range between the smallest and 
 *			biggest input sizes. Iterative merge-sort had the best scalability, as it Had the smallest range in times between 
 *			the smallest and biggest input.
 *	   
 *	d. Did you observe any difference between iterative and recursive implementations of merge
 *	   sort?
 *	Answer: To break down the different merge sort implementations, there is the dividing portion and the merging portion. Since
 *			Both implementations use the same 'merge' method there is no difference. The real difference is whether how we divide
 *			The input array. Recursion starts with the overall array and recursively splits the array into two, and then 'rebuilds'
 *			The array, in order. The iterative approach iterates over the array, starting at two elements and taking larger
 *		    portions of the arrays and sorting them as it goes, such that larger sorted portions of the array are merging into each
 *			other each iteration.
 *			
 *	   
 *	e. Which algorithm is the fastest for each of the 7 input files? 
 *	Answer:
 *		10Random: 				Selection Sort
 *		100Random: 				Merge sort Iterative
 *		1000Random: 			Merge sort Iterative
 *		1000 few unique: 		Merge sort recursive
 *		1000 nearly ordered: 	Merge sort recursive
 *		1000 reverse order: 	Merge sort recursive
 *		1000 sorted: 			Insertion sort
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
    	double[] current = values;
    	ArrayList<Long> times = new ArrayList<Long>();	
    	SortComparison s = new SortComparison();
    	long startTime;
    	long elapsedTime;
    	long dist;
    	
    	//run experiment for each sort.
        startTime = System.nanoTime();
    	s.insertionSort(current);
    	elapsedTime = System.nanoTime();
    	dist = elapsedTime - startTime;
    	times.add(dist);
    	
    	//reset input for fair evaluation
    	current = values;
        startTime = System.nanoTime();
    	s.quickSort(current);
    	elapsedTime = System.nanoTime();
    	dist = elapsedTime - startTime;
    	times.add(dist);
    	
    	current = values;
        startTime = System.nanoTime();
    	s.mergeSortRecursive(current);
    	elapsedTime = System.nanoTime();
    	dist = elapsedTime - startTime;
    	times.add(dist);
    	
    	current = values;
        startTime = System.nanoTime();
    	s.mergeSortIterative(current);
    	elapsedTime = System.nanoTime();
    	dist = elapsedTime - startTime;
    	times.add(dist);
    	
    	current = values;
        startTime = System.nanoTime();
    	s.selectionSort(current);
    	elapsedTime = System.nanoTime();
    	dist = elapsedTime - startTime;
    	times.add(dist);
    	
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
