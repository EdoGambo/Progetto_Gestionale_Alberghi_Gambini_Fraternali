package Hotel_source;

public class Employee extends Person{
    private int id;
    private double salary;
    private Skill skill;

    public Employee(int id, String name, double salary, Skill skill) {
        super(name);
        this.id = id;
        this.salary = salary;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Skill getSkill(){
        return this.skill;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSkill (Skill skill){
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' + 
                ", id=" + id +
                ", salary=" + salary +
                ", skill='" + skill + '\'' +
                '}';
    }
}
