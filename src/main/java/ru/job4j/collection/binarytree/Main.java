package ru.job4j.collection.binarytree;

public class Main {
    public static void main(String[] args) {
        /*
        ExampleTree exampleTree = new ExampleTree();
        System.out.println(exampleTree);
        */

        /*
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7};
        for (int i : array) {
            bst.put(i);
        }
        System.out.println(bst);
        System.out.println(bst.remove(10));
        System.out.println("После удаления узла 10 :");
        System.out.println(bst);
        */

        ///*
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = new int[]{4, 2, 6, 3, 5, 7, 1};
        for (int i : array) {
            bst.put(i);
        }
        System.out.println(bst);
        System.out.println(bst.remove(2));
        System.out.println("После удаления узла 2:");
        System.out.println(bst);
       //*/
    }
}
