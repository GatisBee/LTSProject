/**
 * 
 */
package lu.uni.fstc.algo3.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

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
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
	}

	/**
	 * 
	 */
	private void initGUI() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 800, 600);

		homeView = new HomeView(scanGenerator);
		tabbedPane.addTab("Home", homeView);

		billView = new BillView(scanGenerator);
		tabbedPane.addTab("Bill", billView);

		this.add(tabbedPane);

	}

	private void event() {
	}

}
