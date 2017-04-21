
package com.couchtalks.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "self"
})

@Entity
@Table(name = "links_")
@JsonRootName("Links_")
public class Links2  implements Serializable {


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

    @JsonProperty("self")

    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="Self2_uuid",referencedColumnName="uuid")
    private Self2 self;

    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The self
     */
    @JsonProperty("self")
    public Self2 getSelf() {
        return self;
    }

    /**
     * 
     * @param self
     *     The self
     */
    @JsonProperty("self")
    public void setSelf(Self2 self) {
        this.self = self;
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
