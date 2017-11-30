package com.mobile.desafio.repositoriosgithub.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.util.NetworkService;

public abstract class AbstractAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, AsyncTaskResult<Result>> {

    private GitHubActivity activity;

    private ProgressBar progressBar;

    private boolean conectado;

    AbstractAsyncTask(GitHubActivity activity) {
        this.activity = activity;
        this.conectado = NetworkService.isConnected(activity);
    }

    protected abstract Result executeInBackground(Params[] params) throws Exception;

    protected abstract void onPostExecuteOnline(Result result);

    protected abstract void onPostExecuteOffline(Result result);

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.progressBar = getActivity().findViewById(R.id.progressBar1);
        this.progressBar.setVisibility(View.VISIBLE);
    }

    protected AsyncTaskResult<Result> doInBackground(Params... params) {

        AsyncTaskResult<Result> asyncTaskResult = new AsyncTaskResult<>();
        try {
            asyncTaskResult.setResult(executeInBackground(params));
        } catch (Exception e) {
            //e = ErrorHandler.getDetailedError(e, getActivity());
            Log.e(this.getClass().getName(), e.getMessage(), e);
            asyncTaskResult.setException(e);
        }
        return asyncTaskResult;
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<Result> asyncTaskResult) {

        this.progressBar.setVisibility(View.GONE);

        if (asyncTaskResult.hasException() && asyncTaskResult.existeMensagem()) {
            Toast.makeText(activity, asyncTaskResult.getMensagem(), Toast.LENGTH_LONG);
        } else {

            if (conectado) {
                onPostExecuteOnline(asyncTaskResult.getResult());
            } else {
                onPostExecuteOffline(asyncTaskResult.getResult());
            }

        }
    }

    public GitHubActivity getActivity() {
        return activity;
    }

    public boolean isConectado() {
        return conectado;
    }
}