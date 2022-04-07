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
	public String location_type;
	public String parent_station;

	stops(String filename)
	{
		try
		{
			File file = new File(filename);
			Scanner scanner = new Scanner(file);

			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine().trim();
				String[]key = line.split(",");
				this.stop_id = key[0];
				this.stop_code = key[1];
				this.stop_name = key[2];
				this.stop_desc = key[3];
				this.stop_lat = key[4];
				this.stop_lon = key[5];
				this.zone_id = key[6];
				this.location_type = key[7];
				this.parent_station = key[8];
			}
		}catch(Exception x)
		{
			String [] key = new String[0];
			return;
		}
	}



}
