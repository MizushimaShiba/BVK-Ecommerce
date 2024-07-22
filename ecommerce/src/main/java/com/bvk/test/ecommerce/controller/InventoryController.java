package com.bvk.test.ecommerce.controller;

import com.bvk.test.ecommerce.model.Inventory;
import com.bvk.test.ecommerce.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/add")
    public ResponseEntity<Inventory> add(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryRepository.save(inventory));
    }

    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<Inventory> updateQuantity(@PathVariable(value = "id") Long id, @RequestBody Long quantity) {
        if (!inventoryRepository.existsById(id)) return ResponseEntity.notFound().build();

        Inventory inventory = inventoryRepository.findById(id).orElse(null);


        assert inventory != null;
        inventory.setQuantity(quantity);
        return ResponseEntity.ok(inventoryRepository.save(inventory));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Inventory> get(@PathVariable(value = "id") Long id) {
        if (!inventoryRepository.existsById(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventoryRepository.findById(id).orElse(null));
    }

    
    
}
