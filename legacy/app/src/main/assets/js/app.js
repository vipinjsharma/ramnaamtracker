// App State
const state = {
    todayCount: 0,
    totalCount: 0,
    todayMalas: 0,
    currentStreak: 0,
    dailyGoal: 108,
    monthlyGoal: 3240,
    theme: 'ram',
    language: 'en'
};

// DOM Elements
const elements = {
    userName: document.getElementById('userName'),
    currentStreak: document.getElementById('currentStreak'),
    todayMalas: document.getElementById('todayMalas'),
    todayCount: document.getElementById('todayCount'),
    progressFill: document.getElementById('progressFill'),
    menuBtn: document.getElementById('menuBtn'),
    profileBtn: document.getElementById('profileBtn'),
    menuOverlay: document.getElementById('menuOverlay'),
    clearBtn: document.getElementById('clearBtn'),
    autoDrawBtn: document.getElementById('autoDrawBtn'),
    submitBtn: document.getElementById('submitBtn'),
    shareBtn: document.getElementById('shareBtn')
};

// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    initializeApp();
    setupEventListeners();
});

function initializeApp() {
    // Check if this is first time user
    const hasSeenWalkthrough = localStorage.getItem('hasSeenWalkthrough');
    if (!hasSeenWalkthrough) {
        // Show walkthrough for first time users
        showAppWalkthrough();
        localStorage.setItem('hasSeenWalkthrough', 'true');
    }
    
    // Load user preferences
    loadTheme();
    loadLanguage();
    
    // Load statistics
    loadStatistics();
    
    // Update UI
    updateUI();
}

function setupEventListeners() {
    // Menu controls
    elements.menuBtn.addEventListener('click', toggleMenu);
    elements.profileBtn.addEventListener('click', () => {
        window.location.href = '#profile';
    });
    
    // Writing controls
    elements.clearBtn.addEventListener('click', () => {
        drawingCanvas.clear();
        Android.vibrate(50);
    });
    
    elements.autoDrawBtn.addEventListener('click', () => {
        drawingCanvas.autoDraw();
        Android.vibrate(100);
        submitDrawing();
    });
    
    elements.submitBtn.addEventListener('click', () => {
        submitDrawing();
    });
    
    // Share button
    elements.shareBtn.addEventListener('click', shareProgress);
    
    // Menu items
    document.querySelectorAll('.menu-item').forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            handleMenuItemClick(item.dataset.page);
        });
    });
}

function toggleMenu() {
    elements.menuOverlay.classList.toggle('hidden');
    Android.vibrate(30);
}

function submitDrawing() {
    // Increment counts
    state.todayCount++;
    state.totalCount++;
    state.todayMalas = Math.floor(state.todayCount / 108);
    
    // Update streak
    updateStreak();
    
    // Update UI
    updateUI();
    
    // Vibrate for feedback
    Android.vibrate(50);
    
    // Show confetti on mala completion
    if (state.todayCount % 108 === 0) {
        showConfetti();
    }
    
    // Save progress
    saveProgress();
}

function updateUI() {
    elements.todayCount.textContent = state.todayCount;
    elements.todayMalas.textContent = state.todayMalas;
    elements.currentStreak.textContent = `${state.currentStreak} Days`;
    
    // Update progress bar
    const progress = (state.todayCount / state.dailyGoal) * 100;
    elements.progressFill.style.width = `${Math.min(progress, 100)}%`;
}

function shareProgress() {
    const text = `🕉️ My Ram Naam Writing Progress:\n` +
                `Today's Count: ${state.todayCount}\n` +
                `Malas Completed: ${state.todayMalas}\n` +
                `Current Streak: ${state.currentStreak} days\n` +
                `#RamNaam #Spirituality`;
    
    Android.shareProgress(text);
}

function handleMenuItemClick(page) {
    toggleMenu();
    
    switch (page) {
        case 'write':
            window.location.href = '#write';
            break;
        case 'language':
            showLanguageDialog();
            break;
        case 'theme':
            showThemeDialog();
            break;
        case 'howto':
            showHowToUse();
            break;
        case 'feedback':
            showFeedbackForm();
            break;
        case 'rate':
            Android.openPlayStore();
            break;
        case 'terms':
            showTerms();
            break;
        case 'privacy':
            showPrivacy();
            break;
    }
}

function loadTheme() {
    const theme = localStorage.getItem('theme') || 'ram';
    applyTheme(theme.toLowerCase());
    state.theme = theme;
}

function applyTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('theme', theme);
    
    // Update canvas color if it exists
    if (window.drawingCanvas) {
        const color = getComputedStyle(document.documentElement).getPropertyValue('--primary-color').trim();
        drawingCanvas.ctx.strokeStyle = color;
    }
}

function showThemeDialog() {
    const themes = ['RAM', 'KRISHNA', 'LAKSHMI', 'GANESH', 'SHIVA'];
    const dialog = document.createElement('div');
    dialog.className = 'dialog';
    dialog.innerHTML = `
        <div class="dialog-content">
            <h3>Select Theme</h3>
            <div class="theme-grid">
                ${themes.map(theme => `
                    <button class="theme-option ${state.theme === theme ? 'active' : ''}" 
                            data-theme="${theme.toLowerCase()}"
                            style="background-color: var(--primary);">
                        ${theme}
                    </button>
                `).join('')}
            </div>
            <button class="close-btn">
                <span class="material-icons">close</span>
            </button>
        </div>
    `;

    dialog.querySelector('.close-btn').addEventListener('click', () => {
        dialog.remove();
    });

    dialog.querySelectorAll('.theme-option').forEach(button => {
        button.addEventListener('click', () => {
            const selectedTheme = button.dataset.theme;
            applyTheme(selectedTheme);
            state.theme = selectedTheme.toUpperCase();
            dialog.remove();
            Android.vibrate(50);
        });
    });

    document.body.appendChild(dialog);
}

function loadLanguage() {
    state.language = localStorage.getItem('language') || Android.getDeviceLanguage() || 'en';
    updateLanguage();
}

function updateLanguage() {
    const translations = state.language === 'hi' ? hindiTranslations : englishTranslations;
    
    // Update app name
    document.querySelector('header h1').textContent = translations.appName;
    
    // Update greeting
    const hour = new Date().getHours();
    let greeting;
    if (hour < 12) greeting = translations.greeting.morning;
    else if (hour < 16) greeting = translations.greeting.afternoon;
    else if (hour < 20) greeting = translations.greeting.evening;
    else greeting = translations.greeting.night;
    
    document.querySelector('.greeting h2').textContent = `${greeting}, ${state.userName || 'User'}`;
    
    // Update stats
    document.querySelector('#todayCountLabel').textContent = translations.stats.todayCount;
    document.querySelector('#totalCountLabel').textContent = translations.stats.totalCount;
    document.querySelector('#malasLabel').textContent = translations.stats.malas;
    document.querySelector('#streakLabel').textContent = translations.stats.streak;
    
    // Update buttons
    document.querySelector('#clearBtn').textContent = translations.actions.clear;
    document.querySelector('#submitBtn').textContent = translations.actions.submit;
    document.querySelector('#autoDrawBtn').textContent = translations.actions.autoDraw;
    document.querySelector('#shareBtn').textContent = translations.actions.share;
    
    // Update menu items
    document.querySelectorAll('.menu-item').forEach(item => {
        const key = item.dataset.page;
        item.querySelector('span:not(.material-icons)').textContent = translations.menu[key];
    });
}

function showLanguageDialog() {
    const dialog = document.createElement('div');
    dialog.className = 'dialog';
    dialog.innerHTML = `
        <div class="dialog-content">
            <h3>${state.language === 'hi' ? 'भाषा चुनें' : 'Select Language'}</h3>
            <div class="language-grid">
                <button class="language-option ${state.language === 'en' ? 'active' : ''}" 
                        data-lang="en">English</button>
                <button class="language-option ${state.language === 'hi' ? 'active' : ''}" 
                        data-lang="hi">हिंदी</button>
            </div>
            <button class="close-btn">
                <span class="material-icons">close</span>
            </button>
        </div>
    `;

    dialog.querySelector('.close-btn').addEventListener('click', () => {
        dialog.remove();
    });

    dialog.querySelectorAll('.language-option').forEach(button => {
        button.addEventListener('click', () => {
            const selectedLang = button.dataset.lang;
            state.language = selectedLang;
            localStorage.setItem('language', selectedLang);
            updateLanguage();
            dialog.remove();
            Android.vibrate(50);
        });
    });

    document.body.appendChild(dialog);
}

function loadStatistics() {
    // Load saved statistics from localStorage
    const stats = JSON.parse(localStorage.getItem('statistics') || '{}');
    Object.assign(state, stats);
}

function saveProgress() {
    localStorage.setItem('statistics', JSON.stringify({
        todayCount: state.todayCount,
        totalCount: state.totalCount,
        todayMalas: state.todayMalas,
        currentStreak: state.currentStreak,
        lastWrittenDate: new Date().toISOString()
    }));
}

function updateStreak() {
    const lastWritten = localStorage.getItem('lastWrittenDate');
    const today = new Date().toISOString().split('T')[0];
    
    if (!lastWritten) {
        state.currentStreak = 1;
    } else {
        const lastDate = new Date(lastWritten);
        const diffDays = Math.floor((new Date() - lastDate) / (1000 * 60 * 60 * 24));
        
        if (diffDays === 1) {
            state.currentStreak++;
        } else if (diffDays > 1) {
            state.currentStreak = 1;
        }
    }
    
    localStorage.setItem('lastWrittenDate', today);
}

function showConfetti() {
    // Implement confetti animation
    // You can use a library like canvas-confetti
}

class PullToRefresh {
    constructor() {
        this.startY = 0;
        this.threshold = 60;
        this.isRefreshing = false;
        this.setupElements();
        this.setupEventListeners();
    }

    setupElements() {
        // Create pull to refresh container if it doesn't exist
        if (!document.querySelector('.pull-to-refresh')) {
            const container = document.createElement('div');
            container.className = 'pull-to-refresh';
            const spinner = document.createElement('div');
            spinner.className = 'spinner';
            container.appendChild(spinner);
            document.body.insertBefore(container, document.body.firstChild);
        }
        this.container = document.querySelector('.pull-to-refresh');
    }

    setupEventListeners() {
        document.addEventListener('touchstart', this.onTouchStart.bind(this), { passive: true });
        document.addEventListener('touchmove', this.onTouchMove.bind(this), { passive: false });
        document.addEventListener('touchend', this.onTouchEnd.bind(this), { passive: true });
    }

    onTouchStart(e) {
        this.startY = e.touches[0].pageY;
    }

    onTouchMove(e) {
        if (this.isRefreshing) return;
        
        const currentY = e.touches[0].pageY;
        const scrollTop = document.documentElement.scrollTop;
        
        // Only trigger pull-to-refresh when at the top of the page
        if (scrollTop > 0) return;

        const pull = currentY - this.startY;
        if (pull > 0) {
            e.preventDefault();
            const progress = Math.min(pull / this.threshold, 1);
            this.container.style.transform = `translateY(${pull * 0.5}px)`;
        }
    }

    async onTouchEnd(e) {
        if (this.isRefreshing) return;

        const currentY = e.changedTouches[0].pageY;
        const pull = currentY - this.startY;

        if (pull > this.threshold) {
            await this.refresh();
        } else {
            this.reset();
        }
    }

    async refresh() {
        this.isRefreshing = true;
        this.container.classList.add('visible');

        try {
            // Refresh stats and goals
            await window.stats.loadStats();
            await window.goals.loadGoals();
            
            // Show success toast
            window.toast.show({
                message: window.translations.current.refreshSuccess,
                type: 'success'
            });
        } catch (error) {
            window.toast.show({
                message: window.translations.current.refreshError,
                type: 'error'
            });
        }

        setTimeout(() => {
            this.reset();
            this.isRefreshing = false;
        }, 1000);
    }

    reset() {
        this.container.style.transform = '';
        this.container.classList.remove('visible');
    }
}

// Initialize pull-to-refresh
window.pullToRefresh = new PullToRefresh(); 