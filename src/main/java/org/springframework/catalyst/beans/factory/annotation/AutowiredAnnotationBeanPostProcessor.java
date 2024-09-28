package org.springframework.catalyst.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import org.springframework.catalyst.beans.BeansException;
import org.springframework.catalyst.beans.PropertyValues;
import org.springframework.catalyst.beans.factory.BeanFactory;
import org.springframework.catalyst.beans.factory.BeanFactoryAware;
import org.springframework.catalyst.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.catalyst.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.catalyst.core.convert.ConversionService;

import java.lang.reflect.Field;

/**
 * 处理@Autowired和@Value注解的BeanPostProcessor
 *
 * @author derekyi
 * @date 2020/12/27
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

	private ConfigurableListableBeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		//处理@Value注解
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Value valueAnnotation = field.getAnnotation(Value.class);
			if (valueAnnotation != null) {
				Object value = valueAnnotation.value();
				value = beanFactory.resolveEmbeddedValue((String) value);

				//类型转换
				Class<?> sourceType = value.getClass();
				Class<?> targetType = (Class<?>) TypeUtil.getType(field);
				ConversionService conversionService = beanFactory.getConversionService();
				if (conversionService != null) {
					if (conversionService.canConvert(sourceType, targetType)) {
						value = conversionService.convert(value, targetType);
					}
				}

				BeanUtil.setFieldValue(bean, field.getName(), value);
			}
		}

		//处理@Autowired注解
		for (Field field : fields) {
			Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
			if (autowiredAnnotation != null) {
				Class<?> fieldType = field.getType();
				String dependentBeanName = null;
				Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
				Object dependentBean = null;
				if (qualifierAnnotation != null) {
					dependentBeanName = qualifierAnnotation.value();
					dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
				} else {
					dependentBean = beanFactory.getBean(fieldType);
				}
				BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
			}
		}

		return pvs;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		return true;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
