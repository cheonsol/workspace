package com.kh.textGame.repository;

import com.kh.textGame.entity.Item;
import com.kh.textGame.entity.Item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    List<Item> findByType(ItemType type);

    List<Item> findByNameContainingIgnoreCaseAndType(String name, ItemType type);
}