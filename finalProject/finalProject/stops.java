package finalProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class stops {
	public String stop_id;
	public String stop_code;
	public String stop_name;
	public String stop_desc;
	public String stop_lat;
	public String stop_lon;
	public String zone_id;
	public String stop_url;
	public String location_type;
	public String parent_station;

	stops(String filename, int i)
	{
		
		try
		{
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			int j = 0;
			String newName = "";
			while(scanner.hasNextLine()&&j<=i+1)
			{
				String line = scanner.nextLine().trim();
				String[]key = line.split(",");
				this.stop_id = key[0];
				// flagstop, wb, nb, sb, eb
				this.stop_code = key[1];
				if(key[2].substring(0,9).equals("FLAGSTOP "))
				{
					String a = key[2];
					String firstPart=a.substring(0,9);
					a = a.substring(9);
					newName = a+firstPart;
				}
				else if(key[2].substring(0,3).equals("WB "))
				{
					String a = key[2];
					String firstPart=a.substring(0,3);
					a = a.substring(3);
					newName = a+firstPart;
				}
				else if(key[2].substring(0,3).equals("NB "))
				{
					String a = key[2];
					String firstPart=a.substring(0,3);
					a = a.substring(3);
					newName = a+firstPart;
				}
				else if(key[2].substring(0,3).equals("SB "))
				{
					String a = key[2];
					String firstPart=a.substring(0,3);
					a = a.substring(3);
					newName = a+firstPart;
				}
				else if(key[2].substring(0,3).equals("EB "))
				{
					String a = key[2];
					String firstPart=a.substring(0,3);
					a = a.substring(3);
					newName = a+firstPart;
				}
					this.stop_name = newName;
				this.stop_desc = key[3];
				this.stop_lat = key[4];
				this.stop_lon = key[5];
				this.zone_id = key[6];
				this.stop_url = key[7];
				this.location_type = key[8];
				this.parent_station = key[9];
				j++;
			}
		}catch(Exception x)
		{
			String [] key = new String[0];
			return;
		}
	}

	public static void main (String[]Args)
	{
		stops newStop = new stops("stops.txt",3);
		System.out.print(newStop.stop_name);
	}

}
