package ru.pflb.learning;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final long STREAM_SIZE = 100_000;

    public static final int[] array = new Random().ints(STREAM_SIZE).toArray();

    public static void main(String[] args) throws InterruptedException {
        int min = findMin(array, 4);
        assert min == Arrays.stream(array).min().getAsInt();
        System.out.printf("Min value is: %d", min);
    }

    /**
     *
     * @param array array of numbers
     * @param nThreads number of threads
     * @return minimum value in the array
     */
    public static int findMin(int[] array, int nThreads) throws InterruptedException {
        Worker[] workers = new Worker[nThreads];
        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads; ++i) {
            workers[i] = new Worker(array, i * array.length / nThreads, (i + 1) * array.length / nThreads);
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int min = Integer.MAX_VALUE;
        for (Worker worker : workers) {
            if (worker.getMin() < min) {
                min = worker.getMin();
            }
        }

        return min;
    }
}

