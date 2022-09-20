package vip.ashes.travel.map.entity.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author loveliness
 */
@NoArgsConstructor
@Data
public class BaiDuSearchVo {


    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("result_type")
    private String resultType;
    @JsonProperty("results")
    private List<ResultsDTO> results;

    @NoArgsConstructor
    @Data
    public static class ResultsDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("location")
        private LocationDTO location;
        @JsonProperty("address")
        private String address;
        @JsonProperty("province")
        private String province;
        @JsonProperty("city")
        private String city;
        @JsonProperty("area")
        private String area;
        @JsonProperty("street_id")
        private String streetId;
        @JsonProperty("telephone")
        private String telephone;
        @JsonProperty("detail")
        private Integer detail;
        @JsonProperty("uid")
        private String uid;

        @NoArgsConstructor
        @Data
        public static class LocationDTO {
            @JsonProperty("lat")
            private Double lat;
            @JsonProperty("lng")
            private Double lng;
        }
    }
}
