package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import java.util.Comparator;
import java.util.List;

public class SmartRobot extends Robot {

    public SmartRobot(List lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) throws NoAvailableLockerBoxException {
        Locker locker = this.lockers.stream().max(Comparator.comparing(Locker::getAvailableCapacity)).get();
        Ticket ticket = locker.store(bag);
        if (ticket != null)
            this.ticketLockerMap.put(ticket, locker);
        return ticket;
    }
}
