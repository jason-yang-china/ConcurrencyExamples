package com.epam.poolobject;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class PooledObjects {
    private static Map<MyObject, Long> available= new HashMap<MyObject,Long>();
    private static Map<MyObject, Long> inUse= new HashMap<MyObject,Long>();
    private static long expireTime = 6000; //6 seconds


    /**
     * The function could be used to retrieve the object in available Map, if the object is not expired then return the object to client,
     * otherwise we just remove the object from the map and iterate the item in the map again to see the available element.
     * @return the return object from available map,then track it in the inUse map. It only maintains the inuse map which stays in the memory, once it is released,
     * the available map will kep this in map for reuse purpose.
     */
    public static MyObject getObject() {
        long now = System.currentTimeMillis();

        if(!available.isEmpty()){
            for(Map.Entry<MyObject, Long> entry: available.entrySet()) {
                System.out.println("[now] = "+now+"[entry time value] = "+ entry.getValue() + "[now - expireTime] = "+ (now-entry.getValue()));
                if(now - entry.getValue() >= expireTime) { //remove the object from the available
                      removeElement(available, entry.getKey());
                } else {
                      MyObject object = popElement(available, entry.getKey());
                      pushElement(inUse, object, now);
                      return object;
                }
            }
        }
        return createPooledObject(now);
    }

    public static void pushElement(Map<MyObject, Long> map, MyObject object, long now) {
        map.put(object, now);
    }

    public static MyObject createPooledObject(long time) {
        MyObject myObject = new MyObject();
        inUse.put(myObject, time);
        return myObject;
    }

    public static void removeElement(Map<MyObject, Long> map,MyObject object) {
        map.remove(object);
    }

    public static MyObject popElement(Map<MyObject, Long> map,MyObject object) {
        map.remove(object);
        return object;
    }

    /**
     * release object only means remove its from inuse map and attach its into available
     * @param object
     * @return
     */
    public static void releaseObject(MyObject object) {
        available.put(object, System.currentTimeMillis());
        inUse.remove(object);

    }

    public static Map<MyObject, Long> getInusedMap() {
        return inUse;
    }

    public static Map<MyObject, Long> getAvailableMap() {
        return available;
    }
}
