package tool.swagger.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 组1（组可以配置多个）
     * */
    @Bean
    public Docket coreApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                //swagger开启与否（注意控制开启与否只针对当前组，默认都是开启的）
                .enable(true)
                //API文档的基本信息
                .apiInfo(adminApiInfo())
                //组名
                .groupName("组1")
                //扫描信息
                .select()
                //匹配生成文档的包路径
                .apis(RequestHandlerSelectors.basePackage("tool.swagger"))
                //匹配请求url路径
                .paths(PathSelectors.ant("/swagger1/*"))
                .build();
    }

    /**
     * 组2
     * */
    @Bean
    public Docket coreApiConfig2(){
        return new Docket(DocumentationType.SWAGGER_2)
                //swagger开启与否（注意控制开启与否只针对当前组，默认都是开启的）
                .enable(true)
                //API文档的基本信息
                .apiInfo(adminApiInfo())
                //组名
                .groupName("组2")
                //扫描信息
                .select()
                //匹配生成文档的包路径
                .apis(RequestHandlerSelectors.basePackage("tool.swagger"))
                //匹配请求url路径
                .paths(PathSelectors.ant("/swagger2/*"))
                .build();
    }

    /**
     * API文档的基本信息
     * */
    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("Swagger学习-API")
                //描述
                .description("接口文档信息")
                //版本
                .version("1.0")
                //作者信息
                .contact(new Contact("测试","https://baidu.com","1442018432@qq.com"))
                //许可证
                .license("Apache 2.0")
                //许可证地址
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}

