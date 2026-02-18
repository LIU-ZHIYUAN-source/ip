package zack.command;

//I asked chatgpt how to improve my code, and it suggest me to make the command package,
//here is the conversation link: https://chatgpt.com/share/69941c4b-41f8-800e-8f72-d9e1145b0f65
//and I am also inspired by https://github.com/NUS-CS2103-AY2526-S2/ip/pull/367,
//but all the code below are implemented by myself.

/**
 * Represents the outcome of executing a command.
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean shouldExit;

    /**
     * Creates a CommandResult with feedback only.
     *
     * @param feedbackToUser String message to show to user
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false);
    }

    /**
     * Creates a CommandResult with feedback and exit flag.
     *
     * @param feedbackToUser String message to show to user
     * @param shouldExit     whether the application should exit
     */
    public CommandResult(String feedbackToUser, boolean shouldExit) {
        this.feedbackToUser = feedbackToUser;
        this.shouldExit = shouldExit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean shouldExit() {
        return shouldExit;
    }
}
