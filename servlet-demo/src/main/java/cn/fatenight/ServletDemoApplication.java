package cn.fatenight;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * 主方法
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2021/10/29
 */
public class ServletDemoApplication {

    public static void main(String[] args) throws Exception {
        String userDir = System.getProperty("user.dir") + File.separator + "servlet-demo";
        String tomcatBaseDir = userDir + File.separatorChar + "tomcat";
        String webappDir = userDir + File.separatorChar + "target" + File.separatorChar + "classes";

        // 启动Tomcat:
        Tomcat tomcat = new Tomcat();
        // 设置端口号
        tomcat.setPort(8090);
        // 设置工作目录
        tomcat.setBaseDir(tomcatBaseDir);

        // 设置连接器;Tomcat 9.0 必须调用 Tomcat#getConnector() 方法之后才会监听端口
        tomcat.getConnector();

        // 创建webapp:
        // contextPath要使用的上下文映射，""表示根上下文
        // docBase上下文的基础目录，用于静态文件。相对于服务器主目录必须存在 ({主目录}/webapps/{docBase})
        Context context = tomcat.addWebapp("", webappDir);

        // 支持WebServlet注解
        WebResourceRoot resources = new StandardRoot(context);
        DirResourceSet webResourceSet = new DirResourceSet(resources, "/WEB-INF/classes",
                new File("servlet-demo/target/classes").getAbsolutePath(), "/");
        resources.addPreResources(webResourceSet);
        context.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}
