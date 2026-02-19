package zack.command;

import zack.exception.ZackException;
import zack.model.Model;

//I asked chatgpt how to improve my code, and it suggests me to make the command package,
//here is the conversation link: https://chatgpt.com/share/69941c4b-41f8-800e-8f72-d9e1145b0f65
//and I am also inspired by https://github.com/NUS-CS2103-AY2526-S2/ip/pull/367,
//but all the code below are implemented by myself.

/**
 * Represents an executable command in the Zack application.
 */
public interface Command {
    /**
     * Executes this command using the given model.
     *
     * @param model The model to execute this command on.
     * @return The result of executing the command.
     * @throws ZackException If an error occurs during command execution.
     */
    CommandResult execute(Model model) throws ZackException;
}
