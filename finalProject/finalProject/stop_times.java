
package finalProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class stop_times {

	public String trip_id;
	public String arrival_time;
	public String departure_time;
	public String stop_id;
	public String stop_sequence;
	public String stop_headsign;
	public String pickup_type;
	public String dropoff_type;
	public String shape_dist_traveled;

	stop_times(String filename, int i)
	{
		try
		{
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			int j = 0;
			while(scanner.hasNextLine()&&j<i+1)
			{

				String line = scanner.nextLine().trim();
				String[]key = line.split(",");
				this.trip_id = key[0];

				this.arrival_time = key[1];

				this.departure_time = key[2];
				this.stop_id = key[3];
				this.stop_sequence = key[4];
				this.stop_headsign = key[5];
				this.pickup_type = key[6];
				this.dropoff_type = key[7];
				if(key.length ==9)
				{
					this.shape_dist_traveled = key[8];
				}
				j++;

			}

		}catch(Exception x)
		{

			String[] key = new String[0];
			return;
		}

	}
	static ArrayList<stop_times> insertionSort (ArrayList<stop_times>stops){
			stop_times temporary;
			for(int i = 0; i<stops.size(); i++)
			{
				for (int j = i; j > 0 ; j--)
				{
					int number1 =Integer.parseInt(stops.get(j).stop_id);
					int number2 = Integer.parseInt(stops.get(j-1).stop_id);
					if(number1>number2)
					{
				
						temporary = stops.get(j);
						stops.set(j, stops.get(j-1));
						stops.set(j-1, temporary);
					}
				}
			}
			return(stops);	
		}
	
	public static void findArrivalTime(ArrayList<stop_times> stops,String arrivalTime)
	{
		String str="";

		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for(int i = 0; i<stops.size();i++)
		{

			if(stops.get(i).arrival_time.equals(" "+arrivalTime)||stops.get(i).arrival_time.equals(arrivalTime))
			{
				values.add(i);
			}
		}
		ArrayList<stop_times> valuesStops = new ArrayList<stop_times>();
		for(int i = 0;i<values.size();i++)
		{
			valuesStops.add(stops.get(values.get(i)));
			insertionSort(valuesStops);
		}
		
		for(int i = 0;i<values.size();i++)
		{

			System.out.println("trip id: "+valuesStops.get(i).trip_id+", arrival time: "+valuesStops.get(i).arrival_time+
					", departure time: "+valuesStops.get(i).departure_time +" stop_id: "+valuesStops.get(i).stop_id+
					", stop_sequence: " +valuesStops.get(i).stop_sequence + ", stop headsign: "+ valuesStops.get(i).stop_headsign+
					", pickup type: "+valuesStops.get(i).pickup_type+", dropoff type: "+valuesStops.get(i).dropoff_type+
					", shape distance travelled: "+valuesStops.get(i).shape_dist_traveled);

		}

	}

	public static void main (String[]Args)
	{

		Scanner input = new Scanner(System.in);
		System.out.print("Enter arrival time (hh:mm:ss): ");
		String arrivalTime = input.next();
		ArrayList<stop_times> stops= new ArrayList<stop_times>();
		for(int i = 1;i<=10000;i++)
		{
			stop_times newStop = new stop_times("stop_times.txt",i);
			stops.add(newStop);
		}

		findArrivalTime(stops,arrivalTime);

	}
}


