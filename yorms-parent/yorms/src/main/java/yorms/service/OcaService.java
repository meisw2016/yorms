package yorms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yusys.file.util.OutputData;

@FeignClient("yorms-oca")
public interface OcaService {
	
	@RequestMapping("/Authority/testFeign")
	String testFeign();
}
