import java.util.*;

// A Dynamic Programming based solution for 0-1 Knapsack problem
class KnapsackProblem
{
 
	// Driver program for testing
	public static void main(String args[])
	{
		
		int[] value = new int[]{15, 10, 9, 5};
		int[] weight = new  int[]{1, 5, 3, 4};
		int maxWeight = 8;
		
		// input: a set of weight-value pairs: (w1, v1), ... , (wi, vi) , .... , (wn, vn)
		KnapsackProblem kp = new KnapsackProblem(value, weight, maxWeight);

		kp.find_OPT();
		
		kp.printOPT();
		System.out.println(kp.getOptimalWeight());

		System.out.println(kp.find_O_usualMethod(kp.get_n(), maxWeight));
		System.out.println(kp.find_O_alternativeMethod(kp.get_n(), maxWeight));
	}
	
	private int OPT[][];
	private Array_1D_1BasedIndexing v;	// input values
	private Array_1D_1BasedIndexing w;	// input weights
	private int n;						// number of inputs
	private int targetW;				// maximum weight of knapsack
	
	
	public KnapsackProblem(int[] value, int[] weight, int W)
	{
		v = new Array_1D_1BasedIndexing( value );
		w = new Array_1D_1BasedIndexing( weight );
		n = value.length;
		targetW = W;
		OPT = new int[value.length+1][targetW+1];
	}
	
	// Returns the maximum value that can be put in a knapsack of capacity W
	public void find_OPT()
	{
	 // Build table OPT[][] in bottom up manner
	 for (int i = 0; i <= n; i++)
	 {
		 for ( int iteration_w = 0; iteration_w <= targetW; iteration_w++)
		 {
			 if (i==0 || iteration_w==0)
				  OPT[i][iteration_w] = 0;
			 else if (w.get(i) > iteration_w)
				 OPT[i][iteration_w] = OPT[i-1][iteration_w];
			 else
				 OPT[i][iteration_w] = max(v.get(i) + OPT[i-1][iteration_w-w.get(i)],  OPT[i-1][iteration_w]);
		 }
	  }
	  
	}
	
	public HashSet<Integer> find_O_usualMethod(int i, int tempW)
	{
		// i means we are considering the first i pairs of the original input
		// input: a set of weight-value pairs: (w1, v1), ... , (wi, vi) , .... , (wn, vn)
		if (i==0)
			return new HashSet<Integer>();
		else if (w.get(i)>tempW)
			return find_O_usualMethod(i-1, tempW);
		else if (OPT[i-1][tempW] > (OPT[i-1][tempW-w.get(i)] + v.get(i)))
			return find_O_usualMethod(i-1, tempW);
		else
		{
			HashSet<Integer> set = find_O_usualMethod(i-1, tempW-w.get(i));
			set.add(i);
			return set;
		}
	}
	
	public HashSet<Integer> find_O_alternativeMethod(int i, int tempW)
	{
		// i means we are considering the first i pairs of the original input
		// input: a set of weight-value pairs: (w1, v1), ... , (wi, vi) , .... , (wn, vn)
		if (i==0)
			return new HashSet<Integer>();
		else if (OPT[i][tempW] == OPT[i-1][tempW])
			return find_O_alternativeMethod(i-1, tempW);
		else
		{
			HashSet<Integer> set = find_O_alternativeMethod(i-1, tempW-w.get(i));
			set.add(i);
			return set;
		}
	}
	
	public void printOPT()
	{
		for (int row = 0; row < OPT.length; row++)
			System.out.println( Arrays.toString( OPT[row] ) );
	}
	
	public int getOptimalWeight()
	{
		return OPT[v.length()][ targetW];
	}
	
	public int get_n()
	{
		return n;
	}
	
 
	// A utility function that returns maximum of two integers
	static int max(int a, int b) { return (a > b)? a : b; }
}
