package co.carlex.dynamodb.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 *
 * @author Carlex
 */
public class DynamodbObjectConverterTest {
    
    public DynamodbObjectConverterTest() {
    }

    public void testToAttributeValue_m() throws Exception {
        DynamoDbObjectConverter converter = DynamoDbObjectConverter.builder().setDefaultConverterProvider().build();
        JsonNode jsonNode = new ObjectMapper().readTree("{\"data\": \"123\", \"si\": { \"deep1\":\"789\", \"deep12\":\"yuui\"}, \"list1\": [\" 1\", \" 2\", \"3\", \"4\"]}");
        AttributeValue itemjson = converter.toAttributeValue(jsonNode);
        assertTrue(itemjson.hasM());
        assertEquals(3, itemjson.m().size());
        assertNotNull(itemjson.m().get("data"));
        assertNotNull(itemjson.m().get("si"));
        assertNotNull(itemjson.m().get("list1"));
    }
    
    public void testToAttributeValue_array() throws Exception {
        DynamoDbObjectConverter converter = DynamoDbObjectConverter.builder().setDefaultConverterProvider().build();
        JsonNode jsonNode = new ObjectMapper().readTree("[\" 1\", \" 2\", \"3\", 4]");
        AttributeValue itemjson = converter.toAttributeValue(jsonNode);
        assertTrue(itemjson.hasL());
        assertEquals(4, itemjson.l().size());
        assertEquals(" 2", itemjson.l().get(1).s());
        assertEquals("4", itemjson.l().get(3).n());
    }
    
    public void testToAttributeValue_boolean() throws Exception {
        DynamoDbObjectConverter converter = DynamoDbObjectConverter.builder().setDefaultConverterProvider().build();
        JsonNode jsonNode = new ObjectMapper().readTree("true");
        AttributeValue itemjson = converter.toAttributeValue(jsonNode);
        assertTrue(itemjson.bool());
    }
    
    public void testToAttributeValue_integer() throws Exception {
        DynamoDbObjectConverter converter = DynamoDbObjectConverter.builder().setDefaultConverterProvider().build();
        JsonNode jsonNode = new ObjectMapper().readTree("125");
        AttributeValue itemjson = converter.toAttributeValue(jsonNode);
        assertEquals("125", itemjson.n());
    }
    
    public void testToAttributeValue_string() throws Exception {
        DynamoDbObjectConverter converter = DynamoDbObjectConverter.builder().setDefaultConverterProvider().build();
        JsonNode jsonNode = new ObjectMapper().readTree("\"str\"");
        AttributeValue itemjson = converter.toAttributeValue(jsonNode);
        assertEquals("str", itemjson.s());
    }
    
}
