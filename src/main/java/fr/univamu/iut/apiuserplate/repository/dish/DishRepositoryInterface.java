package fr.univamu.iut.apiuserplate.repository.dish;

import fr.univamu.iut.apiuserplate.model.dish.Dish;

import java.util.ArrayList;

public interface DishRepositoryInterface {
    public void close();

    public Dish getDish(int id  );


    public ArrayList<Dish> getAllDishes() ;


    public boolean updateDish( int id, String name, String description, double price );

    public boolean addDish( String name, String description, double price);

    public boolean deleteDish( int id );
}