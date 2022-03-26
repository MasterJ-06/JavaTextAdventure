import java.util.Random;
import java.util.Scanner;

/**
 * @author Jonathan Miller
 * @version 1.0
 */

//command to generate javadoc: javadoc -d doc src\* -author -version -html5 --main-stylesheet .\dracula-javadoc8.css
public class TextAdventure {
    static int hp = 100;
    static int enemy_hp = 100;
    static int armour = 0;
    static int attack = 0;
    static int res = 0;
    static boolean isEnemyAlive = false;
    static String thing;
    static String choice;
    static String place;
    static String breakable = "";
    static String[] items_list = { "sword", "spear", "potion", "book", "apple", "water", "scroll", "chestplate", "helmet", "gem" };
    static String keep_items = "";
    static String forge_items = "";
    static String start_items = "";
    static String courtyard_items = "";
    static String kitchen_items = "";
    static String fountain_items = "";
    static String armoury_items = "";
    static String hallway_items = "";
    static String storeroom_items = "";
    static String library_items = "";
    static String alchemy_items = "";
    static String inv_items = "";
    static String list = "";
    static Random rand = new Random();
    static Scanner read = new Scanner(System.in);

    /**
     * This method puts all the items into the specified rooms.
     * @since 1.0
     */
    public static void setup() {
        start_items = "";
        forge_items = "&chestplate&&sword&";
        keep_items = "&gem&";
        courtyard_items = "";
        kitchen_items = "&apple&";
        fountain_items = "&water%";
        armoury_items = "&helmet&";
        hallway_items = "";
        storeroom_items = "&scroll&";
        library_items = "&book&";
        alchemy_items = "&potion&";
        inv_items = "&spear&";
        breakable = "&potion&&apple&&water&";
    }

    /**
     * This method runs forever to check if the player has died.
     * @throws InterruptedException Because the thread sleeps every three seconds.
     * @since 1.0
     */
    public static void dead() throws InterruptedException {
        for (;;) {
            if (hp == 0) {
                System.out.println("You died. Game Over.");
                Thread.sleep(3000);
                System.exit(1);
            }
        }
    }

    /**
     * This method checks to see if an item exists in the game.
     * @param item The item to check.
     * @return True is the item exists, otherwise false.
     * @since 1.0
     */
    public static boolean item_exists(String item) {
        int nub = items_list.length;
        item = "&" + item + "&";
        String items = "";
        for (int i = 0; i < nub; i++) {
            items = items + "&" + items_list[i] + "&";
        }
        if (items.contains(item)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method lists items in a room or the users inventory
     * @param thing The room name or inventory
     * @return String of items
     * @since 1.0
     */
    public static String list(String thing) {
        String listing;
        if (thing.contentEquals("inventory")) {
            list = inv_items.replace('&', ' ');
            System.out.println("Inventory items:");
            listing = list;
        } else if (thing.contentEquals("room")) {
            if (place.contentEquals("forge")) {
                list = forge_items;
            } else if (place.contentEquals("keep")) {
                list = keep_items;
            } else if (place.contentEquals("start")) {
                list = start_items;
            } else if (place.contentEquals("courtyard")) {
                list = courtyard_items;
            } else if (place.contentEquals("kitchen")) {
                list = kitchen_items;
            } else if (place.contentEquals("fountain")) {
                list = fountain_items;
            } else if (place.contentEquals("armoury")) {
                list = armoury_items;
            } else if (place.contentEquals("hallway")) {
                list = hallway_items;
            } else if (place.contentEquals("storeroom")) {
                list = storeroom_items;
            } else if (place.contentEquals("library")) {
                list = library_items;
            } else if (place.contentEquals("alchemy")) {
                list = alchemy_items;
            }
            list = list.replace('&', ' ');
            System.out.println(place + " items:");
            listing = list;
        }
        else {
            listing = "I only know list inventory and list room.";
        }
        return listing;
    }

    /**
     * This method checks if an item is in a specific room.
     * @param list The String of room items.
     * @param item The item to check.
     * @return True if the item is in the indicated room otherwise false.
     * @since 1.0
     */
    public static boolean itemInRoom(String list, String item) {
        if (list.contains(item)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if an item is breakable.
     * @param item The item to check.
     * @return True if the item is breakable, otherwise false.
     * @since 1.0
     */
    public static boolean isBreakable(String item) {
        if (breakable.contains(item)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if the user's input is a valid command.
     * @param command The command to validate.
     * @return True if the command is valid otherwise false.
     * @since 1.0
     */
    public static boolean validCommand(String command) {
        if (command.contentEquals("use")) {
            return true;
        } else if (command.contentEquals("pickup")) {
            return true;
        } else if (command.contentEquals("drop")) {
            return true;
        } else if (command.contentEquals("health")) {
            return true;
        } else if (command.contentEquals("go")) {
            return true;
        } else if (command.contentEquals("exit")) {
            return true;
        } else if (command.contentEquals("list")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes an item from the room String.
     * @param mainString The room String.
     * @param toRemove The item to remove.
     * @since 1.0
     */
    public static void removeItem(String mainString, String toRemove) {
        String new_erase;
        new_erase = "&" + toRemove + "&";
        // Search for the substring in string
        boolean pos = mainString.contains(new_erase);
        if (pos) {
            // If found then erase it from string
            if (choice.contentEquals("pickup")) {
                if (place.contentEquals("start")) {
                    start_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("forge")) {
                    forge_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("keep")) {
                    keep_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("courtyard")) {
                    courtyard_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("kitchen")) {
                    kitchen_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("fountain")) {
                    fountain_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("armoury")) {
                    armoury_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("hallway")) {
                    hallway_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("storeroom")) {
                    storeroom_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("library")) {
                    library_items = mainString.replace(new_erase, "");
                } else if (place.contentEquals("alchemy")) {
                    alchemy_items = mainString.replace(new_erase, "");
                }
            } else if (choice.contentEquals("drop")) {
                inv_items = mainString.replace(new_erase, "");
            } else if (choice.contentEquals("use")) {
                inv_items = mainString.replace(new_erase, "");
            }
        }
    }

    /**
     * This method adds an item to a room String.
     * @param room The room to add the items to.
     * @param item The item to add.
     * @since 1.0
     */
    public static void addItem(String room, String item) {
        if (room.contentEquals("inv")) {
            inv_items = inv_items + "&" + item + "&";
        } else if (room.contentEquals("forge")) {
            forge_items = forge_items + "&" + item + "&";
        } else if (room.contentEquals("keep")) {
            keep_items = keep_items + "&" + item + "&";
        } else if (room.contentEquals("start")) {
            start_items = start_items + "&" + item + "&";
        } else if (room.contentEquals("courtyard")) {
            courtyard_items = courtyard_items + "&" + item + "&";
        } else if (room.contentEquals("kitchen")) {
            kitchen_items = kitchen_items + "&" + item + "&";
        } else if (room.contentEquals("fountain")) {
            fountain_items = fountain_items + "&" + item + "&";
        } else if (room.contentEquals("armoury")) {
            armoury_items = armoury_items + "&" + item + "&";
        } else if (room.contentEquals("hallway")) {
            hallway_items = hallway_items + "&" + item + "&";
        } else if (room.contentEquals("storeroom")) {
            storeroom_items = storeroom_items + "&" + item + "&";
        } else if (room.contentEquals("library")) {
            library_items = library_items + "&" + item + "&";
        } else if (room.contentEquals("alchemy")) {
            alchemy_items = alchemy_items + "&" + item + "&";
        }
    }

    /**
     * This method handles all the actions that a user can make in the game. These actions are pickup, drop, and use.
     * @param action The action to take.
     * @param item The item to do actions on.
     * @throws InterruptedException Because the thread sleeps for three seconds.
     * @since 1.0
     */
    public static void itemActions(String action, String item) throws InterruptedException {
        if (action.contentEquals("pickup")) {
            if (place.contentEquals("forge")) {
                if (itemInRoom(forge_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(forge_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("keep")) {
                if (itemInRoom(keep_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(keep_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("start")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("courtyard")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("kitchen")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("fountain")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("armoury")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("hallway")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("storeroom")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("library")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            } else if (place.contentEquals("alchemy")) {
                if (itemInRoom(start_items, item)) {
                    //add the item to inventory
                    addItem("inv", item);
                    removeItem(start_items, item);
                    res = 1;
                } else {
                    System.out.println("There is no " + item + " in this room.");
                }
            }
        } else if (action.contentEquals("drop")) {
            if (itemInRoom(inv_items, item)) {
                //remove item from inventory
                removeItem(inv_items, item);
                //add the item to the room
                if (place.contentEquals("forge")) {
                    addItem("forge", item);
                    res = 1;
                } else if (place.contentEquals("keep")) {
                    addItem("keep", item);
                    res = 1;
                } else if (place.contentEquals("start")) {
                    addItem("start", item);
                    res = 1;
                } else if (place.contentEquals("courtyard")) {
                    addItem("courtyard", item);
                    res = 1;
                } else if (place.contentEquals("kitchen")) {
                    addItem("kitchen", item);
                    res = 1;
                } else if (place.contentEquals("fountain")) {
                    addItem("fountain", item);
                    res = 1;
                } else if (place.contentEquals("armoury")) {
                    addItem("armoury", item);
                    res = 1;
                } else if (place.contentEquals("hallway")) {
                    addItem("hallway", item);
                    res = 1;
                } else if (place.contentEquals("storeroom")) {
                    addItem("storeroom", item);
                    res = 1;
                } else if (place.contentEquals("library")) {
                    addItem("library", item);
                    res = 1;
                } else if (place.contentEquals("alchemy")) {
                    addItem("alchemy ", item);
                    res = 1;
                }
            } else {
                System.out.println("You do not have a " + item + " in you inventory.");
                res = 2;
            }
        } else if (action.contentEquals("use")) {
            if (itemInRoom(inv_items, item)) {
                if (isBreakable(item)) {
                    // destroy item if it is single use
                    if (item.contentEquals("potion")) {
                        hp = hp + 20;
                        System.out.println("Health is now " + hp);
                        removeItem(inv_items, item);
                    } else if (item.contentEquals("apple")) {
                        hp = hp + 15;
                        System.out.println("Health is now " + hp);
                        removeItem(inv_items, item);
                    } else if (item.contentEquals("water")) {
                        hp = hp + 10;
                        System.out.println("Health is now " + hp);
                        removeItem(inv_items, item);
                    } else if (item.contentEquals("book")) {
                        hp = hp + 15;
                        System.out.println("You learn better ways to defend yourself.");
                        System.out.println("Health is now " + hp);
                        removeItem(inv_items, item);
                    } else if (item.contentEquals("scroll")) {
                        hp = hp + 15;
                        System.out.println("You learn better ways to attack.");
                        System.out.println("Health is now " + hp);
                        removeItem(inv_items, item);
                    }
                } else {
                    // keep item if it can be used again
                    if (item.contentEquals("sword")) {
                        if (isEnemyAlive) {
                            attack = rand.nextInt(30);
                            attack += 1;
                            enemy_hp = enemy_hp - attack;
                            if (enemy_hp < 1) {
                                enemy_hp = 0;
                            }
                            System.out.println("Enemy is now " + enemy_hp + " health");
                            if (armour == 0) {
                                attack = rand.nextInt(10);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            } else if (armour == 20) {
                                attack = rand.nextInt(5);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            } else if (armour == 10) {
                                attack = rand.nextInt(7);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            } else if (armour == 30) {
                                attack = rand.nextInt(3);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            }
                            if (enemy_hp < 1) {
                                isEnemyAlive = false;
                                enemy_hp = 100;
                            }
                        } else {
                            System.out.println("There is no enemy to attack.");
                        }
                    } else if (item.contentEquals("spear")) {
                        if (isEnemyAlive) {
                            attack = rand.nextInt(15);
                            attack += 1;
                            enemy_hp = enemy_hp - attack;
                            if (enemy_hp < 1) {
                                enemy_hp = 0;
                            }
                            System.out.println("Enemy is now " + enemy_hp + " health");
                            if (armour == 0) {
                                attack = rand.nextInt(10);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            } else if (armour == 20) {
                                attack = rand.nextInt(5);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            } else if (armour == 10) {
                                attack = rand.nextInt(7);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            } else if (armour == 30) {
                                attack = rand.nextInt(3);
                                attack += 1;
                                hp = hp - attack;
                                System.out.println("You have been hit. Your hp is " + hp);
                            }
                            if (enemy_hp < 1) {
                                isEnemyAlive = false;
                                enemy_hp = 100;
                            }
                        } else {
                            System.out.println("There is no enemy to attack.");
                        }
                    } else if (item.contentEquals("chestplate")) {
                        armour = armour + 20;
                        System.out.println("You have equipped the chestplate.");
                    } else if (item.contentEquals("helmet")) {
                        armour = armour + 10;
                        System.out.println("You have equipped the helmet.");
                    } else if (item.contentEquals("gem")) {
                        System.out.println("You become invincible. The End.");
                        Thread.sleep(3000);
                        System.exit(1);
                    }
                }
                res = 1;
            } else {
                System.out.println("You do not have a " + item + " in you inventory.");
                res = 2;
            }
        }
    }

    /**
     * This method controls everything in the alchemy.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void alchemy() throws InterruptedException {
        place = "alchemy";
        System.out.println("You are now in the alchemy.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("library")) {
                        library();
                    } else if (thing.contentEquals("storeroom")) {
                        storeroom();
                    } else if (thing.contentEquals("keep")) {
                        keep();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the library, storeroom, or keep.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the library.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void library() throws InterruptedException {
        place = "library";
        System.out.println("You are now in the library.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("keep")) {
                        keep();
                    } else if (thing.contentEquals("alchemy")) {
                        alchemy();
                    } else if (thing.contentEquals("storeroom")) {
                        storeroom();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the keep, alchemy, or storeroom.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the storeroom.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void storeroom() throws InterruptedException {
        place = "storeroom";
        System.out.println("You are now in the storeroom.");
        System.out.println("There is an enemy in front of you");
        isEnemyAlive = true;
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("keep")) {
                        keep();
                    } else if (thing.contentEquals("library")) {
                        library();
                    } else if (thing.contentEquals("alchemy")) {
                        alchemy();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the keep, library, or alchemy.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the forge.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void forge() throws InterruptedException {
        place = "forge";
        System.out.println("You are now in the forge.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("hallway")) {
                        hallway();
                    } else if (thing.contentEquals("armoury")) {
                        armoury();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the hallway or armoury.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the armoury.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void armoury() throws InterruptedException {
        place = "armoury";
        System.out.println("You are now in the armoury.");
        System.out.println("There is an enemy in front of you");
        isEnemyAlive = true;
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (thing.contentEquals("inventory")) {
                        list = inv_items.replace('&', ' ');
                        System.out.println(list);
                    } else if (thing.contentEquals("room")) {
                        if (place.contentEquals("forge")) {
                            list = forge_items;
                        } else if (place.contentEquals("keep")) {
                            list = keep_items;
                        } else if (place.contentEquals("start")) {
                            list = start_items;
                        }
                        list = list.replace('&', ' ');
                        System.out.println(list);
                    }
                    else {
                        System.out.println("I only know list inventory and list room.");
                    }
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("fountain")) {
                        fountain();
                    } else if (thing.contentEquals("forge")) {
                        forge();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the fountain or forge.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the fountain.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void fountain() throws InterruptedException {
        place = "fountain";
        System.out.println("You are now next to the fountain.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (thing.contentEquals("inventory")) {
                        list = inv_items.replace('&', ' ');
                        System.out.println(list);
                    } else if (thing.contentEquals("room")) {
                        if (place.contentEquals("forge")) {
                            list = forge_items;
                        } else if (place.contentEquals("keep")) {
                            list = keep_items;
                        } else if (place.contentEquals("start")) {
                            list = start_items;
                        }
                        list = list.replace('&', ' ');
                        System.out.println(list);
                    }
                    else {
                        System.out.println("I only know list inventory and list room.");
                    }
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("courtyard")) {
                        courtyard();
                    } else if (thing.contentEquals("armoury")) {
                        armoury();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the courtyard or armoury.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the courtyard.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void courtyard() throws InterruptedException {
        place = "courtyard";
        System.out.println("You are now in the courtyard.");
        System.out.println("There is an enemy in front of you");
        isEnemyAlive = true;
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("kitchen")) {
                        kitchen();
                    } else if (thing.contentEquals("start")) {
                        startGame();
                    } else if (thing.contentEquals("fountain")) {
                        fountain();
                    } else if (thing.contentEquals("armoury")) {
                        armoury();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the kitchen, start, fountain, or armoury.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the kitchen.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void kitchen() throws InterruptedException {
        place = "kitchen";
        System.out.println("You are now in the kitchen.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("keep")) {
                        keep();
                    } else if (thing.contentEquals("hallway")) {
                        hallway();
                    } else if (thing.contentEquals("start")) {
                        startGame();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the keep, hallway, or start.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the keep.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void keep() throws InterruptedException {
        place = "keep";
        System.out.println("You are now in the keep.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("library")) {
                        library();
                    } else if (thing.contentEquals("storeroom")) {
                        storeroom();
                    } else if (thing.contentEquals("alchemy")) {
                        alchemy();
                    } else if (thing.contentEquals("hallway")) {
                        hallway();
                    } else if (thing.contentEquals("start")) {
                        startGame();
                    } else if (thing.contentEquals("kitchen")) {
                        kitchen();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the library, storeroom, alchemy, hallway, start, or kitchen.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the hallway.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void hallway() throws InterruptedException {
        place = "hallway";
        System.out.println("You are now in the hallway.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in you inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("keep")) {
                        keep();
                    } else if (thing.contentEquals("forge")) {
                        forge();
                    } else if (thing.contentEquals("start")) {
                        startGame();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the keep, forge, or start.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }
    }

    /**
     * This method controls everything in the starting room.
     * @throws InterruptedException Because the thread sleeps for 3 seconds.
     * @since 1.0
     */
    public static void startGame() throws InterruptedException {
        place = "start";
        System.out.println("You are standing in the entrance of a large castle.");
        for (;;) {
            System.out.println("What do you want to do?");
            choice = read.nextLine();
            if (validCommand(choice)) {
                if (choice.contentEquals("use")) {
                    System.out.println("What would you like to use?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("drop")) {
                    System.out.println("What would you like to drop?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in your inventory");
                    }
                } else if (choice.contentEquals("pickup")) {
                    System.out.println("What would you like to pickup?");
                    thing = read.nextLine();
                    if (item_exists(thing)) {
                        itemActions(choice, thing);
                    } else {
                        System.out.println("that item is not in this room");
                    }
                } else if (choice.contentEquals("health")) {
                    System.out.println("You have " + hp + " hp");
                } else if (choice.contentEquals("exit")) {
                    System.exit(0);
                } else if (choice.contentEquals("list")) {
                    System.out.println("What would you like to list?");
                    thing = read.nextLine();
                    System.out.println(list(thing));
                } else if (choice.contentEquals("go")) {
                    System.out.println("Where would you like to go?");
                    thing = read.nextLine();
                    if (thing.contentEquals("keep")) {
                        keep();
                    } else if (thing.contentEquals("kitchen")) {
                        kitchen();
                    } else if (thing.contentEquals("hallway")) {
                        hallway();
                    }  else if (thing.contentEquals("courtyard")) {
                        courtyard();
                    } else {
                        System.out.println("You cannot go there. " + "You can go to the hallway, courtyard, kitchen, or keep.");
                    }
                }
            } else {
                System.out.println("I only know pickup, drop, use, list, go and health as commands.");
            }
        }

    }

    /**
     * This method starts the game on two threads.
     * @since 1.0
     */
    public static void main(String[] args) {
        System.out.println("Running Setup");
        setup();
        System.out.println("Finished Setup");
        Thread StartGame = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startGame();
                } catch (InterruptedException e) {
                    throw new Error("Failed to start Game.");
                }
            }
        });
        StartGame.start();
        Thread Dead = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dead();
                } catch (InterruptedException e) {
                    throw new Error("Failed to start Dead method.");
                }
            }
        });
        Dead.start();
    }
}