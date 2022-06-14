package mapper;

import model.entity.Office;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeMapper extends Mapper<Office> {

    @Override
    public Office map(ResultSet result) throws SQLException {
        Office office = new Office();
        office.setId(result.getInt("officeCode"));
        office.setCity(result.getString("city"));
        office.setPhone(result.getString("phone"));
        office.setAddressLine1(result.getString("addressLine1"));
        office.setAddressLine2(result.getString("addressLine1"));
        office.setState(result.getString("state"));
        office.setCountry(result.getString("country"));
        office.setPostalCode(result.getString("postalCode"));
        office.setTerritory(result.getString("territory"));
        return office;
    }

}

