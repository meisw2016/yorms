package cn.com.yusys.console.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.com.yusys.console.util.RedisUtil;
import cn.com.yusys.file.util.StringUtil;

@Component
public class MyZuulFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(MyZuulFilter.class);

	private static int ExpireTime = 600; // redis中存储的过期时间60s

	@Resource
	private RedisUtil redisUtil;


	/**
	 * shouldFilter则表示该filter是否需要执行 true表示请求走此过滤器，开启 false表示请求不在此过滤器，关闭
	 * 
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * filterOrder表示执行的优先级，值越小表示优先级越高 zuul-filer可以写多个，用此方法来标示执行的优先级
	 */
	@Override
	public int filterOrder() {
		return 0;

	}

	/**
	 * filterType表示filter执行的时机 pre:在请求被路由之前调用 routing: 在请求被路由之中调用 post: 在请求被路由之后调用
	 * error: 处理请求发生错误时调用
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	/**
	 * 过滤器的具体逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();

		log.info("send {} request to {}", request.getMethod(), request.getRequestURI().toString());

		String accessToken = request.getHeader("x-token");
		String userId = request.getHeader("useId");

		try {
			String uri = request.getRequestURI();

			// 是否登录认证
			Boolean isIgnore = isIgore(uri);
			if (!isIgnore) {
				ctx.setSendZuulResponse(true);// 对该请求进行路由
				ctx.setResponseStatusCode(200);
				return null;
			}

			// 前端是否传入header
			if (!StringUtil.isBlank(accessToken) && !StringUtil.isBlank(userId)) {

				if (redisUtil.get(userId) == null) {
					System.out.println("-----User not logged in or expired!-----");
					// 无响应信息
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(401);
					ctx.setResponseBody("-----User not logged in or expired!-----");
					// 阻止向下游执行
					return 0;
				} else {
					// 传送参数是否正确
					if (redisUtil.get(userId).equals(accessToken)) {

						// 是否刷新token过期时间
						Boolean isRefreshExpire = isRefreshExpire(uri);
						if (isRefreshExpire) {
							// 检查过期时间并刷新
							checkLoginWithExpire(userId);
						} else {
							// 检查过期时间不刷新
							checkLoginWithoutExpire(userId);
						}

						// 设置传入后台token内容
						ctx.addZuulRequestHeader("x-token", accessToken);
						// 设置当前用户的角色信息、用户等级...
						ctx.addZuulRequestHeader("useId", userId);
						return null;

					} else {
						// System.out.println("-----access token error!-----");
						// 无响应信息

						ctx.setSendZuulResponse(false);
						ctx.setResponseStatusCode(401);
						ctx.setResponseBody("-----access token error!-----"); 
						// 阻止向下游执行 
						return 0;

					}
				}

			} else {
				//System.out.println("-----access token is null!-----");
				/*
				 * // 无响应信息 ctx.setSendZuulResponse(false); ctx.setResponseStatusCode(401);
				 * ctx.setResponseBody("-----access token is null!-----"); // 阻止向下游执行 return 0;
				 */
				// 无用户信息及token 设置默认用户
				
				ctx.addZuulRequestHeader("x-token", "defaultToken");
				// 设置当前用户的角色信息、用户等级...
				ctx.addZuulRequestHeader("useId", "1");
				
				redisUtil.set("1", "defaultToken",ExpireTime);
				
				return null;

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("-----Internal server error!-----");
			e.printStackTrace();
			// 无响应信息
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(500);
			ctx.setResponseBody("-----Internal server error!-----");

			return 0;
		}

	}

	/**
	 * 是否登录认证
	 * 
	 * @param uri 请求接口标志
	 * @return
	 */
	private Boolean isIgore(String uri) {
		return true;
	}

	/**
	 * 是否刷新过期时间
	 * 
	 * @param uri
	 * @return
	 */
	private Boolean isRefreshExpire(String uri) {
		return true;
	}

	/**
	 * 检查过期时间，并刷新过期时间
	 * 
	 * @param userId
	 * @return
	 */
	private void checkLoginWithExpire(String userId) {
		redisUtil.expire(userId, ExpireTime);
	}

	/**
	 * 检查过期时间 不刷新
	 * 
	 * @param sessionId
	 * @return
	 */
	private void checkLoginWithoutExpire(String userId) {

	}

}
