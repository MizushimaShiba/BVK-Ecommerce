package com.bvk.test.ecommerce.controller;

import com.bvk.test.ecommerce.model.Inventory;
import com.bvk.test.ecommerce.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * // @author 水島芝
 */

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/order/{id}")
    public ResponseEntity<Inventory> order(@PathVariable(value = "id") Long id, @RequestBody Integer quantity) {
        if (!inventoryRepository.existsById(id)) return ResponseEntity.notFound().build();

        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        if (inventory == null) return ResponseEntity.notFound().build();

        if (quantity > inventory.getQuantity()) return ResponseEntity.badRequest().build();
        if (quantity <= 0) return ResponseEntity.badRequest().build();
        if (inventory.getQuantity() == 0) return ResponseEntity.badRequest().build();

        inventory.setQuantity(quantity);
        return ResponseEntity.ok(inventoryRepository.save(inventory));
    }
}
