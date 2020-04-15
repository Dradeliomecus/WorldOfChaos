package engine.audio;

public class OldAudioObject {

	/**
	 * Position in the audio.
	 */
	private int audioPos;

	/**
	 * Audio length.
	 */
	private int audioLength;

	/**
	 * AudioObject's sample's information (like volume).
	 */
	private OldSampleInfo sampleInfo;

	/**
	 * AudioObject's data.
	 */
	private OldAudioData data;

	/**
	 * Creates a new AudioObject instance.
	 *
	 * @param sampleInfo AudioObject's sample information to set
	 * @param data Audio data to set
	 */
	public OldAudioObject(final OldSampleInfo sampleInfo, final OldAudioData data) {
		this.setAudioPos(0.0);
		this.setAudioLength(data.getAudioLength());
		this.setSampleInfo(sampleInfo);
		this.setData(data);
	}

	/**
	 *
	 *
	 * @param stream
	 * @param streamLength
	 * @return false = hit the end of the audio file ; true = continue to play
	 */
	final boolean generateSamples(final float stream, final int streamLength) {
		this.setAudioPos(this.getData().generateSamples(stream, streamLength, this.getAudioPos(), this.getSampleInfo()));

		if(this.getAudioPos() == -1) {
			this.setAudioPos(0.0);
			return false;
		}

		return true;
	}

	/**
	 * Returns the audio pos (in ?).
	 *
	 * @return AudioObject.audioPos
	 */
	private int getAudioPos() {
		return this.audioPos;
	}

	/**
	 * Returns the audio length (in ?).
	 *
	 * @return AudioObject.audioLength
	 */
	private int getAudioLength() {
		return this.audioLength;
	}

	/**
	 * Returns the audio's sample's information.
	 *
	 * @return AudioObject.sampleInfo
	 */
	private OldSampleInfo getSampleInfo() {
		return this.sampleInfo;
	}

	/**
	 * Returns the audio data.
	 *
	 * @return AudioObject.data
	 */
	private OldAudioData getData() {
		return this.data;
	}

	/**
	 * Sets the audio position (between 0.0 and 1.0)
	 *
	 * @param pos Position to set
	 * @throws java.lang.IllegalArgumentException
	 */
	private void setAudioPos(final double pos) {
		if(pos < 0.0 || pos > 1.0) {
			throw new IllegalArgumentException("Position must be between 0.0 and 1.0, position set : " + pos);
		}

		this.audioPos = this.posToAbsolutePos(pos);
	}

	/**
	 * Sets the audio length.
	 *
	 * @param audioLength Audio length to set
	 */
	private void setAudioLength(final int audioLength) {
		this.audioLength = audioLength;
	}

	/**
	 * Sets the sample's information.
	 *
	 * @param sampleInfo Information to set
	 */
	private void setSampleInfo(final OldSampleInfo sampleInfo) {
		this.sampleInfo = sampleInfo;
	}

	/**
	 * Sets the audio data.
	 *
	 * @param data Data to set
	 */
	private void setData(final OldAudioData data) {
		this.data = data;
	}

	/**
	 * Returns the position (from 0.0 to 1.0) to the real position (in ?).
	 *
	 * @param pos Position to convert
	 * @return position (in ?)
	 */
	final protected int posToAbsolutePos(final double pos) {
		return (int)(pos * this.getAudioLength());
	}

}