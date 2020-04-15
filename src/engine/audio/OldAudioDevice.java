package engine.audio;

public abstract class OldAudioDevice {

	/**
	 *
	 *
	 * @param filename File's name
	 * @return
	 */
	protected abstract OldAudioData createAudioFromFile(final String filename);

	/**
	 *
	 *
	 * @param audioData
	 */
	protected abstract void releaseAudio(final OldAudioData audioData);

}