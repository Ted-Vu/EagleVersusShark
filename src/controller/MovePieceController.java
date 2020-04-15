package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;

import model.enumtype.PieceType;
import model.enumtype.TeamType;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacade;
import viewcontroller.contract.ViewControllerInterface;

/**
 * This controller responsible for XXX.
 * <p>
 * It handles event when user makes a move AFTER select a piece.
 * 
 * @author ted &#38; kevin
 * 
 */
public class MovePieceController implements ActionListener {

	private ViewControllerInterface viewControllerFacade;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacade();

	private PieceType pieceType;

	/**
	 *	
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton buttonClicked = (AbstractButton) e.getSource();
		viewControllerFacade.updateBoardMovingPiece(buttonClicked, pieceType);
		updateModel(buttonClicked);
		updateModelStateForNextTurn();

	}

	/**
	 * @param pieceName
	 * @param facade
	 */
	public void setUpControllerState(PieceType pieceName, ViewControllerInterface facade) {
		this.pieceType = pieceName;
		this.viewControllerFacade = facade;
	}

	private void updateModel(AbstractButton buttonClicked) {
		Map<String, Integer> newPos = new HashMap<String, Integer>();
		viewControllerFacade.locateNewPos(buttonClicked, newPos);
		controllerModelFacade.updateModelAfterMovingPiece(newPos, pieceType);
	}

	private void updateModelStateForNextTurn() {
		if (pieceType.team() == TeamType.EAGLE) {
			viewControllerFacade.restoreButtonStateForNextTurn();
			controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} else if (pieceType.team() == TeamType.SHARK) {
			viewControllerFacade.restoreButtonStateForNextTurn();
			controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
		}
	}
}
