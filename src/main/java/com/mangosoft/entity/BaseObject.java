package com.mangosoft.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
public class BaseObject implements Serializable{

    public static final String ID_FIELD = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_FIELD)
    private long id;

}
