package com.testing.model;

/*
 * A simple model that represents the content of an email to be composed.
 * It holds the recipient address, subject, and body text.
 * Used in tests where email composition is part of the scenario.
 */

public class ComposeEmail {
    private String to;
    private String subject;
    private String body;

    public ComposeEmail(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }

    @Override
    public String toString() {
        return "ComposeEmail{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
