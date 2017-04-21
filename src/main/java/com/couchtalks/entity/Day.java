package com.couchtalks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by ShkarupaN on 14.10.2016.
 */
enum Day {

    @JsonProperty
    Sunday,
    @JsonProperty
    Monday,
    @JsonProperty
    Tuesday,
    @JsonProperty
    Wednesday,
    @JsonProperty
    Thursday,
    @JsonProperty
    Friday,
    @JsonProperty
    Saturday;



};