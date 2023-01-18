package com.niu.mall.mbg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;



/**
 * mp 自动生成
 *
 * @author lihaojie
 * @date 2022/08/24 19:56
 **/
public class IbatisGenerate {

    public static void main(String[] args) {
        String outPutDir = System.getProperty("user.dir") + "/mall-admin/src/main/java" ;
        FastAutoGenerator.create("jdbc:mysql://10.9.9.70:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false" ,
                        "root" ,
                        "root")
                // 全局配置
                .globalConfig(builder ->
                        builder
                                .enableSwagger() // 是否启用swagger注解
                                .author("lihaojie") // 作者名称
                                .dateType(DateType.ONLY_DATE) // 时间策略
                                .commentDate("yyyy-MM-dd") // 注释日期
                                .outputDir(outPutDir) // 输出目录
                                //.fileOverride() // 覆盖已生成文件
                                .disableOpenDir() // 生成后禁止打开所生成的系统目录
                )

                // 包配置
                .packageConfig(builder ->
                                builder
                                        .parent("com.niu.mall") // 父包名
                                        .moduleName("") // 父包模块名
                                        .entity("mbg.po") // 实体类包名
                                        .service("admin.service") // service包名
                                        .serviceImpl("admin.service.impl") // serviceImpl包名
                                        .mapper("admin.dao") // mapper包名
                                        .controller("admin.controller") // controller包名
                                        //.xml("")
                                        .pathInfo(new HashMap<OutputFile,String>(){
                                            {
                                                put(OutputFile.controller,"D:\\Code\\IJ\\2022xia\\niu-mall\\mall-admin\\src\\main\\java\\com\\niu\\mall\\admin\\controller");
                                                put(OutputFile.mapper,"D:\\Code\\IJ\\2022xia\\niu-mall\\mall-admin\\src\\main\\java\\com\\niu\\mall\\admin\\dao");
                                                put(OutputFile.xml,"D:\\Code\\IJ\\2022xia\\niu-mall\\mall-admin\\src\\main\\resources\\com\\niu\\mall\\admin\\dao");
                                                put(OutputFile.service,"D:\\Code\\IJ\\2022xia\\niu-mall\\mall-admin\\src\\main\\java\\com\\niu\\mall\\admin\\service");
                                                put(OutputFile.serviceImpl,"D:\\Code\\IJ\\2022xia\\niu-mall\\mall-admin\\src\\main\\java\\com\\niu\\mall\\admin\\service\\impl");
                                                put(OutputFile.entity,"D:\\Code\\IJ\\2022xia\\niu-mall\\mall-admin\\src\\main\\java\\com\\niu\\mall\\mbg\\po");

                                            }
                                        })
                )
                /*.templateConfig(builder ->
                        builder
                                .disable(TemplateType.ENTITY)
                                .entity("/templates/entity.java")
                                .service("/templates/service.java")
                                .serviceImpl("/templates/serviceImpl.java")
                                .mapper("/templates/mapper.java")
                                .xml("/templates/mapper.xml")
                                .controller("/templates/controller.java")
                        )*/
                // 策略配置
                .strategyConfig(builder ->
                        builder
                                //.addTablePrefix("t_") // 增加过滤表前缀
                                //.addTableSuffix("_db") // 增加过滤表后缀
                                //.addFieldPrefix("t_") // 增加过滤字段前缀
                                //.addFieldSuffix("_field") // 增加过滤字段后缀
                                //.addInclude("help") // 表匹配

                                // Entity 策略配置
                                .entityBuilder()
                                .enableLombok() // 开启lombok
                                .enableChainModel() // 链式
                                .enableRemoveIsPrefix() // 开启boolean类型字段移除is前缀
                                .enableTableFieldAnnotation() //开启生成实体时生成的字段注解
                                .versionColumnName("version") // 乐观锁数据库字段
                                .versionPropertyName("version") // 乐观锁实体类名称
                                //.logicDeleteColumnName("is_deleted") // 逻辑删除字段名(数据库)
                                //.logicDeletePropertyName("deleted") // 逻辑删除实体类中的字段名
                                .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略  默认：表名 下划线命名 变——》 驼峰命名
                                .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略  默认为 null，未指定按照 naming 执行 如今：字段名 下划线 -》 驼峰命名
                                .idType(IdType.ASSIGN_ID) // 全局主键类型： 雪花算法生成id
                                .formatFileName("%sPo") // 格式化文件名称
                                //.addTableFills(new Column("create_time", FieldFill.INSERT)) // 添加表字段填充
                                //.addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE)) // 添加表字段填充  list集合
                                .enableColumnConstant()  //开启生成字段常量
                                .enableActiveRecord()

                                // Controller 策略配置
                                .controllerBuilder()
                                //.superClass(BaseController.class)  BaseController.class开启驼峰转连字符
                                //.enableHyphenStyle() //开启驼峰转连字符
                                .enableRestStyle() // 开启生成@RestController 控制器
                                .formatFileName("%sController") // 格式化文件名称	结果：XXXController

                                // Service 策略配置
                                .serviceBuilder()
                                .superServiceClass(IService.class)   //设置 service 接口父类  ：BaseService.class
                                .superServiceImplClass(ServiceImpl.class)
                                //superServiceClass(String)               设置 service 接口父类  ：com.baomidou.global.BaseService
                                .formatServiceFileName("%sService") // 格式化Service 文件名称
                                .formatServiceImplFileName("%sServiceImpl") // 格式化ServiceImpl 文件名称

                                // Mapper 策略配置
                                .mapperBuilder()
                                .enableMapperAnnotation() // 开启@Mapper
                                //.enableBaseColumnList() // 启用 columnList (通用查询结果列)
                                .enableBaseResultMap() // 启动resultMap
                                //.cache(Class<? extends Cache>)  设置缓存实现类  MyMapperCache.class
                                .formatMapperFileName("%sDao") // Mapper 文件名称
                                .formatXmlFileName("%sDao") // Xml 文件名称
                ).execute();


    }
}




