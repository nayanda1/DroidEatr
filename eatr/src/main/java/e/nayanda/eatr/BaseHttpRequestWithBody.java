package e.nayanda.eatr;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by nayanda on 08/02/18.
 */

abstract class BaseHttpRequestWithBody<T extends BaseHttpRequestWithBody> extends BaseHttpRequest<T> implements HttpRequestWithBody<T> {

    protected String body;

    @Override
    public T addBody(String body) {
        this.body = body;
        return (T)this;
    }

    @Override
    public <O> T addJsonBody(O obj) {
        if (obj == null) throw new NullPointerException("Json body cannot be null");
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        this.body = json;
        addHeaders("Content-Type", "application/json");
        return (T)this;
    }

    @Override
    public T addFormUrlEncoded(Map<String, String> form) throws UnsupportedEncodingException {
        if (form == null) throw new NullPointerException("Form cannot be null");
        if (form.size() == 0) return (T)this;
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, String>> entries = form.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = URLEncoder.encode(entry.getKey(), "UTF-8");
            String value = URLEncoder.encode(entry.getValue(), "UTF-8");
            builder = builder.append(key).append("=").append(value).append("&");
        }
        if (builder.length() > 0) builder = builder.deleteCharAt(builder.length() - 1);
        addBody(builder.toString());
        addHeaders("Content-Type", "application/x-www-form-urlencoded");
        return (T)this;
    }
}
