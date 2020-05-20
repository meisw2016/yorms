package cn.com.yusys.oca.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Health")
public class HealthTestController {
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String Test() {
		return "Server Up";
		
	}

}
