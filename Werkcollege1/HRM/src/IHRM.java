
import java.util.ArrayList;

public interface IHRM {

    public void addVacationRequest(String employeeID, int nrOfDays);

    public void addReimbursementRequest(String employeeID, double amount, String description);

    public ArrayList<EmployeeRequest> getEmployeeRequests(String employeeID, Decision preProcDecision, Decision mgrDecision);

    public void preProcessRequests();

    public void approveRequest(String requestID, String managerID);

    public void disapproveRequest(String requestID, String managerID);
}