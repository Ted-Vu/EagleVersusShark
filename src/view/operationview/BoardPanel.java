package view.operationview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controller.SelectPieceController;
import models.engine.EngineImpl;

//Board Panel is an observer of model
public class BoardPanel extends JPanel {
	// private AbstractButton[] buttons = new AbstractButton[81];

	List<List<AbstractButton>> buttonList;

	private ButtonGroup group = new ButtonGroup();

	/**
	 * Constructing the board panel initially is a hard-coded construction since we
	 * know exactly the beginning position of each piece
	 */
	public BoardPanel() {

		setLayout(new GridLayout(9, 9));
		buttonList = new ArrayList<>();
		for (int i = 0; i < EngineImpl.getSingletonInstance().getBoard().getRow(); ++i) {
			buttonList.add(new ArrayList<AbstractButton>());
			for (int j = 0; j < EngineImpl.getSingletonInstance().getBoard().getCol(); ++j) {
				buttonList.get(i).add(new JToggleButton());
				buttonList.get(i).get(j).setBackground(Color.WHITE);
				buttonList.get(i).get(j).setBorder(BorderFactory.createRaisedBevelBorder());
				buttonList.get(i).get(j).addActionListener(new SelectPieceController(buttonList.get(i).get(j), this));
				add(buttonList.get(i).get(j));
				group.add(buttonList.get(i).get(j));
			}
		}

		try {
			Image attackingEagle = ImageIO.read(getClass().getResource("/asset/AttackingEagle.png"));
			Image visionaryEagle = ImageIO.read(getClass().getResource("/asset/VisionaryEagle.png"));
			Image leadershipEagle = ImageIO.read(getClass().getResource("/asset/LeadershipEagle.png"));

			// Hard code initial position
			buttonList.get(0).get(3).setIcon(new ImageIcon(attackingEagle));
			buttonList.get(0).get(3).setActionCommand("AttackingEagle");
			buttonList.get(1).get(4).setIcon(new ImageIcon(leadershipEagle));
			buttonList.get(1).get(4).setActionCommand("LeadershipEagle");
			buttonList.get(0).get(5).setIcon(new ImageIcon(visionaryEagle));
			buttonList.get(0).get(5).setActionCommand("VisionaryEagle");

			buttonList.get(0).get(4).setBackground(Color.BLACK);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EngineImpl.getSingletonInstance().seedData();

		// THIS IS ORGINAL APPROACH WITH PRIMITIVE ARRAY
		// setLayout(new GridLayout((int) Math.sqrt(81), (int) Math.sqrt(81)));
		// Image img = null;
		// try {
		// img = ImageIO.read(getClass().getResource("/asset/AttackingEagle.png"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// // constructing a board here, read from model to construct
		// for (int i = 0; i < 81; i++) {
		// buttons[i] = new JToggleButton();
		// buttons[i].setBackground(Color.WHITE);
		// buttons[i].setBorder(BorderFactory.createRaisedBevelBorder());
		// add(buttons[i]);
		// group.add(buttons[i]);
		// }
		//
		// buttons[0].setIcon(new ImageIcon(img));
	}

	public List<List<AbstractButton>> getButtonList() {
		return buttonList;
	}
}