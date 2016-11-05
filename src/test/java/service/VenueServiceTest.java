/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.SeatStatus;
import domain.Venue;
import utils.SeatIDMaker;

/**
 * @author prabinadhikari
 *
 */
public class VenueServiceTest {
	VenueService venueService;
	private static final int ROWS = 3;
	private static final int COLUMNS = 4;

	@Before
	public void setUp() {
		venueService = new VenueService(new Venue(1, ROWS, COLUMNS));
	}

	@Test
	public void findAvailableSeats() {
		Assert.assertArrayEquals(getExpectedSeatIds(4), venueService.findBestAvailableSeats(4));
	}

	@Test
	public void getTotalNumberOfSeats() {
		Assert.assertEquals(12, venueService.getTotalNumberOfSeats());
	}

	@Test
	public void holdSeats() {
		int[] seatIds = getExpectedSeatIds(3);
		venueService.holdSeats(seatIds);
		for (int i : seatIds) {
			Assert.assertEquals(SeatStatus.ONHOLD, venueService.getVenue().getSeats().get(i).getSeatStatus());
		}

	}

	@Test
	public void markSeatReserved() {
		int[] seatIds = getExpectedSeatIds(3);
		venueService.markSeatReserved(seatIds);
		for (int i : seatIds) {
			Assert.assertEquals(SeatStatus.RESERVED, venueService.getVenue().getSeats().get(i).getSeatStatus());
		}

	}

	private int[] getExpectedSeatIds(int numSeats) {
		List<Integer> seatIds = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				int seatID = SeatIDMaker.getSeatID(i, j, COLUMNS);
				seatIds.add(seatID);
				count++;
				if (count == numSeats) {
					i = ROWS;// to break outer loop
					break;
				}
			}
		}
		return seatIds.stream().mapToInt(x -> x).toArray();
	}

}
