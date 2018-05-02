package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.graph.Graph;
import model.location.Station;
import util.DataPopulator;
import util.PathFinder;

@SuppressWarnings("serial")
public class PatrolFrame extends JFrame {
	
	//UI components
	private JButton buttonCalculate;
	private JComboBox<Station> comboBoxFrom, comboBoxTo;
	private JComboBox<String> comboBoxSearchType;
	private JLabel  labelFrom, labelTo, labelResult, labelMethod, icon;
	
	//variables for manipulation
	private Station from, to;
	private ArrayList<Station> result;
	private DataPopulator dataPopulator;
	private Graph<Station> locations;
	String[] Methods = {"Least stops", "Shortest distance" };
	String method;
	
	public PatrolFrame(int width, int height) {
		dataPopulator = new DataPopulator();
		locations = dataPopulator.populateGraph();
		InitializeFrame(width,height);
		
	}
	
	private void InitializeFrame(int width, int height) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setTitle("Sea patrol unit");
		this.setLayout(new GridLayout(1,2));
		InitializeLabels();
		this.add(icon);
		this.validate();
		this.add(createFormPanel());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void InitializeLabels() {
		labelTo= new JLabel("Please select the station you would like to go to");
		labelFrom = new JLabel("Please select your current location");
		labelMethod = new JLabel("Please select a route option");
		labelResult = new JLabel("Directions will display here");
		icon = new JLabel();
		icon.setIcon(new ImageIcon("images/Northsea.png"));
	}
	
	private JPanel createToPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		this.comboBoxTo = new JComboBox<>(dataPopulator.getStations());
		this.comboBoxTo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to = dataPopulator.getStation(comboBoxTo.getSelectedIndex());
			}
		});
		panel.add(labelTo);
		panel.add(comboBoxTo);
		return panel;
	}

	private JPanel createMethodPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		this.comboBoxSearchType = new JComboBox<String>(Methods);
		this.comboBoxSearchType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				method = (String) comboBoxSearchType.getSelectedItem(); 
			}
		});
		panel.add(labelMethod);
		panel.add(comboBoxSearchType);
		return panel;
		
	}
	
	private JPanel createFromPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		this.comboBoxFrom = new JComboBox<Station>(dataPopulator.getStations());
		this.comboBoxFrom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				from = dataPopulator.getStation(comboBoxFrom.getSelectedIndex());
			}
		});
		panel.add(labelFrom);
		panel.add(comboBoxFrom);
		
		return panel;
	}
	
	private JPanel createResultsPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(labelResult);
		return panel;		
	}
	
	private JPanel createFormPanel() {
		JPanel panel = new JPanel(new GridLayout(5,1));
		panel.add(createFromPanel());
		panel.add(createToPanel());
		panel.add(createMethodPanel());
		panel.add(createCalculateButton());
		panel.add(createResultsPanel());
		return panel;
	}

	private JButton createCalculateButton() {
		buttonCalculate = new JButton("Calculate");
		
		buttonCalculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxSearchType.getSelectedIndex() == 0)
				{
					System.out.println("---- Least stops----");
					result = PathFinder.shortestUnweightedPath(locations, from, to);
					System.out.println("---- calculating path----");
					System.out.println(result.toString());
					labelResult.setText(result.toString());
				}
				if(comboBoxSearchType.getSelectedIndex() == 1) {
					System.out.println("---- Shortest distance----");
					HashMap<Station, Double> shortestPath = PathFinder.dijkstraShortestPath(locations, from);
					System.out.println("---- calculating path----");
					System.out.println(shortestPath.toString());
					labelResult.setText(shortestPath.toString());
				}
			
			}
		});
		
		return buttonCalculate;
	}
}
