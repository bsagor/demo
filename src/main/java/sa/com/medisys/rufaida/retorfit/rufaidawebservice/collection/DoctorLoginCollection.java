package sa.com.medisys.rufaida.retorfit.rufaidawebservice.collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import sa.com.medisys.rufaida.retorfit.rufaidawebservice.model.DoctorLogin;

/**
 * Created by medisys-java on 07-Nov-16.
 */

public class DoctorLoginCollection {
    @SerializedName("data")
    @Expose
    public List<DoctorLogin> data;

}
