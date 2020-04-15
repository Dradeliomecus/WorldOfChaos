package support;

import java.util.concurrent.atomic.AtomicInteger;

public class SlideValues {

	/**
	 * Slide's minimum value.
	 */
	private int minValue;

	/**
	 * Slide's maximum value.
	 */
	private int maxValue;

	/**
	 * Slide's value.
	 */
	final private AtomicInteger value;

	/**
	 * Is the slide displayed in percentage.
	 */
	private boolean isPercentage;

	/**
	 * Creates a new SlideValues instance.
	 *
	 * @param minValue Minimum value to set
	 * @param maxValue Maximum value to set
	 * @param value Value to set
	 * @param isPercentage true = is displayed in percentage
	 */
	public SlideValues(final int minValue, final int maxValue, final int value, final boolean isPercentage) {
		this.setMinValue(minValue);
		this.setMaxValue(maxValue);
		this.value = new AtomicInteger(value);
		this.setIsPercentage(isPercentage);
	}

	/**
	 * Returns the Slide's minimum value.
	 *
	 * @return SlideValues.minValue
	 */
	final public int getMinValue() {
		return this.minValue;
	}

	/**
	 * Returns the Slide's maximum value.
	 *
	 * @return SlideValues.maxValue
	 */
	final public int getMaxValue() {
		return this.maxValue;
	}

	/**
	 * Returns the Slide's value.
	 *
	 * @return SlideValues.value.get()
	 */
	final public int getValue() {
		return this.value.get();
	}

	/**
	 * Returns a reference to the Slide's value.
	 *
	 * @return SlideValues.value
	 */
	final public AtomicInteger getValueReference() {
		return this.value;
	}

	/**
	 * Returns whether the slide's display is in percentage or not.
	 *
	 * @return SlideValues.isPercentage
	 */
	final public boolean isPercentage() {
		return this.isPercentage;
	}

	/**
	 * Sets the Slide's minimum value.
	 *
	 * @param minValue Minimum value to set
	 */
	final public void setMinValue(final int minValue) {
		this.minValue = minValue;
	}

	/**
	 * Sets the Slide's maximum value.
	 *
	 * @param maxValue Maximum value to set
	 */
	final public void setMaxValue(final int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * Sets the Slide's value.
	 *
	 * @param value Value to set
	 */
	final public void setValue(final int value) {
		this.getValueReference().set(value);
	}

	/**
	 * Sets whether the slide's display is in percentage or not.
	 *
	 * @param isPercentage true = is percentage
	 */
	final public void setIsPercentage(final boolean isPercentage) {
		this.isPercentage = isPercentage;
	}

}
