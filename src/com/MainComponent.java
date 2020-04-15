package com;

import engine.CoreEngine;
import engine.util.Time;
import engine.util.Window;
import engine.util.profiling.Profiler;

final public class MainComponent {

	/**
	 * Time when the application started.
	 */
	public static long APPLICATION_START;

	/**
	 * MAIN.
	 *
	 * @param args Arguments
	 */
	public static void main(final String[] args) {
		MainComponent.APPLICATION_START = Time.getNanoTime();

		final Game game = new Game();
		final CoreEngine engine = new CoreEngine(game);

		Window.setIcon("icon16.png", "icon32.png");

		Profiler.setActive(false);

		engine.start();
	}

}