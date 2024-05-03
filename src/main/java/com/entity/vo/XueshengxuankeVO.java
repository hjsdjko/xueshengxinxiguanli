package com.entity.vo;

import com.entity.XueshengxuankeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学生选课
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xueshengxuanke")
public class XueshengxuankeVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学生
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 课程
     */

    @TableField(value = "kecheng_id")
    private Integer kechengId;


    /**
     * 选课时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 申请状态
     */

    @TableField(value = "xueshengxuanke_yesno_types")
    private Integer xueshengxuankeYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "xueshengxuanke_yesno_text")
    private String xueshengxuankeYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xueshengxuanke_shenhe_time")
    private Date xueshengxuankeShenheTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：学生
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：课程
	 */
    public Integer getKechengId() {
        return kechengId;
    }


    /**
	 * 获取：课程
	 */

    public void setKechengId(Integer kechengId) {
        this.kechengId = kechengId;
    }
    /**
	 * 设置：选课时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：选课时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getXueshengxuankeYesnoTypes() {
        return xueshengxuankeYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setXueshengxuankeYesnoTypes(Integer xueshengxuankeYesnoTypes) {
        this.xueshengxuankeYesnoTypes = xueshengxuankeYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getXueshengxuankeYesnoText() {
        return xueshengxuankeYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setXueshengxuankeYesnoText(String xueshengxuankeYesnoText) {
        this.xueshengxuankeYesnoText = xueshengxuankeYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getXueshengxuankeShenheTime() {
        return xueshengxuankeShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setXueshengxuankeShenheTime(Date xueshengxuankeShenheTime) {
        this.xueshengxuankeShenheTime = xueshengxuankeShenheTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
