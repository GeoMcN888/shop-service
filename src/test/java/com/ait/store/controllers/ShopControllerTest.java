package com.ait.store.controllers;

import com.ait.store.models.Shop;
import com.ait.store.repositories.ShopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShopController.class)
class ShopControllerTest {



    @Autowired
    private MockMvc mvc;
    @MockBean
    private ShopRepository shopRepository;
    @MockBean
    EntityManager entityManager;

    @Test
    void getShopById() throws Exception{
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
        entityManager.persist(shop);
        doReturn(Optional.of(shop)).when(shopRepository).findById(1L);
        mvc.perform(get("/shops/{shopId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void addShop() throws Exception{
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);

        mvc.perform(post("/shops")
                .content(asJsonString(shop))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateShop() throws Exception {
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);

        mvc.perform(put("/shops/1")
                .content(asJsonString(shop))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getShops() throws Exception {
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
        Shop shop2 = new Shop(2, "Centra", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 2100000);
        shopRepository.save(shop);
        shopRepository.save(shop2);
        doReturn(Lists.newArrayList(shop, shop2)).when(shopRepository).findAll();
        mvc.perform(get("/shops")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shopId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Supervalu")))
                .andExpect(jsonPath("$[0].address", is("Knocklyon, Dublin")))
                .andExpect(jsonPath("$[0].phoneNumber", is(012342354)))
                .andExpect(jsonPath("$[1].shopId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Centra")))
                .andExpect(jsonPath("$[1].address", is("Knocklyon, Dublin")))
                .andExpect(jsonPath("$[1].phoneNumber", is(012342354)));
    }

    @Test
    void getShopsByNameAndCountry() throws Exception {
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
        Shop shop2 = new Shop(2, "Centra", "Firhouse, Dublin", "Ireland", 014362254, null, "dewre", null, 2100000);
        shopRepository.save(shop);
        shopRepository.save(shop2);
        doReturn(Lists.newArrayList(shop)).when(shopRepository).findByNameAndCountry("Supervalu", "Ireland");
        mvc.perform(get("/shops?name=Supervalu&country=Ireland")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].shopId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Supervalu")))
                .andExpect(jsonPath("$[0].address", is("Knocklyon, Dublin")))
                .andExpect(jsonPath("$[0].phoneNumber", is(012342354)));
    }

    @Test
    void getShopsByName() throws Exception {
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
        Shop shop2 = new Shop(2, "Supervalu", "Firhouse, Dublin", "Ireland", 014362254, null, "dewre", null, 2100000);
        shopRepository.save(shop);
        shopRepository.save(shop2);
        doReturn(Lists.newArrayList(shop, shop2)).when(shopRepository).findByName("Supervalu");
        mvc.perform(get("/shops?name=Supervalu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shopId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Supervalu")))
                .andExpect(jsonPath("$[0].address", is("Knocklyon, Dublin")))
                .andExpect(jsonPath("$[0].phoneNumber", is(012342354)))
                .andExpect(jsonPath("$[1].shopId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Supervalu")))
                .andExpect(jsonPath("$[1].address", is("Firhouse, Dublin")))
                .andExpect(jsonPath("$[1].phoneNumber", is(014362254)));
    }

    @Test
    void getShopsByCountry() throws Exception {
        Shop shop = new Shop(1, "Supervalu", "Knocklyon, Dublin", "Ireland", 012342354, null, "dewre", null, 1500000);
        Shop shop2 = new Shop(2, "Centra", "Firhouse, Dublin", "Ireland", 014362254, null, "dewre", null, 2100000);
        shopRepository.save(shop);
        shopRepository.save(shop2);
        doReturn(Lists.newArrayList(shop, shop2)).when(shopRepository).findByCountry("Ireland");
        mvc.perform(get("/shops?country=Ireland")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].shopId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Supervalu")))
                .andExpect(jsonPath("$[0].address", is("Knocklyon, Dublin")))
                .andExpect(jsonPath("$[0].phoneNumber", is(012342354)))
                .andExpect(jsonPath("$[1].shopId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Centra")))
                .andExpect(jsonPath("$[1].address", is("Firhouse, Dublin")))
                .andExpect(jsonPath("$[1].phoneNumber", is(014362254)));
    }
}