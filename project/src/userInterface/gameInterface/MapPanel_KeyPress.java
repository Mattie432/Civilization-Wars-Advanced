package userInterface.gameInterface;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Class to handle key press events for the map pannel.
 * 
 * @author Matt
 * 
 */
public class MapPanel_KeyPress {

	/**
	 * Constructor for the class. This sets the keypress events for the map
	 * pannel.
	 * 
	 * @param mapPanel
	 *            : MapPanel - the map panel object
	 */
	@SuppressWarnings("serial")
	public MapPanel_KeyPress(final MapPanel mapPanel) {

		InputMap im = mapPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = mapPanel.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "goRight");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "goLeft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "goUp");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "goDown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "plus");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "minus");
		am.put("goRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.invalidate();
				mapPanel.moveView(mapPanel.movementDistance, 0);
				mapPanel.repaint();
			}

		});
		am.put("goLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.invalidate();
				mapPanel.moveView(-mapPanel.movementDistance, 0);
				mapPanel.repaint();
			}
		});

		am.put("goUp", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.invalidate();
				mapPanel.moveView(0, -mapPanel.movementDistance);
				mapPanel.repaint();
			}
		});
		am.put("goDown", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.invalidate();
				mapPanel.moveView(0, mapPanel.movementDistance);
				mapPanel.repaint();
			}
		});
		am.put("plus", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.invalidate();
				if (mapPanel.zoom + 0.1f <= 1) {
					mapPanel.zoom += 0.1f;
				}
				mapPanel.scaleMap();
				mapPanel.repaint();
			}
		});
		am.put("minus", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.invalidate();
				if (mapPanel.zoom - 0.1f > 0.2) {
					mapPanel.zoom -= 0.1f;
				}
				mapPanel.scaleMap();
				mapPanel.repaint();
			}
		});

	}

}
