package edu.gqq.algorithm;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    static class QElem {

        int val;
        int dist;
    }


    /*
     * Complete the 'quickestWayUp' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY ladders
     *  2. 2D_INTEGER_ARRAY snakes
     */

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        Map<Integer, Integer> parents = new HashMap<>();

        // Write your code here
        int[] moves = new int[101];
        for (List<Integer> ladder : ladders) {
            moves[ladder.get(0)] = ladder.get(1);
        }
        for (List<Integer> snake : snakes) {
            moves[snake.get(0)] = snake.get(1);
        }

        QElem qe = new QElem();
        qe.val = 1;
        qe.dist = 0;
        Queue<QElem> queue = new LinkedList<>();
        queue.add(qe);
        boolean[] visited = new boolean[101];
        while (!queue.isEmpty()) {
            qe = queue.remove();
            int v = qe.val;
            if (v == 100) {
                break;
            }

            for (int j = v + 1; j <= v + 6 && j < 101; j++) {
                if (!visited[j]) {
                    visited[j] = true;
                    QElem ele = new QElem();
                    ele.dist = qe.dist + 1;

                    if (moves[j] == 0) {
                        ele.val = j;
                    } else {
                        ele.val = moves[j];
                    }
                    if (!parents.containsKey(ele.val)) {
                        parents.put(ele.val, v);
                    }
                    queue.add(ele);
                }
            }
        }

        if (qe.val == 100) {
            // System.out.println(parents);
            printPath(parents);
        }
        return qe.val == 100 ? qe.dist : -1;
    }

    /*
     * If it's needed, we can print path for the solution.
     */
    public static void printPath(Map<Integer, Integer> parents) {
        List<Integer> path = new ArrayList<>();
        int target = 100;
        path.add(target);
        while (target != 1 && parents.get(target) != null) {
            target = parents.get(target);
            path.add(0, target);
        }
        System.out.println(path);
    }

}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> ladders = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        ladders.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch(IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int m = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> snakes = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        snakes.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch(IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = Result.quickestWayUp(ladders, snakes);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

