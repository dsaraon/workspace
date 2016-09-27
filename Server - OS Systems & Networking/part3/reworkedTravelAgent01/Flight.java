package reworkedTravelAgent01;

public class Flight {
	private final int TotalNumberOfSeats =10;
	private int BookedTillNow;
	
	
	public Flight(int bookedTillNow) {
		super();
		BookedTillNow = bookedTillNow;
	}

	// this reserves a pair of seats
	public void ReserveSeat(){
		if(BookedTillNow <= TotalNumberOfSeats-2){
			this.BookedTillNow +=2;
			System.out.println("A pair of seats are booked");
		}
		else
			System.out.println("No seat available to book");
		System.out.println(this.toString());
	}
	
	public void CancelReservation(){
		if(BookedTillNow >= 2){
			this.BookedTillNow -=2;
			System.out.println("A pair of seats which were booked are now canceled");
		}
		else
			System.out.println("No seat available to book");
		System.out.println(this.toString());
	}
	
	public String toString(){
		String s ="\n";
		s+= "Total number of seats: " + this.TotalNumberOfSeats+"\n";
		s+= "Total number of seats: " + this.BookedTillNow+"\n";
		return s;
	}

}
