package org.springframework.catalyst.reactor.engine;

import java.util.function.Function;


@FunctionalInterface
public interface ExceptionEvent<T> {
    Object exceptionHandle(Exception e, T t);

}
