package com.schedulepiloto.core.email.model;

import com.schedulepiloto.core.email.constants.EmailConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class EmailDto implements Serializable {

    private List<String> attachmentsList;
    @NotNull(message = EmailConstants.NULL_CONTENT_MESSAGE)
    private String content;
    @NotNull(message = EmailConstants.NULL_FROM_MESSAGE)
    private String from;
    @NotNull(message = EmailConstants.NULL_SEND_TO_LIST_MESSAGE)
    @NotEmpty(message = EmailConstants.EMPTY_SEND_TO_LIST_MESSAGE)
    private List<String> sendToList;
    @NotNull(message = EmailConstants.NULL_SUBJECT_MESSAGE)
    private String subject;
    private List<String> replyToList;

    public EmailDto() {

    }

    public EmailDto(List<String> attachmentsList, @NotNull(message = EmailConstants.NULL_CONTENT_MESSAGE) String content, @NotNull(message = EmailConstants.NULL_FROM_MESSAGE) String from, @NotNull(message = "sendToList CAN NOT BE NULL.") @NotEmpty(message = EmailConstants.NULL_SEND_TO_LIST_MESSAGE) List<String> sendToList, @NotNull(message = EmailConstants.NULL_SUBJECT_MESSAGE) String subject, List<String> replyToList) {
        this.attachmentsList = attachmentsList;
        this.content = content;
        this.from = from;
        this.sendToList = sendToList;
        this.subject = subject;
        this.replyToList = replyToList;
    }

    public List<String> getAttachmentsList() {
        return attachmentsList;
    }

    public void setAttachmentsList(List<String> attachmentsList) {
        this.attachmentsList = attachmentsList;
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

    public List<String> getSendToList() {
        return sendToList;
    }

    public void setSendToList(List<String> sendToList) {
        this.sendToList = sendToList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getReplyToList() {
        return replyToList;
    }

    public void setReplyToList(List<String> replyToList) {
        this.replyToList = replyToList;
    }
}
