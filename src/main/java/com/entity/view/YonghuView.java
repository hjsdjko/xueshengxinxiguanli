package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YonghuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学生
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yonghu")
public class YonghuView extends YonghuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 性别的值
	*/
	@ColumnInfo(comment="性别的字典表值",type="varchar(200)")
	private String sexValue;
	/**
	* 院系的值
	*/
	@ColumnInfo(comment="院系的字典表值",type="varchar(200)")
	private String yuanxiValue;
	/**
	* 班级的值
	*/
	@ColumnInfo(comment="班级的字典表值",type="varchar(200)")
	private String banjiValue;




	public YonghuView() {

	}

	public YonghuView(YonghuEntity yonghuEntity) {
		try {
			BeanUtils.copyProperties(this, yonghuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 性别的值
	*/
	public String getSexValue() {
		return sexValue;
	}
	/**
	* 设置： 性别的值
	*/
	public void setSexValue(String sexValue) {
		this.sexValue = sexValue;
	}
	//当前表的
	/**
	* 获取： 院系的值
	*/
	public String getYuanxiValue() {
		return yuanxiValue;
	}
	/**
	* 设置： 院系的值
	*/
	public void setYuanxiValue(String yuanxiValue) {
		this.yuanxiValue = yuanxiValue;
	}
	//当前表的
	/**
	* 获取： 班级的值
	*/
	public String getBanjiValue() {
		return banjiValue;
	}
	/**
	* 设置： 班级的值
	*/
	public void setBanjiValue(String banjiValue) {
		this.banjiValue = banjiValue;
	}




	@Override
	public String toString() {
		return "YonghuView{" +
			", sexValue=" + sexValue +
			", yuanxiValue=" + yuanxiValue +
			", banjiValue=" + banjiValue +
			"} " + super.toString();
	}
}