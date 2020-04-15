package engine.audio;

public class OldSampleInfo {

	/**
	 * Sample's volume.
	 */
	private double volume;

	/**
	 * Returns the Sample's volume.
	 *
	 * @return SampleInfo.volume
	 */
	final public double getVolume() {
		return this.volume;
	}

	/**
	 * Sets the Sample's volume.
	 *
	 * @param volume Volume to set
	 */
	final public void setVolume(final double volume) {
		this.volume = volume;
	}

}