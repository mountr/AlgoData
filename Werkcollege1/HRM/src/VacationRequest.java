
public class VacationRequest extends EmployeeRequest {

    private int nrOfDays;

    public VacationRequest(int nrOfDays, Employee employee) {
        super(employee);
        this.nrOfDays = nrOfDays;
    }

    public int getNrOfDays() {
        return nrOfDays;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        VacationRequest other = (VacationRequest) obj;
        if (nrOfDays != other.nrOfDays)
            return false;
        return true;
    }
}