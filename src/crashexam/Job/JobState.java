package crashexam.Job;

public enum JobState {
    PRINTING(1),
    WAITING(2),
    DONE(3);

    private int jobState;

    private JobState(int jobState) {
        this.jobState = jobState;
    }

    public int getJobState() {
        return jobState;
    }
}