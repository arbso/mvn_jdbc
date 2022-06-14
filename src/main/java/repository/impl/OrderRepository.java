package repository.impl;

import mapper.OrderMapper;
import model.entity.Order;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends BaseRepository<Order, Integer> {

    public OrderRepository() {
        super(new OrderMapper());
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_ORDERS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = getMapper().map(result);
                orders.add(order);
            }
            for(Order obj: orders){
                System.out.println(obj.getId()+" "+obj.getOrderDate());
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ORDER_BY_ID)) {
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
             PreparedStatement statement = connection.prepareStatement(Queries.CHECK_IF_ORDER_EXISTS)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                System.out.println("Order with the code: "+id+" exists.");
                return true;
            }else {
                System.out.println("Order with the code: "+id+" does not exist.");
                return false;
            }


        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean save(Order order) {

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.ADD_ORDER)) {

            statement.setInt(1, order.getId());
            statement.setDate(2,java.sql.Date.valueOf((order.getOrderDate())));
            statement.setDate(3,java.sql.Date.valueOf(order.getRequiredDate()));
            statement.setDate(4,java.sql.Date.valueOf(order.getShippedDate()));
            statement.setString(5,order.getStatus());
            statement.setString(6,order.getComments());
            statement.setInt(7,order.getCustomerNumber());

            int result = statement.executeUpdate();
            System.out.println("Record has been added.");

        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Integer update(Order order) {

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_ORDER)) {

            statement.setDate(1,java.sql.Date.valueOf((order.getOrderDate())));
            statement.setDate(2,java.sql.Date.valueOf(order.getRequiredDate()));
            statement.setDate(3,java.sql.Date.valueOf(order.getShippedDate()));
            statement.setString(4,order.getStatus());
            statement.setString(5,order.getComments());
            statement.setInt(6,order.getCustomerNumber());
            statement.setInt(7, order.getId());

            int result = statement.executeUpdate();
            int rowsAffected = statement.getUpdateCount();
            System.out.println(rowsAffected+" rows have been affected.");
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;

    }

}
