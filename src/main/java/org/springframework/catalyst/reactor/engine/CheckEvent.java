package org.springframework.catalyst.reactor.engine;



@FunctionalInterface
public interface CheckEvent<T> {
     boolean check(T t);
}
