package java8.leasson3;

import java8.leasson1.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author lx
 * @Date 2018/2/20 20:01
 */
public class Lambdas {
    public static List<Project> buildData() {
        List<Project> data = new ArrayList<>();
        data.add(Project.builder().name("Blade").language("java").author("biezhi")
                .stars(3500).description("Lightning fast and elegant mvc framework for Java8").build());

        data.add(Project.builder().name("Tale").language("java").author("biezhi")
                .stars(2600).description("Best beautiful java blog, worth a try").build());

        data.add(Project.builder().name("Vue.js").language("js").author("yyx990803")
                .stars(83000).description("A progressive, incrementally-adoptable JavaScript framework for building UI on the web.").build());

        data.add(Project.builder().name("Flask").language("python").author("pallets")
                .stars(10500).description("The Python micro framework for building web applications").build());

        data.add(Project.builder().name("Elves").language("java").author("biezhi")
                .stars(200).description("Spider").build());

        return data;
    }

    public static void main(String[] args) {
        List<Project> projects = buildData();
//        List<String> names = getNames(projects);
//        List<String> names = getNames(projects, p -> p.getStars() > 1000);
        List<Integer> names = getNames(projects, p -> p.getStars() > 1000, Project::getStars);
        System.out.println(names);
    }

    public static List<String> getNames(List<Project> projects) {
        List<String> names = new ArrayList<>();
        for (Project project : projects) {
            names.add(project.getName());
        }
        return names;
    }

    public static List<String> getNames(List<Project> projects, Predicate<Project> predicate) {
        List<String> names = new ArrayList<>();
        for (Project project : projects) {
            if (predicate.test(project)) {
                names.add(project.getName());
            }
        }
        return names;
    }

    /**
     * function 相当于类型转换
     *
     * @param projects
     * @param predicate
     * @param function
     * @param <R>
     * @return
     */
    public static <R> List<R> getNames(List<Project> projects, Predicate<Project> predicate, Function<Project, R> function) {
        List<R> names = new ArrayList<>();
        for (Project project : projects) {
            if (predicate.test(project)) {
                names.add(function.apply(project));
            }
        }
        return names;
    }

    interface ProjectFunction<R> extends Function<Project, R> {
        static ProjectFunction<Integer> buildStarFunction() {
            return Project::getStars;
        }
    }

}
