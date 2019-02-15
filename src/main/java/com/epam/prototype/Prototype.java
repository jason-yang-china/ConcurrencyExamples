package com.epam.prototype;

public abstract class Prototype implements  Cloneable{

    public abstract Object copy() throws CloneNotSupportedException;
}
