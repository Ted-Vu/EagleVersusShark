package modelcontroller.facade;

import java.util.Map;

import com.google.java.contract.Requires;

import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.engine.EngineImpl;
import model.enumtype.PieceAbility;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.piece.commands.CommandExecutor;
import model.piece.commands.MovePiece;
import model.piece.commands.UseAbility;
import modelcontroller.contract.ControllerModelInterface;

/**
 * @author ted &#38; kevin
 *
 */
public class ControllerModelFacade implements ControllerModelInterface {

	private EngineInterface engine = EngineImpl.getSingletonInstance();
	private CommandExecutor commandExecutor = new CommandExecutor();

	@Override
	@Requires({ "pieceType!=null", "newPos!=null", "newPos.size()>0" })
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, PieceType pieceType) {
		PieceInterface pieceMoved = engine.pieceOperator().getAllPieces().get(pieceType);
		commandExecutor.executeCommand(new MovePiece(newPos.get("x"), newPos.get("y"), pieceMoved));
	}

	@Override
	@Requires({ "teamName!=null" })
	public void updateModelStateForNextTurn(TeamType teamName) {
		EngineImpl.getSingletonInstance().cancelTimer();
		EngineImpl.getSingletonInstance().setActivePlayer(teamName, true);
	}

	@Override
	public void updateModelStateSwapPiece(PieceType affectedPieceEnum) {
		PieceInterface affectedPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(affectedPieceEnum);
		PieceInterface visionaryPiece = EngineImpl.getSingletonInstance().pieceOperator().getAllPieces()
				.get(PieceType.VISIONARYEAGLE);
		commandExecutor.executeCommand(new UseAbility(PieceAbility.SWAP, visionaryPiece, affectedPiece));

	}
}
