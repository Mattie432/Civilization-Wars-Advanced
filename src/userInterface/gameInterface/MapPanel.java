package userInterface.gameInterface;

import building.subclasses.Base;
import game.Game;
import game.Teams;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import unit.subclasses.Marine;
import unit.subclasses.Tank;
import map.Cell;
import map.Map;

/**
 * This is the panel to use for the map display.
 * 
 * @author Dhruvil, Matt
 */
@SuppressWarnings("serial")
public class MapPanel extends JPanel {

	private Timer timer = new Timer();
	public javax.swing.Timer animationTimer = new javax.swing.Timer(1000, null);
	private BufferedImage mapImage_View;
	private BufferedImage mapImage_UnitAndBuildingsScaled;
	private BufferedImage mapImage_UnitAndBuildingsScaledHighlighted;
	private BufferedImage mapImage_Scaled;
	private BufferedImage mapImage_Original;
	private BufferedImage mapImage_Minimap;
	private int minimapSize;
	protected int movementDistance = 10;
	protected float zoom = 1;
	public Map map;
	protected Point viewPort;
	public Point mousePoint = new Point();
	protected Cell leftClickedCell = null;
	protected Cell rightClickedCell = null;
	protected Cell selectedCell = null;
	protected float scaledCellWidth;
	protected boolean mouseInsidePanel = true;
	public boolean moveButtonPressed = false;
	public boolean attackButtonPressed = false;
	public boolean barracksButtonPressed = false;
	public boolean factoryButtonPressed = false;
	public Game game;
	boolean debug = false;
	public static MapPanel_MouseListener listener;

	/**
	 * Constructor for the class, sets the initial view of the map.
	 * 
	 * @param map
	 *            : Map - the map in use
	 */
	public MapPanel(Map map, Game game) {
		this.game = game;
		// Scrolling movement timer
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (mapImage_Scaled != null && viewPort != null
						&& mouseInsidePanel) {
					moveIfInBounds(checkIfInBounds(mousePoint));
				}
			}
		}, 1000, 25);

		// Adds mouse listeners to panel
		listener = new MapPanel_MouseListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);

		// adds keylisteners to panel
		new MapPanel_KeyPress(this);

		// Creates the map
		this.map = map;
		// Read map image from file
		try {
			mapImage_Original = ImageIO.read(getClass().getResource(
					"/imgs/maps/" + map.getMapName() + ".jpg"));
		} catch (IOException e1) {
			System.out.println("Error getting map image!");
		}

		setScaledCellDimensionsPx(map.getCellDimensionsPx());
		scaleMap();

		animationTimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeCharacter();
				redrawMap();
				repaint();
			}
		});
		animationTimer.start();
	}

	/**
	 * Called at first launch. Does the options that need the panel to be
	 * rendered in order to work.
	 */
	public void initialize() {
		viewPort = getMidPointOfMap();
		redrawMap();
		repaint();
		this.requestFocus();
	}

	/**
	 * Gets the mid point of the map image.
	 * 
	 * @return point : Point - the mid point of the map.
	 */
	private Point getMidPointOfMap() {
		// calculate midpoint of map
		int midPointX = ((mapImage_UnitAndBuildingsScaled.getWidth() / 2) - (this
				.getWidth() / 2));
		int midPointY = ((mapImage_UnitAndBuildingsScaled.getHeight() / 2) - (this
				.getHeight() / 2));

		return new Point(midPointX, midPointY);
	}

	/**
	 * Getter method for the zoom level.
	 * 
	 * @return zoom : Float - the zoom scaler
	 */
	public float getZoom() {
		return zoom;
	}

	/**
	 * Paints the components to the JPanel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMapAsBackground(g);
		drawCellNumbers();
	}

	/**
	 * Scales the map to the scaler provided as a parameter. This updates the
	 * scaledMapImage and updates the unit placement on the map.
	 * 
	 * @param scaleFactor
	 *            : Float - the scale factor
	 */
	protected void scaleMap() {

		// scaled width & height of the map
		int scaledMapDimensions = (int) (mapImage_Original.getHeight() * getZoom());
		setScaledCellDimensionsPx((map.getCellDimensionsPx() * getZoom()));

		// scales the image
		mapImage_Scaled = new BufferedImage(scaledMapDimensions,
				scaledMapDimensions, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) mapImage_Scaled.getGraphics();

		int newW = (int) (mapImage_Original.getWidth() * getZoom());
		int newH = (int) (mapImage_Original.getHeight() * getZoom());
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		// draws scaled image
		g.drawImage(mapImage_Original, 0, 0, newW, newH, null);
		g.dispose();

		mapImage_UnitAndBuildingsScaledHighlighted = new BufferedImage(
				scaledMapDimensions, scaledMapDimensions,
				BufferedImage.TYPE_INT_RGB);
		mapImage_UnitAndBuildingsScaledHighlighted.getGraphics().drawImage(
				mapImage_Scaled, 0, 0, null);

		// draw units & buildings onto scaled map image
		if (mapImage_Scaled != null) {
			mapImage_UnitAndBuildingsScaled = new BufferedImage(
					scaledMapDimensions, scaledMapDimensions,
					BufferedImage.TYPE_INT_BGR);

			mapImage_UnitAndBuildingsScaled.getGraphics().drawImage(
					mapImage_Scaled, 0, 0, null);
			// drawBuildingsAndUnits(mapImage_UnitAndBuildingsScaled.getGraphics());
		} else {
			System.err
					.println("Cannot draw units & buildings \nNull scaled map image");
		}

		drawCellNumbers();
	}

	/**
	 * Draws the background image as the map, the image should be named as the
	 * mapname.jpg and is located inside the imgs/maps/ folder
	 * 
	 * @param g
	 *            : Graphics - Graphics to draw with
	 */
	private void drawMapAsBackground(Graphics g) {
		g.drawImage(getView(), 0, 0, this);
	}

	/**
	 * Calculates the viewable area of the map based on the panels size.
	 * 
	 * @return image : BufferedImage - background image of map
	 */
	private BufferedImage getView() {

		if (this.getWidth() > 0 && this.getHeight() > 0) {
			// maps size in pixels
			int mapSize = (mapImage_Scaled.getHeight());

			// checks new position is valid
			if (viewPort.x + this.getWidth() > mapSize) {
				int moveXBy = viewPort.x + this.getWidth() - mapSize;
				moveView(-moveXBy, 0);
			}

			// checks new position is valid
			if (viewPort.y + this.getHeight() > mapSize) {
				int moveYBy = viewPort.y + this.getHeight() - mapSize;
				moveView(0, -moveYBy);
			}

			int windowHeight = this.getHeight();
			int windowWidth = this.getWidth();
			// if window width > map image
			if (this.getHeight() > mapImage_Scaled.getHeight()) {
				windowHeight = mapImage_Scaled.getHeight();
				viewPort.y = 0;
			}
			if (this.getWidth() > mapImage_Scaled.getWidth()) {
				windowWidth = mapImage_Scaled.getWidth();
				viewPort.x = 0;
			}

			// get subimage
			mapImage_View = null;
			try {
				mapImage_View = mapImage_UnitAndBuildingsScaled.getSubimage(
						viewPort.x, viewPort.y, windowWidth, windowHeight);
			} catch (Exception e) {
				System.err.println("Error creating subview!");
			}
		}
		return mapImage_View;

	}

	/**
	 * Moves the screens viewport co-ordinats for the top left corner.
	 * 
	 * @param x
	 *            : int - ammount to move along X
	 * @param y
	 *            : int - ammount to move along Y
	 */
	protected void moveView(int x, int y) {
		boolean xTest = (viewPort.x + x + this.getWidth() <= mapImage_Scaled
				.getWidth() && viewPort.x + x >= 0);
		// Checks new position is valid
		if (xTest) {
			// changes position.
			viewPort.x = viewPort.x + x;
		}
		boolean yTest = (viewPort.y + y + this.getHeight() <= mapImage_Scaled
				.getHeight() && viewPort.y + y >= 0);
		if (yTest) {
			viewPort.y = viewPort.y + y;
		}

	}

	/**
	 * Draws gridlines on the panel with the given cell width.
	 * 
	 * @param g
	 *            : Graphics - the graphics object
	 * @param cellWidthHeight
	 *            : int - the width and height of the cells
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	private void drawGridLines(Graphics g, int cellWidthHeight) {
		g.setColor(Color.BLACK);
		int numRows = this.size().height / cellWidthHeight;
		int numColumns = this.size().width / cellWidthHeight;

		for (int rows = 0; rows <= numRows; rows = rows + 1) {
			g.drawLine(0, rows * cellWidthHeight, numColumns * cellWidthHeight,
					rows * cellWidthHeight);
		}
		for (int columns = 0; columns <= numColumns; columns = columns + 1) {
			g.drawLine(columns * cellWidthHeight, 0, columns * cellWidthHeight,
					numRows * cellWidthHeight);
		}
	}

	/**
	 * Gets the scaled cell dimensions. This is the width and height of a cell
	 * after the scaler factor has been applied
	 * 
	 * @return width : int - scaled cel width
	 */
	public float getScaledCellDimensionsPx() {
		return scaledCellWidth;
	}

	/**
	 * Sets teh scaled cell width and height
	 * 
	 * @param cellWidth
	 *            : float - cell width & height
	 */
	private void setScaledCellDimensionsPx(float cellWidth) {
		this.scaledCellWidth = cellWidth;
	}

	/**
	 * Draws the units and buildings onto the graphics provided.
	 * 
	 * @param g
	 *            : Grapgics - the graphics object to use
	 */
	private void drawBuildingsAndUnits(Graphics g) {
		for (int x = 0; x < map.getNumberOfCellsAcross(); x++) {
			for (int y = 0; y < map.getNumberOfCellsAcross(); y++) {
				Cell c = map.getCell(x, y);
				c.draw(g, getScaledCellDimensionsPx(), this);
			}
		}

	}

	/**
	 * Redraws the map with units, buildings.
	 */
	public void redrawMap() {
		// draw units & buildings onto scaled map image
		if (mapImage_UnitAndBuildingsScaled != null) {
			int scaledMapDimensions = (int) (mapImage_Original.getHeight() * getZoom());
			mapImage_UnitAndBuildingsScaled = new BufferedImage(
					scaledMapDimensions, scaledMapDimensions,
					BufferedImage.TYPE_INT_RGB);

			// draw highlighted map to map
			mapImage_UnitAndBuildingsScaled.getGraphics().drawImage(
					mapImage_UnitAndBuildingsScaledHighlighted, 0, 0, null);
			drawBuildingsAndUnits(mapImage_UnitAndBuildingsScaled.getGraphics());

			// resetHighlightedMap();

			// draw minimap
			mapImage_Minimap.getGraphics().drawImage(
					mapImage_UnitAndBuildingsScaled, 0, 0, minimapSize,
					minimapSize, null);

		} else {
			System.err
					.println("Cannot draw units & buildings \nNull scaled map image");
		}

		repaint();
	}

	/**
	 * Resets the highlighted cells on the map image.
	 */
	public void resetHighlightedMap() {
		// reset hilighted map
		mapImage_UnitAndBuildingsScaledHighlighted = new BufferedImage(
				mapImage_Scaled.getWidth(), mapImage_Scaled.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		mapImage_UnitAndBuildingsScaledHighlighted.getGraphics().drawImage(
				mapImage_Scaled, 0, 0, null);
	}

	/**
	 * Gets a bufferedImage to use as the minimap. This is drawn to the scale
	 * provided
	 * 
	 * @param width
	 *            : Int - the width of the minimap
	 * @param height
	 *            : Int - the height of the minimap
	 * @return minimap : BufferedImage - the minimap
	 */
	public BufferedImage getMiniMap(int width, int height) {
		minimapSize = width;
		mapImage_Minimap = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		mapImage_Minimap.getGraphics().drawImage(
				mapImage_UnitAndBuildingsScaled, 0, 0, width, height, null);

		return mapImage_Minimap;
	}

	/**
	 * This method moves the screen in the direction provided.
	 * 
	 * @param s
	 *            : String - direction to move <i>(top, bottom, left, right)</i>
	 */
	private void moveIfInBounds(String s) {
		if (s == "left") {
			animationTimer.stop();
			invalidate();
			moveView(-movementDistance, 0);
			repaint();
			animationTimer.start();
		} else if (s == "right") {
			animationTimer.stop();
			invalidate();
			moveView(movementDistance, 0);
			repaint();
			animationTimer.start();
		} else if (s == "top") {
			animationTimer.stop();
			invalidate();
			moveView(0, -movementDistance);
			repaint();
			animationTimer.start();
		} else if (s == "bottom") {
			animationTimer.stop();
			invalidate();
			moveView(0, movementDistance);
			repaint();
			animationTimer.start();
		}
	}

	/**
	 * This method checks if the mouse pointer is near the edge of the screen.
	 * If so then it moves the map.
	 * 
	 * @param p
	 *            : Point - mouse location
	 * @return string : String - the direction to move in <i>(top, bottom, left,
	 *         right, null)</i>
	 */
	private String checkIfInBounds(Point p) {
		int borderSize = 25;
		// right side
		if (p.x >= this.getWidth() - borderSize && p.x <= this.getWidth()) {
			// is in right side
			return "right";
		} else if (p.x >= 0 && p.x <= borderSize) {
			// is in left side
			return "left";
		} else if (p.y >= 0 && p.y <= 10) {
			// is in top side
			return "top";
		} else if (p.y <= this.getHeight()
				&& p.y >= this.getHeight() - borderSize) {
			// is in bottom side
			return "bottom";
		} else {
			return null;
		}
	}

	/**
	 * Gets the center point of the current view
	 * 
	 * @return point : Point - center point
	 */
	@SuppressWarnings("unused")
	private Point getCenterViewPointOnMap() {
		Point p = new Point();

		p.x = viewPort.x + (this.getWidth() / 2);
		p.y = viewPort.y + (this.getHeight() / 2);

		return p;
	}

	/**
	 * Draws the cell number into every cell on the map.
	 */
	private void drawCellNumbers() {
		if (debug) {
			for (int x = 0; x < map.getNumberOfCellsAcross(); x++) {
				for (int y = 0; y < map.getNumberOfCellsAcross(); y++) {
					Graphics g = mapImage_UnitAndBuildingsScaled.getGraphics();
					String s = x + ", " + y;
					char[] data = s.toCharArray();
					g.drawChars(
							data,
							0,
							data.length,
							(int) (x * getScaledCellDimensionsPx()),
							(int) (y * getScaledCellDimensionsPx() + getScaledCellDimensionsPx()));
				}
			}
		}
	}

	/**
	 * Finds and highlights the moveable cells on the map. 
	 * @param cell : Cell - the cell to start the show moveable command in.
	 */
	public void showMoveableCells(Cell cell) {

		try {

			ArrayList<Cell> t = cell.getUnit().getMoveableCells(map);
			highlightCells(t.toArray(new Cell[t.size()]), new Color(255, 255,
					255, 64));
			Cell[] cs = new Cell[1];
			cs[0] = cell;
			Color teamColorOLD = Teams.getTeamColor(Teams.getTeamOfPlayer(cell
					.getUnit().getOwner()));
			Color teamColor = new Color(teamColorOLD.getRed(),
					teamColorOLD.getGreen(), teamColorOLD.getBlue(), 50);
			highlightCells(cs, teamColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds and highlights the attackable cells on the map image.
	 * @param cell : Cell - the cell to start the search in.
	 */
	public void showAttackableCells(Cell cell) {

		try {

			ArrayList<Cell> t = cell.getUnit().getAttackableCells(map);
			highlightCells(t.toArray(new Cell[t.size()]), new Color(186, 0, 0,
					64));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Finds and highlights the buildable cells on the map image.
	 * @param cell : Cell - the cell to start the search in.
	 */
	public void showBuildableCells(Base base) {

		try {

			ArrayList<Cell> t = base.getBuildableCells(map);
			highlightCells(t.toArray(new Cell[t.size()]), new Color(255, 255,
					255, 64));
			Cell[] cs = new Cell[1];
			cs[0] = base.getCell();
			Color teamColorOLD = Teams.getTeamColor(Teams.getTeamOfPlayer(base
					.getOwner()));
			Color teamColor = new Color(teamColorOLD.getRed(),
					teamColorOLD.getGreen(), teamColorOLD.getBlue(), 50);
			highlightCells(cs, teamColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hightlights the cells provided as arguments on the map image.
	 * @param cells : Cell[] - cells to highlight
	 * @param color : Color - the color to highlight them in
	 */
	public void highlightCells(Cell[] cells, Color color) {
		Graphics g = mapImage_UnitAndBuildingsScaledHighlighted.getGraphics();

		// Graphics g = unitAndBuildingsScaledMapImage.getGraphics();

		for (Cell cell : cells) {
			int xPos = (int) (cell.getCellPosX() * getScaledCellDimensionsPx());
			int yPos = (int) (cell.getCellPosY() * getScaledCellDimensionsPx());

			g.setColor(color);
			g.fillRect(xPos, yPos, (int) getScaledCellDimensionsPx(),
					(int) getScaledCellDimensionsPx());
		}
	}

	/**
	 * Centers the screen to your teams base.
	 */
	public void centerToYourTeam() {
		if (Teams.getTeamOfPlayer(game.getPlayer()) == Teams.BLUE) {
			// top left
			viewPort.x = 0;
			viewPort.y = 0;
		} else if (Teams.getTeamOfPlayer(game.getPlayer()) == Teams.GREEN) {
			// top right
			viewPort.x = map.getNumberOfCellsAcross()
					* map.getCellDimensionsPx() - this.getWidth();
			viewPort.y = 0;

		} else if (Teams.getTeamOfPlayer(game.getPlayer()) == Teams.ORANGE) {
			// bottom left
			viewPort.x = 0;
			viewPort.y = map.getNumberOfCellsAcross()
					* map.getCellDimensionsPx() - this.getHeight();

		} else if (Teams.getTeamOfPlayer(game.getPlayer()) == Teams.RED) {
			// bottom right
			viewPort.x = map.getNumberOfCellsAcross()
					* map.getCellDimensionsPx() - this.getWidth();
			;
			viewPort.y = map.getNumberOfCellsAcross()
					* map.getCellDimensionsPx() - this.getHeight();

		}
	}

	/**
	 * Gets the cropped image that will be displayed to the user.
	 * @return map : BufferedImage - the image inside the viewport
	 */
	public BufferedImage getMapView() {
		return this.mapImage_View;
	}

	/**
	 * Changes the image of all units to the next one.
	 * This moves each image onto the next frame of the animation
	 */
	public void changeCharacter() {
		Cell[][] cells = map.getCells();

		int x = 0;
		int y = 0;
		for (x = 0; x < cells.length; x++) {
			for (y = 0; y < cells[x].length; y++) {
				if (map.getCell(x, y).getUnit() instanceof Tank) {
					((Tank) map.getCell(x, y).getUnit()).nextImage();
				} else if (map.getCell(x, y).getUnit() instanceof Marine) {
					((Marine) map.getCell(x, y).getUnit()).nextImage();

				}
			}
		}
	}

	int trans = 0;
	int count = 0;
	boolean notify = false;

	/**
	 * Draws a notification message on the screen to the user.
	 * @param message : String - the message to display
	 */
	public void Notify(final String message) {
		if (!notify) {
			trans = 0;
			count = 0;
			final int time = 10;
			final javax.swing.Timer t = new javax.swing.Timer(time, null);
			t.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (trans >= 235) {
						if (count == (3000 / time)) {
							t.stop();
							repaint();
							notify = false;
						} else {
							count += 1;
							// drawNotification(message, trans);
							notify = true;
						}
					} else {
						trans += 20;
						drawNotification(message, trans);
						notify = true;
					}
				}
			});

			t.start();

		}
	}

	/**
	 * Draws a message to the screen.
	 * @param message : String - message to display
	 * @param transparency : int - the messages transparency.
	 */
	private void drawNotification(String message, int transparency) {
		Graphics g = this.getGraphics();
		g.setFont(new Font("TimesRoman", Font.BOLD, 36));
		g.setColor(new Color(255, 255, 255, transparency));

		int stringLen = (int) g.getFontMetrics().getStringBounds(message, g)
				.getWidth();
		int start = this.getWidth() / 2 - stringLen / 2;
		g.drawString(message, start, this.getHeight() / 2);

		// g.drawString(message, this.getWidth() / 2 - message.length() *
		// 10,this.getHeight() / 2);
	}
}
