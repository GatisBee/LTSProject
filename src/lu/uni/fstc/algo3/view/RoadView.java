/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lu.uni.fstc.algo3.simulation.ScanGenerator;
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

	private JButton searchBtn;
	private JComboBox<String> roadSection;
	private JTextArea roadInfo;

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

		searchBtn = new JButton("Search");
		searchBtn.setBounds(210, 10, 100, 25);
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

				String value = "The " + name + " have "
						+ road.getVehiclesOnSection() + " vehicles.";

				roadInfo.setText(value);
			}
		});

	}
}