// Generated by view binder compiler. Do not edit!
package com.ramlekhak.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.ramlekhak.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogLeaveFeedbackBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageButton btnClose;

  @NonNull
  public final MaterialButton btnSubmitFeedback;

  @NonNull
  public final EditText emailInput;

  @NonNull
  public final EditText feedbackInput;

  private DialogLeaveFeedbackBinding(@NonNull CardView rootView, @NonNull ImageButton btnClose,
      @NonNull MaterialButton btnSubmitFeedback, @NonNull EditText emailInput,
      @NonNull EditText feedbackInput) {
    this.rootView = rootView;
    this.btnClose = btnClose;
    this.btnSubmitFeedback = btnSubmitFeedback;
    this.emailInput = emailInput;
    this.feedbackInput = feedbackInput;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogLeaveFeedbackBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogLeaveFeedbackBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_leave_feedback, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogLeaveFeedbackBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_close;
      ImageButton btnClose = ViewBindings.findChildViewById(rootView, id);
      if (btnClose == null) {
        break missingId;
      }

      id = R.id.btn_submit_feedback;
      MaterialButton btnSubmitFeedback = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmitFeedback == null) {
        break missingId;
      }

      id = R.id.email_input;
      EditText emailInput = ViewBindings.findChildViewById(rootView, id);
      if (emailInput == null) {
        break missingId;
      }

      id = R.id.feedback_input;
      EditText feedbackInput = ViewBindings.findChildViewById(rootView, id);
      if (feedbackInput == null) {
        break missingId;
      }

      return new DialogLeaveFeedbackBinding((CardView) rootView, btnClose, btnSubmitFeedback,
          emailInput, feedbackInput);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
