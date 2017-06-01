import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class MarkovProfile {
	
	ArrayList<MarkovJunction> junctions = new ArrayList<MarkovJunction>();
	
	public MarkovProfile()
	{
		
	}
	
	/**
	 * add a word to the profile
	 * @param word
	 */
	public void addWord(String word)
	{
		if (!junctions.contains(word))
		{
			junctions.add(new MarkovJunction(word));
		}
	}
	
	/**
	 * add a single state change from one node to another (increasing the probability of a random state change from that one node to the end node)
	 * @param wordStart
	 * @param wordEnd
	 */
	public void addStateChange(String wordStart, String wordEnd)
	{
		if (!junctions.contains(wordStart) || !junctions.contains(wordEnd)) {return;}
		
		MarkovJunction junctionTemp = getJunctionFromString(wordStart);
		junctionTemp.incrementProbability(wordEnd);
		
	}
	
	public boolean contains(String junctionToFind)
	{
		for (MarkovJunction element : junctions)
		{
			if (element.name.equals(junctionToFind)){return true;}
		}
		
		return false;
	}
	
	public MarkovJunction getJunctionFromString(String junctionToFind)
	{
		for (MarkovJunction element : junctions)
		{
			if (element.name.equals(junctionToFind)){return element;}
		}
		return null;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (MarkovJunction j : junctions)
		{
			sb.append(j.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}

/**
 * defines a "word", a node with many possible state changes or "edges" pointing to other nodes with different probabilities
 * @author Steve
 *
 */
class MarkovJunction {
	
	public String name; //what is the word represented by the junction
	public double total; //measure the total possible state changes from a junction (including duplicates, i.e. 5 state changes from junction A to junction B)
	public HashMap<String, Double> edges = new HashMap<String,Double>();
	
	public MarkovJunction(String name)
	{
		this.name = name;
		total = 0;
	}
	
	public MarkovJunction(String firstEdge, Double firstProb, String name)
	{
		edges.put(firstEdge, firstProb);
		this.total = firstProb;
		this.name = name;
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
	
	public void incrementProbability(String targetEdge)
	{
		if (edges.containsKey(targetEdge))
		{
			Double tempProbability = edges.get(targetEdge);
			edges.remove(targetEdge);
			edges.put(targetEdge, tempProbability++);
			total++;
		} else {
			edges.put(targetEdge, 1d);
			total++;
		}
	}
	
	public double getProbability(String edge)
	{
		return Math.round((edges.get(edge)/total) * 100.0)/100.0;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Junction " + name + ":\n");
		for (String edge : edges.keySet())
		{
			sb.append(edge + ": " + edges.get(edge) + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
}