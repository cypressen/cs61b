package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        Stopwatch sw = new Stopwatch();
        double startTime;
        double endTime;
        int M = 10000;
        for (int N = 1000; N <= 128000; N *= 2) {
            SLList<Integer> arr = new SLList<>();
            opCounts.addLast(M);
            Ns.addLast(N);
            for (int j = 0; j < N; j += 1) {
                arr.addLast(1);
            }
            startTime = sw.elapsedTime();

            for(int j = 0 ; j < M;j+=1){
                arr.getLast();
            }
            endTime = sw.elapsedTime();

            times.addLast(endTime - startTime);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
