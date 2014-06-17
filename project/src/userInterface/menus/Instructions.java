package userInterface.menus;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Window.Type;

/**
 * Instructions window for the game
 * @author Matt
 *
 */
public class Instructions {

	private JFrame frmInstructions;

	/**
	 * Create the application.
	 */
	public Instructions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInstructions = new JFrame();
		frmInstructions.setType(Type.UTILITY);
		frmInstructions.setResizable(false);
		frmInstructions.setVisible(true);
		frmInstructions.setTitle("Instructions");
		frmInstructions.setBounds(100, 100, 817, 638);
		frmInstructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Instructions.class.getResource("/imgs/menu/Instructions.fw.png")));
		frmInstructions.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		frmInstructions.pack();
	}

}
