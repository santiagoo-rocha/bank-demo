package com.devsu.clients.util;

import com.devsu.clients.kernel.command.client.CreateClientCommand;
import com.devsu.clients.kernel.command.client.DeleteClientCommand;
import com.devsu.clients.kernel.command.client.UpdateClientCommand;
import com.devsu.clients.kernel.query.GetClientsQuery;

import static com.devsu.clients.util.Constants.*;

public class Samples {
    public static CreateClientCommand createBookCommandSample(){
        return new CreateClientCommand(TITLE, AUTHOR, EDITION, YEAR);
    }

    public static Book bookSample(){
        return Book.builder()
                .bookId(BOOK_ID)
                .title(TITLE)
                .author(AUTHOR)
                .edition(EDITION)
                .year(YEAR)
                .build();
    }

    public static GetClientsQuery getBooksQuerySample(){
        return new GetClientsQuery(OFFSET, LIMIT);
    }

    public static GetBookByIdQuery getBookByIdQuerySample(){
        return new GetBookByIdQuery(BOOK_ID);
    }

    public static DeleteClientCommand deleteBookCommandSample(){
        return new DeleteClientCommand(BOOK_ID);
    }

    public static UpdateClientCommand updateBookCommandSample(){
        return new UpdateClientCommand(BOOK_ID, AUTHOR, TITLE, null, null);
    }
}
