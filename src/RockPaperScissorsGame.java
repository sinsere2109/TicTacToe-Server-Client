import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * @author Tyren Villanueva 
 *CECS 277
 *Project 3
 */
public class RockPaperScissorsGame 
{

	public static void main(String[] args) 
	{
		System.out.println("Rock-Paper-Scissors: You vs. the Computer");
 

		int x=gameChoice();

		if(x==1)
		{

			Computer cpu=new Computer();		
			char[]	choices=new char[4];		
			int arrayIndex=0;
			boolean game=true;
			int userScore=0;
			int cpuScore=0;
			while(game==true)
			{

				char cpuInput = 0;
				String userChoice=null;
				String cpuChoice=null;
				System.out.println("Your Score-"+userScore);
				System.out.println("Computer Score-"+cpuScore);

				if(arrayIndex<4)
				{

					cpuInput=cpu.getRandomChoice();

					if(cpuInput=='r')
					{
						cpuChoice="Rock";
					}
					else if(cpuInput=='s')
					{
						cpuChoice="Scissors";
					}
					else if(cpuInput=='p')
					{
						cpuChoice="Paper";
					}

				}

				else if(arrayIndex==4)
				{

					Pattern newp=new Pattern(choices);
					cpuInput=cpu.move(newp);
					cpu.addtoMap(new Pattern(choices));
					if(cpuInput=='r')
					{
						cpuChoice="Rock";
					}
					else if(cpuInput=='s')
					{
						cpuChoice="Scissors";
					}
					else if(cpuInput=='p')
					{
						cpuChoice="Paper";
					}

				}
				char userInput=getUserInput();
				if(userInput=='r')
				{
					userChoice="Rock";
				}
				else if(userInput=='s'){
					userChoice="Scissors";
				}
				else if(userInput=='p'){
					userChoice="Paper";
				}
				else if(userInput=='q')
				{

					System.exit(0);
				}
				System.out.println("You chose to throw a "+ userChoice); 
				System.out.println("The computer chose to throw a "+ cpuChoice);
				if(arrayIndex<choices.length)
				{
					choices[arrayIndex]=userInput;
					arrayIndex++;
				}
				else if(arrayIndex==choices.length)
				{
					for(int i=0;i<3;i++)
					{
						choices[i]=choices[i+1];
					}
					choices[3]=userInput;	

				}
				int winner=getWinner(userInput,cpuInput);
				System.out.println(" ");
				if(winner==1)
				{
					userScore++;
				}
				else if(winner==2)
				{
					cpuScore++;
				}


			}
		}
		if(x==2)
		{
			Computer cpu=new Computer();

			loadHashMap(cpu);
			System.out.println("Hashmap Loaded...");
			System.out.println(" ");
			char[]	choices=new char[4];		
			int arrayIndex=0;
			boolean game=true;
			int userScore=0;
			int cpuScore=0;
			while(game==true)
			{

				char cpuInput = 0;
				String userChoice=null;
				String cpuChoice=null;
				System.out.println("Your Score-"+userScore);
				System.out.println("Computer Score-"+cpuScore);

				if(arrayIndex<4)
				{

					cpuInput=cpu.getRandomChoice();

					if(cpuInput=='r')
					{
						cpuChoice="Rock";
					}
					else if(cpuInput=='s')
					{
						cpuChoice="Scissors";
					}
					else if(cpuInput=='p')
					{
						cpuChoice="Paper";
					}

				}

				else if(arrayIndex==4)
				{

					Pattern newp=new Pattern(choices);
					cpuInput=cpu.move(newp);
					cpu.addtoMap(new Pattern(choices));
					if(cpuInput=='r')
					{
						cpuChoice="Rock";
					}
					else if(cpuInput=='s')
					{
						cpuChoice="Scissors";
					}
					else if(cpuInput=='p')
					{
						cpuChoice="Paper";
					}

				}
				char userInput=getUserInput();
				if(userInput=='r')
				{
					userChoice="Rock";
				}
				else if(userInput=='s'){
					userChoice="Scissors";
				}
				else if(userInput=='p'){
					userChoice="Paper";
				}
				else if(userInput=='q')
				{

					if(arrayIndex==4)
					{

						int input=saveOrContinue();

						if(input==1)
						{
							saveHashMap(cpu);
						}
					}
					System.exit(0);
				}
				System.out.println("You chose to throw a "+ userChoice); 
				System.out.println("The computer chose to throw a "+ cpuChoice);
				if(arrayIndex<choices.length)
				{
					choices[arrayIndex]=userInput;
					arrayIndex++;
				}
				else if(arrayIndex==choices.length)
				{
					for(int i=0;i<3;i++)
					{
						choices[i]=choices[i+1];
					}
					choices[3]=userInput;	

				}
				int winner=getWinner(userInput,cpuInput);
				System.out.println(" ");
				if(winner==1)
				{
					userScore++;
				}
				else if(winner==2)
				{
					cpuScore++;
				}


			}
		}

	}
	/**
	 * This method determines a winner for the round
	 * @param userChar user's move 
	 * @param cpuChar computer's
	 * @returns 0 if tie, returns 1 if User Wins, returns 2 if Computer Wins
	 */
	public static int getWinner(char userChar,char cpuChar){

		int winner=0;	
		if( userChar==cpuChar){
			System.out.println("Tie game");
			winner=0;
		}
		else if(userChar=='r'&& cpuChar=='p'){
			System.out.println("You lose!");
			winner=2;
		}
		else if(userChar=='r'&& cpuChar=='s'){
			System.out.println("You Win!");
			winner=1;
		}
		else if(userChar=='p'&& cpuChar=='r'){
			System.out.println("You Win!");
			winner=1;
		}
		else if(userChar=='p'&& cpuChar=='s'){
			System.out.println("You lose!");
			winner=2;
		}
		else if(userChar=='s'&& cpuChar=='r'){
			System.out.println("You lose!");
			winner=2;
		}
		else if(userChar=='s'&& cpuChar=='p'){
			System.out.println("You Win!");
			winner=1;
		}

		return winner;

	}
	public static int saveOrContinue(){
		boolean test=true;
		int userInput = 0;
		while(test)
		{
			Scanner input= new Scanner(System.in);
			System.out.println("Choose an option:");
			System.out.println("1. Save Map to file");
			System.out.println("2. Quit Game");
			try{
				userInput=input.nextInt();
				if(userInput==1||userInput==2){
					test=false;
				}

				else{
					System.out.println("Invalid Input");
					System.out.println(" ");
				}
			}catch(InputMismatchException im){
				input.next();
				System.out.println("Invalid Input");
			}
		}
		return userInput;



	}

	public static int newOrContinueGame()
	{
		boolean test=true;
		int userInput = 0;
		while(test)
		{
			Scanner input= new Scanner(System.in);
			System.out.println("Choose an option:");
			System.out.println("1. New Game");
			System.out.println("2. Continue Game");
			try{
				userInput=input.nextInt();
				if(userInput==1||userInput==2){
					test=false;
				}

				else{
					System.out.println("Invalid Input");
					System.out.println(" ");
				}
			}catch(InputMismatchException im){
				input.next();
				System.out.println("Invalid Input");
			}
		}
		return userInput;
	}

	/**
	 * 
	 * @return 
	 */
	public static char getUserInput()
	{
		boolean test=true;
		char userInput = 0;
		while(test)
		{
			Scanner input= new Scanner(System.in);
			System.out.println("What would you like to throw at the computer?");
			System.out.println(" ");
			System.out.println("r-Rock");
			System.out.println("p-Paper");
			System.out.println("s-Scissors");
			System.out.println("q-Quit");
			try{
				userInput=input.next().charAt(0);
				if(userInput=='r'||userInput=='s'||userInput=='p'){
					test=false;
				}
				else if(userInput=='q'){
					test=false;
					System.out.println("Exiting Game...");

				}
				else
					System.out.println("Invalid Input");
				System.out.println(" ");
			}catch(InputMismatchException im){
				input.next();
				System.out.println("Invalid Input");
			}

		}
		return userInput;
	}


	/**
	 * Asks the user what difficulty they want to play, and returns 
	 * @returns user input 
	 */
	public static int gameChoice()
	{
		boolean testInput=true;
		int x = 0;
		while(testInput)
		{
			Scanner in= new Scanner(System.in);
			System.out.println("Select Difficulty");
			System.out.println("1. Beginner");
			System.out.println("2. Veteran");
			try
			{
				x=in.nextInt();
				if(x==1||x==2)
				{
					testInput=false;
				}
				else 
					System.out.println("Invalid Input");
				System.out.println(" ");
			}
			catch(InputMismatchException im){
				in.next();
				System.out.println("Invalid Input");
			}

		}
		return x;
	}

	/**
	 * Saves the hash map object to a file 
	 */
	public static void saveHashMap(Computer c)
	{

		try{

			ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("Map.dat"));
			out.writeObject(c);
			out.close();
		}catch(IOException e){
			System.out.println("Error processing file");
		}
	}

	/**
	 * loads the hashmap to a file 
	 * @param c- take a computer object to load file
	 */
	public static void loadHashMap(Computer c)
	{
		File file= new File("Map.dat");
		if(file.exists())
		{
			try{
				ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
				c=(Computer) in.readObject();
				in.close();
			}catch(IOException e){
				System.out.println("Error processing file.");
			}catch(ClassNotFoundException e){
				System.out.println("Could not find class.");
			}
		}
	}



}
