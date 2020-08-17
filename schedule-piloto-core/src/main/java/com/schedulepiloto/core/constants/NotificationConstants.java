package com.schedulepiloto.core.constants;

public class NotificationConstants {

    // REST
    public static final String REST_PATH_DEFAULT = "/public/core/notifications";
    public static final String GET_NOT_RECEIVED_BY_USERS_REST = "/not-received/users/{idUser}";
    public static final String GET_NOT_NOTIFIED_BY_USERS_REST = "/not-notified/users/{idUser}";
    public static final String GET_ALL_REST = "/get/all";
    public static final String UPDATE_REST = "/update";

    // TYPES
    public static final Long NOTIFICATION_TYPE_INFORMATION = 1L;
    public static final Long NOTIFICATION_TYPE_SUCCESS = 2L;
    public static final Long NOTIFICATION_TYPE_WARNING = 3L;
    public static final Long NOTIFICATION_TYPE_ERROR = 4L;
    public static final Long NOTIFICATION_TYPE_ALERT = 5L;

    // QUERIES PARAMETERS
    public static final String PARAMETER_ID_USER = "id_user";

    // QUERIES
    /**
     * 0. Get Notifications Dont Received By User
     */
    public static final String QUERY_NOTIFICATION_BY_USER_QUERY = " SELECT " +
            " v1.id, " +
            " v1.id_user AS 'idUser', " +
            " v1.id_notification AS 'idNotification', " +
            " v1.name, " +
            " v1.description, " +
            " DATE_FORMAT(v1.created_date, '%Y-%m-%d %H:%i:%s') AS 'createdDate' ";
    // FROM
    public static final String QUERY_NOTIFICATION_BY_USER_FROM = " FROM view_get_all_notifications_dont_received AS v1 ";
    // WHERE
    public static final String QUERY_NOTIFICATION_BY_USER_WHERE = " WHERE v1.id_user = :" + PARAMETER_ID_USER;
    // ORDER
    public static final String QUERY_NOTIFICATION_BY_USER_ORDER = " ORDER BY v1.created_date DESC ";

    /**
     * 1. Get Notifications Not Notified By User
     */
    public static final String QUERY_NOTIFICATION_NOTIFIED_BY_USER_QUERY = " SELECT " +
            " v1.id, " +
            " v1.id_user AS 'idUser', " +
            " v1.id_notification AS 'idNotification', " +
            " v1.name, " +
            " v1.description, " +
            " DATE_FORMAT(v1.created_date, '%Y-%m-%d') AS 'createdDate' ";
    // FROM
    public static final String QUERY_NOTIFICATION_NOTIFIED_BY_USER_FROM = " FROM view_get_all_notifications_dont_notified AS v1 ";
    // WHERE
    public static final String QUERY_NOTIFICATION_NOTIFIED_BY_USER_WHERE = " WHERE v1.id_user = :" + PARAMETER_ID_USER;

    private NotificationConstants() {
        
    }
}
