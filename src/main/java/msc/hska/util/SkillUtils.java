package msc.hska.util;

import java.util.List;

import msc.hska.client.pojo.Mail;

public class SkillUtils {

	/* ***************** USER CONFIGURATION ***************** */
	public static final String HSKA_USER = getEnvVar("IZ_USER", "IZ-Kürzel");

	public static final String HSKA_PASSWORD = getEnvVar("IZ_USER_PASSWORD", "Passwort");

	// Dieser Wert muss so klein bleiben, da das Hochschule Backend recht langsam
	// ist
	// und wir nur eine begrenzte Zeit in der AWS Cloud zur Verfügung haben!
	public static final int NUMBER_OF_MAILS = getEnvVar("NUMBER_OF_MAILS", 20);

	public static final int NUMBER_OF_UNREAD_MAILS = getEnvVar("NUMBER_OF_UNREAD_MAILS", 0);

	// public static final int NUMBER_OF_UNREAD_MAILS = NUMBER_OF_MAILS;

	// public static final int NUMBER_OF_MAILS = 20;

	// public static final int NUMBER_OF_UNREAD_MAILS = 5;

	// public static final int NUMBER_OF_MAILS = 0;

	// public static final int NUMBER_OF_UNREAD_MAILS = 0;

	// Muss bis zum Veröffentlichen des Skills für jeden selbst eingetragen werden
	public static final String AMAZON_SKILL_ID = getEnvVar("AMAZON_SKILL_ID", "AMAZON-SKILL-ID");

	/* ***************** SKILL CONFIGURATION ***************** */

	public static final String SKILL_NAME = "HsKA Mails";

	public static String mailsToStringOutput(List<Mail> mails) {
		if (mails != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Du hast ").append(mails.size()).append(" neue ungelesene Nachrichten: ");
			for (Mail mail : mails) {
				sb.append("<break time=\"1s\"/>");
				sb.append("Betreff: ").append(mail.getSubject()).append("<break time=\"500ms\"/>");
				sb.append(" Absender: ").append(mail.getSender().getName()).append("<break time=\"500ms\"/>");
				sb.append(" Nachricht: ").append(removeUnwantedChars(trimBody(mail.getBodyPlainText())));
			}
			if (sb.length() > 0)
				return sb.toString();
		}

		return "Keine E-Mails gefunden!";
	}

	public static String trimBody(String body) {
		return body.replaceAll("\\r\\n", " ").trim();
	}

	public static String removeUnwantedChars(String body) {
		if (body == null || body.isEmpty())
			return body;

		body = body.replaceAll("http:\\S+", "externer Link");
		body = body.replaceAll("https:\\S+", "externer Link");
		body = body.replaceAll("\\t", " ");
		body = body.replaceAll("\\s{2,}", " ");
		body = body.replaceAll("[^(a-zA-Z_0-9|\\.|\\?|\\!|\\,|\\s|:|ä|ü|ö|Ä|Ö|Ü|ß)]", "");

		return body;
	}

	public static String getEnvVar(String key, String defaultValue) {
		String value = System.getenv(key);
		if (value != null)
			return value;
		return defaultValue;
	}

	public static int getEnvVar(String key, int defaultValue) {
		String value = System.getenv(key);
		if (value != null) {
			try {
				return Integer.valueOf(value);
			} catch (NumberFormatException ex) {
				return defaultValue;
			}
		}
		return defaultValue;
	}

}
