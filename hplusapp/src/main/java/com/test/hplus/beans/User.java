package com.test.hplus.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private int id;
    private String username;
    private String password;
    private String gender;
    private String activity;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
