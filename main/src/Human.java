public abstract class Human {
    private String firstName;
    private String secondName;
    private int age;
    private int id;
    public Human(){}
    public Human(int id, String firstName, String surname, int age, String telephoneNumber){
        this.id = id;
        this.firstName = firstName;
        this.secondName = surname;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String surname) {
        this.secondName = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
