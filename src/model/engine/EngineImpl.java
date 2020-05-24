package model.engine;

import java.io.Serializable;

import model.board.Cell;
import model.board.GameBoard;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.TeamType;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;

/**
 *
 * @author Sefira
 *
 */
public class EngineImpl implements Engine, Serializable {

	private static final long serialVersionUID = -5482363859150486331L;

	private static Engine engine = null;

	/**
	 * @return the singleton instance of the engine
	 */
	public static Engine getSingletonInstance() {
		if (engine == null) {
			engine = new EngineImpl();
		}
		return engine;
	}

	private GameBoard board;

	private GamePiece gamePiece;

	private int totalNumPiece;

	private PieceCommands pieceCommands;

	private GameTurn gameTurn;

	/**
	 * @return the singleton instance of the engine
	 */
	private EngineImpl() {
		totalNumPiece = 6;
		board = new GameBoard(9, false);
		gamePiece = new GamePiece(this);
		pieceCommands = new PieceCommands(this);
		gameTurn = new GameTurn(this);
	}

	@Override
	public GameTurn gameTurn() {
		return gameTurn;
	}

	@Override
	public PieceCommands getPieceCommands() {
		return pieceCommands;
	}

	public GamePiece getPieceOperator() {
		return gamePiece;
	}

	@Override
	public GameBoard gameBoard() {
		return this.board;
	}

	@Override
	public GamePiece pieceOperator() {
		return gamePiece;
	}

	@Override
	public int getTotalNumPiece() {
		return this.totalNumPiece;
	}

	@Override
	public void configBoardSize(int boardSize) {
		this.board = new GameBoard(boardSize, false);
	}

	@Override
	public void configNumPiece(int numPiece) {
		gamePiece = new GamePiece(this);
		if (numPiece == 6) {
			gamePiece.initializeDefaultPiece();
		} else if (numPiece == 4) {
			gamePiece.initialize4Piece();
			totalNumPiece = 4;
		} else if (numPiece == 2) {
			gamePiece.initialize2Piece();
			totalNumPiece = 2;
		}
	}

	@Override
	public void loadGame(EngineImpl e) {
		board = e.gameBoard();
		gamePiece = e.getPieceOperator();
		pieceCommands = e.getPieceCommands();
	}

	private boolean checkPiecesActiveWinningCondition() {
		if (pieceOperator().getActiveEagles().size() == 0) {
			gameTurn.endGame(TeamType.SHARK);
			return true;
		} else if (pieceOperator().getActiveSharks().size() == 0) {
			gameTurn.endGame(TeamType.EAGLE);
			return true;
		}
		return false;
	}

	private boolean checkPieceEnterMasterCellWinningCondition() {
		for (Piece sharks : pieceOperator().getActiveSharks()) {
			Cell cntPos = board.getCell(sharks.getPosition().get("x"), sharks.getPosition().get("y"));
			if (cntPos.equals(board.getEagleMasterCell())) {
				gameTurn.endGame(TeamType.SHARK);
				return true;
			}
		}

		for (Piece eagle : pieceOperator().getActiveEagles()) {
			Cell cntPos = board.getCell(eagle.getPosition().get("x"), eagle.getPosition().get("y"));
			if (cntPos.equals(board.getSharkMasterCell())) {
				gameTurn.endGame(TeamType.EAGLE);
				return true;
			}
		}
		return false;
	}

	/**
	 * call at controllermodelfacade inside updateStateModelForNextTurn()
	 */
	@Override
	public boolean endGame() {
		if (checkPiecesActiveWinningCondition()) {
			return true;
		}
		if (checkPieceEnterMasterCellWinningCondition()) {
			return true;
		}
		return false;
	}
}
