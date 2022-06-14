package repository.impl;

import mapper.EmployeeMapper;
import model.entity.Employee;
import util.JdbcConnection;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee, Integer> {

    public EmployeeRepository() {
        super(new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_EMPLOYEES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee employee = getMapper().map(result);
                employees.add(employee);
            }
            for(Employee obj: employees){
                System.out.println(obj.getFirstName()+" "+obj.getLastName());
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.CHECK_IF_EMPLOYEE_EXISTS)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            // meqe query printon vetem 1 rekord nqs resultseti mund te beje next atehere ekziston perndryshe false
            if(result.next()) {
                System.out.println("Employee with the ID: "+id+" exists.");
                return true;
            }else {
                System.out.println("Employee with the ID: "+id+" does not exist.");
                return false;
            }


        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean save(Employee employee) {

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.ADD_EMPLOYEE)) {

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getExtension());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getOfficeCode());
            statement.setString(7, employee.getReportsTo());
            statement.setString(8, employee.getJobTitle());

            int result = statement.executeUpdate();
            System.out.println("Record has been added.");

        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Integer update(Employee employee) {

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getExtension());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getOfficeCode());
            statement.setString(6, employee.getReportsTo());
            statement.setString(7, employee.getJobTitle());
            statement.setInt(8, employee.getId());

            int result = statement.executeUpdate();
            int rowsAffected = statement.getUpdateCount();
            System.out.println(rowsAffected+" rows have been affected.");
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;

    }

}
