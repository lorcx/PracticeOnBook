package think_in_java;

/**
 * 18章序列化  xom 生成xml
 * Created by lx on 2015/12/19.
 */
public class Person1 {
    private String firstName;
    private String lastName;


//    public Person1(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public Person1(nu.xom.Element element) {
//        firstName = element.getFirstChildElement("firstName").getValue();
//        lastName = element.getFirstChildElement("lastName").getValue();
//    }
//
//
//    public nu.xom.Element getXml() {
//        Element person = new Element("person");
//        Element firstName = new Element("firstName");
//        firstName.appendChild(this.firstName);
//        Element lastName = new Element("lastName");
//        lastName.appendChild(this.lastName);
//        person.appendChild(firstName);
//        person.appendChild(lastName);
//        return person;
//    }
//
//    @Override
//    public String toString() {
//        return "Person1{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                '}';
//    }
//
//    public static void format(OutputStream os, Document doc) throws Exception {
//        Serializer serializer = new Serializer(os, "UTF-8");
//        serializer.setIndent(4);
//        serializer.setMaxLength(60);
//        serializer.write(doc);
//        serializer.flush();
//    }
//
//    public static void main(String[] args) throws Exception {
//        //序列化生成 xml
//        List<Person1> list = Arrays.asList(new Person1("a1", "b1"), new Person1("a2", "b2"), new Person1("a3", "b3"));
//        System.out.println(list);
//        Element root = new Element("people");
//        for (Person1 p1 : list) {
//            root.appendChild(p1.getXml());
//        }
//        Document doc = new Document(root);
//        format(System.out, doc);//格式化控制台输出
//        format(new BufferedOutputStream(new FileOutputStream(new File("F:\\person.xml"))), doc);
//
//    }
//}
//
//
//class People extends ArrayList<Person1> {
//    /**
//     * 反序列化
//     *
//     * @param fileName :文件名
//     */
//    public People(String fileName) throws Exception {
//        Document doc = new Builder().build(fileName);
//        Elements element = doc.getRootElement().getChildElements();
//        for (int i = 0; i < element.size(); i++) {
//            add(new Person1(element.get(i)));
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        //反序列化 读取xml
//        People peeople = new People("file:///F:/person.xml");// xom 路径只能这么写
//        System.out.println(peeople); //调用 person1的 tostring
//    }
}