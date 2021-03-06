package controller.abstractfactory.eagleability;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

import controller.abstractfactory.AbstractAbilityController;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author Ted & Kevin
 *
 *         A concrete ability controller for visionary eagle
 */
public class VisionaryEagleAbilityController extends AbstractAbilityController {

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonClicked = (AbstractButton) e.getSource();
		String buttonClickedStr = buttonClicked.getActionCommand();
		if (buttonClickedStr.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())
				|| buttonClickedStr.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())) {
			viewControllerFacade.updateBoardAfterSwap(buttonClicked);
		}
		super.controllerModelFacade.updateModelStateSwapPiece(PieceType.parsePieceType(buttonClickedStr));
		super.controllerModelFacade.updateModelStateForNextTurn(TeamType.SHARK);
	}

}
