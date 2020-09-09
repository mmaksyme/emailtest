package helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonHelpers {
    public static String generateUniqueName(String objectName) {
        return objectName + RandomStringUtils.randomAlphanumeric(10);
    }
}
