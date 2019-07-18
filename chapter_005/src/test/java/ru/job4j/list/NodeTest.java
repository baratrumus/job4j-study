package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NodeTest {

    Node<Integer> first = new Node(1);
    Node<Integer> two = new Node(2);
    Node<Integer> third = new Node(3);
    Node<Integer> four = new Node(4);



    @Test
    public void whenPopThenPopped() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(first.hasCycle(first), is(true));
    }
}
