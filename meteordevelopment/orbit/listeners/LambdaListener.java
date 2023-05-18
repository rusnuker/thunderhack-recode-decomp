//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package meteordevelopment.orbit.listeners;

import java.util.function.*;
import meteordevelopment.orbit.*;
import java.lang.invoke.*;
import java.lang.reflect.*;

public class LambdaListener implements IListener
{
    private static boolean isJava1dot8;
    private static Constructor<MethodHandles.Lookup> lookupConstructor;
    private static Method privateLookupInMethod;
    private final Class<?> target;
    private final boolean isStatic;
    private final int priority;
    private Consumer<Object> executor;
    
    public LambdaListener(final Factory factory, final Class<?> klass, final Object object, final Method method) {
        this.target = method.getParameters()[0].getType();
        this.isStatic = Modifier.isStatic(method.getModifiers());
        this.priority = method.getAnnotation(EventHandler.class).priority();
        try {
            final String name = method.getName();
            MethodHandles.Lookup lookup;
            if (LambdaListener.isJava1dot8) {
                final boolean a = LambdaListener.lookupConstructor.isAccessible();
                LambdaListener.lookupConstructor.setAccessible(true);
                lookup = LambdaListener.lookupConstructor.newInstance(klass);
                LambdaListener.lookupConstructor.setAccessible(a);
            }
            else {
                lookup = factory.create(LambdaListener.privateLookupInMethod, klass);
            }
            final MethodType methodType = MethodType.methodType(Void.TYPE, method.getParameters()[0].getType());
            MethodHandle methodHandle;
            MethodType invokedType;
            if (this.isStatic) {
                methodHandle = lookup.findStatic(klass, name, methodType);
                invokedType = MethodType.methodType(Consumer.class);
            }
            else {
                methodHandle = lookup.findVirtual(klass, name, methodType);
                invokedType = MethodType.methodType(Consumer.class, klass);
            }
            final MethodHandle lambdaFactory = LambdaMetafactory.metafactory(lookup, "accept", invokedType, MethodType.methodType(Void.TYPE, Object.class), methodHandle, methodType).getTarget();
            if (this.isStatic) {
                this.executor = (Consumer<Object>)lambdaFactory.invoke();
            }
            else {
                this.executor = (Consumer<Object>)lambdaFactory.invoke(object);
            }
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    
    public void call(final Object event) {
        this.executor.accept(event);
    }
    
    public Class<?> getTarget() {
        return this.target;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public boolean isStatic() {
        return this.isStatic;
    }
    
    static {
        try {
            LambdaListener.isJava1dot8 = System.getProperty("java.version").startsWith("1.8");
            if (LambdaListener.isJava1dot8) {
                LambdaListener.lookupConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
            }
            else {
                LambdaListener.privateLookupInMethod = MethodHandles.class.getDeclaredMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
            }
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    public interface Factory
    {
        MethodHandles.Lookup create(final Method p0, final Class<?> p1) throws InvocationTargetException, IllegalAccessException;
    }
}
