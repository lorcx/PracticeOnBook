package think_in_java;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Preference api
 * 只能存储少量的数据 ，在不同的系统实现是不认同的
 * windows中存在注册表中 键值对存储
 * Created by lx on 2015/12/19.
 */
public class PreferenceDemo {
    public static void main(String[] args) throws BackingStoreException {
        Preferences pref = Preferences.userNodeForPackage(PreferenceDemo.class);
        //添加值
        pref.put("aa","bb");
        pref.put("cc","dd");
        pref.putBoolean("hh",true);
        pref.putInt("ss",1);
        int usageCount = pref.getInt("UsageCount",0);
        usageCount++;
        pref.putInt("UsageCount",usageCount);
        for (String key : pref.keys()){
            System.out.println(key + "==" + pref.get(key,null));//必须给个默认值
        }
        System.out.println(pref.getInt("ss",1));
    }
}
