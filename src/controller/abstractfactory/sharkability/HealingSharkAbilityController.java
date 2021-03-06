package controller.abstractfactory.sharkability;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.abstractfactory.AbstractAbilityController;
import model.contract.Piece;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import modelcontroller.contract.ControllerModelInterface;
import modelcontroller.facade.ControllerModelFacadeImpl;
import view.operationview.HealingSharkDialog;

/**
 * @author Ted & Kevin
 *
 *         A concrete controller for healing shark ability
 */
public class HealingSharkAbilityController extends AbstractAbilityController {

	private HealingSharkDialog healingDialog;
	private ControllerModelInterface controllerModelFacade = new ControllerModelFacadeImpl();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (controllerModelFacade.getCurrentActivePlayer().getPlayerType() == TeamType.SHARK) {
			healingDialog.dispose();
			PieceType affectedPieceEnum = PieceType.parsePieceType(healingDialog.getSharkRevived());
			try {
				super.controllerModelFacade.updateModelStateHealingSharkRevive(affectedPieceEnum);
				super.viewControllerFacade.updateBoardReviveSharkSuccessful(affectedPieceEnum);
				super.controllerModelFacade.updateModelStateForNextTurn(TeamType.EAGLE);
			} catch (RuntimeException ex) {
				super.viewControllerFacade.updateBoardNotiDialog(ex.getMessage());
			}
		} else {
			healingDialog.dispose();
			super.viewControllerFacade.updateBoardNotiDialog("Not correct turn to revive");
		}
	}

	public void injectHealingDialog(HealingSharkDialog healingDialog) {
		this.healingDialog = healingDialog;
	}

	@Override
	public void setUpViewForAbility(PieceType pieceType) {
		List<Piece> activeSharks = controllerModelFacade.getActiveSharks();
		if (activeSharks.size() == controllerModelFacade.getNumPiece() / 2) {
			super.viewControllerFacade.updateBoardNotiDialog("No shark to revive");
		} else {
			healingDialog = new HealingSharkDialog(this);
		}
	}
}
