class Pages {
    constructor() {
        this.currentPage = 'write';
        this.setupEventListeners();
    }
    
    setupEventListeners() {
        document.querySelectorAll('.menu-item').forEach(item => {
            item.addEventListener('click', (e) => {
                e.preventDefault();
                const page = item.dataset.page;
                this.showPage(page);
                document.querySelector('.overlay').classList.add('hidden');
                Android.vibrate(30);
            });
        });
    }
    
    showPage(page) {
        if (page === 'write') {
            this.showMainPage();
            return;
        }
        
        const content = this.getPageContent(page);
        const main = document.querySelector('main');
        main.innerHTML = content;
        this.currentPage = page;
        
        // Setup feedback form handlers if on feedback page
        if (page === 'feedback') {
            this.setupFeedbackForm();
        }
    }
    
    showMainPage() {
        location.reload(); // Simplest way to restore main page state
    }
    
    setupFeedbackForm() {
        const form = document.querySelector('#feedbackForm');
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            const type = form.querySelector('#feedbackType').value;
            const message = form.querySelector('#feedbackMessage').value;
            
            if (message.trim()) {
                Android.sendFeedback(type, message);
                this.showThankYouMessage();
            }
        });
    }
    
    showThankYouMessage() {
        const form = document.querySelector('#feedbackForm');
        form.innerHTML = `
            <div class="thank-you">
                <span class="material-icons">check_circle</span>
                <h3>${state.language === 'hi' ? 'धन्यवाद!' : 'Thank You!'}</h3>
                <p>${state.language === 'hi' ? 
                    'आपकी प्रतिक्रिया के लिए धन्यवाद। हम जल्द ही इसकी समीक्षा करेंगे।' : 
                    'Thank you for your feedback. We will review it soon.'}</p>
                <button class="primary-button" onclick="pages.showMainPage()">
                    ${state.language === 'hi' ? 'वापस जाएं' : 'Go Back'}
                </button>
            </div>
        `;
    }
    
    getPageContent(page) {
        const content = {
            howTo: `
                <div class="page-content">
                    <h2>${state.language === 'hi' ? 'उपयोग कैसे करें' : 'How to Use'}</h2>
                    <div class="tutorial-steps">
                        <div class="step">
                            <span class="material-icons">edit</span>
                            <h3>${state.language === 'hi' ? 'राम लिखें' : 'Write Ram'}</h3>
                            <p>${state.language === 'hi' ? 
                                'कैनवास पर अपनी उंगली या स्टाइलस से राम लिखें। स्वचालित लेखन के लिए ऑटो-ड्रा बटन का उपयोग करें।' : 
                                'Write Ram on the canvas using your finger or stylus. Use the auto-draw button for guided writing.'}</p>
                        </div>
                        <div class="step">
                            <span class="material-icons">counter_1</span>
                            <h3>${state.language === 'hi' ? 'गणना करें' : 'Count Increases'}</h3>
                            <p>${state.language === 'hi' ? 
                                'प्रत्येक बार जब आप राम लिखते हैं और जमा करते हैं, गणना बढ़ जाती है। 108 बार लिखने पर एक माला पूरी होती है।' : 
                                'Each time you write and submit Ram, the count increases. Complete one mala by writing 108 times.'}</p>
                        </div>
                        <div class="step">
                            <span class="material-icons">track_changes</span>
                            <h3>${state.language === 'hi' ? 'प्रगति देखें' : 'Track Progress'}</h3>
                            <p>${state.language === 'hi' ? 
                                'अपनी दैनिक गणना, कुल माला और निरंतरता की जानकारी देखें। अपने लक्ष्यों को पूरा करने की दिशा में प्रगति करें।' : 
                                'View your daily count, total malas, and streak. Progress towards your goals.'}</p>
                        </div>
                        <div class="step">
                            <span class="material-icons">share</span>
                            <h3>${state.language === 'hi' ? 'साझा करें' : 'Share Progress'}</h3>
                            <p>${state.language === 'hi' ? 
                                'अपनी प्रगति को परिवार और दोस्तों के साथ साझा करें। एक साथ आध्यात्मिक यात्रा का आनंद लें।' : 
                                'Share your progress with family and friends. Enjoy the spiritual journey together.'}</p>
                        </div>
                    </div>
                    <button class="primary-button" onclick="pages.showMainPage()">
                        ${state.language === 'hi' ? 'शुरू करें' : 'Start Writing'}
                    </button>
                </div>
            `,
            
            terms: `
                <div class="page-content">
                    <h2>${state.language === 'hi' ? 'सेवा की शर्तें' : 'Terms of Service'}</h2>
                    <div class="terms-content">
                        <section>
                            <h3>1. ${state.language === 'hi' ? 'स्वीकृति' : 'Acceptance'}</h3>
                            <p>${state.language === 'hi' ? 
                                'इस ऐप का उपयोग करके, आप इन सेवा की शर्तों से सहमत होते हैं।' : 
                                'By using this app, you agree to these Terms of Service.'}</p>
                        </section>
                        <section>
                            <h3>2. ${state.language === 'hi' ? 'उपयोग की शर्तें' : 'Usage Terms'}</h3>
                            <p>${state.language === 'hi' ? 
                                'यह ऐप केवल व्यक्तिगत, गैर-व्यावसायिक उपयोग के लिए है।' : 
                                'This app is for personal, non-commercial use only.'}</p>
                        </section>
                        <section>
                            <h3>3. ${state.language === 'hi' ? 'गोपनीयता' : 'Privacy'}</h3>
                            <p>${state.language === 'hi' ? 
                                'आपका डेटा आपके डिवाइस पर सुरक्षित रहता है। हमारी गोपनीयता नीति देखें।' : 
                                'Your data stays secure on your device. See our Privacy Policy.'}</p>
                        </section>
                        <section>
                            <h3>4. ${state.language === 'hi' ? 'परिवर्तन' : 'Changes'}</h3>
                            <p>${state.language === 'hi' ? 
                                'हम इन शर्तों को किसी भी समय बदल सकते हैं। नियमित रूप से जांच करते रहें।' : 
                                'We may change these terms at any time. Check regularly.'}</p>
                        </section>
                    </div>
                    <button class="primary-button" onclick="pages.showMainPage()">
                        ${state.language === 'hi' ? 'वापस जाएं' : 'Go Back'}
                    </button>
                </div>
            `,
            
            privacy: `
                <div class="page-content">
                    <h2>${state.language === 'hi' ? 'गोपनीयता नीति' : 'Privacy Policy'}</h2>
                    <div class="privacy-content">
                        <section>
                            <h3>${state.language === 'hi' ? 'डेटा संग्रह' : 'Data Collection'}</h3>
                            <p>${state.language === 'hi' ? 
                                'हम केवल वही डेटा एकत्र करते हैं जो ऐप के कार्य के लिए आवश्यक है:' : 
                                'We collect only the data necessary for app functionality:'}</p>
                            <ul>
                                <li>${state.language === 'hi' ? 'आपका नाम' : 'Your name'}</li>
                                <li>${state.language === 'hi' ? 'लेखन की गणना' : 'Writing counts'}</li>
                                <li>${state.language === 'hi' ? 'भाषा वरीयता' : 'Language preference'}</li>
                                <li>${state.language === 'hi' ? 'थीम सेटिंग' : 'Theme settings'}</li>
                            </ul>
                        </section>
                        <section>
                            <h3>${state.language === 'hi' ? 'डेटा सुरक्षा' : 'Data Security'}</h3>
                            <p>${state.language === 'hi' ? 
                                'सभी डेटा आपके डिवाइस पर स्थानीय रूप से संग्रहीत किया जाता है। हम कोई डेटा सर्वर पर नहीं भेजते।' : 
                                'All data is stored locally on your device. We do not send any data to servers.'}</p>
                        </section>
                        <section>
                            <h3>${state.language === 'hi' ? 'अनुमतियां' : 'Permissions'}</h3>
                            <p>${state.language === 'hi' ? 
                                'ऐप को केवल वाइब्रेशन और नोटिफिकेशन के लिए अनुमति की आवश्यकता है।' : 
                                'The app only requires permission for vibration and notifications.'}</p>
                        </section>
                    </div>
                    <button class="primary-button" onclick="pages.showMainPage()">
                        ${state.language === 'hi' ? 'वापस जाएं' : 'Go Back'}
                    </button>
                </div>
            `,
            
            feedback: `
                <div class="page-content">
                    <h2>${state.language === 'hi' ? 'प्रतिक्रिया भेजें' : 'Send Feedback'}</h2>
                    <form id="feedbackForm" class="feedback-form">
                        <div class="form-group">
                            <label for="feedbackType">
                                ${state.language === 'hi' ? 'प्रतिक्रिया का प्रकार' : 'Feedback Type'}
                            </label>
                            <select id="feedbackType" required>
                                <option value="suggestion">
                                    ${state.language === 'hi' ? 'सुझाव' : 'Suggestion'}
                                </option>
                                <option value="bug">
                                    ${state.language === 'hi' ? 'समस्या रिपोर्ट' : 'Bug Report'}
                                </option>
                                <option value="appreciation">
                                    ${state.language === 'hi' ? 'प्रशंसा' : 'Appreciation'}
                                </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="feedbackMessage">
                                ${state.language === 'hi' ? 'आपका संदेश' : 'Your Message'}
                            </label>
                            <textarea id="feedbackMessage" rows="5" required
                                placeholder="${state.language === 'hi' ? 
                                    'अपनी प्रतिक्रिया यहां लिखें...' : 
                                    'Write your feedback here...'}"></textarea>
                        </div>
                        <div class="form-buttons">
                            <button type="submit" class="primary-button">
                                ${state.language === 'hi' ? 'भेजें' : 'Send'}
                            </button>
                            <button type="button" class="secondary-button" onclick="pages.showMainPage()">
                                ${state.language === 'hi' ? 'रद्द करें' : 'Cancel'}
                            </button>
                        </div>
                    </form>
                </div>
            `
        };
        
        return content[page] || content.write;
    }
}

// Initialize pages
window.pages = new Pages(); 