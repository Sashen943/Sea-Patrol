package util;

import java.util.ArrayList;
import java.util.HashMap;

import model.graph.Edge;
import model.graph.Graph;
import model.location.Station;

public class DataPopulator {
	
	private static Station norwegianSea;
	private Station skagerrakStrait, straightOfDover, atlanticOcean, IrishSea,  celticSea, englishChannel;
	private Station[] stations;
	private Graph<Station> graph;
	
	/**
	 *  Constructor: init for stations and graph here
	 */
	public DataPopulator() {
		populateStations();
		graph = populateGraph();
		System.out.println(graph.toString());
	}

	public  Graph<Station> populateGraph() {
		
		System.out.println("Populating graph...");
		Graph<Station> graph = new Graph<Station>(false);
		
		//Population data into graph
		graph.addEdge(norwegianSea, skagerrakStrait, 215);
		graph.addEdge(norwegianSea, atlanticOcean, 1000);
		graph.addEdge(norwegianSea, straightOfDover, 1980);
		graph.addEdge(atlanticOcean, skagerrakStrait, 8835);
		graph.addEdge(atlanticOcean, straightOfDover, 7859);
		graph.addEdge(atlanticOcean, IrishSea, 7944);
		graph.addEdge(skagerrakStrait, straightOfDover, 976);
		graph.addEdge(straightOfDover, englishChannel, 163);
		graph.addEdge(englishChannel, celticSea, 580);
		graph.addEdge(celticSea, IrishSea, 500);

		return graph;
	}
	
	private void addStations() {
		stations = new Station[7];
		stations[0] = norwegianSea;
		stations[1] = skagerrakStrait;
		stations[2] = straightOfDover;
		stations[3] = atlanticOcean;
		stations[4] = IrishSea;
		stations[5] = celticSea;
		stations[6] = englishChannel;
	}
	
	private void populateStations() {
		System.out.println("Creating stations...");
		norwegianSea = new Station("Norweigan sea");
		skagerrakStrait = new Station("Skagerrak strait");
		straightOfDover = new Station("Straight of Dover");
		atlanticOcean  = new Station("Atlantic ocean");
		IrishSea = new Station("Irish sea");
		celticSea = new Station("Celtic sea");
		englishChannel = new Station("English channel");
		addStations();
	}

	public Station[] getStations()
	{
		return this.stations;
	}
	
	public Station getStation(int index) {
		return stations[index];
	}
}