package io.navidjalali;

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.
*/

import io.navidjalali.datastructures.ListNode;

class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    ListNode temp = null;
    while (current != null) {
      temp = current.next;
      current.next = prev;
      prev = current;
      current = temp;
    }
    return prev;
  }
}
