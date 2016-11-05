/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import domain.Seat;
import domain.SeatStatus;
import domain.Venue;
import utils.SeatIDMaker;

/**
 * @author prabinadhikari
 *
 */
public class VenueService {
	private static final int TIMEOUT = 1;

	private Venue venue;

	/**
	 * @param venue
	 */
	public VenueService(Venue venue) {
		super();
		this.venue = venue;
		venue.setNoOfSeatsAvailable(venue.getRows() * venue.getColumns());
	}

	public int[] findBestAvailableSeats(int numSeats) {
		int count = 0;
		List<Integer> seatIds = new ArrayList<>();
		for (int i = 0; i < venue.getRows(); i++) {
			for (int j = 0; j < venue.getColumns(); j++) {
				int seatID = SeatIDMaker.getSeatID(i, j, venue.getColumns());
				Seat currentSeat = venue.getSeats().get(seatID);
				if (isEligibleToHold(currentSeat)) {
					seatIds.add(seatID);
					count++;
				} else {
					count = 0;
					seatIds.clear();

				}
				if (count == numSeats) {
					i = venue.getRows();// to break outer loop
					venue.setNoOfSeatsAvailable(venue.getNoOfSeatsAvailable() - numSeats);
					break;
				}
			}
		}
		return seatIds.stream().mapToInt(x -> x).toArray();
	}

	public int getTotalNumberOfSeats() {
		return venue.getRows() * venue.getColumns();
	}

	public void holdSeats(int[] seatIDs) {
		for (int i = 0; i < seatIDs.length; i++) {
			Seat seat = venue.getSeats().get(seatIDs[i]);
			seat.setSeatStatus(SeatStatus.ONHOLD);
			Calendar timeOut = Calendar.getInstance();
			timeOut.add(Calendar.SECOND, TIMEOUT);
			seat.setTimeOut(timeOut);
		}
	}

	public void markSeatReserved(int[] reservedSeatIds) {
		for (int i = 0; i < reservedSeatIds.length; i++) {
			Seat seat = venue.getSeats().get(reservedSeatIds[i]);
			seat.setSeatStatus(SeatStatus.RESERVED);
		}
	}

	private boolean isEligibleToHold(Seat currentSeat) {
		if (currentSeat.getSeatStatus().equals(SeatStatus.ONHOLD)) {
			Long currentMillisec = Calendar.getInstance().getTimeInMillis();
			if (currentMillisec >= currentSeat.getTimeOut().getTimeInMillis()) {
				currentSeat.setSeatStatus(SeatStatus.AVAILABLE);
				venue.setNoOfSeatsAvailable(venue.getNoOfSeatsAvailable() + 1);
			}
		}
		return currentSeat.getSeatStatus().equals(SeatStatus.AVAILABLE);
	}

	/**
	 * @return the venue
	 */
	public Venue getVenue() {
		return venue;
	}

	/**
	 * @param venue
	 *            the venue to set
	 */
	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}
