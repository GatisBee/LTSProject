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

	/**
	 * Initialize general parameter of this view
	 */
	private void init() {
		this.setTitle("LuxTollSystem");
		this.setBounds(0, 0, 800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
	}

	/**
	 * Initialize all the element inside this view
	 */
	private void initGUI() {
		this.mainView = new MainView(scanGenerator);
		this.setContentPane(this.mainView);
	}

}