package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        boolean result = false;
        Node child = null;
        while (!result) {
            if (key.compareTo(node.key) < 0) {
                child = node.left;
            }
            else if (key.compareTo(node.key) > 0) {
                child = node.right;
            }
            if (Objects.isNull(child)) {
                child = new Node(key);

                if (key.compareTo(node.key) < 0) {
                    node.left = child;
                }
                else if (key.compareTo(node.key) > 0) {
                    node.right = child;
                }
                result = true;

            } else {
                node = child;
                //child = child.left;
            }
        }
        return result;
    }

    public boolean contains(T key) {
        return !Objects.isNull(find(root, key));
    }

    private Node find(Node node, T key) {
        Node foundNode = null;
        boolean result = false;
        while (!result) {
            if (Objects.isNull(node)) {
                result = true;
            } else {
                if (key.equals(node.key)) {
                    foundNode = node;
                    result = true;
                } else {
                    if (key.compareTo(node.key) < 0) {
                        node = node.left;
                    }
                    else if (key.compareTo(node.key) > 0) {
                        node = node.right;
                    }
                }
            }
        }
        return foundNode;
    }

    public boolean remove(T key) {
        /* Метод будет реализован в следующих уроках */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPreOrder(node, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPostOrder(node, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        while (!Objects.isNull(node.left)) {
            node = node.left;
        }
        return node;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        while (!Objects.isNull(node) && !Objects.isNull(node.right)) {
            node = node.right;
        }
        return node;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}
