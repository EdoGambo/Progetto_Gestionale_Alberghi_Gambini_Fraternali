package Hotel_controller;

import Hotel_source.Employee;
import Hotel_source.EmployeeManager;
import Hotel_source.Skill;
import Hotel_view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EmployeeController {
    private EmployeeView employeeView;
    private EmployeeManager employeeManager;

    public EmployeeController(EmployeeView employeeView, EmployeeManager employeeManager) {
        this.employeeView = employeeView;
        this.employeeManager = employeeManager;

        // Registrazione degli ascoltatori degli eventi
        this.employeeView.addAddEmployeeListener(new AddEmployeeListener());
        this.employeeView.addGetEmployeeDetailsListener(new GetEmployeeDetailsListener());
        this.employeeView.addUpdateEmployeeDetailsListener(new UpdateEmployeeDetailsListener());
        this.employeeView.addRemoveEmployeeListener(new RemoveEmployeeListener());
        this.employeeView.addTrackAttendanceListener(new TrackAttendanceListener());
    }

    class AddEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = employeeView.getName();
                double salary = employeeView.getSalary();
                Skill skill = employeeView.getSkill();
                employeeManager.addEmployee(name, salary, skill);
                JOptionPane.showMessageDialog(employeeView, "Employee added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(employeeView, "Error adding employee: " + ex.getMessage());
            }
        }
    }

    class GetEmployeeDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int employeeId = employeeView.getEmployeeId();
                Employee employee = employeeManager.getEmployeeDetails(employeeId);
                if (employee != null) {
                    employeeView.setEmployeeDetails(employee.toString());
                } else {
                    JOptionPane.showMessageDialog(employeeView, "Employee not found!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(employeeView, "Error retrieving employee details: " + ex.getMessage());
            }
        }
    }

    class UpdateEmployeeDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int employeeId = employeeView.getUpdateEmployeeId();
                String name = employeeView.getUpdateName();
                double salary = employeeView.getUpdateSalary();
                Skill skill = employeeView.getUpdateSkill();
                employeeManager.updateEmployeeDetails(employeeId, name, skill, salary);
                JOptionPane.showMessageDialog(employeeView, "Employee updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(employeeView, "Error updating employee: " + ex.getMessage());
            }
        }
    }

    class RemoveEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int employeeId = employeeView.getRemoveEmployeeId();
                employeeManager.removeEmployee(employeeId);
                JOptionPane.showMessageDialog(employeeView, "Employee removed successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(employeeView, "Error removing employee: " + ex.getMessage());
            }
        }
    }

    class TrackAttendanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int employeeId = employeeView.getAttendanceEmployeeId();
                LocalDate date = employeeView.getAttendanceDate();
                boolean isPresent = employeeView.getIsPresent();
                employeeManager.trackEmployeeAttendance(employeeId, date, isPresent);
                JOptionPane.showMessageDialog(employeeView, "Attendance tracked successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(employeeView, "Error tracking attendance: " + ex.getMessage());
            }
        }
    }
}


