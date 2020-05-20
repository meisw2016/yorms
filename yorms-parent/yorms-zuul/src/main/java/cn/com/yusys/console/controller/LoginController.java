package cn.com.yusys.console.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;

import cn.com.yusys.console.dto.UserCheck;
import cn.com.yusys.console.dto.UserLoginRequest;
import cn.com.yusys.console.service.UserService;
import cn.com.yusys.console.util.RedisUtil;
import cn.com.yusys.file.util.OutputData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/login")
public class LoginController {
	
	@Autowired
	UserService UserService;
	
	private static int ExpireTime = 600;   // redis中存储的过期时间60s
	
	@Resource
    private RedisUtil redisUtil;
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ApiOperation(value = "/login",notes = "login")
    public OutputData login(@RequestBody UserLoginRequest loginRequest,HttpServletRequest httprRequest,HttpServletResponse response){
		

		OutputData out = new OutputData().returnSuccess();
		
		UserCheck userCheck = UserService.login(loginRequest);
		
		List<Object> data = new ArrayList<Object>();
		data.add(userCheck);
		
		
		if (userCheck.getUserId() != null) {
			String accessToken = UUID.randomUUID().toString();
			redisUtil.set(userCheck.getUserId().toString(), accessToken,ExpireTime);
			userCheck.setAccessToken(accessToken);
		}
		
		out.setData(data);
		
		return out;
    }
}
