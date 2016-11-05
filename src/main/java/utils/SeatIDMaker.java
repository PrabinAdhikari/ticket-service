/**
 * 
 */
package utils;

/**
 * @author prabinadhikari
 *
 */
public class SeatIDMaker {
	public static int getSeatID(int i, int j, int column) {
		return 1 + i * column + j;

	}

}
