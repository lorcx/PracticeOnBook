package java_concurrency_in_pratice.part9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: lx
 * @Date: Created in 2018/2/7 0007
 */
public class ListenerExamples {
    private static ExecutorService exec = Executors.newCachedThreadPool();
    private final JButton colorButton = new JButton("Change color");
    private final Random random = new Random();

    private void backgroundRandom() {
        // 按下按钮会随机更新背景颜色
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorButton.setBackground(new Color(random.nextInt()));
            }
        });
    }

    // 长时间执行任务
    private final JButton computeButton = new JButton("Big computation");

    private void longRunningTask() {
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        /*Do big compution*/
                    }
                });
            }
        });
    }


    private final JButton button = new JButton("Do");
    private final JLabel label = new JLabel("idle");

    private void longRunningTaskWithFeedback() {
        /**
         * 首先设置按钮不可用-> 将长时间执行任务提交个一个线程去处理->执行完毕后开启一个线程来更新视图
         * “线程接力”
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);
                label.setText("busy");
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            /*Do big computation*/
                        } finally {
                            GuiExecutor.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    button.setEnabled(true);
                                    label.setText("idle");
                                }
                            });
                        }
                    }
                });
            }
        });
    }


    /**
     * 可以取消
     */
    private final JButton startButton = new JButton("Start");
    private final JButton cancelButton = new JButton("Cancel");
    private Future<?> runningTask = null;

    private void taskWithCancellation() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (runningTask != null) {
                    runningTask = exec.submit(new Runnable() {
                        @Override
                        public void run() {
                            while (moreWork()) {
                                if (Thread.currentThread().isInterrupted()) {
                                    cleanUpPartialWork();
                                    break;
                                }
                                doSomeWork();
                            }
                        }

                        private void doSomeWork() {

                        }

                        private void cleanUpPartialWork() {

                        }

                        private boolean moreWork() {
                            return false;
                        }
                    });
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (runningTask != null) {
                    runningTask.cancel(true);
                }
            }
        });

    }

    private void runInBackground(final Runnable task) {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                class CancelListener implements ActionListener {
                    BackgroundTask<?> task;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (task != null) {
                            task.cancel(true);
                        }
                    }
                }

                final CancelListener listener = new CancelListener();
                listener.task = new BackgroundTask<Void>() {
                    public Void compute() {
                        while (moreWork() && !isCancelled()) {
                            doSomeWork();
                        }
                        return null;
                    }

                    private boolean isCancelled() {
                        return false;
                    }

                    private boolean moreWork() {
                        return false;
                    }

                    private void doSomeWork() {

                    }

                    public void onCompletion(boolean cancelled, String s, Throwable ex) {
                        cancelButton.removeActionListener(listener);
                        label.setText("done");
                    }

                };

                cancelButton.addActionListener(listener);
                exec.execute(task);

            }
        });
    }

    class BackgroundTask<T> {
        public void cancel(boolean s) {

        }
    }
}
