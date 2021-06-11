package com.neighborhood.website.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MailDto {

    @NotEmpty(message = "L'objet du message est obligatoire")
    @Size(min = 5, max = 75)
    private String objet;

    @NotEmpty(message = "Le contenus du message est obligatoire")
    @Size(min = 5, max = 1000)
    private String content;

    private String from;

    private String to;

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

