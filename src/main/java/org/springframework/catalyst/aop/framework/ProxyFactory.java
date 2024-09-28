package org.springframework.catalyst.aop.framework;

import org.springframework.catalyst.aop.AdvisedSupport;

/**
 * @author zqc
 * @date 2022/12/16
 */
public class ProxyFactory extends AdvisedSupport {


	public ProxyFactory() {
	}

	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	private AopProxy createAopProxy() {
		if (this.isProxyTargetClass() || this.getTargetSource().getTargetClass().length == 0) {
			return new CglibAopProxy(this);
		}

		return new JdkDynamicAopProxy(this);
	}
}
