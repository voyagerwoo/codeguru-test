package vw.codegurutest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vw.codegurutest.infra.DynamoDbChecker;

@RestController
public class CheckApi {
    private final DynamoDbChecker dynamoDbChecker;

    public CheckApi(DynamoDbChecker dynamoDbChecker) {
        this.dynamoDbChecker = dynamoDbChecker;
    }

    @GetMapping("/check/dynamodb/phone-number")
    public String check() {
        return dynamoDbChecker.check("phone-number").getTableStatus();
    }
}
