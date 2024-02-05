package org.example.repository.jdbc;

import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcResourceRepository implements ResourceRepository {
    private String userName = "postgres";
    private String userPassword = "1234";

    @Override
    public List<Resource> getAll() {
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ARK",userName,userPassword)){
            Statement statement = connection.createStatement();
             String sql ="select * from resources";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Resource> result = new ArrayList<>();
            while(resultSet.next()){
            Resource resource = new Resource();
            resource.setId(resultSet.getInt("id"));
            resource.setName(resultSet.getString("name"));
            resource.setPrice(resultSet.getInt("price"));
            result.add(resource);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resource getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void save(Resource resource) {

    }
}
