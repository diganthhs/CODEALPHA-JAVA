import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }
}

class Reservation {
    String guestName;
    Room room;
    int nights;
    boolean isPaid;

    public Reservation(String guestName, Room room, int nights) {
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.isPaid = false;
        room.isAvailable = false;
    }

    public double calculateTotal() {
        return nights * room.price;
    }

    public void makePayment() {
        this.isPaid = true;
        System.out.println("Payment successful for " + guestName);
    }
}

public class HotelReservation {
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View available rooms\n2. Make a reservation\n3. View bookings\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 80.0));
        rooms.add(new Room(103, "Suite", 150.0));
    }

    private static void viewAvailableRooms() {
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room " + room.roomNumber + " - " + room.category + " - $" + room.price);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.println("Enter number of nights: ");
        int nights = scanner.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                Reservation res = new Reservation(name, room, nights);
                reservations.add(res);
                System.out.println("Reservation successful! Total cost: $" + res.calculateTotal());
                res.makePayment();
                return;
            }
        }
        System.out.println("Room not available.");
    }

    private static void viewBookings() {
        for (Reservation res : reservations) {
            System.out.println(res.guestName + " booked Room " + res.room.roomNumber + " for " + res.nights + " nights. Paid: " + res.isPaid);
        }
    }
}
