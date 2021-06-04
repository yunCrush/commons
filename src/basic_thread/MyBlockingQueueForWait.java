package basic_thread;

import java.awt.*;
import java.util.LinkedList;

/**
 * Author: yunCrush
 * Date: 2021/6/4 15:17
 * Description: wait,notifyAll实现 生产者消费者模式
 */
public class MyBlockingQueueForWait {
    private  int maxSize;
    private LinkedList<Object> storge;
    public  MyBlockingQueueForWait(int size){
        this.maxSize = size;
        this.storge = new LinkedList<>();
    }
    //  put操作
    public synchronized  void put() throws InterruptedException {
        while(storge.size() == maxSize){
            wait();
        }
        storge.add(new Object());
        System.out.println("storge size: " + storge.size());
        notifyAll();
    }

    // take操作
    public synchronized  void take() throws InterruptedException {
        while(storge.size()==0){
            wait();
        }
        Object obj = storge.remove();
        System.out.println("take: " + obj);
        notifyAll();

    }
}
