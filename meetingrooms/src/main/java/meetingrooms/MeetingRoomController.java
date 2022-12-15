package meetingrooms;

import java.util.List;
import java.util.Scanner;

public class MeetingRoomController {

    private Office office = new Office();

    private Scanner scanner = new Scanner(System.in);

    private void runMenu() {
        System.out.println("Welcome!");
        System.out.println("How many meeting rooms you would like to register?");
        int roomCount = scanner.nextInt();
        scanner.nextLine();

        readMeetingRooms(roomCount);

        printMenu();

        System.out.println("Choose a menu option!");
        int menuItem = scanner.nextInt();
        scanner.nextLine();

        chooseMenuItem(menuItem);
    }

    private void chooseMenuItem(int menuItem) {
        String meetingRoomData = "data's of the given meeting-room(s):";
        switch (menuItem) {
            case 1:
                System.out.println("meeting-rooms in order");
                printNames(office.getMeetingRooms());
                break;
            case 2:
                System.out.println("meeting-rooms in reverse order");
                printNames(office.getMeetingRoomsInReverseOrder());
                break;
            case 3:
                System.out.println("Give 2 to list every even room, 1 to odd rooms!");
                int number = scanner.nextInt();
                scanner.nextLine();
                printNames(office.getEverySecondMeetingRoom(number));
                break;
            case 4:
                System.out.println(meetingRoomData);
                printMeetingRooms(office.getMeetingRooms());
                break;
            case 5:
                System.out.println(meetingRoomData);
                String name = scanner.nextLine();
                printMeetingRoom(office.getMeetingRoomWithGivenName(name));
                break;
            case 6:
                System.out.println("give the part of given name of the room you would like to search!");
                String namePart = scanner.nextLine();
                System.out.println(meetingRoomData);
                printMeetingRooms(office.getMeetingRoomsWithGivenNamePart(namePart));
                break;
            case 7:
                System.out.println("Give the minimum area of the room!");
                int area = scanner.nextInt();
                scanner.nextLine();
                System.out.println(meetingRoomData);
                printMeetingRooms(office.getMeetingRoomsWithAreaLargerThan(area));
                break;
            case 8:
                System.out.println("Have a nice day!");
                System.exit(1);
                break;
            default:
                System.out.println("unknown menuData");
        }
    }

    private void readMeetingRooms(int roomCount) {
        for (int i = 1; i <= roomCount; i++) {
            System.out.printf("room#%s%n", i);

            System.out.println("name:");
            String name = scanner.nextLine();

            System.out.println("width:");
            int width = scanner.nextInt();
            scanner.nextLine();

            System.out.println("length:");
            int length = scanner.nextInt();
            scanner.nextLine();

            office.addMeetingRoom(name, width, length);

            System.out.println("The given meeting-room saved successfully!");
        }
    }

    public void printMenu() {
        System.out.println("1. meeting-rooms in order");
        System.out.println("2. meeting-rooms in reverse order");
        System.out.println("3. every second meeting-room");
        System.out.println("4. areas");
        System.out.println("5. Search meeting-room by name");
        System.out.println("6. Search meeting-room by name part");
        System.out.println("7. Search meeting-room by area");
        System.out.println("8. exit");
    }

    private void printNames(List<MeetingRoom> meetingRooms) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getName());
        }
    }

    private void printMeetingRooms(List<MeetingRoom> meetingRooms) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            printMeetingRoom(meetingRoom);
        }
    }

    private void printMeetingRoom(MeetingRoom meetingRoom) {
        System.out.printf("%n room name#%s has%n %s m width%n %s m length%n %s m2 area", meetingRoom.getName(), meetingRoom.getWidth(), meetingRoom.getLength(), meetingRoom.getArea());
    }

    public static void main(String[] args) {
        MeetingRoomController meetingRoomController = new MeetingRoomController();

        meetingRoomController.runMenu();
    }
}
