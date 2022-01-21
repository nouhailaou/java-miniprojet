package com.mini.projet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salle {
    protected Long id;
    protected String code;
    protected String type;
    private List<Machine> machines;

}
