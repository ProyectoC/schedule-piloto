package com.schedulepiloto.core.notification.model;

public enum NotificationTemplate {

    TEMPLATE_INFORMATION_CREATION_PROJECT("[AcquaBoard] - Creación Proyecto", "AcquaBoard - Creaci&#243;n Proyecto", "Señor(a) [USER_NAME], usted ha creado el proyecto con nombre <strong>[PROJECT_NAME]</strong>, y ha agregado a los siguientes usuarios: [LIST_USERS]. &#128512;"),
    TEMPLATE_INFORMATION_UPDATE_PROJECT("[AcquaBoard] - Actualización Proyecto", "AcquaBoard - Actualizaci&#243;n Proyecto", "Señor(a) [USER_NAME], usted ha actualizado el proyecto con nombre '[PROJECT_NAME]', y ha agregado a los siguientes usuarios: [LIST_USERS]."),
    TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT("[AcquaBoard] - Asignación Proyecto", "AcquaBoard - Asignaci&#243;n Proyecto", "Cordial Saludo, Usted ha sido asignado al equipo del proyecto con nombre '[PROJECT_NAME]'."),
    TEMPLATE_INFORMATION_CREATION_SPRINT("[AcquaBoard] - Creación Sprint", "AcquaBoard - Creaci&#243;n Sprint", "Señor(a) [USER_NAME], usted ha creado el sprint con nombre '[SPRINT_NAME]'."),
    TEMPLATE_WARNING_UPDATE_SPRINT("[AcquaBoard] - Actualización Sprint", "AcquaBoard - Actualizaci&#243;n Sprint", "¡CUIDADO! Señor(a) [USER_NAME], usted ha actualizado el sprint con nombre '[SPRINT_NAME]'. Fecha de inicio: [DATE_STARTED]. No. de Semanas: [WEEKS]. Estado: [STATUS]."),
    TEMPLATE_INFORMATION_CREATION_REQUEST("[AcquaBoard] - Creación Requerimiento", "AcquaBoard - Creaci&#243;n Requerimiento", "Señor(a) [USER_NAME], usted ha creado el requerimiento con nombre '[REQUEST_NAME]'."),
    TEMPLATE_INFORMATION_UPDATE_REQUEST("[AcquaBoard] - Actualización Requerimiento", "AcquaBoard - Actualizaci&#243;n Requerimiento", "Señor(a) [USER_NAME], usted ha actualizado el requerimiento con nombre '[REQUEST_NAME]'."),
    TEMPLATE_INFORMATION_CREATION_ACTIVITY("[AcquaBoard] - Creación Actividad", "AcquaBoard - Creaci&#243;n Actividad", "Señor(a) [USER_NAME], usted ha creado la actividad con nombre '[ACTIVITY_NAME]'."),
    TEMPLATE_INFORMATION_ASSIGNED_ACTIVITY("[AcquaBoard] - Asignación Actividad", "AcquaBoard - Asignaci&#243;n Actividad", "Se ha asignado la actividad '[ACTIVITY_NAME]' ha [USER_RESPONSIBLE]."),
    TEMPLATE_WARNING_ASSIGNED_ACTIVITY_LEADER("[AcquaBoard] - Asignación Actividad", "AcquaBoard - Asignaci&#243;n Actividad", "Señor(a) [USER_LEADER], usted ha asignado la actividad '[ACTIVITY_NAME]' ha [USER_RESPONSIBLE]," +
            " sin embargo este usuario no cuenta con el skill para realizarla."),
    TEMPLATE_INFORMATION_ACTIVITY_DO_DOING("[AcquaBoard] - Cambio Estado Actividad", "AcquaBoard - Cambio Estado Actividad", "La actividad [ACTIVITY_NAME] ha iniciado el proceso de desarrollo."),
    TEMPLATE_ERROR_ACTIVITY_DO_BLOCKED("[AcquaBoard] - ERROR Estado Actividad", "AcquaBoard - ERROR Estado Actividad", "LA ACTIVIDAD [ACTIVITY_NAME] SE ENCUENTRA EN ESTADO BLOQUEADO, NO HA SIDO POSIBLE INICIAR CON EL DESARROLLO DE ESTA."),
    TEMPLATE_INFORMATION_ACTIVITY_DOING_COMPLETE("[AcquaBoard] - Cambio Estado Actividad", "AcquaBoard - Cambio Estado Actividad", "La actividad [ACTIVITY_NAME] ha iniciado el proceso de verificaci&#243;n por parte del l&#237;der t&#233;cnico."),
    TEMPLATE_WARNING_ACTIVITY_DOING_BLOCKED("[AcquaBoard] - ADVERTENCIA Estado Actividad", "AcquaBoard - ADVERTENCIA Estado Actividad", "LA ACTIVIDAD [ACTIVITY_NAME] SE ENCUENTRA EN ESTADO BLOQUEADO, LUEGO DE ENCONTRARSE EN DESARROLLO."),
    TEMPLATE_INFORMATION_HOURS_WORKED_ACTIVITY("[AcquaBoard] - Reporte de Horas Actividad", "AcquaBoard - Reporte de Horas Actividad", "La actividad [ACTIVITY_NAME] ha reportado un avance de [HOURS] hora(s)."),
    TEMPLATE_SUCCESS_ACTIVITY_COMPLETE_VALIDATE("[AcquaBoard] - Cambio Estado Actividad", "AcquaBoard - Cambio Estado Actividad", "La actividad [ACTIVITY_NAME] se ha validado correctamente. Una actividad menos del sprint!"),
    TEMPLATE_ALERT_ACTIVITY_COMPLETE_DOING("[AcquaBoard] - Cambio Estado Actividad", "AcquaBoard - Cambio Estado Actividad", "La actividad '[ACTIVITY_NAME]' no ha sido aprobada, se deben verificar los criterios de aceptaci&#243;n."),
    TEMPLATE_WARNING_ACTIVITY_BLOCKED_DO("[AcquaBoard] - Cambio Estado Actividad", "AcquaBoard - Cambio Estado Actividad", "La actividad '[ACTIVITY_NAME]' esta lista para ser asignada nuevamente a un usuario."),
    TEMPLATE_ERROR_ACTIVITY("[AcquaBoard] - Error en Actividad", "AcquaBoard - Error en Actividad", "El usuario [USER_RESPONSIBLE] ha reportado el siguiente error: [ERROR_DESCRIPTION], con el nivel: [ERROR_LEVEL]. Para la actividad [ACTIVITY_NAME]."),
    TEMPLATE_ALERT_SPRINT_DAYS_LEFT("[AcquaBoard] - Sprint Pronto a Finalizar", "AcquaBoard - Sprint Pronto a Finalizar", "Estimado(a) [USER_NAME], el sprint [NAME_SPRINT], finalizara en: [DAYS_LEFT], y se encuentra en estado [STATUS_SPRINT]. <br>Actualice el estado del sprint. <br><br>Mensaje Adicional: [MESSAGE]."),
    TEMPLATE_ALERT_SPRINT_DAYS_AFTER("[AcquaBoard] - Sprint Finalizado", "AcquaBoard - Sprint Finalizado", "Estimado(a) [USER_NAME], el sprint [NAME_SPRINT], finalizo hace: [DAYS_LEFT], y se encuentra en estado [STATUS_SPRINT] &#128531;. <br>Actualice el estado del sprint. <br><br>Mensaje Adicional: [MESSAGE]."),
    TEMPLATE_ALERT_ACTIVITY_DAYS_LEFT("[AcquaBoard] - Actividad Pronto a Finalizar", "AcquaBoard - Actividad Pronto a Finalizar", "La actividad [NAME_ACTIVITY], finalizara en: [DAYS_LEFT], y se encuentra en estado [STATUS_ACTIVITY]. <br><br>Mensaje Adicional: [MESSAGE]."),
    TEMPLATE_ALERT_ACTIVITY_DAYS_AFTER("[AcquaBoard] - Actividad Finalizada", "AcquaBoard - Actividad Finalizada", "La actividad [NAME_ACTIVITY], finalizo hace: [DAYS_LEFT], y se encuentra en estado [STATUS_ACTIVITY] &#128531;. <br><br>Mensaje Adicional: [MESSAGE].");

    private final String title;
    private final String subject;
    private final String description;

    NotificationTemplate(String title, String subject, String description) {
        this.title = title;
        this.subject = subject;
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
