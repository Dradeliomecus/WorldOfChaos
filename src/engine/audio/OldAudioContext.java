package engine.audio;

public abstract class OldAudioContext {

	/**
	 * Starts playing an audio object.
	 *
	 * @param audio Audio to play
	 */
	protected abstract void playAudio(final OldAudioObject audio);

	/**
	 * Stops playing an audio object.
	 *
	 * @param audio Audio to pause
	 */
	protected abstract void pauseAudio(final OldAudioObject audio);

	/**
	 *
	 *
	 * @param audio Audio to stop
	 */
	protected abstract void stopAudio(final OldAudioObject audio);

}