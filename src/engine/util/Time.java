package engine.util;

final public class Time {

	/**
	 * Number of second in a nanosecond.
	 */
	final public static double NANO_TO_SECOND = 0.000000001;

	/**
	 * Number of millisecond in a nanosecond.
	 */
	final public static double NANO_TO_MILLI = 0.000001;

	/**
	 * Numbers of nanoseconds in a second.
	 */
	final public static double SECOND_TO_NANO = 1000000000.0;

	/**
	 * Returns the current time (in nanoseconds).
	 *
	 * @return current time
	 */
	public static long getNanoTime() {
		return System.nanoTime();
	}

}
