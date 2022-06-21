package com.xy.dp.factory;

public class FactoryTest {

    public static void main(String[] args) {
        String configName = "controller";
        String configAuthor = "jerry";

        Config config = new Config(configName, configAuthor);
        config.addProperties("able", true);
        config.addProperties("max_time", 60);
        config.addProperties("used", "usa");

        ConfigParser configParser = null;
        ConfigParserFactory factory = new ConfigParserFactory();

        /**
         * 通过构造工厂来生成实例
         *
         * 特点: 工厂构造的基础是同一个接口的许多不同的实现类
         *
         * 感觉实现自同一个接口是理想的，应该不是需要严格遵守的
         *
         * 但是不同的子类至少应该是存在共性的, 就像这里都是解析参数并以特点的格式展示 目的是一致的
         */

        configParser = factory.create("xml");
        System.out.println(configParser.parse(config));

        System.out.println();

        configParser = factory.create("yaml");
        System.out.println(configParser.parse(config));
    }
}
