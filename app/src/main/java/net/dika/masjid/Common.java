package net.dika.masjid;

import net.dika.masjid.remotes.GoogleApiService;
import net.dika.masjid.remotes.RetrofitBuilder;

public class Common {
    private static final String BASE_URL = "https://maps.googleapis.com/";

    public static GoogleApiService getGoogleApiService() {
        return RetrofitBuilder.builder().create(GoogleApiService.class);
    }
}
