package org.springframework.catalyst.context;

import org.springframework.catalyst.beans.factory.HierarchicalBeanFactory;
import org.springframework.catalyst.beans.factory.ListableBeanFactory;
import org.springframework.catalyst.core.io.ResourceLoader;

/**
 * 应用上下文
 *
 * @author derekyi
 * @date 2020/11/28
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
