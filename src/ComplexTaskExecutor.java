import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int poolSize;

    public ComplexTaskExecutor(int poolSize) {
        this.poolSize = poolSize;
    }

    public void executeTasks(int numberOfTasks) {
        String callerName = Thread.currentThread().getName();
        System.out.println(callerName + " starts computing " + numberOfTasks + " tasks");

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<String> results = Collections.synchronizedList(new ArrayList<>());

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("\n" + callerName + ": All tasks complete. Summing results");
            System.out.println("Total results: " + results.size());
            for (String result : results) {
                System.out.println("Result: " + result);
            }
            System.out.println("Summing comleted\n");
        });

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            Future<?> future = executor.submit(() -> {
                ComplexTask task = new ComplexTask(taskId, callerName);
                String result = task.execute();
                results.add(result);

                try {
                    System.out.println(callerName + " - task " + taskId + " wait for barrier.");
                    barrier.await(10, TimeUnit.SECONDS);
                    System.out.println(callerName + " - task " + taskId + " went through barrier.");
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    System.out.println(callerName + " - task " + taskId + " error while waiting: " + e.getMessage());
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error while computing tasks: " + e.getMessage());
            }
        }

        executor.shutdown();
        System.out.println(callerName + " completed computing task\n");
    }
}
