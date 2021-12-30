package com.jw.log;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

public class JApiDocs {
    /**
     * 生成离线接口文档
     */
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        String projectName = "日志系统Log";
        config.setProjectPath("D:/Workspace/nacos/log/src/main/java/com/jw/log"); // 项目根目录
        config.setProjectName(projectName); // 项目名称
        config.setApiVersion(projectName);       // 声明该API的版本
        config.setDocsPath("d:/"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
