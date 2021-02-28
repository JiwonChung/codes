import java.util.stream.LongStream;

class Solution {

    public static void main(String[] args) {

        long[] array = new long[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        for (long r = 1; r < 100; r++) {
            // long[] array = { 1, 2, 3, 4, 5 };

            
            long n = array.length;
            long C = 1;
            for (long i = r; i >= 1; i--) {
                C *= (n - i + 1);
            }
            r = factorialUsingStreams((int)r);
            System.out.println(C / r);
            System.out.println("10");
        } 

    }
    static long factorialUsingStreams(int n) {
        return LongStream.rangeClosed(1, n)
            .reduce(1, (long x, long y) -> x * y);
    }

}