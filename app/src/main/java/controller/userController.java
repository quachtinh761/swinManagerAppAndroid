package controller;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        //id is not null and is not exist in database
        if (user.getUserId().equals("") || userDB.searchById(user.getUserId()).size() > 0){
            return false;
        }

        //check length of name is not greater than 16
        if (user.getName().length() > 32){
            return false;
        }

        //check birthday is valid format dd/mm/yy or dd-mm-yy and valid value
        try{
            String formatDate = "dd-mm-yy";
            String birth = user.getBirthday();
            //format into dd-mm-yy
            if (birth.contains("/")){
                birth = birth.replace("/", "-");
            }
            //if this function succeed, the date input is correct. Otherwise, return false
            DateFormat df = new SimpleDateFormat(formatDate);
            df.setLenient(false);
            df.parse(birth);
        }catch (Exception ex){
            return false;
        }

        //check password length is not smaller than 8
        if (user.getUserPassword().length() < 8){
            return false;
        }

        //check right

        return true;
    }
}
