package map;

import game.Teams;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import map.terrain.Terrain;
import building.Building;
import building.subclasses.*;
import map.terrain.subclasses.Flat;
import unit.subclasses.*;
import unit.Unit;
import userInterface.gameInterface.MapPanel;

/**
 * Class for the constructor and methods for a Cell
 * @author Matt, Dhruvil, Alex, Robert
 */
public class Cell {

    private static final boolean drawHealthBars = true;
    private Terrain terrain;
    public Unit unit;
    public Building building;

    private int cellPosX;
    private int cellPosY;
    
    private Cell previousCell;

    /**
     * Default constructor for the class. This creates a cell with no
     * units/buildings on it.
     *
     * @param terrain : Terrain - the terrain of the cell
     * @param cellPosX : int - the X position of the cell
     * @param cellPosY : int - the Y position of the cell
     */
    public Cell(Terrain terrain, int cellPosX, int cellPosY) {
        this.setTerrain(terrain);
        this.setCellPosX(cellPosX);
        this.setCellPosY(cellPosY);

        // set building/unit to null
        this.building = null;
        this.unit = null;
    }

    /**
     * Seconday constructor for the class. This creates a cell with units &
     * buildings. If either one is not present then set the input parameters to
     * <i>null</i>.
     *
     * @param terrain : Terrain - the terrain of the cell
     * @param cellPosX : int - the X position of the cell
     * @param cellPosY : int - the Y position of the cell
     * @param building : Building - the building on the cell.
     * @param unit : Unit - the unit on the cell.
     */
    public Cell(Terrain terrain, int cellPosX, int cellPosY, Building building,
            Unit unit) {
        this.setTerrain(terrain);
        this.setCellPosX(cellPosX);
        this.setCellPosY(cellPosY);
        this.building = building;
        this.unit = unit;
    }

    /**
     * Gets the terrain of the cell.
     *
     * @return terrain : Terrain - the cell terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Sets the terrain of the cell. <b>Should be set in constructor</b>
     *
     * @param terrain : Terrain - the terrain to set the cell to.
     */
    private void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * Gets the unit present on the cell. If no unit present returns null.
     *
     * @return unit : Unit - any unit present on the cell
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the unit present on the cell.
     *
     * @param unit : Unit - the unit to set as present
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets the building present on the cell. If no unit present returns null.
     *
     * @return building : Building - any building present on the cell
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the building present on the cell.
     *
     * @param building : Building - the building to cell as present
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Gets the cells X position in the map
     *
     * @return cellPosX : int - the cells X position.
     */
    public int getCellPosX() {
        return cellPosX;
    }

    /**
     * Sets the cells X position in the map. <b>Should be set in constructor</b>
     *
     * @param cellPosX : int - the cells X position.
     */
    private void setCellPosX(int cellPosX) {
        this.cellPosX = cellPosX;
    }

    /**
     * Gets the cells Y postition in the map.
     *
     * @return cellPosY : int - the cells Y position
     */
    public int getCellPosY() {
        return cellPosY;
    }

    /**
     * Sets the cells Y position in the map. <b>Should be set in constructor</b>
     *
     * @param cellPosY : int - the cells Y position.
     */
    private void setCellPosY(int cellPosY) {
        this.cellPosY = cellPosY;
    }

    /**
     * Draws this cell with the graphics object g in the correct place.
     *
     * @param gPanel : Graphics - Graphics to draw with.
     * @param cellHeight : float - the cell heiht in px
     * @param panel : MapPanel - the mappanel object
     */
    public void draw(Graphics gPanel, float cellHeight, MapPanel panel) {
        String drawImageName = "";
        Color color = null;
        boolean draw = false;
        
        if(getBuilding() != null){
        	drawImageName = getBuilding().getImageLocation();
        }else if(getUnit() != null){
        	drawImageName = getUnit().getImageLocation();
        }
        
        if (getBuilding() instanceof Base) {
            color = Teams.getTeamColor(Teams.getTeamOfPlayer(getBuilding()
                    .getOwner()));
            draw = true;
        } else if (getBuilding() instanceof Factory) {
            color = Teams.getTeamColor(Teams.getTeamOfPlayer(getBuilding()
                    .getOwner()));
            draw = true;
        } else if (getBuilding() instanceof Barracks) {
            color = Teams.getTeamColor(Teams.getTeamOfPlayer(getBuilding()
                    .getOwner()));
            draw = true;
        } else if (getUnit() instanceof Marine) {
            color = Teams.getTeamColor(Teams.getTeamOfPlayer(getUnit()
                    .getOwner()));
            draw = true;
        } else if (getUnit() instanceof Tank) {
            color = Teams.getTeamColor(Teams.getTeamOfPlayer(getUnit()
                    .getOwner()));
            draw = true;
        }

        if (draw) {
            BufferedImage originalImage = new BufferedImage(90, 90,
                    BufferedImage.TYPE_INT_RGB);
            try {
                BufferedImage rawImage = new BufferedImage(90, 90,
                        BufferedImage.TYPE_INT_RGB);
                rawImage = ImageIO.read(getClass().getResource(
                        drawImageName));

				// tint image
                BufferedImage mask = generateMask(rawImage, color, 0.3f);
                originalImage = tint(rawImage, mask);

            } catch (IOException e1) {
                System.out.println("Error getting map image!");
            }

            // draw health bar
            if (getUnit() != null && drawHealthBars) {
                float healthScale = getUnit().getCurrentHealth()
                        / (float) getUnit().getBaseHealth();
                Graphics g = originalImage.getGraphics();
                int offset = 10;
                g.drawRect(offset, 5, (int) ((90 - 2 * offset)), 10);
                g.setColor(Color.green);
                g.fillRect(offset + 1, 5 + 1,
                        (int) ((90 - 2 * offset) * healthScale) - 1, 10 - 1);
            } else if (getBuilding() != null && drawHealthBars) {
                float healthScale = getBuilding().getCurrentHealth()
                        / (float) getBuilding().getBaseHealth();
                Graphics g = originalImage.getGraphics();
                int offset = 10;
                g.drawRect(offset, 5, (int) ((90 - 2 * offset)), 10);
                g.setColor(Color.green);
                g.fillRect(offset + 1, 5 + 1,
                        (int) ((90 - 2 * offset) * healthScale) - 1, 10 - 1);
            }

            // scaled image height
            int newH = (int) (90 * panel.getZoom());

            // draws scaled image
            gPanel.drawImage(originalImage,
                    (int) (this.getCellPosX() * cellHeight),
                    (int) (this.getCellPosY() * cellHeight), newH, newH, null);

        }

    }

    /**
     * Gets the surrounging cells of this cell.
     * @param map : Map - the map object
     * @param corners : Boolean - whether to get the surrounding 8 cells or 4.
     * @return cells : Queue<Cell> - the surrounding cells
     */
    public Queue<Cell> getSurroundingCells(Map map, boolean corners) {
        Queue<Cell> cells = new LinkedList<Cell>();

        int x = getCellPosX();
        int y = getCellPosY();

        if (x != 0) {
            // left side has space
            cells.add(map.getCell(x - 1, y));
        }
        if (x != map.getNumberOfCellsAcross() - 1) {
            // right side has space
            cells.add(map.getCell(x + 1, y));
        }
        if (y != 0) {
            // top side has space
            cells.add(map.getCell(x, y - 1));
        }
        if (y != map.getNumberOfCellsAcross() - 1) {
            // bottom side has space
            cells.add(map.getCell(x, y + 1));
        }
        
        if (corners) {
            if (x != 0 && y != 0) {
                cells.add(map.getCell(x - 1, y - 1));
            }
            if (x != 0 && y != map.getNumberOfCellsAcross() - 1) {
                cells.add(map.getCell(x - 1, y + 1));
            }
            if (y != 0 && x != map.getNumberOfCellsAcross() - 1) {
                cells.add(map.getCell(x + 1, y - 1));
            }
            if(y != map.getNumberOfCellsAcross() && x != map.getNumberOfCellsAcross() - 1) {
                cells.add(map.getCell(x + 1, y + 1));
            }
        }
        
        return cells;

    }

    /**
     * Returns true if the unit can move to this cell. This method checks if the
     * cell is empty and if the terrain is passable for the unit.
     *
     * @param unit : Unit - the unit to check movement for
     * @return result : Boolean - if the unit can move to this cell
     */
    public boolean canMoveUnitTo(Unit unit) {
        if (getUnit() == null && getBuilding() == null
                && getTerrain().checkAllowedUnits(unit)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * checks if the cell can allow buildings to be build.
     * @param building : The building to check if it can be build
     * @return canBuild : Boolean - if it can be built on
     */
    public boolean canBuildOn(Building building) {
        if (getBuilding() == null && getUnit() == null 
                && getTerrain() instanceof Flat) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the unit is able to attack this cell.
     * @param attackingUnit : Unit - the attacking unit
     * @return answer : Boolean - can it be attacked
     */
    public boolean canAttackCell(Unit attackingUnit) {
        // attackable unit

        if (getUnit() != null) {
            // check not on friendly teams
            if (!Teams.getTeamFriends(
                    Teams.getTeamOfPlayer(attackingUnit.getOwner())).contains(
                            getUnit().getOwner())) {
                return true;
            } else {
                return false;
            }

            // attackable unit
        } else if (getBuilding() != null) {

            if (!Teams.getTeamFriends(
                    Teams.getTeamOfPlayer(attackingUnit.getOwner())).contains(
                            getBuilding().getOwner())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Gets the graphics configuration. Refrence :
     * http://stackoverflow.com/a/14225857
     *
     * @return GraphicsConfiguration : GraphicsConfiguration - the graphics
     * configuration
     */
    public static GraphicsConfiguration getGraphicsConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
    }

    /**
     * Creates a compatable image to tint. Refrence :
     * http://stackoverflow.com/a/14225857
     *
     * @param width : Int - img width
     * @param height : Int - img height
     * @param transparency : int - transparency specified transparancy mode
     * @return img : BufferedImage - compatible image
     */
    public static BufferedImage createCompatibleImage(int width, int height,
            int transparency) {
        BufferedImage image = getGraphicsConfiguration().createCompatibleImage(
                width, height, transparency);
        image.coerceData(true);
        return image;
    }

    /**
     * Applies the rendering hints to the graphics object. Refrence :
     * http://stackoverflow.com/a/14225857
     *
     * @param g2d : Graphics2D - graphics object
     */
    public static void applyQualityRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
    }

    /**
     * Generates the mask for the image. This will be used to tint the image.
     * Refrence : http://stackoverflow.com/a/14225857
     *
     * @param imgSource : BufferedImage - the original image
     * @param color : Color - the color to tint to
     * @param alpha : float - the transparancy rate
     * @return img : BufferedImage - the mask
     */
    public static BufferedImage generateMask(BufferedImage imgSource,
            Color color, float alpha) {
        int imgWidth = imgSource.getWidth();
        int imgHeight = imgSource.getHeight();

        BufferedImage imgMask = createCompatibleImage(imgWidth, imgHeight,
                Transparency.TRANSLUCENT);
        Graphics2D g2 = imgMask.createGraphics();
        applyQualityRenderingHints(g2);

        g2.drawImage(imgSource, 0, 0, null);
        g2.setComposite(AlphaComposite
                .getInstance(AlphaComposite.SRC_IN, alpha));
        g2.setColor(color);

        g2.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight());
        g2.dispose();

        return imgMask;
    }

    /**
     * Tints the image with the mask. Refrence :
     * http://stackoverflow.com/a/14225857
     *
     * @param master : BufferedImage - the original image
     * @param tint : BufferedImage - the mask to apply
     * @return img : BufferedImage - the tinted image
     */
    public BufferedImage tint(BufferedImage master, BufferedImage tint) {
        int imgWidth = master.getWidth();
        int imgHeight = master.getHeight();

        BufferedImage tinted = createCompatibleImage(imgWidth, imgHeight,
                Transparency.TRANSLUCENT);
        Graphics2D g2 = tinted.createGraphics();
        applyQualityRenderingHints(g2);
        g2.drawImage(master, 0, 0, null);
        g2.drawImage(tint, 0, 0, null);
        g2.dispose();

        return tinted;
    }

    /**
     * Gets the previous cell, used in the search by the AI for enemy units.
     * @return previousCell : Cell - the previous cell
     */
	public Cell getPreviousCell() {
		return previousCell;
	}

	/**
	 * Sets the previous cell, used in the search by the AI for enemy units.
	 * @param previousCell : Cell - the cell to set as the previouscell
	 */
	public void setPreviousCell(Cell previousCell) {
		this.previousCell = previousCell;
	}

}
