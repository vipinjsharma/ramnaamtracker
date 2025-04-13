class DrawingCanvas {
    constructor(container) {
        this.canvas = document.createElement('canvas');
        this.ctx = this.canvas.getContext('2d');
        this.isDrawing = false;
        this.points = [];
        this.container = container;
        
        this.setup();
        this.setupEventListeners();
    }
    
    setup() {
        // Make canvas fill container
        this.canvas.style.width = '100%';
        this.canvas.style.height = '100%';
        this.container.appendChild(this.canvas);
        
        // Set canvas size
        this.resize();
        
        // Set initial stroke style
        this.ctx.strokeStyle = getComputedStyle(document.documentElement)
            .getPropertyValue('--primary-color').trim();
        this.ctx.lineWidth = 4;
        this.ctx.lineCap = 'round';
        this.ctx.lineJoin = 'round';
    }
    
    resize() {
        const rect = this.container.getBoundingClientRect();
        this.canvas.width = rect.width;
        this.canvas.height = rect.height;
    }
    
    setupEventListeners() {
        // Touch events
        this.canvas.addEventListener('touchstart', (e) => {
            e.preventDefault();
            const touch = e.touches[0];
            this.startDrawing(touch.clientX, touch.clientY);
        });
        
        this.canvas.addEventListener('touchmove', (e) => {
            e.preventDefault();
            const touch = e.touches[0];
            this.draw(touch.clientX, touch.clientY);
        });
        
        this.canvas.addEventListener('touchend', () => this.stopDrawing());
        
        // Mouse events
        this.canvas.addEventListener('mousedown', (e) => {
            this.startDrawing(e.clientX, e.clientY);
        });
        
        this.canvas.addEventListener('mousemove', (e) => {
            this.draw(e.clientX, e.clientY);
        });
        
        this.canvas.addEventListener('mouseup', () => this.stopDrawing());
        this.canvas.addEventListener('mouseout', () => this.stopDrawing());
        
        // Resize handling
        window.addEventListener('resize', () => this.resize());
    }
    
    startDrawing(clientX, clientY) {
        const rect = this.canvas.getBoundingClientRect();
        const x = clientX - rect.left;
        const y = clientY - rect.top;
        
        this.isDrawing = true;
        this.points = [{x, y}];
        this.ctx.beginPath();
        this.ctx.moveTo(x, y);
    }
    
    draw(clientX, clientY) {
        if (!this.isDrawing) return;
        
        const rect = this.canvas.getBoundingClientRect();
        const x = clientX - rect.left;
        const y = clientY - rect.top;
        
        this.points.push({x, y});
        
        // Smooth line drawing using quadratic curves
        if (this.points.length > 2) {
            const xc = (this.points[this.points.length - 2].x + x) / 2;
            const yc = (this.points[this.points.length - 2].y + y) / 2;
            this.ctx.quadraticCurveTo(
                this.points[this.points.length - 2].x,
                this.points[this.points.length - 2].y,
                xc,
                yc
            );
            this.ctx.stroke();
            this.ctx.beginPath();
            this.ctx.moveTo(xc, yc);
        }
    }
    
    stopDrawing() {
        if (!this.isDrawing) return;
        this.isDrawing = false;
        this.ctx.stroke();
    }
    
    clear() {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.points = [];
    }
    
    async autoDraw() {
        // राम character strokes
        const strokes = [
            // र
            [[0.3, 0.3], [0.3, 0.7]],
            [[0.3, 0.5], [0.4, 0.5]],
            [[0.3, 0.7], [0.4, 0.7]],
            // ा
            [[0.4, 0.3], [0.4, 0.7]],
            // म
            [[0.5, 0.3], [0.5, 0.7]],
            [[0.5, 0.5], [0.7, 0.5]],
            [[0.7, 0.3], [0.7, 0.7]]
        ];
        
        this.clear();
        
        for (const stroke of strokes) {
            this.ctx.beginPath();
            for (let i = 0; i < stroke.length; i++) {
                const [x, y] = stroke[i];
                const px = x * this.canvas.width;
                const py = y * this.canvas.height;
                
                if (i === 0) {
                    this.ctx.moveTo(px, py);
                } else {
                    this.ctx.lineTo(px, py);
                }
            }
            this.ctx.stroke();
            await new Promise(resolve => setTimeout(resolve, 300));
        }
    }
}

// Initialize drawing canvas
window.drawingCanvas = new DrawingCanvas(
    document.querySelector('.writing-area')
); 