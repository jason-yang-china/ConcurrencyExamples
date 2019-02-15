package com.epam;

import com.epam.bridge.HappinessEmotion;
import com.epam.bridge.People;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class BridgePeopleTest  {


    @Test
    public void peopleTest() {
        final People people = spy(new People(mock(HappinessEmotion.class)));

    }
}
