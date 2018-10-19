package com.kanu.interviews.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    private int id;

    private String firstName;

    private String secondName;

    private String accountNumber;
}
