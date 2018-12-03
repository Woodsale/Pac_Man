import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*****************************************************************
Sound enum gives different methods throughout the game the ability
to play sounds using the clip class
@author Team 7
@version Fall 2018
*****************************************************************/
public enum Sound {
		   INTRO("src/intro.wav"),
		   MUNCH("src/munch.wav"),
		   DEATH("src/death.wav");  
	/**	clip object used to play our .wav files */
	private Clip clip;
	/** File object to load the sound files into AudioSystem*/
	File soundFile;
	/** */
	AudioInputStream audioIn;
	/**
	 * Constructor to load the sound file needed into the clip object for the first time
	 * @param soundFileName - .wav file to open
	 */
	Sound(String soundFileName) {
		soundFile = new File(soundFileName);
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
	         clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	/**
	 * Plays the sound clip if another sound clip is not already playing
	 */
	public void play() {
		if(!clip.isActive()) {
			try {
				audioIn = AudioSystem.getAudioInputStream(soundFile);
		         clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audioIn);
		      } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }
			clip.start();
		}
	}
	/**
	 * Stops the current sound clip playing
	 */
	public void stop() {
			clip.stop();
	}
	}
