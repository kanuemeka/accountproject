package com.kanu.interviews.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private int id;

    private String firstName;

    private String secondName;

    private String accountNumber;
}
