package se.kth.csc.iprog.dinnerplanner.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

public class DinnerModel extends Observable implements IDinnerModel {

  private int nrOfGuests;
  Set<Dish> dishes = new HashSet<Dish>();
  Set<Dish> selectedDishes = new HashSet<Dish>();
  String description;
  String ingredientAmount;
  String descriptionTitle;

  public DinnerModel() {
    description = "";
    createDishes();
  }

  /**
   * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
   */
  public Set<Dish> getDishes() {
    return dishes;
  }

  /**
   * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
   */
  public Set<Dish> getDishesOfType(int type) {
    Set<Dish> result = new HashSet<Dish>();
    for (Dish d : dishes) {
      if (d.getType() == type) {
        result.add(d);
      }
    }
    return result;
  }

  /**
   * Returns the set of dishes of specific type, that contain filter in their name
   * or name of any ingredient.
   */
  public Set<Dish> filterDishesOfType(int type, String filter) {
    Set<Dish> result = new HashSet<Dish>();
    for (Dish d : dishes) {
      if (d.getType() == type && d.contains(filter)) {
        result.add(d);
      }
    }
    return result;
  }

  @Override
  public int getNumberOfGuests() {
    return nrOfGuests;
  }

  @Override
  public void setNumberOfGuests(int numberOfGuests) {
    this.nrOfGuests = numberOfGuests < 0 ? Math.abs(numberOfGuests) : numberOfGuests;
    notifyView("Number of Guests Changed");
  }

  @Override
  public Dish getSelectedDish(int type) {
    for (Dish d : selectedDishes) {
      if (d.getType() == type) return d;
    }
    return null;
  }

  @Override
  public Set<Dish> getFullMenu() {
    return selectedDishes;
  }

  @Override
  public Set<Ingredient> getAllIngredients() {
    Set<Ingredient> allIngredients = new HashSet<Ingredient>();
    for (Dish d : selectedDishes) {
      for (Ingredient i : d.getIngredients()) {
        allIngredients.add(i);
      }
    }
    return allIngredients;
  }

  @Override
  public double getTotalMenuPrice() {
    double totalPrice = 0;
    Set<Ingredient> allIngredients = getAllIngredients();
    for (Ingredient i : allIngredients) {
      totalPrice += i.getPrice();
    }
    return nrOfGuests * totalPrice;
  }

  @Override
  public void addDishToMenu(Dish dish) {
    Dish d = null;
    boolean containsDishType = false;
    for (Iterator<Dish> it = selectedDishes.iterator(); it.hasNext(); ) {
      d = it.next();
      if (d.getType() == dish.getType()) {
        containsDishType = true;
        break;
        }
    }
    if(containsDishType) selectedDishes.remove(d);
    selectedDishes.add(dish);
    notifyView("Dish Added To Menu");
  }

  @Override
  public void removeDishFromMenu(Dish dish) {
    for (Dish d : selectedDishes) {
      if (d == dish) selectedDishes.remove(d);
    }
  }

  public void setIngredientAmount(String s) {
    this.ingredientAmount = s;
    notifyView("Ingredient Amount Changed");
  }

  public String getIngredientAmount() {
    return this.ingredientAmount;
  }

  public void setDescription(String s) {
    this.description = s;
    notifyView("Description Changed");
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescriptionTitle(String s) {
    this.descriptionTitle = s;
    notifyView("Description Title Changed");
  }

  public String getDescriptionTitle() {
    return this.descriptionTitle;
  }

  private void notifyView(String hasChanged) {
    setChanged();
    notifyObservers(hasChanged);
  }

  /**
   * Creates all the dishes in the menu
   */
  private void createDishes() {
    //Adding some example data, you can add more
    Dish dish1 = new Dish("French toast", Dish.STARTER, "toast.jpg", "In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
    Ingredient dish1ing1 = new Ingredient("eggs", 0.5, "", 1);
    Ingredient dish1ing2 = new Ingredient("milk", 30, "ml", 6);
    Ingredient dish1ing3 = new Ingredient("brown sugar", 7, "g", 1);
    Ingredient dish1ing4 = new Ingredient("ground nutmeg", 0.5, "g", 12);
    Ingredient dish1ing5 = new Ingredient("white bread", 2, "slices", 2);
    dish1.addIngredient(dish1ing1);
    dish1.addIngredient(dish1ing2);
    dish1.addIngredient(dish1ing3);
    dish1.addIngredient(dish1ing4);
    dish1.addIngredient(dish1ing5);
    dishes.add(dish1);


    Dish dish2 = new Dish("Meat balls", Dish.MAIN, "meatballs.jpg", "Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
    Ingredient dish2ing1 = new Ingredient("extra lean ground beef", 115, "g", 20);
    Ingredient dish2ing2 = new Ingredient("sea salt", 0.7, "g", 3);
    Ingredient dish2ing3 = new Ingredient("small onion, diced", 0.25, "", 2);
    Ingredient dish2ing4 = new Ingredient("garlic salt", 0.6, "g", 3);
    Ingredient dish2ing5 = new Ingredient("Italian seasoning", 0.3, "g", 3);
    Ingredient dish2ing6 = new Ingredient("dried oregano", 0.3, "g", 3);
    Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes", 0.6, "g", 3);
    Ingredient dish2ing8 = new Ingredient("Worcestershire sauce", 16, "ml", 7);
    Ingredient dish2ing9 = new Ingredient("milk", 20, "ml", 4);
    Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese", 5, "g", 8);
    Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs", 115, "g", 4);
    dish2.addIngredient(dish2ing1);
    dish2.addIngredient(dish2ing2);
    dish2.addIngredient(dish2ing3);
    dish2.addIngredient(dish2ing4);
    dish2.addIngredient(dish2ing5);
    dish2.addIngredient(dish2ing6);
    dish2.addIngredient(dish2ing7);
    dish2.addIngredient(dish2ing8);
    dish2.addIngredient(dish2ing9);
    dish2.addIngredient(dish2ing10);
    dish2.addIngredient(dish2ing11);
    dishes.add(dish2);

    Dish dish3 = new Dish("Fish cake", Dish.STARTER, "fishcake.jpg", "In a large mixing bowl, beat the eggs. Add the fish, mix well. Make small cakes of the fish mixture and roll them in breadcrumbs. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides. Serve with a slice of tomato, lemon and some spinach");
    Ingredient dish3ing1 = new Ingredient("eggs", 1, "", 2);
    Ingredient dish3ing2 = new Ingredient("fish", 300, "g", 60);
    Ingredient dish3ing3 = new Ingredient("bread crumbs", 100, "g", 5);
    Ingredient dish3ing4 = new Ingredient("tomato", 1, "", 6);
    Ingredient dish3ing5 = new Ingredient("lemon", 1, "", 6);
    Ingredient dish3ing6 = new Ingredient("spinach", 50, "g", 4);
    dish3.addIngredient(dish3ing1);
    dish3.addIngredient(dish3ing2);
    dish3.addIngredient(dish3ing3);
    dish3.addIngredient(dish3ing4);
    dish3.addIngredient(dish3ing5);
    dish3.addIngredient(dish3ing6);
    dishes.add(dish3);

    Dish dish4 = new Dish("Ice cream", Dish.DESERT, "icecream.jpg", "Grab a spoon and scoop some vanila ice cream in a nice glass. Stick a cinnamon stick in it for estetic pleasure.");
    Ingredient dish4ing1 = new Ingredient("vanila ice cream", 1, "L", 20);
    Ingredient dish4ing2 = new Ingredient("cinnamon stick", 4, "", 15);
    dish4.addIngredient(dish4ing1);
    dish4.addIngredient(dish4ing2);
    dishes.add(dish4);

    Dish dish5 = new Dish("Bread á la carte", Dish.STARTER, "breadtomato.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish5ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish5ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish5ing3 = new Ingredient("tomato", 2, "", 12);
    dish5.addIngredient(dish5ing1);
    dish5.addIngredient(dish5ing2);
    dish5.addIngredient(dish5ing3);
    dishes.add(dish5);

    Dish dish6 = new Dish("Pasties", Dish.STARTER, "pasties.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish6ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish6ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish6ing3 = new Ingredient("tomato", 2, "", 12);
    dish6.addIngredient(dish6ing1);
    dish6.addIngredient(dish6ing2);
    dish6.addIngredient(dish6ing3);
    dishes.add(dish6);

    Dish dish7 = new Dish("Mushroom stew", Dish.STARTER, "mushroomstarter.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish7ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish7ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish7ing3 = new Ingredient("tomato", 2, "", 12);
    dish7.addIngredient(dish7ing1);
    dish7.addIngredient(dish7ing2);
    dish7.addIngredient(dish7ing3);
    dishes.add(dish7);

    Dish dish8 = new Dish("Fried shrimp", Dish.MAIN, "friedshrimp.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish8ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish8ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish8ing3 = new Ingredient("tomato", 2, "", 12);
    dish8.addIngredient(dish8ing1);
    dish8.addIngredient(dish8ing2);
    dish8.addIngredient(dish8ing3);
    dishes.add(dish8);

    Dish dish9 = new Dish("Roasted chicken", Dish.MAIN, "roastedchicken.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish9ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish9ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish9ing3 = new Ingredient("tomato", 2, "", 12);
    dish9.addIngredient(dish9ing1);
    dish9.addIngredient(dish9ing2);
    dish9.addIngredient(dish9ing3);
    dishes.add(dish9);

    Dish dish10 = new Dish("Stew", Dish.MAIN, "stew.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish10ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish10ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish10ing3 = new Ingredient("tomato", 2, "", 12);
    dish10.addIngredient(dish10ing1);
    dish10.addIngredient(dish10ing2);
    dish10.addIngredient(dish10ing3);
    dishes.add(dish10);

    Dish dish11 = new Dish("Broccoli pie", Dish.MAIN, "broccolipie.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish11ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish11ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish11ing3 = new Ingredient("tomato", 2, "", 12);
    dish11.addIngredient(dish11ing1);
    dish11.addIngredient(dish11ing2);
    dish11.addIngredient(dish11ing3);
    dishes.add(dish11);

    Dish dish12 = new Dish("Chocolate cake", Dish.DESERT, "chocolatecake.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish12ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish12ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish12ing3 = new Ingredient("tomato", 2, "", 12);
    dish12.addIngredient(dish12ing1);
    dish12.addIngredient(dish12ing2);
    dish12.addIngredient(dish12ing3);
    dishes.add(dish12);

    Dish dish13 = new Dish("Red cake", Dish.DESERT, "redcake.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish13ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish13ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish13ing3 = new Ingredient("tomato", 2, "", 12);
    dish13.addIngredient(dish13ing1);
    dish13.addIngredient(dish13ing2);
    dish13.addIngredient(dish13ing3);
    dishes.add(dish13);

    Dish dish14 = new Dish("Fruit sticks", Dish.DESERT, "fruitsticks.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish14ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish14ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish14ing3 = new Ingredient("tomato", 2, "", 12);
    dish14.addIngredient(dish14ing1);
    dish14.addIngredient(dish14ing2);
    dish14.addIngredient(dish14ing3);
    dishes.add(dish14);

    Dish dish15 = new Dish("Caramel apples", Dish.DESERT, "caramelapples.jpg", "Slice some nice bread and spread butter on one side. Place a slice of ham on top of it and two slices of tomato as well.");
    Ingredient dish15ing1 = new Ingredient("loaf of bread", 1, "", 15);
    Ingredient dish15ing2 = new Ingredient("tam", 4, "slices", 20);
    Ingredient dish15ing3 = new Ingredient("tomato", 2, "", 12);
    dish15.addIngredient(dish15ing1);
    dish15.addIngredient(dish15ing2);
    dish15.addIngredient(dish15ing3);
    dishes.add(dish15);
  }
}
