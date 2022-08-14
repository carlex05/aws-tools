package co.carlex.dynamodb.utils;

import co.carlex.dynamodb.utils.converters.AttributeValueConverter;
import co.carlex.dynamodb.utils.converters.exceptions.NoConverterException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 *
 * @author Carlex
 */
public final class DynamoDbObjectConverter {

    private final AttributeValueConverterProvider converterProvider;
    
    private DynamoDbObjectConverter(Builder builder){
        this.converterProvider = builder.getConverterProvider();
    }
    
    /**
     * Build an instance of {@link AttributeValue} with jsonNode data, this 
     * could be and object, array or an single value
     * @param jsonNode
     * @return 
     */
    public AttributeValue toAttributeValue(final JsonNode jsonNode) throws NoConverterException{
        for(AttributeValueConverter converter : converterProvider.getConverters()){
            Optional<AttributeValue> optAttributeValue = converter.transform(jsonNode, (node) -> (this.toAttributeValue(node)));
            if(!optAttributeValue.isEmpty()){
                return optAttributeValue.get();
            }
        }
        throw new NoConverterException(jsonNode);
    }
    
    /**
     * Construye un Builder para construir un DynamoDbObjectConverter
     * @return 
     */
    public static Builder builder(){
        return new BuilderImpl();
    }
    
    public interface Builder{
        Builder setConverterProvider(AttributeValueConverterProvider provider);
        Builder setDefaultConverterProvider();
        AttributeValueConverterProvider getConverterProvider();
        DynamoDbObjectConverter build();
    }
    
    static class BuilderImpl implements Builder{

        AttributeValueConverterProvider provider;
        
        @Override
        public Builder setConverterProvider(AttributeValueConverterProvider provider) {
            this.provider = provider;
            return this;
        }

        @Override
        public Builder setDefaultConverterProvider() {
            this.provider = AttributeValueConverterProvider.getDefaultProvider();
            return this;
        }
        
        @Override
        public AttributeValueConverterProvider getConverterProvider(){
            return provider;
        }

        @Override
        public DynamoDbObjectConverter build() {
            return new DynamoDbObjectConverter(this);
        }
        
    }
}
