package fr.univamu.iut.apiuserplate.repository.user;

import fr.univamu.iut.apiuserplate.model.dish.Dish;
import fr.univamu.iut.apiuserplate.model.user.User;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {

    protected Connection dbConnection ;

    public UserRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public User getUser(int id) {
        User selectedUser = null;

        String query = "SELECT * FROM user WHERE id=?";
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, String.valueOf(id));

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                String username = result.getString("username");
                String password = result.getString("password");
                String email = result.getString("email");
                String deliveryAddress = result.getString("deliveryAddress");

                selectedUser = new User(id, username, password, email, deliveryAddress);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers(){
        ArrayList<User> listUsers = new ArrayList<User>();

        String query = "SELECT * FROM user";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ResultSet result = ps.executeQuery();

            while( result.next() )
            {
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String email = result.getString("email");
                String deliveryAddress = result.getString("deliveryAddress");

                listUsers.add(new User(id, username, password, email, deliveryAddress));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }
}
