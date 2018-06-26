package com.topcoder.autoinsurance.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Policy implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("policy")
    private String policy;

    @JsonProperty("policyStartDate")
    private Date policyStartDate;

    @JsonProperty("policyEndDate")
    private Date policyEndDate;

    @JsonProperty("car")
    private String car;

    @JsonProperty("driverPersona")
    private String driverPersona;

    @JsonProperty("status")
    private String status;

    @JsonProperty("basePremium")
    private String basePremium;

    @JsonProperty("usageAdjustment")
    private Double usageAdjustment;

    @JsonProperty("currentUsage")
    private String currentUsage;

    @JsonProperty("nextDue")
    private Date nextDue;

    @JsonProperty("drivers")
    private List<Driver> drivers;

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class Driver implements Serializable {

        @JsonProperty("name")
        private String name;

        @JsonProperty("license")
        private String license;

        public String getName() {
            return name;
        }

        public String getLicense() {
            return license;
        }
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPolicy() {
        return policy;
    }

    public Date getPolicyStartDate() {
        return policyStartDate;
    }

    public Date getPolicyEndDate() {
        return policyEndDate;
    }

    public String getCar() {
        return car;
    }

    public String getDriverPersona() {
        return driverPersona;
    }

    public String getStatus() {
        return status;
    }

    public String getBasePremium() {
        return basePremium;
    }

    public Double getUsageAdjustment() {
        return usageAdjustment;
    }

    public String getCurrentUsage() {
        return currentUsage;
    }

    public Date getNextDue() {
        return nextDue;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
}
