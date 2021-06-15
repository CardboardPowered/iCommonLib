package org.minecarts.api.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.minecarts.api.util.Multithreading;

public class EventRegistery {

    public static HashMap<Class<?>, List<RegisteredListener>> map = new HashMap<>();

    public static boolean register(Class<?> c, Method e, Object lis) {
        EventHandler hand = e.getAnnotation(EventHandler.class);

        List<RegisteredListener> list = map.getOrDefault(c, new ArrayList<>());
        RegisteredListener l = new RegisteredListener(lis, hand, e);
        boolean registered = list.add(l);
        map.put(c, list);

        return registered;
    }

    public static int registerAll(Object lis) {
        int registered = 0;
        for (Method m : lis.getClass().getMethods()) {

            Class<?>[] pt = m.getParameterTypes();
            if (pt.length <= 0) continue;

            Class<?> clazz = pt[0];
            Class<?> clazzo = pt[0];

            while (null != clazz.getSuperclass()) {
                if (clazz.equals(Event.class)) break;

                clazz = clazz.getSuperclass();
            }

            if (null != clazz && clazz.equals(Event.class)) { // first argument of method is subclass of Event
                if (register(clazzo, m, lis)) registered++;
            }
        }
        return registered;
    }

    public static Event invoke(Class<? extends Event> type, Event ev) {
        invoke(map.getOrDefault(type, new ArrayList<>()), ev);

        return ev;
    }

    public static void invoke(List<RegisteredListener> ls, Event ev) {
        if (ev.isAsync()) Multithreading.runAsync(() -> invoke0(ls,ev)); else invoke0(ls,ev);
    }

    public static void invoke0(List<RegisteredListener> ls, Event ev) {
        for (RegisteredListener l : ls) {
            Method m = l.method;
            try {
                if (ev instanceof Cancelable)
                    if (((Cancelable)ev).isCanceled() && !l.ignoreCancelled) return;

                m.invoke(l.l, ev);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}