package model.piece;


import java.util.Set;
import model.board.Cell;
import model.piece.movement.DiagonalMove;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractPiece {
	

	public DefensiveShark(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		return new DiagonalMove().getValidMove(this, 2);

	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}
}