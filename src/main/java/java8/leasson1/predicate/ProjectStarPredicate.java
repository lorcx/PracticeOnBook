package java8.leasson1.predicate;

import java8.leasson1.Project;
import java8.leasson1.ProjectPredicate;

/**
 * @Author lx
 * @Date 2018/2/19 17:29
 */
public class ProjectStarPredicate implements ProjectPredicate {
    private Integer star;

    public ProjectStarPredicate(Integer star) {
        this.star = star;
    }

    @Override
    public boolean test(Project project) {
        return project.getStars() > star;
    }
}
