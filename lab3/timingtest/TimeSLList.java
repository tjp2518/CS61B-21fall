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
        int[] sizeofNum = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        opCounts.addLast(10000);  //set M
        //SLList<?>[] list = new SLList<?>[sizeofNum.length];
        for(int i = 0; i<sizeofNum.length; i++){
           SLList<Integer> list = new SLList<>();
            for(int j = 0; j<sizeofNum[i]; j++){
                list.addFirst(2);
            }
            Ns.addLast(sizeofNum[i]);
            opCounts.addLast(10000);
            Stopwatch sw = new Stopwatch();
            for(int k = 0; k<opCounts.get(i); k++){
                list.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);

        }
        printTimingTable(Ns, times, opCounts);
    }

}
