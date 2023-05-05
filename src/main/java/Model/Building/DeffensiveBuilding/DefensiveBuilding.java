package Model.Building.DeffensiveBuilding;

import Model.Building.Building;
import Model.Resources.Resource;
import Model.User;

import java.util.ArrayList;

public class DefensiveBuilding extends Building {
    private int range;
    private int damage;

    public DefensiveBuilding(String name, int hp, int x, int y, int workers, ArrayList<Resource> price, int range, int damage, User owner) {
        super(name, hp, x, y, workers, price, owner);
        this.range = range;
        this.damage = damage;
    }

    public static Building createBuilding(String name, int x, int y, User owner) {
        DefensiveBuildingType building = DefensiveBuildingType.getBuildingByName(name);
        if (building == null) return null;
        int hp = building.hp;
        int workers = building.workers;
        ArrayList<Resource> price = building.price;
        int range = building.range;
        int damage = building.damage;
        return new DefensiveBuilding(name, hp, x, y, workers, price, range, damage, owner);
    }

    private void doWork() {
        //TODO write a function in which the building scans the area and damages any troop in range (square range)
    }
}
