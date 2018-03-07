// Bailey Waldorf
// Calculator
// 3/7/18

import java.util.*;
import java.io.*;
import java.util.Scanner;

class Node {
     public int number;
     public int distance;
     // setting all values in constructor
     public Node(int number, int distance) {
         this.number = number;
         this.distance = distance;
    }
}

public class calc {
     public static int MAX_NUM = 999999;

     public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);

          int numCases = sc.nextInt();
          for(int i = 0; i < numCases; i++) {

               // reading input
               int a = sc.nextInt(); // add
               int b = sc.nextInt(); // multiply
               int c = sc.nextInt(); // divide
               int target = sc.nextInt();
               int distance = 1, numA, numB, numC;

               // making queue and boolean array to hold unused and used numbers, respectively
               Queue<Node> q = new LinkedList<Node>();
               boolean[] used = new boolean[MAX_NUM + 1];

               // adding default calculator number, 0, into queue and used array
               q.add(new Node(0,0));
               used[0] = true;

               // as long as there there are numbers in the queue, keep looking
               while(q.size() > 0) {

                    // remove number from head of queue
                    Node head = q.remove();

                    // find that number's sum, product, and quotient for each button
                    numA = add(head.number, a);
                    numB = multiply(head.number, b);
                    numC = divide(head.number, c);

                    // if one of these numbers equals the target, print the answer!
                    if(numA == target || numB == target || numC == target) {
                         System.out.println(head.distance + 1);
                         break;
                    }

                    // otherwise, enqueue those number if they haven't been used yet
                    if(!used[numA]) {
                         q.add(new Node(numA, head.distance + 1));
                         used[numA] = true;
                    }
                    if(!used[numB]) {
                         q.add(new Node(numB, head.distance + 1));
                         used[numB] = true;
                    }
                    if(!used[numC]) {
                         q.add(new Node(numC, head.distance + 1));
                         used[numC] = true;
                    }
               }
               // queue size is 0, no numbers reached the target...
               if(q.size() == 0) System.out.println(-1);
          }
          sc.close();
          return;
     }

     private static int add(int currNum, int a) {
          long newNum = currNum + a;
          if(newNum > MAX_NUM)
               newNum = newNum % 1000000;
          return (int)newNum;
     }

     private static int multiply(int currNum, int b) {
          long newNum = currNum * b;
          if(newNum > MAX_NUM)
               newNum = newNum % 1000000;
          return (int)newNum;
     }

     private static int divide(int currNum, int c) {
          long newNum = currNum / c;
          if(newNum > MAX_NUM)
               newNum = newNum % 1000000;
          return (int)newNum;
     }
}
