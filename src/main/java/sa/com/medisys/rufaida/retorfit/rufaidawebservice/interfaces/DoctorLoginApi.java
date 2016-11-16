package sa.com.medisys.rufaida.retorfit.rufaidawebservice.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sa.com.medisys.rufaida.retorfit.rufaidawebservice.collection.DoctorLoginCollection;

/**
 * Created by medisys-java on 07-Nov-16.
 */

public interface DoctorLoginApi {
    @GET("doctorLogin")
    Call<DoctorLoginCollection> getDoctorLoginInfo(@Query("username") String username, @Query("password") String password);
}
