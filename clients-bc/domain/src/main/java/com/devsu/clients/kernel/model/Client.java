package com.devsu.clients.kernel.model;

import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;

import java.time.OffsetDateTime;

public class Client extends Person {

    private final String clientId;
    private final String password;
    private final ClientStatus status;

    private Client(String clientId, String password, ClientStatus status, String name, String lastName, Gender gender,
                   OffsetDateTime birthDate, DocumentType documentType, String documentNumber,
                   String address, String phone
    ) {
        super(name, lastName, gender, birthDate, documentType, documentNumber, address, phone);
        this.clientId = clientId;
        this.password = password;
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public String getClientId() {
        return clientId;
    }

    public String getPassword() {
        return password;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public static final class Builder {
        private String clientId;
        private String password;
        private ClientStatus status;
        private String name;
        private String lastName;
        private Gender gender;
        private OffsetDateTime birthDate;
        private DocumentType documentType;
        private String documentNumber;
        private String address;
        private String phone;

        public Builder() {
        }

        public Builder(Client client) {
            this.clientId = client.clientId;
            this.password = client.password;
            this.status = client.status;
            this.name = client.getName();
            this.lastName = client.getLastName();
            this.gender = client.getGender();
            this.birthDate = client.getBirthDate();
            this.documentType = client.getDocumentType();
            this.documentNumber = client.getDocumentNumber();
            this.address = client.getAddress();
            this.phone = client.getPhone();
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder status(ClientStatus status) {
            this.status = status;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthDate(OffsetDateTime birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder documentType(DocumentType documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Client build() {
            return new Client(clientId, password, status, name, lastName, gender, birthDate, documentType, documentNumber,
                    address, phone);
        }
    }
}
