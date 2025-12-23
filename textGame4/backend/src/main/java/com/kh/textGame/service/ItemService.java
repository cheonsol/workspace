package com.kh.textGame.service;

import com.kh.textGame.dto.ItemDto;
import com.kh.textGame.entity.Item;
import com.kh.textGame.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public ItemDto createItem(ItemDto itemDto) {
        Item item = Item.builder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .img(itemDto.getImg())
                .type(itemDto.getType())
                .attackBoost(itemDto.getAttackBoost())
                .defenseBoost(itemDto.getDefenseBoost())
                .healthBoost(itemDto.getHealthBoost())
                .manaBoost(itemDto.getManaBoost())
                .healAmount(itemDto.getHealAmount())
                .manaRestoreAmount(itemDto.getManaRestoreAmount())
                .price(itemDto.getPrice())
                .build();
        Item savedItem = itemRepository.save(item);
        return convertEntityToDto(savedItem);
    }

    @Transactional(readOnly = true)
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemDto getItemById(Long id) {
        return itemRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }


    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setImg(itemDto.getImg());
        item.setType(itemDto.getType());
        item.setAttackBoost(itemDto.getAttackBoost());
        item.setDefenseBoost(itemDto.getDefenseBoost());
        item.setHealthBoost(itemDto.getHealthBoost());
        item.setManaBoost(itemDto.getManaBoost());
        item.setHealAmount(itemDto.getHealAmount());
        item.setManaRestoreAmount(itemDto.getManaRestoreAmount());
        item.setPrice(itemDto.getPrice());

        Item updatedItem = itemRepository.save(item);
        return convertEntityToDto(updatedItem);
    }


    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDto convertEntityToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setImg(item.getImg());
        itemDto.setType(item.getType());
        itemDto.setAttackBoost(item.getAttackBoost());
        itemDto.setDefenseBoost(item.getDefenseBoost());
        itemDto.setHealthBoost(item.getHealthBoost());
        itemDto.setManaBoost(item.getManaBoost());
        itemDto.setHealAmount(item.getHealAmount());
        itemDto.setManaRestoreAmount(item.getManaRestoreAmount());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }
}
