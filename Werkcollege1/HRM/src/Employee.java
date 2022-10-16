import java.util.Objects;
public class Employee extends Person implements Comparable<Employee> {

    // Instantievariabelen
    private double budget;
    private int nrOfFreeDays;
    private String employeeID;
    private Employee manager;

    // Constructor
    public Employee(String employeeID, Employee manager, String firstname, String lastname, String gender, String email) {
        super(firstname, lastname, gender, email);
        this.budget = 1000;
        this.nrOfFreeDays = 100;
        this.employeeID = employeeID;
        this.manager = manager;
    }

    // Accessor en Mutator
    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
    public int getNrOfFreeDays() {
        return nrOfFreeDays;
    }
    public void setNrOfFreeDays(int nrOfFreeDays) {
        this.nrOfFreeDays = nrOfFreeDays;
    }
    public String getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public Employee getManager() {
        return manager;
    }
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    // Equals & Hashcode krijg je door te rechterklikken, op "generate" te drukken, en dan "Equals & Hashcode" aan te duiden
    // Daarna gewoon kijken op basis van welke instantievariabelen je moet vergelijken & aanvinken in IntelliJ


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return getEmployeeID().equals(employee.getEmployeeID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBudget(), getNrOfFreeDays(), getEmployeeID(), getManager());
    }

    @Override
    public int compareTo(Employee e) {
        return this.employeeID.compareTo(e.employeeID);
    }
    @Override
    public String toString() {
        if (this.manager == null)
            return employeeID + " " + super.getFirstname() + " " + super.getLastname();
        return employeeID + " " + super.getFirstname() + " " + super.getLastname() + " (Manager: " + manager.getEmployeeID() + ")";
    }
}