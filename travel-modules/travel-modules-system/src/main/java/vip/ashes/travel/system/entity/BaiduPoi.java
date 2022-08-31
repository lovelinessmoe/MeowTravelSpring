package vip.ashes.travel.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.baidu_poi")
public class BaiduPoi implements Serializable {
    /**
     * poi的唯一标示，可用于详情检索
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * poi地址信息
     */
    @TableField(value = "address")
    private String address;

    /**
     * 所属省份

     */
    @TableField(value = "province")
    private String province;

    /**
     * 所属城市

     */
    @TableField(value = "city")
    private String city;

    /**
     * 所属区县

     */
    @TableField(value = "area")
    private String area;

    /**
     * poi电话信息
     */
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 纬度值
     */
    @TableField(value = "location_lat")
    private Double locationLat;

    /**
     * 经度值

     */
    @TableField(value = "location_lng")
    private Double locationLng;

    private static final long serialVersionUID = 1L;

    public static final String COL_UID = "uid";

    public static final String COL_NAME = "name";

    public static final String COL_ADDRESS = "address";

    public static final String COL_PROVINCE = "province";

    public static final String COL_CITY = "city";

    public static final String COL_AREA = "area";

    public static final String COL_TELEPHONE = "telephone";

    public static final String COL_LOCATION_LAT = "location_lat";

    public static final String COL_LOCATION_LNG = "location_lng";
}