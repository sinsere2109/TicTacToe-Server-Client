import java.net.*;
import java.lang.*;
import java.io.*;

/**
 * 
 * @author Tyren Villanueva 
 *CECS 277
 *Project 6 
 */
public class Server 
{

public static void main(String [] args)
{
	Pattern newp1=null;
	Computer cpu=new Computer();				
	String getStart=null;
	ObjectInputStream clientInputStream=null;
	ObjectOutputStream clientOutputStream=null;
	//Server
	ServerSocket server;
	Socket s=null;
	BufferedReader in;
	PrintWriter out;
	
	char cpuInput = 0; //computer prediction sent to client 
	try
	{
	server = new ServerSocket(1261);
	System.out.println("Waiting...");
	s = server.accept();
	System.out.println("Connected: "+ s.getInetAddress());
	in= new BufferedReader(new InputStreamReader(s.getInputStream()));
	out = new PrintWriter(s.getOutputStream());
	clientInputStream = new  ObjectInputStream(s.getInputStream());
	clientOutputStream= new ObjectOutputStream(s.getOutputStream());
	 
	while(true)	{	
		 
		getStart = in.readLine();  //Start is "no" if no Button is pressed
		 out.println(getStart);		
		 out.flush();
	if(getStart.equals("yes"))  //getStart is "yes" if a button is pressed
	{
	
	String hasPattern=in.readLine();  //check is a pattern exist, pattern exist after 4 button presses 
	out.println(hasPattern);		  // pattern is "false" if no pattern exist , "true" if pattern is created in client
	out.flush();	
	newp1=null;						 // initialize the pattern object 
		if(hasPattern.equals("false"))   //code runs if pattern doesn't exist 
		{
		cpuInput=cpu.getRandomChoice();   		// random input is generated 
		out.println(cpuInput);					//send computer prediction 
		out.flush();
		}
		else if(hasPattern.equals("true"))		// checks is pattern exist
		{
			newp1 = (Pattern)clientInputStream.readObject(); 	// Pattern is received from client 
			cpuInput=cpu.move(newp1);							//computer prediction is generated 
			cpu.addtoMap(newp1);								//pattern is added to map
			out.println(cpuInput);								//send computer prediction 
			out.flush();
		}
		}

	}
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
	
	}
	}




