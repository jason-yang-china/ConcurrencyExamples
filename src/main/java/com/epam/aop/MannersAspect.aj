package com.epam.aop;

public aspect MannersAspect {
    pointcut deliverMessage()
            : call(* MessageCommunicator.deliver(..));
    before() : deliverMessage() {
        System.out.println("Hello! ");
    }


}
