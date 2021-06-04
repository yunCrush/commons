package basic_thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: yunCrush
 * @Date: 2021/6/4 11:21
 * Description: BlockingQueue 实现生产者、消费者模式
 */
public class ConsumerPattern {
    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        //  生产者
        Runnable producer = () ->{
            while(true){
                try {
                    queue.put(new Object());
                    System.out.println("queue size: " + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(producer).start();
        new Thread(producer).start();
        // 消费者
        Runnable consumer = () -> {
            try {
                Object obj = queue.take();
                System.out.println("take object: "+obj.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
