package org.minecarts.api.event;

import java.lang.reflect.Method;

public class RegisteredListener {

    public final Object l;
    public final EventPriority p;
    public final boolean ignoreCancelled;
    public final Method method;

    public RegisteredListener(Object l, EventHandler h, Method m) {
        this.l = l;
        this.p = h.priority();
        this.ignoreCancelled = h.ignoreCancelled();
        this.method = m;
    }

}