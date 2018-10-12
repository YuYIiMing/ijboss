package com.injoin.ijboss.repo.generator;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Payne.Liu
 * @version 1.0
 * @description TODO
 * @create 2018-10-12 23:54
 * @see
 */
public class MybatisGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://rm-1ud3kpql97tk6fvz7so.mysql.rds.aliyuncs.com:3306/ijboss?useUnicode=true&characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("tsa")
                .setPassword("tsa^togetU")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(false)// true 字段没有注解，false 字段有注解
                .setInclude("ij_robot_csr")//修改替换成你需要的表名，多个表名传数组
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)// 列名策略
                .setSuperEntityClass("com.injoin.ijboss.repo.entity.BaseEntity")
                .setSuperEntityColumns("create_time", "update_time", "del_flag")
        ;
        config.setActiveRecord(false)
                .setAuthor("Payne.Liu")
                .setOutputDir("/Users/liunian/project/injoinu/ijboss/repo/src/main/java/")
                .setFileOverride(true)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setEnableCache(false)
                .setIdType(IdType.AUTO)
                .setKotlin(false)
        ;

        PackageConfig pc = new PackageConfig().setParent("com.injoin.ijboss.repo");

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return "/Users/liunian/project/injoinu/ijboss/repo/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        injectionConfig.setFileOutConfigList(focList);

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(pc)
                .setCfg(injectionConfig)
                .setTemplate(new TemplateConfig().setXml(null).setController(null).setService(null).setServiceImpl(null))
                .execute();
    }
}
