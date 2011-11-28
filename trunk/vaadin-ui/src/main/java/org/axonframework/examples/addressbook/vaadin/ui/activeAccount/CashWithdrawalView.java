package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import nijhof2axon.app.command.WithdrawCashCommand;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.CashWithdrawalCompletedEvent;
import org.axonframework.sample.app.query.ActiveAccountEntry;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-19
 * Time: 11:52:45 AM
 */
public class CashWithdrawalView extends MediatorVerticalLayout {

    private ActiveAccountEntry activeAccountEntry;

    public CashWithdrawalView(final CommandBus commandBus, final LedgerContainer ledgerContainer) {

        final TextField withdrawAmount = new TextField("Specify the amount to withdraw");

        addComponent(withdrawAmount);

        Button deposit = new Button("Withdraw");

        deposit.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                BigDecimal amount = BigDecimal.valueOf(new Double(withdrawAmount.getValue().toString()));

                WithdrawCashCommand withdrawCashCommand =
                        new WithdrawCashCommand(activeAccountEntry.getIdentifier(), amount);

                commandBus.dispatch(withdrawCashCommand);

                ledgerContainer.refreshContent(activeAccountEntry.getIdentifier());

                fire(new CashWithdrawalCompletedEvent());                
            }
        });

        addComponent(deposit);

    }

    public void refreshFor(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }
}
