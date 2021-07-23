package me.isaiah.common;

import java.util.HashMap;

public class Gamemode {

    public static Gamemode SURVIVAL  = new Gamemode(0);
    public static Gamemode CREATIVE  = new Gamemode(1);
    public static Gamemode ADVENTURE = new Gamemode(2);
    public static Gamemode SPECTATOR = new Gamemode(3);

    private static HashMap<Integer, Gamemode> byId = new HashMap<>();

    private int id;

    public Gamemode(int id) {
        this.id = id;
        byId.put(id, this);
    }

    public int getId() {
        return id;
    }

    public static Gamemode getById(int id) {
        return byId.get(id);
    }

}