package ru.job4j.newcoll.fortaskavl;

import java.util.*;
import java.util.stream.Collectors;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean contains(T key) {
        return !Objects.isNull(find(root, key));
    }

    public boolean contains(Node node, T key) {
        return !Objects.isNull(find(node, key));
    }

    private Node find(Node node, T key) {
        Node foundNode = null;
        boolean result = false;
        boolean error = false;
        while (!result && !error) {
            if (Objects.isNull(node)) {
                result = true;
            } else {
                if (key.equals(node.key)) {
                    foundNode = node;
                    result = true;
                } else {
                    if (key.compareTo(node.key) < 0) {
                        node = node.left;
                    } else if (key.compareTo(node.key) > 0) {
                        node = node.right;
                    } else {
                        error = true;
                    }
                }
            }
        }
        return foundNode;
    }

    public boolean put(T key, V value) {
        return insert(key, value);
    }

    public boolean insert(T keyValue, V value) {
        boolean result = false;
        if (Objects.nonNull(keyValue) && !contains(root, keyValue)) {
            root = insert(root, keyValue, value);
            result = true;
        } else if (Objects.nonNull(keyValue) && contains(root, keyValue)) {
            Node oldNode = find(root, keyValue);
            oldNode.value = value;
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T keyValue, V value) {
        Node result = new Node(keyValue, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = keyValue.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = insert(node.left, keyValue, value);
            } else {
                node.right = insert(node.right, keyValue, value);
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(root, key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
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

    public V get(T key) {
        return keySet().contains(key) ? find(root, key).value : null;
    }

    public Set<T> keySet() {
        /* выполнить обход дерева, чтобы получить все ключи */
        return inSymmetricalOrder().stream().collect(Collectors.toSet());
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (T key : keySet()) {
            values.add(find(root, key).value);
        }
        return values;
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

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
