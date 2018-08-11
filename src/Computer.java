import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;


/**
 * @author Tyren Villanueva
 *CECS 277
 *Project 3
 */
public class Computer implements Serializable
{
	private boolean hasPatt=false;
	/**
	 * Hash Map object 
	 */
	private HashMap<Pattern,Integer> hashMap;
	
	/**
	 * Constructor that initializes the hash map
	 */
	public Computer() 
	{
		hashMap=new HashMap<Pattern,Integer>();
	}

	/**
	 * Store pattern 
	 * Adds new pattern object to map
	 * @param p - passes the pattern of the user
	 */
	public void addtoMap(Pattern p)
	{
		Integer numofTimes= hashMap.get(p);
		if(numofTimes==null)
		{

			hashMap.put(p, 1);

		}
		else 
		{
			numofTimes=numofTimes+1;
			hashMap.put(p, numofTimes);
		}

	}

	/**
	 * makePrediction
	 * @param p
	 * @returns a char, which is the new move of the computer
	 */
	public char move(Pattern p)
	{
		
		char newMove=0;
		Integer sCount=0;
		Integer rCount=0;
		Integer pCount=0;
		if (!hashMap.isEmpty())
		{
			char[] pattern= p.getPattern();          
			char [] patternArray= new char[4];
			for(int i=1;i<4;i++)
			{
				patternArray[i-1]=pattern[i];
			}

			patternArray[3]='r';
			Pattern newPattern1= new Pattern(patternArray);
			if(hashMap.containsKey(newPattern1)==true)
			{

				rCount=hashMap.get(newPattern1);
				 
			}

			patternArray[3]='p';
			Pattern newPattern2= new Pattern(patternArray);
			if(hashMap.containsKey(newPattern2)==true)
			{

				pCount=hashMap.get(newPattern2);

			}
			patternArray[3]='s';

			Pattern	newPattern3= new Pattern(patternArray);

			if(hashMap.containsKey(newPattern3)==true)
			{


				sCount=hashMap.get(newPattern3);

			}

			if(rCount>sCount&&rCount>pCount)
			{
				newMove='p';
			}
			else if(sCount>rCount&& sCount>pCount){
				newMove='r';
			}
			else if(pCount>rCount&&pCount>sCount){
				newMove='s';
			}
			else if(rCount==sCount && rCount==sCount){
				newMove=getRandomChoice();
			}
			else if(rCount==pCount && pCount >0)
			{
				Random rand= new Random();
				int random= rand.nextInt(2)+1;
				if(random==1)
				{
					newMove='r';	
				}
				else if(random==2)
				{
					newMove='p';
				}			
			}
			else if(rCount==sCount && sCount>0)
			{
				Random rand= new Random();
				int random= rand.nextInt(2)+1;
				if(random==1)
				{
					newMove='r';	
				}
				else if(random==2)
				{
					newMove='s';
				}			
			}
			else if(pCount == sCount && pCount>0)
			{
				Random rand= new Random();
				int random= rand.nextInt(2)+1;
				if(random==1)
				{
					newMove='p';	
				}
				else if(random==2)
				{
					newMove='s';
				}		

			}

		}
		else{

			newMove=getRandomChoice();
		}

		return newMove;
	}



	/**
	 * gets a random move (char) from the computer 
	 * @returns a char of the new random move of the computer
	 */
	public char getRandomChoice()
	{
		char choice=0;
		Random rand= new Random();
		int random= rand.nextInt(3)+1;

		if(random==1){
			choice='r';
		}
		else if(random==2){
			choice='p';
		}
		else if(random==3){
			choice='s';
		}
		return choice;
	}

	public void sethasPatt(boolean b){
		hasPatt=b;
	}
	public boolean hasPattern(){
	
		return hasPatt;
	}


}
