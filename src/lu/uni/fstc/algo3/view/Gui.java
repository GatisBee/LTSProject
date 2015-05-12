package lu.uni.fstc.algo3.view;

import javax.swing.JFrame;

import lu.uni.fstc.algo3.simulation.ScanGenerator;

public class Gui extends JFrame {

	private MainView mainView;
	private ScanGenerator scanGenerator;

	/**
	 * Create the frame.
	 * 
	 * @param scanGenerator
	 */

	public Gui(ScanGenerator scanGenerator) {
		this.scanGenerator = scanGenerator;

		this.init();
		this.initGUI();
	}

	private void init() {
		this.setTitle("LuxTollSystem");
		// this.setSize(810, 650);
		setBounds(0, 0, 810, 650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
	}

	private void initGUI() {
		this.mainView = new MainView(scanGenerator);
		this.setContentPane(this.mainView);
	}

}