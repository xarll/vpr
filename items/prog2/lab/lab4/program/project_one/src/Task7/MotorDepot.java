package Task7;

import Task1.Car;

public class MotorDepot {
    private int storageSize;
    private enum State{InDepot, InFlight, OnRepair};
    public Car[] storage;
    State[] stateStorage;

    public MotorDepot(int storageSize){
        this.storageSize = storageSize;
        storage = new Car[storageSize];
        stateStorage = new State[storageSize];
    }

    public boolean add(Car car){
        boolean marker = false;
        for(int i = 0;i<storageSize;i++){
            if(car.equals(storage[i])){
                return marker;
            }
        }
        for(int i = 0;i<storageSize;i++) {
            if (storage[i] == null) {
                storage[i] = car;
                stateStorage[i] = State.InDepot;
                marker = true;
                break;
            }
        }
        return marker;
    }

    public boolean del(Car car){
        boolean marker = false;
        for(int i = 0;i<storageSize;i++){
            if(car.equals(storage[i])){
                storage[i] = null;
                stateStorage[i] = null;
                marker = true;
                break;
            }
        }
        return marker;
    }

    public boolean goToFlight(Car car){
        boolean marker = false;
        for(int i = 0;i<storageSize;i++){
            if(car.equals(storage[i])&&stateStorage[i]==State.InDepot){
                stateStorage[i] = State.InFlight;
                marker = true;
                break;
            }
        }
        return marker;
    }

    public boolean goToRepair(Car car){
        boolean marker = false;
        for(int i = 0;i<storageSize;i++){
            if(car.equals(storage[i])&&stateStorage[i]==State.InDepot){
                stateStorage[i] = State.OnRepair;
                marker = true;
                break;
            }
        }
        return marker;
    }

    public boolean goToDepot(Car car){
        boolean marker = false;
        for(int i = 0;i<storageSize;i++){
            if(car.equals(storage[i])&&stateStorage[i]!=State.InDepot){
                stateStorage[i] = State.InDepot;
                marker = true;
                break;
            }
        }
        return marker;
    }

    public void showCarsInDepot(){
        System.out.println("Cars in Depot: ");
        for(int i = 0;i<storageSize;i++){
            if(stateStorage[i]==State.InDepot){
                System.out.println(storage[i]);
            }
        }
    }
    public void showCarsInFlight(){
        System.out.println("Cars in Flight: ");
        for(int i = 0;i<storageSize;i++){
            if(stateStorage[i]==State.InFlight){
                System.out.println(storage[i]);
            }
        }
    }
    public void showCarsIsBroken(){
        System.out.println("Cars is Broken: ");
        for(int i = 0;i<storageSize;i++){
            if(stateStorage[i]==State.OnRepair){
                System.out.println(storage[i]);
            }
        }
    }
    public void show(){
        System.out.println("Cars: ");
        for(int i = 0;i<storageSize;i++){
                System.out.println(storage[i]);
        }
    }
}
