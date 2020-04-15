package com;

import engine.util.Input;
import org.jetbrains.annotations.Contract;
import support.Select;
import support.SlideValues;
import support.json.JSONArray;
import support.json.JSONObject;
import support.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GameOptions {

	/**
	 * Folder where the saves are.
	 */
	final public static String SAVES_FOLDER = "/media/saves";

	/**
	 * Array of all the keys (hot keys).
	 */
	final private static HashMap<String, AtomicInteger> keys = new HashMap<>();

	/**
	 * Array of all the booleans.
	 */
	final private static HashMap<String, AtomicBoolean> booleans = new HashMap<>();

	/**
	 * Array of all the selectors.
	 */
	final private static HashMap<String, support.Select> selectors = new HashMap<>();

	/**
	 * Array of all the sliders.
	 */
	final private static HashMap<String, SlideValues> sliders = new HashMap<>();

	static {
		if(support.File.exists(GameOptions.SAVES_FOLDER + "/options.json")) {
			GameOptions.loadOptions(GameOptions.SAVES_FOLDER + "/options.json");
		} else {
			GameOptions.defaultOptions();
		}
	}

	/**
	 * Returns the key used for an action.
	 *
	 * @param index Action's index
	 * @return keys.get(index)
	 */
	@Contract(pure = true)
	public static AtomicInteger getKey(final String index) {
		return GameOptions.keys.get(index);
	}

	/**
	 * Returns the boolean used for an action.
	 *
	 * @param index Action's index
	 * @return booleans.get(index)
	 */
	@Contract(pure = true)
	public static AtomicBoolean getBoolean(final String index) {
		return GameOptions.booleans.get(index);
	}

	/**
	 * Returns the Select used for an option.
	 *
	 * @param index Option's index
	 * @return selectors.get(index)
	 */
	@Contract(pure = true)
	public static support.Select getSelect(final String index) {
		return GameOptions.selectors.get(index);
	}

	/**
	 * Returns the SlideValues used for an option.
	 *
	 * @param index Option's index
	 * @return sliders.get(index)
	 */
	@Contract(pure = true)
	public static SlideValues getSlide(final String index) {
		return GameOptions.sliders.get(index);
	}

	/**
	 * Returns if the key is already picked by another action.
	 *
	 * @param keyToAvoid Key to not check
	 * @param value Value to check for
	 * @return true = already picked
	 */
	@Contract(pure = true)
	public static boolean isKeyAlreadyPicked(final String keyToAvoid, final int value) {
		for(final String key : GameOptions.keys.keySet()) {
			if(key.equals(keyToAvoid)) {
				continue;
			}
			if(GameOptions.keys.get(key).intValue() == value) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Saves the options.
	 */
	public static void saveOptions() {
		final JSONObject root = new JSONObject();
		final JSONObject keys = new JSONObject();
		final JSONObject booleans = new JSONObject();
		final JSONObject selectors = new JSONObject();
		final JSONObject sliders = new JSONObject();

		for(final String index : GameOptions.keys.keySet()) {
			keys.put(index, GameOptions.getKey(index).get());
		}

		for(final String index : GameOptions.booleans.keySet()) {
			booleans.put(index, GameOptions.getBoolean(index).get());
		}

		for(final String index : GameOptions.selectors.keySet()) {
			final JSONObject selector = new JSONObject();
			final Select select = GameOptions.getSelect(index);
			final JSONArray options = new JSONArray();

			for(int i = 0; i < select.getLength(); i++) {
				options.put(select.getOption(i));
			}

			selector.put("options", options);
			selector.put("value", select.getIndex());

			selectors.put(index, selector);
		}

		for(final String index : GameOptions.sliders.keySet()) {
			final JSONObject slider = new JSONObject();
			final SlideValues slide = GameOptions.getSlide(index);

			slider.put("min", slide.getMinValue());
			slider.put("max", slide.getMaxValue());
			slider.put("value", slide.getValue());
			slider.put("isPercentage", slide.isPercentage());

			sliders.put(index, slider);
		}

		root.put("keys", keys);
		root.put("booleans", booleans);
		root.put("selectors", selectors);
		root.put("sliders", sliders);

		support.File.writeToFile(GameOptions.SAVES_FOLDER + "/options.json", root.toString());
	}

	/**
	 * Loads the options from a JSON file.
	 *
	 * @param fileName File's name
	 */
	private static void loadOptions(final String fileName) {
		if(!support.File.getExtension(fileName).equals("json")) {
			System.err.println("Error: File is not a json. File name: " + fileName);
			System.err.println("Loading default options.");
			GameOptions.defaultOptions();
			return;
		}

		final JSONObject root;

		try {
			root = new JSONObject(new JSONTokener(support.File.getURL(fileName).openStream()));
		} catch(final IOException e) {
			System.err.println("Error: Couldn't load the options.");
			System.err.println("Loading default options.");
			e.printStackTrace();
			GameOptions.defaultOptions();
			return;
		}

		for(final String type : root.keySet()) {
			final JSONObject object = root.getJSONObject(type);

			switch(type) {
			case "keys":
				for(final String index : object.keySet()) {
					GameOptions.keys.put(index, new AtomicInteger(object.getInt(index)));
				}
				break;
			case "booleans":
				for(final String index : object.keySet()) {
					GameOptions.booleans.put(index, new AtomicBoolean(object.getBoolean(index)));
				}
				break;
			case "selectors":
				for(final String index : object.keySet()) {
					final JSONObject selector = object.getJSONObject(index);
					final JSONArray array = selector.getJSONArray("options");
					final String[] options = new String[array.length()];
					for(int i = 0; i < options.length; i++) {
						options[i] = array.getString(i);
					}
					GameOptions.selectors.put(index, new Select(options, selector.getInt("value")));
				}
				break;
			case "sliders":
				for(final String index : object.keySet()) {
					final JSONObject slider = object.getJSONObject(index);
					GameOptions.sliders.put(index, new SlideValues(slider.getInt("min"), slider.getInt("max"), slider.getInt("value"), slider.getBoolean("isPercentage")));
				}
				break;
			default:
				System.err.println("Error in " + fileName);
				System.err.println("Type '" + type + "' is unknown. Ignoring it.");
			}
		}
	}

	/**
	 * Creates the default options.
	 */
	private static void defaultOptions() {
		keys.put("moveNorth", new AtomicInteger(Input.KEY_Z));									// Key to move the hero to the north direction.
		keys.put("moveSouth", new AtomicInteger(Input.KEY_S));									// Key to move the hero in the south direction.
		keys.put("moveWest", new AtomicInteger(Input.KEY_Q));									// Key to move the hero in the west direction.
		keys.put("moveEast", new AtomicInteger(Input.KEY_D));									// Key to move the hero in the east direction.
		keys.put("sprint", new AtomicInteger(Input.KEY_LSHIFT));								// Key to make the hero sprint
		keys.put("action1", new AtomicInteger(Input.MOUSE_LEFT_BUTTON + Input.NUM_KEYCODES));	// Mouse button for the first action.
		keys.put("action2", new AtomicInteger(Input.MOUSE_RIGHT_BUTTON + Input.NUM_KEYCODES));	// Mouse button for the second action.
		keys.put("spell1", new AtomicInteger(Input.KEY_1));										// Key to select the first action in the spell bar.
		keys.put("spell2", new AtomicInteger(Input.KEY_2));             					    // Key to select the second action in the spell bar.
		keys.put("spell3", new AtomicInteger(Input.KEY_3));     							    // Key to select the third action in the spell bar.
		keys.put("spell4", new AtomicInteger(Input.KEY_4));										// Key to select the forth action in the spell bar.
		keys.put("spell5", new AtomicInteger(Input.KEY_5));										// Key to select the fifth action in the spell bar.
		keys.put("spell6", new AtomicInteger(Input.KEY_6));										// Key to select the sixth action in the spell bar.
		keys.put("spell7", new AtomicInteger(Input.KEY_7));										// Key to select the seventh action in the spell bar.
		keys.put("spell8", new AtomicInteger(Input.KEY_8));										// Key to select the eighth action in the spell bar.
		keys.put("spell9", new AtomicInteger(Input.KEY_9));										// Key to select the ninth action in the spell bar.
		keys.put("spell10", new AtomicInteger(Input.KEY_0));									// Key to select the tenth action in the spell bar.
		keys.put("selectTarget", new AtomicInteger(Input.KEY_TAB));								// Key to select the nearest enemy.
		keys.put("inventory", new AtomicInteger(Input.KEY_I));									// Key to open the hero's inventory.
		keys.put("map", new AtomicInteger(Input.KEY_M));										// Key to open the hero's map.
		keys.put("questBook", new AtomicInteger(Input.KEY_J));									// Key to open the hero's quest book.
		keys.put("characteristics", new AtomicInteger(Input.KEY_C));							// Key to open the hero's characteristics.
		keys.put("openConsole", new AtomicInteger(Input.KEY_RETURN));							// Key to open the console.
		keys.put("menu", new AtomicInteger(Input.KEY_ESCAPE));									// Key to open the menu.
		keys.put("professions", new AtomicInteger(Input.KEY_L));								// Key to open the hero's professions.
		keys.put("bestiary", new AtomicInteger(Input.KEY_B));									// Key to open the hero's bestiary.

		booleans.put("doubleKeyToSprint", new AtomicBoolean(true));								// Does typing twice really fast makes your hero sprint.
		booleans.put("generalSubtitles", new AtomicBoolean(false));								// Do we show the subtitles of everything in the game.
		booleans.put("cutsceneSubtitles", new AtomicBoolean(true));								// Do we show the subtitles during the cutscenes.

		selectors.put("resolution", new support.Select(new String[] {"464x256px", "928x512px", "1392x768px", "1856x1024px"}, 3));   // Screen's resolution.
		selectors.put("keyboard", new support.Select(new String[] {"azerty", "qwerty"}, 1));                                         // User's keyboard layout.

		sliders.put("luminosity", new SlideValues(10, 90, 50, true));							// Global luminosity
		sliders.put("maxFPS", new SlideValues(30, 120, 100, false));							// Maximum fps rendered
		sliders.put("generalVolume", new SlideValues(0, 200, 100, true));
	}

}