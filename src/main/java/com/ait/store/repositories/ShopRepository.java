package com.ait.store.repositories;

import com.ait.store.models.Product;
import com.ait.store.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByNameAndCountry(String name, String country);
    List<Shop> findByName(String name);
    List<Shop> findByCountry(String country);
    List<Shop> findByType(String type);
}