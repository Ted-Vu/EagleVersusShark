package model.piece;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.board.Cell;

/**
 * @author sefira & kevin
 *
 */
public class AttackingEagle extends AbstractPiece {

	/**
	 * @param x
	 * @param y
	 */
	public AttackingEagle(int x, int y) {
		super(x, y);
	}

	/**
	 *
	 */
	@Override
	public Set<Cell> getValidMove() {
		Map<String, Integer> currentPosition = this.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<Cell> validMoves = new HashSet<>();
		validMoves.addAll(validMovesSouth(currentX, currentY, 1));
		validMoves.addAll(validMovesNorth(currentX, currentY, 1));
		validMoves.addAll(validMovesEast(currentX, currentY, 1));
		validMoves.addAll(validMovesWest(currentX, currentY, 1));
		validMoves.addAll(validDiaNorthEast(currentX, currentY, 1));
		validMoves.addAll(validDiaSouthWest(currentX, currentY, 1));
		validMoves.addAll(validDiaSouthEast(currentX, currentY, 1));
		validMoves.addAll(validDiaNorthWest(currentX, currentY, 1));
		return validMoves;
	}

	/**
	 *
	 */
	@Override
	public boolean movePiece(int x, int y) {
		setPosition(x, y);
		return true;
	}
}
