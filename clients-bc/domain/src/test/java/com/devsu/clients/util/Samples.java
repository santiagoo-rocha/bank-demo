package com.devsu.clients.util;

import com.devsu.clients.kernel.command.client.CreateClientCommand;
import com.devsu.clients.kernel.command.client.DeleteClientCommand;
import com.devsu.clients.kernel.command.client.UpdateClientCommand;
import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.model.Page;
import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;
import com.devsu.clients.kernel.query.GetClientByIdQuery;
import com.devsu.clients.kernel.query.GetClientsQuery;

import java.time.OffsetDateTime;

import static com.devsu.clients.util.Constants.*;

public class Samples {

    public static CreateClientCommand createClientCommandSample() {
        return new CreateClientCommand(PASSWORD, NAME, LAST_NAME, Gender.M, OffsetDateTime.now(),
                DocumentType.CC, DOCUMENT_NUMBER, ADDRESS, PHONE);
    }

    public static DeleteClientCommand deleteClientCommandSample() {
        return new DeleteClientCommand(CLIENT_ID);
    }

    public static UpdateClientCommand updateClientCommandSample() {
        return new UpdateClientCommand(CLIENT_ID, null, null, NAME,
                Gender.F, NAME, NAME);
    }

    public static GetClientByIdQuery getClientByIdQuerySample() {
        return new GetClientByIdQuery(CLIENT_ID);
    }

    public static GetClientsQuery getClientsQuerySample() {
        return new GetClientsQuery(0, 10);
    }

    public static Client clientSample() {
        return Client.builder()
                .clientId(CLIENT_ID)
                .password(PASSWORD)
                .status(ClientStatus.ENABLE)
                .name(NAME)
                .gender(Gender.M)
                .lastName(LAST_NAME)
                .birthDate(OffsetDateTime.now())
                .documentNumber(DOCUMENT_NUMBER)
                .documentType(DocumentType.CC)
                .address(ADDRESS)
                .phone(PHONE)
                .build();
    }
}
