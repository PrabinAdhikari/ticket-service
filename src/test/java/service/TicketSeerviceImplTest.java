/**
 * 
 */
package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import domain.SeatHold;
import domain.Venue;

/**
 * @author prabinadhikari
 *
 */
public class TicketSeerviceImplTest {
	private TicketService ticketService;
	private static final int ROWS = 3;
	private static final int COLUMNS = 4;
	private static final String EMAIL_ADDRESS = "adhikari.prabin10@gmail.com";

	@Before
	public void setUp() {
		ticketService = new TicketServiceImpl(new Venue(1, ROWS, COLUMNS));
	}

	@Test
	public void numberOfSeatAvilable() {
		assertEquals(12, ticketService.numSeatsAvailable());
	}

	@Test
	public void findAndHoldSeats() {
		SeatHold seatHold = ticketService.findAndHoldSeats(5, EMAIL_ADDRESS);
		assertTrue(seatHold.isHoldSuccessful());
		assertEquals(5, seatHold.getNumSeats());
		assertEquals(7, ticketService.numSeatsAvailable());
	}

	@Test
	public void findSeatHoldWhenVenueIsFull() {
		SeatHold seatHoldTwelve = ticketService.findAndHoldSeats(12, EMAIL_ADDRESS);
		assertTrue(seatHoldTwelve.isHoldSuccessful());
		SeatHold seatHoldOne = ticketService.findAndHoldSeats(1, EMAIL_ADDRESS);
		assertFalse(seatHoldOne.isHoldSuccessful());
	}

	@Test
	public void findAndHoldZeroSeats() {
		SeatHold seatHold = ticketService.findAndHoldSeats(0, EMAIL_ADDRESS);
		assertFalse(seatHold.isHoldSuccessful());
	}

	@Test
	public void fineAndHoldMoreThanAvailableSeats() {
		SeatHold seatHold = ticketService.findAndHoldSeats(13, EMAIL_ADDRESS);
		assertFalse(seatHold.isHoldSuccessful());
	}

	@Test
	public void reserveSeats() {
		SeatHold seatHold = ticketService.findAndHoldSeats(5, EMAIL_ADDRESS);
		assertTrue(seatHold.isHoldSuccessful());
		assertEquals(5, seatHold.getNumSeats());
		assertEquals(7, ticketService.numSeatsAvailable());
		assertEquals("Reserveation Successful", ticketService.reserveSeats(seatHold.getId(), EMAIL_ADDRESS));
	}

	@Test
	public void holdAndReserveGroupSeat() {
		SeatHold seatHoldTwo = ticketService.findAndHoldSeats(2, EMAIL_ADDRESS);
		assertTrue(seatHoldTwo.isHoldSuccessful());
		assertEquals(10, ticketService.numSeatsAvailable());
		assertEquals("Reserveation Successful", ticketService.reserveSeats(seatHoldTwo.getId(), EMAIL_ADDRESS));

		SeatHold seatHoldFive = ticketService.findAndHoldSeats(5, EMAIL_ADDRESS);
		assertTrue(seatHoldFive.isHoldSuccessful());
		assertEquals(5, ticketService.numSeatsAvailable());
		assertEquals("Reserveation Successful", ticketService.reserveSeats(seatHoldFive.getId(), EMAIL_ADDRESS));

		SeatHold seatHoldThree = ticketService.findAndHoldSeats(3, EMAIL_ADDRESS);
		assertTrue(seatHoldThree.isHoldSuccessful());
		assertEquals(2, ticketService.numSeatsAvailable());
		assertEquals("Reserveation Successful", ticketService.reserveSeats(seatHoldThree.getId(), EMAIL_ADDRESS));
	}

	@Test
	public void reserveSeatAfterTimeOut() throws InterruptedException {
		SeatHold seatHoldButNotReserve = ticketService.findAndHoldSeats(5, EMAIL_ADDRESS);
		assertTrue(seatHoldButNotReserve.isHoldSuccessful());
		System.out.println("\n***Waiting for three seconds to test after hold time out ****");
		Thread.sleep(2000);
		System.out.println("\n***Hold time for test is completed ****");

		SeatHold seatHold = ticketService.findAndHoldSeats(10, EMAIL_ADDRESS);
		assertTrue(seatHold.isHoldSuccessful());
		assertEquals(10, seatHold.getNumSeats());
		assertEquals(2, ticketService.numSeatsAvailable());
		assertEquals("Reserveation Successful", ticketService.reserveSeats(seatHold.getId(), EMAIL_ADDRESS));
	}

	@Test
	public void reserveSeatBeforeTimeOut() throws InterruptedException {
		SeatHold seatHoldButNotReserve = ticketService.findAndHoldSeats(5, EMAIL_ADDRESS);
		assertTrue(seatHoldButNotReserve.isHoldSuccessful());
		// No sleep
		SeatHold seatHold = ticketService.findAndHoldSeats(10, EMAIL_ADDRESS);
		assertFalse(seatHold.isHoldSuccessful());

	}

}
