package java_mult_thread_core_technology.part3.example;

/**
 * Created by Administrator on 2017/8/28.
 */
public class DBTools {
    // 保证交替执行，先执行备份A
    private volatile boolean preIsA = false;

    public synchronized void backupA() {
        try {
            while (preIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("★★★★★");
            }
            preIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void backupB() {
        try {
            while (!preIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("☆☆☆☆☆");
            }
            preIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BackupA extends Thread {
    private DBTools dbTools;

    public BackupA(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}

class BackupB extends Thread {
    private DBTools dbTools;

    public BackupB(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}

class RunBackup {
    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; i++) {
            BackupB output = new BackupB(dbTools);
            output.start();
            BackupA inpput = new BackupA(dbTools);
            inpput.start();
        }
        System.out.println(Thread.currentThread().getThreadGroup().activeCount());
    }
}