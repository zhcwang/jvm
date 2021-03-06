package test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    static class ClassA {
        private void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable{
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("123123");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable{
        MethodType mt = MethodType.methodType(void.class, String.class);
        MethodHandle mh = MethodHandles.lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
        return  mh;
    }
}
