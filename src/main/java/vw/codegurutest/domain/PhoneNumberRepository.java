package vw.codegurutest.domain;

public interface PhoneNumberRepository {
    void putPhoneNumber(String name, String phoneNumber);
    String getPhoneNumber(String name);
}
