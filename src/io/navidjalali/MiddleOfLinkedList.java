package io.navidjalali;

/*
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.
*/

import io.navidjalali.datastructures.ListNode;

public class MiddleOfLinkedList {
  public ListNode middleNode(ListNode head) {
    if (head == null)
      return null;

    ListNode slow = head;
    ListNode fast = head;

    while (slow.next != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    if (fast.next != null)
      slow = slow.next;

    return slow;

  }
}
