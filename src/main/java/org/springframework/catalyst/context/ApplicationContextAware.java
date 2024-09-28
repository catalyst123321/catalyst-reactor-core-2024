package org.springframework.catalyst.context;

import org.springframework.catalyst.beans.BeansException;
import org.springframework.catalyst.beans.factory.Aware;

/**
 * 实现该接口，能感知所属ApplicationContext
 *
 * @author derekyi
 * @date 2020/12/1
 */
public interface ApplicationContextAware extends Aware {

	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
