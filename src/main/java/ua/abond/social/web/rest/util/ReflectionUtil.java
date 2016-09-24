package ua.abond.social.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {
    private ReflectionUtil() { }

    /**
     * Proper use of this class is
     *     String testName = (new Util.MethodNameHelper(){}).getName();
     *  or
     *     Method me = (new Util.MethodNameHelper(){}).getMethod();
     * the anonymous class allows easy access to the method name of the enclosing scope.
     */
    public static class MethodNameHelper {
        public String getName() {
            final Method myMethod = this.getClass().getEnclosingMethod();
            if (null == myMethod) {
                // This happens when we are non-anonymously instantiated
                return this.getClass().getSimpleName() + ".unknown()"; // return a less useful string
            }
            final String className = myMethod.getDeclaringClass().getSimpleName();
            return className + "." + myMethod.getName() + "()";
        }

        public Method getMethod() {
            return this.getClass().getEnclosingMethod();
        }
    }

    public static String getUrlMapping(Method method) throws Exception {
        RequestMapping mapping = method.getAnnotation(RequestMapping.class);

        String[] values = mapping.value();
        if (values.length > 1) {
            throw new UnsupportedOperationException("RequestMapping is allowed to have only one mapping");
        }
        else if (values.length == 0) {
            // not possible
            return null;
        }
        return values[0];
    }
}
