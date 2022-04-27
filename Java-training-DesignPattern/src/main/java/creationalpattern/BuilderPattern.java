package creationalpattern;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 设计模式是一种模式，而不是一种固定的实现方式，就像此例子 [建造者模式]里的实现，可以设计更多的层级，但核心思想是保持一致的
 *
 * [建造者模式]
 * ①. 产品对象有复杂的内部结构，产品对象通常包含多个成员属性： 一个套餐包括【主食】【小食】【饮料】
 * ②. 需要生成的产品对象的属性相互依赖，需要指定其生成顺序：
 *          这里没有涉及，可以参考 [鸡块] 和 [鸡块蘸酱]，[薯条] 和 [番茄酱];需要先生成鸡块，然后生成蘸酱，可以取名[鸡块组合]
 * ③. 建造者模式中引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类中： 通过 Builder来实际创建对象，建造者类只需要引用。
 * ④. 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品：不管你创建什么套餐，你都可以简化到两步 --- [选择饮料][选择食物]
 *
 * 使用示例：
 *          在很多游戏软件中，地图包括天空、地面、背景等组成部分，人物角色包括人体、服装、装备等组成部分；
 *          可以使用 [建造者模式]对其进行设计，通过不同的具体建造者创建不同类型的地图或人物。
 *
 * 理解增强：如果将 [抽象工厂模式]看成【汽车配件生产工厂】生产一个产品族的产品，那么 [建造者模式]就是一个【汽车组装工厂】，通过对部件的组装可以返回一辆完整的汽车
 *
 */
public class BuilderPattern {
    static MealBuilder mealBuilder;

    public static void setMealBuilder(MealBuilder mealBuilder) {
        BuilderPattern.mealBuilder = mealBuilder;
    }

    public static void construct(){
        mealBuilder.buildDrink();
        mealBuilder.buildFood();
    }

    public static void main(String[] args) {
        setMealBuilder(new ChickenSet());
        construct();
        Meal meal_chicken = mealBuilder.getMeal();
        System.out.println(meal_chicken.toString());

        setMealBuilder(new BeefSet());
        construct();
        Meal meal_beef = mealBuilder.getMeal();
        System.out.println(meal_beef.toString());
    }
}


class Meal{
    private String drink;
    private ArrayList<String> foodList;

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public ArrayList<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<String> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "drink='" + drink + '\'' +
                ", foodList=" + foodList +
                '}';
    }
}

interface MealBuilder{
    void buildDrink();
    void buildFood();
    Meal getMeal();
}
class ChickenSet implements MealBuilder{
    Cole cole;
    Chips chips;
    Burger burger;

    public void buildDrink() {
        cole = new SmallCole();
    }

    public void buildFood() {
        chips = new SmallChips();
        burger = new ChickenBurger();
    }

    public Meal getMeal() {
        Meal meal = new Meal();
        meal.setDrink(cole.toString());
        meal.setFoodList(new ArrayList<String>(Arrays.asList(chips.toString(),burger.toString())));
        return meal;
    }
}
class BeefSet implements MealBuilder{
    Cole cole;
    Chips chips;
    Burger burger;

    public void buildDrink() {
        cole = new BigCole();
    }

    public void buildFood() {
        chips = new BigChips();
        burger = new BeefBurger();
    }
    public Meal getMeal() {
        Meal meal = new Meal();
        meal.setDrink(cole.toString());
        meal.setFoodList(new ArrayList<String>(Arrays.asList(chips.toString(),burger.toString())));
        return meal;
    }
}

// 可乐
interface Cole {
}

class SmallCole implements Cole {

    @Override
    public String toString() {
        return "一杯小可乐";
    }
}

class MiddleCole implements Cole {

    @Override
    public String toString() {
        return "一杯中可乐";
    }
}

class BigCole implements Cole {

    @Override
    public String toString() {
        return "一杯大可乐";
    }
}

// 薯条
interface Chips {
}

class SmallChips implements Chips {
    @Override
    public String toString() {
        return "一份小薯条";
    }
}

class BigChips implements Chips {
    @Override
    public String toString() {
        return "一份大薯条";
    }
}

// 汉堡
interface Burger {
}
class ChickenBurger implements Burger{
    @Override
    public String toString() {
        return "一份鸡堡";
    }
}
class BeefBurger implements Burger{
    @Override
    public String toString() {
        return "一份牛堡";
    }
}
