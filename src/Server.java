import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
      try {
          EmployeeService obj=new EmployeeServiceImpl();
          Registry registry = LocateRegistry.createRegistry(1099);
          registry.rebind("EmployeeService", obj);


          System.out.println("Employee  RMI Server is running...");

      }





        catch (Exception e){
          e.printStackTrace();
        }


    }
}
