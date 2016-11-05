package domain;

/**
 * @author prabinadhikari
 *
 */
public class SeatHold {
	private int id;
	private int numSeats;
	private String customerEmailId;
	private int[] seatIdsOfHeldSeats;
	private boolean isHoldSuccessful = false;

	public SeatHold() {

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
	 * @return the numSeats
	 */
	public int getNumSeats() {
		return numSeats;
	}

	/**
	 * @param numSeats
	 *            the numSeats to set
	 */
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	/**
	 * @return the seatIdsOfHeldSeats
	 */
	public int[] getSeatIdsOfHeldSeats() {
		return seatIdsOfHeldSeats;
	}

	/**
	 * @param seatIdsOfHeldSeats
	 *            the seatIdsOfHeldSeats to set
	 */
	public void setSeatIdsOfHeldSeats(int[] seatIdsOfHeldSeats) {
		this.seatIdsOfHeldSeats = seatIdsOfHeldSeats;
	}

	/**
	 * @return the customerEmailId
	 */
	public String getCustomerEmailId() {
		return customerEmailId;
	}

	/**
	 * @param customerEmailId the customerEmailId to set
	 */
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	/**
	 * @return the isHoldSuccessful
	 */
	public boolean isHoldSuccessful() {
		return isHoldSuccessful;
	}

	/**
	 * @param isHoldSuccessful the isHoldSuccessful to set
	 */
	public void setHoldSuccessful(boolean isHoldSuccessful) {
		this.isHoldSuccessful = isHoldSuccessful;
	}
	

}
