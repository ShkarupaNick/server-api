
package com.couchtalks.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "days"
})


@Entity
public class Schedule implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue( generator="uuid" )
    @GenericGenerator(
            name="uuid",
            strategy="org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name="uuid_gen_strategy_class",
                            value="org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    public UUID uuid;

    @JsonProperty("time")
    private String time;


    @JsonProperty("days")
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "day", joinColumns = @JoinColumn(name = "day_id"))
    private List<String> days = new ArrayList<String>();


    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The time
     */
    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The days
     */

    public List<String> getDays() {
        return days;
    }

    /**
     * 
     * @param days
     *     The days
     */

    public void setDays(List<String> days) {
        this.days = days;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
