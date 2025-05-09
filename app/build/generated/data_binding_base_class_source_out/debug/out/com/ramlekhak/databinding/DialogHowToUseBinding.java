// Generated by view binder compiler. Do not edit!
package com.ramlekhak.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ramlekhak.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogHowToUseBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageButton btnClose;

  @NonNull
  public final Button btnStartWalkthrough;

  private DialogHowToUseBinding(@NonNull ScrollView rootView, @NonNull ImageButton btnClose,
      @NonNull Button btnStartWalkthrough) {
    this.rootView = rootView;
    this.btnClose = btnClose;
    this.btnStartWalkthrough = btnStartWalkthrough;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogHowToUseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogHowToUseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_how_to_use, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogHowToUseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_close;
      ImageButton btnClose = ViewBindings.findChildViewById(rootView, id);
      if (btnClose == null) {
        break missingId;
      }

      id = R.id.btn_start_walkthrough;
      Button btnStartWalkthrough = ViewBindings.findChildViewById(rootView, id);
      if (btnStartWalkthrough == null) {
        break missingId;
      }

      return new DialogHowToUseBinding((ScrollView) rootView, btnClose, btnStartWalkthrough);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
