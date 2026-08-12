class DrawingCanvas {
    constructor() {
        this.canvas = document.getElementById('drawingCanvas');
        this.ctx = this.canvas.getContext('2d');
        this.isDrawing = false;
        this.points = [];
        this.lastX = 0;
        this.lastY = 0;
        
        this.setupCanvas();
        this.setupEventListeners();
    }
    
    setupCanvas() {
        // Set canvas size to match container
        const container = this.canvas.parentElement;
        this.canvas.width = container.offsetWidth;
        this.canvas.height = container.offsetHeight;
        
        // Set drawing style
        this.ctx.strokeStyle = getComputedStyle(document.body).getPropertyValue('--primary-color');
        this.ctx.lineWidth = 3;
        this.ctx.lineCap = 'round';
        this.ctx.lineJoin = 'round';
    }
    
    setupEventListeners() {
        // Touch events
        this.canvas.addEventListener('touchstart', this.startDrawing.bind(this));
        this.canvas.addEventListener('touchmove', this.draw.bind(this));
        this.canvas.addEventListener('touchend', this.stopDrawing.bind(this));
        
        // Mouse events
        this.canvas.addEventListener('mousedown', this.startDrawing.bind(this));
        this.canvas.addEventListener('mousemove', this.draw.bind(this));
        this.canvas.addEventListener('mouseup', this.stopDrawing.bind(this));
        this.canvas.addEventListener('mouseout', this.stopDrawing.bind(this));
        
        // Handle window resize
        window.addEventListener('resize', () => {
            this.setupCanvas();
            this.redraw();
        });
    }
    
    startDrawing(e) {
        e.preventDefault();
        this.isDrawing = true;
        const pos = this.getPosition(e);
        this.lastX = pos.x;
        this.lastY = pos.y;
        this.points = [[pos.x, pos.y]];
    }
    
    draw(e) {
        e.preventDefault();
        if (!this.isDrawing) return;
        
        const pos = this.getPosition(e);
        this.points.push([pos.x, pos.y]);
        
        this.ctx.beginPath();
        this.ctx.moveTo(this.lastX, this.lastY);
        this.ctx.lineTo(pos.x, pos.y);
        this.ctx.stroke();
        
        this.lastX = pos.x;
        this.lastY = pos.y;
    }
    
    stopDrawing() {
        this.isDrawing = false;
    }
    
    getPosition(e) {
        let x, y;
        const rect = this.canvas.getBoundingClientRect();
        
        if (e.type.startsWith('touch')) {
            const touch = e.touches[0] || e.changedTouches[0];
            x = touch.clientX - rect.left;
            y = touch.clientY - rect.top;
        } else {
            x = e.clientX - rect.left;
            y = e.clientY - rect.top;
        }
        
        return { x, y };
    }
    
    clear() {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.points = [];
    }
    
    redraw() {
        if (this.points.length < 2) return;
        
        this.ctx.beginPath();
        this.ctx.moveTo(this.points[0][0], this.points[0][1]);
        
        for (let i = 1; i < this.points.length; i++) {
            this.ctx.lineTo(this.points[i][0], this.points[i][1]);
        }
        
        this.ctx.stroke();
    }
    
    autoDraw() {
        // Define the points for drawing राम
        const ramPoints = [
            // र
            [[50, 100], [50, 150], [70, 150], [90, 130], [90, 100]],
            [[50, 120], [90, 120]],
            // ा
            [[100, 100], [100, 150]],
            // म
            [[120, 100], [120, 150]],
            [[120, 120], [140, 120], [160, 120]],
            [[160, 100], [160, 150]]
        ];
        
        this.clear();
        this.animateDrawing(ramPoints);
    }
    
    animateDrawing(segments) {
        let segmentIndex = 0;
        let pointIndex = 0;
        
        const animate = () => {
            if (segmentIndex >= segments.length) return;
            
            const currentSegment = segments[segmentIndex];
            
            if (pointIndex === 0) {
                this.ctx.beginPath();
                this.ctx.moveTo(currentSegment[0][0], currentSegment[0][1]);
            }
            
            if (pointIndex < currentSegment.length) {
                this.ctx.lineTo(currentSegment[pointIndex][0], currentSegment[pointIndex][1]);
                this.ctx.stroke();
                pointIndex++;
                requestAnimationFrame(animate);
            } else {
                segmentIndex++;
                pointIndex = 0;
                setTimeout(() => requestAnimationFrame(animate), 100);
            }
        };
        
        requestAnimationFrame(animate);
    }
}

// Initialize the drawing canvas
const drawingCanvas = new DrawingCanvas(); 