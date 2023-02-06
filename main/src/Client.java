public class Client extends Human{
    private String telephoneNumber;
    private String password;
    public Client(){}
    public Client(int id, String firstName, String secondName, int age, String telephoneNumber, String password){
        super(id,firstName,secondName,age);
        this.telephoneNumber = telephoneNumber;
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
