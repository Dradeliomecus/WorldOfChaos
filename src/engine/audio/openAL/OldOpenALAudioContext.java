package engine.audio.openAL;

import engine.audio.OldAudioContext;
import engine.audio.OldAudioObject;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

import java.util.ArrayList;

import static org.lwjgl.openal.AL10.alGetError;

public class OldOpenALAudioContext extends OldAudioContext {

	// TODO: SDL_AudioDeviceID m_device;

	/**
	 *
	 */
	final private ArrayList<Float> stream;

	/**
	 * List of all audio that is currently being played.
	 */
	final private ArrayList<OldAudioObject> playingAudio;

	/**
	 * Creates a new OpenALAudioContext.
	 */
	public OldOpenALAudioContext() {
		super();

		// TODO: Don't hardcore these values !
		try{
			AL.create(null, 44100, 15, false);
		} catch(final LWJGLException e) {
			e.printStackTrace();
		}
		alGetError();

		this.stream = new ArrayList<>();
		this.playingAudio = new ArrayList<>();
	}


	@Override
	protected void playAudio(final OldAudioObject audio) {

	}

	@Override
	protected void pauseAudio(final OldAudioObject audio) {

	}

	@Override
	protected void stopAudio(final OldAudioObject audio) {

	}

	/**
	 *
	 *
	 * @param stream
	 * @param streamLength
	 */
	private void generateSamples(final int stream, final int streamLength) {

	}

	/**
	 * Removes an audio from the playing list.
	 *
	 * @param audio Audio Object to remove
	 */
	private void removeAudio(final OldAudioObject audio) {
		this.playingAudio.remove(audio);
	}

}