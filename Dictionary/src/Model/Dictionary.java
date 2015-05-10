package Model;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * 
 * @author Raluca Bolba
 * Class that represents a synonym dictionary. A dictionary is characterized by a 
 * set of words and synonyms.
 */
public class Dictionary implements DictionaryInterface
{
	private Map<String, List<String>> dictionary;
	
	public Dictionary()
	{
		dictionary = new TreeMap<String, List<String>>();
	}
	
	/**
	 * Adds a word and its synonym to the dictionary
	 * @param 	word		-	the word to be added
	 * @param 	synonym		-	the synonym to be added
	 * @return 	true		- 	if the word and its synonym could be added to dictionary, false otherwise
	 * @throws 	Exception	-	if the dictionary already contains the word and its synonym 
	 * @pre		word != null && synonym != null && word.length() != 0 && synonym.length() != 0 && !isContained(word, synonym)
	 * @post	isContained(word, synonym)
	 */
	@Override
	public boolean addWord(String word, String synonym) throws Exception
	{
		assert word != null && synonym != null && word.length() != 0 && synonym.length() != 0 && !isContained(word, synonym);
		
		if( !word.matches("^[a-zA-Z]+( +[a-zA-Z]+)*$") )
		{
			throw new Exception("The word is invalid.");
		}
		
		if( !synonym.matches("^[a-zA-Z]+( +[a-zA-Z]+)*$") )
		{
			throw new Exception("The synonym is invalid.");
		}
		
		if( isContained(word, synonym) )
		{
			throw new Exception("The dictionary contains the word and the synonym.");
		}
		
		List<String> set = dictionary.get(word);
		if(set != null)
		{
			set.add(synonym);
			
			/*String i = set.get(0);
			while( i != null )
			{
				List<String> list = dictionary.get(i);
				if(list != null)
				{
					list.add(synonym);
				}
				else
				{
					list = new ArrayList<String>();
					list.add(synonym);
					dictionary.put(i, list);
				}
				i = set.get(set.indexOf(i) + 1);
			}*/
		}
		else
		{
			set =  new ArrayList<String>();
			set.add(synonym);
		}
		
		dictionary.put(word, set);
		
		//put also in dictionary the pair (synonym, word)
		
		List<String> set2 = dictionary.get(synonym);
		if(set2 != null)
		{
			set2.add(word);
		}
		else
		{
			set2 =  new ArrayList<String>();
			set2.add(word);
			set2.addAll(set);
			set2.remove(synonym);
		}
		
		dictionary.put(synonym, set2);
		
		assert isContained(word, synonym);
		
		return true;
	}
	
	/**
	 * Removes a word and its synonym from the dictionary
	 * @param	word		-	the word to be removed
	 * @return 	true		-	if the word and its synonyms could be removed
	 * @throws 	Exception	if the dictionary does not contain the word
	 * @pre		word != null && dictionary.containsKey(word) && dictionary != null
	 * @post	! dictionary.containsKey(word)
	 */
	@Override
	public boolean removeWord(String word) throws Exception
	{
		assert word != null && dictionary.containsKey(word) && dictionary != null;
		
		if(! dictionary.containsKey(word))
		{
			throw new Exception( word + " does not exist in dictionary.");
		}
		
		dictionary.remove(word);
		
		assert ! dictionary.containsKey(word);
		
		return true;
	}

	/**
	 * Returns a set of words that matches the parameter
	 * @param 	word	-	the word searched
	 * @return			-	a set of words from dictionary that are matching word
	 * @pre		word != null && dictionary != null
	 * @post	@nochange
	 */
	@Override
	public Set<String> search(String word) throws IllegalArgumentException
	{
		assert word != null && dictionary != null;
		
		Set<String> set = new TreeSet<String>();
		
		if(dictionary.containsKey(word))
		{
			set.add(word);
		}
		else
		{
			Set<String> keys = dictionary.keySet();
			Iterator<String> i = keys.iterator();
			while(i.hasNext())
			{
				String pattern = i.next();
				if(Pattern.matches(word, pattern))
				{
					set.add(pattern);
				}
			}
		}
		
		return set;
	}
	
	
	/**
	 * Returns a list of words representing the synonyms of the parameter
	 * @param 	word	-	the word whose synonyms we what to search
	 * @return			-	the list of synonyms of word from the dictionary
	 * @pre		word != null && dictionary.containsKey(word)
	 * @post	@nochange
	 */
	@Override
	public List<String> getSynonyms(String word)
	{
		assert word != null && dictionary.containsKey(word);
		
		return dictionary.get(word);
	}
	
	
	/**
	 * Reads from a text file a group of words and synonyms and populates the dictionary with them
	 * @param 	filePath	-	the path of the text file
	 * @pre		filePath.contains(".txt")
	 * @post	dictionary != null
	 */
	@SuppressWarnings("resource")
	@Override
	public void populate(String filePath) throws FileNotFoundException, Exception
	{
		assert filePath.contains(".txt");
		
		String word = null;
		String synonym = null;
		
		if(! filePath.contains(".txt"))
		{
			throw new Exception("You must choose a text file.");
		}
		
		BufferedReader buf = new BufferedReader(new FileReader(filePath));
		String line;
		while( (line = buf.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(line, " ,=");
			if(st.hasMoreTokens())
			{
				word = st.nextToken();
			}
			else
			{
				throw new Exception("The format of the file is not correct.");
			}
			while(st.hasMoreTokens())
			{
				synonym = st.nextToken();
				this.addWord(word, synonym);
			}
		}
		
		buf.close();
		
		assert dictionary != null;
			 
		
	}

	
	/**
	 * Writes in a text file the dictionary
	 * @param	filePath	-	the path of the text file to be written
	 * @pre		dictionary != null
	 * @post	@nochange
	 */
	@Override
	public void save(String filePath) 
	{
		assert dictionary != null;
		
		try
		{
			if( !filePath.contains(".txt"))
			{
				filePath = filePath + ".txt";
			}
			
			BufferedWriter buf = new BufferedWriter(new FileWriter(filePath));
			
			for(Map.Entry<String, List<String>> list : dictionary.entrySet())
			{
				buf.append(list.getKey() + " = " + list.getValue().toString());
				buf.newLine();
			}
			
			JOptionPane.showMessageDialog(null, "Dictionary saved.");
			buf.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Couldn't write the file. " + e.getMessage());
		}
	}
	
	/**
	 * Checks if the dictionary is consistent. A dictionary is consistent if all 
	 * words that are used for defining a certain word are also defined by the dictionary
	 * @return	true is the dictionary is consistent, false otherwise
	 * @pre		true
	 * @post	@nochange
	 */
	@Override
	public boolean isConsistent() 
	{
		if(this.dictionary == null)
		{
			return false;
		}
		for(Map.Entry<String, List<String>> e : dictionary.entrySet())
		{
			for(String l : e.getValue())
			{
				if(! dictionary.containsKey(l))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if a pair of word and synonym exists in dictionary
	 * @param	word
	 * @param 	synonym
	 * @return	true if the pair is contains, false otherwise
	 * @pre		dictionary != null
	 * @post	@nochange
	 */
	public boolean isContained(String word, String synonym)
	{
		List<String> list = dictionary.get(word);
		if(list == null)
		{
			return false;
		}
		return list.contains(synonym);
	}
	
	/**
	 * Returns the set of keys from the dictionary
	 * @return	dictionary
	 * @pre		true
	 * @post	@nochange
	 */
	public Set<String> getKeys()
	{
		return this.dictionary.keySet();
	}
	
	/**
	 * Clears the dictionary.
	 * @pre		true
	 * @post	dictionary.isEmpty()
	 */
	/*public void clear()
	{
		this.dictionary.clear();
		
		assert dictionary.isEmpty();
	}*/
	
	/**
	 * Returns and generates a string representation of the dictionary
	 * @pre		dictionary != null
	 * @post	@nochange
	 */
	/*public String toString()
	{
		assert dictionary != null;
		
		String s = "";
	
		for(Map.Entry<String, List<String>> list : dictionary.entrySet())
		{
			s += list.getKey() + " = " + list.getValue().toString() + "\n";
		}
		
		return s;
	}*/
}
