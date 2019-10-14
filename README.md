# RetrofitDemo
Retrofit的学习和使用  
Retrofit将HTTP的API转成Java接口，使用注解来描述HTTP请求。通过**@请求方法(relative  URL)**来进行注解。  
具体的说明：[Retrofit官方说明](https://square.github.io/retrofit/)  

## 使用
1. 创建一个模板类用来接收服务端发送的数据。  
```java
public class Result<T>{
	// 状态码
	private int status;
	// 状态信息
	private String message;
	// 返回的实体数据
	private T data;
	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
    }

}
```
2. 为实体数据设计类
```java
public class Data{
	// ... 
}
```
3. 创建将HTTP抽象为Java的接口
```java
public interface API{
	// get方法
	@GET("abc")
	Call<Result<Data>> getJsonData(@Query("name")String name);
	// post方法...
}
```
4. 创建Retrofit实例
```java
Retrofit retrofit = new Retrofit.Builder()
	.baseUrl("")  // 设置BaseUrl地址
	// ... 设置数据解析器
	.build();
```
5. 创建网络请求接口实例
```java
API api = retrofit.create(API.class);
// 封装请求
Call<Result<Data>> result = api.getJsonData("zhangsan");
```
6. 发送请求,通过enqueue来进行异步请求。其它的请求方法类似。


