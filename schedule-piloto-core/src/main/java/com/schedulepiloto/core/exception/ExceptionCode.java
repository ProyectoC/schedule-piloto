package com.schedulepiloto.core.exception;

public enum ExceptionCode {

    ERROR_CLIENT("99", "Error de cliente."),
    ERROR_UNKNOWN("98", "Error, consulte al administrador del sistema."),
    ERROR_RECORD_NOT_FOUND("97", "Error, el registro no se ha encontrado."),
    ERROR_INVALID_DATA("96", "Error, datos no validos."),
    ERROR_SEND_EMAIL("95", "Error, no fue posible enviar el email."),
    ERROR_REGISTER_LOG("94", "Error, no fue posible registrar el log de seguridad."),
    ERROR_AUTHENTICATION("93", "Error en la autenticaci\u00f3n."),
    ERROR_TOKEN_COMPANY("92", "Error, Token de seguridad invalido."),
    ERROR_PARAMETER_SECURITY_NOT_FOUND("91", "Error, no se ha encontrado el par\u00e1metro con nombre: "),
    ERROR_CONVERT_DATE("90", "Error, no se ha podido convertir la fecha."),
    ERROR_DATA_ACCESS("89", "Error, ocurrio un problema al comunicarse con la capa de datos."),
    ERROR_DATA_ACCESS_ROLl_BACK("88", "Error, ocurrio un problema al comunicarse con la capa de datos."),
    ERROR_DATA_ACCESS_INTEGRATION_VIOLATION("87", "Error, ocurrio un problema al comunicarse con la capa de datos, violacion de la integridad de datos.");

    private final String code;
    private final String description;

    ExceptionCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
