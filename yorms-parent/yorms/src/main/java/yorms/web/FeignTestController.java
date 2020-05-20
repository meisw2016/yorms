package yorms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.file.util.OutputData;
import io.swagger.annotations.ApiOperation;
import yorms.service.OcaService;

@RestController
@RequestMapping("/test")
public class FeignTestController {

	@Autowired
	OcaService ocaService;
	
	@RequestMapping(value = "/tttt", method = RequestMethod.GET)
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "/tttt",notes = "tttt")
	public String test() {
		return ocaService.testFeign();
	}
}
