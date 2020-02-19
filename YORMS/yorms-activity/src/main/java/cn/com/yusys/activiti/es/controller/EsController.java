package cn.com.yusys.activiti.es.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.activiti.es.entity.UserEntity;
import cn.com.yusys.activiti.es.repository.UserRepository;

@Api(value = "es的demo测试",description = "es demo测试")
@RestController
@RequestMapping("/es")
public class EsController {

	private static final Logger log = LoggerFactory.getLogger(EsController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 添加文档
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	@ApiOperation(value = "/addUser",notes = "添加文档",httpMethod = "POST")
	public UserEntity addUser(@RequestBody UserEntity user){
		log.info("添加文档：{}",user);
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/findUser",method = RequestMethod.GET)
	@ApiOperation(value = "/findUser",notes = "es查询",httpMethod = "GET")
	public Optional<UserEntity> findUser(@ApiParam(name="id",value = "id",required =true)@RequestParam(value = "id",required = true)String id){
		log.info("es查询入参：id：{}",id);
		return userRepository.findById(id);
	}
}
