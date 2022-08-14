package co.carlex.dynamodb.utils.converters;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 *
 * @author Carlex
 */
public class AttributeValueConverterListImpl implements AttributeValueConverter{

    @Override
    public Optional<AttributeValue> transform(JsonNode jsonNode, Function<JsonNode, AttributeValue> resolver) {
        if(jsonNode.isArray()){
            List<AttributeValue> items = new ArrayList<>();
            jsonNode.elements().forEachRemaining((itemNode) -> items.add(resolver.apply(itemNode)));
            return Optional.of(AttributeValue.fromL(items));
        }
        return Optional.empty();
    }
    
}
