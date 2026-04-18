// OjekOnline - Main JavaScript

document.addEventListener('DOMContentLoaded', function () {
    // Auto-dismiss alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(function (alert) {
        setTimeout(function () {
            const closeBtn = alert.querySelector('.btn-close');
            if (closeBtn) closeBtn.click();
        }, 5000);
    });

    // Highlight active sidebar link
    const currentPath = window.location.pathname;
    const sidebarLinks = document.querySelectorAll('.sidebar-link');
    sidebarLinks.forEach(function (link) {
        const href = link.getAttribute('href');
        if (href && currentPath.startsWith(href) && href !== '/dashboard') {
            link.classList.add('active');
        } else if (href === '/dashboard' && currentPath === '/dashboard') {
            link.classList.add('active');
        }
    });

    // Confirm delete actions
    const deleteLinks = document.querySelectorAll('a[href*="/delete"]');
    deleteLinks.forEach(function (link) {
        link.addEventListener('click', function (e) {
            if (!confirm('Are you sure you want to delete this item?')) {
                e.preventDefault();
            }
        });
    });

    // Smooth card animations on scroll
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function (entries) {
        entries.forEach(function (entry) {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);

    document.querySelectorAll('.stat-card, .feature-card').forEach(function (card) {
        observer.observe(card);
    });

    console.log('🛵 OjekOnline Admin Panel loaded successfully!');
});
