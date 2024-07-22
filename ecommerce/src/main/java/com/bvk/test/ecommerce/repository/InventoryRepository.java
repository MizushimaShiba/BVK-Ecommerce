package com.bvk.test.ecommerce.repository;

import com.bvk.test.ecommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    


}
