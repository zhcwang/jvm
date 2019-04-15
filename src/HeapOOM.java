
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HeapOOM {

    private static final String LOCAL_VAR = "LOCAL_VAR";

    private ConstantOOM constantOOM = new ConstantOOM();

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();
        while (true){
            l.add(String.format("测试数据：%d",  System.currentTimeMillis()));
        }
    }

    public static void lock(){
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        boolean b = lock.tryLock();

    }
}
