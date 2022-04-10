package finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class mainInterface {

	public static void main(String[] args) {	
		boolean exit = false;
		System.out.println("Welcome to the Vancouver Bus System.");
		Scanner input = new Scanner(System.in);
		while(!exit)
		{
			System.out.printf("Would you like to: \n (a) find the shortest route between two stops \n (b) find info on a stop based on the name \n (c) find info on a trip based on arrival time \n (Type 'a', 'b', 'c' or 'exit'): ");
			String decision = input.next();

			if (decision.equalsIgnoreCase("a"))
			{

				System.out.print("Enter source bus stop: ");
				int stop1 = input.nextInt();

				System.out.print("Enter destination bus stop: ");
				int stop2 = input.nextInt();
				System.out.println("Calculating... ");
				shortestRoute sr = new shortestRoute();
				sr.busSystemBuilder();

				System.out.println("Shortest distance from "+stop1+" to "+stop2+" is "+
						sr.shortestPath(stop1,stop2));
			}

			else if(decision.equalsIgnoreCase("b"))
			{
				System.out.print("Search for a stop by name: ");
				String name =input.next();

				{
					System.out.println("Calculating... ");
					stops.findStop(name);
				}
			}

			else if(decision.equalsIgnoreCase("c"))
			{
				System.out.print("Enter arrival time (hh:mm:ss): ");
				String arrivalTime = input.next();
				String[]key = arrivalTime.split(":");

				try {
					int hours = Integer.parseInt(key[0]);
					int minutes = Integer.parseInt(key[1]);
					int seconds = Integer.parseInt(key[2]);
					if(hours>24||hours<0)
					{
						System.out.println("This time is invalid. Please try again. ");
					}
					else
					{
						System.out.println("Calculating... ");
						ArrayList<stop_times> stops= new ArrayList<stop_times>();
						for(int i = 1;i<=10000;i++)
						{
							stop_times newStop = new stop_times("stop_times.txt",i);
							stops.add(newStop);
						}

						stop_times.findArrivalTime(stops,arrivalTime);
					}

				}catch(NumberFormatException e)
				{
					System.out.println("This is not a valid time");
				}
			}
			else if(decision.equalsIgnoreCase("exit"))
			{
				exit = true;
			}
			else
			{
				System.out.println("Invalid input. Please enter 'a','b','c' or 'exit'.");
			}
		}

		System.out.print("Thank you, goodbye! ");
	}

}
