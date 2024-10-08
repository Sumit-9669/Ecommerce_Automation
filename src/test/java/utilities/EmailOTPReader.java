package utilities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;

public class EmailOTPReader {

	public static String getOTPFromEmail(String host, String storeType, String user, String password) {
		try {
			// Set up email server properties
			Properties properties = new Properties();
			// properties.put("mail.imaps.partialfetch", "false");
			properties.put("mail.store.protocol", "imaps");
			properties.put("mail.imaps.host", "imap.gmail.com");
			properties.put("mail.imaps.port", "993");
			properties.put("mail.imaps.ssl.enable", "true");

			// Connect to email server
			Session emailSession = Session.getInstance(properties);
			Store store = emailSession.getStore("imaps");
			store.connect(host, -1, user, password);

			// Access inbox folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Fetch the latest email
			Message[] messages = inbox.getMessages();
			Message latestMessage = messages[messages.length - 1];

			// Extract OTP from email content
			String otp = extractOTP(latestMessage);

			inbox.close(false);
			store.close();

			return otp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String extractOTP(Message message) throws Exception {
		String content = getTextFromMessage(message);

		// Regular Expression to Extract OTP
		String otpRegex = "\\b\\d{4}\\b"; // Assuming OTP is a 4-digit number
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(otpRegex);
		java.util.regex.Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}
	}

	private static String getTextFromMessage(Message message) throws Exception {

		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("text/html")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			return getTextFromMimeMultipart(mimeMultipart);
		}
		return null;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + bodyPart.getContent();
			} else if (bodyPart.isMimeType("text/html")) {
				result = result + bodyPart.getContent(); // Prefer HTML content over plain text
			}
		}
		return result;

	}
}
