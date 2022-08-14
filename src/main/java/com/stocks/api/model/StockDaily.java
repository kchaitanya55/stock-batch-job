package com.stocks.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="stocks")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDaily {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long Id;

    @JsonProperty("1. open")
    private String open;
    @JsonProperty("2. high")
    private String high;
    @JsonProperty("3. low")
    private String low;
    @JsonProperty("4. close")
    private String close;
    @JsonProperty("5. volume")
    private String volume;
    @JsonIgnore
    private Date stockDate;
}
