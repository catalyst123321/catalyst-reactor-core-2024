package org.springframework.catalyst.context.support;

import org.springframework.catalyst.beans.BeansException;
import org.springframework.catalyst.beans.factory.config.BeanPostProcessor;
import org.springframework.catalyst.context.ApplicationContext;
import org.springframework.catalyst.context.ApplicationContextAware;

/**
 * @author derekyi
 * @date 2020/12/1
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

	private final ApplicationContext applicationContext;

	public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof ApplicationContextAware) {
			((ApplicationContextAware) bean).setApplicationContext(applicationContext);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
