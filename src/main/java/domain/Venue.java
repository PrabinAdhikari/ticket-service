/**
 * 
 */
package domain;

import java.util.HashMap;
import java.util.Map;

import utils.SeatIDMaker;

/**
 * @author prabinadhikari
 *
 */
public class Venue {
	private int id;
	private int rows;
	private int columns;
	Map<Integer, Seat> seats;
	private int noOfSeatsAvailable;

	public Venue() {

	}

	/**
	 * @param id
	 * @param space
	 */
	public Venue(int id, int rows, int columns) {
		this.id = id;
		this.rows = rows;
		this.columns = columns;
		seats = new HashMap<Integer, Seat>();
		addSeatsToSpace();
	}

	private void addSeatsToSpace() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int seatID = SeatIDMaker.getSeatID(i, j, columns);
				seats.put(seatID, new Seat(seatID, this));
			}
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the noOfSeatsAvailable
	 */
	public int getNoOfSeatsAvailable() {
		return noOfSeatsAvailable;
	}

	/**
	 * @param noOfSeatsAvailable
	 *            the noOfSeatsAvailable to set
	 */
	public void setNoOfSeatsAvailable(int noOfSeatsAvailable) {
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @return the seats
	 */
	public Map<Integer, Seat> getSeats() {
		return seats;
	}

	/**
	 * @param seats
	 *            the seats to set
	 */
	public void setSeats(Map<Integer, Seat> seats) {
		this.seats = seats;
	}

}
