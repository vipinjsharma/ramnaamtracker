// Generated by view binder compiler. Do not edit!
package com.ramlekhak.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class DialogTermsConditionsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageButton btnClose;

  private DialogTermsConditionsBinding(@NonNull ScrollView rootView,
      @NonNull ImageButton btnClose) {
    this.rootView = rootView;
    this.btnClose = btnClose;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogTermsConditionsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogTermsConditionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_terms_conditions, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogTermsConditionsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_close;
      ImageButton btnClose = ViewBindings.findChildViewById(rootView, id);
      if (btnClose == null) {
        break missingId;
      }

      return new DialogTermsConditionsBinding((ScrollView) rootView, btnClose);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
