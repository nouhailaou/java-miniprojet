package com.mini.projet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Machine {
    protected Long id;
    private Long salleId;
    protected String reference;
    protected String type;
    protected Date dateAchat;
    private String dateStr;
}
