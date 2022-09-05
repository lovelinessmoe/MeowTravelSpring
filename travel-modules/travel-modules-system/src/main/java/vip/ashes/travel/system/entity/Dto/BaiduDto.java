package vip.ashes.travel.system.entity.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BaiduDto {
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
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
        @JsonProperty("detail_info")
        private DetailInfoDTO detailInfo;

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
        public static class DetailInfoDTO {
            @JsonProperty("distance")
            private Integer distance;
            @JsonProperty("tag")
            private String tag;
            @JsonProperty("navi_location")
            private NaviLocationDTO naviLocation;
            @JsonProperty("type")
            private String type;
            @JsonProperty("detail_url")
            private String detailUrl;
            @JsonProperty("overall_rating")
            private String overallRating;
            @JsonProperty("service_rating")
            private String serviceRating;
            @JsonProperty("facility_rating")
            private String facilityRating;
            @JsonProperty("hygiene_rating")
            private String hygieneRating;
            @JsonProperty("image_num")
            private String imageNum;
            @JsonProperty("comment_num")
            private String commentNum;
            @JsonProperty("favorite_num")
            private String favoriteNum;
            @JsonProperty("checkin_num")
            private String checkinNum;
            @JsonProperty("shop_hours")
            private String shopHours;
            @JsonProperty("children")
            private List<?> children;

            @NoArgsConstructor
            @Data
            public static class NaviLocationDTO {
                @JsonProperty("lng")
                private Double lng;
                @JsonProperty("lat")
                private Double lat;
            }
        }
    }
}
