package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Robot {

    List<Locker> lockers = new ArrayList<>();

    Map<Ticket, Locker> ticketLockerMap = new HashMap<>();

    Ticket store(Bag bag) throws NoAvailableLockerException, NoAvailableLockerBoxException;

    Bag getBagWithTicket(Ticket ticket) throws InvalidTicketException;

}
