
public class Array_1D_1BasedIndexing {
	
	 private int[] a;
	
	public Array_1D_1BasedIndexing(int size)
	{
		a = new int[size];
	}
	
	public Array_1D_1BasedIndexing(int[] ary)
	{
		a = ary;
	}
	
	public void set(int index, int value)
	{
		a[index-1] = value;
	}
	
	public int get(int index)
	{
		return a[index-1];
	}
	
	public int length()
	{
		return a.length;
	}
}
