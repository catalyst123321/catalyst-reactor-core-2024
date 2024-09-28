package org.springframework.catalyst.context.event;

import org.springframework.catalyst.context.ApplicationContext;

/**
 * @author derekyi
 * @date 2020/12/2
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

	public ContextRefreshedEvent(ApplicationContext source) {
		super(source);
	}
}
