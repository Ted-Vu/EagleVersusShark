package model.piece.commands;

import java.io.Serializable;
import java.util.Stack;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.contract.Command;
import model.contract.Piece;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.enumtype.TeamType;
import model.piece.AttackingEagle;
import model.piece.HealingShark;
import model.piece.PieceMemento;

/**
 *
 * @author Sefira
 *
 */
public class PieceCommands implements Serializable {

	private static final long serialVersionUID = -1506663538384796618L;

	private EngineImpl engine;
	private Stack<Command> commandHistory = new Stack<Command>();

	public PieceCommands(EngineImpl engine) {
		this.engine = engine;
	}

	protected void addEvt(Command command) {
		commandHistory.push(command);
	}

	/**
	 * @param piece - the piece to be moved
	 * @param newX  - new x position
	 * @param newY  - new y position Generate the pieces and put them on the board
	 */
	@Requires({ "piece != null", "x>=0", "y>=0" })
	@Ensures({ "piece.getPosition().get(\"x\") != null && piece.getPosition().get(\"y\") != null" })
	public void movePiece(Piece piece, int x, int y, boolean isMode) {
		if (isMode && piece instanceof HealingShark && piece.getModeCount() > 0) {
			throw new IllegalArgumentException("Mode is already used");
		}
		engine.gameBoard().removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		engine.gameBoard().addPiece(x, y);
		piece.movePiece(x, y);
		if (isMode)
			piece.modeUsed();
	}

	/**
	 * Recover the previous state of a piece
	 * 
	 * @param piece     - the piece to be reset
	 * @param prevState - previous state of the piece
	 */
	@Requires({ "piece != null", "prevState!=null" })
	protected void replacePieceVersion(Piece piece, PieceMemento prevState) {
		piece.setActive(prevState.isActive());
		piece.setImmune(prevState.isImmune());
		piece.setPosition(prevState.getX(), prevState.getY());
		piece.setModeCount(prevState.getModeCount());

		engine.gameBoard().removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		engine.gameBoard().addPiece(prevState.getX(), prevState.getY());
	}

	/**
	 * Undo prev move/ability use
	 * 
	 * @param undoNum  - the number of times to undo
	 * @param teamType - the team that uses undo
	 */
	@Requires({ "undoNum >= 1", "teamType!=null" })
	protected void undo(int undoNum, TeamType teamType) {

		if (engine.gameTurn().ableToUndo(teamType)) {

			int availableUndo = commandHistory.size() / 2;
			if (availableUndo < 1)
				throw new RuntimeException("Nothing to undo");
			else if (availableUndo < undoNum) {
				throw new IllegalArgumentException("Only able to undo " + availableUndo + " time(s)");
			} else {
				for (int i = 0; i < undoNum * 2; i++) {
					commandHistory.peek().undo();
					commandHistory.pop();
				}
			}
			engine.gameTurn().incrementUndo(teamType);
		} else {
			throw new RuntimeException("You already used undo");
		}
	}

	/**
	 * use the individual ability of the piece
	 * 
	 * @param pieceAbility  - ability name
	 * @param piece         - the chosen piece
	 * @param affectedPiece - the piece that will be affected by this ability
	 * @param isMode        - is this mode or ability usage
	 */
	@Requires({ "pieceAbility != null", "piece!=null", "affectedPiece!=null" })
	protected void useAbility(PieceAbility pieceAbility, Piece piece, Piece affectedPiece, boolean isMode) {
		if (isMode && piece instanceof AttackingEagle && piece.getModeCount() > 0) {
			throw new IllegalArgumentException("Mode is already used");
		}
		piece.useAbility(pieceAbility, piece, affectedPiece);
		if (isMode)
			piece.modeUsed();
	}

}
