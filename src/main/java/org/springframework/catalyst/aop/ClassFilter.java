package org.springframework.catalyst.aop;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public interface ClassFilter {

	boolean matches(Class<?> clazz);
}
