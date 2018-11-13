package ru.rtk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rtk.entity.Users;
import ru.rtk.service.UsersService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/add/usr", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users addUsers(@RequestBody Users users){
        return usersService.addUsers(users);
    }

    @RequestMapping(value = "/add/adm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users addAdmin(@RequestBody Users users){
        return usersService.addUsers(users);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Users> getUsers(){
        return usersService.getAllUsers();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users getUsersById(@PathVariable(value = "id") String id){
        Users userById;
        userById = usersService.getUsersById(Long.parseLong(id));
        if (userById == null){
            Users errUser = new Users();
            errUser.setId(Long.parseLong(id));
            errUser.setLogin("error");
            errUser.setPassword("Can't get user with that ID");
            return errUser;
        }  else {
            return userById;
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users deleteUsers(@PathVariable(value = "id") String id) {
        return usersService.deleteUsers(Long.parseLong(id));
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Users updUsers(@RequestBody Users users){
        return usersService.updUsers(users);
    }

    @RequestMapping(value = "/getusersbylgn/{lgn}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Users> getUsersByLgn(@PathVariable(value = "lgn") String lgn){
        return usersService.getUsersByLgn(lgn);
    }
}
