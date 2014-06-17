package networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Robert
 * Class that is used for new connections listener
 */
public class AcceptConnections extends Thread {
	ServerListen serverListen;
	public Socket connection;
	private boolean stopThread = false;
	@Override 
	public void run() {
		System.out.println("server listening at at:" + Server.server.getInetAddress().getHostAddress() 
				+ ":" + Server.server.getLocalPort());
		//listen for connections until game is started
		while(Server.startGame == 0 && stopThread == false) {
			try {
				if(Server.clientThreads.size() >= 4) {
					//System.out.println("4 players connected to the game");
					Server.server.close();
				} else {
					if(Server.server.isClosed() || Server.server == null) {
						Server.server = new ServerSocket(Server.port, 0, InetAddress.getLocalHost());	
					}
					System.out.println("server waiting for connections");
					connection = Server.server.accept();				
					serverListen = new ServerListen(connection);
					serverListen.start();
					Server.clientThreads.add(serverListen);		
				}
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void stopThread() {
		this.stopThread = true;
	}
}
