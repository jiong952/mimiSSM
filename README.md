# 简介

[米米商城后台管理系统.doc](https://www.yuque.com/attachments/yuque/0/2022/doc/12600262/1646725015240-0000d9b9-d129-4fa8-8830-d1e27b96789b.doc)

技术栈：

\1.     熟悉Spring框架的使用

\2.     熟悉SpringMVC框架的使用；

\3.     熟悉MyBatis框架的使用

\4.     掌握JSP的标签库的使用

\5.     掌握EL表达式的使用

\6.     掌握AJAX异步刷新技术的使用

\7.     掌握文件上传实现

\8.     掌握异步AJAX分页实现

\9.     熟悉企业日常的接口开发流程

\10.  熟悉企业的项目进度控制规范

\11.  建立起软件开发的基本思想和积累开发中常见的设计思路

\12.  巩固SSM框架以及JavaEE的相关知识点，增强对各个知识点的进一步认识

# 项目搭建

搭建SSM项目的步骤

## 1)新建Maven工程

![img](https://myblogimgbed.oss-cn-shenzhen.aliyuncs.com/img/202205021622181.png)

## 2)修改目录,修改pom.xml文件

![img](https://myblogimgbed.oss-cn-shenzhen.aliyuncs.com/img/202205021622461.png)

### 改web.xml

原先版本太低，不兼容el表达式

模板【可直接复制用】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
</web-app>
```

或者自己手动

1. 删除原先web.xml
2. 打开项目的module

![img](https://myblogimgbed.oss-cn-shenzhen.aliyuncs.com/img/202205021622689.png)

1. 删除原来的web.xml

![img](https://cdn.nlark.com/yuque/0/2022/png/12600262/1646725219818-ce212127-5ca6-41ca-a8e6-f4eb94151948.png)

1. 新建一个先改成web1.xml【才可以显示出来】
2. 把web1.xml改回成web.xml才能生效

### 修改index.jsp

模板

```xml
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
```

### 改pom.xml

把其他的都删除，一般企业开发依赖都是cv进来的

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.zjh</groupId>
  <artifactId>minissm</artifactId>
  <version>1.0</version>
  <packaging>war</packaging>

</project>
```

## 3)添加SSM框架的所有依赖

集中定义依赖版本号，方便管理！

```xml
<!-- 集中定义依赖版本号 -->
  <properties>
    <junit.version>4.12</junit.version>
    <spring.version>5.1.2.RELEASE</spring.version>
    <mybatis.version>3.2.8</mybatis.version>
    <mybatis.spring.version>1.2.2</mybatis.spring.version>
    <mybatis.paginator.version>1.2.15</mybatis.paginator.version>
    <mysql.version>8.0.22</mysql.version>
    <slf4j.version>1.6.4</slf4j.version>
    <druid.version>1.0.9</druid.version>
    <pagehelper.version>5.1.2</pagehelper.version>
    <jstl.version>1.2</jstl.version>
    <servlet-api.version>3.0.1</servlet-api.version>
    <jsp-api.version>2.0</jsp-api.version>
    <jackson.version>2.9.6</jackson.version>
  </properties>
  <dependencies>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.6.11</version>
    </dependency>

    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20140107</version>
    </dependency>

    <!-- spring -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jms</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- Mybatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>${mybatis.version}</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>${mybatis.spring.version}</version>
    </dependency>
    <dependency>
      <groupId>com.github.miemiedev</groupId>
      <artifactId>mybatis-paginator</artifactId>
      <version>${mybatis.paginator.version}</version>
    </dependency>
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>${pagehelper.version}</version>
    </dependency>
    <!-- MySql -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>${mysql.version}</version>
    </dependency>
    <!-- 连接池 -->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>${druid.version}</version>
    </dependency>

    <!-- junit -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>


    <!-- JSP相关 -->
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>${jstl.version}</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.0.1</version>
      <scope>provided</scope>
    </dependency>

    <!--    <dependency>-->
    <!--      <groupId>javax.servlet.jsp</groupId>-->
    <!--      <artifactId>jsp-api</artifactId>-->
    <!--      <version>2.2.1-b03</version>-->
    <!--    </dependency>-->

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jsp-api</artifactId>
      <scope>provided</scope>
      <version>${jsp-api.version}</version>
    </dependency>
    <!-- Jackson Json处理工具包 -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>${jackson.version}</version>
    </dependency>

    <!-- fastjson-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.28</version>
    </dependency>
    <!-- 文件上传用 -->
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.4</version>
    </dependency>
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>

    <!-- log4j -->
    <!--    <dependency>-->
    <!--      <groupId>log4j</groupId>-->
    <!--      <artifactId>log4j</artifactId>-->
    <!--      <version>1.2.17</version>-->
    <!--    </dependency>-->

    <!--支付宝支付-->
<!--    <dependency>-->
<!--      <groupId>com.alipay.sdk</groupId>-->
<!--      <artifactId>alipay-sdk-java</artifactId>-->
<!--      <version>4.8.10.ALL</version>-->
<!--    </dependency>-->
<!--    <dependency>-->
<!--      <groupId>commons-logging</groupId>-->
<!--      <artifactId>commons-logging</artifactId>-->
<!--      <version>1.2</version>-->
<!--    </dependency>-->
  </dependencies>

  <!-- 插件配置 -->
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
          <encoding>UTF-8</encoding>
        </configuration>
      </plugin>

    </plugins>
    <!--识别所有的配置文件-->
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.properties</include>
          <include>**/*.xml</include>
        </includes>
        <filtering>false</filtering>
      </resource>
      <resource>
        <directory>src/main/resources</directory>
        <includes>
          <include>**/*.properties</include>
          <include>**/*.xml</include>
        </includes>
        <filtering>false</filtering>
      </resource>
    </resources>
  </build>
```

## 4)拷贝jdbc.porperties到resources目录下

```xml
jdbc.driver = com.mysql.cj.jdbc.Driver
jdbc.url = jdbc:mysql://localhost:3306/xiaomissm?useUnicode=true&characterEncoding=utf8&useSSL=true
jdbc.username = root
jdbc.password = 123456
```

## 5)新建SqlMapConfig.xml文件,进行分页插件的配置

- 配置分页插件PageInterceptor

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--分页插件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
    </plugins>

</configuration>
```

## 6)新建applicationContext_dao.xml文件,进行数据访问层的配置

- 读取jdbc.properties属性文件
- 创建数据源
- 创建SqlSessionFactoryBean

- - 配置数据源
  - 配置Mybatis核心配置文件
  - 配置实体类起别名

- 创建mapper文件的扫描器，自动注入Mapper

```xml
<!--    读取jdbc.properties属性文件 -->
    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
<!--    创建数据源-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"></property>
        <property name="url" value="${jdbc.url}"></property>
        <property name="username" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
<!--    创建SqlSessionFactoryBean-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean">
<!--        配置数据源-->
        <property name="dataSource" ref="dataSource"></property>
<!--        配置Mybatis核心配置文件-->
        <property name="configLocation" value="classpath:SqlMapConfig.xml"></property>
<!--        配置实体类起别名-->
        <property name="typeAliasesPackage" value="com.zjh.pojo"></property>
    </bean>
<!--    创建mapper文件的扫描器，自动注入Mapper-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.zjh.mapper"></property>
    </bean>
```

## 7)新建applicationContext_service.xml文件,进行业务逻辑层的配置

- 设置业务逻辑层的包扫描器
- 事务管理器
- 添加事务的切面
- 完成切面和切入点的织入

```xml
<!--设置业务逻辑层的包扫描器,目的是在指定的路径下,使用@Service注解的类,Spring负责创建对象,并添加依赖-->
    <context:component-scan base-package="com.zjh.service"></context:component-scan>
<!--事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
<!--添加事务的切面-->
    <tx:advice id="myadvice" transaction-manager="transactionManager">
        <tx:attributes>
<!--            查询-->
            <tx:method name="*select*" read-only="true"/>
            <tx:method name="*find*" read-only="true"/>
            <tx:method name="*get*" read-only="true"/>
            <tx:method name="*search*" read-only="true"/>
<!--            插入-->
            <tx:method name="*insert*" propagation="REQUIRED"/>
            <tx:method name="*save*" propagation="REQUIRED"/>
            <tx:method name="*add*" propagation="REQUIRED"/>
<!--            删除-->
            <tx:method name="*delete*" propagation="REQUIRED"/>
            <tx:method name="*remove*" propagation="REQUIRED"/>
            <tx:method name="*clear*" propagation="REQUIRED"/>
<!--            更新-->
            <tx:method name="*update*" propagation="REQUIRED"/>
            <tx:method name="*modify*" propagation="REQUIRED"/>
            <tx:method name="*change*" propagation="REQUIRED"/>
            <tx:method name="*set*" propagation="REQUIRED"/>
            <tx:method name="*" propagation="SUPPORTS"/>
        </tx:attributes>
    </tx:advice>
<!--    完成切面和切入点的织入-->
    <aop:config>
        <aop:pointcut id="mypointcut" expression="execution(* com.zjh.service.*.*(..))"/>
        <aop:advisor advice-ref="myadvice" pointcut-ref="mypointcut"></aop:advisor>
    </aop:config>
```

## 8)新建springmvc.xml文件,配置springmvc的框架

- 设置包扫描器【controller】
- 设置视图解析器
- 设置文件上传核心组件
- 设置注解驱动

```xml
<!--    设置包扫描器-->
    <context:component-scan base-package="com.zjh.controller"></context:component-scan>
<!--设置视图解析器-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/admin/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
<!--    设置文件上传核心组件-->
    <bean id = "multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    </bean>
<!--    设置注解驱动-->
    <mvc:annotation-driven></mvc:annotation-driven>
```

## 9)web.xml

- 字符过滤器
- 注册spring监听器
- 注册springmvc的前端控制器【这里用*.action就不需要进行静态资源的开启了】

```xml
<!--字符编码过滤器-->
    <filter>
        <filter-name>encode</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>forceResponseEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encode</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

<!--    注册springMVC的前端控制器-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
    </servlet>
<!--    只对*.action结尾的方法进行控制，就不用释放静态资源了-->
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>*.action</url-pattern>
    </servlet-mapping>
<!--    注册spring的框架监听器-->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
<!--    加载application_*.xml文件-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext_*.xml</param-value>
    </context-param>
<!--    初始界面-->
    <welcome-file-list>
        <welcome-file>/admin/login.jsp</welcome-file>
    </welcome-file-list>
```

![img](https://cdn.nlark.com/yuque/0/2022/png/12600262/1646745117636-3afeeb96-6929-48c6-bdc1-5910985b5e13.png)

## 10)使用逆向工程生成pojo和mapper的文件

修改generatorConfig.xml文件

修改数据库的配置

```xml
<!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
		<jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
			connectionURL="jdbc:mysql://localhost:3306/xiaomissm?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true" userId="root"
			password="123456">
		</jdbcConnection>
```

改pojo mapper

```xml
<!-- 指定数据库表 -->
<table schema="" tableName="admin"></table>
<table schema="" tableName="product_info"></table>
<table schema="" tableName="product_type"></table>
```

## 11)开发业务逻辑层,实现登录判断

## 12)开发控制器AdminAction,完成登录处理

## 13)改造页面,发送登录请求,验证登录

MD5加密



# 分页展示

使用PageHelper插件

在调用mapper前加上，返回的值自动会分页处理

```
PageHelper.*startPage*(vo.getPage(),pageSize);
```

前端使用bootstrap组件



# 商品类别下拉列表

创建监听器，放入全局应用作用域中,供新增页面,修改页面,前台的查询功能提供全部商品类别集合

- @WebListener：配置监听器
- 因为无法判断此监听器和spring监听器谁先创建，因此无法使用注入，应手工从Spring容器中取出ProductTypeServiceImpl的对象.
- 要放入servletContextEvent作用域，全局共享

```java
@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //因为无法判断此监听器和spring监听器谁先创建，因此无法使用注入，应手工从Spring容器中取出ProductTypeServiceImpl的对象.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService = (ProductTypeService) context.getBean("ProductTypeServiceImpl");
        List<ProductType> list = productTypeService.getAll();
        System.out.println(list);
        //放入全局应用作用域中,供新增页面,修改页面,前台的查询功能提供全部商品类别集合
        servletContextEvent.getServletContext().setAttribute("typeList",list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
```

# 上传图片后立刻回显的操作

使用ajaxFileUpload方法

```java
function fileChange(){//注意：此处不能使用jQuery中的change事件，因此仅触发一次，因此使用标签的：onchange属性

					$.ajaxFileUpload({
						url: '/prod/ajaxImg.action',//用于文件上传的服务器端请求地址
						secureuri: false,//一般设置为false
						fileElementId: 'pimage',//文件上传控件的id属性  <input type="file" id="pimage" name="pimage" />
						dataType: 'json',//返回值类型 一般设置为json
						success: function(obj) //服务器成功响应处理函数
						{

							$("#imgDiv").empty();  //清空原有数据
							//创建img 标签对象
							var imgObj = $("<img>");
							//给img标签对象追加属性
							imgObj.attr("src","/image_big/"+obj.imgurl);
							imgObj.attr("width","100px");
							imgObj.attr("height","100px");
							//将图片img标签追加到imgDiv末尾
							$("#imgDiv").append(imgObj);
							//将图片的名称（从服务端返回的JSON中取得）赋值给文件本框
							//$("#imgName").html(data.imgName);
						},
						error: function (e)//服务器响应失败处理函数
						{
							alert(e.message);
						}
					});
				}
    //异步ajax文件上传处理
    @RequestMapping("/ajaxImg")
    @ResponseBody
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //生成存放的文件名（不可以使用用户上传的文件名，因为会重复）+文件名后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //使用文件核心上传组件进行转存到本地文件夹
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回json数据（返回存放的文件名到前端进行显示）
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }
```

一旦点击图片上传，就将该图片存到文件夹里，然后利用该文件名回显

# 区分两个路径

```xml
${pageContext.request.contextPath}
request.getServletContext().getRealPath("/image_big");
```

- ${pageContext.request.contextPath}：是在前端请求后端方法时使用的，可以获取[http://localhost:8080 /admin/login.action](http://localhost:8080/admin/login.action)标黄色的【项目请求路径】
- request.getServletContext().getRealPath("/image_big");用于生成项目在本地的路径

# 点击编辑后将商品数据回显，下拉列表框选择

1. 点击编辑按钮时利用商品的id查询全部信息，将该信息封装到ModelAndView中进行跳转【或session中】
2. 然后在update.jsp中使用el表达式读取

对于下拉框的回显

使用foreach遍历判断

```java
<select name="typeId">
<c:forEach items="${typeList}" var="type">
		<option value="${type.typeId}"
                                        <c:if test="${type.typeId==prod.typeId}">
													selected="selected"
                                            </c:if>
										>${type.typeName}</option>

									</c:forEach>
								</select>
```

# 隐藏域

在update.jsp提交时需要提交id和图片pimage，使用隐藏域

```java
<input type="hidden" value="${prod.pId}" name="pId">
<input type="hidden" value="${prod.pImage}" name="pImage">
```

# 清空saveFilename以便判断是否点击了更改

在保存和更新后要将saveFilename置为""

因为在update.jsp页面点击更新后提交的是图片隐藏域的图片名（即旧的图片名）

在productInfoAction中，如果图片发生更改，则saveFileName为新图片名称

此时就应该让

```java
if(!saveFileName.equals("")){
         productInfo.setpImage(saveFileName);
        }
```

# 利用ajax实现全选框以及取消全选

```java
    function allClick() {
        //获取全选按钮的状态
        var flag = $("#all").prop("checked");
        //使得下面复选框的选中状态与复选框相同
        $("input[name='ck']").each(function (){
            this.checked = flag;
        })
    }
    function ckClick() {
        //获取复选框的数目
        var boxLength = $("input[name='ck']").length;
        //获取选中的复选框的数目
        var boxSelectedLength = $("input[name='ck']:checked").length;
        //比较两者是否相等
        if(boxLength == boxSelectedLength){
            $("#all").prop("checked",true);
        }else {
            $("#all").prop("checked",false);
        }
    }
```