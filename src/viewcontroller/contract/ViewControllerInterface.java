package viewcontroller.contract;

import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.MovePieceController;
import controller.PlayerAction;
import controller.abstractfactory.AbilityController;
import model.enumtype.PieceType;

/**
 * @author ted &#38; kevin
 *
 */
public interface ViewControllerInterface {

	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @param buttonClicked
	 * @param newPos
	 */
	public void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos);

	/**
	 * 
	 */
	public void notifyNotStartGame();

	/**
	 * 
	 */
	public void notifySelectWrongTeam();

	/**
	 * @param buttonClicked
	 * @param pieceType
	 */
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType);

	public void updateBoardBeforeMovePiece(AbstractButton buttonClicked, MovePieceController movePieceController);

	/**
	 * @param buttonClicked
	 */
	public void updateBoardSelectAnotherPiece(AbstractButton buttonClicked);

	public void updateBoardBeforeSwap(AbilityController visionController);

	public void getPlayerAction(PlayerAction playerAction);

	public void updateBoardAfterSwap(AbstractButton buttonClicked);

	public void updateBoardChangeAction();

	public void updateBoardBeforeLeadershipProtect(AbilityController leadershipController);

	public void updateBoardAfterLeadershipProtect();

	public void updateBoardBeforeAttackingCapture(AbilityController attackingController);

	public void updateBoardAfterAttackingCapture(AbstractButton btnClicked);

	public void updateBoardFailToCaptureAttacking();

}