package Server.Model.Building.Storage;

import Client.Model.Building.Storage.StorageType;
import Client.Model.Game;
import Client.Model.Resources.Resource;
import Client.Model.Resources.ResourceType;
import Client.Model.User;
import Client.Model.Building.Building;

import java.util.ArrayList;

public class Storage extends Building {
    private ArrayList<Resource> storage = new ArrayList<>();
    private int maxCapacity;

    public Storage(String name, int hp, int x, int y, int workers, ArrayList<Resource> price, ArrayList<Resource> storage, int maxCapacity, User owner) {
        super(name, hp, x, y, workers, price, owner);
        this.storage = storage;
        this.maxCapacity = maxCapacity;
    }

    public static Building createBuilding(String name, int x, int y, User owner) {
        StorageType building = StorageType.getBuildingByName(name);
        if (building == null) return null;
        int hp = building.hp;
        int workers = building.workers;
        ArrayList<Resource> price = building.price;
        ArrayList<Resource> storageBuffer = building.storage;
        ArrayList<Resource> storage = new ArrayList<>();
        for (Resource tempResource : storageBuffer) {
            Resource resource = new Resource(tempResource.getResourceType(), tempResource.getCount());
            storage.add(resource);
        }
        int maxCapacity = building.maxCapacity;
        return new Storage(name, hp, x, y, workers, price, storage, maxCapacity, owner);
    }

    public ArrayList<Resource> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<Resource> storage) {
        this.storage = storage;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Resource getStoredResourceByType(ResourceType resourceType) {
        for (Resource resource : storage) {
            if (resourceType.equals(resource.getResourceType())) {
                return resource;
            }
        }
        return null;
    }

    public void addToStorage(Resource resource) {
        Resource storedResource = getStoredResourceByType(resource.getResourceType());
        Resource governmentResource = Game.getGovernmentByUser(this.getOwner()).getResourceByType(resource.getResourceType());
        storedResource.addCount(resource.getCount());
        governmentResource.addCount(resource.getCount());
        if (storedResource.getCount() > maxCapacity) {
            storedResource.setCount(maxCapacity);
        }
    }

    public boolean removeFromStorage(Resource resource) {
        Resource storedResource = getStoredResourceByType(resource.getResourceType());
        Resource governmentResource = Game.getGovernmentByUser(this.getOwner()).getResourceByType(resource.getResourceType());
        if (storedResource.getCount() == 0) return false;
        storedResource.addCount(-resource.getCount());
        governmentResource.addCount(-resource.getCount());
        return true;
    }
}
