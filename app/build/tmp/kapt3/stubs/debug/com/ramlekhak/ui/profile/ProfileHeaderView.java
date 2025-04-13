package com.ramlekhak.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001c\u001a\u00020\rJ\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\rJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\rJ\u001a\u0010!\u001a\u00020\u001b2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001b0\u001aJ\b\u0010#\u001a\u00020\u001bH\u0002J\b\u0010$\u001a\u00020\u001bH\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J\u0010\u0010\'\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\rH\u0002J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\rH\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010 \u001a\u00020\rH\u0002R\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/ramlekhak/ui/profile/ProfileHeaderView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "avatarCircle", "cancelButton", "confirmButton", "currentName", "", "editNameButton", "Landroid/widget/ImageView;", "editStateContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "initialsText", "Landroid/widget/TextView;", "memberSinceText", "nameEditText", "Landroid/widget/EditText;", "nameText", "normalStateContainer", "onNameChangedListener", "Lkotlin/Function1;", "", "getName", "setMemberSince", "memberSince", "setName", "name", "setOnNameChangedListener", "listener", "setupClickListeners", "showEditState", "showNormalState", "showSuccessMessage", "updateInitials", "updateName", "newName", "validateName", "", "app_debug"})
public final class ProfileHeaderView extends androidx.cardview.widget.CardView {
    @org.jetbrains.annotations.NotNull
    private final androidx.cardview.widget.CardView avatarCircle = null;
    @org.jetbrains.annotations.NotNull
    private final android.widget.TextView initialsText = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.constraintlayout.widget.ConstraintLayout normalStateContainer = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.constraintlayout.widget.ConstraintLayout editStateContainer = null;
    @org.jetbrains.annotations.NotNull
    private final android.widget.TextView nameText = null;
    @org.jetbrains.annotations.NotNull
    private final android.widget.ImageView editNameButton = null;
    @org.jetbrains.annotations.NotNull
    private final android.widget.EditText nameEditText = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.cardview.widget.CardView confirmButton = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.cardview.widget.CardView cancelButton = null;
    @org.jetbrains.annotations.NotNull
    private final android.widget.TextView memberSinceText = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String currentName = "Ram Bhakt";
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNameChangedListener;
    
    @kotlin.jvm.JvmOverloads
    public ProfileHeaderView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    private final void setupClickListeners() {
    }
    
    private final boolean validateName(java.lang.String name) {
        return false;
    }
    
    private final void updateName(java.lang.String newName) {
    }
    
    private final void updateInitials(java.lang.String name) {
    }
    
    private final void showNormalState() {
    }
    
    private final void showEditState() {
    }
    
    private final void showSuccessMessage() {
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setMemberSince(@org.jetbrains.annotations.NotNull
    java.lang.String memberSince) {
    }
    
    public final void setOnNameChangedListener(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> listener) {
    }
    
    @kotlin.jvm.JvmOverloads
    public ProfileHeaderView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public ProfileHeaderView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
}