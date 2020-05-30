package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;

import java.util.Comparator;
import java.util.List;

public class SuperRobot implements Robot{
    List<Locker> lockers;

    public SuperRobot(List lockers) {
        this.lockers = lockers;
    }

    @Override
    public Ticket store(Bag bag) throws NoAvailableLockerException, NoAvailableLockerBoxException {
        Locker locker = this.lockers.stream().max(Comparator.comparing(Locker::getVacancyRate)).get();
        if (locker.isAvailable()) {
            Ticket ticket = locker.store(bag);
            this.ticketLockerMap.put(ticket, locker);
            return ticket;
        }
        throw new NoAvailableLockerException("No locker available");
    }

    @Override
    public Bag getBagWithTicket(Ticket ticket) throws InvalidTicketException {
        Locker locker = ticketLockerMap.get(ticket);
        if (locker == null) {
            throw new InvalidTicketException("Invalid Ticket");
        } else {
            return locker.takeOut(ticket);
        }
    }

}
