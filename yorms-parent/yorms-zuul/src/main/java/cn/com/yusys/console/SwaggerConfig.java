package cn.com.yusys.console;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket createRestApi() {
		/**添加Cookie请求头信息*/
		ParameterBuilder cookie = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		cookie.name("cookies").description("cookies").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(cookie.build());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.com.yusys.console"))
				.paths(PathSelectors.any()).build().globalOperationParameters(pars);
	}
	
	/**
	 * 构建API文档的详细信息函数，注意这里的注解引用的哪个
	 * @author meisw 2019年11月29日 下午2:27:49
	 * @Method: apiInfo 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("SpringBoot使用Swagger2构建 RESTFul API")
				//版本
				.version("1.0")
				//描述
				.description("API 描述").build();
	}
	
}
