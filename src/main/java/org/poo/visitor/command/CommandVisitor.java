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

/**
 * Interfața CommandVisitor face parte din implementarea modelului de design Visitor
 * aplicat tipului de comandă. Aceasta definește o serie de metode visit pentru
 * fiecare tip specific de comandă, permițând astfel operațiuni diferite să fie executate
 * în funcție de tipul comenzii.
 */
public interface CommandVisitor {
    /**
     * Vizitează comanda PrintUsersCommand și execută operațiunea specifică acesteia
     * @param command este instanța PrintUsersCommand care trebuie procesată
     */
    void visit(PrintUsersCommand command);

    /**
     * Vizitează comanda AddAccountCommand și execută operațiunea specifică acesteia
     * @param command este instanța AddAccountCommand care trebuie procesată
     */
    void visit(AddAccountCommand command);

    /**
     * Vizitează comanda AddFundsCommand și execută operațiunea specifică acesteia
     * @param command este instanța AddFundsCommand care trebuie procesată
     */
    void visit(AddFundsCommand command);

    /**
     * Vizitează comanda CreateCardCommand și execută operațiunea specifică acesteia
     * @param command este instanța CreateCardCommand care trebuie procesată
     */
    void visit(CreateCardCommand command);

    /**
     *  Vizitează comanda NotImplementedCommand și execută operațiunea specifică acesteia
     * @param command este instanța NotImplementedCommand care trebuie procesată
     */
    void visit(NotImplementedCommand command);

    /**
     * Vizitează comanda DeleteAccountCommand și execută operațiunea specifică acesteia
     * @param command este instanța DeleteAccountCommand care trebuie procesată
     */
    void visit(DeleteAccountCommand command);

    /**
     * Vizitează comanda DeleteCardCommand și execută operațiunea specifică acesteia
     * @param command este instanța DeleteCardCommand care trebuie procesată
     */
    void visit(DeleteCardCommand command);

    /**
     * Vizitează comanda SetMinBalanceCommand și execută operațiunea specifică acesteia
     * @param command este instanța SetMinBalanceCommand care trebuie procesată
     */
    void visit(SetMinBalanceCommand command);

    /**
     * Vizitează comanda PayOnlineCommand și execută operațiunea specifică acesteia
     * @param command este instanța PayOnlineCommand care trebuie procesată
     */
    void visit(PayOnlineCommand command);

    /**
     * Vizitează comanda SendMoneyCommand și execută operațiunea specifică acesteia
     * @param command este instanța SendMoneyCommand care trebuie procesată
     */
    void visit(SendMoneyCommand command);

    /**
     * Vizitează comanda SetAliasCommand și execută operațiunea specifică acesteia
     * @param command este instanța SetAliasCommand care trebuie procesată
     */
    void visit(SetAliasCommand command);

    /**
     * Vizitează comanda PrintTransactionsCommand și execută operațiunea specifică acesteia
     * @param command este instanța PrintTransactionsCommand care trebuie procesată
     */
    void visit(PrintTransactionsCommand command);

    /**
     * Vizitează comanda CheckCardStatusCommand și execută operațiunea specifică acesteia
     * @param command este instanța CheckCardStatusCommand care trebuie procesată
     */
    void visit(CheckCardStatusCommand command);

    /**
     * Vizitează comanda ChangeInterestRateCommand și execută operațiunea specifică acesteia
     * @param command este instanța ChangeInterestRateCommand care trebuie procesată
     */
    void visit(ChangeInterestRateCommand command);

    /**
     * Vizitează comanda SplitPaymentCommand și execută operațiunea specifică acesteia
     * @param command este instanța SplitPaymentCommand care trebuie procesată
     */
    void visit(SplitPaymentCommand command);

    /**
     * Vizitează comanda ReportCommand și execută operațiunea specifică acesteia
     * @param command este instanța ReportCommand care trebuie procesată
     */
    void visit(ReportCommand command);

    /**
     * Vizitează comanda SpendingsReportCommand și execută operațiunea specifică acesteia
     * @param command este instanța SpendingsReportCommand care trebuie procesată
     */
    void visit(SpendingsReportCommand command);

    /**
     * Vizitează comanda AddInterestCommand și execută operațiunea specifică acesteia
     * @param command este instanța AddInterestCommand care trebuie procesată
     */
    void visit(AddInterestCommand command);
}
