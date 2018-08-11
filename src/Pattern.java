import java.io.Serializable;
import java.util.Arrays;
/**
 * 
 * @author Tyren Villanueva
 *CECS 277
 *Project 3
 */
public class Pattern implements Serializable
{
	
	private char[] pattern;
	/**
	 * Constructor to get array of patterns
	 * @param patt- an array of char that stores the pattern of the user input
	 */
	public Pattern(char[] patt)
	{
		pattern=patt;
	}

	/**
	 * 
	 * @return returns array of pattern
	 */
	public char[] getPattern()
	{
		return pattern;
	}
	@Override
	public int hashCode() 
	{

		return Arrays.hashCode(pattern);
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj==this)
		{
			return true;
		}

		if(!(obj instanceof Pattern))
		{
			return false;
		}
		Pattern p= (Pattern) obj;
		if(Arrays.equals(pattern, p.getPattern())){
			return true;
		}
		return true;
		 
	}


}
