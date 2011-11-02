package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.sample.app.api.fohjin.command.OpenNewAccountForClientCommand;
import org.axonframework.sample.app.query.ClientEntry;

import java.util.Arrays;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetails extends VerticalLayout {

    public ClientDetails(final ClientEntry clientEntry, final CommandBus commandBus, final ActiveAccountContainer activeAccountContainer) {

        final Form clientForm = new Form();
        clientForm.setCaption("Client Details");
        clientForm.setSizeFull();

        BeanItem item = new BeanItem(clientEntry);
        item.removeItemProperty("identifier");

        clientForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "name"}));

        clientForm.setReadOnly(true);

        clientForm.setItemDataSource(item);

        addComponent(clientForm);

        final Table activeAccountsTable = new Table("Active Accounts");
        activeAccountsTable.setContainerDataSource(activeAccountContainer);


//        addComponent(new Label("Client: " + clientEntry.getName()));
//

        final TextField activeAccountName = new TextField("Account Name");

        Button addActiveAccount = new Button("Add active account");

        addActiveAccount.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                AggregateIdentifier aggregateIdentifier = new StringAggregateIdentifier(clientEntry.getIdentifier());

                OpenNewAccountForClientCommand command = new OpenNewAccountForClientCommand(aggregateIdentifier,
                        activeAccountName.getValue().toString());

                commandBus.dispatch(command);

                activeAccountContainer.refreshContent();
            }
        });
//
        addComponent(activeAccountName);
        addComponent(addActiveAccount);
        addComponent(activeAccountsTable);


    }

}
