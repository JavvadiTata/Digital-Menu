package com.karthiksoft.digitalmenu.digital_menu.repo;

import com.karthiksoft.digitalmenu.digital_menu.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<Menu, String> {
}
