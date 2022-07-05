package com.tbd.control5.backend;
import org.springframework.data.annotation.Id;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

public class InstitutionModel {
    @Id private String id;
    // @NotNull(message = "Debe indicar un nombre de Institución")
    // @Size(min=1, max=200, message="Debe tener al menos 1 caracter")
    private String name;
    // @NotNull(message = "Debe indicar un email")
    // @Email(message = "Debe indicar un mail valido")
    private String email;
    private String contact;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
