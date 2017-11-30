package com.mobile.desafio.repositoriosgithub.asynctask.persistence;

import android.os.AsyncTask;
import android.util.Log;

import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.asynctask.AsyncTaskResult;

public abstract class AbstractPersistenceAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, AsyncTaskResult<Result>> {

    private GitHubActivity activity;

    public AbstractPersistenceAsyncTask(GitHubActivity activity) {
        this.activity = activity;
    }

    public abstract Result executeInBackground(Params[] params);

    @Override
    protected AsyncTaskResult<Result> doInBackground(Params[] params) {
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

    public GitHubActivity getActivity() {
        return activity;
    }
}