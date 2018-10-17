package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.model.Floor;
import com.esolutions.trainings.jsc2.model.Guest;
import com.esolutions.trainings.jsc2.model.Hotel;
import com.esolutions.trainings.jsc2.model.Room;
import com.esolutions.trainings.jsc2.web.GuestResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.TreeMap;

import static com.esolutions.trainings.jsc2.util.PerfectSquareUtil.isPerfectSquare;

public class HaveANiceDayHotel {

    private HaveANiceDayHotel() {
    }

    public static GuestResponse getGuestResponse(@PathVariable int floor, @PathVariable int room) {
        GuestResponse guestResponse = new GuestResponse();

        Hotel hotel = createHotel();

        Map<Long, Long> lastGuestOfFloor = new TreeMap<>();
        Map<Long, Long> lastRoomOfFloor = new TreeMap<>();

        for (long currentGuest = 1; currentGuest <= 50000; currentGuest++) {
            long currentFloor = 1L;

            if (currentGuest == 1L) {
                addFirstGuestIfExists(hotel, currentGuest, lastGuestOfFloor, lastRoomOfFloor);
            } else {
                Guest guest = new Guest();
                guest.setId(currentGuest);
                addGuestToFloor(hotel, lastRoomOfFloor, lastGuestOfFloor, currentGuest, currentFloor, guest);
            }
            if (requestedRoomExists(floor, room, hotel)) {
                Long guestId = hotel.getFloors().get((long) floor).getRooms().get((long) room).getGuest().getId();
                guestResponse.setGuest((int) (long) guestId);
                break;
            }
        }
        return guestResponse;
    }

    private static boolean requestedRoomExists(long floor, long room, Hotel hotel) {
        Floor requestFloor = hotel.getFloors().get(floor);
        if (requestFloor != null) {
            Room requestRoom = requestFloor.getRooms().get(room);
            return requestRoom != null;
        }
        return false;
    }

    private static void addGuestToFloor(Hotel hotel, Map<Long, Long> lastRoomOfFloor, Map<Long, Long> lastGuestOfFloor,
                                        long currentGuest, long currentFloor, Guest guest) {

        Long lastGuestId = lastGuestOfFloor.get(currentFloor);

        if (isPerfectSquare(lastGuestId + currentGuest)) {

            Floor floor = hotel.getFloors().get(currentFloor);
            Long lastRoomNumberOfFloor = lastRoomOfFloor.get(currentFloor);

            long currentRoomFloorNumber = lastRoomNumberOfFloor + 1L;

            floor.getRooms().put(currentRoomFloorNumber, new Room(guest));

            lastGuestOfFloor.put(currentFloor, guest.getId());
            lastRoomOfFloor.put(currentFloor, currentRoomFloorNumber);
        } else {
            currentFloor++;

            Long lastRoomNumberOfFloor = lastRoomOfFloor.get(currentFloor);

            if (lastRoomNumberOfFloor == null) {
                Floor newFloor = new Floor();
                hotel.getFloors().put(currentFloor, newFloor);

                long firstRoomOfFlor = 1L;

                newFloor.getRooms().put(firstRoomOfFlor, new Room(guest));

                lastGuestOfFloor.put(currentFloor, guest.getId());
                lastRoomOfFloor.put(currentFloor, firstRoomOfFlor);
            } else {
                //Recursividad
                addGuestToFloor(hotel, lastRoomOfFloor, lastGuestOfFloor, currentGuest, currentFloor, guest);
            }
        }
    }

    private static void addFirstGuestIfExists(Hotel hotel, long guestId, Map<Long, Long> lastGuestOfFloor, Map<Long, Long> lastRoomOfFloor) {
        long firstFloorNumber = 1L;
        long firstRoomNumber = 1L;

        Guest guest = new Guest();
        guest.setId(guestId);
        Floor firstFloor = hotel.getFloors().get(firstFloorNumber);

        Room firstRoom = firstFloor.getRooms().get(firstRoomNumber);

        firstRoom.setGuest(guest);

        lastGuestOfFloor.put(firstFloorNumber, guestId);
        lastRoomOfFloor.put(firstFloorNumber, firstRoomNumber);
    }

    private static Hotel createHotel() {
        return new Hotel(createFirstFloor());
    }

    private static Map<Long, Floor> createFirstFloor() {
        Map<Long, Floor> floors = new TreeMap<>();

        Floor floor = new Floor(createFirstRoom());
        floors.put(1L, floor);

        return floors;
    }

    private static Map<Long, Room> createFirstRoom() {
        Map<Long, Room> rooms = new TreeMap<>();

        Room room = new Room();

        rooms.put(1L, room);
        return rooms;
    }
}
