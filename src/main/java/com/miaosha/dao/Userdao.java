package com.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.miaosha.domain.User;

@Mapper
public interface Userdao {
	
	@Select("select * from user where id = #{id}")
	public User getByid(@Param("id")int id);

	@Insert("insert into user(id,name)values(#{id},#{name})")
	public int insert(User user);

}
