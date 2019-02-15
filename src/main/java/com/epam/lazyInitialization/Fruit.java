package com.epam.lazyInitialization;

import java.util.HashMap;
import java.util.Map;

public class Fruit {
    private static Map<FruitType,Fruit> types = new HashMap<FruitType, Fruit>();

    private Fruit(FruitType type) {
    }

    /**
     * Lazy Factory method, get the Fruit instance associated with a certain type. Instantiates new one if map does not contain this key
     * @param type any allowed fruit type, e.g. Apple, Banana
     * @return
     */
    public static Fruit getFruitByTypeName(FruitType type) {
        Fruit fruit;
        //this has concurrency issues. Here to read to types is not synchronized,
        // types.put and types.containsKey might be called at the same time.
        if(!types.containsKey(type)){
            fruit = new Fruit(type);
            types.put(type, fruit);
        } else {
            fruit = types.get(type);
        }
        return fruit;
    }

    /**
     * Lazy Factory method, gets the Fruit instance associated with a certain
     * type.
     * pattern for using in highly concurrent environments.
     * @param type Any allowed fruit type, e.g. APPLE
     * @return The Fruit instance associated with that type.
     */
    public static Fruit getFruitByTypeNameConcurrentVersion(FruitType type) {

        synchronized (types) {
            if(!types.containsKey(type)) {
                types.put(type, new Fruit(type));
            }
        }
        return types.get(type);
    }

    /**
     * Display all entered fruits.
     */
    public static void showAll() {
        if(types.size()> 0){
            System.out.println("Number of instances made = "+types.size());

            for(Map.Entry<FruitType, Fruit> entry : types.entrySet()) {
                String fruit = entry.getKey().toString();
                fruit = Character.toUpperCase(fruit.charAt(0)) + fruit.substring(1);
                System.out.println(fruit);
            }
            System.out.println();
        }
    }

    /**
     *
     * @return the size of the fruit
     */
    public static int getFruitSize() {
        return types.size();
    }
}
