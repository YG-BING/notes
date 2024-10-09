# Kaptcha 笔记

## 1、依赖

```
<!--kaptcha 图片验证码-->

<dependency>
	<groupId>com.github.penggle</groupId>
	<artifactId>kaptcha</artifactId>
	<version>2.3.2</version>
</dependency>
```

## 2、配置类

```java
@Configuration
public class KaptchConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "black");
        //边框厚度
        properties.setProperty("kaptcha.border.thickness", "1");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "150");
        // 图片高
        properties.setProperty("kaptcha.image.height", "60");
        //图片实现类
        properties.setProperty("kaptcha.producer.impl", "com.google.code.kaptcha.impl.DefaultKaptcha");
        //文本实现类
        properties.setProperty("kaptcha.textproducer.impl", "tool.kaptcha.textCreator.NumberTextCreator");
        //文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //干扰实现类(DefaultNoise) 去除干扰(NoNoise)
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        //干扰颜色
        properties.setProperty("kaptcha.noise.color", "blue");
        //干扰图片样式
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        //背景实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.background.clear.to", "white");
        //文字渲染器
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
```

## 3、生成验证码

```java
@RequestMapping(value = "/getCode",method = RequestMethod.GET)
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 响应配置
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");

        // 生成文字验证码
        String text = defaultKaptcha.createText();
        log.info("验证码：{}",text);

        // 将验证码存放至redis
        log.info("【获取验证码】用户IP:{}",request.getRemoteAddr());
        redisTemplate.opsForValue().set("captcha:"+request.getRemoteAddr(),text,2*60, TimeUnit.SECONDS);

        // 生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        out.flush();
    }
```

