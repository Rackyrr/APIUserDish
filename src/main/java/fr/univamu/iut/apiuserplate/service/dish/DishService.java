package fr.univamu.iut.apiuserplate.service.dish;

import fr.univamu.iut.apiuserplate.repository.dish.DishRepositoryInterface;
import fr.univamu.iut.apiuserplate.model.dish.Dish;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class DishService {
    protected DishRepositoryInterface dishRepo ;

    public  DishService( DishRepositoryInterface dishRepo) {
        this.dishRepo = dishRepo;
    }

    public String getAllDishesJSON(){

        ArrayList<Dish> allBooks = dishRepo.getAllDishes();

        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allBooks);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    public String getDishJSON( int id ){
        String result = null;
        Dish myDish = dishRepo.getDish(id);

        if( myDish != null ) {

            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myDish);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean updateDish(int id, Dish dish) {
        return dishRepo.updateDish(id, dish.getName(), dish.getDescription(), dish.getPrice());
    }

    public boolean addDish(Dish dish) {
        return dishRepo.addDish(dish.getName(), dish.getDescription(), dish.getPrice());
    }

    public boolean deleteDish(int id) {
        return dishRepo.deleteDish(id);
    }
}
