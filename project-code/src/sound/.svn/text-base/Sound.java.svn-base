package sound;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class for playing sounds
 * 
 * @author Alex
 * 
 */
public class Sound {

	String track;
	SoundThread st = generateThread();

	/**
	 * Plays the current track.
	 */
	public void play() {
		st.track = track;
		st.run();
	}

	/**
	 * Stops the playing of sounds
	 */
	public void stop() {
		st.stop();
	}

	/**
	 * Creates a new sound thread
	 * 
	 * @return soundThread : SoundThread - the created sound thread
	 */
	public SoundThread generateThread() {
		SoundThread st = new SoundThread();

		return st;
	}

	/**
	 * Sets the track based on the parameters input from the sound tracks
	 * directory
	 * 
	 * @param track
	 *            : String - the track to play
	 */
	public void setTrackFromAlbum(String track) {
		switch (track) {
		case "main_menu":
			this.track = "/sound/tracks/main_menu.wav";
			break;
		case "game_music":
			this.track = "/sound/tracks/game_music.wav";

		}
	}

	/**
	 * Sets the track to play
	 * 
	 * @param track
	 *            : String - the track name
	 */
	public void setTrack(String track) {
		this.track = track;
	}

	/**
	 * Sound thread object
	 * @author Alex
	 *
	 */
	public class SoundThread implements Runnable {

		public String track = "";
		Clip clip;

		@Override
		public void run() {
			AudioInputStream audioIn = null;
			try {
				audioIn = AudioSystem.getAudioInputStream(getClass()
						.getResource(track));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clip.open(audioIn);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.start();

		}

		public void stop() {
			clip.stop();
		}

	}

}
