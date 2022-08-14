package co.carlex.dynamodb.utils.converters;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 *
 * @author Carlex
 */
public class AttributeValueConverterObjectImpl implements AttributeValueConverter{

    @Override
    public Optional<AttributeValue> transform(JsonNode jsonNode, Function<JsonNode, AttributeValue> resolver) {
        if(jsonNode.isObject()){
            Map<String, AttributeValue> nodeFields = new HashMap<>();
            jsonNode.fields().forEachRemaining(field -> nodeFields.put(field.getKey(), resolver.apply(field.getValue())));
            return Optional.of(AttributeValue.fromM(nodeFields));
        }
        return Optional.empty();
    }
    
}
