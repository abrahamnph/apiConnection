package com.connection.Entity;

import lombok.Data;

@Data
public class RequestConnection {
    private String read_only;
    private String read_write;
    private String user;
    private String pass;
}
