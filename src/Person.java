import java.util.Date;

abstract class Person {
    private int id;
    private String fullName;
    private Date dateOfBirth;
    private String contactInfo;

    public Person(int id, String fullName, Date dateOfBirth, String contactInfo) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactInfo = contactInfo;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getContactInfo() { return contactInfo; }
}
