package com.sha.gateway.model.service;

import com.google.gson.JsonElement;
import com.sha.gateway.channel.service.ProductServiceCallable;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractProductService implements EntityService<JsonElement,Integer>
{
    @Autowired
    protected ProductServiceCallable productServiceCallable;


}
