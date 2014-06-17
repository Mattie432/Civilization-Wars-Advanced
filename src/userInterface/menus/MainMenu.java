package userInterface.menus;

import game.FocusTraversalOnArray;
import game.Game;
import game.GameType;
import game.Teams;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Color;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;

import player.Player;
import player.subclasses.Human;
import sound.Sound;

import javax.swing.JTextArea;

import networking.Client;
import networking.Server;

import javax.swing.JList;

import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 * Main menu class for the game
 * @author Robert, Matt
 */
public class MainMenu {

	public static JFrame frame;
	public static Client client;
	public static Server server;
	Sound sound = new Sound();
	Thread updateClientsList;
	public static Dimension standardDimension = new Dimension(300,440);
	public static Dimension largerDimension = new Dimension(600,600);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);

					frame.setSize(standardDimension);
					frame.setPreferredSize(standardDimension);
					frame.setMinimumSize(standardDimension);
					//frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnknownHostException 
	 */
	public MainMenu() throws UnknownHostException {
		sound.setTrackFromAlbum("main_menu");
		sound.play();
		initialize();
	}

	final JPanel MainMenu_Panel = new JPanel();
	final JPanel SinglePlayer_Panel = new JPanel();
	JPanel HostGame_Panel = new JPanel();
	JPanel Waiting_Panel = new JPanel();
	private JPanel Waiting_Panel_Host;
	public static Game g;
	public static JPanel CardPanel = new JPanel();

	// Map panels!
	final JPanel panel_19 = new JPanel(); // kingsClashMap
	private JTextField txtPlayerName;
	private JTextField clientName;
	private JTextField txtHostPlayerName;
	public static LinkedList<Player> playerOrder =  new LinkedList<Player>();
	public static String mapNameHost;
	public static GameType gameTypeEnumHost;
	private JTextField hostIp;
	private JTextField typeMessageField;
	public JTextArea userChatMessagesTextArea;
	private JTextField serverChatMessageField;
	public JTextArea serverChatMessagesText;
	@SuppressWarnings("rawtypes")
	protected JList txtJoinedPlayers;
	public static CardLayout cl;
	
	/**
	 * Initialize the contents of the frame.
	 * @throws UnknownHostException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() throws UnknownHostException {
		frame = new JFrame();
		//frame.setBounds(100, 100, 600, 600)\;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(CardPanel, BorderLayout.CENTER);
		CardPanel.setLayout(new CardLayout(0, 0));
		CardPanel.add(MainMenu_Panel, "MainMenu");
		CardPanel.add(SinglePlayer_Panel, "SinglePlayerMenu");

		MainMenu_Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblCivilizationWarsAdvanced = new JLabel(
				"<HTML>\r\n<div style=\"text-align: center\">\r\nCivilization Wars: Advanced<BR>\r\n\u00A92014<BR>\r\n<BR>\r\nCreated by:<BR>\r\nAlexander Bor<BR>\r\nMattie432<BR>\r\nRobert Zlatarski<BR>\r\nDhruvil Tank<BR>\r\nLuke Richardson<BR>\r\n</div>\r\n</HTML>");
		MainMenu_Panel.add(lblCivilizationWarsAdvanced, BorderLayout.SOUTH);
		lblCivilizationWarsAdvanced
				.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label = new JLabel("Civilization Wars: Advanced");
		MainMenu_Panel.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		MainMenu_Panel.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);

		JButton btnNewButton = new JButton("Single Player");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "SinglePlayerMenu");
			}
		});
		panel_4.add(btnNewButton);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(10);
		panel_2.add(panel_3);

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);

		JButton btnHostGame = new JButton("Host Game");
		btnHostGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "HostGame");
			}
		});
		panel_5.add(btnHostGame);

		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);

		JButton btnNewButton_1 = new JButton("Join Game");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "JoinGame");

			}
		});
		panel_6.add(btnNewButton_1);
		
		JPanel panel_50 = new JPanel();
		panel_50.setPreferredSize(new Dimension(10, 25));
		panel_2.add(panel_50);
		
		JPanel panel_51 = new JPanel();
		panel_2.add(panel_51);
		
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				Instructions instructions = new Instructions();
			}
		});
		panel_51.add(btnInstructions);
		SinglePlayer_Panel.setLayout(new BorderLayout(0, 0));

		JLabel lblSinglePlayer = new JLabel("Single Player");
		lblSinglePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		SinglePlayer_Panel.add(lblSinglePlayer, BorderLayout.NORTH);

		JPanel panel_7 = new JPanel();
		SinglePlayer_Panel.add(panel_7, BorderLayout.CENTER);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] { 0, 0 };
		gbl_panel_7.rowHeights = new int[] { 0, 0 };
		gbl_panel_7.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_7.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_7.setLayout(gbl_panel_7);

		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 0;
		panel_7.add(panel_8, gbc_panel_8);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(UIManager.getColor("Button.select"));
		panel_8.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_13.getLayout();
		flowLayout_2.setVgap(10);
		panel_9.add(panel_13);

		final JLabel mapnameLabel = new JLabel("kingsClashMap");
		mapnameLabel.setEnabled(false);
		mapnameLabel.setVisible(false);
		panel_13.add(mapnameLabel);

		JPanel panel_21 = new JPanel();
		panel_9.add(panel_21);

		JLabel lblName = new JLabel("Name:");
		panel_21.add(lblName);

		txtPlayerName = new JTextField();
		txtPlayerName.setToolTipText("Please enter a name...");
		panel_21.add(txtPlayerName);
		txtPlayerName.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Difficulty:");
		panel_10.add(lblNewLabel);

		final JComboBox difficultyComboBox = new JComboBox();
		difficultyComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Easy", "Medium", "Hard" }));
		difficultyComboBox.setSelectedIndex(0);
		panel_10.add(difficultyComboBox);

		JPanel panel_12 = new JPanel();
		panel_9.add(panel_12);

		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11);

		JLabel lblGameType = new JLabel("Game Type:");
		panel_11.add(lblGameType);

		final JComboBox gametypeComboBox = new JComboBox();
		gametypeComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"1 vs 1", "2 vs 2", "Free for all" }));
		gametypeComboBox.setSelectedIndex(0);
		panel_11.add(gametypeComboBox);

		JPanel panel_14 = new JPanel();
		panel_9.add(panel_14);

		JPanel panel_15 = new JPanel();
		panel_9.add(panel_15);

		JLabel lblMap = new JLabel("Map:");
		panel_15.add(lblMap);

		JPanel panel_18 = new JPanel();
		panel_15.add(panel_18);
		GridBagLayout gbl_panel_18 = new GridBagLayout();
		gbl_panel_18.columnWidths = new int[] { 30 };
		gbl_panel_18.rowHeights = new int[] { 30 };
		gbl_panel_18.columnWeights = new double[] { 1.0 };
		gbl_panel_18.rowWeights = new double[] { 1.0 };
		panel_18.setLayout(gbl_panel_18);

		// Map image 1
		final JPanel panel_19 = new JPanel();
		panel_19.setPreferredSize(new Dimension(40, 40));
		panel_19.setMaximumSize(new Dimension(40, 40));
		panel_19.setBackground(Color.LIGHT_GRAY);

		GridBagConstraints gbc_panel_19 = new GridBagConstraints();
		gbc_panel_19.fill = GridBagConstraints.BOTH;
		gbc_panel_19.gridx = 0;
		gbc_panel_19.gridy = 0;
		panel_18.add(panel_19, gbc_panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setSize(new Dimension(40, 40));
		lblNewLabel_1.setPreferredSize(new Dimension(40, 40));
		lblNewLabel_1.setIcon(new ImageIcon(MainMenu.class
				.getResource("/imgs/maps/icons/kingsClashMapIcon.jpg")));
		panel_19.setBorder(new LineBorder(SystemColor.desktop, 2));

		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Image clicked");
				panel_19.setBorder(new LineBorder(SystemColor.desktop, 0));

				panel_19.setBorder(new LineBorder(SystemColor.desktop, 2));
				mapnameLabel.setText("kingsClashMap");

			}
		});

		panel_19.add(lblNewLabel_1, BorderLayout.CENTER);

		JPanel panel_16 = new JPanel();
		panel_9.add(panel_16);

		JPanel panel_17 = new JPanel();
		panel_9.add(panel_17);

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String difficulty = difficultyComboBox.getSelectedItem()
						.toString();
				String gameType = gametypeComboBox.getSelectedItem().toString();
				GameType gameTypeEnum;
				if (gameType == "2 vs 2") {
					gameTypeEnum = GameType.AItype2vs2;
				} else if (gameType == "Free for all") {
					gameTypeEnum = GameType.AItypeFreeForAll;
				} else {
					gameTypeEnum = GameType.AItype1vs1;
				}
				String mapName = mapnameLabel.getText();

				String playerName = txtPlayerName.getText();
				
				if(difficulty == "Easy"){
					GameType.setDifficulty(1);
				}else if(difficulty == "Medium"){
					GameType.setDifficulty(2);
				}else if(difficulty == "Hard"){
					GameType.setDifficulty(3);
				}

				System.out.println("Difficulty = " + difficulty
						+ "\nGameType = " + gameType + "\nMapName = " + mapName
						+ "\nPlayername = " + playerName);

				if (playerName.equals("") || playerName == null) {
					ToolTipManager.sharedInstance().mouseMoved(
							new MouseEvent(txtPlayerName, 0, 0, 0, 0, 0, 0,
									false));

				} else {

					// add this player to teams
					Player player = new Human(playerName);
					Teams.addToEmptyTeam(player);
					Teams.setMe(Teams.getTeamOfPlayer(player));
					playerOrder.add(player);
					sound.stop();
					g = new Game(mapName, gameTypeEnum, playerOrder, player);
				}
			}
		});
		panel_17.add(btnStartGame);

		JPanel panel_20 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_20.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		SinglePlayer_Panel.add(panel_20, BorderLayout.NORTH);

		JButton btnNewButton_2 = new JButton("Main Menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "MainMenu");

				frame.setSize(standardDimension);
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_20.add(btnNewButton_2);

		HostGame_Panel = new JPanel();
		CardPanel.add(HostGame_Panel, "HostGame");
		HostGame_Panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_22 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_22.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		HostGame_Panel.add(panel_22, BorderLayout.NORTH);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "MainMenu");
				frame.setSize(standardDimension);
			}
		});
		panel_22.add(btnMainMenu);

		JLabel lblHostGameMenu = new JLabel("Host Game Menu");
		panel_22.add(lblHostGameMenu);

		JPanel panel_23 = new JPanel();
		HostGame_Panel.add(panel_23, BorderLayout.CENTER);
		GridBagLayout gbl_panel_23 = new GridBagLayout();
		gbl_panel_23.columnWidths = new int[] { 0, 0 };
		gbl_panel_23.rowHeights = new int[] { 0, 0 };
		gbl_panel_23.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_23.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_23.setLayout(gbl_panel_23);

		JPanel panel_24 = new JPanel();
		GridBagConstraints gbc_panel_24 = new GridBagConstraints();
		gbc_panel_24.fill = GridBagConstraints.BOTH;
		gbc_panel_24.gridx = 0;
		gbc_panel_24.gridy = 0;
		panel_23.add(panel_24, gbc_panel_24);

		JPanel panel_25 = new JPanel();
		panel_24.add(panel_25);
		panel_25.setLayout(new BoxLayout(panel_25, BoxLayout.Y_AXIS));

		JPanel panel_32 = new JPanel();
		panel_25.add(panel_32);

		final JLabel lblHostGameMapName = new JLabel("kingsClashMap");
		lblHostGameMapName.setVisible(false);
		lblHostGameMapName.setEnabled(false);
		panel_32.add(lblHostGameMapName);

		JPanel panel_49 = new JPanel();
		panel_25.add(panel_49);

		JLabel lblPlayerName = new JLabel("Player Name:");
		panel_49.add(lblPlayerName);

		txtHostPlayerName = new JTextField();
		panel_49.add(txtHostPlayerName);
		txtHostPlayerName.setColumns(10);

		JPanel panel_31 = new JPanel();
		panel_25.add(panel_31);

		JPanel panel_26 = new JPanel();
		panel_25.add(panel_26);

		JLabel lblNewLabel_2 = new JLabel("Game Type:");
		panel_26.add(lblNewLabel_2);

		final JComboBox gameTypeHost = new JComboBox();
		gameTypeHost.setModel(new DefaultComboBoxModel(new String[] { "Free for all" }));
		gameTypeHost.setSelectedIndex(0);
		panel_26.add(gameTypeHost);

		JPanel panel_27 = new JPanel();
		panel_25.add(panel_27);

		JPanel panel_28 = new JPanel();
		panel_25.add(panel_28);

		JLabel lblMap_1 = new JLabel("Map:");
		panel_28.add(lblMap_1);

		JPanel panel_29 = new JPanel();
		panel_28.add(panel_29);
		GridBagLayout gbl_panel_29 = new GridBagLayout();
		gbl_panel_29.columnWidths = new int[] { 30 };
		gbl_panel_29.rowHeights = new int[] { 30 };
		gbl_panel_29.columnWeights = new double[] { 0.0 };
		gbl_panel_29.rowWeights = new double[] { 0.0 };
		panel_29.setLayout(gbl_panel_29);

		final JPanel panel_30 = new JPanel();
		panel_30.setBorder(new LineBorder(Color.GREEN));
		panel_30.setPreferredSize(new Dimension(40, 40));
		panel_30.setSize(new Dimension(30, 30));
		GridBagConstraints gbc_panel_30 = new GridBagConstraints();
		gbc_panel_30.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_30.gridx = 0;
		gbc_panel_30.gridy = 0;
		panel_29.add(panel_30, gbc_panel_30);
		panel_30.setLayout(new BorderLayout(0, 0));

		JLabel lblHostMap1 = new JLabel("");
		lblHostMap1.setIcon(new ImageIcon(MainMenu.class
				.getResource("/imgs/maps/icons/kingsClashMapIcon.jpg")));
		panel_30.add(lblHostMap1);
		lblHostMap1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Image clicked");
				panel_30.setBorder(new LineBorder(SystemColor.desktop, 0));

				panel_30.setBorder(new LineBorder(SystemColor.desktop, 2));
				lblHostGameMapName.setText("kingsClashMap");

			}
		});
		JPanel panel_33 = new JPanel();
		panel_25.add(panel_33);

		JPanel panel_34 = new JPanel();
		panel_25.add(panel_34);

		JButton btnHostGame_1 = new JButton("Host Game");
		
		
		btnHostGame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String playerName = txtHostPlayerName.getText();

				// System.out.println("GameType = " + gameTypeHosStr +
				// "\nMapName = "
				// + mapName + "\nPlayername = " + playerName);

				if (playerName.equals("") || playerName == null) {
					ToolTipManager.sharedInstance().mouseMoved(
							new MouseEvent(txtPlayerName, 0, 0, 0, 0, 0, 0,
									false));

				} else {

					// add this player to teams
				//	Player player = new Human(playerName);
					//Teams.addToEmptyTeam(player);
				//	Teams.setMe(Teams.getTeamOfPlayer(player));
				//	playerOrder.add(player);

					CardLayout cl = (CardLayout) (CardPanel.getLayout());
					cl.show(CardPanel, "Waiting_Host");
					frame.setSize(largerDimension);
					
					server = new Server();
					server.startServer();
					updateClientsList.start();
					
					try {
						client = new Client(InetAddress.getLocalHost().getHostAddress(), 4567,
								playerName, MainMenu.this);
					} catch (UnknownHostException e1) {
						System.out.println(e1.getMessage());
					}
					client.connectToServer();
					client.start();	
				}
				frame.setSize(new Dimension(620,620));

			}
		});
		panel_34.add(btnHostGame_1);

		JLabel lblIpAddress = new JLabel("Ip Address: " + InetAddress.getLocalHost().getHostAddress());
		HostGame_Panel.add(lblIpAddress, BorderLayout.SOUTH);

		JPanel JoinGame_Panel = new JPanel();
		CardPanel.add(JoinGame_Panel, "JoinGame");
		JoinGame_Panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_35 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_35.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		JoinGame_Panel.add(panel_35, BorderLayout.NORTH);

		JButton btnMainMenu_1 = new JButton("Main Menu");
		btnMainMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "MainMenu");
				frame.setSize(standardDimension);
			}
		});
		panel_35.add(btnMainMenu_1);

		JLabel lblJoinGame = new JLabel("Join Game");
		panel_35.add(lblJoinGame);

		JPanel panel_36 = new JPanel();
		JoinGame_Panel.add(panel_36, BorderLayout.CENTER);
		panel_36.setLayout(new BorderLayout(0, 0));

		JPanel panel_37 = new JPanel();
		panel_36.add(panel_37);

		JPanel panel_38 = new JPanel();
		panel_37.add(panel_38);
		panel_38.setLayout(new BoxLayout(panel_38, BoxLayout.Y_AXIS));

		JPanel panel_39 = new JPanel();
		panel_38.add(panel_39);
		
		JLabel label_1 = new JLabel("Hosts IP:");
		panel_39.add(label_1);
		
		hostIp = new JTextField();
		hostIp.setColumns(10);
		panel_39.add(hostIp);

		JPanel panel_40 = new JPanel();
		panel_38.add(panel_40);
		
				JLabel lblNewLabel_3 = new JLabel("Nickname:");
				panel_40.add(lblNewLabel_3);
				
						clientName = new JTextField();
						panel_40.add(clientName);
						clientName.setColumns(10);

		JPanel panel_41 = new JPanel();
		panel_38.add(panel_41);

		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				client = new Client(hostIp.getText(), 4567, clientName.getText(), MainMenu.this);
				client.connectToServer();
				client.start();
				
				//Player player = new Player(txtHostPlayerName
						//.getText());
				//playerOrder.add(player);
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "WaitingClient");
				frame.setSize(new Dimension(620,620));
			}
		});
		panel_41.add(btnJoinGame);

		JLabel lblIpAddress_1 = new JLabel("Ip Address: "+ InetAddress.getLocalHost().getHostAddress());
		JoinGame_Panel.add(lblIpAddress_1, BorderLayout.SOUTH);

		Waiting_Panel_Host = new JPanel();
		CardPanel.add(Waiting_Panel_Host, "Waiting_Host");
		Waiting_Panel_Host.setLayout(new BorderLayout(0, 0));

		JPanel panel_42 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_42.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		Waiting_Panel_Host.add(panel_42, BorderLayout.NORTH);

		JPanel panel_43 = new JPanel();
		Waiting_Panel_Host.add(panel_43, BorderLayout.CENTER);

		JPanel panel_44 = new JPanel();

		JPanel panel_45 = new JPanel();
		panel_44.add(panel_45);
		panel_45.setLayout(new BoxLayout(panel_45, BoxLayout.Y_AXIS));

		final JPanel panel_46 = new JPanel();
		panel_45.add(panel_46);
		
	
		
		updateClientsList = new Thread() {
			
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				
				DefaultListModel list_clients_model = server.list_clients_model;
				txtJoinedPlayers = new JList(list_clients_model);
				txtJoinedPlayers.addListSelectionListener(new ListSelectionListener()
				{
					@Override
					public void valueChanged(ListSelectionEvent e)
					{
						if (e.getValueIsAdjusting())
						{
							System.out.println(txtJoinedPlayers.getSelectedIndex());
						}
					}
				});
				
				panel_46.add(txtJoinedPlayers);
			}
		};

		JPanel panel_48 = new JPanel();
		panel_45.add(panel_48);

		JButton btnStartGame_1 = new JButton("Start Game");
		btnStartGame_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				String difficulty = difficultyComboBox.getSelectedItem()
						.toString();
				String gameType = gameTypeHost.getSelectedItem().toString();
				if (gameType == "2 vs 2") {
					gameTypeEnumHost = GameType.type2vs2;
				} else if (gameType == "Free for all") {
					gameTypeEnumHost = GameType.typeFreeForAll;
				} else {
					gameTypeEnumHost = GameType.type1vs1;
				}
				mapNameHost = mapnameLabel.getText();

				String playerName = txtHostPlayerName.getText();
				
				System.out.println("Difficulty = " + difficulty
						+ "\nGameType = " + gameType + "\nMapName = " + mapNameHost
						+ "\nPlayername = " + playerName);

				if (playerName.equals("") || playerName == null) {
					ToolTipManager.sharedInstance().mouseMoved(
							new MouseEvent(txtPlayerName, 0, 0, 0, 0, 0, 0,
									false));
				} else {
					//g = new Game(mapNameHost, gameTypeEnumHost, playerOrder);
					System.out.println(gameTypeEnumHost);
					server.startGame = 1;
					System.out.println("playerorder" + playerOrder.size());
					System.out.println(Server.players + "players number!!");
					server.startGame(mapNameHost, gameTypeEnumHost, Server.players);
				}
			}
		});
		panel_48.add(btnStartGame_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		serverChatMessageField = new JTextField();
		serverChatMessageField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					if(serverChatMessageField.getText().equals("")) {
						
					} else {
						client.sendMessage(serverChatMessageField.getText());
						serverChatMessagesText.append( txtHostPlayerName.getText()+ ": "+ serverChatMessageField.getText() + "\n");
						serverChatMessageField.setText("");
					}
					
				}
			}
		});
		serverChatMessageField.setColumns(10);
		serverChatMessagesText = new JTextArea();
		serverChatMessagesText.setEditable(false);
		scrollPane_1.setViewportView(serverChatMessagesText);
		JButton serverChatSendButton = new JButton("Send");
		serverChatSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(serverChatMessageField.getText().equals("")) {
					
				} else {
					client.sendMessage(serverChatMessageField.getText());
					serverChatMessagesText.append( txtHostPlayerName.getText()+ ": "+ serverChatMessageField.getText() + "\n");
					serverChatMessageField.setText("");
				}
				
			}
		});
		
				JButton btnMainMenu_2 = new JButton("Main Menu");
				btnMainMenu_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cl = (CardLayout) (CardPanel.getLayout());
						cl.show(CardPanel, "MainMenu");
						frame.setSize(standardDimension);
					}
				});
				JLabel lblWaitingForPlayers = new JLabel("Waiting for players");
		
		JButton kickPlayer = new JButton("Kick");
		kickPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = txtJoinedPlayers.getSelectedIndex();				
				if(index != 0) {
					Server.notifyClient(3, index); //kicked
				//	Server.list_clients_model.removeElementAt(index);
				//	Server.clientThreads.get(index).stopThread();
				//	Server.clientThreads.remove(index);
				//	Server.players.remove(index);
				}
			}
		});
		GroupLayout gl_panel_43 = new GroupLayout(panel_43);
		gl_panel_43.setHorizontalGroup(
			gl_panel_43.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_43.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_43.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_43.createSequentialGroup()
							.addGroup(gl_panel_43.createParallelGroup(Alignment.LEADING)
								.addComponent(serverChatMessageField, 422, 422, 422)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_43.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_43.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_44, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
									.addComponent(serverChatSendButton, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel_43.createSequentialGroup()
									.addComponent(kickPlayer)
									.addGap(46))))
						.addGroup(gl_panel_43.createSequentialGroup()
							.addComponent(btnMainMenu_2)
							.addGap(82)
							.addComponent(lblWaitingForPlayers)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_panel_43.setVerticalGroup(
			gl_panel_43.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_43.createSequentialGroup()
					.addGroup(gl_panel_43.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWaitingForPlayers)
						.addComponent(btnMainMenu_2))
					.addGap(29)
					.addGroup(gl_panel_43.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_43.createSequentialGroup()
							.addComponent(panel_44, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(kickPlayer))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_43.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(serverChatSendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(serverChatMessageField, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		
		panel_43.setLayout(gl_panel_43);

		JLabel lblIpAddress_2 = new JLabel("IP Address: " + InetAddress.getLocalHost().getHostAddress());
		Waiting_Panel_Host.add(lblIpAddress_2, BorderLayout.SOUTH);
		
		JPanel waitingClient = new JPanel();
		CardPanel.add(waitingClient, "WaitingClient");
		waitingClient.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_53 = new JPanel();
		waitingClient.add(panel_53, BorderLayout.NORTH);
		
		JButton button = new JButton("Main Menu");
		button.addActionListener(new ActionListener() {
			
			
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout) (CardPanel.getLayout());
					cl.show(CardPanel, "MainMenu");
					frame.setSize(standardDimension);
				}
		});
		JLabel lblWaitingForHost1 = new JLabel("Waiting to start");
		GroupLayout gl_panel_53 = new GroupLayout(panel_53);
		gl_panel_53.setHorizontalGroup(
			gl_panel_53.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_53.createSequentialGroup()
					.addContainerGap()
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblWaitingForHost1)
					.addGap(405))
		);
		gl_panel_53.setVerticalGroup(
			gl_panel_53.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_53.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_53.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(lblWaitingForHost1)))
		);
		panel_53.setLayout(gl_panel_53);
		
		JPanel panel_55 = new JPanel();
		panel_55.setEnabled(false);
		panel_55.setFocusable(false);
		JLabel welcomePlayerLabel = new JLabel("Welcome! Please wait for the game to start.");
		welcomePlayerLabel.setBounds(0, 240, 253, 16);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(253, 236, 93, 25);
		
		btnDisconnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.disconnect();	
				
				CardLayout cl = (CardLayout) (CardPanel.getLayout());
				cl.show(CardPanel, "MainMenu");
			}
		});
		
		waitingClient.add(panel_55);
		
		JScrollPane scrollPane = new JScrollPane();
		
		typeMessageField = new JTextField();
		typeMessageField.setColumns(10);
		typeMessageField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(typeMessageField.getText().equals("")) {
					
				} else {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						client.sendMessage(typeMessageField.getText());
						userChatMessagesTextArea.append( Client.me.getPlayerName() + ": "+ typeMessageField.getText() + "\n");
						typeMessageField.setText("");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
		
		JButton sendMessageButton = new JButton("Send");
		sendMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(typeMessageField.getText().equals("")) {
					
				} else {
					client.sendMessage(typeMessageField.getText());
					userChatMessagesTextArea.append( Client.me.getPlayerName() + ": "+ typeMessageField.getText() + "\n");
					typeMessageField.setText("");
				}
				
			}
		});
		
		sendMessageButton.setActionCommand("");
		GroupLayout gl_panel_55 = new GroupLayout(panel_55);
		gl_panel_55.setHorizontalGroup(
			gl_panel_55.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_55.createSequentialGroup()
					.addGroup(gl_panel_55.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_55.createSequentialGroup()
							.addGap(115)
							.addComponent(welcomePlayerLabel)
							.addGap(5)
							.addComponent(btnDisconnect))
						.addGroup(gl_panel_55.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_55.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(typeMessageField, Alignment.LEADING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sendMessageButton, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_55.setVerticalGroup(
			gl_panel_55.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_55.createSequentialGroup()
					.addGroup(gl_panel_55.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_55.createSequentialGroup()
							.addGap(9)
							.addComponent(welcomePlayerLabel))
						.addGroup(gl_panel_55.createSequentialGroup()
							.addGap(5)
							.addComponent(btnDisconnect)))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_55.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeMessageField, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(sendMessageButton, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		userChatMessagesTextArea = new JTextArea();
		
		userChatMessagesTextArea.setEditable(false);
		userChatMessagesTextArea.setBounds(new Rectangle(0, 0, 200, 200));
		scrollPane.setViewportView(userChatMessagesTextArea);
		panel_55.setLayout(gl_panel_55);
		CardPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { MainMenu_Panel, SinglePlayer_Panel }));

	}
}
