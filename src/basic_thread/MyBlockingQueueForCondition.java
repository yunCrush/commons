package basic_thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: yunCrush
 * Date: 2021/6/4 14:06
 * Description: Condition实现生产者消费者模式
 */
public class MyBlockingQueueForCondition {
    private Queue queue;
    private  int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public MyBlockingQueueForCondition( int size) {
        this.max = size;
        this.queue = new LinkedList();
    }

    public void put(Object obj) throws InterruptedException {
        lock.lock();
    //  队列满，则阻塞
        try {
            while(queue.size() == max){
    //  producer 的 await() 阻塞生产者线程并释放 Lock
                producer.await();
            }
            queue.add(obj);
    //  往队列放入数据并利用 consumer.signalAll() 通知正在等待的所有消费者并唤醒它们
            consumer.signalAll();
        } finally {
    // 最后切记释放锁
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while(queue.size()==0){
    //  队列为空，消费者等待
                consumer.await();
            }
            Object obj = queue.poll();
    //  唤醒生产者
            producer.signalAll();
            return obj;
        } finally {
            lock.unlock();
        }
    }
}
