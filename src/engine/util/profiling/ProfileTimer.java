package engine.util.profiling;

import engine.util.Time;

import java.math.BigDecimal;

class ProfileTimer {

	/**
	 * Number of significant digits that the times will be returned with.
	 */
	final public static int NUM_SIGNIFICANT_DIGITS = 4;

	/**
	 * Number of time the ProfileTimer has been invocated.
	 */
	private int numInvocations;

	/**
	 * Total time the ProfileTimer has recorded.
	 */
	private double totalTime;

	/**
	 * Minimum time of an invocation.
	 */
	private double minTime;

	/**
	 * Maximum time of an invocation.
	 */
	private double maxTime;

	/**
	 * When did the invocation starts.
	 */
	private double startTime;

	/**
	 * Creates a new ProfileTimer instance.
	 */
	ProfileTimer() {
		this.numInvocations = 0;
		this.totalTime = 0.0;
		this.minTime = 0.0;
		this.maxTime = 0.0;
		this.startTime = 0.0;
	}

	/**
	 * Starts the timer.
	 */
	final void startInvocation() {
		if(this.startTime != 0.0) {
			System.err.println("Error: ProfileTimer.startInvocation has been called twice.");
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.startTime = Time.getNanoTime();
	}

	/**
	 * Ends the timer.
	 */
	final void stopInvocation() {
		if(this.startTime == 0.0) {
			System.err.println("Error: ProfileTimer.stopInvocation has been called without having started the invocation.");
			new Exception().printStackTrace();
			System.exit(1);
		}

		final double time = Time.getNanoTime() - this.startTime;

		if(this.minTime == 0.0 || time < this.minTime) {
			this.minTime = time;
		}

		if(time > this.maxTime) {
			this.maxTime = time;
		}

		this.numInvocations++;
		this.totalTime += time;
		this.startTime = 0.0;
	}

	/**
	 * Returns the number of time the invocation has been called.
	 *
	 * @return ProfileTimer.numInvocations
	 */
	final int getNumInvocations() {
		return this.numInvocations;
	}

	/**
	 * Returns the minimum time of one invocation (in ms).
	 *
	 * @return ProfileTimer.minTime
	 */
	final double getMinTime() {
		final double time = this.minTime * Time.NANO_TO_MILLI;

		final BigDecimal bd = new BigDecimal(time);
		final int power = bd.precision() - ProfileTimer.NUM_SIGNIFICANT_DIGITS;
		final BigDecimal unit = bd.ulp().scaleByPowerOfTen(power);
		final BigDecimal result = bd.divideToIntegralValue(unit).multiply(unit);

		return result.doubleValue();
	}

	/**
	 * Returns the maximum time of one invocation (in ms).
	 *
	 * @return ProfileTimer.maxTime
	 */
	final double getMaxTime() {
		final double time = this.maxTime * Time.NANO_TO_MILLI;

		final BigDecimal bd = new BigDecimal(time);
		final int power = bd.precision() - ProfileTimer.NUM_SIGNIFICANT_DIGITS;
		final BigDecimal unit = bd.ulp().scaleByPowerOfTen(power);
		final BigDecimal result = bd.divideToIntegralValue(unit).multiply(unit);

		return result.doubleValue();
	}

	/**
	 * Returns the average time it takes for one invocation (in ms).
	 *
	 * @return ProfileTimer.getTime(ProfileTimer.numInvocations)
	 */
	final double getTime() {
		return this.getTime(this.getNumInvocations());
	}

	/**
	 * Returns the total time divided by the divisor (in ms).
	 *
	 * @param divisor Number to divide the total time by
	 * @return Time calculated
	 * @throws java.lang.IllegalArgumentException if divisor = 0
	 */
	final double getTime(final double divisor) {
		if(divisor == 0) {
			System.err.println("Error: parameter divisor cannot be equals to 0 in ProfileTimer.getTime(divisor).");
			throw new IllegalArgumentException();
		}

		final double time = (this.totalTime * Time.NANO_TO_MILLI) / divisor;

		final BigDecimal bd = new BigDecimal(time);
		final int power = bd.precision() - ProfileTimer.NUM_SIGNIFICANT_DIGITS;
		final BigDecimal unit = bd.ulp().scaleByPowerOfTen(power);
		final BigDecimal result = bd.divideToIntegralValue(unit).multiply(unit);

		return result.doubleValue();
	}

	/**
	 * Returns if the ProfileTimer is running.
	 *
	 * @return ProfileTimer running of not
	 */
	final boolean isRunning() {
		return this.startTime != 0.0;
	}

	/**
	 * Resets everything.
	 */
	final void reset() {
		this.numInvocations = 0;
		this.totalTime = 0.0;
		this.minTime = 0.0;
		this.maxTime = 0.0;
		this.startTime = 0;
	}

}