import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean available;
    String customerName;

Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.available = true;
        this.customerName = "";
    }
}

public class Main {
   static Room findRoom(ArrayList<Room> rooms, int roomNo) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNo) {
                return room;
            }
        }
        return null;
    }
  static void viewRooms(ArrayList<Room> rooms) {
        System.out.println(" ROOM LIST: ");
        for (Room room : rooms) {
            String status = room.available ? "Available" : "Booked";
            System.out.println(
                "Room No: " + room.roomNumber +
                " | Category: " + room.category +
                " | Status: " + status +
                (room.available ? "" : " | Customer: " + room.customerName)
            );
        }
    }
    static void bookRoom(ArrayList<Room> rooms, Scanner sc) {
        System.out.print("Enter Room Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            sc.next();
            return;
        }

        int roomNo = sc.nextInt();
        Room room = findRoom(rooms, roomNo);

        if (room == null) {
            System.out.println("Room Not Found!");
        } else if (!room.available) {
            System.out.println("Room Already Booked!");
        } else {
            sc.nextLine(); 
            System.out.print("Enter Your Name: ");
            room.customerName = sc.nextLine();

            room.available = false;

            System.out.println("Payment Successful!");
            System.out.println("Room Booked Successfully!");
        }
    }
    static void cancelRoom(ArrayList<Room> rooms, Scanner sc) {
        System.out.print("Enter Room Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            sc.next();
            return;
        }

        int roomNo = sc.nextInt();
        Room room = findRoom(rooms, roomNo);

        if (room == null) {
            System.out.println("Room Not Found!");
        } else if (room.available) {
            System.out.println("Room Was Not Booked!");
        } else {
            room.available = true;
            room.customerName = "";
            System.out.println("Reservation Cancelled!");
        }
    }
public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
      
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Deluxe"));
        rooms.add(new Room(103, "Suite"));

        int choice = 0; 

        do {
            System.out.println(" HOTEL RESERVATION SYSTEM: ");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next(); 
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms(rooms);
                    break;

                case 2:
                    bookRoom(rooms, sc);
                    break;

                case 3:
                    cancelRoom(rooms, sc);
                    break;

                case 4:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
