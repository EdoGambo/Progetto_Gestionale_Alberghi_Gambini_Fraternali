package Hotel_source;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private static int idCounter = 0;
    private int id;
    private String name;
    private double cost;
    private String description;
    private List<Customer> participants;

    public Activity(String name, double cost, String description) {
        this.id = idCounter++;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.participants = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Customer> getParticipants() {
        return participants;
    }

    public void addParticipant(Customer customer) {
        participants.add(customer);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", participants=\n" + participants +
                '}';
    }
}
