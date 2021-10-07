package com.company;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int money;
    private final ArrayList<Animal> animals;
    private final ArrayList<Food> foods;
    public Player(String newName){
        name = newName;
        money = 100;
        animals = new ArrayList<Animal>();
        foods = new ArrayList<Food>();
    }

    public int getMoney(){
        return money;
    }

    public String getName() {
        return name;
    }

    public void removeAllAnimals(){
        this.animals.clear();
    }

    public void removeMoney(int moneyToGive){
        this.money -= moneyToGive;
    }

    public void addMoney(int moneyToGain) {
        this.money += moneyToGain;
    }

    public void removeAnimal(int idx){
        this.animals.remove(idx);
    }

    public void removeFood(Food food) { this.foods.remove(food);}

    public void addFood(Food foodToAdd){
        this.foods.add(foodToAdd);
    }

    public void addAnimal(Animal animalToAdd){
        this.animals.add(animalToAdd);
    }

    public void removeDeadAnimals() {
        this.animals.removeIf(animal -> animal.getHealth() <= 0);
    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }
    public ArrayList<Food> getFoods(){
        return this.foods;
    }

    public void displayInfo(){
        System.out.println("Name: " + this.name);
        System.out.println("Money: " + this.money);
        System.out.println("==== Animals ====");
        animals.forEach(System.out::println);
        System.out.println("==== Foods ====");
        foods.forEach(System.out::println);
    }
}
