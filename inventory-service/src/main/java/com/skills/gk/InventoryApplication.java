package com.skills.gk;

import com.skills.gk.model.Inventory;
import com.skills.gk.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.skells.gk.*")
@ComponentScan(basePackages = { "com.skells.gk.*" })
@EntityScan("com.skells.gk.*")
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//            Inventory inventory1 = new Inventory();
//            inventory1.setSkuCode("iPhone_13_mini");
//            inventory1.setQuantity(50);
//
//            Inventory inventory2 = new Inventory();
//            inventory2.setSkuCode("iPhone_13_mini_red");
//            inventory2.setQuantity(0);
//            inventoryRepository.save(inventory1);
//            inventoryRepository.save(inventory2);
//        };
//    }

}