package common.function;

import controller.userController;
import object.userObject;

/**
 * Created by hongnhan on 17/9/2016.
 */
public class loginUser {

    public static boolean  loginUser(String User,String Pass){
        //if()
        userObject userObject = null;
        userController userController=null;

        userObject.setName(User);
        userObject.setUserPassword(Pass);

        if(!userController.checkValidUser(userObject)) {
            return false;
        }
        return true;
    }
}
