package org.example.springtrain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PersonRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER) //todo cascade
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
