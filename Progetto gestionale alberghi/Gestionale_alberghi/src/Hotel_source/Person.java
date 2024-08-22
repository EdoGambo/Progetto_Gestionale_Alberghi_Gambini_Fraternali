package Hotel_source;

public class Person{
    protected String name;
    protected String email;
    protected String phone;

    public Person(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}