package com.epam;

import com.epam.adapter.Warlord;
import com.epam.adapter.WaterGunAdpter;
import org.junit.Test;

/**
 * Adapter design pattern : this is one class you could not directly call it, but you exactly want to use it,
 * the solution is you create another class which it is similar as the target class, the client calls the wrapped object instead of call
 * the class directly, basically, the two interfaces are different, but one interface could contain another interface
 *
 */
public class AdapterTest {

    @Test
    public void WarlordFightTest() {
         Warlord warlord = new Warlord(new WaterGunAdpter());
         warlord.row();

    }
}
