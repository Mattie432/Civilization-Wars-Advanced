package unit;

import game.Game;
import game.Teams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import player.Player;
import map.Cell;
import map.Map;
import building.Building;
import map.terrain.subclasses.*;
import unit.subclasses.*;

/**
 * Class for the constructor and methods of a Unit
 * @author Dhruvil, Luke, Matt
 */
public abstract class Unit {

    private Map map;
    public int cost;
    protected int reward;
    protected String name;
    protected int currentHealth;
    protected int baseHealth;
    protected int baseMoveRange;
    protected String imageLocation;
    protected int singleUnitHealth;
    protected int singleUnitAttack;
    protected int baseAttackDamage;
    protected int currentAttackDamage;
    protected int baseNumberUnitsInGroup;
    protected int currentNumberUnitsInGroup;
    protected int currentMoveRange;
    protected Cell cell;
    protected boolean attacked;
    public Player owner;
    private Game game;
    public int totalImagesInSprite = 1;
    public int currentSpriteImageNumber = 1;

    /**
     * A constructor for this class that initialises the variables for the object.
     * @param name : String - the name of the unit
     * @param health : int - the max health of the unit
     * @param cost : int - the cost to recruit
     * @param reward : int - the reward for killing the unit
     * @param damage : int - the attack damage of the unit
     * @param numUnits : int - number of units in the squad
     * @param range : int - the movement range of the unit
     * @param img : String - the location of the image for the string
     * @param current : Cell - the current cell the unit is in
     * @param map : Map - the map object
     * @param owner : Player - the owner of the unit
     * @param game : Game - the game object
     */
    public Unit(String name, int health, int cost, int reward, int damage,
            int numUnits, int range, String img, Cell current, Map map,
            Player owner, Game game) {
        this.map = map;
        this.name = name;
        this.cost = cost;
        imageLocation = img;
        baseHealth = health;
        this.cell = current;
        currentHealth = health;
        this.reward = reward;
        baseMoveRange = range;
        currentMoveRange = range;
        baseAttackDamage = damage;
        currentAttackDamage = damage;
        baseNumberUnitsInGroup = numUnits;
        currentNumberUnitsInGroup = numUnits;
        singleUnitHealth = currentHealth / baseNumberUnitsInGroup;
        singleUnitAttack = baseAttackDamage / baseNumberUnitsInGroup;
        attacked = false;
        this.owner = owner;
        this.game = game;
    }

    /**
     * Gets the current range of the unit.
     *
     * @return range : int - current range
     */
    public int getCurrentMoveRange() {
        return currentMoveRange;
    }

    /**
     * Updates the range of the unit after movement.
     * 
     * @param tilesMoved
     */
    public void updateCurrentMoveRange(int tilesMoved) {
        this.currentMoveRange -= tilesMoved;
        if (this.currentMoveRange < 0) {
            this.currentMoveRange = 0;
        }
    }

    /**
     * Gets the original health of the unit.
     *
     * @return health : int - original health
     */
    public int getBaseHealth() {
        return baseHealth;
    }

    /**
     * Gets the health level of the unit.
     *
     * @return health : int - current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the health level of the unit. <b>This should not be used</b> For
     * damage reduction use the attacked() method.
     *
     * @param health : int - current health
     */
    @SuppressWarnings("unused")
    private void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    /**
     * Gets the recruitment cost of the unit.
     *
     * @return cost : int - recruitment cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost of recruitment of the unit. <b>This should not be used.</b>
     * This should be set via the class constructor.
     *
     * @param cost : int - new cost of unit.
     */
    @SuppressWarnings("unused")
    private void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Gets the reward level of the unit when its killed.
     *
     * @return reward : int - reward ammount.
     */
    public int getReward() {
        return reward;
    }

    /**
     * Sets the reward level of the unit. <b>This should not be used.</b> This
     * should be set via the class constructor.
     *
     * @param reward : int - new reward ammount
     */
    @SuppressWarnings("unused")
    private void setReward(int reward) {
        this.reward = reward;
    }

    /**
     * Gets the base attack damage of the unit.
     *
     * @return baseAttackDamage : int - base attack damage
     */
    public int getBaseAttackDamage() {
        return baseAttackDamage;
    }

    /**
     * Gets the base attack damage of the unit.
     *
     * @return baseAttackDamage : int - base attack damage
     */
    public int getCurrentAttackDamage() {
        return currentAttackDamage;
    }

    /**
     * Sets the base attack damage of the unit. <b>This should not be used.</b>
     * This should be set via the class constructor.
     *
     * @param baseAttackDamage : int - new base attack damage
     */
    @SuppressWarnings("unused")
    private void setCurrentAttackDamage(int currentAttackDamage) {
        this.currentAttackDamage = currentAttackDamage;
    }

    /**
     * Gets the base move range of the unit.
     *
     * @return baseMoveRange : int - base move range of the unit.
     */
    public int getBaseMoveRange() {
        return baseMoveRange;
    }

    /**
     * Sets the base movement range of the unit. <b>This should not be used.</b>
     * This should be set via the class constructor.
     *
     * @param baseMoveRange : int - new base move range
     */
    @SuppressWarnings("unused")
    private void setBaseMoveRange(int baseMoveRange) {
        this.baseMoveRange = baseMoveRange;
    }

    /**
     * Gets the image location of the unit.
     *
     * @return imageLocaton : String - image location
     */
    public String getImageLocation() {
        return imageLocation;
    }
    
    /**
     * Gets the image location of the unit.
     *
     * @return imageLocaton : String - image location
     */
    public void setImageLocation(String loc) {
        this.imageLocation = loc;
    }

    /**
     * Gets the original number of units in the group.
     *
     * @return number of units : int - original number of units in the group.
     */
    public int getBaseNumberUnitsInGroup() {
        return baseNumberUnitsInGroup;
    }

    /**
     * Gets the current number of units in the group.
     *
     * @return number of units : int - current number of units in the group.
     */
    public int getCurrentNumberUnitsInGroup() {
        return currentNumberUnitsInGroup;
    }

    /**
     * Gets the health of a single unit in the group.
     *
     * @return health : int - single unit's health.
     */
    public int getSingleUnitHealth() {
        return singleUnitHealth;
    }

    /**
     * Gets the attack of a single unit in the group.
     *
     * @return damage : int - single unit's attack damage.
     */
    public int getSingleUnitAttack() {
        return singleUnitAttack;
    }

    /**
     * Gets the cell of the unit.
     *
     * @return cell : Cell - the cell occupied by the unit.
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Sets the cell of the unit.
     * 
     * @param cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    
    /**
     * Abstract method used to determine the next image in the animation.
     */
    public abstract void nextImage();

    /**
     * The method that is used to swap the cells in which the unit is stored.
     * @param unit
     * @param current
     * @param moveTo
     * @param aiMove
     */
    public void moveToCell(Unit unit, Cell current, Cell moveTo, Boolean aiMove) {

        if (aiMove || Teams.comparePlayers(unit.getOwner(), game.getCurrentPlayer())) {

            int xDistance = Math.abs(current.getCellPosX()
                    - moveTo.getCellPosX());
            int yDistance = Math.abs(current.getCellPosY()
                    - moveTo.getCellPosY());

            if (unit instanceof Marine) {
                if (moveTo.getTerrain() instanceof Water
                        || moveTo.getUnit() != null
                        || moveTo.getBuilding() != null) {
                    if (!aiMove) {
                        game.mapPanel.Notify("You cannot move Marines onto Water!");
                    }
                } else if (getMoveableCells(map).contains(moveTo)) {
                    unit.cell = null;
                    current.unit = null;
                    unit.cell = moveTo;
                    moveTo.unit = unit;
                    this.currentMoveRange -= (xDistance + yDistance);
                }
            } else if (unit instanceof Tank) {
                if (moveTo.getTerrain() instanceof Forest
                        || moveTo.getTerrain() instanceof Bridge
                        || moveTo.getTerrain() instanceof Mountain
                        || moveTo.getUnit() != null
                        || moveTo.getBuilding() != null) {
                    if (!aiMove) {
                        game.mapPanel.Notify("You cannot move Tanks onto Forests!");
                    }
                } else if (getMoveableCells(map).contains(moveTo)) {
                    unit.cell = null;
                    current.unit = null;
                    unit.cell = moveTo;
                    moveTo.unit = unit;
                    this.currentMoveRange -= (xDistance + yDistance);
                }
            }
        }

    }

    /**
     * This method is for when the unit has been attacked by another unit.
     * The corresponding stats E.G. health will be altered accordingly.
     * 
     * @param attacker : Unit - the unit attacking
     * @param toBeAttacked : Unit - the unit to be attacked
     */
    public void attacked(Unit attacker, Unit toBeAttacked) {
        int damage = attacker.getCurrentAttackDamage();
        
        toBeAttacked.currentHealth = toBeAttacked.currentHealth - damage;
        toBeAttacked.currentNumberUnitsInGroup = (int) Math
                .ceil(toBeAttacked.currentHealth
                        / toBeAttacked.singleUnitHealth);
        toBeAttacked.currentAttackDamage = toBeAttacked.singleUnitAttack
                * toBeAttacked.currentNumberUnitsInGroup;
        if (toBeAttacked.getCurrentHealth() < toBeAttacked
                .getSingleUnitHealth()) {
            toBeAttacked.currentNumberUnitsInGroup = 1;
            toBeAttacked.currentAttackDamage = toBeAttacked.singleUnitAttack;
        }
        if (toBeAttacked.currentHealth <= 0) {
            toBeAttacked.getCell().unit = null;
            attacker.owner.addMoney(toBeAttacked.reward);
        }
    }

    /**
     * This method is for when the unit is attacking another unit.
     * If the unit can attack then the appropriate information is sent to the attacked method.
     * 
     * @param attacker : Unit - the unit attackng0
     * @param toBeAttacked : Unit - the unit being attacked
     * @param aiMove : Boolean - if this is being done by an AI or human player
     */
    public void attackUnit(Unit attacker, Unit toBeAttacked, boolean aiMove) {
        if (aiMove || Teams.comparePlayers(this.getOwner(),game.getCurrentPlayer())) {
            HashSet<Player> tmp = Teams.getTeamFriends(Teams
                    .getTeamOfPlayer(owner));
            if (attacker.getAttacked() == true) {
                game.mapPanel.Notify("A unit can only attack once per turn!");
            } else if (tmp.contains(toBeAttacked.getOwner())) {
                game.mapPanel.Notify("You cannot attack allied units!");
            } else {
                moveToCell(this, this.getCell(),
                        moveToAttack(toBeAttacked.getCell()), true);
                toBeAttacked.attacked(attacker, toBeAttacked);
                attacked = true;
            }
        }
    }

    /**
     * Finds the closest cell to the attacked unit. This should be used to
     * determine the cell to move to when attacking a unit from afar.
     *
     * @param cell : Cell - the cell to be attacked
     */
    public Cell moveToAttack(Cell cell) {
        ArrayList<Cell> moveableCells = this.getMoveableCells(map);

        Queue<Cell> surroundingAttackedCell = cell.getSurroundingCells(map,
                false);

        ArrayList<Cell> nextToCells = new ArrayList<Cell>();

        for (Cell c : surroundingAttackedCell) {
            if (moveableCells.contains(c)) {
                nextToCells.add(c);
            }
        }

        Cell closestCell = null;
        for (Cell c : nextToCells) {
            if (closestCell == null) {
                closestCell = c;
                // if c is closer than closestCell then swap.
            } else if (Math.abs(closestCell.getCellPosX()
                    - this.getCell().getCellPosX()) > Math
                    .abs((c.getCellPosX() - this.getCell().getCellPosX()))
                    && Math.abs(closestCell.getCellPosY()
                            - this.getCell().getCellPosY()) > Math.abs((c
                            .getCellPosY() - this.getCell().getCellPosY()))) {
                closestCell = c;
            }
        }
        return closestCell;

    }

    /**
     * This method is for when the unit is attacking a building.
     * If the unit can attack then the appropriate information is sent to the 
     * attacked method in the building class.
     * @param attacker : Unit - the unit attacking
     * @param toBeAttacked : Building - the building being attacked
     * @param aiMove : Boolean - if the AI is playing or a Human
     */
    public void attackBuilding(Unit attacker, Building toBeAttacked,
            boolean aiMove) {
        if (aiMove || Teams.comparePlayers(this.getOwner(),game.getCurrentPlayer())) {
            HashSet<Player> tmp = Teams.getTeamFriends(Teams
                    .getTeamOfPlayer(owner));
            if (attacker.getAttacked() == true) {
                game.mapPanel.Notify("A unit can only attack once per turn!");
            } else if (tmp.contains(toBeAttacked.getOwner())) {
                game.mapPanel.Notify("You cannot attack allied units!");
            } else {
                moveToCell(this, this.getCell(),
                        moveToAttack(toBeAttacked.getCell()), true);
                toBeAttacked.attacked(attacker);
                attacked = true;
            }
        } else {
            game.mapPanel.Notify("You can only attack buildings with your units!");
        }

    }

    /**
     * Gets the name of the unit
     * @return name : String - name of the unit
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the boolean for if the unit has already attacked. (Note it can only attack once per turn)
     * @return attacked : Boolean - if the unit has attacked already
     */
    public boolean getAttacked() {
        return attacked;
    }

    /**
     * Gets the cells moveable by this unit.
     *
     * @param map : Map - the map object
     * @return moveableCells : ArrayList<Cell> - the cells moveable by the unit
     */
    public ArrayList<Cell> getMoveableCells(Map map) {
        Queue<Cell> q = new LinkedList<Cell>();
        q.add(cell);
        ArrayList<Cell> t = getMoveableCells(cell.getUnit(), q,
                new ArrayList<Cell>(), cell.getUnit().getCurrentMoveRange(),
                map);
        return t;
    }

    /**
     * Recurcive method to find the moveable cells of the unit through breadth
     * first search.
     *
     * @param unit : Unit - the unit to move
     * @param queue : Queve<Cell> - A que with the cells to be checked
     * @param expandedNodes : ArrayList<Cell> - Arraylist of moveable cells
     * @param remainingMovePoints : Int - remaining move points
     * @param map : Map - the map object
<<<<<<< .mine
     * @return moveableCells : ArrayList<cell> - the cells this unit can move to.
=======
     * @return ArrayList<Cell>()
>>>>>>> .r371
     */
    private ArrayList<Cell> getMoveableCells(Unit unit, Queue<Cell> queue,
            ArrayList<Cell> expandedNodes, int remainingMovePoints, Map map) {

        if (unit.attacked == false) {
        
        Queue<Cell> newQueue = new LinkedList<Cell>();

        // if there are still nodes
        while (!queue.isEmpty()) {

            Cell currentNode = queue.poll();
            Queue<Cell> nextNodes = currentNode.getSurroundingCells(map, false);
            expandedNodes.add(currentNode);
            for (Cell c : nextNodes) {
                if (!expandedNodes.contains(c)) {
                    // check cell is moveable
                    if (c.canMoveUnitTo(unit)) {
                        newQueue.add(c);
                    }
                }
            }

        }

        remainingMovePoints--;
        if (remainingMovePoints >= 0) {
            return getMoveableCells(unit, newQueue, expandedNodes,
                    remainingMovePoints, map);
        } else {
            Set<Cell> set = new HashSet<Cell>(expandedNodes);
            return new ArrayList<Cell>(set);
        }
        
        } else {
            return new ArrayList<Cell>();
        }

    }

    /**
     * Gets the cells that this unit can attack within its movement range. This method
     * calls getAttackableCells which is the algorithm for finding the cells.
     * @param map : Map - the map object
     * @return attackableCells : ArrayList<Cell> - the cells this unit can attack.
     */
    public ArrayList<Cell> getAttackableCells(Map map) {
        if (attacked == false) {
            Queue<Cell> q = new LinkedList<Cell>();
            ArrayList<Cell> newAttackableQueue = new ArrayList<Cell>();

            q.add(cell);
            ArrayList<Cell> t = getAttackableCells(cell.getUnit(), q,
                    new ArrayList<Cell>(),
                    cell.getUnit().getCurrentMoveRange(), map,
                    newAttackableQueue);
            return t;
        } else {
            return new ArrayList<Cell>();
        }
    }

    /**
     * Recurcive method to find the moveable cells of the unit through breadth
     * first search.
     *
     * @param attackingUnit : Unit - the unit attacking
     * @param queue : Queve<Cell> - A que with the cells to be checked
     * @param expandedNodes : ArrayList<Cell> - Arraylist of moveable cells
     * @param remainingMovePoints : Int - remaining move points
     * @param map : Map - the map object
     * @return attackableCells : ArrayList<cell> - the cells this unit can move to.
     */
    private ArrayList<Cell> getAttackableCells(Unit attackingUnit,
            Queue<Cell> queue, ArrayList<Cell> expandedNodes,
            int remainingMovePoints, Map map, ArrayList<Cell> attackableCells) {
        
        Queue<Cell> newQueue = new LinkedList<Cell>();
        // if there are still nodes
        while (!queue.isEmpty()) {

            Cell currentNode = queue.poll();
            Queue<Cell> nextNodes = currentNode.getSurroundingCells(map, false);
            expandedNodes.add(currentNode);
            for (Cell c : nextNodes) {
                if (!expandedNodes.contains(c)) {
                    // check cell is moveable
                    if (c.canMoveUnitTo(attackingUnit)) {
                        newQueue.add(c);
                    } else if (c.canAttackCell(attackingUnit)) {
                        attackableCells.add(c);
                    }
                }
            }

        }

        remainingMovePoints--;
        if (remainingMovePoints >= 0) {
            return getAttackableCells(attackingUnit, newQueue, expandedNodes,
                    remainingMovePoints, map, attackableCells);
        } else {
            Set<Cell> set = new HashSet<Cell>(attackableCells);
            return new ArrayList<Cell>(set);
        }

    }

    /**
     * Gets the owner of this unit
     * @return owner : Player - owner of the unit
     */
    public Player getOwner() {
        return owner;
    }


    /**
     * Resets the unit ready for another day
     */
    public void reset() {
        currentMoveRange = baseMoveRange;
        attacked = false;
    }


    /**
     * Attacks the closest unit to it. This method searches for the closest enemy
     * unit and advances to attack it. This is used by the AI.
     */
    public void attackClosestUnit() {
        ArrayList<Cell> attackableCells = getAttackableCells(map);

        if (attackableCells.size() == 0) {
            map.resetPreviousCells();

            Queue<Cell> queue = new LinkedList<Cell>();
            queue.add(this.getCell());

            ArrayList<Cell> expandedNodes = new ArrayList<Cell>();

            Cell c = attackClosestUnit_(queue, expandedNodes);

            ArrayList<Cell> moveableCells = getMoveableCells(map);

            if (c != null) {
                boolean loop = true;
                while (loop && !moveableCells.contains(c)) {
                    if (c.getPreviousCell() == null) {
                        loop = false;
                    } else {
                        c = c.getPreviousCell();
                    }
                }
            }

            if (c != null) {
                this.moveToCell(this, this.getCell(), c, true);
            }
            map.resetPreviousCells();
        } else {
            Cell attackCell = attackableCells.get(0);
            if (attackCell.getBuilding() != null) {
                this.attackBuilding(this, attackCell.getBuilding(), true);
            } else if (attackCell.getUnit() != null) {
                this.attackUnit(this, attackCell.getUnit(), true);
            }
        }
    }

    /**
     * Reccursicve method for attacking the closest unit.
     * @param queue : Queue<Cell> - the que of cells to expand
     * @param expandedNodes : ArrayList<Cell> - the expanded cells
     * @return cellToAttack : Cell - the cell the unit will try to attack
     */
    public Cell attackClosestUnit_(Queue<Cell> queue,
            ArrayList<Cell> expandedNodes) {
        Queue<Cell> newQueue = new LinkedList<Cell>();
        Cell returnCell = null;
        // if there are still nodes
        while (!queue.isEmpty()) {

            Cell currentNode = queue.poll();
            Queue<Cell> nextNodes = currentNode.getSurroundingCells(map, false);
            if (!expandedNodes.contains(currentNode)) {
                expandedNodes.add(currentNode);
                for (Cell c : nextNodes) {
                    if (!expandedNodes.contains(c)) {
                        // check cell is moveable
                        if (c.canMoveUnitTo(this)) {
                            c.setPreviousCell(currentNode);
                            newQueue.add(c);
                        } else if (c.canAttackCell(this)) {
                            c.setPreviousCell(currentNode);
                            returnCell = c;
                            return returnCell;
                        }
                    }
                }
            }

        }
        if (newQueue.size() == 0 || returnCell != null) {
            return returnCell;
        } else {
            return attackClosestUnit_(newQueue, expandedNodes);
        }
    }

}
