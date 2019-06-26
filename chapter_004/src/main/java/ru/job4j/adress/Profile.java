package ru.job4j.adress;



import java.util.List;
import java.util.stream.Collectors;

public class Profile {
    private Address address;

    public Profile() {
    }


    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public List<Address> collect(List<Profile> profiles) {
        return profiles
                .stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
    }


}
