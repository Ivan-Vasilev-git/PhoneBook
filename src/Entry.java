public class Entry implements Comparable {
  private int phoneNumber;
  private String firstName;
  private String lastName;

  public Entry(String firstName) {
    this(0, firstName, "");
  }

  public Entry(int phoneNumber, String firstName) {
    this(phoneNumber, firstName, "");
  }

  public Entry(String firstName, String lastName) {
    this(0, firstName, lastName);
  }

  public Entry(int phoneNumber, String firstName, String lastName) {
    this.phoneNumber = phoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return (firstName + " " + lastName).trim();
  }


  @Override
  public String toString() {
    return (phoneNumber + " " + firstName + " " + lastName).trim();
  }

  @Override
  public int compareTo(Object o) {
    Entry obj = (Entry) o;
    return this.getFirstName().compareTo(obj.getFirstName());
  }
}
