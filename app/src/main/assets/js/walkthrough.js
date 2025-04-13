class Walkthrough {
    constructor() {
        this.steps = [
            {
                element: '.writing-area',
                title: {
                    en: 'Writing Area',
                    hi: 'लेखन क्षेत्र'
                },
                content: {
                    en: 'This is where you write "राम". Use your finger or stylus to write.',
                    hi: 'यहाँ आप "राम" लिखेंगे। अपनी उंगली या स्टाइलस का उपयोग करें।'
                },
                position: 'bottom'
            },
            {
                element: '#autoDrawBtn',
                title: {
                    en: 'Auto Draw',
                    hi: 'स्वचालित लेखन'
                },
                content: {
                    en: 'Click here to see how to write "राम" correctly.',
                    hi: 'देखें कि "राम" को सही तरीके से कैसे लिखा जाता है।'
                },
                position: 'top'
            },
            {
                element: '.stats',
                title: {
                    en: 'Statistics',
                    hi: 'आंकड़े'
                },
                content: {
                    en: 'Track your daily count, total malas, and streak here.',
                    hi: 'यहाँ अपनी दैनिक गणना, कुल माला और निरंतरता देखें।'
                },
                position: 'bottom'
            },
            {
                element: '.progress-section',
                title: {
                    en: 'Monthly Goal',
                    hi: 'मासिक लक्ष्य'
                },
                content: {
                    en: 'Track your progress towards the monthly goal of 3240 repetitions.',
                    hi: '3240 पुनरावृत्तियों के मासिक लक्ष्य की ओर अपनी प्रगति देखें।'
                },
                position: 'top'
            }
        ];
        
        this.currentStep = 0;
        this.overlay = null;
        this.tooltip = null;
    }
    
    start() {
        if (localStorage.getItem('walkthroughComplete')) {
            return;
        }
        
        this.createOverlay();
        this.showStep(0);
        
        // Mark walkthrough as complete after user finishes or skips
        localStorage.setItem('walkthroughComplete', 'true');
    }
    
    createOverlay() {
        this.overlay = document.createElement('div');
        this.overlay.className = 'walkthrough-overlay';
        
        this.tooltip = document.createElement('div');
        this.tooltip.className = 'walkthrough-tooltip';
        
        document.body.appendChild(this.overlay);
        document.body.appendChild(this.tooltip);
    }
    
    showStep(index) {
        if (index >= this.steps.length) {
            this.complete();
            return;
        }
        
        const step = this.steps[index];
        const element = document.querySelector(step.element);
        
        if (!element) {
            this.showStep(index + 1);
            return;
        }
        
        const rect = element.getBoundingClientRect();
        
        // Highlight the element
        this.overlay.style.mask = `
            path('M 0 0 L 0 ${window.innerHeight} L ${window.innerWidth} ${window.innerHeight} L ${window.innerWidth} 0 Z
                 M ${rect.left} ${rect.top} L ${rect.left} ${rect.bottom} L ${rect.right} ${rect.bottom} L ${rect.right} ${rect.top} Z')
        `;
        this.overlay.style.webkitMask = this.overlay.style.mask;
        
        // Position tooltip
        const lang = state.language || 'en';
        this.tooltip.innerHTML = `
            <h3>${step.title[lang]}</h3>
            <p>${step.content[lang]}</p>
            <div class="walkthrough-buttons">
                <button class="secondary-button" onclick="walkthrough.skip()">
                    ${lang === 'hi' ? 'छोड़ें' : 'Skip'}
                </button>
                <button class="primary-button" onclick="walkthrough.next()">
                    ${lang === 'hi' ? 'अगला' : 'Next'}
                </button>
            </div>
        `;
        
        // Position tooltip based on step configuration
        const tooltipRect = this.tooltip.getBoundingClientRect();
        let top, left;
        
        switch (step.position) {
            case 'top':
                top = rect.top - tooltipRect.height - 10;
                left = rect.left + (rect.width - tooltipRect.width) / 2;
                break;
            case 'bottom':
                top = rect.bottom + 10;
                left = rect.left + (rect.width - tooltipRect.width) / 2;
                break;
            case 'left':
                top = rect.top + (rect.height - tooltipRect.height) / 2;
                left = rect.left - tooltipRect.width - 10;
                break;
            case 'right':
                top = rect.top + (rect.height - tooltipRect.height) / 2;
                left = rect.right + 10;
                break;
        }
        
        // Keep tooltip within viewport
        top = Math.max(10, Math.min(top, window.innerHeight - tooltipRect.height - 10));
        left = Math.max(10, Math.min(left, window.innerWidth - tooltipRect.width - 10));
        
        this.tooltip.style.top = `${top}px`;
        this.tooltip.style.left = `${left}px`;
        
        // Add highlight effect to element
        element.classList.add('walkthrough-highlight');
        
        this.currentStep = index;
    }
    
    next() {
        const currentElement = document.querySelector(this.steps[this.currentStep].element);
        if (currentElement) {
            currentElement.classList.remove('walkthrough-highlight');
        }
        this.showStep(this.currentStep + 1);
    }
    
    skip() {
        this.complete();
    }
    
    complete() {
        const currentElement = document.querySelector(this.steps[this.currentStep].element);
        if (currentElement) {
            currentElement.classList.remove('walkthrough-highlight');
        }
        
        this.overlay.remove();
        this.tooltip.remove();
        
        // Show auto-draw demonstration
        if (window.drawingCanvas) {
            window.drawingCanvas.autoDraw();
        }
    }
}

// Initialize walkthrough
window.walkthrough = new Walkthrough(); 