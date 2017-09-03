package think_in_java;

import java.util.EnumMap;
import java.util.Iterator;
import static think_in_java.Input.*;

/**
 * Category安全
 * Created by dell on 2015/12/21.
 */
@SuppressWarnings("all")
public enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] types;

    Category(Input... types) {//决定（）中的类型
        this.types = types;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    static {
        for (Category cg : Category.class.getEnumConstants()) {
            for (Input in : cg.types) {
                categories.put(in, cg);
            }
        }
    }

    public static Category categorize(Input in) {
        return categories.get(in);
    }
}

/**
 * Veding:出售的
 */
@SuppressWarnings("all")
class VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    //duration持续
    enum StateDuration {
        TRANSIENT
    }

    enum State {
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < input.amount()) {
                            System.out.println("==" + selection);
                        } else {
                            state = DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        TERMINAL {
            @Override
            void output() {
                System.out.println("halted");
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            @Override
            void next(Input input) {
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        }, GIVING_CHANGE {
            @Override
            void next(Input input) {
                if (amount > 0) {
                    System.out.println("your change " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        };


        private boolean isTransient = false;

        State() {

        }

        State(StateDuration sd) {
            this.isTransient = true;
        }

        void output() {
            System.out.println(amount);
        }

        void next(Input input) {
            throw new RuntimeException("only call next(Input input) for non-transient states");//transient短暂的
        }

        void next() {
            throw new RuntimeException("only call next() statDuration.TRANSIENT");//transient短暂的
        }
    }

    static void run(Generator<Input> gen) {
        while (state != state.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient) {
                state.next();
            }
            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        if (args.length == 1) {
            gen = new FileInputGenerator(args[0]);
        }
        run(gen);
    }
}

@SuppressWarnings("all")
class RandomInputGenerator implements Generator<Input> {

    @Override
    public Input next() {
        return Input.randomSelection();
    }
}

@SuppressWarnings("all")
class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        this.input = new TextFile(fileName, ";").iterator();
    }

    @Override
    public Input next() {
        if (!input.hasNext()) {
            return null;
        }
        return Enum.valueOf(Input.class, input.next().trim());
    }
}