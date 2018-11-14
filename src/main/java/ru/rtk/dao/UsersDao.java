package ru.rtk.dao;

import ru.rtk.entity.Users;

import java.util.List;

public interface UsersDao extends BasicDao<Users>{
    /**
     * method for finding List Users by login
     *@param lgn = login of user
     *@return List users  with success parameters
     * **/
    List<Users> getUsersByLgn(String lgn);

    /**
     * method for finding List Users of all Admin
     *@return List users  with ADMIN role
     * **/
    List<Users> getAdminAll();
}
