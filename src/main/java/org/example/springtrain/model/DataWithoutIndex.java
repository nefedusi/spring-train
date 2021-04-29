package org.example.springtrain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DataWithoutIndex {

    @Id
    private long id;

    @Column
    private long f1;

    @Column
    private long f2;
}
