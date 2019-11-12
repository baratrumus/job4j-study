package ru.job4j.collections;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static java.util.Collections.*;


public class Deque1 {

    //Упражнение  12
    public void operate() {
        Deque<String> states = new ArrayDeque<String>();

        states.add("Germany");  //to tail
        states.add("France");
        //Если объявлен Deque то pop  push работают с ГОЛОВОЙ
        states.push("UK");      // to head
        states.offerLast("Norway"); //to tail

        String sFirst = states.pop(); // remove from head
        String s = states.peek();   //take from head
        String sLast = states.peekLast(); //take from tail
        states.offer(sFirst);   //add to tail
        String s1 = states.pollLast(); // remove from tail

        while (states.peek() != null) {
            System.out.print(states.pop());
        }

 /*
        // Если объявлен Stack то push и  pop работают с ХВОСТОМ, т.е. вставляют на место последнего
          Stack<String> stack = new Stack<>();

        //Если объявлен Deque то pop  push работают с ГОЛОВОЙ,
        // т.е вставляют на место нулевого эл-та
        //Deque<String> stack = new ArrayDeque<String>();
        stack.push("first");
        stack.push("gg");
        stack.push("second");
        stack.push("third");

        System.out.println(stack); // [first, second, third]

        System.out.println(stack.pop()); // "third"  POP берет из хвоста и удаляет
        System.out.println(stack.pop()); // "second"

        System.out.println(stack.pop());
        System.out.println(stack.pop()); // "first"

        System.out.println(stack.pop()); // throws EmptyStackException
*/
    }





    //Упражнение 13  deque

 /*   Write a program that reads the input elements and outputs them in the reverse order.
    The first string contains the number of elements. Each line followed the first one contains an element.
   Sample Input:
           3
            1
            2
            3
   Sample Output:
           3
            2
            1
The first string contains the number of elements. Each line followed the first one contains an element.
*/

public  void operate13() {
    System.out.println("Упражнение 13");
    Scanner sc = new Scanner(System.in);
    //считать строку целиком.nextLine();    .next() считать лишь одно слово
    int count = Integer.parseInt(sc.next());

    // Вариант с листом
     /*   List<String> myList = new ArrayList<>();
       // while (sc.hasNext())
        for(int i=0;i<count;i++)
            myList.add(sc.next());
        collections.reverse(myList);
        for(String s: myList) {
            System.out.println(s);
    */

   // Вариант с deque
     Deque<Integer> dq = new ArrayDeque<>();
     while (sc.hasNextInt()) {
         dq.add(sc.nextInt());
     }


     while (!dq.isEmpty()) {
         System.out.println(dq.pollLast());
     }

}




 /*   Write a program that reads numbers and stores them to a deque. An even number should be added as the first element, an odd number - as the last. After, the program must output all elements from the first to the last.
    The first string contains the number of elements. Each line followed the first one contains an element.
    Sample Input:
            4
            1
            2
            3
            4
    Sample Output:
            4
            2
            1
            3
The first string contains the number of elements. Each line followed the first one contains an element.
*/

    public  void operate14() {
        System.out.println("Упражнение 14");
       Scanner sc = new Scanner(System.in);
         //считать строку целиком.nextLine();    .next() считать лишь одно слово
        int count = Integer.parseInt(sc.next());

        Deque<Integer> dq = new ArrayDeque<>();
        while (sc.hasNextInt()) {
            int elem = Integer.parseInt(sc.next());
            if ((elem % 2) == 0) {
                dq.addFirst(elem);
            } else {
                dq.addLast(elem);
            }
        }

        //вывод либо
        //dq.forEach(System.out::println);

        //либо
        for (Integer i : dq) {
            System.out.println(i);
        }
    }


//Given a string consisting of brackets, write a program to examine whether
// the pairs and the orders of "{", "}", "(", ")", "[", "]" are correct (balanced).
// For example, the program should print true for the string [()]{}{[()()]()}
// and false for ()[]}.

   // {[])}

public  void operate15() {
    System.out.println("Упражнение 15");
    Deque<Character> stack = new ArrayDeque<>();
    boolean state = true;

    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();

    for (Character c:str.toCharArray()) {
        if ("{[(".contains("" + c)) {
            stack.offerLast(c);
        } else if (c == '}') {
            if ((stack.isEmpty())  || (stack.pollLast() != '{')) {
                state = false;
            }
        } else if (c == ')') {
            if ((stack.isEmpty()) || (stack.pollLast() != '(')) {
                state = false;
            }
        } else if (c == ']') {
            if ((stack.isEmpty() || (stack.pollLast() != '['))) {
                state = false;
            }
        }
    }
    if (!(stack.isEmpty())) {
        state = false;
    }

    System.out.println(state);
}
}




