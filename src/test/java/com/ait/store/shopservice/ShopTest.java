package com.ait.store.shopservice;

import com.ait.store.models.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTest {
    private Shop shop;
    private Shop shop2;

        @BeforeEach
        public void setup(){
            shop = new Shop(1,"Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
            shop2 = new Shop(2,"Centra", "Firhouse, Dublin", "Ireland", 012334432, null, "dewre", null, 10000);
        }

        @Test
        @DisplayName("Simple addition should work")
         public void testCombinedValue() {
             assertEquals(1510000, shop.CombinedValue(1500000,10000),
                "Regular multiplication should work");
        }


}
