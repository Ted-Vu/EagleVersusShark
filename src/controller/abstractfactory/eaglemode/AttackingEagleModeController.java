package controller.abstractfactory.eaglemode;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractModeController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class AttackingEagleModeController extends AbstractModeController {

	@Override
	public void setUpViewForMode() {
		try {
			super.viewControllerFacade.updateBoardBeforeUseSpecialBehaviour(this, PieceType.ATTACKINGEAGLE);
		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardErrorAction(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());

		try {
			super.controllerModelFacade.updateModelAttackingEagleCapture(affectedPieceEnum, true);
			super.viewControllerFacade.updateBoardAfterCapture(btnClicked, PieceType.ATTACKINGEAGLE);
			super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		} catch (RuntimeException ex) {
			super.viewControllerFacade.updateBoardErrorAction(ex.getMessage());
		}

	}

}