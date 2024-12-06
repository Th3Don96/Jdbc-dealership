package com.pluralsight.dealership;


import com.pluralsight.Contract;
import com.pluralsight.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDataManager {

    private BasicDataSource dataSource;

    public DealershipDataManager(String username, String password) {
        this.dataSource = new BasicDataSource();
        this.dataSource.setUrl("jdbc:mysql://localhost:3307/dealership");
        this.dataSource.setUsername(username);
        this.dataSource.setPassword(password);
    }

    public List<Vehicle> getVehicleByVin(String name) {
        List<Vehicle> vehicle = new ArrayList<>();
        String query = "SELECT * FROM vehicle WHERE model LIKE ? OR vin LIKE ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");

//            System.out.println(statement);

            try(ResultSet results = statement.executeQuery()) {
                while(results.next()) {
                    System.out.printf("""
                            Vin:  %s
                            Make: %s
                            Model:%s
                            Year: %s
                            ---------------------
                            """,results.getString("vin"),results.getString("first_name"),
                   results.getString("model"),results.getString("year"));
                    vehicle.add(new Vehicle());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }
    public List<Contract> getContractByVin(String vin) {
        List<Contract> contract = new ArrayList<>();
        String query = "SELECT * FROM contract WHERE name LIKE ? OR vin LIKE ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1, "%" + vin + "%");
            statement.setString(2, "%" + vin + "%");

//            System.out.println(statement);

            try(ResultSet results = statement.executeQuery()) {
                while(results.next()) {
                    System.out.printf("""
                            Vin:  %s                    Sales Date:%s
                            Customer Name: %s
                            Email: %s
                            
                            Model:%s
                            Year: %s
                            Price:%s
                            
                            ---------------------
                            """,results.getString("vin"),results.getString("sales_date") results.getString("name"),
                            results.getString("model"),results.getString("year"),results.getString("price"));
                    contract.add(new Contract());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

}

