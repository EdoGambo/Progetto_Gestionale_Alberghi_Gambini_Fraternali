package Hotel_source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeManager{
    private List<Employee> employees;
    private Map<Integer, Map<LocalDate, Boolean>> attendanceRecords;
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String ATTENDANCE_FILE = "attendance.txt";

    public EmployeeManager() {
        this.employees = new ArrayList<>();
        this.attendanceRecords = new HashMap<>();
        loadEmployeesFromFile();
        loadAttendanceFromFile();
    }

    public Employee addEmployee(String name, double salary, Skill skill) {
        int newId = employees.size() + 1; 
        Employee newEmployee = new Employee(newId, name, salary, skill);
        employees.add(newEmployee);
        saveEmployeesToFile();
        return newEmployee;
    }

    public Employee getEmployeeDetails(int employeeId) {
        return employees.stream()
                        .filter(emp -> emp.getId() == employeeId)
                        .findFirst()
                        .orElse(null);
    }

    public void updateEmployeeDetails(int employeeId, String name, Skill skill, double salary) {
        Employee employee = getEmployeeDetails(employeeId);
        if (employee != null) {
            employee.setName(name);
            employee.setSkill(skill);
            employee.setSalary(salary);
            saveEmployeesToFile();
        }
    }

    public void removeEmployee(int employeeId) {
        employees.removeIf(emp -> emp.getId() == employeeId);
        attendanceRecords.remove(employeeId);
        saveEmployeesToFile();
    }

    public void trackEmployeeAttendance(int employeeId, LocalDate date, boolean isPresent) {
        Map<LocalDate, Boolean> dailyRecord = attendanceRecords.getOrDefault(employeeId, new HashMap<>());
        dailyRecord.put(date, isPresent);
        attendanceRecords.put(employeeId, dailyRecord);
        saveAttendanceToFile();

        if (!isPresent) {
            System.out.println("Il dipendente " + getEmployeeDetails(employeeId).getName() + " non era presente il giorno " + date);
        } else {
            System.out.println("Il dipendente " + getEmployeeDetails(employeeId).getName() + " era presente il giorno " + date);
        }
    }

    private void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + "," + employee.getSalary() + "," + employee.getSkill() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to employee file: " + e.getMessage());
        }
    }

    private void saveAttendanceToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ATTENDANCE_FILE))) {
            for (Map.Entry<Integer, Map<LocalDate, Boolean>> entry : attendanceRecords.entrySet()) {
                int employeeId = entry.getKey();
                for (Map.Entry<LocalDate, Boolean> attendanceEntry : entry.getValue().entrySet()) {
                    writer.write(employeeId + "," + attendanceEntry.getKey() + "," + attendanceEntry.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to attendance file: " + e.getMessage());
        }
    }

    private void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { 
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double salary = Double.parseDouble(parts[2]);
                    Skill skill = Skill.valueOf(parts[3]);
                    employees.add(new Employee(id, name, salary, skill));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from employee file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing employee data: " + e.getMessage());
        }
    }

    private void loadAttendanceFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ATTENDANCE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { 
                    int employeeId = Integer.parseInt(parts[0]);
                    LocalDate date = LocalDate.parse(parts[1]);
                    boolean isPresent = Boolean.parseBoolean(parts[2]);
                    Map<LocalDate, Boolean> dailyRecord = attendanceRecords.getOrDefault(employeeId, new HashMap<>());
                    dailyRecord.put(date, isPresent);
                    attendanceRecords.put(employeeId, dailyRecord);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from attendance file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing attendance data: " + e.getMessage());
        }
    }
}