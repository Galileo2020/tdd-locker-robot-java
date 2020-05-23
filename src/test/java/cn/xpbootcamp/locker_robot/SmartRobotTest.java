package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SmartRobotTest {

    @Test
    void should_store_in_first_locker_and_return_ticket_when_store_bag_given_locker_capacity_are_5_and_4_respectively()
            throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(4);
        lockers.add(locker1);
        lockers.add(locker2);
        SmartRobot smartRobot = new SmartRobot(lockers);

        Bag bag = new Bag();
        Ticket ticket = smartRobot.store(bag);

        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(locker1,smartRobot.getLockerWithTicket(ticket));
    }

    @Test
    void should_store_in_second_locker_and_return_ticket_when_store_bag_given_locker_capacity_are_4_and_5_respectively() throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(4);
        Locker locker2 = new Locker(5);
        lockers.add(locker1);
        lockers.add(locker2);
        SmartRobot smartRobot = new SmartRobot(lockers);

        Bag bag = new Bag();
        Ticket ticket = smartRobot.store(bag);

        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(locker2,smartRobot.getLockerWithTicket(ticket));
    }

    @Test
    void should_store_in_first_locker_and_return_ticket_when_store_bag_given_locker_capacity_are_5_5_and_4_respectively()
            throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(5);
        Locker locker3 = new Locker(4);
        lockers.add(locker1);
        lockers.add(locker2);
        lockers.add(locker3);
        SmartRobot smartRobot = new SmartRobot(lockers);

        Bag bag = new Bag();
        Ticket ticket = smartRobot.store(bag);

        Assertions.assertNotNull(ticket);
        Locker result = smartRobot.getLockerWithTicket(ticket);
        Assertions.assertTrue(result == locker1 || result == locker2);
    }

    @Test
    void should_warning_no_locker_available_when_store_bag_given_no_available_lockers() {
        List<Locker> lockers = new ArrayList<>();
        Locker locker = new Locker(0);
        lockers.add(locker);
        SmartRobot smartRobot = new SmartRobot(lockers);

        Bag bag = new Bag();

        Assertions.assertThrows(NoAvailableLockerException.class, () -> smartRobot.store(bag));
    }

    @Test
    void should_return_bag_in_small_capacity_locker_when_get_bag_given_right_ticket() throws NoAvailableLockerBoxException, NoAvailableLockerException, InvalidTicketException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(5);
        lockers.add(locker1);
        lockers.add(locker2);
        SmartRobot smartRobot = new SmartRobot(lockers);
        Bag storedBag = new Bag();
        Ticket ticket = smartRobot.store(storedBag);

        Bag bag = smartRobot.getBagWithTicket(ticket);

        Assertions.assertEquals(storedBag,bag);
    }

    @Test
    void should_return_bag_in_high_capacity_locker_when_get_bag_given_right_ticket() throws NoAvailableLockerBoxException, NoAvailableLockerException, InvalidTicketException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(4);
        Locker locker2 = new Locker(6);
        lockers.add(locker1);
        lockers.add(locker2);
        SmartRobot smartRobot = new SmartRobot(lockers);
        Bag storedBag = new Bag();
        Ticket ticket = smartRobot.store(storedBag);

        Bag bag = smartRobot.getBagWithTicket(ticket);

        Assertions.assertEquals(storedBag,bag);
    }
}
