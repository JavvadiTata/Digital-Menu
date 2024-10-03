package com.karthiksoft.digitalmenu.digital_menu.service;

import com.karthiksoft.digitalmenu.digital_menu.dto.MenuItemRequestDto;
import com.karthiksoft.digitalmenu.digital_menu.model.Menu;
import com.karthiksoft.digitalmenu.digital_menu.model.MenuItem;
import com.karthiksoft.digitalmenu.digital_menu.model.Restaurant;
import com.karthiksoft.digitalmenu.digital_menu.repo.MenuRepository;
import com.karthiksoft.digitalmenu.digital_menu.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private S3Service s3Service;

    public Optional<Object> getMenuByRestaurantId(String restaurantId) {
        System.out.println(restaurantId);
        System.out.println(restaurantRepository.findById(restaurantId).get().getName());
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        System.out.println(restaurantOpt.get().getMenuId());
        if (restaurantOpt.isPresent()) {

            String menuId = restaurantOpt.get().getMenuId();
            System.out.println(menuId);
            return Optional.of(menuRepository.findById(menuId));
        }
        return Optional.empty();
    }



    public Menu addItemToMenu(String restaurantId, MenuItemRequestDto newItemRequestDto) throws IOException {
        Optional<Menu> menuOpt = menuRepository.findById(restaurantId);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();

            // Upload image to S3 and get the image URL
            String imageUrl = s3Service.uploadFile(newItemRequestDto.getItemImage());

            // Create a new MenuItem with the image URL
            MenuItem newItem = new MenuItem(newItemRequestDto.getItemName(), newItemRequestDto.getPrice(), imageUrl);

            // Add the item to the menu category
            menu.addItemToCategory(newItemRequestDto.getCategory(), newItem);

            // Save the updated menu
            return menuRepository.save(menu);
        } else {
            throw new RuntimeException("Menu not found for restaurant with ID: " + restaurantId);
        }
    }
}

