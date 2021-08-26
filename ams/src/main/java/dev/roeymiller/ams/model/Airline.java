package dev.roeymiller.ams.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity(name="Airline")
@Table(name="airlines")
@ApiModel(description = "Airline model")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "long id")
    private Long id;
    @ApiModelProperty(notes = "String name")
    @Column(name="name")
    private String name;
    @ApiModelProperty(notes = "double budget")
    @Column(name="budget")
    private double budget;
    @ApiModelProperty(notes = "String home_base_location format (78.076525/-413.920331)")
    @Column(name="home_base_location")
    private String home_base_location;
    @ApiModelProperty(notes = "List<Destination> destinations-for Calculate Destinations and available destinations")
    @OneToMany
    private List<Destination> destinations;
    @ApiModelProperty(notes = "List<Aircraft> aircrafts-each Airline can buy,sell,and Aircrafts")
    @OneToMany
    private List<Aircraft> aircrafts;

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public Aircraft addAircraft(Aircraft aircraft) {
        aircraft.setAirline_Id(this.getId());
        this.aircrafts.add(aircraft);
        return aircraft;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


    public String getHome_base_location() {
        return home_base_location;
    }

    public void setHome_base_location(String home_base_location) {
        this.home_base_location = home_base_location;
    }

}
