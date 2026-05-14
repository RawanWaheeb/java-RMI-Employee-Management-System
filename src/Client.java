
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
try {
    Registry registry= LocateRegistry.getRegistry("localhost",1099);
    EmployeeService obj=( EmployeeService) registry.lookup("EmployeeService");

    Employee e1 = new Employee(1, "Omar");
    Employee e2 = new Employee(2, "Ghaidaa");
    Employee e3 = new Employee(3, "Hamza");
    Employee e4 = new Employee(4, "Mariam");
    Employee e5 = new Employee(5, "Manar");

    obj.addEmployee(e1);
    obj.addEmployee(e2);
    obj.addEmployee(e3);
    obj.addEmployee(e4);
    obj.addEmployee(e5);


    System.out.println("All Employees: " + obj.getAllEmployees());
    Employee updated = new Employee(1, "Rawan");
    obj.updateEmployee(updated);

    System.out.println("After Update: " + obj.getAllEmployees());

    obj.deleteEmployee(2);

    System.out.println("After Delete: " + obj.getAllEmployees());


}


catch (Exception e) {
    e.printStackTrace();
}


    }


}
