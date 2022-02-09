package com.sha.gateway.channel.utility;

import com.google.gson.JsonElement;
import com.sha.gateway.utility.Util;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
public final class RetrofitUtil
{
    private RetrofitUtil(){}

    public static <T> T callGenericBlock(Call<T> request)
    {
        try {
            Response<T> response = request.execute();

            if (!response.isSuccessful())
            {
                log.error("Unsuccessful request. Error reason: ",response.errorBody().string());
            }

            return response.body();
        }
        catch (IOException e)
        {
            Util.showExceptionInfo(e);

            return null;
        }
    }

}
