package e.nayanda.eatr;

import e.nayanda.eatr.model.Finisher;
import e.nayanda.eatr.model.Response;
import e.nayanda.eatr.model.RestFinisher;
import e.nayanda.eatr.model.RestResponse;

/**
 * Created by nayanda on 08/02/18.
 */

class HttpGet extends BaseHttpRequest<HttpGet> {

    @Override
    public void asyncExecute(final Finisher finisher) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                finisher.onFinished(executor("GET"));
            }
        });
        thread.run();
    }

    @Override
    public <O> void asyncExecute(final RestFinisher<O> finisher, final Class<O> withModelClass) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                finisher.onFinished(executor(withModelClass,"GET"));
            }
        });
        thread.run();
    }

    @Override
    public Response execute() {
        return executor("GET");
    }

    @Override
    public <O> RestResponse<O> execute(Class<O> withModelClass) {
        return executor(withModelClass,"GET");
    }
}
