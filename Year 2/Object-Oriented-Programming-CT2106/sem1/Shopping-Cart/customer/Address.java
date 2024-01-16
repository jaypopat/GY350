package customer;


//  This class represents an Address.
//  It contains four private fields: street, city, zip, and country.
//  These fields are used to store the details of an address.

public class Address {
    private String street; // Represents the street of the address
    private String city; // Represents the city of the address
    private String zip; // Represents the zip code of the address
    private String country; // Represents the country of the address


//      This is the constructor for the Address class.
//      It initializes the street, city, zip, and country fields with the provided values.


    public Address(String street, String city, String zip, String country) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

//    This method returns the street of the address.

    public String getStreet() {
        return street;
    }

    //    This method returns the city of the address.

    public String getCity() {
        return city;
    }

    //    This method returns the zip code of the address.

    public String getZip() {
        return zip;
    }

    //    This method returns the country of the address.


    public String getCountry() {
        return country;
    }

//     This method prints the address in a formatted way.
//     It concatenates the street, city, country, and zip code with newline characters in between.

    public String AddressPrint() {
        return street + "\n" + city + "\n" + country + "\n" + zip;
    }
}
