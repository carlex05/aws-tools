package co.carlex.dynamodb.utils;

import co.carlex.dynamodb.utils.converters.*;
import java.util.List;

/**
 * 
 * @author Carlex
 */
public interface AttributeValueConverterProvider {
    
    static final DefaultAttributeConverterProvider defaultProvider = new DefaultAttributeConverterProvider();
    
    /**
     * Retorna una lista de implementaciones de {@link AttributeValueConverter}
     * @return 
     */
    List<AttributeValueConverter> getConverters();
    
    static AttributeValueConverterProvider getDefaultProvider(){
        return defaultProvider;
    }
    
    static class DefaultAttributeConverterProvider implements AttributeValueConverterProvider{
        
        private DefaultAttributeConverterProvider(){}
        
        final List<AttributeValueConverter> converters = List.of(new AttributeValueConverterNumberImpl(),
            new AttributeValueConverterBooleanImpl(),
            new AttributeValueConverterStringImpl(),
            new AttributeValueConverterListImpl(),
            new AttributeValueConverterObjectImpl());
        
        
        @Override
        public List<AttributeValueConverter> getConverters() {
            return converters;
        }
        
    }
    
}
