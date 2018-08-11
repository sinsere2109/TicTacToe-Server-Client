import java.net.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 
 * @author Tyren Villanueva 
 * CECS 277
 * Project 6
 *
 */
public class Client  implements ActionListener
{
	@SuppressWarnings("resource")
	/**
	 *Jbutton rock- Button for use choice rock
	 *Jbutton paper-Button for user choice paper
	 *Jbutton  scissors- Button for user choice scissors
	 */
	
	private static JButton rock= new JButton();
	private static JButton paper= new JButton();
	private static JButton scissors=new JButton(); 
	/**
	 * char userInput- char is added to userInput when user clicks a button
	 */
	private static char userInput=0;
	/**
	 * boolean checkButton- true if user presses button, false if no button is pressed 
	 */
	private static boolean checkButton=false;
	
	/**
	 * Constructor adds action listener to buttons 
	 */
	public Client()
	
	{
		rock.addActionListener(this);
		paper.addActionListener(this);
		scissors.addActionListener(this);
	}
	
	
	public static void main(String [] args) throws IOException, ClassNotFoundException 
	{
		
	new Client(); //used to take care of action listeners in constructor 

	char[]	choices=new char[4];  //array holding pattern 
	Pattern newp = null;		  // pattern object
	String hasPattern="false";    //string determines if the choices array is full, if full it will add it to the Pattern Obejct 
	char cpuInput;
	int arrayIndex=0;
	int userScore=0;
	int cpuScore=0;
	Socket sock;
	BufferedReader in = null;
	PrintStream out = null;
	ObjectOutputStream clientOutputStream=null;
	ObjectInputStream clientInputStream=null;
		try{
			sock = new Socket("localhost",1261);
			in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
			clientOutputStream = new ObjectOutputStream(sock.getOutputStream());
			clientInputStream = new ObjectInputStream(sock.getInputStream());

		}
		catch(Exception e){
			System.out.println(e);
		}
		
		JFrame frame=new JFrame("Rock Paper Scissors");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel playerChoice=new JLabel(" ", JLabel.CENTER);
		JLabel computerChoice=new JLabel(" ", JLabel.CENTER);;
		JLabel result=new JLabel(" ", JLabel.CENTER);
		JLabel  wins;
		JLabel loses;
		 
		JPanel outpanel= new JPanel();
		JPanel buttonP= new JPanel();
		JPanel totals= new JPanel();;
		String winsStr=Integer.toString(userScore);
		String losesStr=Integer.toString(cpuScore);
		wins=new JLabel("Wins: "+winsStr, JLabel.CENTER);
		loses=new JLabel("Loses: "+losesStr, JLabel.CENTER);
		playerChoice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		computerChoice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		result.setFont(new Font("Times New Roman", Font.BOLD, 20));
		wins.setFont(new Font("Times New Roman", Font.BOLD, 20));
		loses.setFont(new Font("Times New Roman", Font.BOLD, 20));
		outpanel.setLayout(new BorderLayout());
		outpanel.add(playerChoice,BorderLayout.WEST);
		outpanel.add(computerChoice,BorderLayout.EAST);
		outpanel.add(result, BorderLayout.SOUTH);
		
		 
		  buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		  buttonP.add(Box.createGlue());
		  buttonP.add(rock);
		  buttonP.add(Box.createGlue());
		  buttonP.add(paper);
		  buttonP.add(Box.createGlue());
		  buttonP.add(scissors);
		  buttonP.add(Box.createGlue());
		 
		 rock.setIcon(new ImageIcon("rock.png"));
		  rock.validate();
		  paper.setIcon(new ImageIcon("paper.png"));
		  paper.validate();
		  scissors.setIcon(new ImageIcon("scissors.png"));
		  scissors.validate();
		  rock.setContentAreaFilled(false); 
	       rock.setFocusPainted(false); 
	       paper.setContentAreaFilled(false); 
	       paper.setFocusPainted(false); 
	       scissors.setContentAreaFilled(false); 
	       scissors.setFocusPainted(false); 
	 
		  
		  
		  totals.setLayout(new FlowLayout());

	        totals.add(wins);
	        totals.add(loses);
	     
	      
	    frame.setSize(700, 400);
	    frame.setLayout(new BorderLayout());
		frame.add(outpanel,BorderLayout.NORTH);
		 frame.add(buttonP,BorderLayout.CENTER);
		frame.add(totals, BorderLayout.SOUTH);
		frame.setVisible(true);
		 String userChoice=null;
		String cpuChoice=null;
		
			
		while(true){
		if(checkButton==false)		//checks if button is pressed 
		{	
		out.println("no");			// sends "no" to server, notifies that no button is pressed so server loops to beginning 
		out.flush();	
		String getstart=in.readLine();   //receives "yes" , "no" server getStart variable has changed 
		}
		if(checkButton==true){	
			out.println("yes");     //sends "yes" to server if a button is pressed
			out.flush();	
			String get=in.readLine();
			if(arrayIndex==4)  //array index is 4 if the choices[] array is filled and ready to create a pattern
			{
				hasPattern="true";  //sends true if choices[] is filled 
			}
			out.println(hasPattern);  //sends status of hasPattern variable "true" if pattern is being created 
			out.flush();
			String pattern=in.readLine(); //receives the status of hasPattern variable from Server
			if(arrayIndex<4) //If  the choices array is not filled therefore less than 4, we add the user input to the array 
			{
				choices[arrayIndex]=userInput; // add user input to array 
				arrayIndex++;                  //increment the array index, which is used to determine if choices array is filled 

			}
			else if(arrayIndex==4) //if arrayindex=4, the choices[] array is filled  
			{

				char[] patternArray={ choices[0], choices[1] , choices[2], choices[3] }; //create an array for new pattern
			/**
			 * 
			 */
				newp= new Pattern(patternArray); //new pattern is created with updated user input choices 
				clientOutputStream.writeObject(newp); // pattern sent to server 
				clientOutputStream.flush();

				for(int i=0;i<3;i++)  //update the choices[] array with new input received from user 
				{
					choices[i]=choices[i+1];
				}
				choices[3]=userInput;
			}	
			if(userInput=='r')   //create string determined by user input to display for GUI
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
			
			cpuInput=in.readLine().charAt(0); //get computer prediction 
			
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
			
		 
			int winner=getWinner(userInput,cpuInput);  // get the winner of the game 
			System.out.println(" ");
			String theResult=null;
			if(winner==1)
			{
				userScore++;
				theResult="You Won!";
			}
			else if(winner==2)
			{
				cpuScore++;
				theResult="You lose!";
			}
			else if(winner==0)
			{
				theResult="Tie Game";
			}
			playerChoice.setText("You chose " + userChoice + "."); //Update gui 
		     computerChoice.setText("CPU chose " + cpuChoice + ".");
		     String winsStr2=Integer.toString(userScore);
			String losesStr2=Integer.toString(cpuScore);
			wins.setText("Wins: "+winsStr2);
			loses.setText("Loses: "+losesStr2);
			result.setText(theResult);
		
			checkButton=false;	//checkButton is set back to false to leave program waiting for another button click after loop
			
			}
	}
	}
	
	
	
	/**
	 * This method determines a winner for the round
	 * @param userChar user's move 
	 * @param cpuChar computer's move 
	 * @returns 0 if tie, returns 1 if User Wins, returns 2 if Computer Wins
	 */
	public static int getWinner(char userChar,char cpuChar){

		int winner=0;	
		if( userChar==cpuChar){
		
			winner=0;
		}
		else if(userChar=='r'&& cpuChar=='p'){
			
			winner=2;
		}
		else if(userChar=='r'&& cpuChar=='s'){
			
			winner=1;
		}
		else if(userChar=='p'&& cpuChar=='r'){
			
			winner=1;
		}
		else if(userChar=='p'&& cpuChar=='s'){
			
			winner=2;
		}
		else if(userChar=='s'&& cpuChar=='r'){
			
			winner=2;
		}
		else if(userChar=='s'&& cpuChar=='p'){
		
			winner=1;
		}

		return winner;

	}

	
/**
 * Used to get user input 
 * Performs action when button is clicked 
 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		checkButton=true; //checkButton = true when button is clicked, therefore the code can continue in main
		
		if(e.getSource() == rock ){
 
			userInput = 'r';
       
		}
        else if(e.getSource() == paper ){
            userInput= 'p';
        }
        else if(e.getSource() == scissors ){
            userInput = 's';
        }
        
	}




}
