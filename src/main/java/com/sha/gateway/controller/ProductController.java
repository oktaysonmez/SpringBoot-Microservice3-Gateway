package com.sha.gateway.controller;

import com.google.gson.JsonElement;
import com.sha.gateway.model.service.AbstractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("gateway/product")
public class ProductController
{
    @Autowired
    private AbstractProductService service;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("{productID}")
    public ResponseEntity<?> delete(@PathVariable Integer productID) throws IOException
    {
        service.deleteByID(productID);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody JsonElement product)
    {
        return ResponseEntity.ok(service.save(product));
    }
}
