/*
 * Copyright (c) 2010. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.examples.addressbook.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.data.ContactContainer;
import org.axonframework.examples.addressbook.vaadin.ui.client.ClientView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class Nijhof2AxonApplication extends Application {

    private Button moneyTransfer = new Button("Transfer Money!");
    private Button openActiveAccount = new Button("Open Active Account!");
    private Button createClient = new Button("Create a Client");

    @Autowired
    private ContactContainer contactContainer;

    @Autowired
    private ClientContainer clientContainer;

    @Autowired
    private CommandBus commandBus;

    @Override
    public void init() {

        Window mainWindow = new Window("Nijhof2Axon Application");
        setMainWindow(mainWindow);

        mainWindow.getContent().setSizeFull();

        VerticalLayout mainVerticalLayout = new VerticalLayout();

        mainVerticalLayout.addComponent(new ClientView(commandBus, clientContainer));

        mainWindow.setContent(mainVerticalLayout);

    }

}
