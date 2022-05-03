package com.mariadb.bss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name="voltage_indx", columnList = "voltage"))
public class Gim {

    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GimID;

    private Long voltage;

    @Column(length=50)
    private String current;

    @Column(length=50)
    private String temperature;

    @Column(length=50)
    private String redox_factor;

    @Column(length=50)
    private String ox_factor;
}