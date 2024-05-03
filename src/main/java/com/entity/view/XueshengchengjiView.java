package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XueshengchengjiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学生成绩
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xueshengchengji")
public class XueshengchengjiView extends XueshengchengjiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 课程
					 
		/**
		* 课程 的 老师
		*/
		@ColumnInfo(comment="老师",type="int(11)")
		private Integer kechengLaoshiId;
		/**
		* 课程编号
		*/

		@ColumnInfo(comment="课程编号",type="varchar(200)")
		private String kechengUuidNumber;
		/**
		* 课程名称
		*/

		@ColumnInfo(comment="课程名称",type="varchar(200)")
		private String kechengName;
		/**
		* 上课地点
		*/

		@ColumnInfo(comment="上课地点",type="varchar(200)")
		private String kechengAddress;
		/**
		* 课程附件
		*/

		@ColumnInfo(comment="课程附件",type="varchar(200)")
		private String kechengFile;
		/**
		* 课程类型
		*/
		@ColumnInfo(comment="课程类型",type="int(11)")
		private Integer kechengTypes;
			/**
			* 课程类型的值
			*/
			@ColumnInfo(comment="课程类型的字典表值",type="varchar(200)")
			private String kechengValue;
		/**
		* 课程开始时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="课程开始时间",type="timestamp")
		private Date kaishiTime;
		/**
		* 课程结束时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="课程结束时间",type="timestamp")
		private Date jieshuTime;
		/**
		* 截止报名时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="截止报名时间",type="timestamp")
		private Date jiezhiTime;
		/**
		* 课程内容
		*/

		@ColumnInfo(comment="课程内容",type="text")
		private String kechengContent;
	//级联表 学生
		/**
		* 学生姓名
		*/

		@ColumnInfo(comment="学生姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 学生手机号
		*/

		@ColumnInfo(comment="学生手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 学生身份证号
		*/

		@ColumnInfo(comment="学生身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 学生头像
		*/

		@ColumnInfo(comment="学生头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 院系
		*/
		@ColumnInfo(comment="院系",type="int(11)")
		private Integer yuanxiTypes;
			/**
			* 院系的值
			*/
			@ColumnInfo(comment="院系的字典表值",type="varchar(200)")
			private String yuanxiValue;
		/**
		* 班级
		*/
		@ColumnInfo(comment="班级",type="int(11)")
		private Integer banjiTypes;
			/**
			* 班级的值
			*/
			@ColumnInfo(comment="班级的字典表值",type="varchar(200)")
			private String banjiValue;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;



	public XueshengchengjiView() {

	}

	public XueshengchengjiView(XueshengchengjiEntity xueshengchengjiEntity) {
		try {
			BeanUtils.copyProperties(this, xueshengchengjiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 课程
		/**
		* 获取：课程 的 老师
		*/
		public Integer getKechengLaoshiId() {
			return kechengLaoshiId;
		}
		/**
		* 设置：课程 的 老师
		*/
		public void setKechengLaoshiId(Integer kechengLaoshiId) {
			this.kechengLaoshiId = kechengLaoshiId;
		}

		/**
		* 获取： 课程编号
		*/
		public String getKechengUuidNumber() {
			return kechengUuidNumber;
		}
		/**
		* 设置： 课程编号
		*/
		public void setKechengUuidNumber(String kechengUuidNumber) {
			this.kechengUuidNumber = kechengUuidNumber;
		}

		/**
		* 获取： 课程名称
		*/
		public String getKechengName() {
			return kechengName;
		}
		/**
		* 设置： 课程名称
		*/
		public void setKechengName(String kechengName) {
			this.kechengName = kechengName;
		}

		/**
		* 获取： 上课地点
		*/
		public String getKechengAddress() {
			return kechengAddress;
		}
		/**
		* 设置： 上课地点
		*/
		public void setKechengAddress(String kechengAddress) {
			this.kechengAddress = kechengAddress;
		}

		/**
		* 获取： 课程附件
		*/
		public String getKechengFile() {
			return kechengFile;
		}
		/**
		* 设置： 课程附件
		*/
		public void setKechengFile(String kechengFile) {
			this.kechengFile = kechengFile;
		}
		/**
		* 获取： 课程类型
		*/
		public Integer getKechengTypes() {
			return kechengTypes;
		}
		/**
		* 设置： 课程类型
		*/
		public void setKechengTypes(Integer kechengTypes) {
			this.kechengTypes = kechengTypes;
		}


			/**
			* 获取： 课程类型的值
			*/
			public String getKechengValue() {
				return kechengValue;
			}
			/**
			* 设置： 课程类型的值
			*/
			public void setKechengValue(String kechengValue) {
				this.kechengValue = kechengValue;
			}

		/**
		* 获取： 课程开始时间
		*/
		public Date getKaishiTime() {
			return kaishiTime;
		}
		/**
		* 设置： 课程开始时间
		*/
		public void setKaishiTime(Date kaishiTime) {
			this.kaishiTime = kaishiTime;
		}

		/**
		* 获取： 课程结束时间
		*/
		public Date getJieshuTime() {
			return jieshuTime;
		}
		/**
		* 设置： 课程结束时间
		*/
		public void setJieshuTime(Date jieshuTime) {
			this.jieshuTime = jieshuTime;
		}

		/**
		* 获取： 截止报名时间
		*/
		public Date getJiezhiTime() {
			return jiezhiTime;
		}
		/**
		* 设置： 截止报名时间
		*/
		public void setJiezhiTime(Date jiezhiTime) {
			this.jiezhiTime = jiezhiTime;
		}

		/**
		* 获取： 课程内容
		*/
		public String getKechengContent() {
			return kechengContent;
		}
		/**
		* 设置： 课程内容
		*/
		public void setKechengContent(String kechengContent) {
			this.kechengContent = kechengContent;
		}
	//级联表的get和set 学生

		/**
		* 获取： 学生姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 学生姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 学生手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 学生手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 学生身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 学生身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 学生头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 学生头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}
		/**
		* 获取： 院系
		*/
		public Integer getYuanxiTypes() {
			return yuanxiTypes;
		}
		/**
		* 设置： 院系
		*/
		public void setYuanxiTypes(Integer yuanxiTypes) {
			this.yuanxiTypes = yuanxiTypes;
		}


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
		/**
		* 获取： 班级
		*/
		public Integer getBanjiTypes() {
			return banjiTypes;
		}
		/**
		* 设置： 班级
		*/
		public void setBanjiTypes(Integer banjiTypes) {
			this.banjiTypes = banjiTypes;
		}


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

		/**
		* 获取： 电子邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "XueshengchengjiView{" +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", kechengUuidNumber=" + kechengUuidNumber +
			", kechengName=" + kechengName +
			", kechengAddress=" + kechengAddress +
			", kechengFile=" + kechengFile +
			", kaishiTime=" + DateUtil.convertString(kaishiTime,"yyyy-MM-dd") +
			", jieshuTime=" + DateUtil.convertString(jieshuTime,"yyyy-MM-dd") +
			", jiezhiTime=" + DateUtil.convertString(jiezhiTime,"yyyy-MM-dd") +
			", kechengContent=" + kechengContent +
			"} " + super.toString();
	}
}
