package java8.leasson1.predicate;

import java8.leasson1.Project;
import java8.leasson1.ProjectPredicate;

/**
 * @Author lx
 * @Date 2018/2/19 17:31
 */
public class ProjectLanguagePredicate implements ProjectPredicate{

    private String language;

    public ProjectLanguagePredicate(String language) {
        this.language = language;
    }

    @Override
    public boolean test(Project project) {
        return language.equals(project.getLanguage());
    }
}