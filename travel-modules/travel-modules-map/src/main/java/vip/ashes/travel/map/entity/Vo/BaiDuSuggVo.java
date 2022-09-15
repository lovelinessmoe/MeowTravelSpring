package vip.ashes.travel.map.entity.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BaiDuSuggVo {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private List<ResultDTO> result;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("location")
        private LocationDTO location;
        @JsonProperty("uid")
        private String uid;
        @JsonProperty("province")
        private String province;
        @JsonProperty("city")
        private String city;
        @JsonProperty("district")
        private String district;
        @JsonProperty("business")
        private String business;
        @JsonProperty("cityid")
        private String cityid;
        @JsonProperty("tag")
        private String tag;
        @JsonProperty("address")
        private String address;
        @JsonProperty("children")
        private List<ChildrenDTO> children;
        @JsonProperty("adcode")
        private String adcode;

        @NoArgsConstructor
        @Data
        public static class LocationDTO {
            @JsonProperty("lat")
            private Double lat;
            @JsonProperty("lng")
            private Double lng;
        }

        @NoArgsConstructor
        @Data
        public static class ChildrenDTO {
            @JsonProperty("uid")
            private String uid;
            @JsonProperty("name")
            private String name;
            @JsonProperty("show_name")
            private String showName;
        }
    }
}
