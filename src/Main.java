import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		DirectedNode dirNode1 = new DirectedNode(9);
		DirectedNode dirNode2 = new DirectedNode(5);
		DirectedNode dirNode3 = new DirectedNode(15);
		DirectedNode dirNode4 = new DirectedNode(23);
		DirectedNode dirNode5 = new DirectedNode(15);
		
		BinarySearchTree bst = new BinarySearchTree(dirNode1);
		bst.addNode(dirNode2);
		bst.addNode(dirNode5);
		bst.addNode(dirNode4);
		bst.addNode(dirNode3);
		System.out.println(bst.toString());
		System.out.println(bst.getNumChildren());
		System.out.println((bst.getNumChildren(bst.getRightNode(bst.getRoot()))));

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
	 * set the right node of a head
	 * @param nodeToAdd node to add
	 * @param head node to add to
	 */
	public void setRightNode(DirectedNode head, DirectedNode nodeToAdd)
	{
		head.connect(nodeToAdd, 1);
	}
	
	/**
	 * set the left node of a head
	 * @param nodeToAdd node to add
	 * @param head node to add to
	 */
	public void setLeftNode(DirectedNode head, DirectedNode nodeToAdd)
	{
		head.connect(nodeToAdd, 0);
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
	
	/**
	 * helper method to call getNumChildren with root of tree
	 * @return
	 */
	public int getNumChildren()
	{
		return getNumChildren(this.getRoot());
	}
	
	/**
	 * returns the number of children of a target node
	 * @param target target node
	 * @return number of children (left, right)
	 */
	public int getNumChildren(DirectedNode target)
	{
		if (getRightNode(target) == null && getLeftNode(target) == null)
		{
			return 0;
		} else if (getRightNode(target) == null) {
			return 1 + getNumChildren(getLeftNode(target));
		} else if (getLeftNode(target) == null) {
			return 1 + getNumChildren(getRightNode(target));
		} else {
			//in this case, node has two children
			return 2 + getNumChildren(getLeftNode(target)) + getNumChildren(getRightNode(target));
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
	
	//TODO: write contains/search method - BFS vs DFS
	
	/**
	 * helper method to call deleteNode with root as param
	 * @param nodeToRemove the node to remove 
	 */
	public void deleteNode(DirectedNode nodeToRemove)
	{
		deleteNode(root, nodeToRemove);
	}
	
	/**
	 * full method to find and remove a node
	 * @param head the root of the tree to search, aka where the function is currently looking
	 * @param nodeToRemove target node to remove
	 */
	public void deleteNode(DirectedNode head, DirectedNode nodeToRemove)
	{
				
		//check if the node to remove is the head
		if (head.compare(nodeToRemove) == 0)
		{
			//TODO: logic for node deletion here
		} else {
			//TODO: continue searching for node, or alert user if node has no children and is not the target
		}
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
	 * @param head node to add to - should be ROOT node if calling this method
	 * @param nodeToAdd the node to add
	 */
	public void addNode(DirectedNode head, DirectedNode nodeToAdd)
	{
		if (head.compare(nodeToAdd) == -1)
		{
			if (getRightNode(head) == null)
				{
					totalNodes++;
					setRightNode(head, nodeToAdd);
				} else {
					addNode(getRightNode(head), nodeToAdd);
				}
		} else if (head.compare(nodeToAdd) == 1)
		{
			if (getLeftNode(head) == null)
				{
					totalNodes++;
					setLeftNode(head, nodeToAdd);
				} else {
					addNode(getLeftNode(head), nodeToAdd);
				}
		} else {
			//if compare == 0, node is already in tree. silently ignore or print to console
			System.out.println("Node with data " + nodeToAdd.getData() + " already in tree. skipping");
			
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


