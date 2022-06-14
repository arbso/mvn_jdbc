package repository.impl;

import mapper.CustomerMapper;
import model.entity.Customer;
import util.JdbcConnection;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends BaseRepository<Customer, Integer> {

    public CustomerRepository() {
        super(new CustomerMapper());
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_OFFICES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Customer customer = getMapper().map(result);
                customers.add(customer);
            }
            for(Customer obj: customers){
                System.out.println(obj.getCustomerName());
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_OFFICE_BY_CODE)) {
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
             PreparedStatement statement = connection.prepareStatement(Queries.CHECK_IF_OFFICE_EXISTS)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                System.out.println("Customer with the code: "+id+" exists.");
                return true;
            }else {
                System.out.println("Customer with the code: "+id+" does not exist.");
                return false;
            }


        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean save(Customer customer) {

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.ADD_CUSTOMER)) {

            statement.setInt(1, customer.getId());
            statement.setString(2,customer.getCustomerName());
            statement.setString(3,customer.getContactLastName());
            statement.setString(4,customer.getContactFirstName());
            statement.setString(5,customer.getPhone());
            statement.setString(6,customer.getAddressLine1());
            statement.setString(7,customer.getAddressLine2());
            statement.setString(8,customer.getCity());
            statement.setString(9,customer.getState());
            statement.setString(10,customer.getPostalCode());
            statement.setString(11,customer.getCountry());
            statement.setInt(12,customer.getSalesRepEmployeeNumber());
            statement.setDouble(13,customer.getCreditLimit());


            int result = statement.executeUpdate();
            System.out.println("Record has been added.");

        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Integer update(Customer customer) {

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_CUSTOMER)) {

            statement.setString(1,customer.getCustomerName());
            statement.setString(2,customer.getContactLastName());
            statement.setString(3,customer.getContactFirstName());
            statement.setString(4,customer.getPhone());
            statement.setString(5,customer.getAddressLine1());
            statement.setString(6,customer.getAddressLine2());
            statement.setString(7,customer.getCity());
            statement.setString(8,customer.getState());
            statement.setString(9,customer.getPostalCode());
            statement.setString(10,customer.getCountry());
            statement.setInt(11,customer.getSalesRepEmployeeNumber());
            statement.setDouble(12,customer.getCreditLimit());
            statement.setInt(13, customer.getId());

            int result = statement.executeUpdate();
            int rowsAffected = statement.getUpdateCount();
            System.out.println(rowsAffected+" rows have been affected.");
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;

    }

}
