package controller;

import android.content.Context;

import model.DBTemplate;
import model.userInfDB;
import object.userObject;

/**
 * Created by Nguyen Van Tinh on 11/09/2016.
 */
public class userController {
    private userInfDB userDB;
    private userObject user;
    private Context context;

    public userController(userObject user, Context context){
        this.context = context;
        this.user = user;
        userDB = new userInfDB(context, user);
    }

    public boolean changePassword(String oldPassword, String newPassword){
        boolean success = false;
        //this function will require old password to confirm with current user
        if (this.user.getUserPassword().equals(oldPassword)){
            this.user.setUserPassword(newPassword);
            userDB.updateUserInf(this.user);
            success = true;
        }
        return success;
    }

    public boolean changeUserName(String password, String newName){
        boolean success = false;
        //this function will require old password to confirm with current user
        if (this.user.getUserPassword().equals(password)){
            this.user.setName(newName);
            userDB.updateUserInf(this.user);
            success = true;
        }
        return success;
    }

    public boolean addNewUser(userObject user){
        boolean success = checkValidUser(user);
        //check valid data of user
        if (success) {
            userDB.insertUserInf(user);
        }
        return success;
    }

    public boolean checkValidUser(userObject user){
        boolean valid = true;
        //id is not null and is not exist in database
        if (user.getUserId().equals("") || userDB.searchById(user.getUserId()).size() != 0){
            return false;
        }

        //check length of name is not greater than 16
        if (user.getName().length() > 32){
            return false;
        }

        //check birthday is valid format dd/mm/yy
        try{
            String[] date = new String[3];
            if (user.getBirthday().contains("/")){
                user.getBirthday().split("/");
            }else {
                user.getBirthday().split("-");
            }
        }catch (Exception ex){
            return false;
        }

        return valid;
    }
}
