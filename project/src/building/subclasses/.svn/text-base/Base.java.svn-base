package building.subclasses;

import game.Game;
import player.Player;
import map.Cell;
import building.Building;
import game.Teams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import map.Map;

/**
 * Class for the constructor and methods for a Base
 * @author Dhruvil, Alex, Luke
 */
public class Base extends Building {

    public static int cost = 0;

    /**
     * Constructor for the class.
     *
     * @param cell : Cell - the cell of the building
     * @param owner : Player - the buildings owner
     * @param game : Game - the game object
     */
    public Base(Cell cell, Player owner, Game game) {
        super("Base", 500, cost, 0, cell, owner, game);
        this.setImageLocation("/imgs/buildings/Base.png");
    }

    /**
     * A method that shows where the user can place a building after creating it in the base.
     * 
     * @param map
     * @return ArrayList<Cell>
     */
    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public ArrayList<Cell> getBuildableCells(Map map) {
        int range = 5;

        Queue<Cell> q = new LinkedList<Cell>();
        ArrayList<Cell> newBuildableQueue = new ArrayList<Cell>();
        ArrayList<Cell> restrictedCells = new ArrayList();

        q.add(cell);
        ArrayList<Cell> t = getBuildableCells(cell.getBuilding(), q,
                new ArrayList<Cell>(), range, map, restrictedCells);
        return t;
    }

    /**
     * A method that shows where the user can place a building after creating it in the base.
     * 
     * @param building
     * @param queue
     * @param expandedNodes
     * @param remainingBuildPoints
     * @param map
     * @param restrictedCells
     * @return ArrayList<Cell>
     */
    private ArrayList<Cell> getBuildableCells(Building building,
            Queue<Cell> queue, ArrayList<Cell> expandedNodes,
            int remainingBuildPoints, Map map, ArrayList<Cell> restrictedCells) {

        Queue<Cell> newQueue = new LinkedList<Cell>();
        // if there are still nodes
        while (!queue.isEmpty()) {

            Cell currentNode = queue.poll();
            Queue<Cell> nextNodes = currentNode.getSurroundingCells(map, true);
            expandedNodes.add(currentNode);
            for (Cell c : nextNodes) {
                if (!expandedNodes.contains(c)) {
                    // check cell is moveable
                    if (!c.canBuildOn(building)) {
                        restrictedCells.add(c);
                    }
                    newQueue.add(c);
                }
            }

        }

        remainingBuildPoints--;
        if (remainingBuildPoints >= 0) {
            return getBuildableCells(building, newQueue, expandedNodes,
                    remainingBuildPoints, map, restrictedCells);
        } else {
            Set<Cell> set = new HashSet<Cell>(expandedNodes);
            set.removeAll(restrictedCells);
            return new ArrayList<Cell>(set);
        }

    }

    /**
     * A method used to build a new Barracks and then by accessing the getBuildableCells
     * it shows where the user can place the building and allows the user to create a Barracks
     * within the area shown.
     * 
     * @param map
     * @param c
     * @param aiMove
     */
    public void buildBarracks(Map map, Cell c, Boolean aiMove) {

        if (aiMove || Teams.comparePlayers(this.getOwner(),game.getCurrentPlayer())) {
            if (game.getCurrentPlayer().canAfford(Barracks.cost)) {
                if (getBuildableCells(map).contains(c)) {
                    Barracks b = new Barracks(c, this.getOwner(), this.game);
                    b.placeBuilding(b, c);
                    game.getCurrentPlayer().subMoney(Barracks.cost);
                } else {
                    game.mapPanel.Notify("You cannot place a Barrack there!");
                }
            } else {
                if (!aiMove) {
                    game.mapPanel.Notify("You cannot afford to purchace a Barrack!");
                }
            }
        } else {
            game.mapPanel.Notify("You can only control your own Base!");
        }
    }

    /**
     * A method used to build a new Factory and then by accessing the getBuildableCells
     * it shows where the user can place the building and allows the user to create a Factory
     * within the area shown.
     * 
     * @param map
     * @param c
     * @param aiMove
     */
    public void buildFactory(Map map, Cell c, Boolean aiMove) {
        if (aiMove || Teams.comparePlayers(this.getOwner(),game.getCurrentPlayer())) {
            if (game.getCurrentPlayer().canAfford(Factory.cost)) {
                if (getBuildableCells(map).contains(c)) {
                    Factory f = new Factory(c, this.getOwner(), this.game);
                    f.placeBuilding(f, c);
                    game.getCurrentPlayer().subMoney(Factory.cost);
                } else {
                    game.mapPanel.Notify("You cannot place a Factory there!");
                }
            } else {
                if (!aiMove) {
                    game.mapPanel.Notify("You cannot afford to purchace a Factory!");
                }
            }
        } else {
            game.mapPanel.Notify("You can only control your own Base!");
        }
    }

    /**
     * A method used to determine what happens when the Base is destroyed.
     */
    @Override
    public void onDestroyEvent() {

        owner.setEliminated(true);

        //remove this player from players
        game.playerOrder.remove(owner);

        
        game.map.removePlayersBuildingsUnits(owner);
        
        if (game.gameType.toString() == "AItype1vs1"
                || game.gameType.toString() == "AItypeFreeForAll"
                || game.gameType.toString() == "type1vs1"
                || game.gameType.toString() == "typeFreeForAll") {

            if (game.playerOrder.size() <= 1) {
                //end of game!
                game.mapPanel.Notify(game.playerOrder.get(0).getPlayerName() + " Wins!");
                game.endGame();
            }

        } else if (game.gameType.toString() == "AItype2vs2"
                || game.gameType.toString() == "type2vs2") {

            ArrayList<Player> enemies = Teams.getEnemies(game.playerOrder.get(0));
            ArrayList<Player> players = Teams.getAllPlayers();
            int eliminated = 0;

            if (game.playerOrder.size() <= 2) {
                for (Player p : enemies) {
                    if (p.getEliminated()) {
                        eliminated++;
                    }
                }

                if (eliminated == 2) {
                    for (Player p : players) {
                        for (Player e : enemies) {
                            if (Teams.comparePlayers(p, e)) {
                                players.remove(e);
                            }
                        }
                    }
                    
                    game.mapPanel.Notify(players.get(0).getPlayerName() + " & "
                            + players.get(1).getPlayerName() + " Wins!");
                    game.endGame();

                }
            }
        }
    }
}
