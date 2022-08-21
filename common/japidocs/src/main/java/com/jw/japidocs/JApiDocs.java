package com.jw.japidocs;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * 生成离线接口文档
 */
public class JApiDocs {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        //接口文档文件夹名称
        String projectName = "ums";
        //项目根路径
        config.setProjectPath("D:/workspace/nacos/system/ums/src/main/java/com/jw/ums");
        //项目名称
        config.setProjectName(projectName);
        //接口文档版本
        config.setApiVersion(projectName);
        //生成的接口文档存放到哪里
        config.setDocsPath("D:/");
        //配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        //执行生成离线接口文档
        Docs.buildHtmlDocs(config);
    }
}
