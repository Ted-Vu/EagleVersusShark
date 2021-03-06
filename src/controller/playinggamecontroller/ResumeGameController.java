package controller.playinggamecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import model.contract.Player;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacadeImpl;
import viewcontroller.contract.ViewControllerInterface;

public class ResumeGameController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacadeImpl();

	public ResumeGameController(ViewControllerInterface viewControllerFacade) {
		this.viewControllerFacade = viewControllerFacade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player currentPlayer = controllerModelFacade.getCurrentActivePlayer();

		controllerModelFacade.setTurnStartingGame(currentPlayer.getPlayerType());

		controllerModelFacade.setResumeGame();
		viewControllerFacade.resumeGame(currentPlayer.getPlayerType());
		AbstractButton buttonClicked = (AbstractButton) e.getSource();
		buttonClicked.setEnabled(false);
	}

}
