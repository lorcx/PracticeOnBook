package java_structure_arithmetic;

import java.util.Stack;

/**
 * 树类 （未测试 ）
 * Created by lx on 2016/9/10.
 */
public class Tree {
    private Node root;

    /**
     * 查找
     * @param key
     */
    public Node find(int key) {
        Node current = root;
        while (current.iData != key) {
            if (current.iData < key) {
                current = current.rightNode;
            } else {
                current = current.leftNode;
            }
            if (null == current) {
                return null;
            }
        }
        return current;
    }

    /**
     * 插入
     * 暂时不考虑 重复节点问题
     * @param key
     * @param data
     */
    public void insert(int key, double data) {
        Node newNode = new Node();
        newNode.iData = key;
        newNode.dData = data;
        if (null == root) {
            root = newNode;// 根节点
        } else { // 插入叶子结点
            Node parent;
            Node current = root;
            while (true) {
                parent = current;
                if (key < current.iData) {
                    current = current.leftNode;
                    if (null == current) { // 左叶子节点
                        parent.leftNode = newNode;
                        return;
                    }
                } else {
                    current = current.rightNode;
                    if (null == current) { // 右叶子结点
                        parent.rightNode = newNode;
                        return;
                    }
                }
            }
        }
    }

    /**
     * 前序遍历
     * @param localNode
     */
    private void preOrder(Node localNode) {
        if (null != localNode) {
            System.out.println(localNode.iData);
            preOrder(localNode.leftNode);
            preOrder(localNode.rightNode);
        }
    }

    /**
     * 中序遍历
     * @param localNode
     */
    private void inOrder(Node localNode) {
        if (null != localNode) {
            inOrder(localNode.leftNode);
            System.out.println(localNode.iData);
            inOrder(localNode.rightNode);
        }
    }

    /**
     * 后序遍历
     * @param localNode
     */
    private void postOrder(Node localNode) {
        if (null != localNode) {
            postOrder(localNode.leftNode);
            postOrder(localNode.rightNode);
            System.out.println(localNode.iData);
        }
    }

    /**
     * 查找最小值
     * @return
     */
    public Node findMin(int key) {
        Node current = root;
        Node last = null;
        while (null != current) {
            last = current;
            current = current.leftNode;
        }
        return last;
    }

    /**
     * 查找最大值
     * @return
     */
    public Node findMax(int key) {
        Node current = root;
        Node last = null;
        while (null != current) {
            last = current;
            current = current.rightNode;
        }
        return last;
    }

    /**
     * 删除 （非常复杂） 如果删除操作不多可以在节点上加isDelete 状态位
     * 分三种情况考虑：
     *    1.该节点是叶子结点
     *    2.该节点有一个子节点
     *    3.该节点有两个子节点
     * @param：key
     */
    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftNode = true;// 用于查找被删除的节点是不是左子节点
        while (key != current.iData) { // 先找到要删除的节点
            parent = current;
            if (key < current.iData) {
                isLeftNode = true;
                current = current.leftNode;
            } else {
                isLeftNode = false;
                current = current.rightNode;
            }
        }
        if (null == current) { // 没找到
            return false;
        }
        /**
         *  找到节点后，先要检查它是不是真的没有子节点。如果没有子节点，还需要检查它是不是根。
         *  如果它是根的话，只需要把它置为null,这样就清空整棵树了。否则，把父节点的左子节点或右子节点置为null,
         *  断开父节点和那个要删除的节点的连接
         */
        if (current.leftNode == null && current.rightNode == null)  { // 删除没有子节点
            if (current == root) {
                root = null;
            } else if (isLeftNode) {
                parent.leftNode = null;
            } else {
                parent.rightNode = null;
            }
        } else if (current.rightNode == null) { // 删除只有一个子节点
            if (current == root) {
                root = current.leftNode;
            } else if (isLeftNode) {
                parent.leftNode = current.leftNode;
            } else {
                parent.rightNode = current.leftNode;
            }
        } else if (current.leftNode == null) { // 删除只有一个子节点
            if (current == root) {
                root = current.rightNode;
            } else if (isLeftNode) {
                parent.leftNode = current.rightNode;
            } else {
                parent.rightNode = current.rightNode;
            }
        } else { // 后继节点是删除节点的右节点  (*****具体过程看后继节点移动图片******)
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftNode) {
                parent.leftNode = successor;
            } else {
                parent.rightNode = successor;
            }
            successor.leftNode = current.leftNode;
        }
        return true;
    }

    /**
     * 查找后继节点 （用于删除有两个子节点的）
     * 找后继就是找初始集合中大于关键值得最小以为
     *                  100
     *              50        80  （要删除的节点）
     *                    70      90
     *                         85（后继）95
     *       (*****具体过程看后继节点移动图片******)
     * @param:delNode
     * @return
     */
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightNode;
        while (null != current) { // 查找 最小值 就是找左子孙节点
            successorParent = successor;
            successor = current;
            current = current.leftNode;
        }
        if (successor != delNode.rightNode) { // 后继节点是删除节点的右节点的左子孙节点
            successorParent.leftNode = successor.rightNode;
            successor.rightNode = delNode.rightNode;
        }
        return successor;
    }

    /**
     * traverse 穿过
     * @param traverseType
     */
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1 :
                System.out.println("\n前序遍历");
                preOrder(root);
                break;
            case 2 :
                System.out.println("\n中序遍历");
                inOrder(root);
                break;
            case 3 :
                System.out.println("\n后序遍历");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    /**
     * 显示树
     */
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;// 空格
        boolean isRowEmpty = false; // 用来退出外层循环
        System.out.println("..........................................");
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }
            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (null != temp) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftNode);
                    localStack.push(temp.rightNode);
                    if (temp.leftNode != null || temp.rightNode != null) {
                        isRowEmpty = false;
                    } else {
                        System.out.print("--");
                        localStack.push(null);
                        localStack.push(null);
                    }
                    for (int j = 0; j < nBlanks * 2 - 2; j++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("..........................................");
    }
}
