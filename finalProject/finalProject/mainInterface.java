package finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class mainInterface {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Vancouver Bus System.");
		System.out.printf("Would you like to: \n (a) find the... \n (b) find info on a stop \n (c) find info on an arrival time \n (Type'a', 'b' or 'c'): ");
		String decision = input.next();
		
		if(decision.equals("b"))
		{
			System.out.print("Search for a stop by name: ");
			String name =input.next();
			TST tree=new TST();
			ArrayList<stops> allStops = new ArrayList<stops>();
			for(int i = 0;i<=10000;i++)
			{
				stops newStop = new stops("stops.txt",i);
				allStops.add(newStop);
			}
			for(int i = 1;i<=10000;i++)
			{
				String currentName;
				currentName = allStops.get(i).stop_name;
				
				String currentValue = ""+i;
				tree.put(currentName, currentValue);
			}
			
			ArrayList<String>result = new ArrayList<String>();
		
			result = tree.checkPrefix(name);
			ArrayList<Integer>resultInt = new ArrayList<Integer>();

			for(int i = 0;i<result.size();i++)
			{
				resultInt.add(Integer.parseInt(result.get(i)));
				System.out.println("Stop id: "+allStops.get(resultInt.get(i)).stop_id +", stop code: " +allStops.get(resultInt.get(i)).stop_code+
						", Stop name: "+allStops.get(resultInt.get(i)).stop_name +", Stop description: "+allStops.get(resultInt.get(i)).stop_desc+
						", Stop latitude: " + allStops.get(resultInt.get(i)).stop_lat + ", stop longtitude"+allStops.get(resultInt.get(i)).stop_lon + 
						", Zone ID: "+allStops.get(resultInt.get(i)).zone_id+", Stop URL: "+allStops.get(resultInt.get(i)).stop_url+
						", Location type: "+allStops.get(resultInt.get(i)).location_type+ ", Parent Station: "+allStops.get(resultInt.get(i)).parent_station);
			}
		}
		
		if(decision.equals("c"))
		{
			System.out.print("Enter arrival time (hh:mm:ss): ");
			String arrivalTime = input.next();
			ArrayList<stop_times> stops= new ArrayList<stop_times>();
			for(int i = 1;i<=2000;i++)
			{
				stop_times newStop = new stop_times("stop_times.txt",i);
				stops.add(newStop);
			}

			ArrayList<String>details = stop_times.findArrivalTime(stops,arrivalTime);
			for(int i = 0;i<details.size();i++)
			{
				System.out.println(details.get(i));
			}
		}
	}

}
