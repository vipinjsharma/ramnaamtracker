// Generated by view binder compiler. Do not edit!
package com.ramlekhak.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ramlekhak.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SplashScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ProgressBar loadingSpinner;

  @NonNull
  public final ImageView splashLogo;

  @NonNull
  public final TextView splashTagline;

  @NonNull
  public final TextView splashTitle;

  private SplashScreenBinding(@NonNull ConstraintLayout rootView,
      @NonNull ProgressBar loadingSpinner, @NonNull ImageView splashLogo,
      @NonNull TextView splashTagline, @NonNull TextView splashTitle) {
    this.rootView = rootView;
    this.loadingSpinner = loadingSpinner;
    this.splashLogo = splashLogo;
    this.splashTagline = splashTagline;
    this.splashTitle = splashTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SplashScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SplashScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.splash_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SplashScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.loading_spinner;
      ProgressBar loadingSpinner = ViewBindings.findChildViewById(rootView, id);
      if (loadingSpinner == null) {
        break missingId;
      }

      id = R.id.splash_logo;
      ImageView splashLogo = ViewBindings.findChildViewById(rootView, id);
      if (splashLogo == null) {
        break missingId;
      }

      id = R.id.splash_tagline;
      TextView splashTagline = ViewBindings.findChildViewById(rootView, id);
      if (splashTagline == null) {
        break missingId;
      }

      id = R.id.splash_title;
      TextView splashTitle = ViewBindings.findChildViewById(rootView, id);
      if (splashTitle == null) {
        break missingId;
      }

      return new SplashScreenBinding((ConstraintLayout) rootView, loadingSpinner, splashLogo,
          splashTagline, splashTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
