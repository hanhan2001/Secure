package me.xiaoying.secure.configuration.serialization;

import java.util.Map;

public interface ConfigurationSerializable {
    Map<String, Object> serialize();
}