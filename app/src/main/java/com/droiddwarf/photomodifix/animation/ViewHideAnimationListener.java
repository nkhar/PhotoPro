package com.droiddwarf.photomodifix.animation;

import android.animation.Animator;
import android.view.View;

public class ViewHideAnimationListener implements Animator.AnimatorListener {

    final View view;

    public ViewHideAnimationListener(View view) {
        this.view = view;
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        view.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }
}
