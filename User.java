package me.zkx.IMAtDemo;

import java.io.Serializable;

/**
 * Created by zkx on 2017/1/6.
 */
public class User implements Serializable{
    public String id;
    public String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
