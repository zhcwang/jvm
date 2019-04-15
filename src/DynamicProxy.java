import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    interface IHello {
        void say();
    }

    static class Hello implements IHello {
        @Override
        public void say() {
            System.out.println("hello");
        }
    }

    static class DynamicProxyDemo implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj){
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(),
                    this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if("say".equals(method.getName())){
                System.out.println("welcome");
            }
            //return method.invoke(proxy, args);
            return method.invoke(originalObj, args);

        }
    }

    public static void main(String[] args) throws Throwable {
        // method:1
       /* DynamicProxyDemo demo = new DynamicProxyDemo();
        Hello hello = new Hello();
        Method say = hello.getClass().getDeclaredMethod("say");
        demo.invoke(hello, say, null);*/
        // method:2
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        IHello hello1 = (IHello) new DynamicProxyDemo().bind(new Hello());
        hello1.say();
    }

}
