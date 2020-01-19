package vw.codegurutest.infra;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;
import org.springframework.stereotype.Service;

@Service
public class DynamoDbChecker {
    private final AmazonDynamoDB ddbClient;
    private long waitTimeSeconds = 5000;

    public DynamoDbChecker(AmazonDynamoDB ddbClient) {
        this.ddbClient = ddbClient;
    }


    public TableDescription check(String tableName) {
        long elapsedMs;
        long startTimeMs = System.currentTimeMillis();
        do {
            DescribeTableResult describe = ddbClient.describeTable(new DescribeTableRequest().withTableName(tableName));
            String status = describe.getTable().getTableStatus();
            if (TableStatus.ACTIVE.toString().equals(status)) {
                return describe.getTable();
            }

            sleep(10 * 1000);
            elapsedMs = System.currentTimeMillis() - startTimeMs;
        } while (elapsedMs / 1000.0 < waitTimeSeconds);

        throw new IllegalArgumentException("조회 실패.");
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Sleep error", e);
        }
    }
}
