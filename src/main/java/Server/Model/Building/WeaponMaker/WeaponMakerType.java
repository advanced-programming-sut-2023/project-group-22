package Server.Model.Building.WeaponMaker;

import Client.Model.Resources.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public enum WeaponMakerType {
    ARMOURER("armourer", 100, 1, Resource.getResources("wood", "20", "gold", "100"), Resource.getWeapons("metal armour", "1", "iron", "1")),
    TANNER("tanner", 100, 1, Resource.getResources("wood", "20", "gold", "100"), Resource.getWeapons("leather armor", "3", "cow", "1")),
    FLETCHER("fletcher", 100, 1, Resource.getResources("wood", "20", "gold", "100"), Resource.getWeapons("bow", "1", "wood", "2", "crossbow", "1", "wood", "3")),
    POLETURNER("poleturner", 100, 1, Resource.getResources("wood", "10", "gold", "100"), Resource.getWeapons("spear", "1", "wood", "1", "pike", "1", "wood", "2")),
    BLACKSMITH("blacksmith", 100, 1, Resource.getResources("wood", "20", "gold", "100"), Resource.getWeapons("mace", "1", "iron", "1", "sword", "1", "iron", "1"));

    String name;
    int hp;
    int workers;
    ArrayList<Resource> price = new ArrayList<>();
    HashMap<Resource, Resource> weapons = new HashMap<>();

    WeaponMakerType(String name, int hp, int workers, ArrayList<Resource> price, HashMap<Resource, Resource> weapons) {
        this.name = name;
        this.hp = hp;
        this.workers = workers;
        this.price = price;
        this.weapons = weapons;
    }

    public static WeaponMakerType getBuildingByName(String name) {
        for (WeaponMakerType building : WeaponMakerType.values()) {
            if (name.equalsIgnoreCase(building.name))
                return building;
        }
        return null;
    }
}
