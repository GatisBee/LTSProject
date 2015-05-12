/**
 * 
 */
package lu.uni.fstc.algo3.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import lu.uni.fstc.algo3.simulation.ScanGenerator;

/**
 * @author philippe
 *
 */
public class MainView extends JPanel {

	ScanGenerator scanGenerator;

	private JTabbedPane tabbedPane;

	private HomeView homeView;
	private BillView billView;

	/**
	 * @param scanGenerator
	 * 
	 */
	public MainView(ScanGenerator scanGenerator) {
		this.scanGenerator = scanGenerator;

		this.init();
		this.initGUI();
		this.event();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(0, 0, 800, 600);

	}

	/**
	 * 
	 */
	private void initGUI() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 790, 562);

		homeView = new HomeView(scanGenerator);
		tabbedPane.addTab("Home", homeView);

		billView = new BillView(scanGenerator);
		tabbedPane.addTab("Bill", billView);

		this.add(tabbedPane);

	}

	private void event() {
	}

}
