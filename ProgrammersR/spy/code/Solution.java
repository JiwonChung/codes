import java.util.stream.LongStream;

class Solution {

    public static void main(String[] args) {
        long[] array = { 1, 2, 3, 4, 5 };

        long r = 3;
        long n = array.length;
        long C = 1;
        for (long i = r; i >= 1; i--) {
            C *= (n - i + 1);
        }
        r = factorialUsingStreams((int)r);
        System.out.println(C / r);
    }

    static long factorialUsingStreams(int n) {
        return LongStream.rangeClosed(1, n).reduce(1, (long x, long y) -> x * y);
    }

}