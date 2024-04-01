import java.util.*;
package DSA;

import java.util.Scanner;

public class MinHeap {
	private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1]; // Adding 1 to simplify index calculations
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public void insert(int value) {
        if (size >= capacity) {
            System.out.println("Heap is full");
            return;
        }

        size++;
        heap[size] = value;
        int current = size;

        while (current > 1 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return -1;
        }

        int min = heap[1];
        heap[1] = heap[size];
        size--;
        minHeapify(1);

        return min;
    }

    private void minHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left <= size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right <= size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public void printHeap() {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print("Parent: " + heap[i] + " Left child: " + heap[2 * i]);

            if (2 * i + 1 <= size) {
                System.out.print(" Right child: " + heap[2 * i + 1]);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter number of elements to be added:");
    	int num=sc.nextInt();
        MinHeap minHeap = new MinHeap(num);
        System.out.println("Enter "+num+ " numbers:");
        for(int i=0;i<num;i++)
        {
        	int n=sc.nextInt();
            minHeap.insert(n);
        }
        System.out.println("Heap:");
        minHeap.printHeap();

        System.out.println("Extracting min element: " + minHeap.extractMin());
        System.out.println("Heap after extraction:");
        minHeap.printHeap();
    }
}

