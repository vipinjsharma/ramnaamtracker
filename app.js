document.addEventListener('DOMContentLoaded', function() {
    // DOM Elements
    const app = document.getElementById('app');
    const homePage = document.getElementById('homePage');
    const writingPage = document.getElementById('writingPage');
    const profilePage = document.getElementById('profilePage');
    const adminPage = document.getElementById('adminPage');
    const backButton = document.getElementById('backButton');
    const menuButton = document.getElementById('menuButton');
    const profileButton = document.getElementById('profileButton');
    const startWritingBtn = document.getElementById('startWritingBtn');
    const canvas = document.getElementById('drawingCanvas');
    const clearBtn = document.getElementById('clearBtn');
    const drawBtn = document.getElementById('drawBtn');
    const submitBtn = document.getElementById('submitBtn');
    const shareBtn = document.getElementById('shareBtn');
    const downloadBtn = document.getElementById('downloadBtn');
    
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
    
    // Initialize the app
    initializeApp();
    
    // Event Listeners
    backButton.addEventListener('click', handleBackNavigation);
    menuButton.addEventListener('click', toggleMenu);
    profileButton.addEventListener('click', toggleProfile);
    startWritingBtn.addEventListener('click', navigateToWriting);
    clearBtn.addEventListener('click', clearCanvas);
    drawBtn.addEventListener('click', autoDraw);
    submitBtn.addEventListener('click', submitDrawing);
    shareBtn.addEventListener('click', shareStats);
    downloadBtn.addEventListener('click', downloadStats);
    
    // Profile page event listeners
    adminButton.addEventListener('click', navigateToAdmin);
    logoutButton.addEventListener('click', handleLogout);
    languageSelect.addEventListener('change', handleLanguageChange);
    themeSelect.addEventListener('change', handleThemeChange);
    
    // Admin page event listeners
    saveSettingsBtn.addEventListener('click', saveAppSettings);
    exportDataBtn.addEventListener('click', exportData);
    backupDataBtn.addEventListener('click', backupData);
    
    // Canvas event listeners
    canvas.addEventListener('mousedown', startDrawing);
    canvas.addEventListener('mousemove', draw);
    canvas.addEventListener('mouseup', stopDrawing);
    canvas.addEventListener('mouseout', stopDrawing);
    
    // Touch events for mobile
    canvas.addEventListener('touchstart', startDrawingTouch);
    canvas.addEventListener('touchmove', drawTouch);
    canvas.addEventListener('touchend', stopDrawing);
    
    // Functions
    function initializeApp() {
        // Set up canvas
        setupCanvas();
        
        // Load saved data
        loadData();
        
        // Update UI
        updateCountDisplay();
        updateProfileStats();
        
        // Show home page initially
        navigateToHome();
    }
    
    function setupCanvas() {
        // Set canvas to full width of container
        canvas.width = canvas.parentElement.clientWidth;
        canvas.height = canvas.parentElement.clientHeight;
        
        // Get drawing context
        drawingContext = canvas.getContext('2d');
        
        // Set up context style
        drawingContext.strokeStyle = '#ff7817'; // Orange color like in the mockup
        drawingContext.lineJoin = 'round';
        drawingContext.lineCap = 'round';
        drawingContext.lineWidth = 5;
    }
    
    function loadData() {
        // Get today's date for comparison
        const today = new Date().toDateString();
        
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
        
        // Load settings
        if (savedData.settings) {
            if (savedData.settings.language) {
                languageSelect.value = savedData.settings.language;
            }
            if (savedData.settings.theme) {
                themeSelect.value = savedData.settings.theme;
                applyTheme(savedData.settings.theme);
            }
            if (savedData.settings.reminder !== undefined) {
                reminderToggle.checked = savedData.settings.reminder;
            }
        }
    }
    
    function saveData() {
        const today = new Date().toDateString();
        
        const dataToSave = {
            lastDate: today,
            lastActiveDate: today,
            todayCount: todayCount,
            todayMalaCount: todayMalaCount,
            totalCount: totalCount,
            currentStreak: currentStreak,
            longestStreak: longestStreak,
            settings: {
                language: languageSelect.value,
                theme: themeSelect.value,
                reminder: reminderToggle.checked
            }
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
    }
    
    function updateProfileStats() {
        // Update profile stats
        profileTotalCount.textContent = totalCount;
        profileTotalMalas.textContent = Math.floor(totalCount / MALA_COUNT);
        profileCurrentStreak.textContent = `${currentStreak} days`;
        profileLongestStreak.textContent = `${longestStreak} days`;
        
        // Update progress bars
        const dailyProgress = Math.min(100, (todayCount / DAILY_GOAL) * 100);
        dailyGoalProgress.style.width = `${dailyProgress}%`;
        
        const monthProgress = Math.min(100, (todayMalaCount / MONTHLY_GOAL) * 100);
        monthlyGoalProgress.style.width = `${monthProgress}%`;
    }
    
    function navigateToPage(page) {
        // Hide all pages
        homePage.classList.remove('active');
        writingPage.classList.remove('active');
        profilePage.classList.remove('active');
        adminPage.classList.remove('active');
        
        // Show target page
        page.classList.add('active');
        
        // Update back button visibility
        if (page === homePage) {
            backButton.style.visibility = 'hidden';
            profileButton.style.visibility = 'visible';
            menuButton.style.visibility = 'visible';
        } else {
            backButton.style.visibility = 'visible';
            profileButton.style.visibility = 'visible';
            menuButton.style.visibility = 'hidden';
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
    
    // Get menu elements
    const menuOverlay = document.getElementById('menuOverlay');
    const profileOverlay = document.getElementById('profileOverlay');
    const closeMenuBtn = document.getElementById('closeMenuBtn');
    const closeProfileBtn = document.getElementById('closeProfileBtn');
    const quickCurrentStreak = document.getElementById('quickCurrentStreak');
    
    // Menu navigation links
    const menuWriteLink = document.getElementById('menuWriteLink');
    const menuShareLink = document.getElementById('menuShareLink');
    const menuRateLink = document.getElementById('menuRateLink');
    const menuFeedbackLink = document.getElementById('menuFeedbackLink');
    const menuHowToLink = document.getElementById('menuHowToLink');
    const menuLanguageLink = document.getElementById('menuLanguageLink');
    const menuTermsLink = document.getElementById('menuTermsLink');
    const menuPrivacyLink = document.getElementById('menuPrivacyLink');
    
    // Profile navigation links
    const profileViewLink = document.getElementById('profileViewLink');
    const profileAccountLink = document.getElementById('profileAccountLink');
    const profileLanguageLink = document.getElementById('profileLanguageLink');
    const profileThemeLink = document.getElementById('profileThemeLink');
    const profileReminderLink = document.getElementById('profileReminderLink');
    const profileAdminLink = document.getElementById('profileAdminLink');
    const profileLogoutLink = document.getElementById('profileLogoutLink');
    
    // Add event listeners for menu items
    closeMenuBtn.addEventListener('click', closeMenuOverlay);
    closeProfileBtn.addEventListener('click', closeProfileOverlay);
    menuWriteLink.addEventListener('click', handleMenuWriteClick);
    menuShareLink.addEventListener('click', handleMenuShareClick);
    menuRateLink.addEventListener('click', handleMenuRateClick);
    menuFeedbackLink.addEventListener('click', handleMenuFeedbackClick);
    menuHowToLink.addEventListener('click', handleMenuHowToClick);
    menuLanguageLink.addEventListener('click', handleMenuLanguageClick);
    menuTermsLink.addEventListener('click', handleMenuTermsClick);
    menuPrivacyLink.addEventListener('click', handleMenuPrivacyClick);
    
    // Add event listeners for profile items
    profileViewLink.addEventListener('click', handleProfileViewClick);
    profileAccountLink.addEventListener('click', handleProfileAccountClick);
    profileLanguageLink.addEventListener('click', handleProfileLanguageClick);
    profileThemeLink.addEventListener('click', handleProfileThemeClick);
    profileReminderLink.addEventListener('click', handleProfileReminderClick);
    profileAdminLink.addEventListener('click', handleProfileAdminClick);
    profileLogoutLink.addEventListener('click', handleProfileLogoutClick);
    
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
        shareStats();
    }
    
    function handleMenuRateClick() {
        closeMenuOverlay();
        alert('Rate app functionality would be implemented here');
    }
    
    function handleMenuFeedbackClick() {
        closeMenuOverlay();
        alert('Feedback submission functionality would be implemented here');
    }
    
    function handleMenuHowToClick() {
        closeMenuOverlay();
        alert('How to use guide would be displayed here');
    }
    
    function handleMenuLanguageClick() {
        closeMenuOverlay();
        alert('Language would be changed to Hindi');
    }
    
    function handleMenuTermsClick() {
        closeMenuOverlay();
        alert('Terms and conditions would be displayed here');
    }
    
    function handleMenuPrivacyClick() {
        closeMenuOverlay();
        alert('Privacy policy would be displayed here');
    }
    
    // Profile link handlers
    function handleProfileViewClick() {
        closeProfileOverlay();
        navigateToProfile();
    }
    
    function handleProfileAccountClick() {
        closeProfileOverlay();
        alert('Account settings would be displayed here');
    }
    
    function handleProfileLanguageClick() {
        closeProfileOverlay();
        alert('Language preferences would be displayed here');
    }
    
    function handleProfileThemeClick() {
        closeProfileOverlay();
        alert('Theme preferences would be displayed here');
    }
    
    function handleProfileReminderClick() {
        closeProfileOverlay();
        alert('Reminder settings would be displayed here');
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
        if (theme === 'dark') {
            document.documentElement.classList.add('dark-theme');
        } else {
            document.documentElement.classList.remove('dark-theme');
        }
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
    }
    
    function stopDrawing() {
        isDrawing = false;
    }
    
    function clearCanvas() {
        drawingContext.clearRect(0, 0, canvas.width, canvas.height);
    }
    
    function autoDraw() {
        // Clear the canvas first
        clearCanvas();
        
        // The points to draw राम (Ram)
        const ramPoints = [
            // र (Ra)
            [[0.2, 0.3], [0.2, 0.7], [0.15, 0.7], [0.3, 0.7], [0.3, 0.55], [0.2, 0.55]],
            // ा (Aa matra)
            [[0.3, 0.4], [0.35, 0.4]],
            // म (Ma)
            [[0.4, 0.3], [0.4, 0.7], [0.5, 0.7], [0.5, 0.5], [0.4, 0.5], [0.5, 0.3]]
        ];
        
        // Draw राम
        let partIndex = 0;
        let pointIndex = 0;
        
        function drawNextPoint() {
            if (partIndex >= ramPoints.length) {
                // Drawing complete
                return;
            }
            
            const currentPart = ramPoints[partIndex];
            
            if (pointIndex === 0) {
                // Start a new part
                const startPoint = currentPart[0];
                drawingContext.beginPath();
                drawingContext.moveTo(
                    startPoint[0] * canvas.width,
                    startPoint[1] * canvas.height
                );
                pointIndex++;
            } else if (pointIndex < currentPart.length) {
                // Continue drawing current part
                const nextPoint = currentPart[pointIndex];
                drawingContext.lineTo(
                    nextPoint[0] * canvas.width,
                    nextPoint[1] * canvas.height
                );
                drawingContext.stroke();
                pointIndex++;
            } else {
                // Move to next part
                partIndex++;
                pointIndex = 0;
            }
            
            // Continue animation
            if (partIndex < ramPoints.length) {
                requestAnimationFrame(drawNextPoint);
            } else {
                // Automatically submit after drawing
                setTimeout(submitDrawing, 500);
            }
        }
        
        // Start the animated drawing
        drawNextPoint();
    }
    
    function submitDrawing() {
        // Increment counts
        todayCount++;
        totalCount++;
        
        // Calculate mala count (1 mala = 108 rams)
        todayMalaCount = Math.floor(todayCount / MALA_COUNT);
        
        // Clear the canvas for next drawing
        clearCanvas();
        
        // Update streak info (if this is first submission today)
        if (todayCount === 1) {
            currentStreak++;
            longestStreak = Math.max(currentStreak, longestStreak);
        }
        
        // Update the UI
        updateCountDisplay();
        
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
    
    function downloadStats() {
        // For now just use exportData function
        exportData();
    }
    
    // Handle window resize to adjust canvas
    window.addEventListener('resize', setupCanvas);
});