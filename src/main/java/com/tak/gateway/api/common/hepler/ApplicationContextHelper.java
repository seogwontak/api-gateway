package com.tak.gateway.api.common.hepler;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Helper for spring ApplicationContext.
 *
 */
@Component
public final class ApplicationContextHelper implements ApplicationContextAware {

    /** The context. */
    private static ApplicationContext context;

    /** 내부 synchronization을 위한 정보. */
    private static final Object       mSemaphore = new Object();

    /**
     * Gets the application context.
     *
     * @return the application context
     * @created at 2025. 01. 19 by Gwontak seo
     */
    public static final ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * Gets the bean.
     */
    public static final <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * Gets the bean.
     */
    public static final Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * Sets the context.
     */
    private static void setContext(ApplicationContext ctx) {
        synchronized (mSemaphore) {
            context = ctx;
        }
    }

    /**
     * Sets the application context.
     *
     * @param applicationContext the new application context
     * @throws BeansException the beans exception
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }
}
