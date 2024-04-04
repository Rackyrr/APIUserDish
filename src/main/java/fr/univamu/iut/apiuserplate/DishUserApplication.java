package fr.univamu.iut.apiuserplate;

import fr.univamu.iut.apiuserplate.repository.dish.DishRepositoryInterface;
import fr.univamu.iut.apiuserplate.repository.dish.DishRepositoryMariadb;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class DishUserApplication extends Application {


    @Produces
    private DishRepositoryInterface openDishDbConnection(){
        DishRepositoryMariadb db = null;

        try{
            db = new DishRepositoryMariadb("jdbc:mariadb://mysql-espace-virtuel-valentin.alwaysdata.net/espace-virtuel-valentin_platuser", "352228_val", "pr*3Xr.Kr\"ATaFz");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }


    private void closeDishDbConnection(@Disposes DishRepositoryInterface dishRepo ) {
        dishRepo.close();
    }

}

