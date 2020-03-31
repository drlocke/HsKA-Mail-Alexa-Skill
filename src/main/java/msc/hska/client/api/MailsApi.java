package msc.hska.client.api;

import java.util.List;

import msc.hska.client.pojo.Mail;
import msc.hska.client.pojo.MailFolder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MailsApi {
	
    @GET("exchange/mailfolders")
    Call<List<MailFolder>> getEmailFolders();

    @GET("exchange/emailoverview/{folderId}")
    Call<List<Mail>> getEmailOverview(@Path("folderId") String folderId);
    
    @GET("exchange/emailoverview/{folderId}")
    Call<List<Mail>> getEmailOverview(@Path("folderId") String folderId, @Query("maxresults") Integer maxresults);

    @GET("exchange/email/{folderId}/{id}")
    Call<Mail> getMailById(@Path("folderId") String mailbox, @Path("id") String id);
    
}
