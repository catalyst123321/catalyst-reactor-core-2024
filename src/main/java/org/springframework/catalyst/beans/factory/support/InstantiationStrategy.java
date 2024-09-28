package org.springframework.catalyst.beans.factory.support;

import org.springframework.catalyst.beans.BeansException;
import org.springframework.catalyst.beans.factory.config.BeanDefinition;

/**
 * Bean的实例化策略
 *
 * @author derekyi
 * @date 2020/11/23
 */
public interface InstantiationStrategy {

	Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
