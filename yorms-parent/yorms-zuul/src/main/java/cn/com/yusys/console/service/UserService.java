package cn.com.yusys.console.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.yusys.console.dto.UserCheck;
import cn.com.yusys.console.dto.UserLoginRequest;

@FeignClient("yorms-oca")
public interface UserService {

	@RequestMapping(value = "/User/login", method = RequestMethod.POST)
	public UserCheck login(@RequestBody UserLoginRequest loginRequest );

}
