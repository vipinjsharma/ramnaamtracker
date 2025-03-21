document.addEventListener('DOMContentLoaded', function() {
    // DOM Elements
    const app = document.getElementById('app');
    const homePage = document.getElementById('homePage');
    const writingPage = document.getElementById('writingPage');
    const backButton = document.getElementById('backButton');
    const menuButton = document.getElementById('menuButton');
    const startWritingBtn = document.getElementById('startWritingBtn');
    const canvas = document.getElementById('drawingCanvas');
    const clearBtn = document.getElementById('clearBtn');
    const drawBtn = document.getElementById('drawBtn');
    const submitBtn = document.getElementById('submitBtn');
    const shareBtn = document.getElementById('shareBtn');
    const downloadBtn = document.getElementById('downloadBtn');
    
    // Counter elements
    const homeTodayCount = document.getElementById('homeTodayCount');
    const homeTodayMalaCount = document.getElementById('homeTodayMalaCount');
    const homeTotalCount = document.getElementById('homeTotalCount');
    const writingTodayCount = document.getElementById('writingTodayCount');
    const writingTodayMalaCount = document.getElementById('writingTodayMalaCount');
    
    // Constants
    const MALA_COUNT = 108; // Number of rams in one mala
    
    // State
    let isDrawing = false;
    let lastX = 0;
    let lastY = 0;
    let todayCount = 0;
    let todayMalaCount = 0;
    let totalCount = 0;
    let drawingContext;
    
    // Initialize the app
    initializeApp();
    
    // Event Listeners
    backButton.addEventListener('click', navigateToHome);
    startWritingBtn.addEventListener('click', navigateToWriting);
    clearBtn.addEventListener('click', clearCanvas);
    drawBtn.addEventListener('click', autoDraw);
    submitBtn.addEventListener('click', submitDrawing);
    shareBtn.addEventListener('click', shareStats);
    downloadBtn.addEventListener('click', downloadStats);
    
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
            todayMalaCount = savedData.todayMalaCount || 0;
        } else {
            // Reset daily counts if it's a new day
            todayCount = 0;
            todayMalaCount = 0;
        }
        
        totalCount = savedData.totalCount || 0;
    }
    
    function saveData() {
        const today = new Date().toDateString();
        
        const dataToSave = {
            lastDate: today,
            todayCount: todayCount,
            todayMalaCount: todayMalaCount,
            totalCount: totalCount
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
    
    function navigateToHome() {
        homePage.classList.add('active');
        writingPage.classList.remove('active');
        backButton.style.visibility = 'hidden';
        menuButton.style.visibility = 'visible';
        updateCountDisplay();
    }
    
    function navigateToWriting() {
        homePage.classList.remove('active');
        writingPage.classList.add('active');
        backButton.style.visibility = 'visible';
        menuButton.style.visibility = 'hidden';
        
        // Make sure canvas is properly sized
        setupCanvas();
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
        
        // Update the UI
        updateCountDisplay();
        
        // Save data
        saveData();
    }
    
    function shareStats() {
        // Share functionality would be implemented here
        // For now, show an alert
        alert('Share functionality would be implemented here');
    }
    
    function downloadStats() {
        // Download functionality would be implemented here
        // For now, show an alert
        alert('Download functionality would be implemented here');
    }
    
    // Handle window resize to adjust canvas
    window.addEventListener('resize', setupCanvas);
});