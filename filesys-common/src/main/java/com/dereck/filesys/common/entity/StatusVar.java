package com.dereck.filesys.common.entity;


public class StatusVar {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static boolean hasUser(){return tl.get() != null;}

    public static void removeUser(){
        tl.remove();
    }

}
