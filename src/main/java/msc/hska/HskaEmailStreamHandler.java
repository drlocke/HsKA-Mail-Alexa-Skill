package msc.hska;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import msc.hska.handler.CancelAndStopIntentHandler;
import msc.hska.handler.HskaEmailIntentHandler;
import msc.hska.handler.HelpIntentHandler;
import msc.hska.handler.LaunchRequestHandler;
import msc.hska.handler.SessionEndedRequestHandler;
import msc.hska.util.SkillUtils;

public class HskaEmailStreamHandler extends SkillStreamHandler {

	private static Skill getSkill() {
		return Skills.standard()
				.addRequestHandlers(new CancelAndStopIntentHandler(), new HskaEmailIntentHandler(),
						new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler())
				.withSkillId(SkillUtils.AMAZON_SKILL_ID).build();
	}

	public HskaEmailStreamHandler() {
		super(getSkill());
	}
}