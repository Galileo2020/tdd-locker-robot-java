package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;
import java.util.Comparator;
import java.util.List;

public class SmartRobot extends Robot {

    public SmartRobot(List lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) throws NoAvailableLockerBoxException, NoAvailableLockerException {
        Locker locker = this.lockers.stream().max(Comparator.comparing(Locker::getAvailableCapacity)).get();
        if (locker.isAvailable()) {
            Ticket ticket = locker.store(bag);
            this.ticketLockerMap.put(ticket, locker);
            return ticket;
        }
        throw new NoAvailableLockerException("No locker available");
    }
}
