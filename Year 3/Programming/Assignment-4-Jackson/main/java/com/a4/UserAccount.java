package com.a4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a user account with a unique user ID, name, and email address.
 * Implements Comparable interface to allow sorting by email address (natural ordering).
 * Used a record because it is a simple data carrier class with no additional methods.
 */
public record UserAccount(long userId, String name, String emailAddress) implements Comparable<UserAccount> {

    /**
     * Constructs a new instance of UserAccount class
     *
     * @param userId       the user ID of the user
     * @param name         the name of the user
     * @param emailAddress the email address of the user
     */
    @JsonCreator
    public UserAccount(@JsonProperty("userId") long userId,
                       @JsonProperty("name") String name,
                       @JsonProperty("emailAddress") String emailAddress) {
        this.userId = userId;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    /**
     * Returns a toString implementation of the UserAccount
     *
     * @return a formatted string representation of a user account.
     */
    @Override
    public String toString() {
        return String.format("UserAccount {userId=%d, name='%s', emailAddress='%s'}", userId, name, emailAddress);
    }

    /**
     * Compares this object with the passed in object for order.
     * Returns a negative integer, zero, or a positive integer as this object is less than, equal to,
     * or greater than the specified object.
     *
     * @param o the other object to be compared with
     * @return a negative integer, zero, or a positive integer
     */
    @Override
    public int compareTo(UserAccount o) {
        return this.emailAddress.compareTo(o.emailAddress);
    }

    /**
     * Two objects are considered equal if their user IDs are the same.
     *
     * @param o the object with which to compare
     * @return a boolean -  true if the user IDs are the same, false otherwise - some additional checks are made such as
     * checking if the object is an instance of UserAccount and if the same object is being compared
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.userId == that.userId;
    }
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(userId);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        return result;
    }
}