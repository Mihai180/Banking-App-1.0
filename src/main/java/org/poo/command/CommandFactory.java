package org.poo.command;

import org.poo.fileio.CommandInput;
import org.poo.service.*;
import org.poo.service.AccountService;

public class CommandFactory {
    public Command createCommand(CommandInput input) {
        String commandName = input.getCommand();

        switch (commandName) {
            case "printUsers":
                return new PrintUsersCommand(commandName, input.getTimestamp());
            case "addAccount":
                return new AddAccountCommand(commandName, input.getTimestamp(), input.getEmail(), input.getAccountType(), input.getCurrency(), input.getInterestRate());
            case "addFunds":
                return new AddFundsCommand(commandName, input.getTimestamp(), input.getAccount(), input.getAmount());
            case "createCard":
            case "createOneTimeCard":
                return new CreateCardCommand(commandName, input.getTimestamp(), input.getAccount(), input.getEmail());
            case "deleteAccount":
                return new DeleteAccountCommand(commandName, input.getTimestamp(), input.getAccount(), input.getEmail());

            default:
                return new NotImplementedCommand(commandName, input.getTimestamp());
        }
    }
}
