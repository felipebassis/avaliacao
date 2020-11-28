package com.elotech.avaliacao.common.crud;

@FunctionalInterface
public interface Mapper<T, U> {
    U convert(T param);
}
