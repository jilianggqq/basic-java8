package edu.gqq.ayuan.week5;

import edu.gqq.ayuan.IntUtil;

/**
 * File Name: SlistSort.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */
/*
 * To compile you require: IntUtil.java RandomInt.java Slist2.java SlistSort.java
 */

public class SlistSort {

    private Slist2 s;
    private int[] work;
    private boolean show;
    private static IntUtil u = new IntUtil();
    //You cannot add any variables to this class

    SlistSort(Slist2 s, String method, int[] work, boolean show) {
        this.s = s;
        this.work = work;
        this.show = show;
        if (method == "merge_sort") {
            merge_sort();
        } else if (method == "quick_sort") {
            quick_sort();
        }
    }

    private void increment_numCompare(int x) {
        work[0] = work[0] + x;
    }

    private void increment_numSwap(int x) {
        work[1] = work[1] + x;
    }

    private void increment_Recursion(int x) {
        work[2] = work[2] + x;
    }


    /************************************************************
     WRITE YOUR CODE BELOW
     YOU CAN HAVE ANY NUMBER OF PRIVATE METHODS AND PRIVATE CLASS
     *************************************************************/
    private void merge_sort() {
        if (show) {
            s.Pln("Before sort s = ");
        }
        //WRITE CODE BELOW
        //YOU CAN HAVE ANY NUMBER OF PRIVATE METHODS
        s.first = mergeSort(s.first);
        if (show) {
            s.Pln("After  sort s = ");
        }
    }

    // Function to merge sort
    private Node2 mergeSort(Node2 head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node2 mid = findMid(head);
        Node2 head2 = mid.next;
        mid.next = null;
        Node2 newHead1 = mergeSort(head);
        Node2 newHead2 = mergeSort(head2);
        Node2 finalHead = merge(newHead1, newHead2);

        return finalHead;
    }

    // Function to merge two linked lists
    private Node2 merge(Node2 head1, Node2 head2) {
        Node2 merged = new Node2(-1, -1);
        Node2 temp = merged;

        // While head1 is not null and head2
        // is not null
        while (head1 != null && head2 != null) {
            if (head1.d <= head2.d) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        // While head1 is not null
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }

        // While head2 is not null
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return merged.next;
    }

    private Node2 findMid(Node2 head) {
        Node2 slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void quick_sort() {
        if (show) {
            s.Pln("Before sort s = ");
        }
        //WRITE CODE BELOW
        //YOU CAN HAVE ANY NUMBER OF PRIVATE METHODS
        if (s == null || s.first == null) {
            return;
        }
        Node2 first = s.first;
        Node2 end = first;
        while (end.next != null) {
            end = end.next;
        }
        quickSort(first, end);

        if (show) {
            s.Pln("After  sort s = ");
        }
    }

    private Node2 paritionLast(Node2 start, Node2 end) {
        if (start == end || start == null || end == null) {
            return start;
        }

        Node2 pivot_prev = start;
        Node2 curr = start;
        int pivot = end.d;

        while (start != end) {
            if (start.d <= pivot) {
                pivot_prev = curr;
                int temp = curr.d;
                curr.d = start.d;
                start.d = temp;
                curr = curr.next;
            }
            start = start.next;
        }

        int temp = curr.d;
        curr.d = pivot;
        end.d = temp;

        return pivot_prev;
    }

    private void quickSort(Node2 start, Node2 end) {
        if (start == null || start == end || start == end.next) {
            return;
        }

        // split list and partition recurse
        Node2 pivot_prev = paritionLast(start, end);
        quickSort(start, pivot_prev);

        if (pivot_prev != null && pivot_prev == start) {
            quickSort(pivot_prev.next, end);
        } else if (pivot_prev != null && pivot_prev.next != null) {
            quickSort(pivot_prev.next.next, end);
        }
    }


    public static void main(String[] args) {
        System.out.println("SlistSort STARTS");

        System.out.println("SlistSort ENDS");
    }

}
	