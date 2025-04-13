package com.ramlekhak.viewmodel;

/**
 * ViewModel for the Writing screen.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\b\u0010\u0010\u001a\u00020\u000eH\u0002R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/ramlekhak/viewmodel/WritingViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/ramlekhak/data/CountRepository;", "(Lcom/ramlekhak/data/CountRepository;)V", "_todayCount", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "todayCount", "Landroidx/lifecycle/LiveData;", "getTodayCount", "()Landroidx/lifecycle/LiveData;", "addCount", "", "count", "loadTodayCount", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class WritingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.ramlekhak.data.CountRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _todayCount = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Integer> todayCount = null;
    
    @javax.inject.Inject
    public WritingViewModel(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.CountRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTodayCount() {
        return null;
    }
    
    private final void loadTodayCount() {
    }
    
    public final void addCount(int count) {
    }
}