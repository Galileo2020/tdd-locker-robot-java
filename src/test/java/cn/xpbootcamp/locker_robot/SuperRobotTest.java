package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SuperRobotTest {

    @Test
    void should_store_in_one_of_the_lockers_and_return_ticket_when_store_bag_given_two_lockers_capacity_are_two_respectively() {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        lockers.add(locker1);
        lockers.add(locker2);
        SuperRobot superRobot = new SuperRobot(lockers);

        Bag bag = new Bag();
        Ticket ticket = superRobot.store(bag);
        Locker result = superRobot.ticketLockerMap.get(ticket);

        Assertions.assertNotNull(ticket);
        Assertions.assertTrue(result == locker1 || result == locker2);
    }
}
