package userInterface.gameInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import game.Game;
import game.Teams;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import building.Building;
import building.subclasses.Barracks;
import building.subclasses.Factory;
import map.terrain.Terrain;
import unit.Unit;
import userInterface.menus.Instructions;
import userInterface.menus.MainMenu;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import map.terrain.subclasses.Flat;
import unit.subclasses.Marine;
import unit.subclasses.Tank;

/**
 * Class for the constructor and the methods of the UserInterface_Bottom
 * @author Dhruvil, Alex, Matt
 */
@SuppressWarnings("serial")
public class UserInterface_Bottom extends JPanel {

	private Game game;

	private JButton attack = new JButton("Attack");
	private JButton move = new JButton("Move");
	private JButton endDay = new JButton("End Day");
	private JButton barracks = new JButton("Barracks");
	private JButton factory = new JButton("Factory");
	private JButton marine = new JButton("Marine");
	private JButton tank = new JButton("Tank");

	public Color BLACK = new Color(0, 0, 0);
	public Color WHITE = new Color(255, 255, 255);
	public JLabel minimap;

	public JLabel unitIcon;
	public JPanel gameIconPanel = new JPanel();

	public JPanel rightPanel = new JPanel();

	// public JTextField clickedName = new JTextField();
	public JTextArea textbox = new JTextArea();
	private final JLabel clickedName = new JLabel("New label");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JPanel iconPanel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("New label");
	private final JButton btnHelp = new JButton("Help");

	public boolean ended;

	/**
	 * Paints the background of the bottom userinterface bar with a background image.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage image = drawImage("/imgs/menu/background.jpg");
		int iw = image.getWidth(this);
		int ih = image.getHeight(this);
		if (iw > 0 && ih > 0) {
			for (int x = 0; x < getWidth(); x += iw) {
				for (int y = 0; y < getHeight(); y += ih) {
					g.drawImage(image, x, y, iw, ih, this);
				}
			}
		}
	}

	/**
	 * Constructor for the class. This sets up the initial state of the bottom UI
	 * @param game : Game - the game object
	 */
	public UserInterface_Bottom(final Game game) {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setFocusable(false);
		setFocusTraversalKeysEnabled(false);

		this.game = game;
		this.setBackground(Color.BLACK);
		// clickedName.setEditable(false);

		textbox.setBackground(this.BLACK);
		textbox.setForeground(this.WHITE);
		textbox.setEditable(false);
		rightPanel.setOpaque(false);
		this.rightPanel.setBackground(this.BLACK);

		endDay.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				game.turnFinshed();
				if (MainMenu.client != null) {
					MainMenu.client.endTurn(true);
				}

				if (Teams.comparePlayers(game.getCurrentPlayer(),
						game.getPlayer())) {
					endDay.setEnabled(true);
				} else {
					endDay.setEnabled(false);
				}

			}
		}));
		setLayout(new GridLayout(0, 3, 0, 0));
		panel_2.setOpaque(false);
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);

		add(panel_2);

		this.minimap = new JLabel(new ImageIcon(game.mapPanel.getMiniMap(250,
				250)));
		panel_2.add(minimap);
		panel_1.setOpaque(false);
		add(panel_1);
		panel.setOpaque(false);
		panel_1.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 46, 0 };
		gbl_panel.rowHeights = new int[] { 42, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		GridBagConstraints gbc_gameIconPanel = new GridBagConstraints();
		gbc_gameIconPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_gameIconPanel.gridx = 0;
		gbc_gameIconPanel.gridy = 0;
		gameIconPanel.setOpaque(false);
		panel.add(gameIconPanel, gbc_gameIconPanel);

		this.gameIconPanel.setBackground(Color.RED);
		gameIconPanel.setLayout(new BoxLayout(gameIconPanel, BoxLayout.Y_AXIS));
		clickedName.setAlignmentX(Component.CENTER_ALIGNMENT);
		clickedName.setHorizontalAlignment(SwingConstants.CENTER);

		clickedName.setBackground(this.BLACK);
		clickedName.setForeground(this.WHITE);

		gameIconPanel.add(clickedName);
		iconPanel.setOpaque(false);
		iconPanel.setBackground(Color.GREEN);

		gameIconPanel.add(iconPanel);
		iconPanel.setLayout(new BorderLayout(0, 0));

		iconPanel.add(lblNewLabel, BorderLayout.NORTH);
		this.add(rightPanel);
		// this.add(endDay);

		resetToDefault();

		attack.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.mapPanel.moveButtonPressed = true) {
					game.mapPanel.moveButtonPressed = false;
				}
				game.mapPanel.attackButtonPressed = true;
			}
		}));

		move.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.mapPanel.attackButtonPressed = true) {
					game.mapPanel.attackButtonPressed = false;
				}
				game.mapPanel.moveButtonPressed = true;
			}
		}));

		barracks.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.mapPanel.factoryButtonPressed = true) {
					game.mapPanel.factoryButtonPressed = false;
				}
				game.mapPanel.barracksButtonPressed = true;
			}
		}));

		factory.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.mapPanel.barracksButtonPressed = true) {
					game.mapPanel.barracksButtonPressed = false;
				}
				game.mapPanel.factoryButtonPressed = true;
			}
		}));

		marine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Barracks b = (Barracks) game.mapPanel.leftClickedCell
						.getBuilding();
				recruitMarine(b);
			}
		});

		tank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Factory f = (Factory) game.mapPanel.leftClickedCell
						.getBuilding();
				recruitTank(f);
			}
		});

		btnHelp.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				Instructions instructions = new Instructions();
			}
		});

	}

	/** 
	 * Changes if the End Day button is enabled or disabled
	 */
	public void setEndDayButton() {
		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())) {
			endDay.setEnabled(true);
		} else {
			endDay.setEnabled(false);
		}
		this.repaint();
	}

	/**
	 * Method for checking if a building has space to spawn units
	 * 
	 * @param b : Building - the building to check
	 * @return answer : Boolean - if it has space to build
	 */
	private boolean isSpawnable(Building b) {
		int x = b.getCell().getCellPosX();
		int y = b.getCell().getCellPosY();

		if ((game.map.getCell(x + 1, y).getTerrain() instanceof Flat
				&& game.map.getCell(x + 1, y).getBuilding() == null && game.map
				.getCell(x + 1, y).getUnit() == null)
				|| (game.map.getCell(x - 1, y).getTerrain() instanceof Flat
						&& game.map.getCell(x - 1, y).getBuilding() == null && game.map
						.getCell(x - 1, y).getUnit() == null)
				|| (game.map.getCell(x, y + 1).getTerrain() instanceof Flat
						&& game.map.getCell(x, y + 1).getBuilding() == null && game.map
						.getCell(x, y + 1).getUnit() == null)
				|| (game.map.getCell(x, y - 1).getTerrain() instanceof Flat
						&& game.map.getCell(x, y - 1).getBuilding() == null && game.map
						.getCell(x, y - 1).getUnit() == null)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method for recruiting a Marine
	 * 
	 * @param b : Barracks : Barracks to recruit Marine from
	 */
	private void recruitMarine(Barracks b) {
		if (isSpawnable(b)) {
			if (game.getCurrentPlayer().canAfford(Marine.cost)) {

				b.recruitMarine(game.mapPanel.map, b.getCell(), false);
				game.mapPanel.redrawMap();

				if (MainMenu.client != null) {
					System.out.println("sending recruit marine to server");
					MainMenu.client.sendMoveToServer(b.getCell().getCellPosX(),
							b.getCell().getCellPosY(), 0, 0, "recruitMarine");
				}

			} else {
				game.mapPanel.Notify("You cannot afford to purchase a Marine!");
			}
		} else {
			game.mapPanel.Notify("No space to train another Marine!");
		}
	}

	/**
	 * Method for recruiting a Marine
	 * 
	 * @param x1
	 *            x value of the Barracks to recruit Marine from
	 * @param y1
	 *            y value of the Barracks to recruit Marine from
	 */
	public void recruitMarine(int x1, int y1) {
		Barracks b = (Barracks) game.map.getCell(x1, y1).getBuilding();

		b.recruitMarine(game.mapPanel.map, b.getCell(), false);
		game.mapPanel.redrawMap();
	}

	/**
	 * Method for recruiting a Tank
	 * 
	 * @param f : Factory to recruit Tank from
	 */
	private void recruitTank(Factory f) {
		if (f.numOfTanks < 5) {
			if (isSpawnable(f)) {

				if (game.getCurrentPlayer().canAfford(Tank.cost)) {

					f.recruitTank(game.mapPanel.map, f.getCell(), false);
					game.mapPanel.redrawMap();

					if (MainMenu.client != null) {
						System.out.println("sending recruit tank to server");
						MainMenu.client.sendMoveToServer(f.getCell()
								.getCellPosX(), f.getCell().getCellPosY(), 0,
								0, "recruitTank");
					}

				} else {
					game.mapPanel
							.Notify("You cannot afford to purchase a Tank!");
				}
			} else {
				game.mapPanel.Notify("No space to train another Tank!");
			}
		} else {
			game.mapPanel
					.Notify("You have reached the maximum number of Tanks!");
		}
	}

	/**
	 * Method for recruiting a Tank
	 * 
	 * @param x1
	 *            x value of the Factory to recruit Tank from
	 * @param y1
	 *            y value of the Factory to recruit Tank from
	 */
	public void recruitTank(int x1, int y1) {
		Factory f = (Factory) game.map.getCell(x1, y1).getBuilding();

		f.recruitTank(game.mapPanel.map, f.getCell(), false);
		game.mapPanel.redrawMap();
	}


	/**
	 * Method for creating a context panel for a tank
	 * @param building : Building - the building to display
	 * @return jpanel : JPanel - the pannel to add
	 */
	public JPanel setTankBar(Unit unit) {
		JPanel np = new JPanel();
		np.setBackground(this.BLACK);
		np.setOpaque(false);
		np.add(btnHelp);
		np.add(attack);
		np.add(move);
		np.add(endDay);

		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())
				&& Teams.comparePlayers(game.getCurrentPlayer(),
						unit.getOwner())) {
			attack.setEnabled(true);
			move.setEnabled(true);
		} else {
			attack.setEnabled(false);
			move.setEnabled(false);
		}

		setEndDayButton();

		np.repaint();
		return np;

	}


	/**
	 * Method for creating a context panel for a marine
	 * @param building : Building - the building to display
	 * @return jpanel : JPanel - the pannel to add
	 */
	public JPanel setMarineBar(Unit unit) {
		JPanel np = new JPanel();
		np.setBackground(this.BLACK);
		np.setOpaque(false);
		np.setOpaque(false);
		np.add(btnHelp);
		np.add(attack);
		np.add(move);
		np.add(endDay);

		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())
				&& Teams.comparePlayers(game.getCurrentPlayer(),
						unit.getOwner())) {
			attack.setEnabled(true);
			move.setEnabled(true);
		} else {
			attack.setEnabled(false);
			move.setEnabled(false);
		}

		setEndDayButton();

		np.repaint();
		return np;

	}


	/**
	 * Method for creating a context panel for a terrain tile
	 * @param building : Building - the building to display
	 * @return jpanel : JPanel - the pannel to add
	 */
	public JPanel setTerrainBar(Terrain terrain) {
		JPanel np = new JPanel();
		np.setBackground(this.BLACK);
		np.setOpaque(false);
		np.setOpaque(false);
		np.add(btnHelp);
		np.add(endDay);

		setEndDayButton();

		np.repaint();
		return np;

	}

	/**
	 * Method for creating a context panel for a Base
	 * @param building : Building - the building to display
	 * @return jpanel : JPanel - the pannel to add
	 */
	public JPanel setBaseBar(Building building) {
		JPanel np = new JPanel();
		np.setBackground(this.BLACK);
		np.setLayout(new FlowLayout());
		np.setOpaque(false);
		np.add(btnHelp);
		np.add(barracks);
		np.add(factory);
		np.add(endDay);

		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())
				&& Teams.comparePlayers(game.getCurrentPlayer(),
						building.getOwner())) {
			barracks.setEnabled(true);
			factory.setEnabled(true);
		} else {
			barracks.setEnabled(false);
			factory.setEnabled(false);
		}

		setEndDayButton();

		np.repaint();
		return np;
	}


	/**
	 * Method for creating a context panel for a barracks
	 * @param building : Building - the building to display
	 * @return jpanel : JPanel - the pannel to add
	 */
	public JPanel setBarracksBar(Building building) {
		JPanel np = new JPanel();
		np.setLayout(new FlowLayout());
		np.setBackground(this.BLACK);
		np.setOpaque(false);
		np.add(btnHelp);
		np.add(marine);
		np.add(endDay);

		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())
				&& Teams.comparePlayers(game.getCurrentPlayer(),
						building.getOwner())) {
			marine.setEnabled(true);
		} else {
			marine.setEnabled(false);
		}

		setEndDayButton();

		np.repaint();
		return np;
	}


	/**
	 * Method for creating a context panel for a factory
	 * @param building : Building - the building to display
	 * @return jpanel : JPanel - the pannel to add
	 */
	public JPanel setFactoryBar(Building building) {
		JPanel np = new JPanel();
		np.setLayout(new FlowLayout());
		np.setBackground(this.BLACK);
		np.setOpaque(false);
		np.add(btnHelp);
		np.add(tank);
		np.add(endDay);

		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())
				&& Teams.comparePlayers(game.getCurrentPlayer(),
						building.getOwner())) {
			tank.setEnabled(true);
		} else {
			tank.setEnabled(false);
		}

		setEndDayButton();

		np.repaint();
		return np;
	}

	/**
	 * Method for updating the context bar
	 * 
	 * @param unit : Unit - the unit which the panel shall be for
	 */
	public void clickedUnit(Unit unit) {

		reset();

		clickedName.setText(unit.getName());
		clickedName.setOpaque(false);
		gameIconPanel.add(clickedName);

		if (unit.getName().equals("Tank") || unit.getName().equals("Marine")) {

			String path = "/imgs/units/" + unit.getName() + ".png";
			if (unit.getName() == "Marine") {
				path = "/imgs/units/Marines.png";
			}

			this.unitIcon = new JLabel(new ImageIcon(drawImage(path)));
			gameIconPanel.add(iconPanel);
			iconPanel.add(unitIcon);
			gameIconPanel.repaint();

			textbox.setText("Health: " + unit.getCurrentHealth() + "/"
					+ unit.getBaseHealth() + "\n" + "Attack Damage: "
					+ unit.getCurrentAttackDamage() + "/"
					+ unit.getBaseAttackDamage() + "\n" + "Units: "
					+ unit.getCurrentNumberUnitsInGroup() + "/"
					+ unit.getBaseNumberUnitsInGroup() + "\n" + "Moves: "
					+ unit.getCurrentMoveRange() + "/"
					+ unit.getBaseMoveRange() + "\n" + "Coordinates: " + "("
					+ unit.getCell().getCellPosX() + ","
					+ unit.getCell().getCellPosY() + ")" + "\n" + "Owner: "
					+ unit.getOwner().getPlayerName());
			textbox.setOpaque(false);
			gameIconPanel.add(textbox);

		}

		if (unit.getName() == "Tank") {
			rightPanel.add(setTankBar(unit));
			this.add(rightPanel);

		} else if (unit.getName() == "Marine") {
			rightPanel.add(setMarineBar(unit));
			this.add(rightPanel);
		} else {
			System.out.println("No switch found for unit: " + unit.getName());
		}

		this.revalidate();
	}

	/**
	 * Method for updating the context bar to a terrain
	 * 
	 * @param terrain : Terrain - the terrain which the panel shall be for
	 */
	public void clickedTerrain(Terrain terrain) {

		reset();
		String path = "/imgs/terrains/" + terrain.getName() + ".png";
		this.unitIcon = new JLabel(new ImageIcon(drawImage(path)));
		unitIcon.setBounds(0, 85, 100, 100);
		clickedName.setText(terrain.getName());
		gameIconPanel.add(clickedName);
		gameIconPanel.add(iconPanel);
		iconPanel.add(unitIcon);
		gameIconPanel.repaint();

		// Generate a list of all the allowed units
		String allowedUnitsText = new String();
		List<Unit> units = terrain.getAllowedUnits();

		int n = 1;
		for (Unit unit : units) {
			if (n < units.size()) {
				allowedUnitsText += " " + unit.getName() + ",";
			} else {
				allowedUnitsText += " " + unit.getName();
			}

			n++;
		}

		// Update the text box
		textbox.setText("Terrain: " + terrain.getName() + "\n"
				+ "Attack Buffer: " + terrain.getAttackBuff() + "\n"
				+ "Health Buffer: " + terrain.getHealthBuff() + "\n"
				+ "Move Buffer: " + terrain.getMoveBuff() + "\n"
				+ "Allowed Units: " + allowedUnitsText);
		textbox.setOpaque(false);

		// Add it to the bar
		gameIconPanel.add(textbox);

		rightPanel.add(setTerrainBar(terrain));

		this.add(rightPanel);
		this.revalidate(); // Not sure why but this works...

	}

	/**
	 * Method for updating the context bar for a building
	 * 
	 * @param building : Building - the building which the panel shall be for
	 */
	public void clickedBuilding(Building building) {
		reset();

		String path = "/imgs/buildings/" + building.getName() + ".png";
		this.unitIcon = new JLabel(new ImageIcon(drawImage(path)));

		clickedName.setText(building.getName());
		gameIconPanel.add(clickedName);
		gameIconPanel.add(iconPanel);
		iconPanel.add(unitIcon);
		gameIconPanel.repaint();

		textbox.setText("Health: " + building.getCurrentHealth() + "/"
				+ building.getBaseHealth() + "\n" + "Coordinates: " + "("
				+ building.getCell().getCellPosX() + ","
				+ building.getCell().getCellPosY() + ")" + "\n" + "Owner: "
				+ building.getOwner().getPlayerName());
		textbox.setOpaque(false);
		gameIconPanel.add(textbox);

		// this.add(gameIconPanel);
		rightPanel.setOpaque(false);

		if (building.getName() == "Base") {
			rightPanel.add(setBaseBar(building));
		}

		if (building.getName() == "Barracks") {
			rightPanel.add(setBarracksBar(building));
		}

		if (building.getName() == "Factory") {
			rightPanel.add(setFactoryBar(building));
		}
		rightPanel.setOpaque(false);

		this.add(rightPanel);

		this.revalidate();

	}

	/**
	 * Generic method to reset the context bar (use resetToDefault to also fill
	 * with default items)
	 */
	public void reset() {
		gameIconPanel.removeAll();
		iconPanel.removeAll();

		// Empty current panel
		rightPanel.removeAll();
		rightPanel.setOpaque(false);
		rightPanel.repaint();
		this.remove(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
	}

	/**
	 * Generic method to reset the context bar to it's default
	 */
	public void resetToDefault() {
		reset();
		if (ended == false) {
			JPanel np = new JPanel();
			np.setOpaque(false);
			np.setLayout(new FlowLayout());

			this.setVisible(false);
			np.add(btnHelp);
			this.add(rightPanel);
			np.add(endDay);
			this.setVisible(true);
			rightPanel.add(np);
		}

		if (Teams.comparePlayers(game.getCurrentPlayer(), game.getPlayer())) {
			endDay.setEnabled(true);
		} else {
			endDay.setEnabled(false);
		}

	}

	/** 
	 * Generic method to reset the context bar (use resetToDefault to also fill
	 * with default items)
	 * 
	 * @param path : String - path to the image to draw
	 * 
	 * @return image : BufferedImage - of the path provided
	 */
	private BufferedImage drawImage(String path) {

		BufferedImage originalImage = new BufferedImage(85, 85,
				BufferedImage.TYPE_INT_RGB);
		try {
			originalImage = ImageIO.read(getClass().getResource(path));
		} catch (IOException e1) {
			System.out.println("Error getting map image!");
		} catch (IllegalArgumentException e2) {
			System.out.println("No image found");
		}

		return originalImage;
	}

}
