package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class Office {

    List<MeetingRoom> meetingRooms = new ArrayList<>();

    public void addMeetingRoom(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public List<MeetingRoom> getMeetingRoomsInReverseOrder() {
        List<MeetingRoom> result = new ArrayList<>();
        for (int i = meetingRooms.size() - 1; i >= 0; i--) {
            result.add(meetingRooms.get(i));
        }
        return result;
    }

    public List<MeetingRoom> getEverySecondMeetingRoom(int number) {
        List<MeetingRoom> everySecondMeetingRooms = new ArrayList<>();
        if (number == 2) {
            for (int i = 1; i < meetingRooms.size(); i += 2) {
                everySecondMeetingRooms.add(meetingRooms.get(i));
            }
        }
        if (number == 1) {
            for (int i = 0; i < meetingRooms.size(); i += 2) {
                everySecondMeetingRooms.add(meetingRooms.get(i));
            }
        }
        return everySecondMeetingRooms;
    }

    public MeetingRoom getMeetingRoomWithGivenName(String name) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getName().equals(name)) {
                return meetingRoom;
            }
        }
        return null;
    }

    public List<MeetingRoom> getMeetingRoomsWithGivenNamePart(String namePart) {
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getName().contains(namePart)) {
                result.add(meetingRoom);
            }
        }
        return result;
    }

    public List<MeetingRoom> getMeetingRoomsWithAreaLargerThan(int area) {
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (area < meetingRoom.getArea()) {
                result.add(meetingRoom);
            }
        }
        return result;
    }
}
