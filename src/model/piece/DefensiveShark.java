package model.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.piece.movement.BasicMove;
import model.piece.movement.DiagonalDecorator;
import model.piece.movement.PieceMoveDecorator;

/**
 * @author chanboth
 *
 */
public class DefensiveShark extends AbstractPiece {

	final int NEIGHBOURING_DISTANCE = 1;
	private final Engine engine;

	private static final long serialVersionUID = -3824904265692727849L;

	public DefensiveShark(int x, int y, Engine engine) {
		super(x, y);
		this.engine = engine;
	}

	@Override
	@Requires({ "getPosition() != null" })
	@Ensures("getValidMove() != null")
	public Set<Cell> getValidMove() {
		return new PieceMoveDecorator(new DiagonalDecorator(new BasicMove())).getValidMove(this, 2);
	}

	@Override
	@Requires({ "x>=0", "y>=0" })
	public void movePiece(int x, int y) {
		setPosition(x, y);
	}

	@Override
	public void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece) {
		if (pieceAbility.equals(PieceAbility.PROTECT)) {
			defend(affectedPiece);
			if (EngineImpl.getSingletonInstance().pieceOperator().getHealingAbilityCounter() == 2) {
				EngineImpl.getSingletonInstance().pieceOperator().resetHealingAbilityCounter();
			}
		} else {
			throw new IllegalArgumentException("Invalid ability");
		}
	}

	private void defend(Piece targetPiece) {
		targetPiece.setImmune(true);

	}

	@Override
	@Ensures("abilityCells() != null")
	public Set<Cell> abilityCells() {
		Set<Cell> neighbourCells = new HashSet<>();
		Set<Cell> surroundingEightCells = new HashSet<>();
		List<Piece> activeSharks = engine.pieceOperator().getActiveSharks();

		// Return all the neighbouring cells around other sharks
		for (Piece shark : activeSharks) {
			if (!(shark instanceof DefensiveShark)) {

				// All the neighbour cells around a shark - to be traversed

				surroundingEightCells.addAll(new DiagonalDecorator(new BasicMove()).getValidMove(shark, NEIGHBOURING_DISTANCE));
				Cell sharkPosition = new Cell(shark.getPosition().get("x"), shark.getPosition().get("y"));
				surroundingEightCells.add(sharkPosition);
			}
		}
		// Remove master cell
		surroundingEightCells.remove(EngineImpl.getSingletonInstance().getBoard().getSharkMasterCell());

		// * Add the cell to the return list if the following are true:
		// * Cell is not occupied
		// * Cell is within the board
		// *
		for (Cell possibleCell : surroundingEightCells) {
			if (!possibleCell.getOccupied() && possibleCell.getY() < engine.getBoard().getSize()
					&& possibleCell.getY() >= 0 && possibleCell.getX() < engine.getBoard().getSize()
					&& possibleCell.getX() >= 0) {
				neighbourCells.add(possibleCell);
			}
		}

		return neighbourCells;
	}
	
	@Override
	public Set<Cell> modeCells() {
		// return all possible corner cells that are not occupied
		return null;
	}



	@Override
	public String toString() {
		return String.format("%s", "DefensiveShark");
	}

}