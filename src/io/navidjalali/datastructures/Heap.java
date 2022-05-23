package io.navidjalali.datastructures;

import java.util.*;

public class Heap<A> {
  private A[] heap;
  private int size;
  final private Comparator<A> comparator;

  public Heap(int capacity, Comparator<A> cmp) {
    heap = (A[]) new Object[capacity];
    size = 0;
    comparator = cmp;
  }

  private int leftChildIndex(int index) {
    return 2 * index + 1;
  }

  private int rightChildIndex(int index) {
    return 2 * index + 2;
  }

  private int parentIndex(int index) {
    return (index - 1) / 2;
  }

  private boolean hasLeftChild(int index) {
    return leftChildIndex(index) < size;
  }

  private boolean hasRightChild(int index) {
    return rightChildIndex(index) < size;
  }

  private boolean hasParent(int index) {
    return parentIndex(index) >= 0;
  }

  private A unsafeLeftChild(int index) {
    return heap[leftChildIndex(index)];
  }

  private A unsafeRightChild(int index) {
    return heap[rightChildIndex(index)];
  }

  private A unsafeParent(int index) {
    return heap[parentIndex(index)];
  }

  public Optional<A> leftChild(int index) {
    if (hasLeftChild(index)) {
      return Optional.of(unsafeLeftChild(index));
    } else {
      return Optional.empty();
    }
  }

  public Optional<A> rightChild(int index) {
    if (hasRightChild(index)) {
      return Optional.of(unsafeRightChild(index));
    } else {
      return Optional.empty();
    }
  }

  public Optional<A> parent(int index) {
    if (hasParent(index)) {
      return Optional.of(unsafeParent(index));
    } else {
      return Optional.empty();
    }
  }

  private void swap(int index1, int index2) {
    A temp = heap[index1];
    heap[index1] = heap[index2];
    heap[index2] = temp;
  }

  private void capacityCheck() {
    if (size == heap.length) {
      A[] newHeap = (A[]) new Object[size * 2];
      System.arraycopy(heap, 0, newHeap, 0, size);
      heap = newHeap;
    } else if (4 * size <= heap.length && size > 0) {
      A[] newHeap = (A[]) new Object[2 * size];
      System.arraycopy(heap, 0, newHeap, 0, size);
      heap = newHeap;
    }
  }

  private A unsafePeek() {
    if (size == 0) {
      throw new IllegalStateException("Heap is empty");
    }
    return heap[0];
  }

  public Optional<A> peek() {
    if (size == 0) {
      return Optional.empty();
    } else {
      return Optional.of(unsafePeek());
    }
  }

  private A unsafeTake() {
    if (size == 0) {
      throw new IllegalStateException("Heap is empty");
    }

    A item = heap[0];
    A last = heap[size - 1];
    heap[0] = last;
    heap[size - 1] = null;
    size--;
    heapifyDown();
    capacityCheck();
    return item;
  }

  public Optional<A> take() {
    if (size == 0) {
      return Optional.empty();
    } else {
      return Optional.of(unsafeTake());
    }
  }

  public void offer(A item) {
    capacityCheck();
    heap[size] = item;
    size++;
    heapifyUp();
  }

  private void heapifyUp() {
    int index = size - 1;
    while (hasParent(index) && comparator.compare(unsafeParent(index), heap[index]) > 0) {
      swap(parentIndex(index), index);
      index = parentIndex(index);
    }
  }

  private void heapifyDown() {
    int index = 0;
    while (hasLeftChild(index)) {
      int smallChildIndex = leftChildIndex(index);
      if (hasRightChild(index) && comparator.compare(unsafeLeftChild(index), unsafeRightChild(index)) > 0) {
        smallChildIndex = rightChildIndex(index);
      }

      if (comparator.compare(heap[index], heap[smallChildIndex]) < 0) {
        break;
      } else {
        swap(index, smallChildIndex);
        index = smallChildIndex;
      }
    }
  }

  public void clear() {
    size = 0;
    heap = (A[]) new Object[16];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(A a) {
    return Arrays.asList(heap).contains(a);
  }

  public boolean nonEmpty() {
    return size > 0;
  }

  public List<A> sortedList() {
    List<A> list = new ArrayList<>();
    while (nonEmpty()) {
      list.add(unsafeTake());
    }
    return list;
  }

  public String toString() {
    return "Heap(" + Arrays.toString(heap) + ")";
  }

  public static <A> Heap<A> heapify(A[] array, Comparator<A> comparator) {
    Heap<A> heap = new Heap<>(array.length * 2, comparator);
    for (A a : array) {
      heap.offer(a);
    }
    return heap;
  }

  public static <A> Heap<A> heapify(List<A> list, Comparator<A> comparator) {
    Heap<A> heap = new Heap<>(list.size() * 2, comparator);
    for (A a : list) {
      heap.offer(a);
    }
    return heap;
  }

  public Heap<A> reversed() {
    Heap<A> heap = new Heap<>(size, comparator.reversed());
    while (nonEmpty()) {
      heap.offer(unsafeTake());
    }
    return heap;
  }
}
