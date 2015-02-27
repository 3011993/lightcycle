package com.soundcloud.android.lightcycle;

public abstract class LightCycleInjector {

    public static void attach(LightCycleDispatcher<?> target) {
        LightCycleInjector injector;
        final String injectorClassName = getInjectorClassName(target);
        try {
            final Class<?> injectorClass = target.getClass().getClassLoader().loadClass(injectorClassName);
            injector = (LightCycleInjector) injectorClass.newInstance();
            injector.inject(target);
        } catch (Exception e) {
            // no injectors found, so ignore.
        }
    }

    private static String getInjectorClassName(Object target) {
        return target.getClass().getCanonicalName() + "$LightCycleInjector";
    }

    public abstract void inject(LightCycleDispatcher<?> target);
}
