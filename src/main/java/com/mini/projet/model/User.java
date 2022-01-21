package com.mini.projet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    protected Long id;
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected String phone;

    public String getFullname() {
       return this.firstname+ " "+this.lastname;
    }
}
