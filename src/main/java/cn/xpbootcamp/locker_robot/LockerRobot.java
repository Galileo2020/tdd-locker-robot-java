package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;
import java.util.*;

public class LockerRobot implements Robot {

    List<Locker> lockers = new ArrayList<>();

    public LockerRobot(List lockers) {
        this.lockers= lockers;
    }

    @Override
    public Ticket store(Bag bag) throws NoAvailableLockerException, NoAvailableLockerBoxException {
        for (Locker locker : lockers) {
            if (locker.isAvailable()) {
                Ticket ticket = locker.store(bag);
                ticketLockerMap.put(ticket, locker);
                return ticket;
            }
        }
        throw new NoAvailableLockerException("No locker available");
    }

    @Override
    public Bag getBagWithTicket(Ticket ticket) throws InvalidTicketException {
        Locker locker = ticketLockerMap.get(ticket);
        if (locker == null) {
            throw new InvalidTicketException("Invalid Ticket");
        } else {
            this.ticketLockerMap.remove(ticket);
            return locker.takeOut(ticket);
        }
    }

    Locker getLockerWithTicket(Ticket ticket) {
        return ticketLockerMap.get(ticket);
    }
}
