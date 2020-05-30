package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.exception.InvalidTicketException;
import cn.xpbootcamp.locker_robot.exception.NoAvailableLockerBoxException;

import java.util.*;

public class Locker {

    List<Box> boxes;
    Map<Ticket, Integer> keyStore;

    public Locker(int size) {
        boxes = new ArrayList<>(Collections.nCopies(size, new Box()));
        keyStore = new HashMap<>();
    }

    public Ticket store(Bag bag) throws NoAvailableLockerBoxException {
        int index = -1;
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).storeBag(bag)) {
                index = i;
                break;
            }
        }
        if(index != -1){
            return generateTicket(index);
        }
        else {
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

    public float getVacancyRate(){
        float vacancyRate;
        if(boxes.size() != 0){
            vacancyRate = (float)(boxes.size()-keyStore.size())/boxes.size();
        }
        else {
            vacancyRate = 0;
        }
        return vacancyRate;
    }
}
