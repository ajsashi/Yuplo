package com.yuplo.presenter;


public interface FragmentPresenter extends Presenter{

    interface View extends Presenter.View{

        void setTitle();
    }
}
