package asset;

public enum PieceType {
	ATTACKINGEAGLE {
		@Override
		public String getFileName() {
			return "/asset/AttackingEagle.png";
		}

		@Override
		public String toString() {
			return "AttackingEagle";
		}

		@Override
		public int initialCoordinateX(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int initialCoordinateY(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}
	},

	LEADERSHIPEAGLE {
		@Override
		public String getFileName() {
			return "/asset/LeadershipEagle.png";
		}

		@Override
		public String toString() {
			return "LeadershipEagle";
		}

		@Override
		public int initialCoordinateX(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int initialCoordinateY(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}
	},

	VISIONARYEAGLE {
		@Override
		public String getFileName() {
			return "/asset/VisionaryEagle.png";
		}

		@Override
		public String toString() {
			return "VisionaryEagle";
		}

		@Override
		public int initialCoordinateX(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int initialCoordinateY(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}
	},

	AGGRESSIVESHARK {
		@Override
		public String getFileName() {
			return "/asset/AggressiveShark.png";
		}

		@Override
		public String toString() {
			return "AggressiveShark";
		}

		@Override
		public int initialCoordinateX(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int initialCoordinateY(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

	},

	DEFENSIVESHARK {
		@Override
		public String getFileName() {
			return "/asset/DefensiveShark.png";
		}

		@Override
		public String toString() {
			return "DefensiveShark";
		}

		@Override
		public int initialCoordinateX(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int initialCoordinateY(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}
	},

	HEALINGSHARK {
		@Override
		public String getFileName() {
			return "/asset/HealingShark.png";
		}

		@Override
		public String toString() {
			return "HealingShark";
		}

		@Override
		public int initialCoordinateX(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int initialCoordinateY(int boardSize) {
			// TODO Auto-generated method stub
			return 0;
		}
	};

	private static String eagleTeamName = "eagleplayer";

	private static String sharkTeamName = "sharkplayer";

	public static String getEagleTeamName() {
		return eagleTeamName;
	}

	public static String getSharkTeamName() {
		return sharkTeamName;
	}

	public abstract String getFileName();

	public abstract int initialCoordinateX(int boardSize);

	public abstract int initialCoordinateY(int boardSize);

	public abstract String toString();
}
