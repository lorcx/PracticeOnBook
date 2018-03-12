package java_concurrency_in_pratice.part11;

import java_concurrency_in_pratice.anno.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用一个全局锁
 * @Author lx
 * @Date 2018/2/11 18:28
 */
@ThreadSafe
public class ServerStatusBeforeSplit {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatusBeforeSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public synchronized void addUser(String u) {
        users.add(u);
    }

    public synchronized void addQuery(String u) {
        queries.add(u);
    }

    public synchronized void removeQuery(String u) {
        queries.remove(u);
    }

    public synchronized void removeUser(String u) {
        users.remove(u);
    }
}
