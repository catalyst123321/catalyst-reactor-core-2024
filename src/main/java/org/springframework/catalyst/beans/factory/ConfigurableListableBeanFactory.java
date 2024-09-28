package org.springframework.catalyst.beans.factory;

import org.springframework.catalyst.beans.BeansException;
import org.springframework.catalyst.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.catalyst.beans.factory.config.BeanDefinition;
import org.springframework.catalyst.beans.factory.config.BeanPostProcessor;
import org.springframework.catalyst.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author derekyi
 * @date 2020/11/28
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

	/**
	 * 根据名称查找BeanDefinition
	 *
	 * @param beanName
	 * @return
	 * @throws BeansException 如果找不到BeanDefintion
	 */
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	/**
	 * 提前实例化所有单例实例
	 *
	 * @throws BeansException
	 */
	void preInstantiateSingletons() throws BeansException;

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
