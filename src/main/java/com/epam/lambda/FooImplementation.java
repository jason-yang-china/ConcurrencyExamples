package com.epam.lambda;

import java.util.function.Function;

public class FooImplementation {

    public String add(String string, Foo foo){
        return foo.method(string);
    }

    public String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }
}
