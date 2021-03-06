package view.operationview;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.google.java.contract.Requires;

import controller.playinggamecontroller.MakingMovePropertyChangeListener;
import controller.playinggamecontroller.StartGameController;
import model.engine.EngineImpl;
import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 */
public class StatusPanel extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 8787252718705342879L;

	private JButton startButton;
	private JLabel turnLabel;
	private JTextField turnTextField;
	private JLabel timerLabel;
	private JTextField timerTextField;

	private List<SwingWorker<Void, Void>> workerThreads;

	public StatusPanel(ModePanel modePanel) {
		startButton = new JButton("START");
		turnLabel = new JLabel("Turn:");

		turnTextField = new JTextField(10);
		turnTextField.setBackground(new Color(220, 225, 233));
		turnTextField.setEditable(false);
		timerLabel = new JLabel("Timer:");
		timerTextField = new JTextField(10);
		timerTextField.setBackground(new Color(220, 225, 233));
		timerTextField.setEditable(false);

		workerThreads = new ArrayList<SwingWorker<Void, Void>>();

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Status Panel"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		startButton.addActionListener(new StartGameController(this, modePanel));

		JPanel turnLabelPanel = new JPanel();
		turnLabelPanel.add(turnLabel);
		turnLabelPanel.add(turnTextField);
		add(turnLabelPanel);

		JPanel timerPanel = new JPanel();
		timerPanel.add(timerLabel);
		timerPanel.add(timerTextField);
		add(timerPanel);

		JPanel startButtonPanel = new JPanel();
		startButtonPanel.add(startButton);
		add(startButtonPanel);

		PropertyChangeListener[] listeners = EngineImpl.getSingletonInstance().gameTurn().getGameEngineCallback()
				.getPropertyChangeListener();
		for (PropertyChangeListener listener : listeners) {
			if (listener instanceof MakingMovePropertyChangeListener) {
				((MakingMovePropertyChangeListener) listener).injectStatusPanel(this);
			}
		}
	}

	private void cancelTimer() {
		for (SwingWorker<Void, Void> preWorker : workerThreads) {
			preWorker.cancel(true);
		}
		turnTextField.setText("PAUSE GAME");
		timerTextField.setText("PAUSE GAME");
	}

	public void endGameTimer() {
		for (SwingWorker<Void, Void> preWorker : workerThreads) {
			preWorker.cancel(true);
		}
		turnTextField.setText("END GAME");
		timerTextField.setText("END GAME");
	}

	@Override
	@Requires({ "evt!=null" })
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("UpdateBoardPauseGame")) {
			cancelTimer();
		} else if (evt.getPropertyName().equalsIgnoreCase("ResumeGame")) {
			updateTurnLabel((TeamType) evt.getNewValue());
			startCountDown();
		}
	}

	public void startCountDown() {
		for (SwingWorker<Void, Void> preWorker : workerThreads) {
			preWorker.cancel(true);
		}
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 10; i >= 0; --i) {
					timerTextField.setText(i + "");
					Thread.sleep(1000);
				}
				return null;
			}
		};
		workerThreads.add(worker);
		worker.execute();
	}

	@Requires({ "currentPlayer!=null" })
	public void updateTurnLabel(TeamType currentPlayer) {
		turnTextField.setText(currentPlayer.toString());
	}

}
