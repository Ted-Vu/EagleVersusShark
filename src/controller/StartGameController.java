package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import models.engine.EngineImpl;
import models.player.Player;
import view.operationview.StatusPanel;

public class StartGameController implements ActionListener {

	private StatusPanel statusPanel;

	public StartGameController(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player initialPlayer = EngineImpl.getSingletonInstance().getInitialPlayerActivePlayer();
		if (initialPlayer.getPlayerType().equalsIgnoreCase("sharkplayer")) {
			statusPanel.updateTurnLabel("Shark");
			 statusPanel.startCountDown();
			EngineImpl.getSingletonInstance().setActivePlayerTimer("eagle");

		} else if (initialPlayer.getPlayerType().equalsIgnoreCase("eagleplayer")) {
			statusPanel.updateTurnLabel("Eagle");
			 statusPanel.startCountDown();
			EngineImpl.getSingletonInstance().setActivePlayerTimer("eagle");

		}

		JButton button = (JButton) e.getSource();
		button.setEnabled(false);
	}

}