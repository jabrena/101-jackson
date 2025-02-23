package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import io.vavr.collection.Map;

public class VavrMapMergeModule extends SimpleModule {
    
    public VavrMapMergeModule() {
        super("VavrMapMergeModule", Version.unknownVersion());
        setDeserializerModifier(new VavrMapDeserializerModifier());
    }
    
    private static class VavrMapDeserializerModifier extends BeanDeserializerModifier {
        @Override
        public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config,
                                                     BeanDescription beanDesc,
                                                     JsonDeserializer<?> deserializer) {
            if (Map.class.isAssignableFrom(beanDesc.getBeanClass())) {
                return new StringStringMergingVavrMapDeserializer();
            }
            return deserializer;
        }
    }
} 