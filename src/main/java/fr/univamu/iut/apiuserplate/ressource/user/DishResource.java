package fr.univamu.iut.apiuserplate.ressource.user;

import fr.univamu.iut.apiuserplate.repository.dish.DishRepositoryInterface;
import fr.univamu.iut.apiuserplate.model.dish.Dish;
import fr.univamu.iut.apiuserplate.service.dish.DishService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("/dishes")
@ApplicationScoped
public class DishResource {

    private DishService dishService;

    public DishResource(){}

    public @Inject DishResource( DishRepositoryInterface DishRepo ){
        this.dishService = new DishService(DishRepo);
    }

    public DishService getDishService() {
        return dishService;
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public String getAllDishes() {
        return dishService.getAllDishesJSON();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getDish(@PathParam("id") int id) {
        String result = dishService.getDishJSON(id);

        if( result == null )
            throw new NotFoundException();

        return result;
    }

    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateDish(@PathParam("id") int id, Dish dish) {
        if( ! dishService.updateDish(id, dish) )
            throw new NotFoundException();
        else
            return Response.ok(dish.getName() + " updated").build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addDish(Dish dish) {
        if( ! dishService.addDish(dish)) {
            throw new BadRequestException();
        } else {
            return Response.ok(dish.getName() + " added").build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteDish(@PathParam("id") int id) {
        if( ! dishService.deleteDish(id) )
            throw new NotFoundException();
        else
            return Response.ok("Dish deleted").build();
    }
}
