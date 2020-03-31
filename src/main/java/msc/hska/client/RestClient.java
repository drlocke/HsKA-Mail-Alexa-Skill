package msc.hska.client;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import msc.hska.client.api.MailsApi;
import msc.hska.client.data.Authentication;
import msc.hska.client.exception.ClientException;
import msc.hska.client.pojo.Mail;
import msc.hska.client.pojo.MailFolder;
import msc.hska.client.util.ApiNames;
import msc.hska.client.util.GsonUtils;
import msc.hska.util.SkillUtils;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

	public static final String BASE_URL = "https://www.iwi.hs-karlsruhe.de/Intranetaccess/REST/";

	private OkHttpClient okHttpClient;
	private MailsApi mailsApi;
	private Authentication authentication;
	private static RestClient instance = null;

	public static RestClient getInstance(Authentication auth) {
		if (instance == null) {
			instance = new RestClient(auth);
		}
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	private RestClient(Authentication auth) {
		init(auth);
	}

	public MailsApi getMailsApi() {
		return mailsApi;
	}

//    public Mail getMailById(String mailbox, String id, CustomCountDownTimer timer) {
//        Call<Mail> call = mailsApi.getMailById(mailbox, id);
//        addToActiveCallList(call);
//        Response<Mail> response;
//        try {
//            response = call.execute();
//        } catch (IOException e) {
//            Trace.printStackTrace(e);
//            return null;
//        }
//        removeFromActiveCallList(call);
//        Mail mail = null;
//        if (response.isSuccessful()) {
//            mail = response.body();
//            DatabaseUtils.insertMailsAsync(mail);
//            MailOverviewActivity.refreshDone();
//        } else {
//            System.out.println(response.errorBody());
//        }
//        if (timer != null)
//            timer.onFinish();
//        return mail;
//    }
//
//    public void callMailOverview(String mailbox, MailOverviewActivity mailOverviewActivity) {
//        callMailOverview(mailbox, null, mailOverviewActivity);
//    }

	private void init(Authentication auth) {
		authentication = auth;

		okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException {
				Request originalRequest = chain.request();

				Request.Builder builder = originalRequest.newBuilder().header("Authorization",
						Credentials.basic(authentication.getName(), authentication.getPassword()));

				return chain.proceed(builder.build());
			}
		}).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(GsonUtils.getInstance().getGson()))
				.client(okHttpClient).build();

		mailsApi = retrofit.create(MailsApi.class);
	}

	@SuppressWarnings("unused")
	public List<Mail> getUnreadMailsFromIncome() throws ClientException {
		MailFolder mailFolder = null;
		if (mailFolder == null) {
			Call<List<MailFolder>> call = mailsApi.getEmailFolders();
			try {
				Response<List<MailFolder>> response = call.execute();

				if (response.body() != null) {
					for (MailFolder folder : response.body())
						if (folder.getName() != null && ApiNames.compare(ApiNames.FOLDER_INCOMING, folder.getName()))
							mailFolder = folder;
				} else
					throw new ClientException("Error. Kein " + ApiNames.FOLDER_INCOMING + " Ordner gefunden!");

			} catch (IOException e) {
				throw new ClientException("Error. Kein " + ApiNames.FOLDER_INCOMING + " Ordner gefunden!");
			}
		}
		
		if (mailFolder != null) {
			Call<List<Mail>> call;
			if (SkillUtils.NUMBER_OF_MAILS > 0)
				call = mailsApi.getEmailOverview(mailFolder.getId(), SkillUtils.NUMBER_OF_MAILS);
			else
				call = mailsApi.getEmailOverview(mailFolder.getId());
			
			try {
				Response<List<Mail>> response = call.execute();

				if (response.body() != null) {
					//filter all read == true out!
					List<Mail> unreadMails = response.body().stream().filter(mail -> !mail.getRead()).collect(Collectors.toList());
					if (SkillUtils.NUMBER_OF_UNREAD_MAILS > 0)
						return unreadMails.subList(0, (Math.min(unreadMails.size(), SkillUtils.NUMBER_OF_UNREAD_MAILS)));
					else
						return unreadMails;
				} else
					throw new ClientException("Es sind keine E-Mails vorhande.");
			} catch (IOException e) {
				throw new ClientException("Error. Keine E-Mails gefunden!");
			}

		} else
			throw new ClientException("Error. Kein " + ApiNames.FOLDER_INCOMING + " Ordner gefunden!");
	}
	
	public void markAsRead(List<Mail> mails) {
		
	}

}
