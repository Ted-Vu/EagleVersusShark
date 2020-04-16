package model.piece;

import java.util.Set;
import model.board.Cell;
import model.contract.PieceMovementInterface;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */
public class HealingShark extends AbstractPiece {
	
	private PieceMovementInterface validMoves = new DiagonalMove();
	

	public HealingShark(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		return this.validMoves.getValidMove(this, 1);
	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

}