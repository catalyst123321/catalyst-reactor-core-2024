package org.springframework.catalyst.beans.factory;

/**
 * @author derekyi
 * @date 2020/11/29
 */
public interface InitializingBean {

	void afterPropertiesSet() throws Exception;
}
