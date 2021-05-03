package com.ait.store.shopservice;

import com.ait.store.models.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ShopTest {
    private Shop shop;

        @BeforeEach
        public void setup(){
            shop = new Shop(1,"Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
        }

        @Test
        @DisplayName("Simple addition should work")
         public void testCombinedValue() {
             assertEquals(1510000, shop.CombinedValue(1500000,10000),
                "Regular multiplication should work");
        }


}
