/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit;

public class NoLambdaFactoryException
extends RuntimeException {
    public NoLambdaFactoryException(Class<?> klass) {
        super("No registered lambda listener for '" + klass.getName() + "'.");
    }
}

