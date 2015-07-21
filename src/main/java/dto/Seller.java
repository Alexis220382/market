package dto;


public class Seller {
    
    private int idSeller;
    private String lastName;
    private String firstName;
    private String secondName;

    public Seller(int idSeller, String lastName, String firstName, String secondName) {
        this.idSeller = idSeller;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Seller(String lastName, String firstName, String secondName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getIdSeller() { return idSeller; }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    
    

}
