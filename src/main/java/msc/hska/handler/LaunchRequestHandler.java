package msc.hska.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import msc.hska.util.SkillUtils;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "H.S.K.A. Mails gestartet";
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard(SkillUtils.SKILL_NAME, speechText)
				.withReprompt(speechText).build();
	}
}