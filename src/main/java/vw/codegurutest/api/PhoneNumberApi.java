package vw.codegurutest.api;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;
import vw.codegurutest.domain.PhoneNumberRepository;


@RestController
public class PhoneNumberApi {
    private final PhoneNumberRepository repo;

    public PhoneNumberApi(PhoneNumberRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/v1/phone-number")
    public void putPhoneNumber(@RequestBody PhoneNumberEntry entry) {
        repo.putPhoneNumber(entry.getName(), entry.getPhoneNumber());
    }

    @GetMapping("/v1/phone-number")
    public String test(@RequestParam String name) {
        return repo.getPhoneNumber(name);
    }

    @ToString
    @Getter
    static class PhoneNumberEntry {
        private String name;
        private String phoneNumber;
    }
}
