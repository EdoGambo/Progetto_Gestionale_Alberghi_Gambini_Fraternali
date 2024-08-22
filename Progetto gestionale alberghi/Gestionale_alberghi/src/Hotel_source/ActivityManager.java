package Hotel_source;

import java.io.*;
import java.util.*;

public class ActivityManager {
    private List<Activity> activities;
    private static final String FILE_NAME = "activities.txt";

    public ActivityManager() {
        this.activities = new ArrayList<>();
        loadActivitiesFromFile();
    }

    public Activity createActivity(String name, double cost, String description) {
        Activity activity = new Activity(name, cost, description);
        activities.add(activity);
        saveActivitiesToFile();
        return activity;
    }

    public Activity getActivityDetails(int activityId) {
        for (Activity activity : activities) {
            if (activity.getId() == activityId) {
                return activity;
            }
        }
        return null;
    }

    public void updateActivityDetails(int activityId, String name, double cost, String description) {
        for (Activity activity : activities) {
            if (activity.getId() == activityId) {
                activity.setName(name);
                activity.setCost(cost);
                activity.setDescription(description);
                saveActivitiesToFile();
                return;
            }
        }
    }

    public void cancelActivity(int activityId) {
        Iterator<Activity> iterator = activities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getId() == activityId) {
                iterator.remove();
                saveActivitiesToFile();
                return;
            }
        }
    }

    private void loadActivitiesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[1];
                    double cost = Double.parseDouble(parts[2]);
                    String description = parts[3];
                    activities.add(new Activity(name, cost, description));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading activities from file: " + e.getMessage());
        }
    }

    private void saveActivitiesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Activity activity : activities) {
                bw.write(activity.getId() + "," + activity.getName() + "," + activity.getCost() + "," + activity.getDescription());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving activities to file: " + e.getMessage());
        }
    }
}

