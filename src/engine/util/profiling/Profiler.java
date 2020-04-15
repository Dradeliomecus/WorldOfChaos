package engine.util.profiling;

import engine.util.console.Console;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;

final public class Profiler {

	/**
	 * Is the Profiler on.
	 */
	private static boolean on = false;

	/**
	 * Profiler's console.
	 */
	private static Console console;

	/**
	 * All parent's ProfileTimer.
	 */
	private static HashMap<String, ProfileTimer> timers;

	/**
	 * All children's ProfileTimer.
	 */
	private static HashMap<String, HashMap<String, ProfileTimer>> childrenTimers;

	/**
	 * Adds a ProfileTimer.
	 *
	 * @param name ProfileTimer's name
	 */
	public static void addProfileTimer(final String name) {
		final int dashPosition = name.indexOf('-');

		if(dashPosition > 0) {
			final String parent = name.substring(0, dashPosition);

			// Check if parent does exists
			if(!Profiler.timers.containsKey(parent)) {
				System.err.println("Error: Parent \"" + parent + "\" has not been found in Profiler.timers");
				new Exception().printStackTrace();
				System.exit(1);
			}

			final String child = name.substring(dashPosition + 1);

			if(!Profiler.childrenTimers.containsKey(parent)) Profiler.childrenTimers.put(parent, new HashMap<String, ProfileTimer>());
			Profiler.childrenTimers.get(parent).put(child, new ProfileTimer());
		} else {
			Profiler.timers.put(name, new ProfileTimer());
		}
	}

	/**
	 * Starts a ProfileTimer, creates it if it does not exist.
	 *
	 * @param name ProfileTimer's name
	 */
	public static void startProfileTimer(final String name) {
		if(Profiler.isOff()) {
			return;
		}

		final int dashPosition = name.indexOf('-');

		if(!Profiler.timers.containsKey(name)) {
			if(dashPosition > 0) {
				final String parentName = name.substring(0, dashPosition);
				final String childName = name.substring(dashPosition + 1);
				if(!Profiler.childrenTimers.containsKey(parentName) || !Profiler.childrenTimers.get(parentName).containsKey(childName)) {
					Profiler.addProfileTimer(name);
				}
			} else {
				Profiler.addProfileTimer(name);
			}
		}

		if(dashPosition > 0) {
			final String parentName = name.substring(0, dashPosition);
			final String childName = name.substring(dashPosition + 1);
			Profiler.childrenTimers.get(parentName).get(childName).startInvocation();
		} else {
			Profiler.timers.get(name).startInvocation();
		}
	}

	/**
	 * Stops the ProfileTimer.
	 *
	 * @param name ProfileTimer's name
	 */
	public static void stopProfileTimer(final String name) {
		if(Profiler.isOff()) {
			return;
		}

		final int dashPosition = name.indexOf('-');

		if(dashPosition > 0) {
			final String parentName = name.substring(0, dashPosition);
			final String childName = name.substring(dashPosition + 1);
			Profiler.childrenTimers.get(parentName).get(childName).stopInvocation();
		} else {
			Profiler.timers.get(name).stopInvocation();
		}
	}

	/**
	 * Checks if the ProfileTimer isRunning.
	 *
	 * @param name ProfileTimer's name
	 * @return ProfileTimer running
	 */
	public static boolean isProfileTimerRunning(final String name) {
		if(Profiler.isOff()) {
			return false;
		}

		final int dashPosition = name.indexOf('-');

		try {
			if(dashPosition > 0) {
				final String parentName = name.substring(0, dashPosition);
				final String childName = name.substring(dashPosition + 1);
				return Profiler.childrenTimers.get(parentName).get(childName).isRunning();
			} else {
				return Profiler.timers.get(name).isRunning();
			}
		} catch(final NullPointerException e) {
			return false;
		}
	}

	/**
	 * Displays and resets all the information contained in the all the ProfileTimers.
	 *
	 * @param totalTime totalTime of one frame (in ms)
	 * @param frames Number of frames in the second
	 */
	public static void displayAndResetTime(final double totalTime, final int frames) {
		if(Profiler.isOff()) {
			return;
		}

		double totalMeasuredTime = 0.0;

		final LinkedHashMap<String, Double[]> times = new LinkedHashMap<>();

		for(final String key : Profiler.timers.keySet()) {
			times.put(key, new Double[] { Profiler.timers.get(key).getTime(frames), Profiler.timers.get(key).getMinTime(), Profiler.timers.get(key).getMaxTime(), (double) Profiler.timers.get(key).getNumInvocations()} );

			if(Profiler.childrenTimers.containsKey(key)) {
				for(final String childName : Profiler.childrenTimers.get(key).keySet()) {
					times.put("  - " + childName + "\\", new Double[] {
						Profiler.childrenTimers.get(key).get(childName).getTime(frames),
						Profiler.childrenTimers.get(key).get(childName).getMinTime(),
						Profiler.childrenTimers.get(key).get(childName).getMaxTime(),
						(double) Profiler.childrenTimers.get(key).get(childName).getNumInvocations()
					});
					Profiler.childrenTimers.get(key).get(childName).reset();
				}
			}

			Profiler.timers.get(key).reset();
		}

		// Number of characters until the "time:"
		final int TIME_LENGTH = 25;
		// Number of characters before "called .. times."
		final int CALLED_LENGTH = 85;

		for(final String key : times.keySet()) {
			final double averageTime = times.get(key)[0];
			final double minTime = times.get(key)[1];
			final double maxTime = times.get(key)[2];
			String keyToDisplay = "" + key;
			if(key.charAt(key.length() - 1) != '\\') {
				totalMeasuredTime += averageTime;
			} else {
				keyToDisplay = keyToDisplay.substring(0, key.length() - 1);
			}

			String timeToDisplay = " " + keyToDisplay + " time";
			while(timeToDisplay.length() < TIME_LENGTH) {
				timeToDisplay += " ";
			}
			timeToDisplay += "\t: " + averageTime + " ms   (" + minTime + " ~ " + maxTime + "ms)";

			while(timeToDisplay.length() < CALLED_LENGTH) {
				timeToDisplay += " ";
			}
			//timeToDisplay += "\t";

			Profiler.getConsole().println(timeToDisplay + "\t called " + times.get(key)[3].intValue() + " \ttimes.");
		}

		final double otherTime = totalTime - totalMeasuredTime;
		final BigDecimal bd = new BigDecimal(otherTime);
		final int power = bd.precision() - ProfileTimer.NUM_SIGNIFICANT_DIGITS;
		final BigDecimal unit = bd.ulp().scaleByPowerOfTen(power);
		final BigDecimal result = bd.divideToIntegralValue(unit).multiply(unit);

		Profiler.getConsole().println(" Other time: " + result.doubleValue() + " ms");
		Profiler.getConsole().println(" Total time: " + totalTime + " ms  (" + frames + " frames)");
		Profiler.getConsole().println(" ---------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * Updates the Profiler.
	 */
	public static void update() {
		if(Profiler.isOn()) {
			Profiler.getConsole().update();
		}
	}

	/**
	 * Returns the Profiler's console.
	 *
	 * @return Profiler::console
	 */
	private static Console getConsole() {
		return Profiler.console;
	}

	/**
	 * Returns if the Profiler is active.
	 *
	 * @return Profiler.on
	 */
	public static boolean isOn() {
		return Profiler.on;
	}

	/**
	 * Returns if the Profiler is inactive.
	 *
	 * @return !Profiler.on
	 */
	public static boolean isOff() {
		return !Profiler.isOn();
	}

	/**
	 * Sets if the Profiler is active or not.
	 *
	 * @param enabled true = active
	 */
	public static void setActive(final boolean enabled) {
		Profiler.on = enabled;

		if(Profiler.getConsole() == null && enabled) {
			Profiler.console = new Console("Profiling console");
			Profiler.timers = new HashMap<>();
			Profiler.childrenTimers = new HashMap<>();
		}
	}

}