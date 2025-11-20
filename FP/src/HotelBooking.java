import java.util.*;

class Hotel {
    private final Object lockRoom = new Object();  
    private final Object lockPay = new Object();   
    private Set<Integer> bookedRooms = new HashSet<>(Arrays.asList()); 
    Scanner sc = new Scanner(System.in);
    public void bookRoom(String user) {
  
        while (true) {
            synchronized (lockRoom) {  
                System.out.println(user + " enter room number to book (1-5) or -1 to exit:");
                int room = sc.nextInt();

                if (room == -1) {
                    System.out.println(user + " exited.");
                    break;
                }

                if (bookedRooms.contains(room)) {
                    System.out.println("Room " + room + " already booked Try another.");
                    continue; // try again
                }

                synchronized (lockPay) { 
                    System.out.println(user + " processing payment...");
                    
                    bookedRooms.add(room);
                    System.out.println( user + " booked room " + room);
                }
            }
        }
    }
}

public class HotelBooking {
    public static void main(String[] args) throws InterruptedException {
        Hotel hotel = new Hotel();

        Thread user1 = new Thread(() -> hotel.bookRoom("User1"));
        Thread user2 = new Thread(() -> hotel.bookRoom("User2"));

        user1.start();
        user2.start();

        user1.join();
        user2.join();

        System.out.println("✔ All bookings done.");
    }
}
