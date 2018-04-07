package java8_in_action.part8.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 责任链模式
 * @Author lx
 * @Date 2018/4/7 15:11
 */
public class ChainOfResponsibilityMain {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCherckerProcessing();
        p1.setSuccessor(p2);
        String result1 = p1.handle("Aren't labdas really sexy!!");
        System.out.println(result1);


        UnaryOperator<String> headerProcessing = (String text) -> "FROM Raoul, Mario and Alan:" + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> f = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = f.apply("Aren't labdas really sexy!!");
        System.out.println(result2);

    }

    private static abstract class ProcessingObject<T> {
        protected ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        protected abstract T handleWork(T input);

    }

    private static class HeaderTextProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return "FROM Raoul, Mario and Alan:" + text;
        }
    }

    private static class SpellCherckerProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return text.replaceAll("labda", " lambda");
        }
    }
}
