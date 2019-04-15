import test.ClassA;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Described：常量池内存溢出探究
 * @VM args :
 *   JDK < 1.8   -XX:PermSize=10M -XX:MaxPermSize=10M
 *   JDK >= 1.8  -XX:MetaspaceSize=10M  -XX:MaxMetaspaceSize=10m
 * 在Java8之后取消了永久带，主要是为了融合HotSpot JVM 与 JRockit VM
 */
public class ConstantOOM {
    /**
     * JDK 1.8 以前的的测试方法，String放在方法区的常量池中
     */
    public void constantOOMBefore8(){
        try {
            List<String> strings = new ArrayList<>();
            int i = 0;
            while(true){
                strings.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    /**
     * JDK 1.8 以后的测试方法，String已经放在堆中了
     */
    public void constantOOMAfter7() throws Exception {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        URL url = new File("").toURI().toURL();
        URL[] urls = new URL[]{url};
        while (true) {
            ClassLoader loader = new URLClassLoader(urls);
            Class<?> loadClass = loader.loadClass(Object.class.getName());
            classes.add(loadClass);
        }
    }
    public static void main(String[] args) throws Exception{
        ConstantOOM oom = new ConstantOOM();
        oom.constantOOMAfter7();
    }

}
