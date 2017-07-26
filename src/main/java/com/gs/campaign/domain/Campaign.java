package com.gs.campaign.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String heartTeam;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    public Campaign() {};

    public Campaign(Long id, String name, String heartTeam, LocalDate startDate, LocalDate endDate) {
   	this.id = id;
	this.name = name;
   	this.heartTeam = heartTeam;
   	this.startDate = startDate;
   	this.endDate = endDate;
       }
    
    public Campaign(String name, String heartTeam, LocalDate startDate, LocalDate endDate) {
	this.name = name;
	this.heartTeam = heartTeam;
	this.startDate = startDate;
	this.endDate = endDate;
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

    public String getHeartTeam() {
        return heartTeam;
    }

    public void setHeartTeam(String heartTeam) {
        this.heartTeam = heartTeam;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getExpirationDate() {
	return new StringBuilder()
		.append(this.startDate)
		.append(" to ")
		.append(this.endDate)
		.toString();
    }
    
    public void addDays(int days) {
	this.endDate.plusDays(days);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Campaign other = (Campaign) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
