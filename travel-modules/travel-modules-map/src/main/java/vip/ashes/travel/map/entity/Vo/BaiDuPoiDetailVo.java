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
public class BaiDuPoiDetailVo {


    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private ResultDTO result;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        @JsonProperty("uid")
        private String uid;
        @JsonProperty("street_id")
        private String streetId;
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
        @JsonProperty("telephone")
        private String telephone;
        @JsonProperty("detail_info")
        private DetailInfoDTO detailInfo;
        @JsonProperty("detail")
        private Integer detail;

        @NoArgsConstructor
        @Data
        public static class LocationDTO {
            @JsonProperty("lng")
            private Double lng;
            @JsonProperty("lat")
            private Double lat;
        }

        @NoArgsConstructor
        @Data
        public static class DetailInfoDTO {
            @JsonProperty("tag")
            private String tag;
            @JsonProperty("navi_location")
            private NaviLocationDTO naviLocation;
            @JsonProperty("shop_hours")
            private String shopHours;
            @JsonProperty("alias")
            private List<String> alias;
            @JsonProperty("detail_url")
            private String detailUrl;
            @JsonProperty("type")
            private String type;
            @JsonProperty("overall_rating")
            private String overallRating;
            @JsonProperty("service_rating")
            private String serviceRating;
            @JsonProperty("environment_rating")
            private String environmentRating;
            @JsonProperty("image_num")
            private String imageNum;
            @JsonProperty("comment_num")
            private String commentNum;
            @JsonProperty("content_tag")
            private String contentTag;

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
