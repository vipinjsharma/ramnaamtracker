document.addEventListener('DOMContentLoaded', function() {
    console.log("DOM fully loaded, initializing app...");
    // DOM Elements
    const app = document.getElementById('app');
    const homePage = document.getElementById('homePage');
    const writingPage = document.getElementById('writingPage');
    const profilePage = document.getElementById('profilePage');
    const adminPage = document.getElementById('adminPage');
    const homeButton = document.getElementById('homeButton');
    const menuButton = document.getElementById('menuButton');
    const profileButton = document.getElementById('profileButton');
    const startWritingBtn = document.getElementById('startWritingBtn');
    const canvas = document.getElementById('drawingCanvas');
    const clearBtn = document.getElementById('clearBtn');
    const drawBtn = document.getElementById('drawBtn');
    const submitBtn = document.getElementById('submitBtn');
    const shareBtn = document.getElementById('shareBtn');
    const downloadBtn = document.getElementById('downloadBtn');
    const whatsappShareBtn = document.getElementById('whatsappShareBtn');
    
    // Get menu and overlay elements
    const menuOverlay = document.getElementById('menuOverlay');
    const profileOverlay = document.getElementById('profileOverlay');
    const closeMenuBtn = document.getElementById('closeMenuBtn');
    const closeProfileBtn = document.getElementById('closeProfileBtn');
    const quickCurrentStreak = document.getElementById('quickCurrentStreak');
    
    // Menu navigation links
    const menuWriteLink = document.getElementById('menuWriteLink');
    const menuProfileLink = document.getElementById('menuProfileLink');
    const menuShareLink = document.getElementById('menuShareLink');
    const menuDownloadLink = document.getElementById('menuDownloadLink');
    const menuRateLink = document.getElementById('menuRateLink');
    const menuFeedbackLink = document.getElementById('menuFeedbackLink');
    const menuHowToLink = document.getElementById('menuHowToLink');
    const menuLanguageLink = document.getElementById('menuLanguageLink');
    const menuThemeLink = document.getElementById('menuThemeLink');
    const menuTermsLink = document.getElementById('menuTermsLink');
    const menuPrivacyLink = document.getElementById('menuPrivacyLink');
    
    // Sidebar profile elements
    const sidebarUsername = document.getElementById('sidebarUsername');
    const sidebarStats = document.getElementById('sidebarStats');
    const sidebarAvatar = document.getElementById('sidebarAvatar');
    
    // Profile navigation links
    const profileGoalsLink = document.getElementById('profileGoalsLink');
    const profileReminderLink = document.getElementById('profileReminderLink');
    const profileLogoutLink = document.getElementById('profileLogoutLink');
    const editGoalsBtn = document.getElementById('editGoalsBtn');
    const reminderSettingsBtn = document.getElementById('reminderSettingsBtn');
    
    // Profile page elements
    const adminButton = document.getElementById('adminButton');
    const logoutButton = document.getElementById('logoutButton');
    const languageSelect = document.getElementById('languageSelect');
    const themeSelect = document.getElementById('themeSelect');
    const reminderToggle = document.getElementById('reminderToggle');
    
    // Admin page elements
    const saveSettingsBtn = document.getElementById('saveSettingsBtn');
    const exportDataBtn = document.getElementById('exportDataBtn');
    const backupDataBtn = document.getElementById('backupDataBtn');
    const malaCountInput = document.getElementById('malaCountInput');
    const appTitleInput = document.getElementById('appTitleInput');
    const goalSettingsInput = document.getElementById('goalSettingsInput');
    
    // Counter elements
    const homeTodayCount = document.getElementById('homeTodayCount');
    const homeTodayMalaCount = document.getElementById('homeTodayMalaCount');
    const homeTotalCount = document.getElementById('homeTotalCount');
    const writingTodayCount = document.getElementById('writingTodayCount');
    const writingTodayMalaCount = document.getElementById('writingTodayMalaCount');
    
    // Profile stats elements
    const profileTotalCount = document.getElementById('profileTotalCount');
    const profileTotalMalas = document.getElementById('profileTotalMalas');
    const profileCurrentStreak = document.getElementById('profileCurrentStreak');
    const profileLongestStreak = document.getElementById('profileLongestStreak');
    const dailyGoalProgress = document.getElementById('dailyGoalProgress');
    const monthlyGoalProgress = document.getElementById('monthlyGoalProgress');
    
    // Constants
    const MALA_COUNT = 108; // Number of rams in one mala
    
    // App settings
    let appLanguage = 'en'; // Default language (en = English, hi = Hindi)
    let currentTheme = 'light'; // Default theme (light, dark, system)
    let userName = 'Ram Bhakt'; // Default user name
    
    // Translations
    const translations = {
        'en': {
            // Home Page
            'app_title': 'Ram Lekhak',
            'today_count': 'Today\'s Count',
            'today_mala': 'Today\'s Malas',
            'total_count': 'Total Count',
            'write_ram': 'Write RAM',
            'stats': 'Stats',
            
            // Writing Page
            'clear': 'Clear',
            'auto_draw': 'Auto Draw',
            'submit': 'Submit',
            
            // Profile Page
            'profile': 'Profile',
            'total_written': 'Total RAM Written',
            'total_malas': 'Total Malas',
            'current_streak': 'Current Streak',
            'longest_streak': 'Longest Streak',
            'daily_goal': 'Daily Goal',
            'monthly_goal': 'Monthly Goal',
            'writing_goals': 'Writing Goals',
            'account': 'Account',
            'theme': 'Theme',
            'daily_reminders': 'Daily Reminders',
            'admin': 'Admin',
            'logout': 'Logout',
            
            // Theme Settings
            'light': 'Light',
            'dark': 'Dark',
            'system': 'System',
            'save': 'Save',
            
            // Menu
            'ram_writing': 'Ram Naam Writing',
            'share': 'Share',
            'rate_app': 'Rate App',
            'feedback': 'Feedback',
            'how_to_use': 'How to Use',
            'language': 'Language',
            'terms': 'Terms',
            'privacy': 'Privacy',
            
            // Other
            'cancel': 'Cancel',
            'confirm': 'Confirm',
            
            // Name Editing
            'name_updated': 'Name updated!',
            'name_length_error': 'Name must be between 2 and 30 characters',
            'edit_name': 'Edit Name'
        },
        'hi': {
            // Home Page
            'app_title': 'राम लेखक',
            'today_count': 'आज की गिनती',
            'today_mala': 'आज की माला',
            'total_count': 'कुल गिनती',
            'write_ram': 'राम लिखें',
            'stats': 'आंकड़े',
            
            // Writing Page
            'clear': 'साफ़ करें',
            'auto_draw': 'स्वतः बनाएं',
            'submit': 'जमा करें',
            
            // Profile Page
            'profile': 'प्रोफ़ाइल',
            'total_written': 'कुल लिखित राम',
            'total_malas': 'कुल मालाएँ',
            'current_streak': 'वर्तमान स्ट्रीक',
            'longest_streak': 'सबसे लंबी स्ट्रीक',
            'daily_goal': 'दैनिक लक्ष्य',
            'monthly_goal': 'मासिक लक्ष्य',
            'writing_goals': 'लेखन लक्ष्य',
            'account': 'खाता',
            'theme': 'थीम',
            'daily_reminders': 'दैनिक अनुस्मारक',
            'admin': 'प्रशासन',
            'logout': 'लॉगआउट',
            
            // Theme Settings
            'light': 'उजला',
            'dark': 'अंधेरा',
            'system': 'सिस्टम',
            'save': 'सहेजें',
            
            // Menu
            'ram_writing': 'राम नाम लेखन',
            'share': 'साझा करें',
            'rate_app': 'ऐप रेट करें',
            'feedback': 'प्रतिक्रिया',
            'how_to_use': 'उपयोग कैसे करें',
            'language': 'भाषा',
            'terms': 'नियम और शर्तें',
            'privacy': 'गोपनीयता',
            
            // Other
            'cancel': 'रद्द करें',
            'confirm': 'पुष्टि करें',
            
            // Name Editing
            'name_updated': 'नाम अपडेट हो गया!',
            'name_length_error': 'नाम 2 से 30 अक्षरों के बीच होना चाहिए',
            'edit_name': 'नाम संपादित करें'
        }
    };
    let reminderEnabled = false; // Default reminder state
    let reminderTime = ''; // Default reminder time (empty - no selection)
    let reminderDays = []; // Default reminder days (empty - no selection)
    let isAdminUser = false; // Default user role (non-admin)
    const DAILY_GOAL = 108; // Default daily goal (1 mala)
    const MONTHLY_GOAL = 21; // Default monthly goal (21 malas)
    
    // State
    let isDrawing = false;
    let lastX = 0;
    let lastY = 0;
    let todayCount = 0;
    let todayMalaCount = 0;
    let totalCount = 0;
    let currentStreak = 0;
    let longestStreak = 0;
    let drawingContext;
    let lastActivePage = homePage;
    
    // Goal tracking
    let dailyGoal = 108; // Default: 1 mala per day (108 Rams)
    let monthlyGoal = 3240; // Default: 30 malas per month (30 days * 1 mala * 108)
    let currentMonthCount = 0; // Total for current month
    
    // Initialize the app
    initializeApp();
    
    // Event Listeners
    menuButton.addEventListener('click', toggleMenu);
    homeButton.addEventListener('click', navigateToHome);
    profileButton.addEventListener('click', navigateToProfile);
    
    // Name editing event listeners
    const editNameBtn = document.getElementById('edit-name-btn');
    const saveNameBtn = document.getElementById('save-name-btn');
    const cancelNameBtn = document.getElementById('cancel-name-btn');
    const profileName = document.getElementById('profile-name');
    const nameInput = document.getElementById('name-input');
    const editNameControls = document.getElementById('edit-name-controls');
    
    if (editNameBtn) {
        editNameBtn.addEventListener('click', handleEditNameClick);
    }
    
    if (saveNameBtn) {
        saveNameBtn.addEventListener('click', handleSaveNameClick);
    }
    
    if (cancelNameBtn) {
        cancelNameBtn.addEventListener('click', handleCancelNameClick);
    }
    
    // Navigation listeners
    startWritingBtn.addEventListener('click', navigateToWriting);
    clearBtn.addEventListener('click', clearCanvas);
    drawBtn.addEventListener('click', autoDraw);
    submitBtn.addEventListener('click', submitDrawing);
    shareBtn.addEventListener('click', shareStats);
    downloadBtn.addEventListener('click', downloadStats);
    // WhatsApp share button on writing page
    if (whatsappShareBtn) {
        whatsappShareBtn.addEventListener('click', shareViaWhatsApp);
    }
    
    // Profile page event listeners
    if (adminButton) adminButton.addEventListener('click', navigateToAdmin);
    if (logoutButton) logoutButton.addEventListener('click', handleLogout);
    if (languageSelect) languageSelect.addEventListener('change', handleLanguageChange);
    if (themeSelect) themeSelect.addEventListener('change', handleThemeChange);
    
    // Goal settings listeners
    if (editGoalsBtn) editGoalsBtn.addEventListener('click', handleEditGoalsClick);
    if (reminderSettingsBtn) reminderSettingsBtn.addEventListener('click', handleProfileReminderClick);
    
    // Admin page event listeners
    if (saveSettingsBtn) saveSettingsBtn.addEventListener('click', saveAppSettings);
    if (exportDataBtn) exportDataBtn.addEventListener('click', exportData);
    if (backupDataBtn) backupDataBtn.addEventListener('click', backupData);
    
    // Canvas event listeners
    canvas.addEventListener('mousedown', startDrawing);
    canvas.addEventListener('mousemove', draw);
    canvas.addEventListener('mouseup', stopDrawing);
    canvas.addEventListener('mouseout', stopDrawing);
    
    // Touch events for mobile
    canvas.addEventListener('touchstart', startDrawingTouch);
    canvas.addEventListener('touchmove', drawTouch);
    canvas.addEventListener('touchend', stopDrawing);
    
    // Menu and overlay event listeners
    closeMenuBtn.addEventListener('click', closeMenuOverlay);
    closeProfileBtn.addEventListener('click', closeProfileOverlay);
    
    // Menu navigation links event listeners
    menuWriteLink.addEventListener('click', handleMenuWriteClick);
    if (menuProfileLink) menuProfileLink.addEventListener('click', navigateToProfile);
    menuShareLink.addEventListener('click', handleMenuShareClick);
    if (menuDownloadLink) menuDownloadLink.addEventListener('click', downloadStats);
    menuRateLink.addEventListener('click', handleMenuRateClick);
    menuFeedbackLink.addEventListener('click', handleMenuFeedbackClick);
    menuHowToLink.addEventListener('click', handleMenuHowToClick);
    menuLanguageLink.addEventListener('click', handleMenuLanguageClick);
    menuThemeLink.addEventListener('click', handleProfileThemeClick);
    menuTermsLink.addEventListener('click', handleMenuTermsClick);
    menuPrivacyLink.addEventListener('click', handleMenuPrivacyClick);
    
    // Profile menu event listeners
    if (profileGoalsLink) profileGoalsLink.addEventListener('click', handleEditGoalsClick);
    if (profileReminderLink) profileReminderLink.addEventListener('click', handleProfileReminderClick);
    if (profileLogoutLink) profileLogoutLink.addEventListener('click', handleProfileLogoutClick);
    
    // Helper function to get translated text
    function getTranslation(key) {
        const currentLang = translations[appLanguage] || translations['en'];
        return currentLang[key] || key;
    }
    
    // Secret admin mode (click app logo 5 times)
    const appLogo = document.querySelector('.header-center h1');
    let logoClickCount = 0;
    let clickTimer = null;
    
    if (appLogo) {
        appLogo.addEventListener('click', () => {
            logoClickCount++;
            
            // Clear previous timer
            if (clickTimer) {
                clearTimeout(clickTimer);
            }
            
            // Set timer to reset count after 3 seconds of inactivity
            clickTimer = setTimeout(() => {
                logoClickCount = 0;
            }, 3000);
            
            // Check if we've reached 5 clicks
            if (logoClickCount === 5) {
                // Toggle admin mode
                isAdminUser = !isAdminUser;
                
                // Update UI
                updateProfileStats();
                
                // Show toast message
                const message = isAdminUser ? 
                    getTranslation('admin_activated') || 'Admin mode activated' : 
                    getTranslation('admin_deactivated') || 'Admin mode deactivated';
                
                showToast(message);
                
                // Vibrate device if possible
                try {
                    if (window.AndroidInterface && typeof window.AndroidInterface.vibrate === 'function') {
                        window.AndroidInterface.vibrate();
                    }
                } catch (e) {
                    console.warn("Could not vibrate device:", e);
                }
                
                // Reset click count
                logoClickCount = 0;
            }
        });
    } else {
        console.warn("App logo element not found for admin mode activation");
    }
    
    // Functions
    function initializeApp() {
        // Check for URL parameters (used when launched from Android WebView)
        checkUrlParameters();
        
        // Set up canvas
        setupCanvas();
        
        // Load saved data
        loadData();
        
        // Update UI
        updateCountDisplay();
        updateProfileStats();
        
        // Apply language settings
        applyLanguage();
        
        // Show home page initially
        navigateToHome();
    }
    
    function checkUrlParameters() {
        // Parse URL parameters
        const urlParams = new URLSearchParams(window.location.search);
        const langParam = urlParams.get('lang');
        
        // Check if we have a language parameter
        if (langParam) {
            console.log("Detected language from URL parameter:", langParam);
            if (langParam === 'hi' || langParam === 'en') {
                appLanguage = langParam;
                console.log("Setting app language to:", appLanguage);
            }
        }
        
        // Check if we're running in Android WebView
        const isAndroidApp = window.AndroidInterface !== undefined || 
                            navigator.userAgent.includes('RamLekhakApp') ||
                            document.body.classList.contains('android-app');
        
        if (isAndroidApp) {
            console.log("Running in Android WebView");
            document.body.classList.add('android-app');
            
            // Check for admin mode from Android
            try {
                if (window.AndroidInterface && typeof window.AndroidInterface.isAdminEnabled === 'function') {
                    isAdminUser = window.AndroidInterface.isAdminEnabled();
                    console.log("Admin mode from Android:", isAdminUser);
                }
            } catch (error) {
                console.warn("Error checking admin mode from Android interface:", error);
            }
            
            // Check if we can get language from Android interface
            try {
                if (window.AndroidInterface && typeof window.AndroidInterface.getDeviceLanguage === 'function') {
                    const deviceLang = window.AndroidInterface.getDeviceLanguage();
                    console.log("Detected language from Android:", deviceLang);
                    if (deviceLang === 'hi' || deviceLang === 'en') {
                        // Only override if not already set from URL parameter
                        if (!langParam) {
                            appLanguage = deviceLang;
                            console.log("Setting app language from Android interface:", appLanguage);
                        }
                    }
                }
            } catch (error) {
                console.warn("Error getting language from Android interface:", error);
            }
        }
    }
    
    function setupCanvas() {
        // Set canvas to full width of container
        canvas.width = canvas.parentElement.clientWidth;
        canvas.height = canvas.parentElement.clientHeight;
        
        // Get drawing context
        drawingContext = canvas.getContext('2d');
        
        // Get current theme accent color
        let accentColor = getComputedStyle(document.documentElement).getPropertyValue('--accent-color').trim() || "#ff7817";
        
        // Set up context style based on current theme
        drawingContext.strokeStyle = accentColor;
        drawingContext.lineJoin = 'round';
        drawingContext.lineCap = 'round';
        drawingContext.lineWidth = 5;
    }
    
    function loadData() {
        // Get today's date for comparison
        const today = new Date().toDateString();
        const thisMonth = new Date().getMonth() + 1; // 1-12 for month
        
        // Check if we have stored data
        const savedData = JSON.parse(localStorage.getItem('ramNaamData')) || {};
        
        // Initialize counts
        if (savedData.lastDate === today) {
            todayCount = savedData.todayCount || 0;
            // Calculate mala count based on today's count
            todayMalaCount = Math.floor(todayCount / MALA_COUNT);
            
            // If user wrote something today, update streak
            if (todayCount > 0 && savedData.lastActiveDate !== today) {
                // Check if last active date was yesterday to continue streak
                const yesterday = new Date();
                yesterday.setDate(yesterday.getDate() - 1);
                
                if (savedData.lastActiveDate === yesterday.toDateString()) {
                    currentStreak = (savedData.currentStreak || 0) + 1;
                } else {
                    currentStreak = 1; // Reset streak if not consecutive days
                }
                
                // Update longest streak if needed
                longestStreak = Math.max(currentStreak, savedData.longestStreak || 0);
            } else {
                currentStreak = savedData.currentStreak || 0;
                longestStreak = savedData.longestStreak || 0;
            }
        } else {
            // Reset daily counts if it's a new day
            todayCount = 0;
            todayMalaCount = 0;
            
            // Initialize streaks from saved data
            currentStreak = savedData.currentStreak || 0;
            longestStreak = savedData.longestStreak || 0;
        }
        
        totalCount = savedData.totalCount || 0;
        
        // Load monthly count
        if (savedData.currentMonth === thisMonth) {
            // If same month, load saved monthly count
            currentMonthCount = savedData.currentMonthCount || 0;
            
            // Make sure currentMonthCount is at least as much as todayCount
            // This fixes issues where the two counts might get out of sync
            if (currentMonthCount < todayCount) {
                currentMonthCount = todayCount;
            }
        } else {
            // Reset monthly count for new month
            currentMonthCount = todayCount; // Start with today's count
        }
        
        // Load custom goals
        dailyGoal = savedData.dailyGoal || dailyGoal;
        monthlyGoal = savedData.monthlyGoal || monthlyGoal;
        
        // Load settings
        if (savedData.settings) {
            if (savedData.settings.language) {
                appLanguage = savedData.settings.language;
                
                // Only set select value if element exists
                if (languageSelect) {
                    languageSelect.value = savedData.settings.language;
                }
            }
            
            // Load saved username if available
            if (savedData.settings.userName) {
                userName = savedData.settings.userName;
            }
            if (savedData.settings.theme) {
                currentTheme = savedData.settings.theme;
                
                // Only set select value if element exists
                if (themeSelect) {
                    themeSelect.value = savedData.settings.theme;
                }
                applyTheme(savedData.settings.theme);
            }
            if (savedData.settings.reminder !== undefined && reminderToggle) {
                reminderToggle.checked = savedData.settings.reminder;
            }
        }
        
        // Load reminder settings
        const savedReminders = JSON.parse(localStorage.getItem('ramNaamReminders')) || {};
        if (savedReminders) {
            reminderEnabled = savedReminders.enabled !== undefined ? savedReminders.enabled : reminderEnabled;
            reminderTime = savedReminders.time || reminderTime;
            reminderDays = savedReminders.days || reminderDays;
        }
    }
    
    function saveData() {
        const today = new Date().toDateString();
        const thisMonth = new Date().getMonth() + 1; // 1-12 for month
        
        // Create settings object with null checks
        const settings = {
            language: appLanguage,
            theme: currentTheme,
            reminder: false,
            userName: userName
        };
        
        // Add values from form elements if they exist
        if (languageSelect) {
            settings.language = appLanguage || languageSelect.value;
        }
        
        if (themeSelect) {
            settings.theme = currentTheme || themeSelect.value;
        }
        
        if (reminderToggle) {
            settings.reminder = reminderToggle.checked;
        }
        
        const dataToSave = {
            lastDate: today,
            lastActiveDate: today,
            todayCount: todayCount,
            todayMalaCount: todayMalaCount,
            totalCount: totalCount,
            currentStreak: currentStreak,
            longestStreak: longestStreak,
            currentMonth: thisMonth,
            currentMonthCount: currentMonthCount,
            dailyGoal: dailyGoal,
            monthlyGoal: monthlyGoal,
            settings: settings
        };
        
        localStorage.setItem('ramNaamData', JSON.stringify(dataToSave));
    }
    
    function updateCountDisplay() {
        // Update home page counts
        homeTodayCount.textContent = todayCount;
        homeTodayMalaCount.textContent = todayMalaCount;
        homeTotalCount.textContent = totalCount;
        
        // Update writing page counts
        writingTodayCount.textContent = todayCount;
        writingTodayMalaCount.textContent = todayMalaCount;
        writingCurrentStreak.textContent = `${currentStreak} Days`;
        
        // Update daily progress bar on writing page
        const dailyProgress = Math.min(100, (todayCount / dailyGoal) * 100);
        const progressBar = document.getElementById('dailyProgressBar');
        if (progressBar) {
            progressBar.style.width = `${dailyProgress}%`;
            
            // Also update the percentage text
            const progressPercentage = document.getElementById('progress-percentage');
            if (progressPercentage) {
                progressPercentage.textContent = `${Math.round(dailyProgress)}%`;
            }
        }
    }
    
    function updateProfileStats() {
        // Update profile stats
        profileTotalCount.textContent = totalCount;
        profileTotalMalas.textContent = Math.floor(totalCount / MALA_COUNT);
        profileCurrentStreak.textContent = `${currentStreak} days`;
        profileLongestStreak.textContent = `${longestStreak} days`;
        
        // Update user name display
        const profileNameElement = document.getElementById('profile-name');
        if (profileNameElement) {
            profileNameElement.textContent = userName;
            
            // Update avatar with new name
            const avatarImg = document.querySelector('.profile-avatar img');
            if (avatarImg) {
                const nameParts = userName.split(' ');
                const initials = nameParts.map(part => part.charAt(0)).join('+');
                avatarImg.src = `https://ui-avatars.com/api/?name=${encodeURIComponent(userName)}&background=FF7817&color=fff`;
            }
        }
        
        // Update sidebar stats
        if (sidebarUsername) {
            sidebarUsername.textContent = userName;
        }
        
        if (sidebarStats) {
            const totalMalas = Math.floor(totalCount / MALA_COUNT);
            sidebarStats.textContent = `${totalMalas} ${totalMalas === 1 ? 'mala' : 'malas'} completed`;
        }
        
        if (sidebarAvatar) {
            sidebarAvatar.src = `https://ui-avatars.com/api/?name=${encodeURIComponent(userName)}&background=FF7817&color=fff`;
        }
        
        // Update progress bars using custom goals
        const dailyProgress = Math.min(100, (todayCount / dailyGoal) * 100);
        dailyGoalProgress.style.width = `${dailyProgress}%`;
        
        // Calculate monthly progress based on total counts instead of just full malas
        // This allows for partial mala progress to show in the UI
        const monthProgress = Math.min(100, (currentMonthCount / monthlyGoal) * 100);
        monthlyGoalProgress.style.width = `${monthProgress}%`;
        
        // Calculate mala values for accurate display
        const monthlyMalaGoal = Math.ceil(monthlyGoal / MALA_COUNT);
        const totalMonthlyEntries = monthlyMalaGoal * MALA_COUNT;
        const completedMalas = Math.floor(currentMonthCount / MALA_COUNT);
        
        // Update goal text displays if they exist
        if (document.getElementById('daily-goal-display')) {
            document.getElementById('daily-goal-display').textContent = 
                `${todayCount}/${dailyGoal} (${Math.round(dailyProgress)}%)`;
        }
        
        if (document.getElementById('monthly-goal-display')) {
            // New clearer format: "168/3240 (1 of 30 malas completed)"
            document.getElementById('monthly-goal-display').textContent = 
                `${currentMonthCount}/${totalMonthlyEntries} (${completedMalas} of ${monthlyMalaGoal} malas completed)`;
        }
        
        // Add detailed subtext for monthly goal if element exists
        const monthlyGoalSubtext = document.getElementById('monthly-goal-subtext');
        if (monthlyGoalSubtext) {
            const percentComplete = Math.round(monthProgress);
            monthlyGoalSubtext.innerHTML = `<span>${percentComplete}%</span> of your monthly goal achieved`;
        }
        
        // Show/hide admin button based on user role
        if (isAdminUser) {
            adminButton.style.display = 'block';
        } else {
            adminButton.style.display = 'none';
        }
    }
    
    function navigateToPage(page) {
        // Hide all pages
        homePage.classList.remove('active');
        writingPage.classList.remove('active');
        profilePage.classList.remove('active');
        adminPage.classList.remove('active');
        
        // Show target page
        page.classList.add('active');
        
        // Update button visibility for different pages
        if (page === homePage) {
            // On home page, show all navigation buttons
            menuButton.style.visibility = 'visible';
            profileButton.style.visibility = 'visible';
        } else {
            // On other pages, still show all buttons but make menu toggle smaller
            menuButton.style.visibility = 'visible';
            profileButton.style.visibility = 'visible';
        }
        
        // Save last active page for back navigation (if not admin)
        if (page !== adminPage) {
            lastActivePage = page;
        }
        
        // Update stats
        updateCountDisplay();
        
        if (page === profilePage) {
            updateProfileStats();
        }
        
        // Set up canvas if needed
        if (page === writingPage) {
            setupCanvas();
        }
    }
    
    function navigateToHome() {
        navigateToPage(homePage);
    }
    
    function navigateToWriting() {
        navigateToPage(writingPage);
    }
    
    function navigateToProfile() {
        closeAllOverlays();
        navigateToPage(profilePage);
    }
    
    function navigateToAdmin() {
        navigateToPage(adminPage);
    }
    
    function handleBackNavigation() {
        if (adminPage.classList.contains('active')) {
            navigateToPage(lastActivePage);
        } else {
            navigateToHome();
        }
    }
    
    // Name editing functions
    function handleEditNameClick() {
        // Get references to elements
        const profileName = document.getElementById('profile-name');
        const nameInput = document.getElementById('name-input');
        const editNameControls = document.getElementById('edit-name-controls');
        
        // Hide name element and show edit controls
        profileName.style.display = 'none';
        editNameControls.style.display = 'flex';
        
        // Set input value to current name
        nameInput.value = userName;
        
        // Focus the input field
        nameInput.focus();
    }
    
    function handleSaveNameClick() {
        // Get references to elements
        const profileName = document.getElementById('profile-name');
        const nameInput = document.getElementById('name-input');
        const editNameControls = document.getElementById('edit-name-controls');
        
        // Validate the name (2-30 characters)
        const newName = nameInput.value.trim();
        
        if (newName.length < 2 || newName.length > 30) {
            showToast(getTranslation('name_length_error') || 'Name must be between 2 and 30 characters', 3000, 'error');
            return;
        }
        
        // Update the name variable
        userName = newName;
        
        // Update the display
        profileName.textContent = userName;
        
        // Show name element and hide edit controls
        profileName.style.display = 'block';
        editNameControls.style.display = 'none';
        
        // Save to local storage
        saveData();
        
        // Update the avatar
        const avatarImg = document.querySelector('.profile-avatar img');
        if (avatarImg) {
            avatarImg.src = `https://ui-avatars.com/api/?name=${encodeURIComponent(userName)}&background=FF7817&color=fff`;
        }
        
        // Show success message
        showToast(getTranslation('name_updated') || 'Name updated!', 2000);
        
        // Add haptic feedback if available (mobile device)
        try {
            if (window.navigator && window.navigator.vibrate) {
                window.navigator.vibrate(50);
            } else if (window.AndroidInterface && typeof window.AndroidInterface.vibrate === 'function') {
                window.AndroidInterface.vibrate(50);
            }
        } catch (e) {
            console.warn("Could not vibrate device:", e);
        }
    }
    
    function handleCancelNameClick() {
        // Get references to elements
        const profileName = document.getElementById('profile-name');
        const editNameControls = document.getElementById('edit-name-controls');
        
        // Show name element and hide edit controls
        profileName.style.display = 'block';
        editNameControls.style.display = 'none';
    }
    
    function toggleMenu() {
        menuOverlay.classList.add('active');
        menuOverlay.style.width = '100%';
    }
    
    function toggleProfile() {
        profileOverlay.classList.add('active');
        profileOverlay.style.width = '100%';
        // Update quick streak display
        quickCurrentStreak.textContent = currentStreak;
    }
    
    function closeMenuOverlay() {
        menuOverlay.classList.remove('active');
        menuOverlay.style.width = '0';
    }
    
    function closeProfileOverlay() {
        profileOverlay.classList.remove('active');
        profileOverlay.style.width = '0';
    }
    
    function closeAllOverlays() {
        closeMenuOverlay();
        closeProfileOverlay();
    }
    
    // Menu link handlers
    function handleMenuWriteClick() {
        closeMenuOverlay();
        navigateToWriting();
    }
    
    function handleMenuShareClick() {
        closeMenuOverlay();
        
        // Options for sharing
        const shareOptions = `
            <div class="share-options">
                <h3>Share Your Progress</h3>
                <p>Share your Ram Naam writing journey with others:</p>
                <div class="share-buttons">
                    <button id="shareWhatsapp" class="share-button whatsapp">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z"></path>
                        </svg>
                        WhatsApp
                    </button>
                    <button id="shareSMS" class="share-button sms">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path>
                        </svg>
                        SMS
                    </button>
                    <button id="shareEmail" class="share-button email">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path>
                            <polyline points="22,6 12,13 2,6"></polyline>
                        </svg>
                        Email
                    </button>
                    <button id="shareCopy" class="share-button copy">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path>
                            <rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect>
                        </svg>
                        Copy Text
                    </button>
                </div>
            </div>
        `;
        
        // Create a modal for share options
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${shareOptions}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Share text
        const shareText = `I've written RAM naam ${totalCount} times (${Math.floor(totalCount / MALA_COUNT)} malas) with my current streak of ${currentStreak} days!`;
        
        // WhatsApp sharing
        const whatsappBtn = modal.querySelector('#shareWhatsapp');
        whatsappBtn.addEventListener('click', () => {
            window.open(`https://wa.me/?text=${encodeURIComponent(shareText)}`, '_blank');
        });
        
        // SMS sharing
        const smsBtn = modal.querySelector('#shareSMS');
        smsBtn.addEventListener('click', () => {
            window.open(`sms:?body=${encodeURIComponent(shareText)}`, '_blank');
        });
        
        // Email sharing
        const emailBtn = modal.querySelector('#shareEmail');
        emailBtn.addEventListener('click', () => {
            window.open(`mailto:?subject=My Ram Naam Writing Progress&body=${encodeURIComponent(shareText)}`, '_blank');
        });
        
        // Copy text
        const copyBtn = modal.querySelector('#shareCopy');
        copyBtn.addEventListener('click', () => {
            navigator.clipboard.writeText(shareText).then(() => {
                alert('Text copied to clipboard!');
                modal.style.display = 'none';
                setTimeout(() => {
                    document.body.removeChild(modal);
                }, 300);
            }).catch(err => {
                console.error('Could not copy text: ', err);
                alert('Failed to copy text. Please try again.');
            });
        });
    }
    
    function handleMenuRateClick() {
        closeMenuOverlay();
        
        // Create rating form HTML
        const ratingForm = `
            <div class="rating-form">
                <h3>Rate Ram Naam Writing App</h3>
                <p>We value your feedback! Please rate your experience:</p>
                
                <div class="star-rating">
                    <input type="radio" id="star5" name="rating" value="5">
                    <label for="star5">★</label>
                    <input type="radio" id="star4" name="rating" value="4">
                    <label for="star4">★</label>
                    <input type="radio" id="star3" name="rating" value="3">
                    <label for="star3">★</label>
                    <input type="radio" id="star2" name="rating" value="2">
                    <label for="star2">★</label>
                    <input type="radio" id="star1" name="rating" value="1">
                    <label for="star1">★</label>
                </div>
                
                <textarea placeholder="Tell us what you like about the app or how we can improve it..."></textarea>
                
                <button id="submitRating" class="primary-button">Submit Rating</button>
            </div>
        `;
        
        // Create a modal for the rating form
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${ratingForm}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Submit button functionality
        const submitBtn = modal.querySelector('#submitRating');
        submitBtn.addEventListener('click', () => {
            const selectedRating = modal.querySelector('input[name="rating"]:checked');
            const feedbackText = modal.querySelector('textarea').value;
            
            if (selectedRating) {
                // In a real app, this would send the rating to a server
                const rating = selectedRating.value;
                alert(`Thank you for your ${rating}-star rating!`);
                modal.style.display = 'none';
                setTimeout(() => {
                    document.body.removeChild(modal);
                }, 300);
            } else {
                alert('Please select a rating before submitting.');
            }
        });
    }
    
    function handleMenuFeedbackClick() {
        closeMenuOverlay();
        
        // Create feedback form HTML
        const feedbackForm = `
            <div class="feedback-form">
                <h3>Leave Feedback</h3>
                <p>We'd love to hear your thoughts on how to improve the Ram Naam Writing app!</p>
                
                <textarea placeholder="Please share your feedback, suggestions, or report any issues you've encountered..."></textarea>
                <input type="email" placeholder="Your email (optional)">
                
                <button id="submitFeedback" class="primary-button">Submit Feedback</button>
            </div>
        `;
        
        // Create a modal for the feedback form
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${feedbackForm}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Submit button functionality
        const submitBtn = modal.querySelector('#submitFeedback');
        submitBtn.addEventListener('click', () => {
            const feedbackText = modal.querySelector('textarea').value;
            const email = modal.querySelector('input[type="email"]').value;
            
            if (feedbackText.trim() === '') {
                alert('Please enter your feedback before submitting.');
                return;
            }
            
            // In a real app, this would send the feedback to a server
            alert('Thank you for your feedback! We appreciate your input.');
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
    }
    
    function handleMenuHowToClick() {
        closeMenuOverlay();
        
        // Create how-to guide HTML content
        const howToGuide = `
            <h3>How to Use Ram Naam Writing App</h3>
            <div class="howto-guide">
                <section class="guide-section">
                    <h4>1. Writing Ram Naam</h4>
                    <p>You can write राम in two ways:</p>
                    <ul>
                        <li><strong>Manual Writing:</strong> Use your finger or stylus to write राम on the canvas.</li>
                        <li><strong>Auto-Draw:</strong> Tap the "DRAW" button to automatically write राम.</li>
                    </ul>
                    <p>After writing, tap "Submit" to add to your count. The "Auto-Draw" option automatically submits after drawing.</p>
                </section>
                
                <section class="guide-section">
                    <h4>2. Tracking Progress</h4>
                    <p>The app tracks:</p>
                    <ul>
                        <li><strong>Today's Count:</strong> Number of राम written today</li>
                        <li><strong>Total Count:</strong> Total राम written since you started</li>
                        <li><strong>Mala Count:</strong> 1 mala = 108 राम, showing your spiritual progress</li>
                        <li><strong>Streak:</strong> Number of consecutive days you've written राम</li>
                    </ul>
                </section>
                
                <section class="guide-section">
                    <h4>3. Profile and Settings</h4>
                    <p>The profile button (top left) provides access to:</p>
                    <ul>
                        <li>Detailed statistics and streaks</li>
                        <li>App preferences like theme and language</li>
                        <li>Daily reminder settings</li>
                    </ul>
                </section>
                
                <section class="guide-section">
                    <h4>4. Menu Features</h4>
                    <p>The menu button (☰) provides access to:</p>
                    <ul>
                        <li>Ram Naam Writing page</li>
                        <li>Share your progress with friends</li>
                        <li>Rate the app</li>
                        <li>Leave feedback</li>
                        <li>Language options</li>
                        <li>Terms and Privacy Policy</li>
                    </ul>
                </section>
                
                <section class="guide-section">
                    <h4>5. Benefits of Ram Naam Writing</h4>
                    <p>Regular writing of राम helps with:</p>
                    <ul>
                        <li>Clearing karmas</li>
                        <li>Releasing suppressed emotions</li>
                        <li>Controlling bad habits</li>
                        <li>Providing spiritual connection and peace</li>
                    </ul>
                </section>
                
                <button id="closeHowTo" class="primary-button">Got It!</button>
            </div>
        `;
        
        // Create a modal for the how-to guide
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content how-to-modal">
                <span class="close">&times;</span>
                ${howToGuide}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // "Got It" button functionality
        const gotItBtn = modal.querySelector('#closeHowTo');
        gotItBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
    }
    
    function handleMenuLanguageClick() {
        closeMenuOverlay();
        
        // Create language selection modal
        const languageOptions = `
            <div class="language-options">
                <h3>Select Language / भाषा चुनें</h3>
                <p>Choose your preferred language for the app interface:</p>
                
                <div class="language-buttons">
                    <button id="langEnglish" class="language-button ${appLanguage === 'en' ? 'active' : ''}">
                        <span class="lang-icon">🇬🇧</span>
                        English
                    </button>
                    <button id="langHindi" class="language-button ${appLanguage === 'hi' ? 'active' : ''}">
                        <span class="lang-icon">🇮🇳</span>
                        हिंदी (Hindi)
                    </button>
                </div>
                
                <p class="language-note">Note: Changing language will refresh the app interface.</p>
            </div>
        `;
        
        // Create a modal for language selection
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${languageOptions}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Language selection functionality
        const englishBtn = modal.querySelector('#langEnglish');
        const hindiBtn = modal.querySelector('#langHindi');
        
        englishBtn.addEventListener('click', () => {
            if (appLanguage !== 'en') {
                appLanguage = 'en';
                saveData();
                applyLanguage();
                modal.style.display = 'none';
                setTimeout(() => {
                    document.body.removeChild(modal);
                }, 300);
            }
        });
        
        hindiBtn.addEventListener('click', () => {
            if (appLanguage !== 'hi') {
                appLanguage = 'hi';
                saveData();
                applyLanguage();
                modal.style.display = 'none';
                setTimeout(() => {
                    document.body.removeChild(modal);
                }, 300);
            }
        });
    }
    
    function applyLanguage() {
        // Use our global translations dictionary
        
        try {
            // Apply translations to elements using our getTranslation helper
            
            // Update header and main page elements
            const appTitleElement = document.querySelector('.header-center h1');
            if (appTitleElement) {
                appTitleElement.textContent = getTranslation('app_title');
            }
            
            // Update stats labels
            const todayCountLabel = document.querySelector('.today-count h3');
            const todayMalaLabel = document.querySelector('.today-mala h3');
            const totalCountLabel = document.querySelector('.total-count h3');
            
            if (todayCountLabel) todayCountLabel.textContent = getTranslation('today_count');
            if (todayMalaLabel) todayMalaLabel.textContent = getTranslation('today_mala');
            if (totalCountLabel) totalCountLabel.textContent = getTranslation('total_count');
            
            // Update stat cards on writing page
            const writingStatLabels = document.querySelectorAll('.stat-card .label');
            if (writingStatLabels && writingStatLabels.length > 0) {
                writingStatLabels.forEach(label => {
                    // Map element text to translation keys
                    if (label.textContent.includes('Current Streak')) {
                        label.textContent = getTranslation('current_streak');
                    } else if (label.textContent.includes('Today\'s Malas')) {
                        label.textContent = getTranslation('today_mala');
                    } else if (label.textContent.includes('Today\'s Count')) {
                        label.textContent = getTranslation('today_count');
                    }
                });
            }
            
            // Update daily goal progress label
            const progressLabel = document.querySelector('.progress-label span:first-child');
            if (progressLabel) {
                progressLabel.textContent = getTranslation('daily_goal');
            }
            
            // Update buttons
            if (clearBtn) clearBtn.textContent = getTranslation('clear');
            if (drawBtn) drawBtn.textContent = getTranslation('auto_draw');
            if (submitBtn) submitBtn.textContent = getTranslation('submit');
            
            // Update menu items
            if (menuWriteLink) menuWriteLink.textContent = getTranslation('ram_writing');
            if (menuShareLink) menuShareLink.textContent = getTranslation('share');
            if (menuRateLink) menuRateLink.textContent = getTranslation('rate_app');
            if (menuFeedbackLink) menuFeedbackLink.textContent = getTranslation('feedback');
            if (menuHowToLink) menuHowToLink.textContent = getTranslation('how_to_use');
            if (menuLanguageLink) menuLanguageLink.textContent = getTranslation('language');
            if (menuThemeLink) menuThemeLink.textContent = getTranslation('theme');
            if (menuTermsLink) menuTermsLink.textContent = getTranslation('terms');
            if (menuPrivacyLink) menuPrivacyLink.textContent = getTranslation('privacy');
            
            // Update profile elements
            if (profileAccountLink) profileAccountLink.textContent = getTranslation('account');
            if (profileReminderLink) profileReminderLink.textContent = getTranslation('daily_reminders');
            if (profileLogoutLink) profileLogoutLink.textContent = getTranslation('logout');
            if (adminButton) adminButton.textContent = getTranslation('admin');
            
            // Update name editing elements
            const editNameBtn = document.getElementById('edit-name-btn');
            const saveNameBtn = document.getElementById('save-name-btn');
            const cancelNameBtn = document.getElementById('cancel-name-btn');
            
            if (editNameBtn) editNameBtn.title = getTranslation('edit_name');
            if (saveNameBtn) saveNameBtn.title = getTranslation('save');
            if (cancelNameBtn) cancelNameBtn.title = getTranslation('cancel');
            
            // Writing button
            if (startWritingBtn) startWritingBtn.textContent = getTranslation('write_ram');
        } catch (e) {
            console.warn('Error applying language changes:', e);
            // Log more detailed error information for debugging
            console.log('Current language:', appLanguage);
            console.log('Available translations:', Object.keys(translations));
        }
        
        // Alert user about the language change
        const message = appLanguage === 'en' ? 
            'Language changed to English!' : 
            'भाषा हिंदी में बदल दी गई है!';
        
        // Show toast notification instead of alert
        showToast(message);
    }
    
    function showToast(message, duration = 3000, type = '') {
        // Create toast element
        const toast = document.createElement('div');
        toast.className = 'toast';
        if (type) {
            toast.classList.add(type);
        }
        toast.textContent = message;
        
        // Add toast to the document
        document.body.appendChild(toast);
        
        // Show the toast
        setTimeout(() => {
            toast.classList.add('show');
        }, 10);
        
        // Hide and remove the toast after the specified duration
        setTimeout(() => {
            toast.classList.remove('show');
            setTimeout(() => {
                document.body.removeChild(toast);
            }, 300);
        }, duration);
    }
    
    // Confetti animation for mala completion
    function showConfetti() {
        const confettiContainer = document.getElementById('confettiContainer');
        confettiContainer.innerHTML = ''; // Clear any existing confetti
        
        // Define confetti colors based on our theme
        const colors = [
            '#FF6F00', // Primary
            '#D84315', // Accent
            '#FFC107', // Progress bar
            '#FFD54F', // Light yellow
            '#FF9800'  // Orange
        ];
        
        // Create confetti pieces
        for (let i = 0; i < 100; i++) {
            const confetti = document.createElement('div');
            confetti.className = 'confetti';
            confetti.style.left = Math.random() * 100 + 'vw';
            confetti.style.animationDuration = (Math.random() * 3 + 2) + 's';
            confetti.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
            
            // Randomize sizes
            const size = Math.random() * 10 + 5;
            confetti.style.width = size + 'px';
            confetti.style.height = size + 'px';
            
            // Add some rotation and delay
            confetti.style.animationDelay = Math.random() * 2 + 's';
            confetti.style.transform = `rotate(${Math.random() * 360}deg)`;
            
            // Add different shapes
            if (Math.random() > 0.6) {
                confetti.style.borderRadius = '50%'; // Circle
            } else if (Math.random() > 0.5) {
                confetti.style.borderRadius = '2px'; // Square
            } else {
                confetti.style.width = '8px';
                confetti.style.height = '12px';
                confetti.style.borderRadius = '0'; // Rectangle
            }
            
            confettiContainer.appendChild(confetti);
        }
        
        // Remove confetti after animation completes
        setTimeout(() => {
            confettiContainer.innerHTML = '';
        }, 5000);
    }
    
    function handleMenuTermsClick() {
        closeMenuOverlay();
        
        // Terms and Conditions content
        const termsContent = `
            <div class="terms-content">
                <h3>Terms and Conditions</h3>
                
                <section>
                    <h4>1. Acceptance of Terms</h4>
                    <p>By accessing or using the Ram Naam Writing app, you agree to be bound by these Terms and Conditions. If you do not agree with any part of these terms, please do not use the application.</p>
                </section>
                
                <section>
                    <h4>2. Use of the Application</h4>
                    <p>The Ram Naam Writing app is designed for spiritual and personal use. You may use this application for personal, non-commercial purposes only. Any use of the app or its content for commercial purposes is strictly prohibited without prior written consent.</p>
                </section>
                
                <section>
                    <h4>3. User Accounts</h4>
                    <p>When you create an account with us, you guarantee that the information you provide is accurate, complete, and current at all times. You are responsible for maintaining the confidentiality of your account and password.</p>
                </section>
                
                <section>
                    <h4>4. Intellectual Property</h4>
                    <p>The app and its original content, features, and functionality are owned by Ram Naam Writing and are protected by international copyright, trademark, and other intellectual property laws.</p>
                </section>
                
                <section>
                    <h4>5. Limitations</h4>
                    <p>In no event shall Ram Naam Writing be liable for any damages arising out of the use or inability to use the application. The app is provided on an "as is" basis without warranties of any kind.</p>
                </section>
                
                <section>
                    <h4>6. Governing Law</h4>
                    <p>These Terms shall be governed by and construed in accordance with the laws of India, without regard to its conflict of law provisions.</p>
                </section>
                
                <section>
                    <h4>7. Changes to Terms</h4>
                    <p>We reserve the right to modify these terms at any time. We will notify users of any changes by updating the date at the top of this page.</p>
                </section>
                
                <button id="acceptTerms" class="primary-button">I Accept</button>
            </div>
        `;
        
        // Create a modal for the terms
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${termsContent}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Accept button functionality
        const acceptBtn = modal.querySelector('#acceptTerms');
        acceptBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
    }
    
    function handleMenuPrivacyClick() {
        closeMenuOverlay();
        
        // Privacy Policy content
        const privacyContent = `
            <div class="privacy-content">
                <h3>Privacy Policy</h3>
                
                <section>
                    <h4>1. Information We Collect</h4>
                    <p>The Ram Naam Writing app collects minimal personal information. We only store the information necessary for the functionality of the app, such as your writing statistics and app preferences. All data is stored locally on your device unless you choose to sync it with our servers.</p>
                </section>
                
                <section>
                    <h4>2. Use of Information</h4>
                    <p>The information we collect is used to provide and improve the service. We use your data to:</p>
                    <ul>
                        <li>Keep track of your writing progress</li>
                        <li>Maintain your user preferences</li>
                        <li>Generate statistics about your usage</li>
                        <li>Improve the app based on anonymous usage data</li>
                    </ul>
                </section>
                
                <section>
                    <h4>3. Data Storage</h4>
                    <p>By default, all data is stored locally on your device. If you opt to create an account, your data will be securely backed up to our servers to enable cross-device synchronization. We employ industry-standard encryption to protect your data during transmission and storage.</p>
                </section>
                
                <section>
                    <h4>4. Data Sharing</h4>
                    <p>We do not sell, trade, or otherwise transfer your personally identifiable information to outside parties. This does not include trusted third parties who assist us in operating our service, so long as those parties agree to keep this information confidential.</p>
                </section>
                
                <section>
                    <h4>5. Analytics</h4>
                    <p>We may use anonymous usage data for analytical purposes to improve the application. This data does not contain personally identifiable information.</p>
                </section>
                
                <section>
                    <h4>6. Your Rights</h4>
                    <p>You have the right to access, update, or delete your personal information. You can do this directly through the app in your account settings, or by contacting us.</p>
                </section>
                
                <section>
                    <h4>7. Policy Changes</h4>
                    <p>We may update this privacy policy from time to time. We will notify you of any changes by posting the new privacy policy on this page and updating the effective date.</p>
                </section>
                
                <button id="acceptPrivacy" class="primary-button">I Understand</button>
            </div>
        `;
        
        // Create a modal for the privacy policy
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${privacyContent}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Accept button functionality
        const acceptBtn = modal.querySelector('#acceptPrivacy');
        acceptBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
    }
    
    // Profile link handlers
    function handleProfileViewClick() {
        closeProfileOverlay();
        navigateToProfile();
    }
    
    function handleProfileAccountClick() {
        closeProfileOverlay();
        navigateToProfile();
        
        // After navigation, show the goals editing modal
        handleEditGoalsClick();
    }
    
    // Removed handleProfileLanguageClick since we now only have language in the main menu
    
    function handleProfileThemeClick() {
        closeProfileOverlay();
        
        // Create theme selection modal with cultural theme palette
        const themeOptions = `
            <div class="theme-options">
                <h3>Select Theme</h3>
                <p>Tap a color to instantly change the theme:</p>
                
                <div id="selectedThemeDisplay" class="selected-theme-display ${currentTheme.startsWith('theme-') ? '' : 'hidden'}">
                    Selected: <span id="currentThemeName">${currentTheme.replace('theme-', '').charAt(0).toUpperCase() + currentTheme.replace('theme-', '').slice(1)}</span>
                </div>
                
                <div class="cultural-theme-palette">
                    <div class="theme-color-option">
                        <button id="themeRam" class="theme-palette-btn theme-ram ${currentTheme === 'theme-ram' ? 'active' : ''}" data-theme="theme-ram" data-name="Ram"></button>
                        <span class="theme-label">Ram</span>
                    </div>
                    <div class="theme-color-option">
                        <button id="themeKrishna" class="theme-palette-btn theme-krishna ${currentTheme === 'theme-krishna' ? 'active' : ''}" data-theme="theme-krishna" data-name="Krishna"></button>
                        <span class="theme-label">Krishna</span>
                    </div>
                    <div class="theme-color-option">
                        <button id="themeLakshmi" class="theme-palette-btn theme-lakshmi ${currentTheme === 'theme-lakshmi' ? 'active' : ''}" data-theme="theme-lakshmi" data-name="Lakshmi"></button>
                        <span class="theme-label">Lakshmi</span>
                    </div>
                    <div class="theme-color-option">
                        <button id="themeGanesh" class="theme-palette-btn theme-ganesh ${currentTheme === 'theme-ganesh' ? 'active' : ''}" data-theme="theme-ganesh" data-name="Ganesh"></button>
                        <span class="theme-label">Ganesh</span>
                    </div>
                    <div class="theme-color-option">
                        <button id="themeShiva" class="theme-palette-btn theme-shiva ${currentTheme === 'theme-shiva' ? 'active' : ''}" data-theme="theme-shiva" data-name="Shiva"></button>
                        <span class="theme-label">Shiva</span>
                    </div>
                </div>
                
                <p>Or choose from standard options:</p>
                
                <div class="theme-buttons">
                    <button id="themeLight" class="theme-button ${currentTheme === 'light' ? 'active' : ''}">
                        <span class="theme-icon">☀️</span>
                        <div class="theme-details">
                            <span class="theme-name">Light Theme</span>
                            <span class="theme-desc">Default light appearance</span>
                        </div>
                    </button>
                    <button id="themeDark" class="theme-button ${currentTheme === 'dark' ? 'active' : ''}">
                        <span class="theme-icon">🌙</span>
                        <div class="theme-details">
                            <span class="theme-name">Dark Theme</span>
                            <span class="theme-desc">Easier on eyes in low light</span>
                        </div>
                    </button>
                    <button id="themeSystem" class="theme-button ${currentTheme === 'system' ? 'active' : ''}">
                        <span class="theme-icon">⚙️</span>
                        <div class="theme-details">
                            <span class="theme-name">System Theme</span>
                            <span class="theme-desc">Follows your device settings</span>
                        </div>
                    </button>
                </div>
            </div>
        `;
        
        // Create a modal for theme selection
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${themeOptions}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Theme selection functionality - Standard themes
        const lightBtn = modal.querySelector('#themeLight');
        const darkBtn = modal.querySelector('#themeDark');
        const systemBtn = modal.querySelector('#themeSystem');
        
        lightBtn.addEventListener('click', () => {
            setTheme('light');
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        darkBtn.addEventListener('click', () => {
            setTheme('dark');
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        systemBtn.addEventListener('click', () => {
            setTheme('system');
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Cultural Theme Selection
        const ramBtn = modal.querySelector('#themeRam');
        const krishnaBtn = modal.querySelector('#themeKrishna');
        const lakshmiBtn = modal.querySelector('#themeLakshmi');
        const ganeshBtn = modal.querySelector('#themeGanesh');
        const shivaBtn = modal.querySelector('#themeShiva');
        
        ramBtn.addEventListener('click', () => {
            setTheme('theme-ram');
            vibrateDevice(50, 'medium'); // Haptic feedback
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        krishnaBtn.addEventListener('click', () => {
            setTheme('theme-krishna');
            vibrateDevice(50, 'medium'); // Haptic feedback
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        lakshmiBtn.addEventListener('click', () => {
            setTheme('theme-lakshmi');
            vibrateDevice(50, 'medium'); // Haptic feedback
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        ganeshBtn.addEventListener('click', () => {
            setTheme('theme-ganesh');
            vibrateDevice(50, 'medium'); // Haptic feedback
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        shivaBtn.addEventListener('click', () => {
            setTheme('theme-shiva');
            vibrateDevice(50, 'medium'); // Haptic feedback
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
    }
    
    function setTheme(theme) {
        console.log('Setting theme to:', theme);
        
        // Store the selected theme
        currentTheme = theme;
        
        if (theme === 'system') {
            // Check system preference
            const prefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
            const effectiveTheme = prefersDark ? 'dark-theme' : 'theme-ram'; // Use dark theme or Ram theme as default light
            applyTheme(effectiveTheme);
            
            // Setup media query listener for system theme changes
            const darkModeMediaQuery = window.matchMedia('(prefers-color-scheme: dark)');
            darkModeMediaQuery.addEventListener('change', (e) => {
                if (currentTheme === 'system') {
                    const newTheme = e.matches ? 'dark-theme' : 'theme-ram';
                    applyTheme(newTheme);
                }
            });
        } else {
            // Make sure theme name is properly formatted
            const effectiveTheme = theme.startsWith('theme-') ? theme : 
                                   (theme === 'dark' ? 'dark-theme' : `theme-${theme}`);
            applyTheme(effectiveTheme);
        }
        
        // Save the preference
        saveData();
        
        // Show feedback with more descriptive names for cultural themes
        let themeName = '';
        
        if (theme === 'light') {
            themeName = 'Light';
        } else if (theme === 'dark' || theme === 'dark-theme') {
            themeName = 'Dark';
        } else if (theme === 'system') {
            themeName = 'System';
        } else if (theme === 'theme-ram' || theme === 'ram') {
            themeName = 'Ram (Saffron)';
        } else if (theme === 'theme-krishna' || theme === 'krishna') {
            themeName = 'Krishna (Blue)';
        } else if (theme === 'theme-lakshmi' || theme === 'lakshmi') {
            themeName = 'Lakshmi (Gold)';
        } else if (theme === 'theme-ganesh' || theme === 'ganesh') {
            themeName = 'Ganesh (Red)';
        } else if (theme === 'theme-shiva' || theme === 'shiva') {
            themeName = 'Shiva (Purple)';
        } else {
            themeName = theme.charAt(0).toUpperCase() + theme.slice(1).replace('-theme', '').replace('theme-', '');
        }
        
        // Update all UI elements that depend on theme
        updateThemeUI(theme);
        
        // Show a toast notification
        showToast(`Theme changed to ${themeName}`);
        
        console.log('Theme set complete:', theme);
    }
    
    // Helper function to update all UI elements that depend on theme
    function updateThemeUI(theme) {
        // Normalize theme name format
        const normalizedTheme = theme.startsWith('theme-') ? theme : 
                              (theme === 'dark' || theme === 'dark-theme') ? 'dark-theme' : 
                              `theme-${theme}`;
                              
        // Update document and body classes directly
        document.documentElement.className = normalizedTheme;
        document.body.className = normalizedTheme;
        
        // Make sure Android app class is preserved if this is running in WebView
        if (window.isRunningInWebView) {
            document.body.classList.add('android-app');
        }
        
        // Set primary colors explicitly
        let primaryColor, backgroundColor, textColor;
        
        switch(normalizedTheme) {
            case 'theme-ram':
                primaryColor = '#ff7817';
                backgroundColor = '#fff7e6';
                textColor = '#333333';
                break;
            case 'theme-krishna':
                primaryColor = '#2874A6';
                backgroundColor = '#EBF5FB';
                textColor = '#1A5276';
                break;
            case 'theme-lakshmi':
                primaryColor = '#D4AC0D';
                backgroundColor = '#FEF9E7';
                textColor = '#7D6608';
                break;
            case 'theme-ganesh':
                primaryColor = '#C0392B';
                backgroundColor = '#FDEDEC';
                textColor = '#641E16';
                break;
            case 'theme-shiva':
                primaryColor = '#7D3C98';
                backgroundColor = '#F4ECF7';
                textColor = '#4A235A';
                break;
            case 'dark-theme':
                primaryColor = '#FF6F00';
                backgroundColor = '#2c2c2c';
                textColor = '#f0f0f0';
                break;
            default:
                // Default to Ram theme if none matches
                primaryColor = '#ff7817';
                backgroundColor = '#fff7e6';
                textColor = '#333333';
        }
        
        // Apply direct styling to important elements
        document.body.style.backgroundColor = backgroundColor;
        document.body.style.color = textColor;
        
        const header = document.querySelector('header');
        if (header) {
            header.style.backgroundColor = primaryColor;
            header.style.color = 'white';
        }
        
        // Update theme display if open
        const themeDisplay = document.getElementById('selectedThemeDisplay');
        const themeNameElement = document.getElementById('currentThemeName');
        
        if (themeDisplay && themeNameElement) {
            if (normalizedTheme.startsWith('theme-')) {
                const displayName = normalizedTheme.replace('theme-', '').charAt(0).toUpperCase() + 
                                  normalizedTheme.replace('theme-', '').slice(1);
                themeNameElement.textContent = displayName;
                themeDisplay.classList.remove('hidden');
            } else {
                themeDisplay.classList.add('hidden');
            }
        }
        
        // Update active buttons
        const buttons = document.querySelectorAll('.theme-palette-btn, .theme-button');
        buttons.forEach(btn => {
            btn.classList.remove('active');
            
            // Handle different button ID formats
            const btnTheme = btn.dataset?.theme || btn.id.toLowerCase().replace('theme', '');
            const normalizedBtnTheme = btnTheme.startsWith('theme-') ? btnTheme : 
                                     (btnTheme === 'dark') ? 'dark-theme' : 
                                     `theme-${btnTheme}`;
                                     
            if (normalizedBtnTheme === normalizedTheme) {
                btn.classList.add('active');
            }
        });
        
        // Force a refresh on the UI
        document.documentElement.style.display = 'none';
        document.documentElement.offsetHeight; // Trigger reflow
        document.documentElement.style.display = '';
    }
    
    function handleProfileReminderClick() {
        closeProfileOverlay();
        navigateToProfile(); // Stay on profile page when setting reminders
        
        // Create reminder settings modal
        const reminderOptions = `
            <div class="reminder-options">
                <h3>Daily Reminder Settings</h3>
                <p>Get notified daily to write Ram Naam and keep your streak going!</p>
                
                <div class="reminder-toggle-section">
                    <label class="toggle-container">
                        <span class="toggle-label">Enable Daily Reminder</span>
                        <div class="toggle-switch-large">
                            <input type="checkbox" id="reminderEnable" ${reminderEnabled ? 'checked' : ''}>
                            <span class="toggle-slider-large"></span>
                        </div>
                    </label>
                </div>
                
                <div class="reminder-time-section ${!reminderEnabled ? 'disabled' : ''}">
                    <div class="time-selection">
                        <label for="reminderTime">Reminder Time:</label>
                        <input type="time" id="reminderTime" value="${reminderTime}" ${!reminderEnabled ? 'disabled' : ''}>
                        
                        <div class="time-presets">
                            <button class="time-preset-btn" data-time="07:00" ${!reminderEnabled ? 'disabled' : ''}>Morning (7 AM)</button>
                            <button class="time-preset-btn" data-time="12:00" ${!reminderEnabled ? 'disabled' : ''}>Noon (12 PM)</button>
                            <button class="time-preset-btn" data-time="18:00" ${!reminderEnabled ? 'disabled' : ''}>Evening (6 PM)</button>
                        </div>
                    </div>
                    
                    <div class="reminder-days">
                        <p>Reminder Days:</p>
                        <div class="day-presets">
                            <button class="day-preset-btn" data-preset="weekdays" ${!reminderEnabled ? 'disabled' : ''}>Weekdays</button>
                            <button class="day-preset-btn" data-preset="weekends" ${!reminderEnabled ? 'disabled' : ''}>Weekends</button>
                            <button class="day-preset-btn" data-preset="everyday" ${!reminderEnabled ? 'disabled' : ''}>Every Day</button>
                        </div>
                        <div class="day-buttons">
                            <button class="day-button" data-day="sun" ${!reminderEnabled ? 'disabled' : ''}>S</button>
                            <button class="day-button" data-day="mon" ${!reminderEnabled ? 'disabled' : ''}>M</button>
                            <button class="day-button" data-day="tue" ${!reminderEnabled ? 'disabled' : ''}>T</button>
                            <button class="day-button" data-day="wed" ${!reminderEnabled ? 'disabled' : ''}>W</button>
                            <button class="day-button" data-day="thu" ${!reminderEnabled ? 'disabled' : ''}>T</button>
                            <button class="day-button" data-day="fri" ${!reminderEnabled ? 'disabled' : ''}>F</button>
                            <button class="day-button" data-day="sat" ${!reminderEnabled ? 'disabled' : ''}>S</button>
                        </div>
                    </div>
                </div>
                
                <button id="saveReminder" class="primary-button">Save Settings</button>
            </div>
        `;
        
        // Create a modal for the reminder settings
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${reminderOptions}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Toggle reminder enable/disable
        const reminderEnableToggle = modal.querySelector('#reminderEnable');
        const reminderTimeSection = modal.querySelector('.reminder-time-section');
        const reminderTimeInput = modal.querySelector('#reminderTime');
        const dayButtons = modal.querySelectorAll('.day-button');
        const timePresetButtons = modal.querySelectorAll('.time-preset-btn');
        const dayPresetButtons = modal.querySelectorAll('.day-preset-btn');
        
        reminderEnableToggle.addEventListener('change', () => {
            const isEnabled = reminderEnableToggle.checked;
            
            if (isEnabled) {
                reminderTimeSection.classList.remove('disabled');
                reminderTimeInput.disabled = false;
                dayButtons.forEach(btn => btn.disabled = false);
                timePresetButtons.forEach(btn => btn.disabled = false);
                dayPresetButtons.forEach(btn => btn.disabled = false);
            } else {
                reminderTimeSection.classList.add('disabled');
                reminderTimeInput.disabled = true;
                dayButtons.forEach(btn => btn.disabled = true);
                timePresetButtons.forEach(btn => btn.disabled = true);
                dayPresetButtons.forEach(btn => btn.disabled = true);
            }
        });
        
        // Time preset buttons functionality
        timePresetButtons.forEach(btn => {
            btn.addEventListener('click', () => {
                // Update time input with preset value
                reminderTimeInput.value = btn.dataset.time;
                
                // Highlight active preset
                timePresetButtons.forEach(b => b.classList.remove('active'));
                btn.classList.add('active');
            });
            
            // Set initial active state based on current time
            if (reminderTime === btn.dataset.time) {
                btn.classList.add('active');
            }
        });
        
        // Day preset buttons functionality
        dayPresetButtons.forEach(btn => {
            btn.addEventListener('click', () => {
                const preset = btn.dataset.preset;
                
                // Clear current selections
                dayButtons.forEach(b => b.classList.remove('active'));
                
                // Select days based on preset
                if (preset === 'weekdays') {
                    dayButtons.forEach(b => {
                        if (['mon', 'tue', 'wed', 'thu', 'fri'].includes(b.dataset.day)) {
                            b.classList.add('active');
                        }
                    });
                } else if (preset === 'weekends') {
                    dayButtons.forEach(b => {
                        if (['sat', 'sun'].includes(b.dataset.day)) {
                            b.classList.add('active');
                        }
                    });
                } else if (preset === 'everyday') {
                    dayButtons.forEach(b => b.classList.add('active'));
                }
                
                // Highlight active preset
                dayPresetButtons.forEach(b => b.classList.remove('active'));
                btn.classList.add('active');
            });
            
            // Set initial active state based on current days
            const weekdays = ['mon', 'tue', 'wed', 'thu', 'fri'];
            const weekends = ['sat', 'sun'];
            const everyday = [...weekdays, ...weekends];
            
            if (btn.dataset.preset === 'weekdays' && 
                weekdays.every(day => reminderDays.includes(day)) && 
                reminderDays.length === weekdays.length) {
                btn.classList.add('active');
            } else if (btn.dataset.preset === 'weekends' && 
                       weekends.every(day => reminderDays.includes(day)) && 
                       reminderDays.length === weekends.length) {
                btn.classList.add('active');
            } else if (btn.dataset.preset === 'everyday' && 
                       everyday.every(day => reminderDays.includes(day)) && 
                       reminderDays.length === everyday.length) {
                btn.classList.add('active');
            }
        });
        
        // Day button functionality
        dayButtons.forEach(btn => {
            btn.addEventListener('click', () => {
                btn.classList.toggle('active');
                
                // Remove highlight from presets when individual days are selected
                dayPresetButtons.forEach(b => b.classList.remove('active'));
                
                // Check if current selection matches any preset
                const activeDays = Array.from(modal.querySelectorAll('.day-button.active'))
                    .map(b => b.dataset.day);
                    
                const weekdays = ['mon', 'tue', 'wed', 'thu', 'fri'];
                const weekends = ['sat', 'sun'];
                const everyday = [...weekdays, ...weekends];
                
                dayPresetButtons.forEach(b => {
                    if (b.dataset.preset === 'weekdays' && 
                        weekdays.every(day => activeDays.includes(day)) && 
                        activeDays.length === weekdays.length) {
                        b.classList.add('active');
                    } else if (b.dataset.preset === 'weekends' && 
                              weekends.every(day => activeDays.includes(day)) && 
                              activeDays.length === weekends.length) {
                        b.classList.add('active');
                    } else if (b.dataset.preset === 'everyday' && 
                              everyday.every(day => activeDays.includes(day)) && 
                              activeDays.length === everyday.length) {
                        b.classList.add('active');
                    }
                });
            });
        });
        
        // Save button functionality
        const saveBtn = modal.querySelector('#saveReminder');
        saveBtn.addEventListener('click', () => {
            // Get settings values
            reminderEnabled = reminderEnableToggle.checked;
            reminderTime = reminderTimeInput.value;
            
            // Get selected days
            reminderDays = [];
            modal.querySelectorAll('.day-button.active').forEach(btn => {
                reminderDays.push(btn.dataset.day);
            });
            
            // Save settings
            saveReminderSettings();
            
            // Provide feedback and close modal
            showToast('Reminder settings saved');
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
    }
    
    function saveReminderSettings() {
        // In a real app, this would request notification permission if needed
        // and schedule the notifications based on the settings
        const settings = {
            enabled: reminderEnabled,
            time: reminderTime,
            days: reminderDays
        };
        
        localStorage.setItem('ramNaamReminders', JSON.stringify(settings));
    }
    
    function handleEditGoalsClick() {
        // Create goals editing modal
        const goalsEditForm = `
            <div class="goals-edit-form">
                <h3>Set Your Writing Goals</h3>
                <p>Customize your daily and monthly goals to track your progress:</p>
                
                <div class="goals-form-section">
                    <div class="form-group">
                        <label for="dailyGoalInput">Daily Goal (RAM count):</label>
                        <div class="input-with-presets">
                            <input type="number" id="dailyGoalInput" min="1" max="10000" value="${dailyGoal}">
                            <div class="preset-buttons">
                                <button class="preset-btn" data-value="108">1 mala (108)</button>
                                <button class="preset-btn" data-value="216">2 malas (216)</button>
                                <button class="preset-btn" data-value="1080">10 malas (1080)</button>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="monthlyGoalInput">Monthly Goal (RAM count):</label>
                        <div class="input-with-presets">
                            <input type="number" id="monthlyGoalInput" min="1" max="100000" value="${monthlyGoal}">
                            <div class="preset-buttons">
                                <button class="preset-btn" data-value="3240">30 malas (3240)</button>
                                <button class="preset-btn" data-value="10800">100 malas (10800)</button>
                                <button class="preset-btn" data-value="21600">200 malas (21600)</button>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="form-info">
                    <p><strong>Note:</strong> 1 mala = 108 repetitions of RAM</p>
                    <p>Setting realistic goals helps maintain consistency and build a strong practice.</p>
                </div>
                
                <button id="saveGoalsBtn" class="primary-button">Save Goals</button>
            </div>
        `;
        
        // Create a modal for the goals edit form
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <span class="close">&times;</span>
                ${goalsEditForm}
            </div>
        `;
        
        document.body.appendChild(modal);
        
        // Show the modal
        setTimeout(() => {
            modal.style.display = 'flex';
        }, 10);
        
        // Close button functionality
        const closeBtn = modal.querySelector('.close');
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
        });
        
        // Preset buttons functionality for daily goal
        const dailyPresetBtns = modal.querySelectorAll('.form-group:nth-child(1) .preset-btn');
        const dailyGoalInput = modal.querySelector('#dailyGoalInput');
        
        dailyPresetBtns.forEach(btn => {
            btn.addEventListener('click', () => {
                dailyGoalInput.value = btn.dataset.value;
                
                // Visual feedback
                dailyPresetBtns.forEach(b => b.classList.remove('active'));
                btn.classList.add('active');
            });
            
            // Set initial active state
            if (parseInt(btn.dataset.value) === dailyGoal) {
                btn.classList.add('active');
            }
        });
        
        // Preset buttons functionality for monthly goal
        const monthlyPresetBtns = modal.querySelectorAll('.form-group:nth-child(2) .preset-btn');
        const monthlyGoalInput = modal.querySelector('#monthlyGoalInput');
        
        monthlyPresetBtns.forEach(btn => {
            btn.addEventListener('click', () => {
                monthlyGoalInput.value = btn.dataset.value;
                
                // Visual feedback
                monthlyPresetBtns.forEach(b => b.classList.remove('active'));
                btn.classList.add('active');
            });
            
            // Set initial active state
            if (parseInt(btn.dataset.value) === monthlyGoal) {
                btn.classList.add('active');
            }
        });
        
        // Save button functionality
        const saveBtn = modal.querySelector('#saveGoalsBtn');
        saveBtn.addEventListener('click', () => {
            // Get and validate goal values
            const newDailyGoal = parseInt(dailyGoalInput.value);
            const newMonthlyGoal = parseInt(monthlyGoalInput.value);
            
            if (isNaN(newDailyGoal) || newDailyGoal <= 0) {
                showToast('Please enter a valid daily goal');
                return;
            }
            
            if (isNaN(newMonthlyGoal) || newMonthlyGoal <= 0) {
                showToast('Please enter a valid monthly goal');
                return;
            }
            
            // Update goals
            dailyGoal = newDailyGoal;
            monthlyGoal = newMonthlyGoal;
            
            // Save to storage
            saveData();
            
            // Update UI displays
            updateProfileStats();
            
            // Provide feedback and close modal
            showToast('Your goals have been updated');
            modal.style.display = 'none';
            setTimeout(() => {
                document.body.removeChild(modal);
            }, 300);
            
            // Vibrate device if supported
            try {
                if (window.AndroidInterface && typeof window.AndroidInterface.vibrate === 'function') {
                    window.AndroidInterface.vibrate(200, 50); // Longer vibration for confirmation
                }
            } catch (e) {
                console.warn("Could not vibrate device:", e);
            }
        });
    }
    
    function handleProfileAdminClick() {
        closeProfileOverlay();
        navigateToAdmin();
    }
    
    function handleProfileLogoutClick() {
        closeProfileOverlay();
        handleLogout();
    }
    
    function handleLogout() {
        // For now, just show an alert and go to home
        alert('Logout functionality would be implemented here');
        navigateToHome();
    }
    
    function handleLanguageChange() {
        // Save the language preference
        saveData();
        // For now, just show an alert
        alert(`Language changed to ${languageSelect.options[languageSelect.selectedIndex].text}`);
    }
    
    function handleThemeChange() {
        const theme = themeSelect.value;
        applyTheme(theme);
        saveData();
    }
    
    function applyTheme(theme) {
        console.log('Applying theme:', theme);
        
        // Normalize theme name for consistency
        const normalizedTheme = theme.startsWith('theme-') ? theme : 
                              (theme === 'dark' || theme === 'dark-theme') ? 'dark-theme' : 
                              `theme-${theme}`;
        
        // Theme color mapping with accent colors for headings
        const themeColors = {
            'theme-ram': {
                primary: '#ff7817',
                background: '#fff7e6', 
                text: '#333333',
                accent: '#ff7817' // Accent color for headings and stats
            },
            'theme-krishna': {
                primary: '#2874A6',
                background: '#EBF5FB',
                text: '#1A5276',
                accent: '#2874A6' // Blue for Krishna theme
            },
            'theme-lakshmi': {
                primary: '#D4AC0D',
                background: '#FEF9E7',
                text: '#7D6608',
                accent: '#D4AC0D' // Gold for Lakshmi theme
            },
            'theme-ganesh': {
                primary: '#C0392B',
                background: '#FDEDEC',
                text: '#641E16',
                accent: '#C0392B' // Red for Ganesh theme
            },
            'theme-shiva': {
                primary: '#7D3C98',
                background: '#F4ECF7',
                text: '#4A235A',
                accent: '#7D3C98' // Purple for Shiva theme
            },
            'dark-theme': {
                primary: '#FF6F00',
                background: '#2c2c2c',
                text: '#f0f0f0',
                accent: '#FF6F00' // Keep orange accent in dark theme
            }
        };
        
        // Make sure we have colors for this theme
        const colors = themeColors[normalizedTheme] || themeColors['theme-ram'];
        
        // 1. Clean up all theme classes first
        const allThemeClasses = [
            'dark-theme',
            'theme-ram',
            'theme-krishna',
            'theme-lakshmi', 
            'theme-ganesh',
            'theme-shiva'
        ];
        
        // Remove all theme classes from both documentElement and body
        document.documentElement.classList.remove(...allThemeClasses);
        document.body.classList.remove(...allThemeClasses);
        
        // 2. Set the new theme class
        document.documentElement.className = normalizedTheme;
        document.body.className = normalizedTheme;
        
        // 3. Apply direct styling for immediate visual feedback
        document.documentElement.style.setProperty('--primary-color', colors.primary);
        document.documentElement.style.setProperty('--background-color', colors.background);
        document.documentElement.style.setProperty('--text-color', colors.text);
        document.documentElement.style.setProperty('--accent-color', colors.accent);
        
        // Apply to body directly as well
        document.body.style.backgroundColor = colors.background;
        document.body.style.color = colors.text;
        
        // Apply accent color to headings and stats
        const headings = document.querySelectorAll('.card-title, .section-title, .stat-label, h2, h3, .stat-title, .card-header');
        headings.forEach(heading => {
            heading.style.color = colors.accent;
        });
        
        // Apply to stat values and other important numbers
        const statValues = document.querySelectorAll('.stat-value, .profile-stat-value, .count-value, .mala-count, .streak-value');
        statValues.forEach(value => {
            value.style.color = colors.accent;
        });
        
        // Apply accent colors to specific text elements but exclude elements in the header
        const accentTextElements = document.querySelectorAll('.profile-section-title, .total-count, .current-streak, .main-content h1, .main-content h2, .main-content h3, .main-content h4, .profile-section h3');
        accentTextElements.forEach(element => {
            element.style.color = colors.accent;
        });
        
        // Update progress bars - make them more visible
        const progressBars = document.querySelectorAll('.progress-bar');
        progressBars.forEach(bar => {
            bar.style.backgroundColor = "#e0e0e0";
        });
        
        // Make progress fill more visible
        const progressFills = document.querySelectorAll('.progress-fill');
        progressFills.forEach(fill => {
            fill.style.backgroundColor = colors.accent;
        });
        
        // Update buttons with accent color and white text (orange buttons with white text)
        const accentButtons = document.querySelectorAll('.primary-button, .action-button, .secondary-button');
        accentButtons.forEach(button => {
            button.style.backgroundColor = colors.accent;
            button.style.borderColor = colors.accent;
            button.style.color = 'white';
            
            // Add hover effect
            button.onmouseover = () => {
                button.style.backgroundColor = colors.accent;
                button.style.opacity = '0.9';
                button.style.color = 'white';
            };
            
            button.onmouseout = () => {
                button.style.backgroundColor = colors.accent;
                button.style.opacity = '1';
                button.style.color = 'white';
            };
        });
        
        // Apply to header for immediate visibility
        const header = document.querySelector('header');
        if (header) {
            header.style.backgroundColor = colors.primary;
            header.style.color = 'white'; // Consistent header text color
            
            // Explicitly set all header text elements to white
            const headerTextElements = header.querySelectorAll('h1, h2, h3, h4, p, span, div, a');
            headerTextElements.forEach(el => {
                el.style.color = 'white';
            });
        }
        
        // Also explicitly handle texts that are directly in header background areas
        const headerTexts = document.querySelectorAll('.header-text, .app-title, .greeting-text, .welcome-text, .main-header-text, .header-area h1, .header-area h2, .header-area h3');
        headerTexts.forEach(text => {
            text.style.color = 'white';
        });
        
        // Specifically target the welcome greeting text that appears on blue background
        const greetingTexts = document.querySelectorAll('.greeting-message, .benefits-title');
        greetingTexts.forEach(greeting => {
            greeting.style.color = 'white';
        });
        
        // Make sure Android app class is preserved if running in WebView
        if (window.isRunningInWebView || window.AndroidInterface) {
            document.body.classList.add('android-app');
        }
        
        // Update theme selection indicators
        const allButtons = document.querySelectorAll('.theme-palette-btn, .theme-button');
        allButtons.forEach(btn => {
            btn.classList.remove('active');
            
            // Get theme value from either dataset or ID
            const btnTheme = btn.dataset?.theme || btn.id.toLowerCase().replace('theme', '');
            
            // Normalize button theme same as we did for selected theme
            const normalizedBtnTheme = btnTheme.startsWith('theme-') ? btnTheme : 
                                     (btnTheme === 'dark') ? 'dark-theme' : 
                                     `theme-${btnTheme}`;
            
            if (normalizedBtnTheme === normalizedTheme) {
                btn.classList.add('active');
            }
        });
        
        // Update theme display name
        const themeDisplay = document.getElementById('selectedThemeDisplay');
        const themeNameElement = document.getElementById('currentThemeName');
        
        if (themeDisplay && themeNameElement) {
            if (normalizedTheme.startsWith('theme-')) {
                // Format display name: theme-krishna -> Krishna
                const displayName = normalizedTheme.replace('theme-', '').charAt(0).toUpperCase() + 
                                 normalizedTheme.replace('theme-', '').slice(1);
                themeNameElement.textContent = displayName;
                themeDisplay.classList.remove('hidden');
            } else if (normalizedTheme === 'dark-theme') {
                themeNameElement.textContent = 'Dark';
                themeDisplay.classList.remove('hidden');
            } else {
                themeDisplay.classList.add('hidden');
            }
        }
        
        // Update Android status bar color if available
        if (window.AndroidInterface) {
            try {
                window.AndroidInterface.updateStatusBarColor(colors.primary);
            } catch (e) {
                console.error('Error updating Android status bar color:', e);
            }
        }
        
        console.log('Theme application complete:', normalizedTheme);
    }
    
    function saveAppSettings() {
        // Update MALA_COUNT and other settings
        // Note: In a real app, we'd need to update the global constant and reload data
        alert('Settings saved successfully');
    }
    
    function exportData() {
        const exportData = {
            totalCount: totalCount,
            todayCount: todayCount,
            malaCount: Math.floor(totalCount / MALA_COUNT),
            streak: currentStreak,
            longestStreak: longestStreak,
            exportDate: new Date().toISOString()
        };
        
        // Create a blob and download it
        const dataStr = JSON.stringify(exportData, null, 2);
        const blob = new Blob([dataStr], { type: 'application/json' });
        const url = URL.createObjectURL(blob);
        
        const a = document.createElement('a');
        a.href = url;
        a.download = 'ram_naam_data.json';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
    }
    
    function backupData() {
        // For now, just show an alert
        alert('Database backup functionality would be implemented here');
    }
    
    function startDrawing(e) {
        isDrawing = true;
        [lastX, lastY] = [e.offsetX, e.offsetY];
    }
    
    function startDrawingTouch(e) {
        e.preventDefault();
        isDrawing = true;
        const rect = canvas.getBoundingClientRect();
        const touch = e.touches[0];
        [lastX, lastY] = [touch.clientX - rect.left, touch.clientY - rect.top];
    }
    
    function draw(e) {
        if (!isDrawing) return;
        
        drawingContext.beginPath();
        drawingContext.moveTo(lastX, lastY);
        drawingContext.lineTo(e.offsetX, e.offsetY);
        drawingContext.stroke();
        
        [lastX, lastY] = [e.offsetX, e.offsetY];
        
        // Add haptic feedback for drawing precision
        providePrecisionFeedback(e.offsetX, e.offsetY);
    }
    
    function drawTouch(e) {
        if (!isDrawing) return;
        e.preventDefault();
        
        const rect = canvas.getBoundingClientRect();
        const touch = e.touches[0];
        const offsetX = touch.clientX - rect.left;
        const offsetY = touch.clientY - rect.top;
        
        drawingContext.beginPath();
        drawingContext.moveTo(lastX, lastY);
        drawingContext.lineTo(offsetX, offsetY);
        drawingContext.stroke();
        
        [lastX, lastY] = [offsetX, offsetY];
        
        // Add haptic feedback for touch drawing precision
        providePrecisionFeedback(offsetX, offsetY);
    }
    
    function stopDrawing() {
        isDrawing = false;
    }
    
    // Haptic feedback functions for drawing precision
    function providePrecisionFeedback(x, y) {
        // Get canvas dimensions
        const canvasWidth = canvas.width;
        const canvasHeight = canvas.height;
        
        // Define the ideal Ram character area (center of canvas)
        const idealCenterX = canvasWidth / 2;
        const idealCenterY = canvasHeight / 2;
        
        // Calculate distance from ideal center
        const distanceFromIdeal = Math.sqrt(
            Math.pow(x - idealCenterX, 2) + 
            Math.pow(y - idealCenterY, 2)
        );
        
        // Normalize the distance to a percentage of canvas size
        const normalizedDistance = distanceFromIdeal / (Math.min(canvasWidth, canvasHeight) / 2);
        
        // Provide haptic feedback based on precision
        if (normalizedDistance < 0.2) {
            // Very precise - strong vibration
            vibrateDevice(100, 'high');
        } else if (normalizedDistance < 0.4) {
            // Moderately precise - medium vibration
            vibrateDevice(50, 'medium');
        } else if (normalizedDistance < 0.6) {
            // Less precise - light vibration
            vibrateDevice(20, 'low');
        }
        // No vibration for very imprecise drawing (> 0.6)
    }
    
    function vibrateDevice(duration, intensity) {
        // Check if in Android WebView with our interface
        if (window.AndroidInterface && typeof window.AndroidInterface.vibrate === 'function') {
            try {
                // Use our enhanced Android vibration API with intensity
                window.AndroidInterface.vibrate(duration, intensity);
                return; // Exit early if Android vibration worked
            } catch (e) {
                console.warn("Error using Android vibration:", e);
                // Fall back to web vibration
            }
        }
        
        // Fallback for web browsers: Check if vibration API is available
        if ('vibrate' in navigator) {
            switch(intensity) {
                case 'high':
                    navigator.vibrate([duration, 50, duration]);
                    break;
                case 'medium':
                    navigator.vibrate(duration);
                    break;
                case 'low':
                    navigator.vibrate([duration/2, 20, duration/2]);
                    break;
                default:
                    navigator.vibrate(duration);
            }
        }
    }
    
    function clearCanvas() {
        drawingContext.clearRect(0, 0, canvas.width, canvas.height);
    }
    
    function autoDraw() {
        // Clear the canvas first
        clearCanvas();
        
        // Get current theme accent color
        let accentColor = getComputedStyle(document.documentElement).getPropertyValue('--accent-color').trim() || "#ff7817";
        let strokeColor = accentColor;
        
        // Set drawing properties for the RAM text
        drawingContext.font = "bold 48px Devanagari, Arial, sans-serif";
        drawingContext.fillStyle = accentColor;
        drawingContext.strokeStyle = strokeColor;
        drawingContext.lineWidth = 1;
        
        // Center position for the text
        const x = canvas.width / 2 - 50;
        const y = canvas.height / 2 + 15;
        
        // Text to draw
        const ramText = "राम";
        
        // Draw the text with animation
        let charIndex = 0;
        let timer = null;
        
        function drawNextChar() {
            if (charIndex > ramText.length) {
                clearTimeout(timer);
                setTimeout(submitDrawing, 500);
                return;
            }
            
            // Clear canvas and redraw the text up to current character
            clearCanvas();
            const partialText = ramText.substring(0, charIndex);
            drawingContext.fillText(partialText, x, y);
            drawingContext.strokeText(partialText, x, y);
            
            charIndex++;
            
            // Continue animation with slight delay
            timer = setTimeout(drawNextChar, 400);
        }
        
        // Start the animated drawing
        drawNextChar();
    }
    
    function submitDrawing() {
        // Store previous mala count to check if a mala was completed
        const prevMalaCount = Math.floor(todayCount / MALA_COUNT);
        
        // Increment counts
        todayCount++;
        totalCount++;
        currentMonthCount++; // Also increment the current month count
        
        // Calculate new mala count (1 mala = 108 rams)
        todayMalaCount = Math.floor(todayCount / MALA_COUNT);
        
        // Check if a mala was just completed
        if (todayMalaCount > prevMalaCount) {
            // Show confetti animation
            showConfetti();
            // Show toast notification
            showToast(`Congratulations! You completed ${todayMalaCount} mala${todayMalaCount > 1 ? 's' : ''}! 🎉`, 4000, 'success');
            
            // Add haptic feedback if available
            if (navigator.vibrate) {
                navigator.vibrate([100, 50, 100, 50, 200]);
            }
        }
        
        // Clear the canvas for next drawing
        clearCanvas();
        
        // Update streak info (if this is first submission today)
        if (todayCount === 1) {
            currentStreak++;
            longestStreak = Math.max(currentStreak, longestStreak);
        }
        
        // Update the UI
        updateCountDisplay();
        updateProfileStats(); // Also update the profile stats to reflect latest count
        
        // Save data
        saveData();
    }
    
    function shareStats() {
        // Prepare sharing text
        const shareText = `I've written RAM naam ${totalCount} times (${Math.floor(totalCount / MALA_COUNT)} malas) with my current streak of ${currentStreak} days!`;
        
        // Check if Web Share API is available
        if (navigator.share) {
            navigator.share({
                title: 'RAM Naam Writing Stats',
                text: shareText,
                // url: window.location.href
            }).catch(() => {
                // Fallback
                alert(shareText);
            });
        } else {
            // Fallback for browsers that don't support sharing
            alert(shareText);
        }
    }
    
    function shareViaWhatsApp() {
        // Get today's date formatted for better reading
        const today = new Date();
        const dateFormatted = today.toLocaleDateString();
        
        // Create a motivational message based on streak and progress
        let motivationalMessage = "";
        if (currentStreak > 7) {
            motivationalMessage = "🔥 Amazing consistency! Keep the spiritual journey going.";
        } else if (currentStreak > 3) {
            motivationalMessage = "🌟 Great progress! Your dedication is inspiring.";
        } else if (currentStreak > 0) {
            motivationalMessage = "✨ Every day of practice matters. Keep going!";
        } else {
            motivationalMessage = "💫 Today is a perfect day to continue your spiritual journey.";
        }
        
        // Calculate daily goal progress
        const dailyGoalPercentage = Math.round((todayCount / dailyGoal) * 100);
        
        // Format the WhatsApp message with today's stats and motivation
        const shareText = `🕉️ *Ram Naam Writing - Today's Progress* 🕉️\n\n`
            + `Date: ${dateFormatted}\n`
            + `Today's Count: ${todayCount} राम\n`
            + `Today's Malas: ${todayMalaCount}\n`
            + `Daily Goal: ${dailyGoalPercentage}% completed\n`
            + `Current Streak: ${currentStreak} days\n\n`
            + `${motivationalMessage}\n\n`
            + `🙏 Ram Ram 🙏`;
        
        // Try to use the native sharing if available
        try {
            // Check if we're in the Android app with custom interface
            if (window.AndroidInterface && typeof window.AndroidInterface.shareToWhatsApp === 'function') {
                window.AndroidInterface.shareToWhatsApp(shareText);
                
                // Provide haptic feedback if available
                if (typeof window.AndroidInterface.vibrate === 'function') {
                    window.AndroidInterface.vibrate(20);
                }
                
                showToast("Sharing via WhatsApp...");
                return;
            }
        } catch (e) {
            console.warn("Error using Android interface for sharing:", e);
        }
        
        // Fallback to standard web approach
        const encodedText = encodeURIComponent(shareText);
        const whatsappUrl = `https://wa.me/?text=${encodedText}`;
        
        // Open WhatsApp in a new tab
        window.open(whatsappUrl, '_blank');
        
        // Show a confirmation toast
        showToast("Opening WhatsApp...");
        
        // Vibrate device if possible for feedback
        if ('vibrate' in navigator) {
            navigator.vibrate(20);
        }
    }
    
    function downloadStats() {
        // For now just use exportData function
        exportData();
    }
    
    // Handle window resize to adjust canvas
    window.addEventListener('resize', setupCanvas);
});