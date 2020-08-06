package engine.math;

import engine.util.Position;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Matrix2l {

	/**
	 * Matrix value.
	 */
	private @NotNull long[][] m;

	/**
	 * Create a new Matrix2l instance.
	 */
	public Matrix2l() {
		this.m = new long[2][2];
	}

	/**
	 * Creates a copy of a Matrix2l.
	 *
	 * @param m Matrix2l to copy
	 */
	public Matrix2l(final @NotNull Matrix2l m) {
		this.m = m.getM();
	}

	@Override
	final public String toString() {
		String string = super.toString() + "\n";

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				string += this.get(i, j) + " ";
			}
			string += "\n";
		}

		return string;
	}

	/**
	 * Initializes the matrix to make it an identity matrix (1 on diagonal, 0 elsewhere).
	 *
	 * @return (this)
	 */
	final public @NotNull Matrix2l initIdentity() {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				this.set(i, j, i == j ? 1 : 0);
			}
		}

		return this;
	}

	/**
	 * Returns the matrix2l's determinant.
	 *
	 * @return new long
	 */
	final public long det() {
		return this.m[0][0]*this.m[1][1] - this.m[0][1]*this.m[1][0];
	}

	/**
	 * Returns a copy of the Matrix2l's value.
	 *
	 * @return Matrix2l's value
	 */
	final public @NotNull long[][] getM() {
		final long[][] matrix = this.m.clone();

		for(int i=0; i<2; i++) {
			matrix[i] = matrix[i].clone();
		}

		return matrix;
	}

	/**
	 * Returns the Matrix2l's value at [x][y].
	 *
	 * @param x Matrix2l's row
	 * @param y Matrix2l's column
	 * @return Matrix's value[x][y]
	 */
	@Contract(pure = true)
	final public long get(final int x, final int y) {
		return this.m[x][y];
	}

	/**
	 * Sets the Matrix2l's value.
	 *
	 * @param m Value to set
	 * @throws IllegalArgumentException if m is not a long[2][2]
	 */
	final public void setM(final @NotNull long[][] m) {
		if(m.length != 2 || m[0].length != 2) {
			System.err.println("Error: The value to set in the Matrix2f must be a float[2][2]");
			System.err.println("Value set: float[" + m.length + "][" + (m.length == 0 ? "null" : ("[" + m[0].length) + "]"));
			throw new IllegalArgumentException();
		}

		this.m = m;
	}

	/**
	 * Sets the Matrix2l's value at [x][y].
	 *
	 * @param x Matrix2l's row
	 * @param y Matrix2l's column
	 * @param value Value to set
	 */
	final public void set(final int x, final int y, final long value) {
		this.m[x][y] = value;
	}

	/**
	 * Sets a Matrix2l's row.
	 *
	 * @param row Row's index
	 * @param pos Row to set
	 */
	final public void setRow(final int row, final @NotNull Position pos) {
		this.m[row][0] = pos.getX();
		this.m[row][1] = pos.getY();
	}

	/**
	 * Sets a Matrix2l's column.
	 *
	 * @param col Column's index
	 * @param pos Column to set
	 */
	final public void setCol(final int col, final @NotNull Position pos) {
		this.m[0][col] = pos.getX();
		this.m[1][col] = pos.getY();
	}

	/**
	 * Returns the result of (this) + m.
	 * Does not change the value of (this).
	 *
	 * @param m Matrix to add
	 * @return new Matrix2l
	 */
	final public @NotNull Matrix2l add(final @NotNull Matrix2l m) {
		final Matrix2l result = new Matrix2l();

		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				result.set(i, j, this.get(i, j) + m.get(i, j));
			}
		}

		return result;
	}

	/**
	 * Returns the result of (this) * m.
	 * Does not change the value of (this).
	 *
	 * @param m Matrix2l to multiply by
	 * @return new Matrix2l
	 */
	@Contract(pure = true)
	final public @NotNull Matrix2l mul(final @NotNull Matrix2l m) {
		final Matrix2l result = new Matrix2l();

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				result.set(i, j,
					this.get(i, 0) * m.get(0, j) +
					this.get(i, 1) * m.get(1, j)
				);
			}
		}

		return result;
	}

}