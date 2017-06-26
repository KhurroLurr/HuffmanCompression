// Nicholas Espinosa
// COP 3503 - 0001
// 4.21.2016
// Huffman Compression
// Huffman Class

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman
{
	// Data from the file
	private static byte[] fileData;
	private static int[] freq;
	
	// BinaryMinHeap for HuffmanTrees
	private static BinaryMinHeap<HuffmanTree> queue;
	
	// String array containing all of the compressed data
	private static String[] compressionTable;
	
	// Calculates the frequency of each byte
	public static void calculateFreqs()
	{
		// Frequency table is created
		freq = new int[256];
		
		// Frequencies are established
		for(byte b : fileData)
			freq[b]++;
	}
	
	//Obtains file data
	public static void getFileData()
	{
		try
		{
			// Opening the file
			Scanner input = new Scanner(new File("src/input.dat"));
			
			String data = "";
			
			// Obtaining input from the file
			while(input.hasNextLine())
				data += input.nextLine();
				
			// Closing the file
			input.close();
			
			fileData = new byte[data.length()];
			
			// Transferring contents to bytes
			for(int i = 0; i < data.length(); i++)
				fileData[i] = (byte)data.charAt(i);
			
			calculateFreqs();
		} 
		catch (FileNotFoundException e)
		{
			// File not Found
			e.printStackTrace();
		}
	}
	
	// Creates the queue
	public static void createQueue()
	{
		queue = new BinaryMinHeap<HuffmanTree>(fileData.length);
	
		// Travel through the frequency array
		for(int i = 0; i < freq.length; i++)
		{
			// If valid, the byte and frequency are added to the queue
			if(freq[i] > 0)
				queue.push(new HuffmanTree((byte)i, freq[i]));
		}
	}
	
	// Creates a new Huffman Tree
	public static HuffmanTree makeTree(HuffmanTree node1, int freq1,HuffmanTree node2, int freq2)
	{
		// Creating the new tree
		HuffmanTree newTree = new HuffmanTree((byte)-1, freq1 + freq2);
		
		// Establishing relationships
		newTree.setLeftNode(node1);
		newTree.setRightNode(node2);
		node1.setParent(newTree);
		node2.setParent(newTree);
		
		// The new tree is returned
		return newTree;
	}
	
	// Creates the HuffmanTree of all the bytes
	public static void createHuffmanTree()
	{
		// While there is more than one object in the queue
		while(queue.getSize() > 1)
		{
			// The first two are popped
			HuffmanTree n1 = queue.pop();
			HuffmanTree n2 = queue.pop();
			
			// A new tree is created then pushed back into the stack
			HuffmanTree nt = makeTree(n1, n1.getFrequency(), n2, n2.getFrequency());
			queue.push(nt);
		}
	}
	
	// Fills in the compression table
	public static void fillInTable(HuffmanTree tree, String comp)
	{
		// If a proper node is found
		if(tree.getData() != -1)
		{
			// Enter the compressed value into the table and exit
			compressionTable[tree.getData()] = comp;
			return;
		}
			
		// Otherwise, continue on and adjust string based on direction
		fillInTable(tree.getLeftNode(), comp + "0");
		fillInTable(tree.getRightNode(), comp + "1");
	}
	
	// Creates the compression table
	public static void createTable()
	{
		// Table is created and root is placed inside a value
		compressionTable = new String[256];
		HuffmanTree lt = queue.pop();
		
		// The table is then filled in
		fillInTable(lt, "");
	}
	
	// Prints out the table
	public static void printTable()
	{
		// The total value is initialized
		int total = 0;
		
		// For each value in the table
		for(int i = 0; i < 256; i++)
		{
			// If a valid entry is found
			if(compressionTable[i] != null)
			{
				// Print out the byte and the string that represents it
				// Then add to the total
				System.out.println((char)i + " " + compressionTable[i]);
				total = total + (compressionTable[i].length() * freq[i]);
			}
		}
		
		// Prints out the total size of the compressed file
		System.out.println("Total size: " + total);
	}
	
	public static void main(String[] args)
	{
		// Call functions to perform operations
		getFileData();
		createQueue();
		createHuffmanTree();
		createTable();
		printTable();
	}

}
