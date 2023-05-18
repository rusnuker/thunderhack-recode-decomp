//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package meteordevelopment.orbit;

import meteordevelopment.orbit.listeners.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

public class EventBus implements IEventBus
{
    private final Map<Object, List<IListener>> listenerCache;
    private final Map<Class<?>, List<IListener>> staticListenerCache;
    private final Map<Class<?>, List<IListener>> listenerMap;
    private final List<LambdaFactoryInfo> lambdaFactoryInfos;
    
    public EventBus() {
        this.listenerCache = new ConcurrentHashMap<Object, List<IListener>>();
        this.staticListenerCache = new ConcurrentHashMap<Class<?>, List<IListener>>();
        this.listenerMap = new ConcurrentHashMap<Class<?>, List<IListener>>();
        this.lambdaFactoryInfos = new ArrayList<LambdaFactoryInfo>();
    }
    
    @Override
    public void registerLambdaFactory(final String packagePrefix, final LambdaListener.Factory factory) {
        synchronized (this.lambdaFactoryInfos) {
            this.lambdaFactoryInfos.add(new LambdaFactoryInfo(packagePrefix, factory));
        }
    }
    
    @Override
    public <T> T post(final T event) {
        final List<IListener> listeners = this.listenerMap.get(event.getClass());
        if (listeners != null) {
            for (final IListener listener : listeners) {
                listener.call(event);
            }
        }
        return event;
    }
    
    @Override
    public <T extends ICancellable> T post(final T event) {
        final List<IListener> listeners = this.listenerMap.get(event.getClass());
        if (listeners != null) {
            event.setCancelled(false);
            for (final IListener listener : listeners) {
                listener.call(event);
                if (event.isCancelled()) {
                    break;
                }
            }
        }
        return event;
    }
    
    @Override
    public void subscribe(final Object object) {
        this.subscribe(this.getListeners(object.getClass(), object), false);
    }
    
    @Override
    public void subscribe(final Class<?> klass) {
        this.subscribe(this.getListeners(klass, null), true);
    }
    
    @Override
    public void subscribe(final IListener listener) {
        this.subscribe(listener, false);
    }
    
    private void subscribe(final List<IListener> listeners, final boolean onlyStatic) {
        for (final IListener listener : listeners) {
            this.subscribe(listener, onlyStatic);
        }
    }
    
    private void subscribe(final IListener listener, final boolean onlyStatic) {
        if (onlyStatic) {
            if (listener.isStatic()) {
                this.insert(this.listenerMap.computeIfAbsent(listener.getTarget(), aClass -> new CopyOnWriteArrayList()), listener);
            }
        }
        else {
            this.insert(this.listenerMap.computeIfAbsent(listener.getTarget(), aClass -> new CopyOnWriteArrayList()), listener);
        }
    }
    
    private void insert(final List<IListener> listeners, final IListener listener) {
        int i;
        for (i = 0; i < listeners.size() && listener.getPriority() <= listeners.get(i).getPriority(); ++i) {}
        listeners.add(i, listener);
    }
    
    @Override
    public void unsubscribe(final Object object) {
        this.unsubscribe(this.getListeners(object.getClass(), object), false);
    }
    
    @Override
    public void unsubscribe(final Class<?> klass) {
        this.unsubscribe(this.getListeners(klass, null), true);
    }
    
    @Override
    public void unsubscribe(final IListener listener) {
        this.unsubscribe(listener, false);
    }
    
    private void unsubscribe(final List<IListener> listeners, final boolean staticOnly) {
        for (final IListener listener : listeners) {
            this.unsubscribe(listener, staticOnly);
        }
    }
    
    private void unsubscribe(final IListener listener, final boolean staticOnly) {
        final List<IListener> l = this.listenerMap.get(listener.getTarget());
        if (l != null) {
            if (staticOnly) {
                if (listener.isStatic()) {
                    l.remove(listener);
                }
            }
            else {
                l.remove(listener);
            }
        }
    }
    
    private List<IListener> getListeners(final Class<?> klass, final Object object) {
        final List<IListener> listeners;
        final Function<Object, List<IListener>> func = o -> {
            listeners = new CopyOnWriteArrayList<IListener>();
            this.getListeners(listeners, klass, object);
            return listeners;
        };
        if (object == null) {
            return this.staticListenerCache.computeIfAbsent(klass, func);
        }
        for (final Object key : this.listenerCache.keySet()) {
            if (key == object) {
                return this.listenerCache.get(object);
            }
        }
        final List<IListener> listeners2 = func.apply(object);
        this.listenerCache.put(object, listeners2);
        return listeners2;
    }
    
    private void getListeners(final List<IListener> listeners, final Class<?> klass, final Object object) {
        for (final Method method : klass.getDeclaredMethods()) {
            if (this.isValid(method)) {
                listeners.add(new LambdaListener(this.getLambdaFactory(klass), klass, object, method));
            }
        }
        if (klass.getSuperclass() != null) {
            this.getListeners(listeners, klass.getSuperclass(), object);
        }
    }
    
    private boolean isValid(final Method method) {
        return method.isAnnotationPresent(EventHandler.class) && method.getReturnType() == Void.TYPE && method.getParameterCount() == 1 && !method.getParameters()[0].getType().isPrimitive();
    }
    
    private LambdaListener.Factory getLambdaFactory(final Class<?> klass) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        meteordevelopment/orbit/EventBus.lambdaFactoryInfos:Ljava/util/List;
        //     4: dup            
        //     5: astore_2       
        //     6: monitorenter   
        //     7: aload_0         /* this */
        //     8: getfield        meteordevelopment/orbit/EventBus.lambdaFactoryInfos:Ljava/util/List;
        //    11: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    16: astore_3       
        //    17: aload_3        
        //    18: invokeinterface java/util/Iterator.hasNext:()Z
        //    23: ifeq            63
        //    26: aload_3        
        //    27: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    32: checkcast       Lmeteordevelopment/orbit/EventBus$LambdaFactoryInfo;
        //    35: astore          info
        //    37: aload_1         /* klass */
        //    38: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    41: aload           info
        //    43: getfield        meteordevelopment/orbit/EventBus$LambdaFactoryInfo.packagePrefix:Ljava/lang/String;
        //    46: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    49: ifeq            60
        //    52: aload           info
        //    54: getfield        meteordevelopment/orbit/EventBus$LambdaFactoryInfo.factory:Lmeteordevelopment/orbit/listeners/LambdaListener$Factory;
        //    57: aload_2        
        //    58: monitorexit    
        //    59: areturn        
        //    60: goto            17
        //    63: aload_2        
        //    64: monitorexit    
        //    65: goto            75
        //    68: astore          5
        //    70: aload_2        
        //    71: monitorexit    
        //    72: aload           5
        //    74: athrow         
        //    75: new             new            !!! ERROR
        //    78: dup            
        //    79: aload_1         /* klass */
        //    80: invokespecial   invokespecial  !!! ERROR
        //    83: athrow         
        //    Signature:
        //  (Ljava/lang/Class<*>;)Lmeteordevelopment/orbit/listeners/LambdaListener$Factory;
        //    MethodParameters:
        //  Name   Flags  
        //  -----  -----
        //  klass  
        //    StackMapTable: 00 05 FD 00 11 07 00 04 07 00 56 2A FA 00 02 44 07 00 41 FA 00 06
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      59     68     75     Any
        //  60     65     68     75     Any
        //  68     72     68     75     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:280)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:177)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2438)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static class LambdaFactoryInfo
    {
        public final String packagePrefix;
        public final LambdaListener.Factory factory;
        
        public LambdaFactoryInfo(final String packagePrefix, final LambdaListener.Factory factory) {
            this.packagePrefix = packagePrefix;
            this.factory = factory;
        }
    }
}
