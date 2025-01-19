package com.tak.gateway.api.common;

import com.tak.gateway.api.common.hepler.ApplicationContextHelper;
import com.tak.gateway.api.domain.type.ApplicationName;
import com.tak.gateway.api.domain.type.SpringProfile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

public class CommonConstants extends BaseConstants {

    public final static ApplicationName APPLICATION_NAME;
    public final static SpringProfile SPRING_ACTIVE_PROFILE;
    public final static String          SYSTEM_USER_ID = "system";
    public final static String          BATCH_USER_ID  = "batch";

    public final static String DATE_FORMAT      = "yyyy-MM-dd";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_INT  = "yyyyMMdd";

    //Patterns for HTML Elements
    public static final String SELECTED         = "selected=\"selected\"";
    public static final String CHECKED          = "checked=\"checked\"";
    public static final String REQUIRED         = "required=\"required\"";
    public static final String SELECT_PATTERN   = "<select name=\"{0}\" id=\"{1}\" class=\"{3}\" style=\"{4}\" {5}>{2}</select>";
    public static final String OPTION_PATTERN   = "<option value=\"{0}\" {2}>{1}</option>";
    public static final String RADIO_PATTERN    = "<input type=\"radio\" name=\"{0}\" value=\"{2}\" class=\"{3}\" style=\"{4}\" {5} {6}/> {1}";
    public static final String CHECKBOX_PATTERN = "<input type=\"checkbox\" name=\"{0}\" value=\"{2}\" class=\"{3}\" style=\"{4}\" {5} {6}/> {1}";
    public static final String BUTTON_PATTERN   = "<button id=\"{0}\" class=\"{2}\" style=\"{3}\" {4}>{1}</button>";
    public static final String ANCHOR_PATTERN   = "<a id=\"{0}\" class=\"{2}\" style=\"{3}\" {4}>{1}</a>";

    static {
        Environment env = ApplicationContextHelper.getApplicationContext().getEnvironment();

        APPLICATION_NAME = ApplicationName.fromName(System.getProperty("app.name"));

        final boolean isDevProfileActive = env.acceptsProfiles(Profiles.of("dev"));
        final boolean isPrdProfileActive = env.acceptsProfiles(Profiles.of("prd"));
        if (isPrdProfileActive) {
            SPRING_ACTIVE_PROFILE = SpringProfile.PRD;
        } else if (isDevProfileActive) {
            SPRING_ACTIVE_PROFILE = SpringProfile.DEV;
        } else {
            SPRING_ACTIVE_PROFILE = SpringProfile.LOCAL;
        }
    }
}
