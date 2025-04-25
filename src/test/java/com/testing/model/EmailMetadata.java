package com.testing.model;

/*
 * Represents minimal metadata extracted from emails in the email list,
 * such as sender and subject. Typically used for assertions.
 */

public class EmailMetadata {
    private String sender;
    private String subject;

    public EmailMetadata(String sender, String subject) {
        this.sender = sender;
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
