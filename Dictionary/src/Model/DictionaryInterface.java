package Model;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 * @author Bolba Raluca
 * Interface that defines the methods and the behavior of a dictionary
 *
 */

public interface DictionaryInterface 
{
	/**
	 * Adds a word and its synonym to the dictionary
	 * @param 	word		-	the word to be added
	 * @param 	synonym		-	the synonym to be added
	 * @return 	true		- 	if the word and its synonym could be added to dictionary, false otherwise
	 * @throws 	Exception	-	if the dictionary already contains the word and its synonym 
	 * @pre		word != null && synonym != null && word.length() != 0 && synonym.length() != 0 && !isContained(word, synonym)
	 * @post	isContained(word, synonym)
	 */
	public boolean addWord(String word, String synonym) throws Exception;
	
	
	/**
	 * Removes a word and its synonym from the dictionary
	 * @param	word		-	the word to be removed
	 * @return 	true		-	if the word and its synonyms could be removed
	 * @throws 	Exception	if the dictionary does not contain the word
	 * @pre		word != null && dictionary.containsKey(word) && dictionary != null
	 * @post	! dictionary.containsKey(word)
	 */
	public boolean removeWord(String word) throws Exception;
	
	
	/**
	 * Returns a set of words that matches the parameter
	 * @param 	word	-	the word searched
	 * @return			-	a set of words from dictionary that are matching word
	 * @pre		word != null && dictionary != null
	 * @post	@nochange
	 */
	public Set<String> search(String word) throws IllegalArgumentException;
	
	
	/**
	 * Returns a list of words representing the synonyms of the parameter
	 * @param 	word	-	the word whose synonyms we what to search
	 * @return			-	the list of synonyms of word from the dictionary
	 * @pre		word != null && dictionary.containsKey(word)
	 * @post	@nochange
	 */
	public List<String> getSynonyms(String word);
	
	
	/**
	 * Reads from a text file a group of words and synonyms and populates the dictionary with them
	 * @param 	filePath	-	the path of the text file
	 * @pre		filePath.contains(".txt")
	 * @post	dictionary != null
	 */
	public void populate(String filePath) throws FileNotFoundException, Exception;
	
	
	/**
	 * Writes in a text file the dictionary
	 * @param	filePath	-	the path of the text file to be written
	 * @pre		dictionary != null
	 * @post	@nochange
	 */
	public void save(String filePath);
	
	
	/**
	 * Checks if the dictionary is consistent. A dictionary is consistent if all 
	 * words that are used for defining a certain word are also defined by the dictionary
	 * @return	true is the dictionary is consistent, false otherwise
	 * @pre		true
	 * @post	@nochange
	 */
	public boolean isConsistent();
	
	
	/**
	 * Checks if a pair of word and synonym exists in dictionary
	 * @param	word
	 * @param 	synonym
	 * @return	true if the pair is contains, false otherwise
	 * @pre		dictionary != null
	 * @post	@nochange
	 */
	public boolean isContained(String word, String synonym);
}
