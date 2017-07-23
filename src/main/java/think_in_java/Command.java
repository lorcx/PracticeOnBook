package think_in_java;

import java.util.EnumMap;
import java.util.Map;

/**
 * enumMap
 * Created by lx on 2015/12/20.
 */
public interface Command {
    void action();
}

class enumMaps{
    public static void main(String[] args) {
        EnumMap<AlarmPoints,Command> map  = new EnumMap<AlarmPoints,Command>(AlarmPoints.class);
        map.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("11111111111");
            }
        });

        map.put(AlarmPoints.KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("2");
            }
        });

        for (Map.Entry<AlarmPoints,Command> entry : map.entrySet()){
            System.out.println(entry.getKey()); //key
            entry.getValue().action(); //value
        }

    }
}