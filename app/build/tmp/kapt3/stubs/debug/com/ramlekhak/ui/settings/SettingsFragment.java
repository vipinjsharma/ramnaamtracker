package com.ramlekhak.ui.settings;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J\u001a\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010#\u001a\u00020\u0017H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006\'"}, d2 = {"Lcom/ramlekhak/ui/settings/SettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/ramlekhak/databinding/FragmentSettingsBinding;", "binding", "getBinding", "()Lcom/ramlekhak/databinding/FragmentSettingsBinding;", "languageRadioGroup", "Landroid/widget/RadioGroup;", "notificationSwitch", "Lcom/google/android/material/switchmaterial/SwitchMaterial;", "radioEnglish", "Landroid/widget/RadioButton;", "radioHindi", "resetMidnightSwitch", "userPreferences", "Lcom/ramlekhak/data/UserPreferences;", "getUserPreferences", "()Lcom/ramlekhak/data/UserPreferences;", "setUserPreferences", "(Lcom/ramlekhak/data/UserPreferences;)V", "loadCurrentSettings", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupListeners", "updateLocale", "languageCode", "", "app_debug"})
public final class SettingsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.ramlekhak.databinding.FragmentSettingsBinding _binding;
    @javax.inject.Inject
    public com.ramlekhak.data.UserPreferences userPreferences;
    private android.widget.RadioGroup languageRadioGroup;
    private android.widget.RadioButton radioEnglish;
    private android.widget.RadioButton radioHindi;
    private com.google.android.material.switchmaterial.SwitchMaterial notificationSwitch;
    private com.google.android.material.switchmaterial.SwitchMaterial resetMidnightSwitch;
    
    public SettingsFragment() {
        super();
    }
    
    private final com.ramlekhak.databinding.FragmentSettingsBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.UserPreferences getUserPreferences() {
        return null;
    }
    
    public final void setUserPreferences(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.UserPreferences p0) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadCurrentSettings() {
    }
    
    private final void setupListeners() {
    }
    
    private final void updateLocale(java.lang.String languageCode) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
}