import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Steve
 * a class to help out with probability and statistics experiments regarding the markov table
 */
public class ProbabilityLab {
	
	/**
	 * test percentages with different data types
	 */
	public static void percentageTest()
	{
		/*
		double probX,probY,probZ;
		probX=probY=probZ=0d;
		System.out.println("Probabilities: X: "+ probX +" Y: "+ probY +" Z: " + probZ);
		*/
		
		/*
		double probX,probY,probZ;
		probX = 5d;
		probY = 2d;
		probZ = 6d;
		
		double total = probX + probY + probZ;
		System.out.println("Probabilities: X: "+ probX/total +" Y: "+ probY/total +" Z: " + probZ/total);
		*/
		
		Junction junc = new Junction("X", 5d);
		junc.addEdge("Y", 3);
		junc.addEdge("Z", 7);
		
		System.out.println(junc.getProbability("X"));
		System.out.println(junc.getProbability("Y"));
		System.out.println(junc.getProbability("Z"));
		System.out.println(junc.getProbability("X") + junc.getProbability("Y") + junc.getProbability("Z"));
	}
}

/**
 * represent a state in which the probabilities of going to all other states adds up to 100
 * @author Steve
 *
 */
class Junction {
	public double total;
	public HashMap<String, Double> edges = new HashMap<String,Double>();
	
	public Junction(String firstEdge, Double firstProb)
	{
		edges.put(firstEdge, firstProb);
		this.total = firstProb;
	}

	/**
	 * push a new edge to the junction and even the output balance
	 * @param newEdge string representation of new edge to add
	 * @param probability probability of new edge (weight)
	 */
	public void addEdge(String newEdge, double probability)
	{
		total = total + probability;
		edges.put(newEdge, probability);
	}
	
	public double getProbability(String edge)
	{
		return Math.round((edges.get(edge)/total) * 100.0)/100.0;
	}
	
	public String pick()
	{
		Random r = new Random();
		int pick = r.nextInt(((int) total));
		
		return ""; //TODO: FIGURE OUT PICK (SORT HASHMAP?)
	}
	
	
}
