package service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import domain.SeatHold;
import domain.Venue;

/**
 * @author prabinadhikari
 *
 */

public class TicketServiceImpl implements TicketService {

	private VenueService venueService;
	private Map<Integer, SeatHold> seatHoldMap = new HashMap<>();

	public TicketServiceImpl(Venue venue) {
		venueService = new VenueService(venue);
	}

	@Override
	public int numSeatsAvailable() {
		return venueService.getVenue().getNoOfSeatsAvailable();
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {

		SeatHold seatHold = new SeatHold();

		if (numSeats < 1) {
			System.out.println("Number of seats to reserve cannot be less than one");
		} else {
			synchronized (venueService) {
				int[] seatIDs = venueService.findBestAvailableSeats(numSeats);
				if (seatIDs.length != numSeats) {
					System.out.println("Required number of seats is not available\nWe have " + seatIDs.length
							+ " seats available");
				} else {
					int holdId = UUID.randomUUID().hashCode();
					seatHold.setId(holdId);
					seatHold.setNumSeats(numSeats);
					seatHold.setSeatIdsOfHeldSeats(seatIDs);
					seatHold.setCustomerEmailId(customerEmail);
					seatHold.setHoldSuccessful(true);
					seatHoldMap.put(holdId, seatHold);
					venueService.holdSeats(seatIDs);
				}
			}
		}
		return seatHold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		String message = "Reservation is not completed";
		SeatHold seatHold = seatHoldMap.get(seatHoldId);
		if (seatHold != null && seatHold.getCustomerEmailId().equalsIgnoreCase(customerEmail)) {
			int[] reservedSeatIds = seatHold.getSeatIdsOfHeldSeats();
			if (reservedSeatIds.length > 0) {
				venueService.markSeatReserved(reservedSeatIds);
				message = "Reserveation Successful";
			}
		}
		return message;
	}

}
