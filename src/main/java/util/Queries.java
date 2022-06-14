package util;

public final class Queries {

    private Queries() {}

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    //UPDATED

    public static final String CHECK_IF_EMPLOYEE_EXISTS = "SELECT * FROM employees WHERE employeeNumber = ?;";


    public static final String ADD_EMPLOYEE = "INSERT INTO employees(employeeNumber,lastName,firstName,extension,email,officeCode,reportsTo,jobTitle)" +
               "VALUES(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE lastName = VALUES(lastname)," +
            "                       firstName = VALUES(firstName), " +
            "                        extension = VALUES(extension)," +
            "                        email = VALUES(email)," +
            "                        officeCode= VALUES(officeCode)," +
            "                        reportsTo= VALUES(reportsTo)," +
            "                        jobTitle= VALUES(jobTitle)";

    public static final String UPDATE_EMPLOYEE = "UPDATE employees " +
            "SET lastName =?," +
            "firstName=?," +
            "    extension=?," +
            "    email=?," +
            "    officeCode=?," +
            "    reportsTo=?," +
            "    jobTitle=?" +
            "WHERE employeeNumber =?";

    public static final String FIND_ALL_OFFICES = "SELECT * FROM offices";

    public static final String FIND_OFFICE_BY_CODE = "SELECT * FROM offices WHERE officeCode = ?";

    public static final String CHECK_IF_OFFICE_EXISTS = "SELECT * FROM offices WHERE officeCode = ?;";

    public static final String ADD_OFFICE = "INSERT INTO offices(officeCode,city,phone,addressLine1,addressLine2,state,country,postalCode,territory)" +
            "VALUES(?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE " +
            "                        city = VALUES(city)," +
            "                        phone = VALUES(phone), " +
            "                        addressLine1 = VALUES(addressLine1)," +
            "                        addressLine2 = VALUES(addressLine2)," +
            "                        state= VALUES(state)," +
            "                        country= VALUES(country)," +
            "                        postalCode= VALUES(postalCode),"+
            "                        territory= VALUES(territory)";

    public static final String UPDATE_OFFICE = "UPDATE offices " +
            "SET city =?," +
            "phone=?," +
            "    addressLine1=?," +
            "    addressLine2=?," +
            "    state=?," +
            "    country=?," +
            "    postalCode=?" +
            "    territory=?" +
            "WHERE officeCode =?";

    public static final String FIND_ALL_CUSTOMERS = "SELECT * FROM (SELECT * from customers " +
            "INNER JOIN orders USING (customerNumber) " +
            ") AS T";

    public static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM (SELECT * from customers " +
            "INNER JOIN orders USING (customerNumber) " +
            ") AS T " +
            "WHERE customerNumber = ?;";

    public static final String CHECK_IF_CUSTOMER_EXISTS = "SELECT * FROM (SELECT * from customers " +
            "INNER JOIN orders USING (customerNumber) " +
            ") AS T " +
            "WHERE customerNumber = ?;";

    public static final String ADD_CUSTOMER = "INSERT INTO customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE " +
            "                customerName = VALUES(customerName)," +
            "                contactLastName = VALUES(contactLastName)," +
            "                contactFirstName = VALUES(contactFirstName)," +
            "                phone = VALUES(phone)," +
            "                addressLine1 = VALUES(addressLine1)," +
            "                addressLine2 = VALUES(addressLine2)," +
            "                city = VALUES(city)," +
            "                state = VALUES(state)," +
            "                postalCode = VALUES(postalCode)," +
            "                country = VALUES(country)," +
            "                salesRepEmployeeNumber = VALUES(salesRepEmployeeNumber)," +
            "                creditLimit = VALUES(creditLimit)";

    public static final String UPDATE_CUSTOMER = "UPDATE customers " +
            "SET " +
            "                customerName = ?," +
            "                contactLastName = ?," +
            "                contactFirstName = ?," +
            "                phone = ?," +
            "                addressLine1 = ?," +
            "                addressLine2 = ?," +
            "                city = ?," +
            "                state = ?," +
            "                postalCode = ?," +
            "                country = ?," +
            "                salesRepEmployeeNumber = ?," +
            "                creditLimit = ?" +
            " WHERE customerNumber = ?";

    public static final String FIND_ALL_ORDERS = "SELECT * FROM(" +
            "SELECT * FROM orders" +
            "INNER JOIN customers USING (customerNumber)) AS T ";

    public static final String FIND_ORDER_BY_ID = "SELECT * FROM(" +
            "SELECT * FROM orders" +
            "INNER JOIN customers USING (customerNumber)) AS T " +
            "WHERE customerNumber = ?;";

    public static final String CHECK_IF_ORDER_EXISTS = "SELECT * FROM(" +
            "SELECT * FROM orders" +
            "INNER JOIN customers USING (customerNumber)) AS T " +
            "WHERE customerNumber = ?;";

    public static final String ADD_ORDER = "INSERT INTO orders(orderNumber,orderDate,requiredDate,shippedDate,status,comments,customerNumber) " +
            "VALUES(?,?,?,?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE orderDate = VALUES(orderDate)," +
            "                        requiredDate = VALUES(requiredDate)," +
            "                        shippedDate= VALUES(shippedDate)," +
            "                        status= VALUES(status)," +
            "                        comments= VALUES(comments)," +
            "                        customerNumber= VALUES(customerNumber)";

    public static final String UPDATE_ORDER = "UPDATE orders " +
            "SET " +
            "                        orderDate = ?," +
            "                        requiredDate = ?," +
            "                        shippedDate= ?," +
            "                        status= ?," +
            "                        comments= ?," +
            "                        customerNumber= ? " +
            "WHERE orderNumber = ?;";
}
