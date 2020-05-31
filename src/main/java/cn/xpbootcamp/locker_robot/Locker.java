package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;
import java.util.*;
import java.util.stream.IntStream;

public class Locker {

    private List<Box> boxes = new ArrayList<>();
    private Map<Ticket, Integer> keyStore = new HashMap<>();

    public Locker(int size) {
        IntStream.range(0, size).forEach(i -> boxes.add(new Box()));
    }

    public Ticket store(Bag bag) throws NoAvailableLockerBoxException {
        int index = IntStream.range(0, boxes.size()).filter(i -> boxes.get(i).storeBag(bag)).findFirst().orElse(-1);

        if(index != -1){
            return generateTicket(index);
        } else {
            throw new NoAvailableLockerBoxException("Locker is full");
        }
    }

    private Ticket generateTicket(int index) {
        Ticket ticket = new  Ticket();
        keyStore.put(ticket, index);
        return ticket;
    }

    public Bag takeOut(Ticket ticket) throws InvalidTicketException {
        if (ticket == null)
            throw new InvalidTicketException("Invalid Ticket");

        Integer index = keyStore.get(ticket);
        if (index != null) {
            keyStore.remove(ticket);
            return boxes.get(index).takeOut();
        } else {
            throw new InvalidTicketException("Invalid Ticket");
        }
    }

    public boolean isAvailable() {
        return boxes.stream().anyMatch(Box::isAvailable);
    }

    public int getAvailableCapacity() {
        return (int) this.boxes.stream().filter(Box::isAvailable).count();
    }

    public double getVacancyRate() {
        double availableCapacity = getAvailableCapacity();
        return availableCapacity == 0 ? 0.0 : availableCapacity / boxes.size();
    }
}
