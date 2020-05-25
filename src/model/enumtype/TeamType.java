package model.enumtype;

import java.awt.Color;

/**
 * @author ted &#38; kevin
 *
 */
public enum TeamType {
	/**
	 * Include: VisionaryEagle, LeadershipEagle, AttackingEagle
	 */
	EAGLE(Color.YELLOW),

	/**
	 * Include: AggressiveShark, DefensiveShark, HealingShark
	 */
	SHARK(Color.BLUE);

	private final Color color;

	TeamType(final Color color) {
		this.color = color;
	}

	public Color color() {
		return color;
	}

	public static TeamType parseTeamType(String animal) {
		TeamType team = null;
		if (animal.equalsIgnoreCase(PieceType.AGGRESSIVESHARK.toString())
				|| animal.equalsIgnoreCase(PieceType.DEFENSIVESHARK.toString())
				|| animal.equalsIgnoreCase(PieceType.HEALINGSHARK.toString())) {
			team = TeamType.SHARK;
		} else if (animal.equalsIgnoreCase(PieceType.ATTACKINGEAGLE.toString())
				|| animal.equalsIgnoreCase(PieceType.LEADERSHIPEAGLE.toString())
				|| animal.equalsIgnoreCase(PieceType.VISIONARYEAGLE.toString())) {
			team = TeamType.EAGLE;
		}
		return team;
	}
}
