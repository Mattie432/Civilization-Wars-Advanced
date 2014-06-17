package userInterface.gameInterface;

import game.Game;
import game.Teams;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import player.Player;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.BoxLayout;

import java.io.IOException;

import javax.swing.border.BevelBorder;

/**
 * Class for the constructor and methods of the UserInterface_Top
 * 
 * @author Dhruvil, Alex, Matt
 */
@SuppressWarnings("serial")
public class UserInterface_Top extends JPanel {

	@SuppressWarnings("unused")
	private Game game;

	/* Stuff that gets prepended to variable data */
	private String dayPrepend = "Day: ";
	private String timerPerpend = "Time Left: ";

	/* Background of top bar */
	private Color bgColour = new Color(0, 0, 0);
	@SuppressWarnings("unused")
	private Color textColour = new Color(255, 255, 255);

	/* Some fields */
	private JLabel theDay = new JLabel(printFormattedDay("1"));
	private JLabel timerText = new JLabel(printFormattedTime("0"));
	private JLabel playerName = new JLabel("Current Player :"); // Change to
	private JLabel ally = new JLabel("Ally Player:");
	// something
	// like
	// getPlayer().getName()
	private final JLabel playerMoney = new JLabel("Player Money : <dynamic>");
	private final JLabel lblYourTeam = new JLabel("Your Colour: ");
	private final JLabel lblTeamColor = new JLabel("");
	private final JPanel panel_2 = new JPanel();

	/**
	 * Constructor for the class, this sets up the bars initial state
	 * @param game : Game - the game object
	 */
	public UserInterface_Top(final Game game) {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		this.setLayout(new GridLayout(1, 10));
		this.setBackground(bgColour);
		this.game = game;
		timerText.setBackground(bgColour);
		timerText.setForeground(Color.WHITE);
		add(timerText);
		theDay.setBackground(bgColour);
		theDay.setForeground(Color.WHITE);
		add(theDay);
		playerMoney.setForeground(Color.WHITE);
		playerMoney.setBackground(Color.BLACK);

		add(playerMoney);
		playerName.setBackground(bgColour);
		playerName.setForeground(Color.WHITE);
		add(playerName);
		playerName.setText("Current player : "
				+ game.playerOrder.get(game.currentPlayerOrderNum)
						.getPlayerName());
		ally.setBackground(bgColour);
		ally.setForeground(Color.WHITE);
		add(ally);

		String allyPlayer = "None";

		for (Player p : Teams.getTeamFriends(Teams.getMe())) {
			if (!p.getPlayerName().equals(game.getPlayer().getPlayerName())) {
				allyPlayer = p.getPlayerName();
			}
		}

		ally.setText("Ally Player : " + allyPlayer);
		panel_2.setOpaque(false);
		panel_2.setBackground(Color.BLACK);
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_2.add(lblYourTeam);
		lblYourTeam.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblYourTeam.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourTeam.setForeground(Color.WHITE);
		lblTeamColor.setBackground(Teams.getTeamColor(Teams
				.getTeamOfPlayer(game.getPlayer())));
		lblTeamColor.setOpaque(true);
		panel_2.add(lblTeamColor);
		lblTeamColor.setVerifyInputWhenFocusTarget(false);
		lblTeamColor.setInheritsPopupMenu(false);
		lblTeamColor.setRequestFocusEnabled(false);
		lblTeamColor.setSize(new Dimension(20, 20));
		lblTeamColor.setMinimumSize(new Dimension(20, 20));
		lblTeamColor.setPreferredSize(new Dimension(20, 20));
		lblTeamColor.setMaximumSize(new Dimension(20, 20));

	}

	/**
	 * Sets the current player name to the parameter
	 * @param playeName : String - the player name
	 */
	public void setCurrentPlayer(final String playeName) {
		playerName.setText("Current Player: " + playeName);
		playerName.setForeground(Teams.getTeamColor(Teams
				.getTeamOfPlayer(playeName)));
		playerName.repaint();
	}

	/**
	 * Gets the string to display on the bar
	 * @param day : String - the day
	 * @return day : String - the string to print on the topbar
	 */
	public String printFormattedDay(String day) {
		return dayPrepend + day;
	}

	/**
	 * Gets the string to display on the bar
	 * @param time : String - the time
	 * @return time : String - the string to print on the topbar
	 */
	public String printFormattedTime(String time) {
		return timerPerpend + time;
	}

	/**
	 * Gets the current day
	 * @return day : JLabel - the label displaying the day
	 */
	public JLabel getTheDay() {
		return theDay;
	}

	/**
	 * Sets the day label
	 * @param theDay : JLabel - the day jlabel
	 */
	public void setTheDay(JLabel theDay) {
		this.theDay = theDay;
	}

	/**
	 * Gets the timers label
	 * @return label : JLabel - the label of the timer
	 */
	public JLabel getTimerText() {
		return timerText;
	}

	/**
	 * Sets the text of the timer jlabel
	 * @param timerText : Jlabel - label to set text of
	 */
	public void setTimerText(JLabel timerText) {
		this.timerText = timerText;
	}

	/**
	 * Gets the player names jlabel.
	 * @return playerName : JLabel - the player names jlabel
	 */
	public JLabel getPlayerName() {
		return playerName;
	}

	/**
	 * Sets the player names jlabel
	 * @param playerName : JLabel - the playername jlabel
	 */
	public void setPlayerName(JLabel playerName) {
		this.playerName = playerName;
	}

	/**
	 * Updates the players money label
	 * @param amount : Int - the money the player has
	 */
	public void setPlayerMoney(int amount) {
		this.playerMoney.setText("Player Money: " + amount);
	}

	/**
	 * Draws the background of the panel with a tiled image.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		BufferedImage originalImage = new BufferedImage(85, 85,
				BufferedImage.TYPE_INT_RGB);
		try {
			originalImage = ImageIO.read(getClass().getResource(
					"/imgs/menu/background.jpg"));
		} catch (IOException e1) {
			System.out.println("Error getting map image!");
		} catch (IllegalArgumentException e2) {
			System.out.println("No image found");
		}

		int iw = originalImage.getWidth(this);
		int ih = originalImage.getHeight(this);
		if (iw > 0 && ih > 0) {
			for (int x = 0; x < getWidth(); x += iw) {
				for (int y = 0; y < getHeight(); y += ih) {
					g.drawImage(originalImage, x, y, iw, ih, this);
				}
			}
		}
	}

}