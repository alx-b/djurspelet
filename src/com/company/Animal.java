package com.company;

abstract class Animal implements Cloneable {
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

  @Override
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
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

  public String getType() {
    return type;
  }

  public String getSex() {
    return sex;
  }

  public boolean isAlive(){
    return this.health > 0;
  }

  public void eatFood(Food food) {
    this.health += food.getHealthBonus();
    System.out.println(getName() + " health is now: " + getHealth());
  }

  public void randomLowerHealth(){
    this.health -= (int)(Math.random() * 30) + 10;
  }
}
