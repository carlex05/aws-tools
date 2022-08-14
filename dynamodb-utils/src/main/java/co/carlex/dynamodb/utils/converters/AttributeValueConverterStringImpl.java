package co.carlex.dynamodb.utils.converters;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;
import java.util.function.Function;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 * Implementación para manejar los nodos String
 * @author Carlex
 */
public class AttributeValueConverterStringImpl implements AttributeValueConverter{

    @Override
    public Optional<AttributeValue> transform(JsonNode jsonNode, Function<JsonNode, AttributeValue> resolver) {
        if(jsonNode.isTextual()){
            return Optional.of(AttributeValue.fromS(jsonNode.asText()));
        }
        return Optional.empty();
    }
    
}
