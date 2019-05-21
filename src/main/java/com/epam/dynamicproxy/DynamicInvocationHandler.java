package com.epam.dynamicproxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DynamicInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = Logger.getLogger( DynamicInvocationHandler.class.getName() );
    private final Object target;

    public DynamicInvocationHandler(Object target) {
        this.target = target;
    }
    private Map<String, Integer> calls  = new HashMap<>();

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        LOGGER.info("method being called : "+method.getName());
        if(method.getName().contains("toString")){
            System.out.println("entering into invoke method");
            return calls.toString();
        }
        calls.merge(method.getName(), 1, Integer::sum);
        return method.invoke(target, args);
    }
}
