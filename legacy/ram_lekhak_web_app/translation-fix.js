// This file adds comprehensive translation support to the Ram Lekhak app
// It should be included in index.html after app.js

(function(window) {
    // Wait for the DOM to be fully loaded
    document.addEventListener('DOMContentLoaded', function() {
        // Create a more comprehensive translation fix
        
        // First, let's add any missing translations
        if (window.translations) {
            // Add settings translation if missing
            if (!window.translations.en.settings) {
                window.translations.en.settings = 'Settings';
                window.translations.hi.settings = 'सेटिंग्स';
            }
            
            // Add Write RAM button translation if missing
            if (!window.translations.en.write_ram_btn) {
                window.translations.en.write_ram_btn = 'Write RAM';
                window.translations.hi.write_ram_btn = 'राम लिखें';
            }
            
            // Add missing stats translations
            if (!window.translations.en.stats_title) {
                window.translations.en.stats_title = 'Stats';
                window.translations.hi.stats_title = 'आंकड़े';
            }
            
            // Add missing download translation
            if (!window.translations.en.download) {
                window.translations.en.download = 'Download';
                window.translations.hi.download = 'डाउनलोड';
            }
            
            // Add missing theme labels
            if (!window.translations.en.theme_selector) {
                window.translations.en.theme_selector = 'Theme:';
                window.translations.hi.theme_selector = 'थीम:';
            }
        }

        // Create an enhanced apply language function
        function enhancedApplyLanguage() {
            if (!window.getTranslation) {
                console.error('Translation function not available');
                return;
            }
            
            try {
                const currentLang = window.appLanguage || 'en';
                console.log('Applying enhanced language translations for:', currentLang);
                
                // 1. Translate all elements with data-i18n attribute
                document.querySelectorAll('[data-i18n]').forEach(el => {
                    const key = el.getAttribute('data-i18n');
                    if (key) {
                        el.textContent = window.getTranslation(key);
                    }
                });
                
                // 2. Translate specific elements by class/id
                
                // Main app title
                const appTitle = document.querySelector('.header-center h1');
                if (appTitle) {
                    appTitle.textContent = window.getTranslation('app_title');
                }
                
                // Menu header
                const menuHeader = document.querySelector('.menu-header h2');
                if (menuHeader) {
                    menuHeader.textContent = window.getTranslation('app_title');
                }
                
                // Start writing button
                const startWritingBtn = document.querySelector('#startWritingBtn span');
                if (startWritingBtn) {
                    startWritingBtn.textContent = window.getTranslation('write_ram');
                }
                
                // Home page stat cards
                const todayCountLabel = document.querySelector('.today-count h3');
                const todayMalaLabel = document.querySelector('.today-mala h3');
                const totalCountLabel = document.querySelector('.total-count h3');
                
                if (todayCountLabel) todayCountLabel.textContent = window.getTranslation('today_count');
                if (todayMalaLabel) todayMalaLabel.textContent = window.getTranslation('today_mala');
                if (totalCountLabel) totalCountLabel.textContent = window.getTranslation('total_count');
                
                // Writing page buttons
                const clearBtn = document.getElementById('clearBtn');
                const drawBtn = document.getElementById('drawBtn');
                const submitBtn = document.getElementById('submitBtn');
                const shareBtn = document.getElementById('shareBtn');
                const whatsappShareBtn = document.getElementById('whatsappShareBtn');
                const downloadBtn = document.getElementById('downloadBtn');
                
                if (clearBtn) clearBtn.textContent = window.getTranslation('clear');
                if (drawBtn) drawBtn.textContent = window.getTranslation('auto_draw');
                if (submitBtn) submitBtn.textContent = window.getTranslation('submit');
                if (shareBtn) shareBtn.textContent = window.getTranslation('share');
                if (whatsappShareBtn) whatsappShareBtn.textContent = window.getTranslation('share') + ' (WhatsApp)';
                if (downloadBtn) downloadBtn.textContent = window.getTranslation('download');
                
                // Writing page stat cards
                document.querySelectorAll('.stat-card .label').forEach(label => {
                    if (label.textContent.includes('Current Streak')) {
                        label.textContent = window.getTranslation('current_streak');
                    } else if (label.textContent.includes('Today\'s Malas')) {
                        label.textContent = window.getTranslation('today_mala');
                    } else if (label.textContent.includes('Today\'s Count')) {
                        label.textContent = window.getTranslation('today_count');
                    }
                });
                
                // Menu items
                const menuLinks = {
                    '#menuWriteLink': 'ram_writing',
                    '#menuShareLink': 'share',
                    '#menuRateLink': 'rate_app',
                    '#menuFeedbackLink': 'feedback',
                    '#menuHowToLink': 'how_to_use',
                    '#menuLanguageLink': 'language',
                    '#menuThemeLink': 'theme',
                    '#menuTermsLink': 'terms',
                    '#menuPrivacyLink': 'privacy'
                };
                
                for (const [selector, key] of Object.entries(menuLinks)) {
                    const link = document.querySelector(selector);
                    if (link) link.textContent = window.getTranslation(key);
                }
                
                // Profile page stats
                document.querySelectorAll('.stats-box .stat-label').forEach(label => {
                    const text = label.textContent.trim();
                    if (text.includes('Total RAM Written')) {
                        label.textContent = window.getTranslation('total_written');
                    } else if (text.includes('Total Malas')) {
                        label.textContent = window.getTranslation('total_malas');
                    } else if (text.includes('Current Streak')) {
                        label.textContent = window.getTranslation('current_streak');
                    } else if (text.includes('Longest Streak')) {
                        label.textContent = window.getTranslation('longest_streak');
                    }
                });
                
                // Goal progress labels
                document.querySelectorAll('.progress-container .progress-label span:first-child').forEach((label, index) => {
                    if (index === 0) {
                        label.textContent = window.getTranslation('daily_goal');
                    } else if (index === 1) {
                        label.textContent = window.getTranslation('monthly_goal');
                    }
                });
                
                // Profile menu items
                document.querySelectorAll('.profile-menu a .link-title').forEach(item => {
                    const text = item.textContent.trim();
                    if (text.includes('Writing Goals')) {
                        item.textContent = window.getTranslation('writing_goals');
                    } else if (text.includes('Account')) {
                        item.textContent = window.getTranslation('account');
                    } else if (text.includes('Theme')) {
                        item.textContent = window.getTranslation('theme');
                    } else if (text.includes('Daily Reminders')) {
                        item.textContent = window.getTranslation('daily_reminders');
                    } else if (text.includes('Admin')) {
                        item.textContent = window.getTranslation('admin');
                    } else if (text.includes('Logout')) {
                        item.textContent = window.getTranslation('logout');
                    }
                });
                
                // Theme options
                document.querySelectorAll('.theme-option .theme-label').forEach(label => {
                    const text = label.textContent.trim();
                    if (text.includes('Light')) {
                        label.textContent = window.getTranslation('light');
                    } else if (text.includes('Dark')) {
                        label.textContent = window.getTranslation('dark');
                    } else if (text.includes('System')) {
                        label.textContent = window.getTranslation('system');
                    }
                });
                
                // Page headers
                document.querySelectorAll('.page-header h2').forEach(header => {
                    if (header.textContent.includes('Profile')) {
                        header.textContent = window.getTranslation('profile');
                    } else if (header.textContent.includes('Admin')) {
                        header.textContent = window.getTranslation('admin');
                    }
                });
                
                // Name editing controls
                const editNameBtn = document.getElementById('edit-name-btn');
                const saveNameBtn = document.getElementById('save-name-btn');
                const cancelNameBtn = document.getElementById('cancel-name-btn');
                
                if (editNameBtn) editNameBtn.textContent = window.getTranslation('edit_name');
                if (saveNameBtn) saveNameBtn.textContent = window.getTranslation('save');
                if (cancelNameBtn) cancelNameBtn.textContent = window.getTranslation('cancel');
                
                // Settings form labels
                document.querySelectorAll('label[for]').forEach(label => {
                    if (label.getAttribute('for') === 'languageSelect') {
                        label.textContent = window.getTranslation('language') + ':';
                    } else if (label.getAttribute('for') === 'themeSelect') {
                        label.textContent = window.getTranslation('theme') + ':';
                    }
                });
                
                // Profile section headers
                document.querySelectorAll('.profile-menu-section h3').forEach(section => {
                    if (section.textContent.includes('Settings')) {
                        section.textContent = window.getTranslation('settings');
                    } else if (section.textContent.includes('Account')) {
                        section.textContent = window.getTranslation('account');
                    }
                });
                
                // Profile buttons
                const adminButton = document.getElementById('adminButton');
                const logoutButton = document.getElementById('logoutButton');
                
                if (adminButton) adminButton.textContent = window.getTranslation('admin');
                if (logoutButton) logoutButton.textContent = window.getTranslation('logout');
                
                console.log('Enhanced language translations applied successfully');
            } catch (error) {
                console.error('Error in enhanced language translation:', error);
            }
        }
        
        // Override the original applyLanguage function or patch it
        if (window.applyLanguage) {
            // Save original function
            const originalApplyLanguage = window.applyLanguage;
            
            // Replace with enhanced version
            window.applyLanguage = function() {
                try {
                    // Call original function first
                    originalApplyLanguage();
                } catch (e) {
                    console.warn('Original applyLanguage had an error:', e);
                }
                
                // Then apply our enhanced version
                enhancedApplyLanguage();
            };
            
            // Apply translations immediately if language is already set
            if (window.appLanguage) {
                setTimeout(window.applyLanguage, 500); // Small delay to ensure DOM is ready
            }
        } else {
            console.error('Original applyLanguage function not found');
        }
        
        // Add support for data-i18n attributes to make future translations easier
        // Example usage: <span data-i18n="today_count"></span>
        const setupI18nAttributes = function() {
            // Look for elements with data-i18n attribute and translate them
            document.querySelectorAll('[data-i18n]').forEach(el => {
                const key = el.getAttribute('data-i18n');
                if (key && window.getTranslation) {
                    el.textContent = window.getTranslation(key);
                }
            });
        };
        
        // Set up a MutationObserver to handle dynamic elements
        const observer = new MutationObserver(function(mutations) {
            let needsTranslationUpdate = false;
            
            mutations.forEach(function(mutation) {
                if (mutation.type === 'childList' && mutation.addedNodes.length > 0) {
                    // Check if any added nodes have data-i18n or are relevant elements
                    mutation.addedNodes.forEach(function(node) {
                        if (node.nodeType === 1) { // Element node
                            if (node.hasAttribute && node.hasAttribute('data-i18n') || 
                                node.querySelector && node.querySelector('[data-i18n]')) {
                                needsTranslationUpdate = true;
                            }
                        }
                    });
                }
            });
            
            if (needsTranslationUpdate && window.appLanguage) {
                enhancedApplyLanguage();
            }
        });
        
        // Start observing the document with the configured parameters
        observer.observe(document.body, { childList: true, subtree: true });
        
        // Initial setup
        setupI18nAttributes();
        
        // Also trigger translation when language is changed
        const originalHandleLanguageChange = window.handleLanguageChange;
        if (originalHandleLanguageChange) {
            window.handleLanguageChange = function() {
                originalHandleLanguageChange.apply(this, arguments);
                setTimeout(enhancedApplyLanguage, 100); // Apply with slight delay
            };
        }
        
        console.log('Translation enhancement system initialized');
    });
})(window);