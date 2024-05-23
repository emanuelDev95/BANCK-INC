package com.egvc.banckinc.infraestructure.adapters.helper;

public interface MapperGeneric <D,E> {

    E toEntity(D data);
    D toData(E entity);
}
