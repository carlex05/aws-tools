package co.carlex.dynamodb.utils.converters;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;
import java.util.function.Function;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 * Implementación de converter para números
 * @author Carlex
 */
public class AttributeValueConverterNumberImpl implements AttributeValueConverter{

    /**
     * @@inheritDoc 
     * @param jsonNode
     * @return 
     */
    @Override
    public Optional<AttributeValue> transform(JsonNode jsonNode, Function<JsonNode, AttributeValue> resolver) {
        if(jsonNode.isNumber()){
            return Optional.of(AttributeValue.fromN(jsonNode.asText()));
        }
        return Optional.empty();
    }
    
}
