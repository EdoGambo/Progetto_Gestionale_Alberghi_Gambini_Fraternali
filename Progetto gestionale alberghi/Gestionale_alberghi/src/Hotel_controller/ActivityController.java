package Hotel_controller;

import Hotel_source.Activity;
import Hotel_source.ActivityManager;
import Hotel_view.ActivityView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityController {
    private ActivityManager activityManager;
    private ActivityView activityView;

    public ActivityController(ActivityManager activityManager, ActivityView activityView) {
        this.activityManager = activityManager;
        this.activityView = activityView;

        this.activityView.addCreateActivityListener(new CreateActivityListener());
        this.activityView.addGetActivityDetailsListener(new GetActivityDetailsListener());
        this.activityView.addUpdateActivityListener(new UpdateActivityListener());
        this.activityView.addDeleteActivityListener(new DeleteActivityListener());
    }

    class CreateActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = activityView.getName();
            double cost = activityView.getCost();
            String description = activityView.getDescription();
            Activity activity = activityManager.createActivity(name, cost, description);
            activityView.setActivityDetails("Activity created: " + activity.getId());
        }
    }

    class GetActivityDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int activityId = activityView.getActivityId();
                Activity activity = activityManager.getActivityDetails(activityId);
                if (activity != null) {
                    activityView.setActivityDetails("Activity ID: " + activity.getId() +
                            "\nName: " + activity.getName() +
                            "\nCost: " + activity.getCost() +
                            "\nDescription: " + activity.getDescription());
                } else {
                    activityView.setActivityDetails("Activity not found.");
                }
            } catch (NumberFormatException ex) {
                activityView.setActivityDetails("Invalid activity ID.");
            }
        }
    }

    class UpdateActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int activityId = activityView.getUpdateActivityId();
                String name = activityView.getUpdateName();
                double cost = activityView.getUpdateCost();
                String description = activityView.getUpdateDescription();
                activityManager.updateActivityDetails(activityId, name, cost, description);
                activityView.setActivityDetails("Activity updated: " + activityId);
            } catch (NumberFormatException ex) {
                activityView.setActivityDetails("Invalid activity ID.");
            }
        }
    }

    class DeleteActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int activityId = activityView.getDeleteActivityId();
                activityManager.cancelActivity(activityId);
                activityView.setActivityDetails("Activity deleted: " + activityId);
            } catch (NumberFormatException ex) {
                activityView.setActivityDetails("Invalid activity ID.");
            }
        }
    }
}
