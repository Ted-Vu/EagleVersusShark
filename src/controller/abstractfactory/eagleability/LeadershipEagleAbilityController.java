package controller.abstractfactory.eagleability;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractAbilityController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

public class LeadershipEagleAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btnClicked = (AbstractButton) e.getSource();
		PieceType affectedPieceEnum = PieceType.parsePieceType(btnClicked.getActionCommand());
		super.controllerModelFacade.updateModelStateProtectPiece(affectedPieceEnum, PieceType.LEADERSHIPEAGLE);

		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
		viewControllerFacade.updateBoardAfterProtect();

	} 

	@Override
	public void setUpViewForAbility() {
		try {
			super.viewControllerFacade.updateBoardBeforeCommitAction(this, PieceType.LEADERSHIPEAGLE);
		} catch (RuntimeException e) {
			super.viewControllerFacade.updateBoardErrorAction(e.getMessage());
		}
	}
}
