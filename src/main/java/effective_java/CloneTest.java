package effective_java;

/**
 * 书上关于克隆的一些代码片段
 * Created by lx on 2016/4/30.
 */
public class CloneTest {
    private Entry[] buckets;//散列桶

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * 深拷贝 递归方式
         * @return
         */
//        Entry deepCopy() {
//            return new Entry(key, value, next == null ? null : next.deepCopy());
//        }

        /**
         * 迭代方式 比递归好
         * @return
         */
        Entry deepCopy() {
            Entry result = new Entry(key,value,next);
            for(Entry p = result;p.next != null;p = p.next){
                p.next = new Entry(p.next.key,p.next.value,p.next);
            }
            return result;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneTest ct = (CloneTest) super.clone();
        ct.buckets = new Entry[buckets.length];
        for(int i = 0;i < buckets.length;i++){
            if(buckets[i] != null){
                ct.buckets[i] = buckets[i].deepCopy();
                return ct;
            }
        }
        return ct;
    }

}
