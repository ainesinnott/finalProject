package finalProject;

import java.util.ArrayList;

public class shortestRoute {

	public double[][]busSystem = new double[12479][12479];

	public shortestRoute()
	{
		busSystemBuilder();
	}
	public void busSystemBuilder()
	{
		for(int i = 0;i<12479;i++)
		{
			for (int j =0; j<12479;j++)
			{
				if(i==j)
				{
					busSystem[i][j] = 0;
				}
				else
				{
					busSystem[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		int source = 0;
		int destination = 0;
		int lastTrip = 0;
		int currentTrip =0;
		double cost = 1;
		ArrayList<stop_times> allStopTimes= new ArrayList<stop_times>();
		for(int i = 1;i<=1700000;i++)
		{
			stop_times newStopTime = new stop_times("stop_times.txt",i);
			allStopTimes.add(newStopTime);
		}
		for(int i = 0; i <allStopTimes.size();i++)
		{
			lastTrip = currentTrip;
			currentTrip = Integer.parseInt(allStopTimes.get(i).trip_id);
			source = destination;
			destination = Integer.parseInt(allStopTimes.get(i).stop_id);

			if(lastTrip == currentTrip)
			{
				busSystem[source][destination]=cost;
			}
		}

		int transferType;
		int minTransferTime;

		ArrayList<transfers> allTransfers= new ArrayList<transfers>();
		for(int i = 1;i<=5085;i++)
		{
			transfers newTransfer = new transfers("transfers.txt",i);
			allTransfers.add(newTransfer);
		}
		for(int i = 0; i <allTransfers.size();i++)
		{
			source =Integer.parseInt(allTransfers.get(i).from_stop_id);
			destination=Integer.parseInt(allTransfers.get(i).to_stop_id);
			transferType = Integer.parseInt(allTransfers.get(i).transfer_type);
			if(transferType == 0)
			{
				busSystem[source][destination]=2;
			}
			else if(transferType == 2)
			{
				minTransferTime = Integer.parseInt(allTransfers.get(i).min_transfer_time);
				busSystem[source][destination]=minTransferTime/100;

			}
		}
	}
	private void relaxEdge(int source, int destination, double[]distanceTo, int[]edgeTo)
	{
		if(distanceTo[destination] > distanceTo[source] +busSystem[source][destination])
		{
			distanceTo[destination] = distanceTo[source]+busSystem[source][destination];
			edgeTo[destination] = source;
		}
	}

	public ArrayList<String> shortestPath(int source, int destination)
	{
		int marked[] = new int[12479];
		double distanceTo[] = new double[12479];
		int edgeTo[] =new int[12479];
		ArrayList<String> results = new ArrayList<String>();

		marked[source] = 1;
		distanceTo[source] = 0;
		int position = source;
		int countStops = 0;

		double node1 = source;
		double node2=destination;
		String route= "";

		double shortestDistance = Double.POSITIVE_INFINITY;

		if(source == destination)
		{
			results.add("Cost: " +Double.toString(busSystem[source][destination]));
			results.add("via: ");
			results.add(Integer.toString(source));
			return results;
		}
		for(int i = 0;i<distanceTo.length;i++)
		{
			if(i!=source)
			{
				distanceTo[i] = Double.POSITIVE_INFINITY;
			}
		}

		while(countStops<distanceTo.length) 
		{
			for(int i = 0; i < distanceTo.length;i++)
			{
				if(marked[i]!=1&&shortestDistance>distanceTo[i])
				{
					position = i;
					shortestDistance = distanceTo[i];
				}
			}
			countStops++;

			for(int i = 0; i<busSystem[position].length;i++)
			{
				if(marked[i] ==0)
				{
					relaxEdge(position, i , distanceTo, edgeTo);
				}
			}
			marked[position]=1;

		}
		if(distanceTo[destination] == Double.POSITIVE_INFINITY)
		{
			results.add("There is no roue possible between these stops");
			return results;
		}

		while(node1!=node2)
		{
			route = "," + edgeTo[destination]+route;
			destination = edgeTo[destination];
		}
		route = route + ", "+ destination;

		results.add("Cost: "+Double.toString(distanceTo[destination]));
		results.add("via: ");
		results.add(route);
		return results;

	}
}