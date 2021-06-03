package basic_thread;

/**
 * Author: yunCrush
 * Date:2021/6/3 23:47
 * Description:
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("count = " + count++);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5);
        thread.interrupt();
    }

}

