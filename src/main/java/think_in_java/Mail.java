package think_in_java;

import java.util.Iterator;

/**
 * 使用enum的职责链
 * Created by lx on 2015/12/20.
 */
@SuppressWarnings("all")
public class Mail {
    //delilvery :递送
    enum GeneralDelivery {YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability {UNSCANNABLE,YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE,YES1,YES2,YES3,YES4}
    enum Address {INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
    enum ReturnAddress {MISSING,OK1,OK2,OK3,OK4,OK5}
    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;

    static long count = 0;//count在类初始化，只初始化一次
    long id = count++;

    @Override
    public String toString() {
        return "Mail : " + id;
    }

    //详情
    public String detail() {
        return "Mail{" +
                "generalDelivery=" + generalDelivery +
                ", scannability=" + scannability +
                ", readability=" + readability +
                ", address=" + address +
                ", returnAddress=" + returnAddress +
                ", id=" + id +
                '}';
    }

    //随机生成mail
    public static Mail randomMail(){
        Mail m = new Mail();
        m.generalDelivery= Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    /**
     * 迭代mail对象用
     * @param count
     * @return
     */
    public static Iterable<Mail> generator(final int count){
        return new Iterable<Mail>() {
            int n = count;
            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    //不能删除
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

@SuppressWarnings("all")
class postOffice{
    enum MailHandler{
        GeneralDelivery{
            @Override
            boolean handler(Mail m) {
                switch (m.generalDelivery){
                    case YES :
                        System.out.println("Using general delivery for" + m);
                        return true;
                    default : return false;
                }
            }
        },
        Scannability{
            @Override
            boolean handler(Mail m) {
                //switch嵌套
                switch (m.scannability){
                    case UNSCANNABLE : return false;
                    default :
                        switch (m.address){
                            case INCORRECT : return false;
                            default :
                                System.out.println("delivery" + m +" automaticlly");
                                return true;
                        }
                }
            }
        },
        Readability{
            @Override
            boolean handler(Mail m) {
                switch (m.readability){
                    case ILLEGIBLE : return false;
                    default :
                        switch (m.address){
                            case INCORRECT:return false;
                            default:
                                System.out.println("delivery" + m +" normally");
                                return true;
                        }
                }
            }
        },
        ReturnAddress{
            @Override
            boolean handler(Mail m) {
                switch (m.returnAddress){
                    case MISSING : return false;
                    default :
                        System.out.println("returning " + m + "to sender");
                        return true;
                }
            }
        };

        //管理
        abstract boolean handler(Mail m);
    }

    static void handler(Mail m){
        for (MailHandler mh : MailHandler.values()){
            if(mh.handler(m)){
                return;
            }
            System.out.println(m + "dead mail!!!!!!!");
        }
    }


    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)){
            System.out.println("detail : "+ mail.detail());
            handler(mail);
            System.out.println("-----------");
        }
    }

    /**
     * output :责任链模式遍历查找 ，找到就退出
     * detail : Mail{generalDelivery=NO2, scannability=UNSCANNABLE, readability=YES3, address=OK1, returnAddress=OK1, id=0}
     Mail : 0dead mail!!!!!!!
     Mail : 0dead mail!!!!!!!
     deliveryMail : 0 normally
     -----------
     detail : Mail{generalDelivery=NO5, scannability=YES3, readability=ILLEGIBLE, address=OK5, returnAddress=OK1, id=1}
     Mail : 1dead mail!!!!!!!
     deliveryMail : 1 automaticlly
     -----------
     detail : Mail{generalDelivery=YES, scannability=YES3, readability=YES1, address=OK1, returnAddress=OK5, id=2}
     Using general delivery forMail : 2
     -----------
     detail : Mail{generalDelivery=NO4, scannability=YES3, readability=YES1, address=INCORRECT, returnAddress=OK4, id=3}
     Mail : 3dead mail!!!!!!!
     Mail : 3dead mail!!!!!!!
     Mail : 3dead mail!!!!!!!
     returning Mail : 3to sender
     -----------
     detail : Mail{generalDelivery=NO4, scannability=UNSCANNABLE, readability=YES1, address=INCORRECT, returnAddress=OK2, id=4}
     Mail : 4dead mail!!!!!!!
     Mail : 4dead mail!!!!!!!
     Mail : 4dead mail!!!!!!!
     returning Mail : 4to sender
     -----------
     detail : Mail{generalDelivery=NO3, scannability=YES1, readability=ILLEGIBLE, address=OK4, returnAddress=OK2, id=5}
     Mail : 5dead mail!!!!!!!
     deliveryMail : 5 automaticlly
     -----------
     detail : Mail{generalDelivery=YES, scannability=YES4, readability=ILLEGIBLE, address=OK4, returnAddress=OK4, id=6}
     Using general delivery forMail : 6
     -----------
     detail : Mail{generalDelivery=YES, scannability=YES3, readability=YES4, address=OK2, returnAddress=MISSING, id=7}
     Using general delivery forMail : 7
     -----------
     detail : Mail{generalDelivery=NO3, scannability=YES1, readability=YES3, address=INCORRECT, returnAddress=MISSING, id=8}
     Mail : 8dead mail!!!!!!!
     Mail : 8dead mail!!!!!!!
     Mail : 8dead mail!!!!!!!
     Mail : 8dead mail!!!!!!!
     -----------
     detail : Mail{generalDelivery=NO1, scannability=UNSCANNABLE, readability=YES2, address=OK1, returnAddress=OK4, id=9}
     Mail : 9dead mail!!!!!!!
     Mail : 9dead mail!!!!!!!
     deliveryMail : 9 normally
     -----------
     *
     */
}