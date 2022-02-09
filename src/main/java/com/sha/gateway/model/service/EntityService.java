package com.sha.gateway.model.service;

import java.io.IOException;
import java.util.List;

public interface EntityService<E, I>
{
    List<E> findAll();

    E save(E entity);

    void deleteByID(I id) throws IOException;
}
