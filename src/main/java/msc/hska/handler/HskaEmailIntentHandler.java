package msc.hska.handler;

import java.util.List;
import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import msc.hska.client.RestClient;
import msc.hska.client.data.Authentication;
import msc.hska.client.exception.ClientException;
import msc.hska.client.pojo.Mail;
import msc.hska.util.SkillUtils;

import static com.amazon.ask.request.Predicates.intentName;

public class HskaEmailIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HskaEmailIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		RestClient client = RestClient.getInstance(new Authentication(SkillUtils.HSKA_USER, SkillUtils.HSKA_PASSWORD));
		String speechText;
		try {
			List<Mail> unreadMails = client.getUnreadMailsFromIncome();
			speechText = SkillUtils.mailsToStringOutput(unreadMails);
			//client.markAsRead(unreadMails);
		} catch (ClientException e) {
			speechText = e.getMessage();
		}
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard(SkillUtils.SKILL_NAME, speechText).build();
	}
}