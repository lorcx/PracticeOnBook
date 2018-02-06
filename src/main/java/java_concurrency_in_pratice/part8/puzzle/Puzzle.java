package java_concurrency_in_pratice.part8.puzzle;

import java.util.Set;

/**
 * 谜题框架
 * @Author: lx
 * @Date: Created in 2018/2/6 0006
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);

}
