// Generated by view binder compiler. Do not edit!
package com.ramlekhak.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.ramlekhak.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSettingsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final MaterialCardView languageCard;

  @NonNull
  public final RadioGroup languageRadioGroup;

  @NonNull
  public final MaterialCardView notificationCard;

  @NonNull
  public final SwitchMaterial notificationSwitch;

  @NonNull
  public final RadioButton radioEnglish;

  @NonNull
  public final RadioButton radioHindi;

  @NonNull
  public final MaterialCardView resetMidnightCard;

  @NonNull
  public final SwitchMaterial resetMidnightSwitch;

  private FragmentSettingsBinding(@NonNull ScrollView rootView,
      @NonNull MaterialCardView languageCard, @NonNull RadioGroup languageRadioGroup,
      @NonNull MaterialCardView notificationCard, @NonNull SwitchMaterial notificationSwitch,
      @NonNull RadioButton radioEnglish, @NonNull RadioButton radioHindi,
      @NonNull MaterialCardView resetMidnightCard, @NonNull SwitchMaterial resetMidnightSwitch) {
    this.rootView = rootView;
    this.languageCard = languageCard;
    this.languageRadioGroup = languageRadioGroup;
    this.notificationCard = notificationCard;
    this.notificationSwitch = notificationSwitch;
    this.radioEnglish = radioEnglish;
    this.radioHindi = radioHindi;
    this.resetMidnightCard = resetMidnightCard;
    this.resetMidnightSwitch = resetMidnightSwitch;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.language_card;
      MaterialCardView languageCard = ViewBindings.findChildViewById(rootView, id);
      if (languageCard == null) {
        break missingId;
      }

      id = R.id.language_radio_group;
      RadioGroup languageRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (languageRadioGroup == null) {
        break missingId;
      }

      id = R.id.notification_card;
      MaterialCardView notificationCard = ViewBindings.findChildViewById(rootView, id);
      if (notificationCard == null) {
        break missingId;
      }

      id = R.id.notification_switch;
      SwitchMaterial notificationSwitch = ViewBindings.findChildViewById(rootView, id);
      if (notificationSwitch == null) {
        break missingId;
      }

      id = R.id.radio_english;
      RadioButton radioEnglish = ViewBindings.findChildViewById(rootView, id);
      if (radioEnglish == null) {
        break missingId;
      }

      id = R.id.radio_hindi;
      RadioButton radioHindi = ViewBindings.findChildViewById(rootView, id);
      if (radioHindi == null) {
        break missingId;
      }

      id = R.id.reset_midnight_card;
      MaterialCardView resetMidnightCard = ViewBindings.findChildViewById(rootView, id);
      if (resetMidnightCard == null) {
        break missingId;
      }

      id = R.id.reset_midnight_switch;
      SwitchMaterial resetMidnightSwitch = ViewBindings.findChildViewById(rootView, id);
      if (resetMidnightSwitch == null) {
        break missingId;
      }

      return new FragmentSettingsBinding((ScrollView) rootView, languageCard, languageRadioGroup,
          notificationCard, notificationSwitch, radioEnglish, radioHindi, resetMidnightCard,
          resetMidnightSwitch);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
