package basic_thread;

/**
 * Author: yunCrush
 * Date:2021/6/4 0:03
 * Description: 当前线程在休眠中响应中断
 */
public class StopDuringSleep {
    public static void main(String[] args) throws InterruptedException {
       Runnable runnable = () ->{
           int num = 0;
           while(!Thread.currentThread().isInterrupted() && num <= 1000){
               System.out.println("num: " + num++);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
    }

}
