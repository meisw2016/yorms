package cn.com.yusys.activiti.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName="myes",type="user")
public class UserEntity {
	
	@Id
	private String id;
	
	private String name;
	
	private Integer sex;
	
	private Integer age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}

}
