package org.springframework.catalyst.beans.factory.config;

import org.springframework.catalyst.beans.factory.HierarchicalBeanFactory;
import org.springframework.catalyst.core.convert.ConversionService;
import org.springframework.catalyst.util.StringValueResolver;

/**
 * @author derekyi
 * @date 2020/11/28
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	/**
	 * @param beanPostProcessor
	 */
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

	/**
	 * 销毁单例bean
	 */
	void destroySingletons();

	void addEmbeddedValueResolver(StringValueResolver valueResolver);

	String resolveEmbeddedValue(String value);

	void setConversionService(ConversionService conversionService);

	ConversionService getConversionService();

}
