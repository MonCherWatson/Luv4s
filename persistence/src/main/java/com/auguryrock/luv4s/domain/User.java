package com.auguryrock.luv4s.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    protected String name;
    protected String accountId;
    protected String password;
    protected String description;
}
