package org.poo.visitor.command;

import org.poo.command.ReportCommand;
import org.poo.command.AddInterestCommand;
import org.poo.command.SpendingsReportCommand;
import org.poo.command.SplitPaymentCommand;
import org.poo.command.SetAliasCommand;
import org.poo.command.SendMoneyCommand;
import org.poo.command.PrintTransactionsCommand;
import org.poo.command.PayOnlineCommand;
import org.poo.command.DeleteCardCommand;
import org.poo.command.PrintUsersCommand;
import org.poo.command.SetMinBalanceCommand;
import org.poo.command.ChangeInterestRateCommand;
import org.poo.command.CheckCardStatusCommand;
import org.poo.command.DeleteAccountCommand;
import org.poo.command.CreateCardCommand;
import org.poo.command.AddFundsCommand;
import org.poo.command.NotImplementedCommand;
import org.poo.command.AddAccountCommand;

public interface CommandVisitor {
    /**
     *
     * @param command
     */
    void visit(PrintUsersCommand command);

    /**
     *
     * @param command
     */
    void visit(AddAccountCommand command);

    /**
     *
     * @param command
     */
    void visit(AddFundsCommand command);

    /**
     *
     * @param command
     */
    void visit(CreateCardCommand command);

    /**
     *
     * @param command
     */
    void visit(NotImplementedCommand command);

    /**
     *
     * @param command
     */
    void visit(DeleteAccountCommand command);

    /**
     *
     * @param command
     */
    void visit(DeleteCardCommand command);

    /**
     *
     * @param command
     */
    void visit(SetMinBalanceCommand command);

    /**
     *
     * @param command
     */
    void visit(PayOnlineCommand command);

    /**
     *
     * @param command
     */
    void visit(SendMoneyCommand command);

    /**
     *
     * @param command
     */
    void visit(SetAliasCommand command);

    /**
     *
     * @param command
     */
    void visit(PrintTransactionsCommand command);

    /**
     *
     * @param command
     */
    void visit(CheckCardStatusCommand command);

    /**
     *
     * @param command
     */
    void visit(ChangeInterestRateCommand command);

    /**
     *
     * @param command
     */
    void visit(SplitPaymentCommand command);

    /**
     *
     * @param command
     */
    void visit(ReportCommand command);

    /**
     *
     * @param command
     */
    void visit(SpendingsReportCommand command);

    /**
     *
     * @param command
     */
    void visit(AddInterestCommand command);
}
