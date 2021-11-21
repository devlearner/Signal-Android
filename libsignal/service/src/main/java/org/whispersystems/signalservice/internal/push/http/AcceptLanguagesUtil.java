package org.whispersystems.signalservice.internal.push.http;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class AcceptLanguagesUtil {
  public static Map<String, String> getHeadersWithAcceptLanguage(Locale locale) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Accept-Language", formatLanguages(locale.getLanguage(), locale.getCountry(), Locale.US.getLanguage()));

    return headers;
  }

  public static String getAcceptLanguageHeader(Locale locale) {
    return "Accept-Language:" + formatLanguages(locale.getLanguage(), locale.getCountry(), Locale.US.getLanguage());
  }

  private static String formatLanguages(String language, String country, String fallback) {
    if (Objects.equals(language, fallback)) {
      return language + ";q=1";
    } else if (country.equals("")) {
      return language + ";q=1," + fallback + ";q=0.5";
    } else {
      return language + "-" + country + ";q=1," + language + ";q=0.9," + fallback + ";q=0.5";
    }
  }
}
