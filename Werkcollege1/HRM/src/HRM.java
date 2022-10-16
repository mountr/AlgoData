import java.util.ArrayList;

public class HRM implements IHRM{

    // Instantievariabelen
    private ArrayList<Employee> employees;
    private ArrayList<EmployeeRequest> requests;

    //Constructor
    public HRM(ArrayList<Employee> employees) {
        this.employees = employees;
        requests = new ArrayList<>();
    }

    //Checken of employee bestaat
    public Employee employeeExist (String employeeID){
        for(Employee employee : employees){
            if (employee.getEmployeeID().equals(employeeID))
                return employee;
        }
        return null;
    }

    @Override
    public void addVacationRequest(String employeeID, int nrOfDays) {
        if(employeeExist(employeeID)!= null)
            requests.add(new VacationRequest(nrOfDays,employeeExist(employeeID)));
        else System.out.println("De werknemer bestaat niet!");
    }

    @Override
    public void addReimbursementRequest(String employeeID, double amount, String description) {
        if(employeeExist(employeeID) != null)
            requests.add(new ReimbursementRequest(amount,description,employeeExist(employeeID)));
        else
            System.out.println("De werknemer bestaat niet!");
    }
    @Override
    public ArrayList<EmployeeRequest> getEmployeeRequests(String employeeID, Decision preProcDecision, Decision mgrDecision) {
        ArrayList<EmployeeRequest> requestlist = new ArrayList<>();
        if (employeeExist(employeeID) != null) {
            for(EmployeeRequest request : requests){
                if(request.getRequester().equals(employeeExist(employeeID)) && request.getManagerDecision().equals(mgrDecision)
                        && request.getPreProcessingDecision().equals(preProcDecision))
                    requestlist.add(request);
            }
            return requestlist;
        }
        else{
            System.out.println("De werknemer bestaat niet!");
            return null;
        }
    }
    @Override
    public void preProcessRequests() {
        for(EmployeeRequest request : requests) {
            if (request.getManagerDecision().equals(Decision.Undecided)) {
                if (request instanceof VacationRequest) {
                    if (((VacationRequest) request).getNrOfDays() <= request.getRequester().getNrOfFreeDays())
                        request.setPreProcessingDecision(Decision.Accepted);
                    else
                        request.setPreProcessingDecision(Decision.NotAccepted);
                } else if (request instanceof ReimbursementRequest) {
                    if (( ((ReimbursementRequest) request).getAmount() <= request.getRequester().getBudget()))
                        request.setPreProcessingDecision(Decision.Accepted);
                    else
                        request.setPreProcessingDecision(Decision.NotAccepted);
                }
            }
        }
    }
    @Override
    public void approveRequest(String requestID, String managerID) {
        boolean existingrequest = false;
        Employee requester = null;
        EmployeeRequest request = null;
        for(EmployeeRequest r: requests)
            if(r.getRequestID().equals(requestID)){
                existingrequest = true;
                requester = r.getRequester();
                request = r;
                break;
            }
        if(!existingrequest ||!request.getPreProcessingDecision().equals(Decision.Accepted)
                || !request.getManagerDecision().equals(Decision.Undecided) || employeeExist(managerID) == null
                || !requester.getManager().getEmployeeID().equals(managerID)){
            System.out.println("De aanvraag kan niet goedgekeurd worden!");
        }
        else{
            request.setManagerDecision(Decision.Accepted);
            if(request instanceof ReimbursementRequest)
                requester.setBudget((requester.getBudget() - ((ReimbursementRequest) request).getAmount()));
            else if(request instanceof VacationRequest)
                requester.setNrOfFreeDays(requester.getNrOfFreeDays() - (((VacationRequest) request).getNrOfDays()));
            preProcessRequests();
        }

    }
    @Override
    public void disapproveRequest(String requestID, String managerID) {
        boolean existingrequest = false;
        Employee requester = null;
        EmployeeRequest request = null;
        for (EmployeeRequest r : requests)
            if (r.getRequestID().equals(requestID)) {
                existingrequest = true;
                requester = r.getRequester();
                request = r;
                break;
            }
        if (!existingrequest || !request.getManagerDecision().equals(Decision.Undecided)
                || employeeExist(managerID) == null || !requester.getManager().getEmployeeID().equals(managerID)){
            System.out.println("De aanvraag kan niet afgekeurd worden!");
        } else
            request.setManagerDecision(Decision.NotAccepted);
    }
    @Override
    public String toString () {
        String list = "";
        for (int i = 0; i < employees.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(j).getEmployeeID().compareTo(employees.get(index).getEmployeeID()) < 0)
                    index = j;
            }
            Employee emp = employees.get(index);
            employees.set(index, employees.get(i));
            employees.set(i, emp);
        }
        for (Employee employee : employees) {
            String accepted = "";
            String notAccepted = "";
            String undecided = "";
            list = list.concat(employee.toString() + "\n");
            list = list.concat("----------------------\n");
            for (EmployeeRequest request : requests) {
                if (request.getRequester().equals(employee) && request.getManagerDecision().equals(Decision.Accepted))
                    accepted = accepted.concat(request +"\n");
                else if (request.getRequester().equals(employee)
                        && (request.getManagerDecision().equals(Decision.NotAccepted)
                        || request.getPreProcessingDecision().equals(Decision.NotAccepted)))
                    notAccepted = notAccepted.concat(request +"\n");
                else if (request.getRequester().equals(employee) && request.getManagerDecision().equals(Decision.Undecided))
                    undecided = undecided.concat(request +"\n");
            }
            if (accepted.equals(""))
                accepted = "Geen requests beschikbaar" +"\n";
            if (notAccepted.equals(""))
                notAccepted = "Geen requests beschikbaar" +"\n";
            if (undecided.equals(""))
                undecided = "Geen requests beschikbaar" +"\n";
            list = list.concat("Goedgekeurde aanvragen:\n");
            list = list.concat(accepted);
            list = list.concat("Geweigerde aanvragen:\n");
            list = list.concat(notAccepted);
            list = list.concat("Aanvragen die nog moeten goedgekeurd worden:\n");
            list = list.concat(undecided);
            list = list.concat("\n");
        }
        return list;
    }
}