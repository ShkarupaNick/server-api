
package com.couchtalks.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "tvrage",
    "thetvdb",
    "imdb"
})

@Entity
public class Externals implements Serializable {
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


    @JsonProperty("tvrage")
    private Integer tvrage;
    @JsonProperty("thetvdb")
    private Integer thetvdb;
    @JsonProperty("imdb")
    private String imdb;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The tvrage
     */
    @JsonProperty("tvrage")
    public Object getTvrage() {
        return tvrage;
    }

    /**
     *
     * @param tvrage
     *     The tvrage
     */
    @JsonProperty("tvrage")
    public void setTvrage(Integer tvrage) {
        this.tvrage = tvrage;
    }

    /**
     * 
     * @return
     *     The thetvdb
     */
    @JsonProperty("thetvdb")
    public Integer getThetvdb() {
        return thetvdb;
    }

    /**
     * 
     * @param thetvdb
     *     The thetvdb
     */
    @JsonProperty("thetvdb")
    public void setThetvdb(Integer thetvdb) {
        this.thetvdb = thetvdb;
    }

    /**
     * 
     * @return
     *     The imdb
     */
    @JsonProperty("imdb")
    public String getImdb() {
        return imdb;
    }

    /**
     * 
     * @param imdb
     *     The imdb
     */
    @JsonProperty("imdb")
    public void setImdb(String imdb) {
        this.imdb = imdb;
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
