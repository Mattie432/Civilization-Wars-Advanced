package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import player.Player;
import player.subclasses.AI;
import map.Cell;
import map.Map;
import sound.Sound;
import unit.Unit;
import userInterface.gameInterface.UserInterface_Top;
import userInterface.gameInterface.UserInterface_Bottom;
import userInterface.gameInterface.MapPanel;

import java.awt.Dimension;

/**
 * This is the main class that controls the running of the game.
 * 
 * @author Matt
 */
public class Game {
	public UserInterface_Top uiInterfaceTop;
	public static UserInterface_Bottom uiInterfaceBottom;
	public Map map;
	public MapPanel mapPanel;
	public Player player = null;
	public LinkedList<Player> playerOrder;
	public int currentPlayerOrderNum = 0;
	/* Data used in bar */
	private Timer t = new Timer(1000, null);
	private int day = 1;
	private int gameTernMaxTime = 300;
	private long time = gameTernMaxTime * 1000; // Number of seconds 1000
	public GameType gameType;

	/**
	 * Constructor for the class, this sets up the game depending upon what type
	 * of game you request.
	 * 
	 * @param mapName
	 *            : String - the map to loads name
	 * @param gameType
	 *            : GameType - the gametype to load.
	 * @param playerOrder
	 *            : PlayerOrder - the players to play in the current game.
	 * @param me
	 *            : Player - the player who is this games owner
	 */
	public Game(String mapName, GameType gameType,
			LinkedList<Player> playerOrder, Player me) {

		// sets the player order object = to the one given
		this.playerOrder = playerOrder;
		this.gameType = gameType;
		this.player = me;
		this.player.setGame(this);

		// Set up game depending upon the gametype
		if (gameType == GameType.AItype1vs1) {
			// 1vs1 (1human 1AI)
			AI aiPlayer1 = new AI(this);
			Teams.addAiPlayer();

			Teams.addToEmptyTeam(aiPlayer1);

			this.playerOrder.add(aiPlayer1);
			// set difficulty
			aiPlayer1.setDifficulty(GameType.getDifficulty());

		} else if (gameType == GameType.AItype2vs2) {
			// 2vs2 (1human 2ai)
			AI aiPlayer1 = new AI(this);
			Teams.addAiPlayer();
			AI aiPlayer2 = new AI(this);
			Teams.addAiPlayer();
			AI aiPlayer3 = new AI(this);
			Teams.addAiPlayer();

			Teams.addToEmptyTeam(aiPlayer1);
			Teams.addToEmptyTeam(aiPlayer2);
			Teams.addToEmptyTeam(aiPlayer3);

			this.playerOrder.add(aiPlayer1);
			this.playerOrder.add(aiPlayer2);
			this.playerOrder.add(aiPlayer3);

			// set aiPlayer1 friends with player
			Teams.setTeamFriends(Teams.getTeamOfPlayer(player), aiPlayer1);
			Teams.setTeamFriends(Teams.getTeamOfPlayer(aiPlayer1), player);
			// set aiPlayer2 friends with aiPlayer3
			Teams.setTeamFriends(Teams.getTeamOfPlayer(aiPlayer2), aiPlayer3);
			Teams.setTeamFriends(Teams.getTeamOfPlayer(aiPlayer3), aiPlayer2);

			System.out.println(Teams.getTeamOfPlayer(player));
			System.out.println(Teams.getTeamOfPlayer(aiPlayer1));
			System.out.println(Teams.getTeamOfPlayer(aiPlayer2));
			System.out.println(Teams.getTeamOfPlayer(aiPlayer3));

			// set difficulty
			aiPlayer1.setDifficulty(GameType.getDifficulty());
			aiPlayer2.setDifficulty(GameType.getDifficulty());
			aiPlayer3.setDifficulty(GameType.getDifficulty());
		} else if (gameType == GameType.AItypeFreeForAll) {
			// Free for all (1human 3Ai)

			AI aiPlayer1 = new AI(this);
			Teams.addAiPlayer();
			AI aiPlayer2 = new AI(this);
			Teams.addAiPlayer();
			AI aiPlayer3 = new AI(this);
			Teams.addAiPlayer();

			Teams.addToEmptyTeam(aiPlayer1);
			Teams.addToEmptyTeam(aiPlayer2);
			Teams.addToEmptyTeam(aiPlayer3);

			this.playerOrder.add(aiPlayer1);
			this.playerOrder.add(aiPlayer2);
			this.playerOrder.add(aiPlayer3);

			// set difficulty
			aiPlayer1.setDifficulty(GameType.getDifficulty());
			aiPlayer2.setDifficulty(GameType.getDifficulty());
			aiPlayer3.setDifficulty(GameType.getDifficulty());
		} else if (gameType == GameType.type1vs1) {
			for (int i = 0; i < playerOrder.size(); i++) {
				Teams.addToEmptyTeam(playerOrder.get(i));
			}
			Teams.setMe(Teams.getTeamOfPlayer(me));
		} else if (gameType == GameType.type2vs2) {
			// 2vs2 (human)

		} else if (gameType == GameType.typeFreeForAll) {
			for (int i = 0; i < playerOrder.size(); i++) {
				Teams.addToEmptyTeam(playerOrder.get(i));
			}
			Teams.setMe(Teams.getTeamOfPlayer(me));

		}

		// select map
		map = new Map(90, mapName, this);

		// give all players game object
		for (Player p : playerOrder) {
			p.setGame(this);
		}

		// Create game inteface
		createGameInterface();

		// setup timer
		setupTimer();

		mapPanel.centerToYourTeam();

		Sound s = new Sound();
		s.setTrackFromAlbum("game_music");
		s.play();

	}

	/**
	 * Sets up the timer object that is used for each players turn time.
	 */
	private void setupTimer() {
		uiInterfaceTop.setCurrentPlayer(playerOrder.get(currentPlayerOrderNum)
				.getPlayerName());
		t.setDelay(1000);
		t.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (time >= 0) {
					long s = ((time / 1000) % 60);
					long m = (((time / 1000) / 60) % 60);
					// long h = ((((time / 1000) / 60) / 60) % 60);

					if (s < 10) {
						String s2 = "0" + s;
						uiInterfaceTop.getTimerText()
								.setText(
										uiInterfaceTop.printFormattedTime(m
												+ ":" + s2));
					} else {
						uiInterfaceTop.getTimerText().setText(
								uiInterfaceTop.printFormattedTime(m + ":" + s));
					}

					time -= 1000;
				} else {

					t.stop();
					uiInterfaceTop.getTimerText().setText("DAY ENDED!");
					turnFinshed();
				}
			}
		});

		t.start();
	}

	/**
	 * This creates the windows that is used to display the game to the user.
	 */
	@SuppressWarnings("static-access")
	private void createGameInterface() {
		JFrame frame = new JFrame();
		frame.setMinimumSize(new Dimension(700, 700));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		final MapPanel mapPanel = new MapPanel(map, this);
		this.mapPanel = mapPanel;

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		uiInterfaceTop = new UserInterface_Top(this);
		this.uiInterfaceBottom = new UserInterface_Bottom(this);

		container.add(uiInterfaceTop, BorderLayout.NORTH);

		container.add(mapPanel, BorderLayout.CENTER);

		container.add(uiInterfaceBottom, BorderLayout.SOUTH);

		frame.getContentPane().add(container);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player.updateMoney();

		mapPanel.initialize();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// server.stopServer();
			}

		});

		frame.setVisible(true);
	}

	/**
	 * This returns the player who owns this game. The local client.
	 * 
	 * @return player : Player - the player of the game
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * This method is called when a players turn is finished. It will reset
	 * there units and buildings, increment there economy and move onto the next
	 * player in the playerOrder.
	 */
	public void turnFinshed() {
		mapPanel.attackButtonPressed = false;
		mapPanel.moveButtonPressed = false;
		mapPanel.barracksButtonPressed = false;
		mapPanel.factoryButtonPressed = false;
		mapPanel.resetHighlightedMap();
		// change the player
		currentPlayerOrderNum++;
		// if reached last player return to start
		if (currentPlayerOrderNum >= playerOrder.size()) {
			currentPlayerOrderNum = 0;
			day++;
			uiInterfaceTop.getTheDay().setText(
					uiInterfaceTop.printFormattedDay("" + day));
			// passive money per day
			for (Player p : Teams.getAllPlayers()) {
				p.addMoney(300);
			}
		}
		uiInterfaceTop.setCurrentPlayer(playerOrder.get(currentPlayerOrderNum)
				.getPlayerName());

		// Reset units & buildings
		endDayReset();

		// restart timer
		t.stop();
		time = gameTernMaxTime * 1000;
		t.start();

		moveAI();

		uiInterfaceBottom.setEndDayButton();
	}

	/**
	 * This method will check if the current player is an AI one. If so then it
	 * will ask it to play its turn.
	 */
	private void moveAI() {
		if (playerOrder.get(currentPlayerOrderNum) instanceof AI) {
			AI ai = (AI) playerOrder.get(currentPlayerOrderNum);
			ai.play();
			turnFinshed();
		}
	}

	/**
	 * Resets all of the units movement points.
	 */
	private void endDayReset() {
		// reset units
		for (Cell[] cellX : map.getCells()) {
			for (Cell cellY : cellX) {

				Unit unit = cellY.getUnit();
				if (unit != null) {
					unit.reset();
				}

			}
		}

	}

	/**
	 * Gets the day that the game is on.
	 * @return day : Int - the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the maximun time of each players turn.
	 * @return maxTime : int - seconds of the game time.
	 */
	public int getGameTernMaxTime() {
		return gameTernMaxTime;
	}

	/**
	 * Gets the current player in the game.
	 * @return currentPLayer : Player - the current player.
	 */
	public Player getCurrentPlayer() {
		return playerOrder.get(currentPlayerOrderNum);
	}

	/**
	 * Gets the mapPanel of the game.
	 * @return mapPanel : MapPanel - the mappanel object
	 */
	public MapPanel getMapPanel() {
		return mapPanel;
	}

	/**
	 * Ends the game. Resets the teams and stops all timer events.
	 */
	public void endGame() {
		t.stop();
		Teams.resetTeams();

		for (MouseListener m : mapPanel.getMouseListeners()) {
			mapPanel.removeMouseListener(m);
		}

		uiInterfaceBottom.ended = true;
		uiInterfaceBottom.removeAll();

	}
}
