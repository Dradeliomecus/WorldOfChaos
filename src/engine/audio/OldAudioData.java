package engine.audio;

public abstract class OldAudioData {

	/**
	 * Creates a new AudioData instance.
	 */
	public OldAudioData() {

	}

	/**
	 *
	 *
	 * @param stream
	 * @param streamLength
	 * @param pos
	 * @param info
	 * @return
	 */
	protected abstract int generateSamples(final float stream, final int streamLength, final int pos, final OldSampleInfo info);

	/**
	 * Returns the audio length.
	 *
	 * @return
	 */
	protected abstract int getAudioLength();

}