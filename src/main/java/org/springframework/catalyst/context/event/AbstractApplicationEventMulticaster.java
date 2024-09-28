package org.springframework.catalyst.context.event;

import org.springframework.catalyst.beans.BeansException;
import org.springframework.catalyst.beans.factory.BeanFactory;
import org.springframework.catalyst.beans.factory.BeanFactoryAware;
import org.springframework.catalyst.context.ApplicationEvent;
import org.springframework.catalyst.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

	public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

	private BeanFactory beanFactory;

	@Override
	public void addApplicationListener(ApplicationListener<?> listener) {
		applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
	}

	@Override
	public void removeApplicationListener(ApplicationListener<?> listener) {
		applicationListeners.remove(listener);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
