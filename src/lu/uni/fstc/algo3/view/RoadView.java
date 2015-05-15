/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lu.uni.fstc.algo3.filter.CollectionFilter;
import lu.uni.fstc.algo3.filter.ScanEntryDirectionFilterCriteria;
import lu.uni.fstc.algo3.filter.ScaneEntryNumberPlateFilterCriteria;
import lu.uni.fstc.algo3.filter.ScaneEntryRoadSectionFilterCriteria;
import lu.uni.fstc.algo3.simulation.ScanGenerator;
import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.system.Direction;
import lu.uni.fstc.algo3.system.LTS;
import lu.uni.fstc.algo3.system.RoadSection;
import lu.uni.fstc.algo3.view.HomeView.State;

/**
 * @author philippe
 *
 */
public class RoadView extends JPanel {

	ScanGenerator scanGenerator;
	private LTS lts;

	private State state;

	private JComboBox<String> roadSection;
	private JTextField carChoice;
	private JButton searchBtn;
	private JTextArea roadInfo;

	private RoadSection currentRoadSection;

	/**
	 * @param scanGenerator
	 * 
	 */
	public RoadView(ScanGenerator scanGenerator) {
		this.scanGenerator = scanGenerator;
		this.lts = LTS.getInstance();

		this.init();
		this.initGUI();
		this.event();
	}

	private void init() {
		this.setLayout(null);
	}

	/**
	 * 
	 */
	private void initGUI() {

		List<String> roadNames = new ArrayList<String>();
		for (RoadSection roadSection : lts.getRoadMap().getRoadSections()) {
			roadNames.add(roadSection.getName());
		}

		String[] array = new String[roadNames.size()];
		roadSection = new JComboBox<String>(roadNames.toArray(array));
		roadSection.setBounds(10, 10, 200, 25);
		this.add(roadSection);

		carChoice = new JTextField("Car Number Plate");
		carChoice.setBounds(220, 10, 200, 25);
		this.add(carChoice);

		searchBtn = new JButton("Search");
		searchBtn.setBounds(430, 10, 100, 25);
		this.add(searchBtn);

		roadInfo = new JTextArea("Road Section info...");
		roadInfo.setFont(new Font("Serif", Font.ITALIC, 16));
		roadInfo.setLineWrap(true);
		roadInfo.setWrapStyleWord(true);

		JScrollPane areaScrollPane = new JScrollPane(roadInfo);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Road Info"),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)),
				areaScrollPane.getBorder()));

		areaScrollPane.setBounds(10, 50, 750, 450);

		this.add(areaScrollPane);

	}

	private void event() {

		this.roadSection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String name = (String) cb.getSelectedItem();
				RoadSection road = lts.getRoadMap().findRoadSection(name);

				String value = "There are currently "
						+ road.getVehiclesOnSection() + " vehicles on  "
						+ road.getName() + ".";

				roadInfo.setText(value);
				currentRoadSection = road;
			}
		});

		this.searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern(" dd/MM/yyyy HH:mm");

				String value = "";

				String name = (String) roadSection.getSelectedItem();
				RoadSection road = lts.getRoadMap().findRoadSection(name);

				LTS lts = LTS.getInstance();

				List<ScanEntry> scanEntries = lts.getAllScans();

				// Add filter for direction and road section
				CollectionFilter collectionFilter = new CollectionFilter();
				collectionFilter
						.addFilterCriteria(new ScaneEntryRoadSectionFilterCriteria(
								road));
				collectionFilter
						.addFilterCriteria(new ScanEntryDirectionFilterCriteria(
								Direction.IN));

				// If the field content a number plate we add an other filter
				// for found this number plate.
				if (!carChoice.getText().equals("Car Number Plate")
						&& !carChoice.getText().equals("")) {

					collectionFilter.filter(scanEntries);

					collectionFilter
							.addFilterCriteria(new ScaneEntryNumberPlateFilterCriteria(
									carChoice.getText()));
				}

				collectionFilter.filter(scanEntries);

				// Display the result in the text area
				for (ScanEntry scanEntry : scanEntries) {
					value += scanEntry.getTimestamp().format(formatter)
							+ " - "
							+ scanEntry.getNumberPlate().getNumberPlate()
							+ " - "
							+ lts.getVehicleRegistry()
									.getVehicle(scanEntry.getNumberPlate())
									.getMaker() + " - "
							+ scanEntry.getRoadSection().getName() + "\n";
				}

				roadInfo.setText(value);

			}
		});

	}
}