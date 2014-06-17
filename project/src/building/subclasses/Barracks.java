package building.subclasses;

import game.Game;
import game.Teams;
import player.Player;
import unit.subclasses.Marine;
import map.Cell;
import map.Map;
import map.terrain.subclasses.Flat;
import building.Building;

/**
 * Class for the constructor and methods for a Barracks
 * @author Dhruvil, Alex, Luke
 */
public class Barracks extends Building {

    public static int cost = 200;

    /**
     * Constructor for the class.
     *
     * @param cell : Cell - the cell of the building
     * @param owner : Player - the buildings owner
     * @param game : Game - the game object
     */
    public Barracks(Cell cell, Player owner, Game game) {
        super("Barracks", 300, cost, 100, cell, owner, game);
        this.setImageLocation("/imgs/buildings/Barracks.png");
    }

    /**
     * Recruits a new marine close to the building.
     *
     * @param m : Map - the map object
     * @param cell : Cell - the cell of the building
     */
    public void recruitMarine(Map m, Cell cell, Boolean aiMove) {

        if (aiMove || Teams.comparePlayers(this.getOwner(), game.getCurrentPlayer())) {
            if (game.getCurrentPlayer().canAfford(Marine.cost)) {

                int x = cell.getCellPosX();
                int y = cell.getCellPosY();

                if (m.getCell(x + 1, y).getTerrain() instanceof Flat
                        && m.getCell(x + 1, y).getBuilding() == null
                        && m.getCell(x + 1, y).getUnit() == null) {
                    m.getCell(x + 1, y)
                            .setUnit(
                                    new Marine(m.getCell(x + 1, y), m,
                                            getOwner(), game));
                    game.getCurrentPlayer().subMoney(Marine.cost);
                } else if (m.getCell(x - 1, y).getTerrain() instanceof Flat
                        && m.getCell(x - 1, y).getBuilding() == null
                        && m.getCell(x - 1, y).getUnit() == null) {
                    m.getCell(x - 1, y)
                            .setUnit(
                                    new Marine(m.getCell(x - 1, y), m,
                                            getOwner(), game));
                    game.getCurrentPlayer().subMoney(Marine.cost);
                } else if (m.getCell(x, y + 1).getTerrain() instanceof Flat
                        && m.getCell(x, y + 1).getBuilding() == null
                        && m.getCell(x, y + 1).getUnit() == null) {
                    m.getCell(x, y + 1)
                            .setUnit(
                                    new Marine(m.getCell(x, y + 1), m,
                                            getOwner(), game));
                    game.getCurrentPlayer().subMoney(Marine.cost);
                } else if (m.getCell(x, y - 1).getTerrain() instanceof Flat
                        && m.getCell(x, y - 1).getBuilding() == null
                        && m.getCell(x, y - 1).getUnit() == null) {
                    m.getCell(x, y - 1)
                            .setUnit(
                                    new Marine(m.getCell(x, y - 1), m,
                                            getOwner(), game));
                    game.getCurrentPlayer().subMoney(Marine.cost);
                } else {
                }
            } else {
            }
        } else {
            game.mapPanel.Notify("You can only control your own units!");
        }
    }

    @Override
    public void onDestroyEvent() {

    }
}
