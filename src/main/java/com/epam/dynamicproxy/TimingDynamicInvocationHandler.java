package com.epam.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TimingDynamicInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = Logger.getLogger( DynamicInvocationHandler.class.getName() );

    private Object target;
    private final Map<String, Method> methods = new HashMap<>();
    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;

        for(Method method: target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;
        LOGGER.info("Executing "+method.getName()+" finished in "+elapsed+" ns");
        return result;
    }
}
