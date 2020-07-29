package engine.math;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Matrix<T extends Number> {

	/**
	 * Matrix' number of rows.
	 */
	final private int M;

	/**
	 * Matrix' number of columns.
	 */
	final private int N;

	/**
	 * Matrix value.
	 */
	private @NotNull T[][] m;

	/**
	 * Create a new Matrix instance.
	 *
	 * @param M Number of rows
	 * @param N Number of columns
	 */
	public Matrix(final int M, final int N) {
		if(M < 1 || N < 1) {
			System.err.println("Error: tried to create a Matrix of size M=" + M + " ; N=" + N);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.M = M;
		this.N = N;

		this.m = (T[][]) new Number[M][N];
	}

	/**
	 * Creates a copy of a Matrix.
	 *
	 * @param m Matrix to copy
	 */
	public Matrix(final @NotNull Matrix<T> m) {
		this.M = m.M;
		this.N = m.N;

		this.m = m.getM();
	}

	@Override
	final public String toString() {
		String string = super.toString() + "\n";

		for(int i = 0; i < this.M; i++) {
			for(int j = 0; j < this.N; j++) {
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
	final public @NotNull Matrix<T> initIdentity() {
		if(!this.isSquare()) {
			System.err.println("Error: Cannot create identity matrix of non-square matrix.");
			System.err.println("Current matrix is of size " + M + 'x' + N);
			new Exception().printStackTrace();
			return this;
		}

		for(int i = 0; i < this.M; i++) {
			for(int j = 0; j < this.N; j++) {
				this.set(i, j, i == j ? (T)((Number) 1) : (T)((Number) 0));
			}
		}

		return this;
	}

	/**
	 * Returns true if (this) is a square-matrix (M = N).
	 *
	 * @return new boolean
	 */
	final public boolean isSquare() {
		return this.M == this.N;
	}

	/**
	 * Returns a copy of the Matrix' value.
	 *
	 * @return Matrix' value
	 */
	final public @NotNull T[][] getM() {
		final T[][] matrix = this.m.clone();

		for(int i=0; i<this.M; i++) {
			matrix[i] = matrix[i].clone();
		}

		return matrix;
	}

	/**
	 * Returns the Matrix' value at [x][y].
	 *
	 * @param x Matrix' row
	 * @param y Matrix' column
	 * @return Matrix's value[x][y]
	 */
	@Contract(pure = true)
	final public @NotNull T get(final int x, final int y) {
		return this.m[x][y];
	}

	/**
	 * Sets the Matrix' value.
	 *
	 * @param m Value to set
	 * @throws IllegalArgumentException if m is not a T[M][N]
	 */
	final public void setM(final @NotNull T[][] m) {
		if(m.length != this.M || m[0].length != this.N) {
			System.err.println("Error: The value to set in the Matrix2f must be a float[" + this.M + "][2=" + this.N + "]");
			System.err.println("Value set: float[" + m.length + "][" + (m.length == 0 ? "null" : ("[" + m[0].length) + "]"));
			throw new IllegalArgumentException();
		}

		this.m = m;
	}

	/**
	 * Sets the Matrix' value at [x][y].
	 *
	 * @param x Matrix' row
	 * @param y Matrix' column
	 * @param value Value to set
	 */
	final public void set(final int x, final int y, final @NotNull T value) {
		this.m[x][y] = value;
	}

	/**
	 * Returns the result of (this) + m.
	 * Does not change the value of (this).
	 *
	 * @param m Matrix to add
	 * @return new Matrix
	 */
	final public Matrix<T> add(final @NotNull Matrix<T> m) {
		if(this.M != m.M || this.N != m.N) {
			System.err.println("Error: Could not add the 2 matrices as they do not have the same size");
			System.err.println("this is of size " + this.M + 'x' + this.N);
			System.err.println("m is of size " + m.M + 'x' + m.N);
			new Exception().printStackTrace();
			return null;
		}

		final Matrix<T> matrix = new Matrix<>(this.M, this.N);

		for(int i=0; i<this.M; i++) {
			for(int j=0; j<this.N; j++) {
				matrix.set(i, j, this.get(i, j) + m.get(i, j));
			}
		}

		return matrix;
	}

}