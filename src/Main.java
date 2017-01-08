import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.mxgraph.model.mxGraphModel;

public class Main {

	public static void main(String[] args) {

		
		BinaryTreeNode dirNode1 = new BinaryTreeNode(9);
		BinaryTreeNode dirNode2 = new BinaryTreeNode(5);
		BinaryTreeNode dirNode3 = new BinaryTreeNode(18);
		BinaryTreeNode dirNode4 = new BinaryTreeNode(23);
		BinaryTreeNode dirNode5 = new BinaryTreeNode(15);
		
		List<BinaryTreeNode> nodes = new ArrayList<BinaryTreeNode>();
		nodes.add(dirNode1); nodes.add(dirNode2); nodes.add(dirNode3); nodes.add(dirNode4); nodes.add(dirNode5);
		
		BinarySearchTree bst = new BinarySearchTree(dirNode1);
		bst.addNode(dirNode2);
		bst.addNode(dirNode3);
		bst.addNode(dirNode4);
		bst.addNode(dirNode5);
		System.out.println(bst.simpleToString());
		System.out.println("Children: " + bst.getNumChildren());
		System.out.println("Level: " + bst.getLevel());
		/*
		

		System.out.println(bst.getNumChildren());
		System.out.println((bst.getNumChildren(bst.getRoot().right)));
		*/

	}

}

/**
 * binary search tree class utilizing node objects
 * @author Steve
 *
 */
class BinarySearchTree
{
	private BinaryTreeNode root;
	private int totalNodes;

	/**
	 * default constructor for BST object
	 */
	public BinarySearchTree()
	{
		root = new BinaryTreeNode(0);
		totalNodes = 1;
	}
	
	/**
	 * default constructor for BST object initializing root with data
	 */
	public BinarySearchTree(BinaryTreeNode rootNode)
	{
		root = rootNode;
		totalNodes = 1;
	}
	
	/**
	 * helper method with no params to call getLevel with root node
	 * @return the level of the current tree
	 */
	public int getLevel()
	{
		return getLevel(root);
	}
	
	/**
	 * get the level of the tree
	 * @return the level - how deep is the tree (node = level 1)
	 */
	public int getLevel(BinaryTreeNode target)
	{
		
		//root node is index 1
		int leftLevel = 1;
		int rightLevel = 1;
		
		if (target == null)
			{
				return 0;
			}
		if (target.left != null)
			{
				leftLevel = 1 + getLevel(target.left);
			}
		if (target.right != null)
			{
				rightLevel = 1 + getLevel(target.right);
			}
		
		return Math.max(leftLevel, rightLevel);
	}
	
	/**
	 * @return the root node
	 */
	public BinaryTreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	
	/**
	 * get the node's right child
	 * @param target the target node to access 
	 * @return accessed node
	 */
	public BinaryTreeNode getRightNode(BinaryTreeNode target)
	{
		try
			{
				return target.right;
			} catch (Exception e)
			{
				//right node does not exist
				return null;
			}
		
	}
	
	/**
	 * set the right child of a node
	 * @param nodeToAdd node to add
	 * @param head node to add to
	 */
	public void setRightNode(BinaryTreeNode head, BinaryTreeNode nodeToAdd)
	{
		head.right = nodeToAdd;
	}
	
	/**
	 * set the left child of a head
	 * @param nodeToAdd node to add
	 * @param head node to add to
	 */
	public void setLeftNode(BinaryTreeNode head, BinaryTreeNode nodeToAdd)
	{
		head.left = nodeToAdd;
	}
	
	/**
	 * get the node's left child
	 * @param target the target node to access 
	 * @return accessed node
	 */
	public BinaryTreeNode getLeftNode(BinaryTreeNode target)
	{
		try
			{
				return target.left;
			} catch (Exception e)
			{
				//TODO: create special 'null node' / node does not exist exception?
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
	public int getNumChildren(BinaryTreeNode target)
	{
		int leftNodes = 0;
		int rightNodes = 0;
		
		if (target.left != null)
			{
				leftNodes = getNumChildren(target.left) + 1;
			}
		if (target.right != null)
			{
				rightNodes = getNumChildren(target.right) + 1;
			}
		
		return leftNodes + rightNodes;
		
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
	 * simple toString method with only data for easier viewing
	 * @return
	 */
	public String simpleToString()
	{
		return "BinarySearchTree " + root.simpleToString();
	}
	
	//TODO: write contains/search method - BFS vs DFS?

	
	/**
	 * helper method to call deleteNode with root as param
	 * @param nodeToRemove the node to remove 
	 */
	public void deleteNode(BinaryTreeNode nodeToRemove)
	{
		deleteNode(root, nodeToRemove);
	}
	
	/**
	 * full method to find and remove a node
	 * @param head the root of the tree to search, aka where the function is currently looking
	 * @param nodeToRemove target node to remove
	 */
	public void deleteNode(BinaryTreeNode head, BinaryTreeNode nodeToRemove)
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
	public void addNode(BinaryTreeNode nodeToAdd)
	{
		addNode(root, nodeToAdd);
	}
	
	/**
	 * add a node to the binary search tree object
	 * @param head node to add to - should be ROOT node if calling this method
	 * @param nodeToAdd the node to add
	 */
	public void addNode(BinaryTreeNode head, BinaryTreeNode nodeToAdd)
	{
		if (head.compare(nodeToAdd) == -1)
		{
			if (head.right == null)
				{
					totalNodes++;
					head.right = nodeToAdd;
					return;
				} else {
					addNode(head.right, nodeToAdd);
				}
		} else if (head.compare(nodeToAdd) == 1)
		{
			if (head.left == null)
				{
					totalNodes++;
					head.left = nodeToAdd;
					return;
				} else {
					addNode(head.left, nodeToAdd);
				}
		} else {
			//if compare == 0, node is already in tree. silently ignore or print to console
			System.out.println("Node with data " + nodeToAdd.getData() + " already in tree. skipping");
			
		}
	}

}

/**
 * treenode class for use in a binary tree
 * @author Steve
 *
 */
class BinaryTreeNode extends Node
{
	BinaryTreeNode left;
	BinaryTreeNode right;
	
	/**
	 * default constructor for treenode
	 */
	public BinaryTreeNode()
	{
		super();
		
	}
	
	/**
	 * constructor with data param
	 */
	public BinaryTreeNode(Object data)
	{
		super(data);
	}
	
	/*
	 * toString method for easier visualization
	 */
	public String simpleToString()
	{
		System.out.println("Simple toString called with data " + data);
		Object leftNodeInfo;
		Object rightNodeInfo;
		
		if (this.left == null) 
			{
				leftNodeInfo = "null";
			} else {
				leftNodeInfo = left.simpleToString();
			}
		if (this.right == null) 
			{
				rightNodeInfo = "null";
			} else {
				rightNodeInfo = right.simpleToString();
			}
		return "[" + data + ",left=" + leftNodeInfo + ",right=" + rightNodeInfo + "]";
	}
	
	@Override
	public String toString()
	{
		String leftNodeInfo;
		String rightNodeInfo;
		if (this.left == null) 
			{
				leftNodeInfo = "null";
			} else {
				leftNodeInfo = left.toString();
			}
		if (this.right == null) 
			{
				rightNodeInfo = "null";
			} else {
				rightNodeInfo = right.toString();
			}
		return "TreeNode: [data=" + data.toString() + ",left=" + leftNodeInfo + ",right=" + rightNodeInfo + "]";

		
	}
	
}

/**
 * node class for use in a directed graph
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

	}
	
	/**
	 * constructor to initialize data
	 * @param data
	 */
	public DirectedNode(Object data)
	{
		super(data);
		connectedTo = new ArrayList<DirectedNode>();
		

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
	

	Object data;
	
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
	 * @return the data - also can use node.data 
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


