/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lu.uni.fstc.algo3.LTSRunner;
import lu.uni.fstc.algo3.simulation.ScanGenerator;

/**
 * @author philippe
 *
 */
public class HomeView extends JPanel {

	ScanGenerator scanGenerator;

	private State state;

	private JButton generateNbPlate;
	private JButton startBtn;
	private JButton stopBtn;

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

	private void init() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
	}

	/**
	 * 
	 */
	private void initGUI() {

		this.state = State.stop;

		startBtn = new JButton("Start");
		startBtn.setBounds(1, 10, 100, 25);
		this.add(startBtn);

		stopBtn = new JButton("Stop");
		stopBtn.setBounds(100, 10, 100, 25);
		this.add(stopBtn);

		generateNbPlate = new JButton("Generate Number Plate");
		generateNbPlate.setBounds(400, 10, 200, 25);
		this.add(generateNbPlate);

	}

	private void event() {

		this.startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (state.equals(State.stop)) {
					scanGenerator.startGenerating();
					state = State.start;
				}
			}
		});

		this.stopBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (state.equals(State.start)) {
					scanGenerator.stopGenerating();
					state = State.stop;
				}

			}
		});

		this.generateNbPlate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (state.equals(State.stop)) {
					LTSRunner.generateNumberPlates();
					state = State.stop;
				}

			}
		});

	}

	public enum State {
		start, stop
	}
}
