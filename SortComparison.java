import java.util.Arrays;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2019
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){	
    	for(int i =1; i < a.length; i++) {
    		double next = a[i];
    		int k = i-1;
    		while(k >= 0 && next < a[k]){
    			double temp = a[k];
    			a[k] = next;
    			a[k+1] = temp;
    			k--;
    		}
    	}
    	return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
	
		 //todo: implement the sort

    }//end quicksort

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {
    	int N = a.length;
    	double[] aux = new double[N];
    	for(int sz = 1; sz < N; sz = sz+sz) {
    		for(int lo = 0; lo < N-sz; lo += sz+sz){
    			merge(a,aux,lo,lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    		}
    	}
    	return a;
    }//end mergesortIterative
    
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
    	double aux[] = new double[a.length];
    	mergeSortRecursive(a, aux, 0, a.length-1);
    	return a;
	
   }//end mergeSortRecursive
    
    private static void mergeSortRecursive (double [] a, double [] aux, int lo, int hi){
    	if(hi <= lo) {
    		return;
    	}
    	int mid = lo + (hi-lo) / 2;
    	mergeSortRecursive(a, aux, lo, mid);
    	mergeSortRecursive(a, aux, mid+1, hi);
    	merge(a, aux, lo, mid, hi);
    	
    }
    
    private static void merge (double[] a, double[] aux, int lo, int mid, int hi){
    	int i = lo; int j = mid+1;
    	for(int k = lo; k <= hi; k++) {
    		aux[k] = a[k];
    	}
    	for(int k =lo; k <= hi;k++){
    		if(i > mid)					a[k] = aux[j++];
    		else if(j > hi) 			a[k] = aux[i++];
    		else if (aux[j] < aux[i])	a[k] = aux[j++];
    		else						a[k] = aux[i++];
    	}
    }	
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
    	double min = a[0];
    	int smallestPos = 0;
    	
    	for(int i =0; i  < a.length; i++) {
    		for(int k =i; k < a.length; k++) {
    			if(a[k] < min) {
    				min = a[k];		
    				smallestPos = k;
    			}	
    		}
			double temp = a[i];
			a[i] = min;
			a[smallestPos] = temp;
			min = Double.MAX_VALUE;
    	}
    	return a;
    }

   


    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

 }//end class
