package debug.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by haoranchen on 16-7-6.
 */
public class Test {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        atomicInteger.set(2147483647);

        while(true) {
            System.out.println(atomicInteger.getAndIncrement());
        }
    }
}
