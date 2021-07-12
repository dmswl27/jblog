package com.douzone.jblog.dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="response")
public class XmlResult {
	private String result; 		 /* "success" or "fail"  */
	private CategoryVo data;    /* if success, data set */
	private String message; 	 /* if fail, message set */
	
	private XmlResult() {
	}

	private XmlResult(CategoryVo data) {
		result = "success";
		this.data = data; 
	}

	private XmlResult(String message) {
		result = "fail";
		this.message = message; 
	}

	public static XmlResult success(CategoryVo data) {
		return new XmlResult(data);
	}

	public static XmlResult fail(String message) {
		return new XmlResult(message);
	}
	
	@XmlRootElement(name="data")
	public static class CategoryVo {
		private Long no;
		private String name;
		private String desc;
		private String reg_date;
		private String blog_id;
		private int count;
		
		
		public Long getNo() {
			return no;
		}


		public void setNo(Long no) {
			this.no = no;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getDesc() {
			return desc;
		}


		public void setDesc(String desc) {
			this.desc = desc;
		}


		public String getReg_date() {
			return reg_date;
		}


		public void setReg_date(String reg_date) {
			this.reg_date = reg_date;
		}


		public String getBlog_id() {
			return blog_id;
		}


		public void setBlog_id(String blog_id) {
			this.blog_id = blog_id;
		}


		public int getCount() {
			return count;
		}


		public void setCount(int count) {
			this.count = count;
		}
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public CategoryVo getData() {
		return data;
	}

	public void setData(CategoryVo data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}