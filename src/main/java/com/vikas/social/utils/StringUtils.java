package com.vikas.social.utils;
import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    private StringUtils() {

    }

    public static final String ERROR_STR = "{} exception occurred!! Reason: {}";

    public static final String STATUS = "status";
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_DELETED = "deleted";
    public static final String STATUS_DELETEDBYADMIN = "deletedbyadmin";

    public static final String BELL_STATUS_ACTIVE = "active";
    public static final String BELL_STATUS_PENDINGMODERATION = "pendingmoderation";
    public static final String BELL_STATUS_FAILEDMODERATION = "failedmoderation";

    public static final String BELL_STATUS_DELETED = "deleted";
    public static final String BELL_STATUS_DELETEDBYADMIN = "deletedbyadmin";

    public static final String TAG_STATUS_APPROVED = "approved";

    public static final String BOARD_TYPE_PRIVATE = "private";

    public static final String REACTION_TYPE_LIKED = "Liked";
    public static final String REACTION_TYPE_REACTED = "Reacted";

    public static final String REACTION_TYPE_LIKED_BODY = "liked";
    public static final String REACTION_TYPE_REACTED_BODY = "reacted to";

    public static final String BELL_ACTION_DELETED = "deleted";
    public static final String BELL_ACTION_RESTORED = "restored";

    public static final String USER_STATUS_SUSPENDED = "suspended";
}
