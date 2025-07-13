package com.sisol.sys_citas.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashUtils {


    //hashear la contraseña ingresada
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt() );
    }


    // verificar si el password ingresado es correcto 
    public static boolean checkPassword(String plainPassword, String hashed){

        return BCrypt.checkpw(plainPassword, hashed);
    }
    
}
