package com.epam;

import com.epam.ds.AVLTree;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class AVLTreeTest {

    @Test
    public void avlTreeInsertionTest() {
        AVLTree tree = new AVLTree();
        Set<Integer> numSet = this.getRandomNumbersSet(30000, 50000);
        Iterator<Integer> iterator = numSet.iterator();
        AVLTree.Node root = null;
        while (iterator.hasNext()) {
            int value = iterator.next();

            root = tree.insert(root, value);
        }
        //tree.print(root);
        tree.preOrder(root);

    }

    @Test
    public void avlTree20NumbersInsertionTest() {
        AVLTree tree = new AVLTree();
        Set<Integer> numSet = this.getRandomNumbersSet(30, 200);
        System.out.println(numSet);
        Iterator<Integer> iterator = numSet.iterator();
        AVLTree.Node root = null;
        while (iterator.hasNext()) {
            int value = iterator.next();

            root = tree.insert(root, value);
        }
        //tree.print(root);
        System.out.printf("[");
        tree.preOrder(root);
        System.out.printf("]");
        System.out.println();
        System.out.printf("[");
        tree.inOrder(root);
        System.out.printf("]");
        System.out.println();
        System.out.println(tree.height(root));

    }



    @Test
    public void avlTreeHeightTest() {
        AVLTree tree = new AVLTree();
        Set<Integer> numSet = this.getRandomNumbersSet(100000, 300000);
        Iterator<Integer> iterator = numSet.iterator();
        AVLTree.Node root = null;
        while (iterator.hasNext()) {
            int value = iterator.next();

            root = tree.insert(root, value);
        }
        System.out.println(tree.height(root));
    }

    public Set<Integer> getRandomNumbersSet(int size_num, int range) {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>(size_num);

        while(set.size() < size_num) {
            set.add(random.nextInt(range));
        }
        return set;
    }

}
