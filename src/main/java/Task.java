public abstract class Task {
    protected String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        this.isDone = true;
    }

    public void markNotDone(){
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public abstract String getType();
    public abstract String toDisplayString();
}
