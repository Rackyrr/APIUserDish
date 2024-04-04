package fr.univamu.iut.apiuserplate.repository.user;

import fr.univamu.iut.apiuserplate.model.user.User;

import java.util.ArrayList;

public interface UserRepositoryInterface {
    public User getUser(int id);
    public ArrayList<User> getAllUsers();
}
