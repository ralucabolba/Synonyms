package Model;

/**
 * 
 * @author Raluca Bolba
 *
 */
public class Pattern 
{
	/**
	 * Checks if two words are matching. The first word can contain characters like '*' which can represents 
	 * any string, including null, or '?' which represents a single letter.
	 * @param 	word
	 * @param 	pattern
	 * @return	true if the word and the pattern are matching, false otherwise
	 * @throws 	IllegalArgumentException if the word contains two '*' characters consecutively
	 */
	public static boolean matches(String word, String pattern) throws IllegalArgumentException
	{
		int i;
		
		
		if(word == null || word.length() == 0)
		{
			return false;
		}
		
		if((pattern == null || pattern.length() == 0) && word.charAt(0) != '*' )
		{
			return false;
		}
		
		for(i=1 ; i<word.length() ; i++)
		{
			if(word.charAt(i) == '*' && word.charAt(i) == word.charAt(i-1))
			{
				throw new IllegalArgumentException("The word contains two '*'.");
			}
		}
		
		if(word.equals(pattern))
		{
			return true;
		}
		
		if(word.charAt(0) == '*')
		{
			if(word.length() == 1)
			{
				return true;
			}
			if(word.length() == 1 && pattern.length() == 0)
			{
				return true;
			}
			for(i=0 ; i<pattern.length() ; i++)
			{
				if(matches(word.substring(1), pattern.substring(i)))
				{
					return true;
				}
			}
		}
		
		if(word.charAt(0) ==  '?' || word.charAt(0) == pattern.charAt(0))
		{
			if(word.length() ==  1 && pattern.length() == 1)
			{
				return true;
			}
			if(matches(word.substring(1), pattern.substring(1)))
			{
				return true;
			}
		}
		
		return false;
	}
}
