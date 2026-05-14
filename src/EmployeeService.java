import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
public interface EmployeeService extends Remote {

    boolean addEmployee(Employee e) throws RemoteException;

    Employee getEmployee(int id) throws RemoteException;

    boolean deleteEmployee(int id) throws RemoteException;

    boolean updateEmployee(Employee e) throws RemoteException;

    List<Employee> getAllEmployees() throws RemoteException;
}
