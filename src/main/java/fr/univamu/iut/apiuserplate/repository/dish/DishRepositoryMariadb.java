package fr.univamu.iut.apiuserplate.repository.dish;

import fr.univamu.iut.apiuserplate.model.dish.Dish;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class DishRepositoryMariadb implements DishRepositoryInterface, Closeable {
    protected Connection dbConnection ;

    public DishRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public Dish getDish(int id) {

        Dish selectedDish = null;

        String query = "SELECT * FROM dish WHERE id=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, String.valueOf(id));

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                selectedDish = new Dish(id, name, description, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedDish;
    }

    @Override
    public ArrayList<Dish> getAllDishes() {
        ArrayList<Dish> listDishes ;

        String query = "SELECT * FROM dish";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ResultSet result = ps.executeQuery();

            listDishes = new ArrayList<>();

            while ( result.next() )
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                Dish currentDish = new Dish(id, name, description, price);

                listDishes.add(currentDish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listDishes;
    }

    @Override
    public boolean updateDish(int id, String name, String description, double price) {
        String query = "UPDATE dish SET name=?, description=?, price=? WHERE id=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, String.valueOf(price));
            ps.setString(4, String.valueOf(id));

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean addDish(String name, String description, double price) {
        String query = "INSERT INTO dish (name, description, price) VALUES (?, ?, ?)";
        int nbRowCreated = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, String.valueOf(price));

            nbRowCreated = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ( nbRowCreated != 0 );
    }

    @Override
    public boolean deleteDish(int id) {
        String query = "DELETE FROM dish WHERE id=?";
        int nbRowDeleted = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, String.valueOf(id));

            nbRowDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowDeleted != 0 );
    }
}

