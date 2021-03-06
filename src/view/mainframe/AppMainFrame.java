package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import view.operationview.BoardPanel;
import view.operationview.OperationToolbar;
import view.operationview.RightPanel;

/**
 * 
 * Container for board
 * 
 * @author ted &#38; kevin
 */
public class AppMainFrame extends JFrame {

	private static final long serialVersionUID = -6241584551658525365L;
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;

	private OperationToolbar operationToolbar;
	private BoardPanel boardPanel;
	private RightPanel rightPanel;

	/**
	 * Construct component here
	 */
	public AppMainFrame() {
		boardPanel = new BoardPanel(this);
		rightPanel = new RightPanel(boardPanel.getFacade());
		operationToolbar = new OperationToolbar(this, boardPanel.getFacade());

		setTitle("Eagle vs Shark");

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenDimension.width / 2 - FRAME_WIDTH / 2, screenDimension.height / 2 - FRAME_HEIGHT / 2);

		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.add(boardPanel);
		centerPanel.add(rightPanel);

		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(operationToolbar, BorderLayout.NORTH);

		boardPanel.getFacade().addPropertyChangeListener(rightPanel.getModePanel());
		boardPanel.getFacade().addPropertyChangeListener(rightPanel.getStatusPanel());

		// for program termination when closing frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public RightPanel getRightPanel() {
		return rightPanel;
	}
}
