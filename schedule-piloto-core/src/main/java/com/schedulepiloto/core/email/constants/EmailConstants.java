package com.schedulepiloto.core.email.constants;

public class EmailConstants {

    // Validations
    public static final String ERROR_EMAIL_PROPERTIES = "Properties or email config are not valid.";
    public static final String NULL_CONTENT_MESSAGE = "content CAN NOT BE NULL.";
    public static final String NULL_FROM_MESSAGE = "from CAN NOT BE NULL.";
    public static final String NULL_SEND_TO_LIST_MESSAGE = "sendToList CAN NOT BE NULL.";
    public static final String EMPTY_SEND_TO_LIST_MESSAGE = "sendToList CAN NOT BE EMPTY.";
    public static final String NULL_SUBJECT_MESSAGE = "subject CAN NOT BE NULL.";
    public static final String NULL_EMAIL_ORIGIN_MESSAGE = "emailOrigin CAN NOT BE NULL.";
    public static final String NULL_SMTP_HOST_MESSAGE = "smtpHost CAN NOT BE NULL.";
    public static final String NULL_SMTP_PORT_MESSAGE = "smtpPort CAN NOT BE NULL.";
    public static final String NULL_PASSWORD_MESSAGE = "passwordOrigin CAN NOT BE NULL.";

    // Templates
    public static final String EMAIL_SEND_RESPONSE_DEFAULT_USER = "email-template-kentaurus-general.html";
    public static final String EMAIL_SEND_REGISTER_USER = "email-register-user-acqua-board.html";
    public static final String EMAIL_SEND_REGISTER_USER_US = "email-register-user-us-acqua-board.html";
    public static final String EMAIL_SEND_VALIDATION_USER = "email-validation-user-acqua-board.html";
    public static final String EMAIL_SEND_FORGOT_PASSWORD_USER = "email-forgot-password-acqua-board.html";
    public static final String EMAIL_SEND_CHANGE_PASSWORD_USER = "email-change-password-acqua-board.html";
    public static final String EMAIL_SEND_NOTIFICATION_USER = "email-notification-acqua-board.html";

    // Parameters Templates
    public static final String PARAMETER_TEMPLATE_USER_NAME = "[USER_NAME]";
    public static final String PARAMETER_TEMPLATE_URL_VERIFICATION = "[URL_VERIFICATION]";
    public static final String PARAMETER_TEMPLATE_URL_LOGIN = "[URL_LOGIN]";
    public static final String PARAMETER_TEMPLATE_USER_EMAIL = "[USER_EMAIL]";
    public static final String PARAMETER_TEMPLATE_USER_PASSWORD = "[USER_PASSWORD]";
    public static final String PARAMETER_TEMPLATE_TITLE = "[TITLE_MESSAGE]";
    public static final String PARAMETER_TEMPLATE_SUB_TITLE = "[SUB_TITLE_MESSAGE]";
    public static final String PARAMETER_TEMPLATE_CONTENT_MESSAGE = "[CONTENT_MESSAGE]";
    public static final String PARAMETER_TEMPLATE_SHORT_MESSAGE = "[SHORT_MESSAGE]";

    // Parameters DataBase
    public static final String PARAMETER_URL_APP = "url.app.web";
    public static final String PARAMETER_WEB_API = "url.api.web";
    public static final String PARAMETER_LIMIT_FAILED_ATTEMPTS = "limit.failed.authentication";
    public static final String PARAMETER_URL_CONFIRM_EMAIL = "mail.confirmation.destination.url";
    public static final String PARAMETER_PASSWORD_EXPIRED = "password.expiration";
    public static final String PARAMETER_SEND_EMAIL_REGISTER_USER_SHORT_MESSAGE = "email.register.user.short-message";
    public static final String PARAMETER_SEND_EMAIL_REGISTER_USER_US_SHORT_MESSAGE = "email.register.user.short-message.us";
    public static final String PARAMETER_SEND_EMAIL_REGISTER_USER_MESSAGE = "email.register.user.message";
    public static final String PARAMETER_SEND_EMAIL_REGISTER_USER_US_MESSAGE = "email.register.user.message.us";
    public static final String PARAMETER_SEND_EMAIL_VERIFICATION_USER_SHORT_MESSAGE = "email.verification.user.short-message";
    public static final String PARAMETER_SEND_EMAIL_VERIFICATION_USER_MESSAGE = "email.verification.user.message";
    public static final String PARAMETER_SEND_EMAIL_FORGOT_PASSWORD_SHORT_MESSAGE = "email.forgot-password.user.short-message";
    public static final String PARAMETER_SEND_EMAIL_FORGOT_PASSWORD_MESSAGE = "email.forgot-password.user.message";
    public static final String PARAMETER_SEND_EMAIL_CHANGE_PASSWORD_SHORT_MESSAGE = "email.change-password.user.short-message";
    public static final String PARAMETER_SEND_EMAIL_CHANGE_PASSWORD_MESSAGE = "email.change-password.user.message";

    // Errors
    public static final String ERROR_EMAIL_SEND_CLIENT = "ERROR SENDING EMAIL CLIENT, ERROR CODE: {} - ERROR DESCRIPTION: {}";
    public static final String ERROR_EMAIL_SEND_ME = "ERROR SENDING EMAIL OWN, ERROR CODE: {} - ERROR DESCRIPTION: {}";

    // Default Messages
    public static final String TITLE_DEFAULT_EMAIL = "Myventory - 2020";
    public static final String SUBJECT_DEFAULT_SEND_REGISTER_USER = "Myventory - Confirmación de email";
    public static final String SUBJECT_DEFAULT_SEND_REGISTER_USER_US = "Myventory - Nuevo usuario registrado";
    public static final String SUBJECT_DEFAULT_SEND_RESPONSE_VALIDATION_EMAIL = "Myventory - Email validado";
    public static final String SUBJECT_DEFAULT_SEND_CHANGE_PASSWORD = "Myventory - Cambio de contraseña";
    public static final String SUBJECT_DEFAULT_SEND_REMEMBER_PASSWORD = "Myventory - Restauración de contraseña";
    public static final String SUBJECT_DEFAULT_SEND_ACTIVATE_USER_ACCOUNT = "Account verified successfully.";
}
