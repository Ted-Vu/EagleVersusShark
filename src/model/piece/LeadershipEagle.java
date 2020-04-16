
package model.piece;

import java.util.Set;
import model.board.Cell;
import model.piece.movement.BasicMove;

/**
 * @author sefira & kevin
 *
 */
public class LeadershipEagle extends AbstractPiece {
	

	public LeadershipEagle(int x, int y) {
		super(x, y);
	}

	@Override
	public Set<Cell> getValidMove() {
		return new BasicMove().getValidMove(this, 2);
	}

	@Override
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}
}
