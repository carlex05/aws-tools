package co.carlex.dynamodb.utils.converters;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;
import java.util.function.Function;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 *
 * @author Carlex
 */
public class AttributeValueConverterBooleanImpl implements AttributeValueConverter {

    @Override
    public Optional<AttributeValue> transform(JsonNode jsonNode, Function<JsonNode, AttributeValue> resolver) {
        if(jsonNode.isBoolean()){
            return Optional.of(AttributeValue.fromBool(jsonNode.asBoolean()));
        }
        return Optional.empty();
    }
    
}
