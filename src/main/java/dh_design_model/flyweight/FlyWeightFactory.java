package dh_design_model.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 * Created by dell on 2016/6/19.
 */
public class FlyWeightFactory {
    private Map flyweights = new HashMap();

    public FlyWeightFactory() {
        flyweights.put("X",new ConCreateFlyWeight());
        flyweights.put("Y",new ConCreateFlyWeight());
        flyweights.put("Z",new ConCreateFlyWeight());
    }

    public FlyWeight getFlyWeight(String key){
        if(!flyweights.containsKey(key)){
            return (FlyWeight) flyweights.put(key,new ConCreateFlyWeight());
        }
        return (FlyWeight) flyweights.get(key);
    }
}
