package com.devsu.clients.adapter.out.repository.client.dto;

import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private OffsetDateTime birthDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Column(nullable = false, unique = true)
    private String documentNumber;
    private String address;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
