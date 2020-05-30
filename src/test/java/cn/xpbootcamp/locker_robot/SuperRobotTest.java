package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SuperRobotTest {

    @Test
    void should_store_in_one_of_the_lockers_and_return_ticket_when_store_bag_given_two_lockers_capacity_are_two_respectively() throws NoAvailableLockerBoxException, NoAvailableLockerException {
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

    @Test
    void two_lockers_vacancy_rate_should_be_fifty_percent_when_store_bag_given_two_lockers_capacity_are_two_respectively_and_one_locker_have_one_bag_in_it()
            throws NoAvailableLockerBoxException, NoAvailableLockerException {
        List<Locker> lockers = new ArrayList<>();
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        lockers.add(locker1);
        lockers.add(locker2);
        SuperRobot superRobot = new SuperRobot(lockers);
        Bag bag = new Bag();
        superRobot.store(bag);

        Bag anotherBag = new Bag();
        Ticket ticket = superRobot.store(anotherBag);

        System.out.println(locker1.boxes.size()-locker1.keyStore.size());
        System.out.println(locker2.boxes.size()-locker2.keyStore.size());

        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(0.5,locker1.getVacancyRate());
        Assertions.assertEquals(0.5,locker2.getVacancyRate());
    }

    @Test
    void should_warning_no_locker_available_when_store_bag_given_no_available_lockers() {
        List<Locker> lockers = new ArrayList<>();
        Locker locker = new Locker(0);
        lockers.add(locker);
        SuperRobot superRobot = new SuperRobot(lockers);

        Bag bag = new Bag();

        Assertions.assertThrows(NoAvailableLockerException.class, () -> superRobot.store(bag));
    }
}
