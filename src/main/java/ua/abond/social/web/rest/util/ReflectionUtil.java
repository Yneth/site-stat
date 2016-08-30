package ua.abond.social.web.rest.util;

import java.lang.reflect.Method;

public class ReflectionUtil {
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
}
