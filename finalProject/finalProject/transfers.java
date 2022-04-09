package finalProject;

import java.io.File;
import java.util.Scanner;

public class transfers {
	public String from_stop_id;
	public String to_stop_id;
	public String transfer_type;
	public String min_transfer_time;

	transfers(String filename, int i)
	{
		try
		{
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			int j = 0;
			while(scanner.hasNextLine()&&j<=i+1)
			{
				String line = scanner.nextLine().trim();
				String[]key = line.split(",");
				this.from_stop_id = key[0];
				this.to_stop_id = key[1];
				this.transfer_type = key[2];
				this.min_transfer_time = key[3];
				j++;
			}

		}catch(Exception x)
		{

			String[] key = new String[0];
			return;
		}
	}

}
