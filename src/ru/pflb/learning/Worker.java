package ru.pflb.learning;

public class Worker implements Runnable {

    private final int[] array;
    private final int start;
    private final int end;
    private int min = Integer.MAX_VALUE;

    /**
     *
     * @param array array of ints
     * @param start start index of interval to find min value
     * @param end end index of interval to find min value
     */
    public Worker(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        if (array == null) return;
        for (int i = start; i < end; ++i) {
            if (array[i] < min) {
                min = array[i];
            }
        }
    }

    /**
     *
     * @return min value found in array
     */
    public int getMin() {
        return min;
    }
}
