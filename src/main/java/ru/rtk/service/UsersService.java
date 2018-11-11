package ru.rtk.service;

import ru.rtk.entity.Users;

import java.util.List;

public interface UsersService {
    /**
     * method for add user to base
     *
     * @param users = new user for creation in DB
     * @return created user
     */
    Users addUsers(Users users);

    /**
     * method for receiving all users
     *
     * @return all Users
     */
    List<Users> getAllUsers();

    /**
     * method for receive specify Users by id
     *
     * @param id = uniq Users id
     * @return specify Users by id
     */
    Users getUsersById(long id);

    /**
     * method for user delete
     *
     * @param id = User's id for delete
     * @return removed Users
     */
    Users deleteUsers(long id);

    /**
     * method for update Users
     *
     * @param users = update existing users in DB
     * @return updated users
     */
    Users updUsers(Users users);

    /**
     * method for receiving all users by login
     *
     * @return all Users by login
     */
    List<Users> getUsersByLgn(String lgn);
}

