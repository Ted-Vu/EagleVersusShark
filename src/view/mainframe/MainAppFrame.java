package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.operationview.BoardPanel;
import view.operationview.OperationToolbar;
import view.operationview.RightPanel;

public class MainAppFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6241584551658525365L;
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;

	private OperationToolbar operationToolbar;
	private BoardPanel boardPanel;
	private RightPanel rightPanel;

	public MainAppFrame() {
		boardPanel = new BoardPanel();
		operationToolbar = new OperationToolbar(this);
		rightPanel = new RightPanel();

		setTitle("Eagle vs Shark");

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenDimension.width / 2 - FRAME_WIDTH / 2, screenDimension.height / 2 - FRAME_HEIGHT / 2);

		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.add(boardPanel);
		centerPanel.add(rightPanel);
		
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(operationToolbar, BorderLayout.NORTH);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
}
