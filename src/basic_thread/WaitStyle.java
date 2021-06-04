package basic_thread;

/**
 * Author: yunCrush
 * Date: 2021/6/4 15:28
 * Description: MyBlockingQueueForWait 测试
 */
public class WaitStyle {
    public static void main(String[] args) {
    MyBlockingQueueForWait myBlockingQueueForWait = new MyBlockingQueueForWait(10);

    Producer producer = new Producer(myBlockingQueueForWait);
    Consumer consumer = new Consumer(myBlockingQueueForWait);

    new Thread(producer).start();
    new Thread(consumer).start();
    }
    //  生产者
    static  class Producer implements Runnable{
        private  MyBlockingQueueForWait storge;
        public Producer(MyBlockingQueueForWait storge){
            this.storge = storge;
        }
        @Override
        public void run() {
            for(int i=0;i<100;i++){
                try {
                    storge.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //  消费者
    static class Consumer implements  Runnable{
        private  MyBlockingQueueForWait storge;
        public Consumer(MyBlockingQueueForWait storge){
            this.storge = storge;
        }
        @Override
        public void run() {
            for(int i=0; i<100;i++){
                try {
                    storge.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
