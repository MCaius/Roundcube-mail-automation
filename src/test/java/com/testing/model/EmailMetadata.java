package com.testing.model;

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

