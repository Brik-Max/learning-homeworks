
public class ComplexTask {
    private final int taskId;
    private final String callerName;

    public ComplexTask(int taskId, String callerName) {
        this.taskId = taskId;
        this.callerName = callerName;
    }

    public String execute() {
        try {
            Thread.sleep(100 + (long)(Math.random() * 400));
            System.out.println(callerName + " - task " + taskId + " completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return callerName + " - result of task " + taskId;
    }
}
