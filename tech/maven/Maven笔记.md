# Maven 笔记

## 1、maven坐标

```xml
<!-- 
	通过groupId，artifactId，version可以做到唯一定位依赖
	1、groupId：模块包路径（项目构建完成之后便不再变化）
	2、artifactId：模块名（项目构建完成之后便不再变化）
	3、version：版本（项目上线部署的时候需要改变）
-->
<groupId>com.yg</groupId>
<artifactId>sb_template</artifactId>
<version>0.0.1-SNAPSHOT</version>

<!--
	scope标签的作用：声明依赖的作用域
	1、compile：默认值-所有地方均可以使用该依赖
	2、test：test文件夹下可以使用
	举个例子来说，假如说某个依赖加了test这个作用域，那么在main文件夹下就无法使用该依赖所提供的类，接口以及注解。如果不知道用哪个作用域，使用默认值compile就一定不会错
	注意：只有scope的属性是compile的时候，打包的时候依赖才会被打入jar中。比方说现在存在一个项目A，项目A中引入了依赖B，那么在给项目A进行打包的时候只有依赖B的scope属性是compile才会被打入jar中
-->
<scope>compile</scope>
```



## 2、pom标签

1. packaging

   ```xml
   <!-- 
   	1、jar：默认值-不写packaging标签默认为jar
   	2、war：war包（添加war属性之后idea会自动构建web工程，也就是获取main=>webapp=>WEB-INF=>web.xml）
   	3、pom：不打包，作为父模块
   -->
   <packaging>jar</packaging>
   ```

2. properties

   ```xml
   <!-- 声明变量：可以在xml的其他地方通过${}引用 -->
   <properties>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <maven.compiler.target>1.8</maven.compiler.target>
       <maven.compiler.source>1.8</maven.compiler.source>
       <junit.version>5.9.2</junit.version>
   </properties>
   
   <!-- 引用 -->
   <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
   </dependency>
   ```

3. dependencyManagement

   ```xml
   <!-- 
   	1、在dependencyManagement中声明的依赖不会被真正的引用，只做依赖声明，也就说声明的这些依赖子模块中可能用到也可能用不到，一旦用到并且不指定版本的话默认使用父模块中依赖的版本，如果手动指定了版本那么以手动指定的版本为准
   	2、在这里声明依赖版本之后子模块中引入依赖的时候便可以不用声明version 
   -->
   
   <!-- 父模块声明：此时父模块并没有真正的引入（打包的话不存在该依赖的jar） -->
   <dependencyManagement>
      <dependencies>
          <dependency>
               <groupId>com.google.code.gson</groupId>
               <artifactId>gson</artifactId>
               <version>2.10.1</version>
           </dependency>
      </dependencies>
   </dependencyManagement>
   
   <!-- 子模块引入：不需要声明版本 -->
   <dependencies>
       <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
        </dependency>
   </dependencies>
   ```
   
4. 



## 3、maven特性

1. 依赖传递

   <font color=red>引入依赖的时候会将引入依赖的依赖也一起引入</font>

   现在有如下依赖关系：

   ```
   A.jar -> B.jar -> C.jar
   ```

   现在存在一个项目D，项目D中需要用到A.jar,B.jar,C.jar，此时引入依赖的时候只需要引入A.jar即可，而不是说将A，B，C三个都引入一遍。

   因为A依赖B那么A.jar中就含有B.jar，B依赖C那么B.jar中就含有C.jar，所以引入A.jar相当于引入了B.jar和C.jar

   注意：B和C这两个jar的作用范围(scope)必须得是compile，这样A在打包的时候B就会被打入A中，B在打包的时候C就会被打入B中

2. 依赖冲突

   <font color=red>依赖冲突就是一个项目中引入了重复的依赖</font>

   现在又如下依赖关系：

   ```
   A -> B(1.0)
   C -> B(2.0)
   ```

   现在存在一个项目D，项目D依赖A和C，那么根据maven的依赖传递，B(1.0)和B(2.0)也会被一起引入D，此时就会出现依赖冲突。当然maven可以自己解决依赖冲突问题。

   <hr>

   **<u>maven解决依赖冲突的原则：</u>**

   - 第一原则："谁短用谁"

     ```
     A -> F -> B(1.0)
     C -> B(2.0)
     ```

     此时D依赖A和C，那么D就会优先检测从A到B和从C到B那么依赖关系的距离更短，优先使用距离短的，也就是B(2.0)

   - 第二原则："谁先用谁"

     ```
     A -> B(1.0)
     C -> B(2.0)
     ```

     如果依赖的路径长度一样，那么按照A和C在pom文件中出现的先后顺序，优先使用先出现，也就是B(1.0)

3. 模块继承

   <font color=red>模块继承就是子模块会继承父模块中的依赖。如果子模块中没有父模块中有使用父模块的，如果子模块和父模块中都有那么使用子模块自己的</font>

   ```xml
   <!-- 在子模块中添加该配置就代表子模块继承父模块的依赖，构建了继承关系 -->
   <parent>
       <artifactId>Payment</artifactId>
       <groupId>com.roba</groupId>
       <version>1.0.0</version>
   </parent>
   ```

   

   - 问题引出：项目A中含有三个子模块B，C，D。三个自模块分别给不同的人开发，而在B和C中都要用到依赖E.jar，但是由于两人没有及时的沟通导致B使用E(1.0)，C使用E(2.0)

   - 问题解决：为了避免同一个依赖在不同的模块下被引入不同的版本，可以在父模块中引入E(1.0)，那么所有的子模块下都会引入E(1.0)就不会出现不同模块引入同一个依赖的不同版本问题

   - 问题解决优化：在上述解决方式中存在的一个问题是，B，C，D三个模块只有B和C使用了依赖E，如果在父模块中引入E，那么不需要E的D中也会被动的引入，D在打包的时候引入将E打入。如果频繁出现上述的问题，那么就会导致D的jar包中无用的jar越来越多，D的jar也会越来越大。此时可以在父模块中使用依赖管理声明，作用是声明可能用到的依赖及其版本，而不真正的引入依赖。

     ```xml
     <!-- 
     	1、在dependencyManagement中声明的依赖不会被真正的引用，只做依赖声明，也就说声明的这些依赖子模块中可能用到也可能用不到，一旦用到并且不指定版本的话默认使用父模块中依赖的版本，如果手动指定了版本那么以手动指定的版本为准
     	2、在这里声明依赖版本之后子模块中引入依赖的时候便可以不用声明version 
     -->
     <dependencyManagement>
        <dependencies>
            <dependency>
            </dependency>
        </dependencies>
     </dependencyManagement>
     ```

4. 模块聚合

   <font color=red>聚合就是父模块可以统一的管理子模块，比方说clean，package等插件的执行，只要父模块执行了，那么聚合在父模块下的子模块也会执行</font>

   ```xml
   <!-- 在父模块中使用该配置就意味着modules下的所有模块聚合到父模块，由父模块统一管理 -->
   <modules>
       <module>pms</module>
       <module>oms</module>
       <module>agent</module>
   </modules>
   ```



## 4、依赖排除问题

现在存在如下依赖关系：

```
A -> B -> C
```

由于依赖的传递性，A依赖B的同时间接的依赖的C。如果A想不依赖C可做如下设置

```xml
<dependency>
	<groupId>sample.ProjectB</groupId>
	<artifactId>Project-B</artifactId>
	<version>1.0</version>
	<exclusions>
	  <exclusion>  <!--排除C依赖 declare the exclusion here -->
	    <groupId>sample.ProjectC</groupId>
	    <artifactId>Project-C</artifactId>
	  </exclusion>
	</exclusions> 
</dependency>

<!-- A的pom文件 -->
<dependencies>
    <!-- B的依赖 -->
    <dependency>
        <groupId>com.test</groupId>
		<artifactId>B</artifactId>
		<version>1.0</version>
        <!-- 通过exclusions标签可以排除B中的多个依赖（C） -->
        <exclusions>
	  		<exclusion> 
	    		<groupId>com.test</groupId>
	    		<artifactId>C</artifactId>
	  		</exclusion>
		</exclusions> 
    </dependency>
</dependencies>
```

