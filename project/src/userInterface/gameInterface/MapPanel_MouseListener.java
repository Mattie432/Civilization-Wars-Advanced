package userInterface.gameInterface;

import game.Teams;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import userInterface.menus.MainMenu;
import building.subclasses.*;
import map.*;

/**
 * Mouse Listener class for the map panel. This is where all mouse events are
 * created.
 * @author Dhruvil, Alex, Luke, Robert, Matt
 */
public class MapPanel_MouseListener extends MouseAdapter {

    private MapPanel mapPanel;

    /**
     * Constructor for class. Allows the listener access to the map panel
     *
     * @param mp : MapPanel - the map panel object
     */
    public MapPanel_MouseListener(MapPanel mp) {
        this.mapPanel = mp;

    }

    /**
     * Mouseclick event, used when the user clicks on a cell.
     */
    @SuppressWarnings("static-access")
	@Override
    public void mouseClicked(MouseEvent me) {
        int x = (int) ((me.getX() + mapPanel.viewPort.x) / mapPanel.scaledCellWidth);
        int y = (int) ((me.getY() + mapPanel.viewPort.y) / mapPanel.scaledCellWidth);
		mapPanel.resetHighlightedMap();

        // if left mouse button was pressed
        if (SwingUtilities.isLeftMouseButton(me)) {
            refresh();

            // check to see if either buttons are pressed
            if (!mapPanel.moveButtonPressed && !mapPanel.attackButtonPressed
                    && !mapPanel.barracksButtonPressed
                    && !mapPanel.factoryButtonPressed) {
                mapPanel.leftClickedCell = mapPanel.map.getCell(x, y);
            }

            // check for if the move button is pressed
            if (mapPanel.moveButtonPressed) {
                mapPanel.selectedCell = mapPanel.map.getCell(x, y);

                // check to see whether a unit is selected
                if (mapPanel.leftClickedCell != null
                        && mapPanel.leftClickedCell.getUnit() != null
                        && mapPanel.selectedCell.getUnit() == null
                        && mapPanel.selectedCell.getBuilding() == null) {

                    moveUnit(mapPanel.leftClickedCell, mapPanel.selectedCell);
                }

                mapPanel.moveButtonPressed = false;

                // check to see if the attack button was pressed
            } else if (mapPanel.attackButtonPressed) {
                mapPanel.selectedCell = mapPanel.map.getCell(x, y);

                // check to see if a unit was selected
                if (mapPanel.leftClickedCell != null
                        && mapPanel.leftClickedCell.getUnit() != null
                        && mapPanel.selectedCell.getUnit() != null
                        && mapPanel.selectedCell.getBuilding() == null) {

                    attackUnit(mapPanel.leftClickedCell, mapPanel.selectedCell);

                    // check to see if a unit and building was selected
                } else if (mapPanel.leftClickedCell != null
                        && mapPanel.leftClickedCell.getUnit() != null
                        && mapPanel.selectedCell.getUnit() == null
                        && mapPanel.selectedCell.getBuilding() != null) {

                    attackBuilding(mapPanel.leftClickedCell,
                            mapPanel.selectedCell);
                }

                mapPanel.attackButtonPressed = false;

                // check for if the factory barracks button was pressed
            } else if (mapPanel.factoryButtonPressed) {
                mapPanel.selectedCell = mapPanel.map.getCell(x, y);

                // check to see if a unit was selected
                if (mapPanel.leftClickedCell != null
                        && mapPanel.selectedCell.getUnit() == null
                        && mapPanel.selectedCell.getBuilding() == null) {

                    buildFactory((Base) mapPanel.leftClickedCell.getBuilding(),
                            mapPanel.selectedCell);
                }

                mapPanel.factoryButtonPressed = false;

                // check for if the build barracks button was pressed
            } else if (mapPanel.barracksButtonPressed) {
                mapPanel.selectedCell = mapPanel.map.getCell(x, y);

                // check to see if a unit was selected
                if (mapPanel.leftClickedCell.getBuilding() instanceof Base
                        && mapPanel.selectedCell.getUnit() == null
                        && mapPanel.selectedCell.getBuilding() == null) {

                    buildBarracks(
                            (Base) mapPanel.leftClickedCell.getBuilding(),
                            mapPanel.selectedCell);
                }

                mapPanel.barracksButtonPressed = false;

            } else if (mapPanel.leftClickedCell.getBuilding() != null) {

                // check to see if a Barracks is selected
                if (mapPanel.map.getCell(x, y).getBuilding() instanceof Barracks) {

                    // check to see if a Factory was selected
                } else if (mapPanel.map.getCell(x, y).getBuilding() instanceof Factory) {

                    // check to see if a Base was selected
                } else if (mapPanel.map.getCell(x, y).getBuilding() instanceof Base) {
                    mapPanel.showBuildableCells((Base) mapPanel.leftClickedCell
                            .getBuilding());
                    mapPanel.redrawMap();
                }

                mapPanel.game.uiInterfaceBottom
                        .clickedBuilding(mapPanel.leftClickedCell.getBuilding());

                // check to see if a unit was selected
            } else if (mapPanel.leftClickedCell.getUnit() != null) {
                mapPanel.game.uiInterfaceBottom
                        .clickedUnit(mapPanel.leftClickedCell.getUnit());
                mapPanel.showMoveableCells(mapPanel.map.getCell(x, y));
                mapPanel.showAttackableCells(mapPanel.map.getCell(x, y));
                mapPanel.redrawMap();

                // check to see if an empty cell was selected
            } else if (mapPanel.leftClickedCell.getTerrain() != null) {
                mapPanel.game.uiInterfaceBottom.clickedTerrain(mapPanel.map
                        .getCell(x, y).getTerrain());
            }

            // if the right mouse button was pressed
        } else if (SwingUtilities.isRightMouseButton(me)) {
            mapPanel.rightClickedCell = mapPanel.map.getCell(x, y);

            // check to see if a unit was selected
            if (!mapPanel.moveButtonPressed && !mapPanel.attackButtonPressed
                    && mapPanel.leftClickedCell != null
                    && mapPanel.leftClickedCell.getUnit() != null
                    && mapPanel.rightClickedCell.getUnit() == null
                    && mapPanel.rightClickedCell.getBuilding() == null) {
                moveUnit(mapPanel.leftClickedCell, mapPanel.rightClickedCell);

                // check to see if a unit was selected
            } else if (!mapPanel.moveButtonPressed
                    && !mapPanel.attackButtonPressed
                    && mapPanel.leftClickedCell != null
                    && mapPanel.leftClickedCell.getUnit() != null
                    && mapPanel.rightClickedCell.getUnit() != null
                    && mapPanel.rightClickedCell.getBuilding() == null) {

                attackUnit(mapPanel.leftClickedCell, mapPanel.rightClickedCell);

                // check to see if a unit and building was selected
            } else if (!mapPanel.moveButtonPressed
                    && !mapPanel.attackButtonPressed
                    && mapPanel.leftClickedCell != null
                    && mapPanel.leftClickedCell.getUnit() != null
                    && mapPanel.rightClickedCell.getUnit() == null
                    && mapPanel.rightClickedCell.getBuilding() != null) {

                attackBuilding(mapPanel.leftClickedCell,
                        mapPanel.rightClickedCell);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        mapPanel.mouseInsidePanel = true;
    }

    @Override
    public void mouseExited(MouseEvent me) {
        mapPanel.mouseInsidePanel = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Mouse moved command, used when scrolling if the
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mapPanel.mousePoint = e.getPoint();
    }

    /**
     * Method for attacking a unit
     *
     * @param c1 Cell of the selected unit
     * @param c2 Cell of the target unit
     */
    private void attackUnit(Cell c1, Cell c2) {
        if (c1.getUnit().getAttackableCells(mapPanel.map).contains(c2.getUnit().getCell())) {
            if (Teams.comparePlayers(c1.unit.getOwner(), mapPanel.game.getPlayer())) {
                if (Teams.comparePlayers(mapPanel.game.getCurrentPlayer(), mapPanel.game.getPlayer())) {

                    c1.getUnit().attackUnit(c1.getUnit(), c2.getUnit(), false);
                    refresh();

                    if (MainMenu.client != null) {
                        System.out.println("sending move to server");
                        MainMenu.client.sendMoveToServer(c1.getCellPosX(), c1.getCellPosY(),
                                c2.getCellPosX(), c2.getCellPosY(), "attackUnit");
                    }

                } else {
                    mapPanel.Notify("It's not your turn yet!");
                }
            }
        }
    }

    /**
     * Method for attacking a unit
     *
     * @param x1 x value of the selected unit
     * @param y1 y value of the selected unit
     * @param x2 x value of the target unit
     * @param y2 y value of the target unit
     */
    public void attackUnit(int x1, int y1, int x2, int y2) {

        Cell c1 = mapPanel.map.getCell(x1, y1);
        Cell c2 = mapPanel.map.getCell(x2, y2);

        c1.getUnit().attackUnit(c1.getUnit(), c2.getUnit(), false);
        refresh();

    }

    /**
     * Method for attacking a building
     *
     * @param c1 Cell of the selected unit
     * @param c2 Cell of the target building
     */
    private void attackBuilding(Cell c1, Cell c2) {
        if (c1.getUnit().getAttackableCells(mapPanel.map).contains(c2.getBuilding().getCell())) {
            if (Teams.comparePlayers(c1.unit.getOwner(), mapPanel.game.getPlayer())) {
                if (Teams.comparePlayers(mapPanel.game.getCurrentPlayer(), mapPanel.game.getPlayer())) {

                    c1.getUnit().attackBuilding(c1.getUnit(), c2.getBuilding(), false);
                    refresh();

                    if (MainMenu.client != null) {
                        System.out.println("sending move to server");
                        MainMenu.client.sendMoveToServer(c1.getCellPosX(), c1.getCellPosY(),
                                c2.getCellPosX(), c2.getCellPosY(), "attackBuilding");
                    }

                } else {
                    mapPanel.Notify("It's not your turn yet!");
                }
            }
        }
    }

    /**
     * Method for attacking a building
     *
     * @param x1 x value of the selected unit
     * @param y1 y value of the selected unit
     * @param x2 x value of the target building
     * @param y2 y value of the target building
     */
    public void attackBuilding(int x1, int y1, int x2, int y2) {

        Cell c1 = mapPanel.map.getCell(x1, y1);
        Cell c2 = mapPanel.map.getCell(x2, y2);

        c1.getUnit().attackBuilding(c1.getUnit(), c2.getBuilding(), false);
        refresh();

    }

    /**
     * Method for moving a unit
     *
     * @param c1 Cell of the selected unit
     * @param c2 Cell to move to
     */
    private void moveUnit(Cell c1, Cell c2) {
        if (Teams.comparePlayers(c1.unit.getOwner(), mapPanel.game.getPlayer())) {
            if (Teams.comparePlayers(mapPanel.game.getCurrentPlayer(), mapPanel.game.getPlayer())) {

                c1.getUnit().moveToCell(c1.getUnit(), c1, c2, false);
                refresh();

                if (MainMenu.client != null) {
                    System.out.println("sending move to server");
                    MainMenu.client.sendMoveToServer(c1.getCellPosX(), c1.getCellPosY(),
                            c2.getCellPosX(), c2.getCellPosY(), "moveUnit");
                }

            } else {
                mapPanel.Notify("It's not your turn yet!");
            }
        } else {
            mapPanel.Notify("You can only control your units!");
        }
    }

    /**
     * Method for moving a unit
     *
     * @param x1 x value of the selected unit
     * @param y1 y value of the selected unit
     * @param x2 x value of the cell to move to
     * @param y2 y value of the cell to move to
     */
    public void moveUnit(int x1, int y1, int x2, int y2) {
        Cell c1 = mapPanel.map.getCell(x1, y1);
        Cell c2 = mapPanel.map.getCell(x2, y2);

        c1.getUnit().moveToCell(c1.getUnit(), c1, c2, false);
        refresh();
    }

    /**
     * Method for building a Barracks
     *
     * @param b Base to build Barracks from
     * @param c Cell to build Barracks on
     */
    private void buildBarracks(Base b, Cell c) {
        if (mapPanel.game.getCurrentPlayer().canAfford(Barracks.cost)) {
        if (Teams.comparePlayers(b.getOwner(), mapPanel.game.getPlayer())) {
            if (Teams.comparePlayers(mapPanel.game.getCurrentPlayer(), mapPanel.game.getPlayer())) {

                b.buildBarracks(mapPanel.map, c, false);
                refresh();

                if (MainMenu.client != null) {
                    System.out.println("sending move to server");
                    MainMenu.client.sendMoveToServer(mapPanel.leftClickedCell.getCellPosX(),
                            mapPanel.leftClickedCell.getCellPosY(),
                            mapPanel.selectedCell.getCellPosX(), mapPanel.selectedCell.getCellPosY(),
                            "buildBarracks");
                }

            } else {
                mapPanel.Notify("It's not your turn yet!");
            }
        }
        
        } else {
            mapPanel.Notify("You cannot afford to purchase a Barracks!");
        }
    }

    /**
     * Method for building a Barracks
     *
     * @param x1 x value of the Base to build Barracks from
     * @param y1 y value of the Base to build Barracks from
     * @param x2 x value of the Cell to build Barracks on
     * @param y2 y value of the Cell to build Barracks on
     */
    public void buildBarracks(int x1, int y1, int x2, int y2) {

        Base b = (Base) mapPanel.map.getCell(x1, y1).getBuilding();
        Cell c = mapPanel.map.getCell(x2, y2);

        b.buildBarracks(mapPanel.map, c, false);
        refresh();
    }

    /**
     * Method for building a Factory
     *
     * @param b Base to build Factory from
     * @param c Cell to build Factory on
     */
    private void buildFactory(Base b, Cell c) {
        if (mapPanel.game.getCurrentPlayer().canAfford(Factory.cost)) {
            if (Teams.comparePlayers(b.getOwner(), mapPanel.game.getPlayer())) {
                if (Teams.comparePlayers(mapPanel.game.getCurrentPlayer(), mapPanel.game.getPlayer())) {

                    b.buildFactory(mapPanel.map, c, false);
                    refresh();

                    if (MainMenu.client != null) {
                        System.out.println("sending move to server");
                        MainMenu.client.sendMoveToServer(mapPanel.leftClickedCell.getCellPosX(),
                                mapPanel.leftClickedCell.getCellPosY(),
                                mapPanel.selectedCell.getCellPosX(), mapPanel.selectedCell.getCellPosY(),
                                "buildFactory");
                    }

                } else {
                    mapPanel.Notify("It's not your turn yet!");
                }
            }
            
        } else {
            mapPanel.Notify("You cannot afford to purchase a Factory!");
        }

    }

    /**
     * Method for building a Factory
     *
     * @param x1 x value of the Base to build Factory from
     * @param y1 y value of the Base to build Factory from
     * @param x2 x value of the Cell to build Factory on
     * @param y2 y value of the Cell to build Factory on
     */
    public void buildFactory(int x1, int y1, int x2, int y2) {

        Base b = (Base) mapPanel.map.getCell(x1, y1).getBuilding();
        Cell c = mapPanel.map.getCell(x2, y2);

        b.buildFactory(mapPanel.map, c, false);
        refresh();
    }

    /**
     * Method to repaint the Map and the UI
     */
    @SuppressWarnings("static-access")
	private void refresh() {
        mapPanel.redrawMap();
        mapPanel.game.uiInterfaceBottom.resetToDefault();
        mapPanel.game.uiInterfaceBottom.repaint();
    }
}
