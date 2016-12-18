import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		DirectedNode dirNode1 = new DirectedNode(9);
		DirectedNode dirNode2 = new DirectedNode(5);
		DirectedNode dirNode3 = new DirectedNode(3);
		DirectedNode dirNode4 = new DirectedNode(6);
		DirectedNode dirNode5 = new DirectedNode(1);
		
		BinarySearchTree bst = new BinarySearchTree(dirNode1);
		bst.addNode(dirNode2);
		System.out.println(bst.toString());
		

	}

}

/**
 * binary search tree class utilizing node objects
 * @author Steve
 *
 */
class BinarySearchTree
{
	private DirectedNode root;
	private int level;
	private int totalNodes;

	/**
	 * default constructor for BST object
	 */
	public BinarySearchTree()
	{
		root = new DirectedNode();
		level = 1;
		totalNodes = 1;
	}
	
	/**
	 * default constructor for BST object initializing root with data
	 */
	public BinarySearchTree(DirectedNode rootNode)
	{
		root = rootNode;
		level = 1;
		totalNodes = 1;
	}
	
	/**
	 * @return the root node
	 */
	public DirectedNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(DirectedNode root) {
		this.root = root;
	}
	
	/**
	 * get the node on the right, aka the one in the second index
	 * @param target the target node to access 
	 * @return accessed node
	 */
	public DirectedNode getRightNode(DirectedNode target)
	{
		try
			{
				return target.getConnectedTo().get(1);
			} catch (Exception e)
			{
				//right node does not exist
				return null;
			}
		
	}
	
	/**
	 * set the right node of a base
	 * @param nodeToAdd node to add
	 * @param base node to add to
	 */
	public void setRightNode(DirectedNode base, DirectedNode nodeToAdd)
	{
		base.connect(nodeToAdd, 1);
	}
	
	/**
	 * set the left node of a base
	 * @param nodeToAdd node to add
	 * @param base node to add to
	 */
	public void setLeftNode(DirectedNode base, DirectedNode nodeToAdd)
	{
		base.connect(nodeToAdd, 0);
	}
	
	/**
	 * get the node on the right, aka the one in the second index
	 * @param target the target node to access 
	 * @return accessed node
	 */
	public DirectedNode getLeftNode(DirectedNode target)
	{
		try
			{
				return target.getConnectedTo().get(0);
			} catch (Exception e)
			{
				//left node does not exist
				return null;
			}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * TODO: add level, completeness, node count, etc
	 */
	@Override
	public String toString() {
		return "BinarySearchTree [root=" + root + "totalNodes=" + totalNodes + "]";
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * helper method to call the full addNode method with root as param
	 * @param nodeToAdd
	 */
	public void addNode(DirectedNode nodeToAdd)
	{
		addNode(root, nodeToAdd);
	}
	
	/**
	 * add a node to the binary search tree object
	 * @param base node to add to - should be ROOT node if calling this method
	 * @param nodeToAdd the node to add
	 */
	public void addNode(DirectedNode base, DirectedNode nodeToAdd)
	{
		if (base.compare(nodeToAdd) == -1)
		{
			if (getRightNode(base) == null)
				{
					totalNodes++;
					setRightNode(base, nodeToAdd);
				} else {
					addNode(getRightNode(base), nodeToAdd);
				}
		} else if (base.compare(nodeToAdd) == 1)
		{
			if (getLeftNode(base) == null)
				{
					totalNodes++;
					setLeftNode(base, nodeToAdd);
				} else {
					addNode(getLeftNode(base), nodeToAdd);
				}
		} else {
			//if compare == 0, node is already in tree. silently ignore or print to console
			System.out.println("Node " + nodeToAdd + " already in tree. skipping");
			
		}
	}

}



/**
 * node class for use in a directed graph or tree
 * @author Steve
 *
 */
class DirectedNode extends Node 
{
	private ArrayList<DirectedNode> connectedTo;

	/**
	 * default constructor
	 */
	public DirectedNode()
	{
		super();
		connectedTo = new ArrayList<DirectedNode>();
		
		//ensure node has two allocations for right and left connectors
		connectedTo.add(null);
		connectedTo.add(null);
	}
	
	/**
	 * constructor to initialize data
	 * @param data
	 */
	public DirectedNode(Object data)
	{
		super(data);
		connectedTo = new ArrayList<DirectedNode>();
		
		//ensure node has two allocations for right and left connectors
		connectedTo.add(null);
		connectedTo.add(null);
	}
	
	/**
	 * constructor to initialize data and any nodes that this one connects to
	 * @param data
	 * @param connectedTo array of other nodes that this one is connected to
	 */
	public DirectedNode(Object data, ArrayList<DirectedNode> connectedTo)
	{
		super(data);
		this.connectedTo = connectedTo;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [KEY=" + KEY + ", data=" + super.getData() + ", connectedTo={\n"+ connectedTo +"\n}]";
	}

	/**
	 * @return the connectedTo
	 */
	public List<DirectedNode> getConnectedTo() {
		return connectedTo;
	}
	
	/**
	 * connects to node with the key of the target node
	 * @param targetNode the node to connect to 
	 * @return if node was successfully connected
	 */
	public boolean connect(DirectedNode targetNode)
	{
		return connectedTo.add(targetNode);
		
	}
	
	/**
	 * connect method with index specification
	 * @param targetNode node to connect to
	 * @param position index to insert node at
	 */
	public void connect(DirectedNode targetNode, int position)
	{
		connectedTo.set(position, targetNode);

	}

}

/**
 * node class
 * @author Steve
 *
 */
class Node
{
	private static int nextKey = 0;
	public final int KEY;
	

	private Object data;
	
	/**
	 * default constructor, initialize key value and increment nextKey
	 */
	public Node()
	{
		KEY = nextKey;
		nextKey++;
	}

	/**
	 * constructor to init key value, increment next key, set data
	 * @param data data to set
	 */
	public Node(Object data)
	{
		KEY = nextKey;
		nextKey++;
		this.data = data;
	}
	
		/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [KEY=" + KEY + ", data=" + data + "]";
	}
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return this.data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * compare method for supported data types, used to compare node data
	 * @param target the node to compare with
	 * @return see compareTo doc
	 */
	public int compare(Node target) {
		if (this.getData() instanceof Integer && target.getData() instanceof Integer)
		{
			return ((Integer) this.getData()).compareTo((Integer) target.getData());
		} else if (this.getData() instanceof String && target.getData() instanceof String)
		{
			return ((String) this.getData()).compareTo((String) target.getData());
		} else {
			System.out.println("unsupported node compare operation attempted with " + target.getClass());
			
			return 0;
		}
		
	}
	
	
	
}


