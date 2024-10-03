package com.karthiksoft.digitalmenu.digital_menu.controllers;

import com.karthiksoft.digitalmenu.digital_menu.dto.MenuItemRequestDto;
import com.karthiksoft.digitalmenu.digital_menu.model.Menu;
import com.karthiksoft.digitalmenu.digital_menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{restaurantId}")
    public Optional<Object> getMenuByRestaurantId(@PathVariable String restaurantId)
    {
        System.out.println(restaurantId);
        return menuService.getMenuByRestaurantId(restaurantId);
//                .map(ResponseEntity.ok())
//                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{restaurantId}/menu/item")
    public ResponseEntity<Menu> addItemToMenu(@PathVariable String restaurantId,
                                              @RequestParam("category") String category,
                                              @RequestParam("itemName") String itemName,
                                              @RequestParam("price") double price,
                                              @RequestParam("itemImage") MultipartFile itemImage) {
        try {
            MenuItemRequestDto newItemRequestDto = new MenuItemRequestDto();
            newItemRequestDto.setCategory(category);
            newItemRequestDto.setItemName(itemName);
            newItemRequestDto.setPrice(price);
            newItemRequestDto.setItemImage(itemImage);

            Menu updatedMenu = menuService.addItemToMenu(restaurantId, newItemRequestDto);
            return ResponseEntity.ok(updatedMenu);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
