package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService  = new UserServiceImpl();

        //Connection
//        System.out.println(Util.getConnection());

        //Create table
        userService.createUsersTable();

        //Save
//        userService.saveUser("Zhigit", "Turumbekov", (byte) 21);
        userService.saveUser("Nuke", "Medetov", (byte) 18);

        //Get all
        System.out.println(userService.getAllUsers());

        //Delete
        userService.removeUserById(1L);

        //Clean table
        userService.cleanUsersTable();

        //Delete table
        userService.dropUsersTable();
    }
}
