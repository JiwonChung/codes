package Fibonacci;


public class Main {
    // public static void main(String[] args) {
    //     long beforeTime = System.currentTimeMillis();
    //     System.out.println(fibonacci1(40));
    //     long afterTime = System.currentTimeMillis();
    //     System.out.println("재귀함수가 걸린시간: " + (long)(afterTime - beforeTime));

    //     beforeTime = System.currentTimeMillis();
    //     System.out.println(fibonacci2(40));
    //     afterTime = System.currentTimeMillis();
    //     System.out.println("다프 걸린시간: " + (long)(afterTime - beforeTime));        
    public static void main(String[] args) {
        System.out.println(fibonacciTopDown(100));        
    }    

    public static int fibonacci1 (int numb) {
        if (numb == 1) {
            return 1;
        }
        if (numb == 2) {
            return 1;
        }
        return fibonacci1(numb - 1) + fibonacci1(numb - 2);
    }

    
    
    public static int fibonacci2 (int numb) {
        int[] array = new int[numb];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < numb; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[numb - 1];
    }

    static long[] dpTable = new long[100];
        
    public static long fibonacciTopDown (long numb) {
        if (numb == 1 || numb == 2) {
            return 1;
        }
        if (dpTable[(int)numb] != 0) {
            return dpTable[(int)numb];
        }
        dpTable[(int)numb] = fibonacciTopDown(numb - 1) + fibonacciTopDown(numb - 2);
        return dpTable[(int)numb];
    }
}
