package java_concurrency_in_pratice.part11;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 锁分解优化，提升可伸缩性，降低锁竞争
 *
 * @Author lx
 * @Date 2018/2/11 18:28
 */
@ThreadSafe
public class ServerStatusAfterSplit {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatusAfterSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public void addUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }

    public void addQuery(String u) {
        synchronized (queries) {
            queries.add(u);
        }
    }

    public void removeQuery(String u) {
        synchronized (queries) {
            queries.remove(u);
        }
    }

    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        }
    }
}
