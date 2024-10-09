# Jsp 笔记

## 一、Jsp语法

### 1、基本语法

- `<%  %>`

  `<%  %>`中用于书写Java代码

  ```jsp
  <!-- 用法1：书写基本的Java代码-->
  <% System.out.printLn("Hello"); %>
  
  <!-- 用法2：组合Html标签使用 -->
  <% for(int i = 0;i < 10;i++){ %>
  	<span>123</span>
  <% } %>
  ```

- `<%!  %>`

  `<%!  %>`中用于声明Java代码中使用的变量或者方法

  ```jsp
  <!-- 用法1：声明变量-->
  <%! int a = 1; %>
  <% out.printLn(a); %>
  
  <!-- 用法2：声明方法-->
  <%!
     public int add(int a,int b){
         return a+b;
     }
  %>
  <% out.printLn(add(1,2)); %>
  ```

- `<%=  %>`

  `<%=  %>`用于将内容直接输出到页面上 等同于 <% out.printLn(); %>

  ```jsp
  <%= "内容" %>
  ```

  

### 2、三大指令
