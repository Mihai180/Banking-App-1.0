package org.poo.command;

import org.poo.fileio.CommandInput;

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
            case "deleteCard":
                return new DeleteCardCommand(input.getCardNumber(), input.getEmail());
            case "setMinimumBalance":
                return new SetMinBalanceCommand(input.getAccount(), input.getMinBalance());
            case "payOnline":
                return new PayOnlineCommand(input.getCardNumber(), input.getAmount(), input.getCurrency(), input.getTimestamp(), input.getDescription(), input.getCommerciant(), input.getEmail());
            default:
                return new NotImplementedCommand(commandName, input.getTimestamp());
        }
    }
}
