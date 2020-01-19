package vw.codegurutest.infra;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import org.springframework.stereotype.Service;
import vw.codegurutest.domain.PhoneNumberRepository;


import java.util.HashMap;

@Service
public class PhoneNumberDynamoDbRepository implements PhoneNumberRepository {
    private final AmazonDynamoDB ddbClient;
    private String tableName = "phone-number";

    public PhoneNumberDynamoDbRepository(AmazonDynamoDB ddbClient) {
        this.ddbClient = ddbClient;
    }

    @Override
    public void putPhoneNumber(String name, String phoneNumber) {
        ddbClient.putItem(tableName, new HashMap<String, AttributeValue>() {{
            put("name", new AttributeValue(name));
            put("phoneNumber", new AttributeValue(phoneNumber));
        }});
    }

    @Override
    public String getPhoneNumber(String name) {
        GetItemResult record = ddbClient.getItem(tableName, new HashMap<String, AttributeValue>() {{
            put("name", new AttributeValue(name));
        }});

        try {
            return record.getItem().get("phoneNumber").getS();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("폰 넘버 없음.", e);
        }
    }
}
