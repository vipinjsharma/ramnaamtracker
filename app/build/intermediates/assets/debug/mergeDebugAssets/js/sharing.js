class Sharing {
    constructor() {
        this.setupEventListeners();
    }
    
    setupEventListeners() {
        const shareBtn = document.querySelector('#shareBtn');
        if (shareBtn) {
            shareBtn.addEventListener('click', () => this.showShareDialog());
        }
    }
    
    showShareDialog() {
        const dialog = document.createElement('div');
        dialog.className = 'dialog';
        dialog.innerHTML = `
            <div class="dialog-content">
                <h3>${state.language === 'hi' ? 'प्रगति साझा करें' : 'Share Progress'}</h3>
                <div class="share-options">
                    <button class="share-option whatsapp" onclick="sharing.shareToWhatsApp()">
                        <img src="images/whatsapp.png" alt="WhatsApp">
                        <span>WhatsApp</span>
                    </button>
                    <button class="share-option other" onclick="sharing.shareToOther()">
                        <span class="material-icons">share</span>
                        <span>${state.language === 'hi' ? 'अन्य' : 'Other'}</span>
                    </button>
                </div>
                <button class="close-btn">
                    <span class="material-icons">close</span>
                </button>
            </div>
        `;
        
        dialog.querySelector('.close-btn').addEventListener('click', () => {
            dialog.remove();
        });
        
        document.body.appendChild(dialog);
    }
    
    formatShareText() {
        const stats = JSON.parse(localStorage.getItem('statistics') || '{}');
        const lang = state.language || 'en';
        
        if (lang === 'hi') {
            return `🕉️ राम नाम लेखन यात्रा 📝\n\n` +
                   `आज का लेखन: ${stats.todayCount || 0}\n` +
                   `कुल माला: ${stats.totalMalas || 0}\n` +
                   `निरंतर दिन: ${stats.streak || 0}\n\n` +
                   `मासिक लक्ष्य: ${Math.round((stats.monthlyCount || 0) / 3240 * 100)}% पूर्ण\n\n` +
                   `राम नाम लेखक ऐप से साझा किया गया 🙏`;
        }
        
        return `🕉️ Ram Naam Writing Journey 📝\n\n` +
               `Today's Count: ${stats.todayCount || 0}\n` +
               `Total Malas: ${stats.totalMalas || 0}\n` +
               `Daily Streak: ${stats.streak || 0} days\n\n` +
               `Monthly Goal: ${Math.round((stats.monthlyCount || 0) / 3240 * 100)}% complete\n\n` +
               `Shared from Ram Naam Lekhak App 🙏`;
    }
    
    shareToWhatsApp() {
        const text = encodeURIComponent(this.formatShareText());
        const url = `whatsapp://send?text=${text}`;
        
        // Try to open WhatsApp
        const fallback = () => {
            Android.showToast(state.language === 'hi' ? 
                'WhatsApp इंस्टॉल नहीं है' : 
                'WhatsApp is not installed'
            );
            this.shareToOther();
        };
        
        try {
            window.location.href = url;
            setTimeout(() => {
                if (document.hasFocus()) {
                    fallback();
                }
            }, 300);
        } catch (e) {
            fallback();
        }
    }
    
    shareToOther() {
        Android.shareText(this.formatShareText());
    }
}

// Initialize sharing
window.sharing = new Sharing(); 