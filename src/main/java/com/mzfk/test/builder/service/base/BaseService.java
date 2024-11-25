package com.mzfk.test.builder.service.base;

import java.util.Collection;

abstract class BaseService<T, I> {
    abstract T findById(I id);

    abstract Collection<T> findAll();

    abstract T save(T entity);

    abstract void deleteById(I id);
}
