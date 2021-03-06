package view.configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.startgamecontroller.SaveGameBtnController;
import viewcontroller.contract.ViewControllerInterface;

/**
 * SaveGameDialog initiated when user hit save on menu bar
 * 
 * @author kevin & ted
 */
public class SaveGameDialog extends JDialog {

	private static final long serialVersionUID = -4258981534634398081L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 100;

	private JTextField fileNameField;

	private JButton saveGameButton = new JButton("Save");

	/**
	 * Constructor savegamedialog all of its necessary components
	 * 
	 * @param viewControllerFacade
	 */
	public SaveGameDialog(ViewControllerInterface viewControllerFacade) {
		fileNameField = new JTextField(15);

		setTitle("Save Game");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		saveGameButton.addActionListener(new SaveGameBtnController(this, viewControllerFacade));

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.add(new JLabel("File name:"));
		textFieldPanel.add(fileNameField);
		add(textFieldPanel, BorderLayout.CENTER);

		JPanel btnPanel = new JPanel();
		btnPanel.add(saveGameButton);
		add(btnPanel, BorderLayout.SOUTH);

		setModal(true);
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	/**
	 * @return name of the saving file
	 * @param
	 */
	public String getFileNameInput() {
		return fileNameField.getText();
	}

}