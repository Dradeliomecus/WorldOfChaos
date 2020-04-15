package support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Select {

    /**
     * Select's list.
     */
    final private @NotNull String[] list;

    /**
     * Select's index currently selected.
     */
    private int index;

    /**
     * Creates a new Select instance.
     *
     * @param list List to set
     * @param index Index to set
     */
    public Select(final @NotNull String[] list, final int index) {
        this.list = list;
        this.index = index;
    }

    /**
     * Returns the Select's length.
     *
     * @return Select.list.length
     */
    @Contract(pure = true)
    final public int getLength() {
        return this.getList().length;
    }

    /**
     * Returns the Select's index currently selected.
     *
     * @return Select.index
     */
    @Contract(pure = true)
    final public int getIndex() {
        return this.index;
    }

    /**
     * Returns the Select's list.
     *
     * @return Select.list
     */
    @Contract(pure = true)
    private String[] getList() {
        return this.list;
    }

    /**
     * Returns a Select's option.
     *
     * @param index Option's index
     * @return Select.list[index]
     */
    @Contract(pure = true)
    final public @NotNull String getOption(final int index) {
        if(index < 0 || index >= this.getList().length) {
            System.err.println("Error: Index not valid.");
            System.err.println("Index passed in parameter: " + index + " ; List length: " + this.getList().length);
            new Exception().printStackTrace();
            System.exit(1);
        }

        return this.getList()[index];
    }

    /**
     * Returns the option currently selected.
     *
     * @return Select.list[Select.index]
     */
    @Contract(pure = true)
    final public @NotNull String getOptionSelected() {
        return this.getOption(this.getIndex());
    }

    /**
     * Sets the Select's index.
     *
     * @param index Index to set
     */
    final public void setIndex(final int index) {
        if(index < 0 || index >= this.getLength()) {
            System.err.println("Error: Tried to set an incorrect index.");
            System.err.println("Index = " + index + ", select's length = " + this.getLength());
            new Exception().printStackTrace();
            return;
        }

        this.index = index;
    }

}