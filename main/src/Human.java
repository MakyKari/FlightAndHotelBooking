public class Human {
    private String firstName;
    private String surname;
    private int age;
    private static int idgen = 0;
    private int id;

    public Human(){
        id = idgen++;
    }

    public Human(String firstName, String surname){
        this();
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
