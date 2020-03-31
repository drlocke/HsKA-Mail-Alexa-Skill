package msc.hska.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.model.Response;

import msc.hska.util.SkillUtils;

public class CancelAndStopIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
	}

	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Bis bald.";
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard(SkillUtils.SKILL_NAME, speechText).build();
	}
}