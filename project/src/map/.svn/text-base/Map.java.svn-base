package map;

import java.util.ArrayList;
import player.Player;
import game.Game;
import game.Teams;
import unit.Unit;
import unit.subclasses.Marine;
import building.Building;
import building.subclasses.Base;
import map.terrain.subclasses.*;
import map.terrain.Terrain;
import map.terrain.subclasses.Flat;

/**
 * This class contains all information for the map.
 * 
 * @author Robert, Matt
 * 
 */
public class Map {

	/**
	 * The first parameter is the Y coord and the second is the X cells[y][x]
	 */
	private Cell[][] cells;
	public Game game;
	public String mapName;
	// width and height of a single cell
	private int cellDimensionsPx;

	/*
	 * This main method should not be in here, main method should be in the game
	 * class public static void main(String[] args) { Map map = new Map(90, 90,
	 * "kingsClashMap"); System.out.println(map.cells[0][1]); }
	 */

	/**
	 * Default constructor for new map the class.
	 * 
	 * @param cellWidth
	 *            : int - set the width of cells
	 * @param cellHeight
	 *            : int - set the height of cells
	 * @param mapName
	 *            : String - select the map by its name
	 * @param game
	 */
	public Map(int cellDimensionsPx, String mapName, Game game) {
		this.game = game;
		this.cellDimensionsPx = cellDimensionsPx;
		this.mapName = mapName;
		// System.out.println(cellDimensionsPx);
		cells = new Cell[1980 / cellDimensionsPx][1980 / cellDimensionsPx];
		setMap(mapName);

	}

	/**
	 * Sets the map name, this would be used to change which map is loaded.
	 * 
	 * @param mapName
	 *            : String - the map name to load
	 */
	public void setMap(String mapName) {

		if (mapName == "kingsClashMap") {
			initializeKingsClashMap();
			initializeKingsClashMapUnits();
		} else {
			initializeKingsClashMap();
			initializeKingsClashMapUnits();
		}
	}

	/**
	 * This initialized the map "kingsClash" this sets the starting unit
	 * placement for each team in the game.
	 */
	private void initializeKingsClashMapUnits() {
		Player bluePlayer = Teams.getPlayersOfTeam(Teams.BLUE);
		if (bluePlayer != null) {
			// set building placement & unit placement TOP LEFT
			getCell(1, 1)
					.setBuilding(new Base(getCell(1, 1), bluePlayer, game));
			getCell(2, 2).setUnit(
					new Marine(getCell(2, 2), this, bluePlayer, game));
		}

		Player greenPlayer = Teams.getPlayersOfTeam(Teams.GREEN);
		if (greenPlayer != null) {
			// set buildings TOP RIGHT
			getCell(20, 1).setBuilding(
					new Base(getCell(20, 1), greenPlayer, game));
			getCell(19, 2).setUnit(
					new Marine(getCell(19, 2), this, greenPlayer, game));
		}

		Player orangePlayer = Teams.getPlayersOfTeam(Teams.ORANGE);
		if (orangePlayer != null) {
			// set buildings BOTTOM LEFT
			getCell(1, 20).setBuilding(
					new Base(getCell(1, 20), orangePlayer, game));
			getCell(2, 19).setUnit(
					new Marine(getCell(2, 19), this, orangePlayer, game));
		}

		Player redPlayer = Teams.getPlayersOfTeam(Teams.RED);
		if (redPlayer != null) {
			// set buildings TOP RIGHT
			getCell(20, 20).setBuilding(
					new Base(getCell(20, 20), redPlayer, game));
			getCell(19, 19).setUnit(
					new Marine(getCell(19, 19), this, redPlayer, game));
		}

	}

	/**
	 * This initializes the "kingsClash" map's cells. It sets there terrain and
	 * position to match the map image.
	 */
	private void initializeKingsClashMap() {
		for (int x = 0; x < 22; x++) {
			for (int y = 0; y < 22; y++) {
				Cell cell = null;
				Terrain terrain = null;
				if (x == 0 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 0 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 1 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 1 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 2 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 2 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 8) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 9) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 12) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 13) {
					// river
					terrain = new Water();

				} else if (x == 3 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 4 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 4 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 5 && y == 7) {
					// bridge
					terrain = new Bridge();

				} else if (x == 5 && y == 14) {
					// bridge
					terrain = new Bridge();
				} else if (x == 6 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 6 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 4) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 5) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 6) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 15) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 16) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 17) {
					// river
					terrain = new Water();

				} else if (x == 7 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 8 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 8 && y == 10) {
					// mountain
					terrain = new Mountain();
				} else if (x == 8 && y == 11) {
					// mountain
					terrain = new Mountain();
				} else if (x == 8 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 9 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 9 && y == 10) {
					// tree
					terrain = new Forest();
				} else if (x == 9 && y == 11) {
					// tree
					terrain = new Forest();
				} else if (x == 9 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 0) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 1) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 2) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 8) {
					// mountain
					terrain = new Mountain();
				} else if (x == 10 && y == 9) {
					// tree
					terrain = new Forest();
				} else if (x == 10 && y == 10) {
					// tree
					terrain = new Forest();
				} else if (x == 10 && y == 11) {
					// tree
					terrain = new Forest();
				} else if (x == 10 && y == 12) {
					// tree
					terrain = new Forest();
				} else if (x == 10 && y == 13) {
					// mountain
					terrain = new Mountain();
				} else if (x == 10 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 19) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 20) {
					// river
					terrain = new Water();

				} else if (x == 10 && y == 21) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 0) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 1) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 2) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 8) {
					// mountain
					terrain = new Mountain();
				} else if (x == 11 && y == 9) {
					// tree
					terrain = new Forest();
				} else if (x == 11 && y == 10) {
					// tree
					terrain = new Forest();
				} else if (x == 11 && y == 11) {
					// tree
					terrain = new Forest();
				} else if (x == 11 && y == 12) {
					// tree
					terrain = new Forest();
				} else if (x == 11 && y == 13) {
					// mountain
					terrain = new Mountain();
				} else if (x == 11 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 19) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 20) {
					// river
					terrain = new Water();

				} else if (x == 11 && y == 21) {
					// river
					terrain = new Water();

				} else if (x == 12 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 12 && y == 10) {
					// tree
					terrain = new Forest();
				} else if (x == 12 && y == 11) {
					// tree
					terrain = new Forest();
				} else if (x == 12 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 13 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 13 && y == 10) {
					// mountain
					terrain = new Mountain();
				} else if (x == 13 && y == 11) {
					// mountain
					terrain = new Mountain();
				} else if (x == 13 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 3) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 4) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 5) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 6) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 15) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 16) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 17) {
					// river
					terrain = new Water();

				} else if (x == 14 && y == 18) {
					// river
					terrain = new Water();

				} else if (x == 15 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 15 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 16 && y == 7) {
					// bridge
					terrain = new Bridge();
				} else if (x == 16 && y == 14) {
					// bridge
					terrain = new Bridge();
				} else if (x == 17 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 17 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 7) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 8) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 9) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 12) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 13) {
					// river
					terrain = new Water();

				} else if (x == 18 && y == 14) {
					// river
					terrain = new Water();

				} else if (x == 19 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 19 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 20 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 20 && y == 11) {
					// river
					terrain = new Water();

				} else if (x == 21 && y == 10) {
					// river
					terrain = new Water();

				} else if (x == 21 && y == 11) {
					// river
					terrain = new Water();

				} else {
					// grass
					terrain = new Flat();

				}

				cell = new Cell(terrain, x, y);
				cells[y][x] = cell;
			}
		}

	}

	/**
	 * Function for setting all cells from a given 2D array
	 * 
	 * @param cells
	 *            : 2D array with cells representing a map
	 */

	@SuppressWarnings("unused")
	private void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	/**
	 * Setting a single cell given coordinates x and y
	 * 
	 * @param x
	 *            : x coordinate of cell
	 * @param y
	 *            : y coordinate of cell
	 * @param cell
	 *            : new cell to replace the old one
	 */
	@SuppressWarnings("unused")
	private void setCell(int x, int y, Cell cell) {
		this.cells[y][x] = cell;
	}

	/**
	 * Function for getting the map
	 * 
	 * @return cells : 2D array representing the map
	 */
	public Cell[][] getCells() {
		return this.cells;
	}

	/**
	 * Getting a single cell given its coordinates
	 * 
	 * @param x
	 *            : x coordinate
	 * @param y
	 *            : y coordinate
	 * @return cell: the selected cell
	 */
	public Cell getCell(int x, int y) {
		return this.cells[y][x];
	}

	/**
	 * Getting cell width's and height's measures
	 * 
	 * @return cellWidth : cell's measures
	 */
	public int getCellDimensionsPx() {
		return this.cellDimensionsPx;
	}

	/**
	 * Sets teh cell width for the map
	 * 
	 * @param cellWidthHeight
	 *            : int - pixel width of each cell.
	 */
	public void setCellWidthHeight(int cellWidthHeight) {
		this.cellDimensionsPx = cellWidthHeight;
	}

	/**
	 * Return the number of cells taht make up the horizontal width of the map
	 * 
	 * @return num : int - number of cells
	 */
	public int getNumberOfCellsAcross() {
		return cells[0].length;
	}

	/**
	 * Gets the map name of the current map.
	 * 
	 * @return mapname : String - the map name
	 */
	public String getMapName() {
		return this.mapName;
	}

	/**
	 * Gets all the units of the player on the map.
	 * 
	 * @param player
	 *            : Player - the player to get the units of
	 * @return ArrayList<Unit> - arrayList of units
	 */
	public ArrayList<Unit> getPlayersUnits(Player player) {
		ArrayList<Unit> units = new ArrayList<Unit>();

		for (Cell[] cell1 : cells) {
			for (Cell cell : cell1) {
				if (cell.getUnit() != null) {
					if (Teams.comparePlayers(cell.getUnit().getOwner(), player)) {
						units.add(cell.getUnit());
					}
				}
			}
		}

		return units;

	}

	/**
	 * Gets all of the players buildings
	 * 
	 * @param player
	 *            : Player - the player to get the units of
	 * @return ArrayList<Building> - the buildings of the player
	 */
	public ArrayList<Building> getPlayersBuildings(Player player) {
		ArrayList<Building> buildings = new ArrayList<Building>();

		for (Cell[] cell1 : cells) {
			for (Cell cell : cell1) {
				if (cell.getBuilding() != null) {
					if (Teams.comparePlayers(cell.getBuilding().getOwner(),
							player)) {
						buildings.add(cell.getBuilding());
					}
				}
			}
		}

		return buildings;

	}

	/**
	 * Resets all previous cells for all cells in the map. This is used for the
	 * AI's enemy search.
	 */
	public void resetPreviousCells() {
		for (Cell[] c1 : cells) {
			for (Cell c : c1) {
				c.setPreviousCell(null);
			}
		}
	}

	/**
	 * Removes all units and building for the specified player.
	 * 
	 * @param player
	 *            : Player - the player to remove buildings of.
	 */
	public void removePlayersBuildingsUnits(Player player) {
		for (Cell[] cell1 : cells) {
			for (Cell cell : cell1) {
				// remove buildings
				if (cell.getBuilding() != null) {
					if (Teams.comparePlayers(cell.getBuilding().getOwner(),
							player)) {
						cell.building = null;
					}
				}
				// remove units
				if (cell.getUnit() != null) {
					if (Teams.comparePlayers(cell.getUnit().getOwner(), player)) {
						cell.unit = null;
					}
				}

			}
		}
	}

}
