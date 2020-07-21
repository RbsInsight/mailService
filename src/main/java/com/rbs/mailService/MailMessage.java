package com.rbs.mailService;

public class MailMessage {
    private String mail_to_address;
    private String mail_cc_address;
    private String mail_bcc_address;
    private String mail_subject;
    private String mail_body;

    public String getMail_to_address() {
        return mail_to_address;
    }

    public void setMail_to_address(String mail_to_address) {
        this.mail_to_address = mail_to_address;
    }

    public String getMail_cc_address() {
        return mail_cc_address;
    }

    public void setMail_cc_address(String mail_cc_address) {
        this.mail_cc_address = mail_cc_address;
    }

    public String getMail_bcc_address() {
        return mail_bcc_address;
    }

    public void setMail_bcc_address(String mail_bcc_address) {
        this.mail_bcc_address = mail_bcc_address;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public void setMail_subject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    public String getMail_body() {
        return mail_body;
    }

    public void setMail_body(String mail_body) {
        this.mail_body = mail_body;
    }
}
