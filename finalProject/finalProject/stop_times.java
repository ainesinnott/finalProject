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
		public String pickup_type;
		public String dropoff_type;
		public String shape_dist_traveled;
	
		stop_times(String filename)
		{
			
			try
			{
				File file = new File(filename);
				Scanner scanner = new Scanner(file);

				while(scanner.hasNextLine())
				{
					for(int i = 0;i<20000;i+=8)
					{
					String line = scanner.nextLine().trim();
					String[]key = line.split(",");
					this.trip_id = key[0];
					this.arrival_time = key[1];
					this.departure_time = key[2];
					this.stop_id = key[3];
					this.stop_sequence = key[4];
					this.pickup_type = key[5];
					this.dropoff_type = key[6];
					this.shape_dist_traveled = key[7];
					}	
				}
			}catch(Exception x)
			{
				String [] key = new String[0];
				return;
			}
		}
	}


