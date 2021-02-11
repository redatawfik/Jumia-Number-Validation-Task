package com.number.validation.dao;

import com.number.validation.model.Customer;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.number.validation.dao.Constants.*;

@Configuration
public class CustomerDao {

    public List<Customer> getAll() {

        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");

            while (resultSet.next()) {
                int id = resultSet.getInt(ID_COLUMN);
                String name = resultSet.getString(NAME_COLUMN);
                String phoneNumber = resultSet.getString(PHONE_COLUMN);

                Customer customer = new Customer(id, name, phoneNumber);
                customers.add(customer);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            closeQuietly(connection, statement, resultSet);
        }

        return customers;
    }

    private void closeQuietly(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
