/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lu.uni.fstc.algo3.LTSRunner;
import lu.uni.fstc.algo3.simulation.ScanGenerator;

/**
 * @author philippe
 *
 */
public class HomeView extends JPanel {

	private ScanGenerator scanGenerator;

	private State state;

	private JButton generateNbPlate;
	private JButton starStoptBtn;
	private JTextArea infoText;

	/**
	 * @param scanGenerator
	 * 
	 */
	public HomeView(ScanGenerator scanGenerator) {
		this.scanGenerator = scanGenerator;

		this.init();
		this.initGUI();
		this.event();
	}

	/**
	 * Initialize general parameter of this view
	 */
	private void init() {
		this.setLayout(null);
	}

	/**
	 * Initialize all the element inside this view
	 */
	private void initGUI() {

		this.state = State.stop;

		starStoptBtn = new JButton("Start");
		starStoptBtn.setBounds(10, 10, 100, 25);
		this.add(starStoptBtn);

		generateNbPlate = new JButton("Generate Number Plate");
		generateNbPlate.setBounds(560, 10, 220, 25);
		this.add(generateNbPlate);

		infoText = new JTextArea("");
		infoText.setFont(new Font("Serif", Font.ITALIC, 16));
		infoText.setLineWrap(true);
		infoText.setWrapStyleWord(true);

		JScrollPane areaScrollPane = new JScrollPane(infoText);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Info"),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)),
				areaScrollPane.getBorder()));

		areaScrollPane.setBounds(10, 100, 750, 250);

		this.add(areaScrollPane);

	}

	/**
	 * Create events for this view
	 */
	private void event() {

		this.starStoptBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (state.equals(State.stop)) {
					scanGenerator.startGenerating();
					state = State.start;
					starStoptBtn.setText("Stop");
					infoText.setText("Start Simulation");

				} else {
					scanGenerator.stopGenerating();
					state = State.stop;
					starStoptBtn.setText("Start");
					infoText.setText("Stop Simulation");

				}
			}
		});

		this.generateNbPlate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (state.equals(State.stop)) {
					LTSRunner.generateNumberPlates();
					state = State.stop;
					String value = infoText.getText() + "\n"
							+ "Generate new number plates";
					infoText.setText(value);

				}
			}
		});

	}

	public enum State {
		start, stop
	}
}
