
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
    "uuid",
    "id",
    "url",
    "name",
    "season",
    "number",
    "airdate",
    "airtime",
    "airstamp",
    "runtime",
    "image",
    "summary",
    "show",
    "_links",
    "is_featured"
})

@Entity
public class Item implements Serializable {

    @Id
    @JsonProperty("uuid")
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


    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    @Column(name = "url", length = 1028)
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("season")
    private Integer season;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("airdate")
    private String airdate;
    @JsonProperty("airtime")
    private String airtime;
    @JsonProperty("airstamp")
    private String airstamp;
    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("image")
    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="image_uuid",referencedColumnName="uuid")

    private Image image;

    @JsonProperty("summary")
    @Column(name = "summary", length = 5000)
    private String summary;

    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="show_uuid",referencedColumnName="uuid")
    @JsonProperty("show")
    private Show show;


    @ManyToOne(optional=true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="_links_uuid",referencedColumnName="uuid")
    @JsonProperty("_links")
    private Links2 links;


    @JsonProperty("is_featured")
    @Column(name = "is_featured")
    private  Boolean isFeatured = false;



    @JsonProperty("categories")
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "item_categories",
            joinColumns = @JoinColumn(name = "item_uuid", referencedColumnName="uuid"),inverseJoinColumns = @JoinColumn(name = "category_uuid", referencedColumnName="uuid"))
    private Set<Category> categories;

    @JsonIgnore
    @Column(name = "viewsCnt", columnDefinition = "int4 default 0")
    private Integer viewsCnt = 0;

    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getViewsCnt() {
        return viewsCnt==null?0:viewsCnt;
    }

    public void setViewsCnt(Integer viewsCnt) {
        this.viewsCnt = viewsCnt;
    }

    public void addViewsCnt() {
        setViewsCnt(getViewsCnt()+1);
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The season
     */
    @JsonProperty("season")
    public Integer getSeason() {
        return season;
    }

    /**
     * 
     * @param season
     *     The season
     */
    @JsonProperty("season")
    public void setSeason(Integer season) {
        this.season = season;
    }

    /**
     * 
     * @return
     *     The number
     */
    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The number
     */
    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 
     * @return
     *     The airdate
     */
    @JsonProperty("airdate")
    public String getAirdate() {
        return airdate;
    }

    /**
     * 
     * @param airdate
     *     The airdate
     */
    @JsonProperty("airdate")
    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    /**
     * 
     * @return
     *     The airtime
     */
    @JsonProperty("airtime")
    public String getAirtime() {
        return airtime;
    }

    /**
     * 
     * @param airtime
     *     The airtime
     */
    @JsonProperty("airtime")
    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    /**
     * 
     * @return
     *     The airstamp
     */
    @JsonProperty("airstamp")
    public String getAirstamp() {
        return airstamp;
    }

    /**
     * 
     * @param airstamp
     *     The airstamp
     */
    @JsonProperty("airstamp")
    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    @JsonProperty("runtime")
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    @JsonProperty("runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")
    public Object getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The summary
     */
    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The show
     */
    @JsonProperty("show")
    public Show getShow() {
        return show;
    }

    /**
     * 
     * @param show
     *     The show
     */
    @JsonProperty("show")
    public void setShow(Show show) {
        this.show = show;
    }

    /**
     * 
     * @return
     *     The links
     */
    @JsonProperty("_links")
    public Links2 getLinks() {
        return links;
    }

    /**
     * 
     * @param links
     *     The _links
     */
    @JsonProperty("_links")
    public void setLinks(Links2 links) {
        this.links = links;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
