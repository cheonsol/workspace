package com.kh.textGame.controller;

import com.kh.textGame.dto.ItemDto;
import com.kh.textGame.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    // 아이템 생성 (관리자 권한 필요)
    @PostMapping
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDto));
    }

    // 모든 아이템 조회 (모두 허용)
    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    // 아이템 ID로 조회 (모두 허용)
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        ItemDto itemDto = itemService.getItemById(id);
        if (itemDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDto);
    }

    // 아이템 수정 (관리자 권한 필요)
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        try {
            return ResponseEntity.ok(itemService.updateItem(id, itemDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 아이템 삭제 (관리자 권한 필요)
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
