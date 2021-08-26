package dev.roeymiller.ams.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity(name="Destination")
@Table(name="destinations")
@ApiModel(description = "Destination model")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "long id")
    private long id;
    @ApiModelProperty(notes = "String name")
    @Column(name="name")
    private String name;
    @ApiModelProperty(notes = "String location format (78.076525/-413.920331)")
    @Column(name="location")
    private String location;
    @ApiModelProperty(notes = "double distance")
    @Column(name="distance",nullable = true)
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
