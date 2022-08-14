package co.carlex.dynamodb.utils.converters.exceptions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Excepción disparada en caso que no se pueda convertir un dato en especial
 * @author Carlex
 */
public class NoConverterException extends RuntimeException {
    
    
    public NoConverterException(JsonNode jsonNode){
        super("Error convirtiendo " + jsonNode);
    }
    
    
}
