// Nicholas Espinosa
// COP 3503 - 0001
// 4.21.2016
// Huffman Compression
// BinaryMinHeap Class

import java.util.ArrayList;

public class BinaryMinHeap<AnyType extends Comparable<AnyType>>
{
	// Class variables
	private ArrayList<AnyType> queue;
	private int size;
	
	// Class constructor
	public BinaryMinHeap(int maxSize)
	{
		queue = new ArrayList<AnyType>(maxSize);
		size = 0;
	}

	// Pops off the top element
	public AnyType pop()
	{
		// Get the minimum
		AnyType min = queue.get(0);
		
		// Swap the bottom and top, then reduce size
		swap(0, size - 1);
		size--;
		
		//Sift the new variable down
		siftDown(0);
		
		// Return the minimum value
		return min;
	}
	
	public void push(AnyType node)
	{
		// Inserts the new value at the end of the queue
		queue.add(size, node);
		
		// Increases size
		size++;
		
		// Sifts the value up the min-heap
		siftUp(size - 1);
	}
	
	// Swap any two values given their index
	private void swap(int a, int b)
	{
		AnyType temp = queue.get(a);
		queue.set(a, queue.get(b));
		queue.set(b, temp);
	}
	
	// Sift a value up
	private void siftUp(int current)
	{
		// If the value is at the top, then exit
		if(current == 0)
			return;
		
		// Find the parent
		int parent = ((current - 1)/ 2);
		
		
		// If the value is smaller than the parent
		if(queue.get(current).compareTo(queue.get(parent)) < 0)
		{
			// Swap places then continue sifting up
			swap(current, parent);
			siftUp(parent);
		}
	}
	
	// Sifting down
	private void siftDown(int current)
	{	
		// If you cannot sift down any further
		if((2 * current) > (size - 1)) return;
		
		// The lowest value is set to the current and left is determined
		int lowest = current;
		int left = (2 * current);

		// If the current value is greater than the left value
		// The lowest index is modified
		if(queue.get(lowest).compareTo(queue.get(left)) > 0)
			lowest = left; 
		
		// If the current value is greater than or equal to a valid right value
		// The lowest index is modified
		if(left + 1 < size && queue.get(lowest).compareTo(queue.get(left + 1)) > 0)
			lowest = left + 1; 
		
		// If the current value is not the lowest
		if(lowest != current)
		{
			// Swap and sift down
			swap(current, lowest);
			siftDown(lowest);
		}
	}
	
	// Returns the size of the queue
	public int getSize()
	{
		return size;
	}
	
	// Prints the values in the queue
	public void print()
	{
		for(int i = 0; i < size; i++)
			System.out.println(queue.get(i));
	}
}
