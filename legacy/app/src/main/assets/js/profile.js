class Profile {
    constructor() {
        this.adminClickCount = 0;
        this.adminClickTimer = null;
        this.setupEventListeners();
    }
    
    setupEventListeners() {
        // Logo click for admin access
        const logo = document.querySelector('header h1');
        logo.addEventListener('click', () => this.handleLogoClick());
        
        // Profile edit button
        const editNameBtn = document.querySelector('#editNameBtn');
        if (editNameBtn) {
            editNameBtn.addEventListener('click', () => this.showEditNameDialog());
        }
        
        // Reminder settings button
        const reminderBtn = document.querySelector('#reminderBtn');
        if (reminderBtn) {
            reminderBtn.addEventListener('click', () => this.showReminderDialog());
        }
    }
    
    handleLogoClick() {
        this.adminClickCount++;
        
        // Reset count after 2 seconds of inactivity
        clearTimeout(this.adminClickTimer);
        this.adminClickTimer = setTimeout(() => {
            this.adminClickCount = 0;
        }, 2000);
        
        // Show admin panel after 5 clicks
        if (this.adminClickCount >= 5) {
            this.showAdminPanel();
            this.adminClickCount = 0;
        }
    }
    
    showEditNameDialog() {
        const dialog = document.createElement('div');
        dialog.className = 'dialog';
        dialog.innerHTML = `
            <div class="dialog-content">
                <h3>${state.language === 'hi' ? 'नाम बदलें' : 'Edit Name'}</h3>
                <input type="text" id="nameInput" 
                       value="${state.userName || ''}" 
                       placeholder="${state.language === 'hi' ? 'अपना नाम लिखें' : 'Enter your name'}"
                       class="name-input">
                <div class="dialog-buttons">
                    <button class="save-btn">
                        ${state.language === 'hi' ? 'सहेजें' : 'Save'}
                    </button>
                    <button class="cancel-btn">
                        ${state.language === 'hi' ? 'रद्द करें' : 'Cancel'}
                    </button>
                </div>
                <button class="close-btn">
                    <span class="material-icons">close</span>
                </button>
            </div>
        `;
        
        const nameInput = dialog.querySelector('#nameInput');
        const saveBtn = dialog.querySelector('.save-btn');
        const cancelBtn = dialog.querySelector('.cancel-btn');
        const closeBtn = dialog.querySelector('.close-btn');
        
        saveBtn.addEventListener('click', () => {
            const newName = nameInput.value.trim();
            if (newName) {
                state.userName = newName;
                localStorage.setItem('userName', newName);
                updateLanguage(); // Update greeting with new name
                Android.vibrate(50);
            }
            dialog.remove();
        });
        
        [cancelBtn, closeBtn].forEach(btn => {
            btn.addEventListener('click', () => dialog.remove());
        });
        
        document.body.appendChild(dialog);
        nameInput.focus();
    }
    
    showReminderDialog() {
        const dialog = document.createElement('div');
        dialog.className = 'dialog';
        dialog.innerHTML = `
            <div class="dialog-content">
                <h3>${state.language === 'hi' ? 'रिमाइंडर सेट करें' : 'Set Reminders'}</h3>
                <div class="reminder-settings">
                    <div class="reminder-time">
                        <label>${state.language === 'hi' ? 'समय' : 'Time'}</label>
                        <input type="time" id="reminderTime" 
                               value="${localStorage.getItem('reminderTime') || '06:00'}">
                    </div>
                    <div class="reminder-days">
                        <label>${state.language === 'hi' ? 'दिन' : 'Days'}</label>
                        <div class="day-buttons">
                            ${['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'].map((day, i) => `
                                <button class="day-btn" data-day="${i}">
                                    ${state.language === 'hi' ? 
                                        ['रवि', 'सोम', 'मंगल', 'बुध', 'गुरु', 'शुक्र', 'शनि'][i] : 
                                        day}
                                </button>
                            `).join('')}
                        </div>
                    </div>
                </div>
                <div class="dialog-buttons">
                    <button class="save-btn">
                        ${state.language === 'hi' ? 'सहेजें' : 'Save'}
                    </button>
                    <button class="cancel-btn">
                        ${state.language === 'hi' ? 'रद्द करें' : 'Cancel'}
                    </button>
                </div>
                <button class="close-btn">
                    <span class="material-icons">close</span>
                </button>
            </div>
        `;
        
        const timeInput = dialog.querySelector('#reminderTime');
        const dayButtons = dialog.querySelectorAll('.day-btn');
        const saveBtn = dialog.querySelector('.save-btn');
        const cancelBtn = dialog.querySelector('.cancel-btn');
        const closeBtn = dialog.querySelector('.close-btn');
        
        // Load saved reminder days
        const reminderDays = JSON.parse(localStorage.getItem('reminderDays') || '[]');
        dayButtons.forEach(btn => {
            const day = parseInt(btn.dataset.day);
            if (reminderDays.includes(day)) {
                btn.classList.add('active');
            }
            
            btn.addEventListener('click', () => {
                btn.classList.toggle('active');
                Android.vibrate(30);
            });
        });
        
        saveBtn.addEventListener('click', () => {
            const time = timeInput.value;
            const days = Array.from(dayButtons)
                .filter(btn => btn.classList.contains('active'))
                .map(btn => parseInt(btn.dataset.day));
            
            localStorage.setItem('reminderTime', time);
            localStorage.setItem('reminderDays', JSON.stringify(days));
            
            // Notify Android to set alarms
            Android.setReminders(time, JSON.stringify(days));
            Android.vibrate(50);
            dialog.remove();
        });
        
        [cancelBtn, closeBtn].forEach(btn => {
            btn.addEventListener('click', () => dialog.remove());
        });
        
        document.body.appendChild(dialog);
    }
    
    showAdminPanel() {
        const dialog = document.createElement('div');
        dialog.className = 'dialog';
        dialog.innerHTML = `
            <div class="dialog-content">
                <h3>Admin Panel</h3>
                <div class="admin-controls">
                    <button id="resetStats">Reset Statistics</button>
                    <button id="exportData">Export User Data</button>
                    <button id="testNotif">Test Notification</button>
                </div>
                <button class="close-btn">
                    <span class="material-icons">close</span>
                </button>
            </div>
        `;
        
        dialog.querySelector('#resetStats').addEventListener('click', () => {
            if (confirm('Are you sure you want to reset all statistics?')) {
                localStorage.clear();
                location.reload();
            }
        });
        
        dialog.querySelector('#exportData').addEventListener('click', () => {
            const data = {
                statistics: JSON.parse(localStorage.getItem('statistics') || '{}'),
                settings: {
                    theme: localStorage.getItem('theme'),
                    language: localStorage.getItem('language'),
                    userName: localStorage.getItem('userName'),
                    reminderTime: localStorage.getItem('reminderTime'),
                    reminderDays: JSON.parse(localStorage.getItem('reminderDays') || '[]')
                }
            };
            Android.exportData(JSON.stringify(data));
        });
        
        dialog.querySelector('#testNotif').addEventListener('click', () => {
            Android.showTestNotification();
        });
        
        dialog.querySelector('.close-btn').addEventListener('click', () => {
            dialog.remove();
        });
        
        document.body.appendChild(dialog);
    }
}

// Initialize profile management
window.profile = new Profile(); 