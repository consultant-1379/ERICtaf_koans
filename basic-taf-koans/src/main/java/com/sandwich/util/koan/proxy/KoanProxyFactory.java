package com.sandwich.util.koan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import com.sandwich.koan.KoanIncompleteException;

public class KoanProxyFactory {

    public static interface KoanDecorator<T> {
        T __(Object... args);

        int __();
    }

    @SuppressWarnings("unchecked")
    public static <T, V> T getProxy(Class<V> getUnderscoreReturnType, T objectToDecorate) {

        return (T) new KoanBridge<T, V>(objectToDecorate);
    }

    private static class KoanBridge<T, V> implements InvocationHandler, KoanDecorator<V> {

        T proxied;

        public KoanBridge(T proxied) {
            this.proxied = proxied;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (method.getDeclaringClass().equals(proxied.getClass())) {
                return method.invoke(proxied, args);
            } else if (method.getName().equals("__")) {
                return this.__(args);
            } else
                throw new RuntimeException();
        }

        @Override
        public V __(Object... args) {
            throw new KoanIncompleteException("This Koan has not been completed");
        }

        @Override
        public int __() {
            throw new KoanIncompleteException("This Koan has not been completed");
        }

    }

}