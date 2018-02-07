package e.nayanda.eatr;

import java.util.Map;

import e.nayanda.eatr.model.Finisher;
import e.nayanda.eatr.model.Response;
import e.nayanda.eatr.model.RestFinisher;
import e.nayanda.eatr.model.RestResponse;

/**
 * Created by nayanda on 07/02/18.
 */

public interface HttpRequest<T extends HttpRequest> {
    T setUrl(String url);
    T setParams(Map<String, String> params);
    T setHeaders(Map<String, String> headers);
    T addHeaders(String key, String value);
    T addAuthorization(String token);
    T setTimeout(int timeout);
    void asyncExecute(Finisher finisher);
    <O> void asyncExecute(RestFinisher<O> finisher, Class<O> withModelClass);
    Response execute();
    <O> RestResponse<O> execute(Class<O> withModelClass);
}
