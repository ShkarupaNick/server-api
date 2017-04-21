
package com.couchtalks.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "average"
})


@Entity
public class Rating implements Serializable {


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

    @JsonProperty("average")
    private Integer average;
    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The average
     */
    @JsonProperty("average")
    public Integer getAverage() {
        return average;
    }

    /**
     * 
     * @param average
     *     The average
     */
    @JsonProperty("average")
    public void setAverage(Integer average) {
        this.average = average;
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
