package com.epam.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class SimpleInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = Logger.getLogger( DynamicInvocationHandler.class.getName() );
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Invoked method: "+ method.getName());
        return 32;
    }
}
