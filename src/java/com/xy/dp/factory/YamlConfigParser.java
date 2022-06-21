package com.xy.dp.factory;

import org.springframework.util.StringUtils;

import java.util.Map;

public class YamlConfigParser implements ConfigParser{

    private static final String FORMAT = "\t%s: %s\n";

    @Override
    public String parse(Config config) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("config:\n");

        if (config == null)
            return stringBuilder.toString();

        if (StringUtils.isEmpty(config.getName()))
            return stringBuilder.toString();
        else
            stringBuilder.append(String.format(FORMAT, "name", config.getName()));

        if (StringUtils.isEmpty(config.getAuthor()))
            return stringBuilder.toString();
        else
            stringBuilder.append(String.format(FORMAT, "author", config.getAuthor()));

        Map<String, Object> k2v = config.getK2v();
        for (Map.Entry<String, Object> entry : k2v.entrySet()) {
            stringBuilder.append(String.format(FORMAT, entry.getKey(), entry.getValue()));
        }

        return stringBuilder.toString();
    }
}
