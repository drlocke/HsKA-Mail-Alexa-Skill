package msc.hska.client;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import msc.hska.client.RestClient;
import msc.hska.client.data.Authentication;
import msc.hska.client.exception.ClientException;
import msc.hska.client.pojo.Mail;
import msc.hska.util.SkillUtils;

class RestClientTest {

	@Test
	void test() {
		RestClient client = RestClient.getInstance(new Authentication(SkillUtils.HSKA_USER, SkillUtils.HSKA_PASSWORD));
		String speechText = null;
		try {
			List<Mail> mails = client.getUnreadMailsFromIncome();
			if (mails != null)
				assertTrue(mails.size() > 0);
			else
			{
				fail("Mail list was NULL!");
				return;
			}
			
			speechText = SkillUtils.mailsToStringOutput(mails);
		} catch (ClientException e) {
			fail(e.getMessage());
			return;
		}
		//System.out.println(speechText);
		assertTrue(speechText != null && !speechText.isEmpty());
	}

}
