package think_in_java;

/**
 * enum用到的
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}

