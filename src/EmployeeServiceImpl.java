




import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {


    private final String url = "jdbc:mysql://localhost:3306/company_db";
    private final String user = "root";
    private final String password = "";

    public EmployeeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addEmployee(Employee e) throws RemoteException {
        String sql = "INSERT INTO employees (id, name) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee getEmployee(int id) throws RemoteException {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Employee(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteEmployee(int id) throws RemoteException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee e) throws RemoteException {
        String sql = "UPDATE employees SET name = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}