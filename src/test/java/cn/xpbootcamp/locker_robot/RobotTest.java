package cn.xpbootcamp.locker_robot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RobotTest {

    @Test
    void should_return_ticket_when_store_bag_given_robot_have_available_locker() throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker = new Locker(5);
        lockers.add(locker);
        Robot robot = new Robot(lockers);

        Bag bag = new Bag();
        Ticket ticket = robot.store(bag);

        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_store_into_the_second_locker_and_return_ticket_when_store_bag_given_first_locker_is_full() throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker theFirstLocker = new Locker(0);
        Locker theSecondLocker = new Locker(1);
        Locker theThirdLocker = new Locker(1);
        lockers.add(theFirstLocker);
        lockers.add(theSecondLocker);
        lockers.add(theThirdLocker);
        Robot robot = new Robot(lockers);

        Bag bag = new Bag();
        Ticket ticket = robot.store(bag);

        Assertions.assertEquals(theSecondLocker, robot.getLockerWithTicket(ticket));
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_warning_no_locker_available_when_store_bag_given_no_available_lockers() {
        List<Locker> lockers = new ArrayList<>();
        Locker locker = new Locker(0);
        lockers.add(locker);
        Robot robot = new Robot(lockers);

        Bag bag = new Bag();

        Assertions.assertThrows(NoAvailableLockerException.class, () -> robot.store(bag));
    }

    @Test
    void should_return_bag_in_first_locker_when_get_bag_given_right_ticket() throws NoAvailableLockerBoxException, NoAvailableLockerException, InvalidTicketException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(1);
        lockers.add(locker1);
        Robot robot = new Robot(lockers);
        Bag storedBag = new Bag();
        Ticket ticket = robot.store(storedBag);

        Bag bag = robot.getBagWithTicket(ticket);

        Assertions.assertEquals(storedBag, bag);
    }

    @Test
    void should_return_bag_in_second_locker_when_get_bag_given_right_ticket() throws NoAvailableLockerBoxException, NoAvailableLockerException, InvalidTicketException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(1);
        lockers.add(locker1);
        lockers.add(locker2);
        Robot robot = new Robot(lockers);
        Bag storedBag = new Bag();
        Ticket ticket = robot.store(storedBag);

        Bag bag = robot.getBagWithTicket(ticket);

        Assertions.assertEquals(storedBag, bag);
    }

    @Test
    void should_not_get_bag_when_get_bag_given_wrong_ticket() throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker = new Locker(1);
        lockers.add(locker);
        Robot robot = new Robot(lockers);
        Bag storedBag = new Bag();
        Ticket theRightTicket = robot.store(storedBag);
        Ticket theWrongTicket = new Ticket();
        Assertions.assertNotEquals(theRightTicket, theWrongTicket);

        Assertions.assertThrows(InvalidTicketException.class, () -> robot.getBagWithTicket(theWrongTicket));
    }

    @Test
    void should_not_get_bag_when_get_bag_given_expired_ticket() throws NoAvailableLockerBoxException, NoAvailableLockerException, InvalidTicketException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker = new Locker(1);
        lockers.add(locker);
        Robot robot = new Robot(lockers);
        Bag storedBag = new Bag();
        Ticket theRightTicket = robot.store(storedBag);
        Bag bag = robot.getBagWithTicket(theRightTicket);
        assertEquals(storedBag, bag);

        Assertions.assertThrows(InvalidTicketException.class, () -> robot.getBagWithTicket(theRightTicket));
    }
}
