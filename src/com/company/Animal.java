package com.company;

abstract class Animal {
  protected String name;
  protected String sex;
  protected String type;
  protected int health;
  protected int price;


  public Animal(String name, String sex, int health, int price){
    this.name = name;
    this.sex = sex;
    this.type = this.getClass().getSimpleName();
    this.health = health;
    this.price = price;
  }
  public String toString() {
    return "Name: " + this.name + " - Sex: " + this.sex + " - Health: " + this.health + " - Type: " + this.type + " - Price: " + this.price + "";
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public int getPrice() {
    return price;
  }

  public boolean isAlive(){
    return this.health > 0;
  }

  public void eatFood(Food food) {
    this.health += food.getHealthBonus();
  }

  public void randomLowerHealth(){
    this.health -= (int)(Math.random() * 30) + 10;
  }
}
