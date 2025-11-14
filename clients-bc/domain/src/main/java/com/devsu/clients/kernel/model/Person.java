package com.devsu.clients.kernel.model;

import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;

import java.time.OffsetDateTime;

public abstract class Person{
    private final String name;
    private final String lastName;
    private final Gender gender;
    private final OffsetDateTime birthDate;
    private final DocumentType documentType;
    private final String documentNumber;
    private final String address;
    private final String phone;

    public Person(
            String name,
            String lastName,
            Gender gender,
            OffsetDateTime birthDate,
            DocumentType documentType,
            String documentNumber,
            String address,
            String phone
    ) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.address = address;
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
