package domain;

import java.util.Calendar;

/**
 * @author prabinadhikari
 *
 */
public class Seat {
	private int id;
	private SeatStatus status;
	private Calendar timeOut;

	public Seat() {

	}

	/**
	 * @param id
	 */
	public Seat(int id, Venue venue) {
		super();
		this.id = id;
		status = SeatStatus.AVAILABLE;
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
	 * @return the seatStatus
	 */
	public SeatStatus getSeatStatus() {

		return status;
	}

	/**
	 * @param seatStatus
	 *            the seatStatus to set
	 */
	public void setSeatStatus(SeatStatus seatStatus) {
		this.status = seatStatus;
	}

	/**
	 * @return the timeOut
	 */
	public Calendar getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut
	 *            the timeOut to set
	 */
	public void setTimeOut(Calendar timeOut) {
		this.timeOut = timeOut;
	}

}
