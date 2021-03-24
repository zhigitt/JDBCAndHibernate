package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String query = """
                create table if not exists users(
                id serial primary key,
                first_name varchar,
                last_name varchar,
                age int);
                """;
        int execute = 0;

        try {
            Statement statement =connection.createStatement();
            statement.executeUpdate(query);

            execute = statement.executeUpdate(query);
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(execute != 0 ? "Nice" : "Error");
    }

    public void dropUsersTable() {
        String query = "drop table if exists users";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);

            System.out.println("Deleted table");
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String query = """
                insert into users(first_name, last_name, age)
                values(?, ?, ?);
                """;

        int execute = 0;

        try {
            PreparedStatement preparedStatement =connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            execute = preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        System.out.println(execute != 0 ?  "Error":"Nice");
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
            System.out.println("Deleted");
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users =new ArrayList<>();

        String query = """
                select * from users;
                """;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        String query = "truncate table users";

        int execute = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            execute = preparedStatement.executeUpdate();

            System.out.println("Cleaning table");
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}