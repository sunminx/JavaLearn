package com.xy.dp.factory;

public class ConfigParserFactory {

    private static final String XML_TAG = "xml";
    private static final String YAML_TAG = "yaml";

    public ConfigParser create(String tag) {
        ConfigParser configParser = null;

        switch (tag.toLowerCase()) {
            case XML_TAG:
                configParser = new XmlConfigParser();
                break;
            case YAML_TAG:
                configParser = new YamlConfigParser();
                break;
            default:
        }

        return configParser;
    }
}
