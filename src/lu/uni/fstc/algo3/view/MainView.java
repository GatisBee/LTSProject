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

	private ScanGenerator scanGenerator;

	private JTabbedPane tabbedPane;

	private HomeView homeView;
	private BillView billView;
	private SearchView searchView;
	private RoadView roadView;

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

	/**
	 * Initialize general parameter of this view
	 */
	private void init() {
		this.setLayout(null);
		this.setBounds(0, 0, 800, 600);

	}

	/**
	 * Initialize all the element inside this view
	 */
	private void initGUI() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 788, 550);

		homeView = new HomeView(scanGenerator);
		tabbedPane.addTab("Home", homeView);

		billView = new BillView(scanGenerator);
		tabbedPane.addTab("Bill", billView);

		// searchView = new SearchView(scanGenerator);
		// tabbedPane.addTab("Search", searchView);

		roadView = new RoadView(scanGenerator);
		tabbedPane.addTab("Road Section", roadView);

		this.add(tabbedPane);

	}

	/**
	 * Create events for this view
	 */
	private void event() {
	}

}
