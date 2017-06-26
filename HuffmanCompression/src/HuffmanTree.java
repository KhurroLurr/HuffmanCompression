// Nicholas Espinosa
// COP 3503 - 0001
// 4.21.2016
// Huffman Compression
// HuffmanTree Class

public class HuffmanTree implements Comparable<HuffmanTree>
{
	// Data for the node
	private byte data;
	private int frequency;
	
	// Pointers
	private HuffmanTree leftNode;
	private HuffmanTree rightNode;
	private HuffmanTree parent;
	
	public HuffmanTree(byte data, int frequency)
	{
		// Establish data and frequency
		this.data = data;
		this.frequency = frequency;
		
		// Nodes are set to null
		leftNode = null;
		rightNode = null;
		parent = null;
	}
	
	// Getters for frequency and data
	public byte getData()
	{
		return this.data;
	}
	public int getFrequency()
	{
		return this.frequency;
	}

	// Left node getters and setters
	public HuffmanTree getLeftNode()
	{
		return this.leftNode;
	}
	public void setLeftNode(HuffmanTree leftNode)
	{
		this.leftNode = leftNode;
	}
	
	// Right node getters and setters
	public HuffmanTree getRightNode()
	{
		return this.rightNode;
	}
	public void setRightNode(HuffmanTree rightNode)
	{
		this.rightNode = rightNode;
	}
	
	// Parent node getters and setters
	public HuffmanTree getParent()
	{
		return this.parent;
	}
	public void setParent(HuffmanTree parent)
	{
		this.parent = parent;
	}
	
	// Returns the string value of a HuffmanTree
	public String toString()
	{
		return (char)this.data + " " + this.frequency;
	}

	// Compare to function
	@Override
	public int compareTo(HuffmanTree arg0)
	{
		return (this.frequency - arg0.frequency);
	}
		
}
