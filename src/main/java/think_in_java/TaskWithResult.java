package think_in_java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 带返回值的线程
 * Created by lx on 2015/12/25.
 */
@SuppressWarnings("all")
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "taskWithResult id :" + id;
    }
}

@SuppressWarnings("all")
class CallAbleDemo{
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<Future<String>>();
        for (int i = 0;i < 10;i++){
            list.add(service.submit(new TaskWithResult(i)));
        }
        for (Future<String> f:list){
            try {
                System.out.println(f.get());//f.isDone()
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                service.shutdown();
            }
        }
    }
}
