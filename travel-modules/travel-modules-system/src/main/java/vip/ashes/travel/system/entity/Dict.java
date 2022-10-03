package vip.ashes.travel.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典表
 *
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.dict")
public class Dict implements Serializable {
    public static final String COL_CODE = "code";
    public static final String COL_DICT_KEY = "dict_key";
    public static final String COL_DICT_VALUE = "dict_value";
    public static final String COL_REMARK = "remark";
    private static final long serialVersionUID = 1L;
    /**
     * 字典码
     */
    @TableField(value = "code")
    private String code;
    /**
     * 字典值
     */
    @TableField(value = "dict_key")
    private Integer dictKey;
    /**
     * 字典名称
     */
    @TableField(value = "dict_value")
    private String dictValue;
    /**
     * 字典备注
     */
    @TableField(value = "remark")
    private String remark;
}
