package dev.roeymiller.ams.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Aircraft")
@Table(name="aircrafts")
@ApiModel(description = "Aircraft model")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "long id")
    private Long id;

    @Column(name="max_distance")
    @ApiModelProperty(notes = "double max_distance")
    private double max_distance;
    @Column(name="price")
    @ApiModelProperty(notes = "double price")
    private double price;
    @Column(name="created_at")
    @ApiModelProperty(notes = "String created_at")
    private String created_at;
    @Column(name="airline_Id",nullable = true)
    @ApiModelProperty(notes = "long airline_Id")
    private long airline_Id;
    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public long getAirline_Id() {
        return airline_Id;
    }

    public void setAirline_Id(long airline_Id) {
        this.airline_Id = airline_Id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMax_distance() {
        return max_distance;
    }

    public void setMax_distance(double max_distance) {
        this.max_distance = max_distance;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
