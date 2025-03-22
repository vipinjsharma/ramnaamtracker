document.addEventListener('DOMContentLoaded', initializeApp);

// Global variables
let isDrawing = false;
let context;
let canvas;
let currentTheme = 'light';
let currentLanguage = 'en';
let autoMode = false;
let isAndroid = false;

// Data structure for storing counts
let data = {
    totalCount: 0,
    dailyCounts: {},
    todayCount: 0,
    streak: 0,
    lastDate: '',
    malaCount: 0, // 1 mala = 108 repetitions
    stars: 0,
};

// Text resources for multiple languages
const textResources = {
    'en': {
        'app_title': 'Ram Lekhak',
        'write_button': 'Write',
        'home_title': 'Ram Naam Writing',
        'total_count': 'Total Count',
        'today_count': 'Today\'s Count',
        'current_streak': 'Current Streak',
        'mala_count': 'Mala Count',
        'days': 'days',
        'clear': 'Clear',
        'auto': 'Auto',
        'submit': 'Submit',
        'profile_stats': 'Your Stats',
        'write_goal': 'Writing Goal',
        'account': 'Account',
        'theme': 'Theme',
        'reminder': 'Daily Reminder',
        'admin': 'Admin Panel',
        'logout': 'Logout',
        'menu_write': 'Ram Naam Writing',
        'menu_share': 'Share',
        'menu_rate': 'Rate App',
        'menu_feedback': 'Feedback',
        'menu_howto': 'How to Use',
        'menu_language': 'Language',
        'menu_terms': 'Terms of Service',
        'menu_privacy': 'Privacy Policy',
        'light_theme': 'Light Theme',
        'dark_theme': 'Dark Theme',
        'auto_theme': 'Auto Theme',
        'save': 'Save',
        'remind_time': 'Reminder Time',
        'remind_days': 'Reminder Days',
        'monday': 'Monday',
        'tuesday': 'Tuesday',
        'wednesday': 'Wednesday',
        'thursday': 'Thursday',
        'friday': 'Friday',
        'saturday': 'Saturday',
        'sunday': 'Sunday',
        'export': 'Export Data',
        'backup': 'Backup Data',
        'admin_panel': 'Admin Panel',
        'success_save': 'Successfully saved!',
        'success_submit': 'Ram Naam submitted!',
        'success_clear': 'Canvas cleared!',
        'success_auto': 'Auto mode started!',
        'success_export': 'Data exported!',
        'success_backup': 'Data backed up!',
        'success_reminder': 'Reminder settings saved!',
        'ram_stats': 'Ram Naam Stats',
        'download': 'Download',
        'close': 'Close',
        'daily_stats': 'Daily Stats',
        'monthly_stats': 'Monthly Stats',
        'yearly_stats': 'Yearly Stats',
        'rate_experience': 'Rate your experience',
        'send_feedback': 'Send Feedback',
        'back': 'Back',
        'settings': 'Settings'
    },
    'hi': {
        'app_title': 'राम लेखक',
        'write_button': 'लिखें',
        'home_title': 'राम नाम लेखन',
        'total_count': 'कुल गिनती',
        'today_count': 'आज की गिनती',
        'current_streak': 'वर्तमान स्ट्रीक',
        'mala_count': 'माला गिनती',
        'days': 'दिन',
        'clear': 'साफ करें',
        'auto': 'स्वतः',
        'submit': 'जमा करें',
        'profile_stats': 'आपके आंकड़े',
        'write_goal': 'लेखन लक्ष्य',
        'account': 'खाता',
        'theme': 'थीम',
        'reminder': 'दैनिक अनुस्मारक',
        'admin': 'व्यवस्थापक पैनल',
        'logout': 'लॉग आउट',
        'menu_write': 'राम नाम लेखन',
        'menu_share': 'साझा करें',
        'menu_rate': 'ऐप रेट करें',
        'menu_feedback': 'प्रतिक्रिया',
        'menu_howto': 'उपयोग कैसे करें',
        'menu_language': 'भाषा',
        'menu_terms': 'सेवा की शर्तें',
        'menu_privacy': 'गोपनीयता नीति',
        'light_theme': 'लाइट थीम',
        'dark_theme': 'डार्क थीम',
        'auto_theme': 'ऑटो थीम',
        'save': 'सहेजें',
        'remind_time': 'अनुस्मारक समय',
        'remind_days': 'अनुस्मारक दिन',
        'monday': 'सोमवार',
        'tuesday': 'मंगलवार',
        'wednesday': 'बुधवार',
        'thursday': 'गुरुवार',
        'friday': 'शुक्रवार',
        'saturday': 'शनिवार',
        'sunday': 'रविवार',
        'export': 'डेटा निर्यात करें',
        'backup': 'डेटा बैकअप करें',
        'admin_panel': 'व्यवस्थापक पैनल',
        'success_save': 'सफलतापूर्वक सहेजा गया!',
        'success_submit': 'राम नाम जमा किया गया!',
        'success_clear': 'कैनवास साफ किया गया!',
        'success_auto': 'स्वतः मोड शुरू हुआ!',
        'success_export': 'डेटा निर्यात किया गया!',
        'success_backup': 'डेटा बैकअप किया गया!',
        'success_reminder': 'अनुस्मारक सेटिंग्स सहेजी गईं!',
        'ram_stats': 'राम नाम आंकड़े',
        'download': 'डाउनलोड',
        'close': 'बंद करें',
        'daily_stats': 'दैनिक आंकड़े',
        'monthly_stats': 'मासिक आंकड़े',
        'yearly_stats': 'वार्षिक आंकड़े',
        'rate_experience': 'अपने अनुभव को रेट करें',
        'send_feedback': 'प्रतिक्रिया भेजें',
        'back': 'वापस',
        'settings': 'सेटिंग्स'
    }
};

function initializeApp() {
    // Check if running in Android WebView
    checkPlatform();
    
    // Setup initial UI
    setupCanvas();
    
    // Load previously saved data
    loadData();
    
    // Update displays
    updateCountDisplay();
    updateProfileStats();
    
    // Initialize page navigation events
    document.getElementById('writeButton').addEventListener('click', navigateToWriting);
    document.getElementById('homeButton').addEventListener('click', navigateToHome);
    document.getElementById('profileButton').addEventListener('click', navigateToProfile);
    document.getElementById('menuButton').addEventListener('click', toggleMenu);
    document.getElementById('profileMenuButton').addEventListener('click', toggleProfile);
    
    // Setup menu click handlers
    document.getElementById('menuWrite').addEventListener('click', handleMenuWriteClick);
    document.getElementById('menuShare').addEventListener('click', handleMenuShareClick);
    document.getElementById('menuRate').addEventListener('click', handleMenuRateClick);
    document.getElementById('menuFeedback').addEventListener('click', handleMenuFeedbackClick);
    document.getElementById('menuHowTo').addEventListener('click', handleMenuHowToClick);
    document.getElementById('menuLanguage').addEventListener('click', handleMenuLanguageClick);
    document.getElementById('menuTerms').addEventListener('click', handleMenuTermsClick);
    document.getElementById('menuPrivacy').addEventListener('click', handleMenuPrivacyClick);
    
    // Setup profile menu click handlers
    document.getElementById('profileView').addEventListener('click', handleProfileViewClick);
    document.getElementById('profileAccount').addEventListener('click', handleProfileAccountClick);
    document.getElementById('profileTheme').addEventListener('click', handleProfileThemeClick);
    document.getElementById('profileReminder').addEventListener('click', handleProfileReminderClick);
    document.getElementById('profileAdmin').addEventListener('click', handleProfileAdminClick);
    document.getElementById('profileLogout').addEventListener('click', handleProfileLogoutClick);
    
    // Setup canvas controls
    document.getElementById('clearButton').addEventListener('click', clearCanvas);
    document.getElementById('autoButton').addEventListener('click', autoDraw);
    document.getElementById('submitButton').addEventListener('click', submitDrawing);
    
    // Setup language selector
    document.getElementById('languageSelector').addEventListener('change', handleLanguageChange);
    
    // Setup theme selector
    document.getElementById('themeSelector').addEventListener('change', handleThemeChange);
    
    // Setup reminder settings save
    document.getElementById('saveReminderButton').addEventListener('click', saveReminderSettings);
    
    // Setup admin panel buttons
    document.getElementById('exportButton').addEventListener('click', exportData);
    document.getElementById('backupButton').addEventListener('click', backupData);
    
    // Setup overlay closers
    document.querySelectorAll('.overlay-close').forEach(button => {
        button.addEventListener('click', closeAllOverlays);
    });
    
    // Setup back buttons
    document.querySelectorAll('.back-button').forEach(button => {
        button.addEventListener('click', handleBackNavigation);
    });

    // Add a click counter for admin panel access (hidden feature)
    let adminClickCount = 0;
    const appTitle = document.querySelector('.app-title');
    appTitle.addEventListener('click', function() {
        adminClickCount++;
        if (adminClickCount >= 5) {
            navigateToAdmin();
            adminClickCount = 0;
        }
    });
    
    // Apply current language and theme
    applyLanguage();
    applyTheme(currentTheme);
    
    // Set initial page
    navigateToHome();
}

function checkPlatform() {
    // Check if running in Android WebView
    isAndroid = typeof AndroidInterface !== 'undefined';
    console.log("Running on Android: " + isAndroid);
}

function setupCanvas() {
    canvas = document.getElementById('drawingCanvas');
    context = canvas.getContext('2d');
    
    // Setup canvas dimensions
    resizeCanvas();
    window.addEventListener('resize', resizeCanvas);
    
    // Setup drawing events
    canvas.addEventListener('mousedown', startDrawing);
    canvas.addEventListener('mousemove', draw);
    canvas.addEventListener('mouseup', stopDrawing);
    canvas.addEventListener('mouseout', stopDrawing);
    
    // Touch events for mobile
    canvas.addEventListener('touchstart', startDrawingTouch);
    canvas.addEventListener('touchmove', drawTouch);
    canvas.addEventListener('touchend', stopDrawing);
    
    // Setup canvas style
    context.strokeStyle = '#FF8C00'; // Orange color for Ram
    context.lineJoin = 'round';
    context.lineCap = 'round';
    context.lineWidth = 3;
}

function resizeCanvas() {
    const container = document.querySelector('.canvas-container');
    canvas.width = container.offsetWidth - 20; // Adjust for padding
    canvas.height = container.offsetHeight - 20;
}

function loadData() {
    const savedData = localStorage.getItem('ramLekhakData');
    if (savedData) {
        data = JSON.parse(savedData);
    }
    
    // Load app settings
    const savedSettings = localStorage.getItem('ramLekhakSettings');
    if (savedSettings) {
        const settings = JSON.parse(savedSettings);
        currentTheme = settings.theme || 'light';
        currentLanguage = settings.language || 'en';
    }
    
    // Check if it's a new day and reset todayCount if needed
    const today = new Date().toLocaleDateString();
    if (data.lastDate !== today) {
        data.todayCount = 0;
        data.lastDate = today;
        
        // Update streak
        if (isConsecutiveDay(data.lastDate)) {
            data.streak++;
        } else if (daysBetween(data.lastDate, today) > 1) {
            data.streak = 0;
        }
        
        saveData();
    }
}

function isConsecutiveDay(lastDate) {
    const lastDay = new Date(lastDate);
    const today = new Date();
    const timeDiff = today.getTime() - lastDay.getTime();
    const dayDiff = Math.floor(timeDiff / (1000 * 3600 * 24));
    return dayDiff === 1;
}

function daysBetween(date1, date2) {
    const d1 = new Date(date1);
    const d2 = new Date(date2);
    const timeDiff = d2.getTime() - d1.getTime();
    return Math.floor(timeDiff / (1000 * 3600 * 24));
}

function saveData() {
    localStorage.setItem('ramLekhakData', JSON.stringify(data));
}

function updateCountDisplay() {
    document.getElementById('totalCount').textContent = data.totalCount;
    document.getElementById('todayCount').textContent = data.todayCount;
    document.getElementById('streakCount').textContent = data.streak;
    document.getElementById('malaCount').textContent = Math.floor(data.totalCount / 108);
    
    // Update stars based on today's count
    updateStars();
}

function updateStars() {
    const starsContainer = document.getElementById('starsContainer');
    const starCount = Math.min(5, Math.floor(data.todayCount / 20));
    data.stars = starCount;
    
    // Clear previous stars
    starsContainer.innerHTML = '';
    
    // Add stars based on count (1 star per 20 entries, max 5)
    for (let i = 0; i < 5; i++) {
        const star = document.createElement('span');
        star.className = i < starCount ? 'star filled' : 'star';
        star.innerHTML = '★';
        star.addEventListener('click', function() {
            // Allow manual star rating for feedback
            const clickedIndex = i;
            const stars = starsContainer.querySelectorAll('.star');
            stars.forEach((s, index) => {
                s.className = index <= clickedIndex ? 'star filled' : 'star';
            });
            data.stars = clickedIndex + 1;
            saveData();
            
            // Show toast feedback
            showToast('Rating saved!');
        });
        starsContainer.appendChild(star);
    }
}

function updateProfileStats() {
    document.getElementById('profileTotalCount').textContent = data.totalCount;
    document.getElementById('profileDailyAvg').textContent = calculateDailyAverage();
    document.getElementById('profileTotalDays').textContent = Object.keys(data.dailyCounts).length;
    document.getElementById('profileMalas').textContent = Math.floor(data.totalCount / 108);
}

function calculateDailyAverage() {
    const days = Object.keys(data.dailyCounts).length;
    if (days === 0) return '0';
    return (data.totalCount / days).toFixed(1);
}

function navigateToPage(page) {
    // Hide all pages
    document.querySelectorAll('.page').forEach(p => {
        p.classList.remove('active');
    });
    
    // Show requested page
    document.getElementById(page + 'Page').classList.add('active');
    
    // Close any open overlays
    closeAllOverlays();
    
    // Update header based on page
    updateHeaderForPage(page);
}

function updateHeaderForPage(page) {
    document.querySelector('.app-header').classList.remove('hidden');
    document.querySelector('.app-title').textContent = 
        textResources[currentLanguage][page === 'home' ? 'app_title' : page + '_title'] || 'Ram Lekhak';
    
    // Show/hide back button based on page
    const backButton = document.querySelector('.back-button');
    if (page === 'home') {
        backButton.classList.add('hidden');
    } else {
        backButton.classList.remove('hidden');
    }
}

function navigateToHome() {
    navigateToPage('home');
}

function navigateToWriting() {
    navigateToPage('writing');
    // Reset canvas
    clearCanvas();
}

function navigateToProfile() {
    navigateToPage('profile');
    updateProfileStats();
}

function navigateToAdmin() {
    navigateToPage('admin');
}

function handleBackNavigation() {
    navigateToHome();
}

function toggleMenu() {
    document.getElementById('menuOverlay').classList.toggle('active');
}

function toggleProfile() {
    document.getElementById('profileOverlay').classList.toggle('active');
}

function closeMenuOverlay() {
    document.getElementById('menuOverlay').classList.remove('active');
}

function closeProfileOverlay() {
    document.getElementById('profileOverlay').classList.remove('active');
}

function closeAllOverlays() {
    closeMenuOverlay();
    closeProfileOverlay();
    
    // Also close other potential overlays
    document.querySelectorAll('.overlay').forEach(overlay => {
        overlay.classList.remove('active');
    });
}

function handleMenuWriteClick() {
    closeAllOverlays();
    navigateToWriting();
}

function handleMenuShareClick() {
    closeAllOverlays();
    shareStats();
}

function handleMenuRateClick() {
    closeAllOverlays();
    document.getElementById('rateOverlay').classList.add('active');
}

function handleMenuFeedbackClick() {
    closeAllOverlays();
    document.getElementById('feedbackOverlay').classList.add('active');
}

function handleMenuHowToClick() {
    closeAllOverlays();
    document.getElementById('howToOverlay').classList.add('active');
}

function handleMenuLanguageClick() {
    closeAllOverlays();
    document.getElementById('languageOverlay').classList.add('active');
    
    // Set current language in selector
    document.getElementById('languageSelector').value = currentLanguage;
}

function applyLanguage() {
    // Update all text elements with the appropriate language
    document.querySelectorAll('[data-text]').forEach(element => {
        const textKey = element.getAttribute('data-text');
        if (textResources[currentLanguage] && textResources[currentLanguage][textKey]) {
            element.textContent = textResources[currentLanguage][textKey];
        }
    });
    
    // Update placeholders
    document.querySelectorAll('[data-placeholder]').forEach(element => {
        const placeholderKey = element.getAttribute('data-placeholder');
        if (textResources[currentLanguage] && textResources[currentLanguage][placeholderKey]) {
            element.placeholder = textResources[currentLanguage][placeholderKey];
        }
    });
}

function showToast(message, duration = 3000) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.classList.add('visible');
    
    setTimeout(() => {
        toast.classList.remove('visible');
    }, duration);
    
    // Also show Android toast if available
    if (isAndroid) {
        try {
            AndroidInterface.showToast(message);
        } catch (e) {
            console.error("Error showing Android toast", e);
        }
    }
}

function handleMenuTermsClick() {
    closeAllOverlays();
    document.getElementById('termsOverlay').classList.add('active');
}

function handleMenuPrivacyClick() {
    closeAllOverlays();
    document.getElementById('privacyOverlay').classList.add('active');
}

function handleProfileViewClick() {
    closeAllOverlays();
    document.getElementById('statsOverlay').classList.add('active');
}

function handleProfileAccountClick() {
    closeAllOverlays();
    document.getElementById('accountOverlay').classList.add('active');
}

function handleProfileThemeClick() {
    closeAllOverlays();
    document.getElementById('themeOverlay').classList.add('active');
    
    // Set current theme in selector
    document.getElementById('themeSelector').value = currentTheme;
}

function setTheme(theme) {
    currentTheme = theme;
    applyTheme(theme);
    saveAppSettings();
}

function handleProfileReminderClick() {
    closeAllOverlays();
    document.getElementById('reminderOverlay').classList.add('active');
    
    // Load saved reminder settings
    const reminderTime = localStorage.getItem('reminderTime');
    if (reminderTime) {
        document.getElementById('reminderTime').value = reminderTime;
    } else {
        document.getElementById('reminderTime').value = '';
    }
    
    const reminderDays = localStorage.getItem('reminderDays');
    if (reminderDays) {
        const days = JSON.parse(reminderDays);
        document.querySelectorAll('.reminder-day-checkbox').forEach(checkbox => {
            checkbox.checked = days.includes(checkbox.value);
        });
    } else {
        // No default selected days
        document.querySelectorAll('.reminder-day-checkbox').forEach(checkbox => {
            checkbox.checked = false;
        });
    }
}

function saveReminderSettings() {
    const reminderTime = document.getElementById('reminderTime').value;
    localStorage.setItem('reminderTime', reminderTime);
    
    const selectedDays = [];
    document.querySelectorAll('.reminder-day-checkbox:checked').forEach(checkbox => {
        selectedDays.push(checkbox.value);
    });
    localStorage.setItem('reminderDays', JSON.stringify(selectedDays));
    
    showToast(textResources[currentLanguage]['success_reminder']);
    closeAllOverlays();
}

function handleProfileAdminClick() {
    closeAllOverlays();
    navigateToAdmin();
}

function handleProfileLogoutClick() {
    closeAllOverlays();
    document.getElementById('logoutConfirmOverlay').classList.add('active');
}

function handleLogout() {
    // Clear all data
    localStorage.clear();
    
    // Reset data object
    data = {
        totalCount: 0,
        dailyCounts: {},
        todayCount: 0,
        streak: 0,
        lastDate: new Date().toLocaleDateString(),
        malaCount: 0,
        stars: 0,
    };
    
    // Reset UI
    updateCountDisplay();
    updateProfileStats();
    
    // Navigate to home
    navigateToHome();
    
    // Close overlay
    closeAllOverlays();
    
    // Show confirmation
    showToast('Logged out successfully!');
}

function handleLanguageChange() {
    const languageSelector = document.getElementById('languageSelector');
    currentLanguage = languageSelector.value;
    applyLanguage();
    saveAppSettings();
    closeAllOverlays();
}

function handleThemeChange() {
    const themeSelector = document.getElementById('themeSelector');
    setTheme(themeSelector.value);
    closeAllOverlays();
}

function applyTheme(theme) {
    const body = document.body;
    body.classList.remove('light-theme', 'dark-theme');
    
    if (theme === 'auto') {
        // Check system preference
        if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
            body.classList.add('dark-theme');
        } else {
            body.classList.add('light-theme');
        }
    } else {
        body.classList.add(theme + '-theme');
    }
}

function saveAppSettings() {
    const settings = {
        theme: currentTheme,
        language: currentLanguage
    };
    localStorage.setItem('ramLekhakSettings', JSON.stringify(settings));
}

function exportData() {
    const dataStr = JSON.stringify(data, null, 2);
    const dataUri = 'data:application/json;charset=utf-8,' + encodeURIComponent(dataStr);
    
    if (isAndroid) {
        // Use Android's sharing functionality
        try {
            AndroidInterface.shareText('Ram Lekhak Data Export', dataStr);
            showToast(textResources[currentLanguage]['success_export']);
        } catch (e) {
            console.error("Error sharing data via Android interface", e);
            // Fallback to browser download
            downloadData(dataUri, 'ram_lekhak_data.json');
        }
    } else {
        // Browser download
        downloadData(dataUri, 'ram_lekhak_data.json');
        showToast(textResources[currentLanguage]['success_export']);
    }
}

function downloadData(dataUri, filename) {
    const link = document.createElement('a');
    link.setAttribute('href', dataUri);
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

function backupData() {
    // Copy data to a special backup key
    localStorage.setItem('ramLekhakBackup', JSON.stringify(data));
    
    showToast(textResources[currentLanguage]['success_backup']);
}

function startDrawing(e) {
    isDrawing = true;
    draw(e);
}

function startDrawingTouch(e) {
    isDrawing = true;
    drawTouch(e);
}

function draw(e) {
    if (!isDrawing) return;
    
    const rect = canvas.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;
    
    context.lineTo(x, y);
    context.stroke();
    context.beginPath();
    context.moveTo(x, y);
}

function drawTouch(e) {
    if (!isDrawing) return;
    e.preventDefault();
    
    const rect = canvas.getBoundingClientRect();
    const touch = e.touches[0];
    const x = touch.clientX - rect.left;
    const y = touch.clientY - rect.top;
    
    context.lineTo(x, y);
    context.stroke();
    context.beginPath();
    context.moveTo(x, y);
}

function stopDrawing() {
    isDrawing = false;
    context.beginPath();
}

function clearCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    showToast(textResources[currentLanguage]['success_clear']);
    
    // Stop auto mode if active
    if (autoMode) {
        autoMode = false;
    }
}

function autoDraw() {
    // Check if already in auto mode
    if (autoMode) return;
    
    autoMode = true;
    clearCanvas();
    showToast(textResources[currentLanguage]['success_auto']);
    
    // Draw "राम" in stylized format
    const text = "राम";
    const fontSize = Math.min(canvas.width / 2, 120);
    
    context.font = `${fontSize}px Devanagari, Arial`;
    context.textAlign = 'center';
    context.textBaseline = 'middle';
    
    // Use text with a timeout to make it appear animated
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    
    let charIndex = 0;
    let drawnText = "";
    
    function drawNextChar() {
        if (charIndex < text.length) {
            drawnText += text[charIndex];
            context.clearRect(0, 0, canvas.width, canvas.height);
            context.fillStyle = "#FF8C00"; // Orange color
            context.fillText(drawnText, centerX, centerY);
            charIndex++;
            setTimeout(drawNextChar, 500); // Draw next character after 500ms
        } else {
            // Auto-submit after drawing completes
            setTimeout(() => {
                if (autoMode) {
                    submitDrawing();
                    autoMode = false;
                }
            }, 1000);
        }
    }
    
    // Start the animation
    drawNextChar();
}

function submitDrawing() {
    // Increment counters
    data.totalCount++;
    data.todayCount++;
    
    // Update mala count (1 mala = 108 repetitions)
    data.malaCount = Math.floor(data.totalCount / 108);
    
    // Save to daily counts
    const today = new Date().toLocaleDateString();
    if (!data.dailyCounts[today]) {
        data.dailyCounts[today] = 0;
    }
    data.dailyCounts[today]++;
    
    // Update last date
    data.lastDate = today;
    
    // Save data
    saveData();
    
    // Update UI
    updateCountDisplay();
    updateProfileStats();
    
    // Clear canvas for next drawing
    clearCanvas();
    
    // Show confirmation
    showToast(textResources[currentLanguage]['success_submit']);
}

function shareStats() {
    const statsText = `
🔶 Ram Lekhak Stats 🔶
Total Ram Naam: ${data.totalCount}
Mala Count (108): ${Math.floor(data.totalCount / 108)}
Current Streak: ${data.streak} days
Today's Count: ${data.todayCount}
Rating: ${'★'.repeat(data.stars)}${'☆'.repeat(5-data.stars)}
    `;
    
    if (isAndroid) {
        // Use Android's native sharing
        try {
            AndroidInterface.shareText('Ram Lekhak Stats', statsText.trim());
        } catch (e) {
            console.error("Error sharing via Android interface", e);
            // Fallback to web sharing if available
            if (navigator.share) {
                shareViaWebAPI(statsText);
            } else {
                // Create a fallback UI for sharing
                alert("Please copy and share:\n\n" + statsText);
            }
        }
    } else if (navigator.share) {
        shareViaWebAPI(statsText);
    } else {
        // Create a fallback UI for sharing
        alert("Please copy and share:\n\n" + statsText);
    }
}

function shareViaWebAPI(text) {
    navigator.share({
        title: 'Ram Lekhak Stats',
        text: text.trim()
    }).catch(error => console.error('Error sharing:', error));
}

function downloadStats() {
    // Generate a simple chart image or PDF
    alert("This feature is coming soon!");
}