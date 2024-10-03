package com.karthiksoft.digitalmenu.digital_menu.repo;

import com.karthiksoft.digitalmenu.digital_menu.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
