package run;

import model.entity.Customer;
import model.entity.Employee;
import model.entity.Office;
import model.entity.Order;
import repository.impl.CustomerRepository;
import repository.impl.EmployeeRepository;
import repository.impl.OfficeRepository;
import repository.impl.OrderRepository;
import util.JdbcConnection;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        JdbcConnection.connect();
        EmployeeRepository emp = new EmployeeRepository();
        OfficeRepository off = new OfficeRepository();
        CustomerRepository cus = new CustomerRepository();
        OrderRepository ord = new OrderRepository();

        boolean test1 = emp.exists(1);

        Employee test2 = new Employee(44343,
                "bruh",
                "bruh",
                "x6",
                "bruh@gmail.com",
                "2",
                "1002",
                "pronar");
        emp.save(test2);

        Employee test3 = new Employee(1,
                "sotiri",
                "arbri",
                "x43414",
                "2313@gmail.com",
                "2",
                "1002",
                "pronar");
        emp.update(test2);

        Office test4 = new Office(32,
                "Tirane",
                "+34567890",
                "Stadiumi Arena",
                "",
                "Albania",
                "Albania",
                "1019",
                "SHQYPNI"
        );
        off.save(test4);

        Customer test5 = new Customer(121212,
                "Filan",
                "K1",
                "K2",
                "+567890",
                "",
                "",
                "Durres",
                "Albaniaaaaa",
                "1019",
                "Albania",
                1370,
                900.0
                );


        cus.update(test5);

        Order test6 = new Order(
                90000,
                "2000-01-01",
                "2000-02-02",
                "2000-05-03",
                "Shipped",
                "Check on availabiity",
                363
        );

        ord.update(test6);
    }

}
