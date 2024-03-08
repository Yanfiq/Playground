package PPBO_12_L0122114;

/*
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

class CountPrimesSingleThread {
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int countPrimesInRange(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

}

class PrimeCounterThread extends Thread {
    private int start;
    private int end;
    private int count;

    public PrimeCounterThread(int start, int end) {
        this.start = start;
        this.end = end;
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class CountPrimesMultiThread {
    public static int countPrimesInRange(int startRange, int endRange) {
        PrimeCounterThread[] threads = new PrimeCounterThread[5];
        int segment = (endRange - startRange + 1) / 5;
        int count = 0;
        int currentStart = startRange;

        for (int i = 0; i < 5; i++) {
            int currentEnd = i == 4 ? endRange : currentStart + segment - 1;
            threads[i] = new PrimeCounterThread(currentStart, currentEnd);
            currentStart = currentEnd + 1;
            threads[i].start();
        }

        for (PrimeCounterThread thread : threads) {
            try {
                thread.join();
                count += thread.getCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}

public class PPBO_12_L0122114 {
    public static void main(String[] args) {
        int startRange = 1;
        int endRange = 1000000;

        long start_1 = System.currentTimeMillis();
        int primeCount_single = CountPrimesSingleThread.countPrimesInRange(startRange, endRange);
        long end_1 = System.currentTimeMillis();

        long start_2 = System.currentTimeMillis();
        int primeCount_multi = CountPrimesMultiThread.countPrimesInRange(startRange, endRange);
        long end_2 = System.currentTimeMillis();
        
        System.out.println("Jumlah bilangan prima dari " + startRange + " sampai " + endRange);
        System.out.println("Singlethread = "+primeCount_single+", time = "+(end_1-start_1)+" millis");
        System.out.println("Multithread = "+primeCount_multi+", time = "+(end_2-start_2)+" millis");
    }
}

/*
 * multithreading dapat lebih cepat dibanding singlethreading
 * 
 * hasil
 * Jumlah bilangan prima dari 1 sampai 1000
 * Singlethread = 168, time = 2 millis
 * Multithread = 168, time = 3 millis
 * 
 * Jumlah bilangan prima dari 1 sampai 10000
 * Singlethread = 1229, time = 1 millis
 * Multithread = 1229, time = 5 millis
 * 
 * Jumlah bilangan prima dari 1 sampai 100000
 * Singlethread = 9592, time = 8 millis
 * Multithread = 9592, time = 7 millis
 * 
 * Jumlah bilangan prima dari 1 sampai 1000000
 * Singlethread = 78498, time = 129 millis
 * Multithread = 78498, time = 37 millis
 */