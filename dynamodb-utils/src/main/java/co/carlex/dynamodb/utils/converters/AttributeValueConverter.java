package co.carlex.dynamodb.utils.converters;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;
import java.util.function.Function;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 *
 * @author Carlex
 */
public interface AttributeValueConverter {
    
    /**
     * Si la implementación del converter logra convertir el jsonNode entonces 
     * debe retornarlo, en caso contrario debe retornar {@link Optional#empty() }
     * @param jsonNode
     * @param resolver en caso de necesitar resolver un jsonNode anidado este 
     * parámetro permitiría resolver el dato a AttributeValue
     * @return Un {@link Optional} con el {@link AttributeValue} del jsonNode, 
     * si la implementación actual no lo puede convertir entonces debe retornar
     * {@link Optional#empty() }
     */
    Optional<AttributeValue> transform(JsonNode jsonNode, Function<JsonNode, AttributeValue> resolver);
    
}
