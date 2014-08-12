package com.stock.util;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpReq {
	public static String Get(String reqUrl){
		HttpClient client = new HttpClient();
		String result = "";
		// 设置代理服务器地址和端口(第一个参数是代理服务器地址，第二个参数是端口号。)
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);

		// 创建GET方法的实例，在GET方法的构造函数中传入待连接的地址即可。
		// 用GetMethod将会自动处理转发过程，如果想要把自动处理转发过程去掉的话，可以调用方法setFollowRedirects(false)。
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod getMethod = new GetMethod(reqUrl);

		// 调用实例httpClient的executeMethod方法来执行getMethod。
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode;
		try {
			statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			/**
			 * 在返回的状态码正确后，即可取得内容。取得目标地址的内容有三种方法：
			 * 第一种，getResponseBody，该方法返回的是目标的二进制的byte流；
			 * 第二种，getResponseBodyAsString
			 * ，这个方法返回的是String类型，值得注意的是该方法返回的String的编码是根据系统默认的编码方式
			 * ，所以返回的String值可能编码类型有误，在本文的"字符编码"部分中将对此做详细介绍；
			 * 第三种，getResponseBodyAsStream
			 * ，这个方法对于目标地址中有大量数据需要传输是最佳的。在这里我们使用了最简单的getResponseBody方法。
			 */
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
		
			// 处理内容
			
			//String abc = new String(responseBody, "GBK");
			result = new String(responseBody, "GBK");
			//System.out.println(abc);
			//String aaa = new String(abc.getBytes("utf-8"), "UTF-8");
			//System.out.println(aaa);
			//System.out.println(new String(responseBody, "GBK"));
			//System.out.println(new String(ab.getBytes("GBK"),"utf-8"));
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		//System.out.println(result);
		//System.out.println("***********************");
		return result; 
	}
}
