/**
 * Test Navbar - Para verificar el comportamiento
 */

// Función para mostrar el estado del navbar
function showNavbarStatus() {
    const navbar = document.getElementById('primary-header');
    const header = document.getElementById('header');
    const navbarPrimary = navbar.querySelector('#navbar-primary');
    const body = document.body;
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    const floatingLogo = navbar.querySelector('.navbar-brand-floating');
    
    console.log('=== Estado del Navbar ===');
    console.log('Scroll Top:', scrollTop);
    console.log('Navbar Fixed:', navbar.classList.contains('navbar-fixed'));
    console.log('Body Active:', body.classList.contains('navbar-fixed-active'));
    console.log('Header Height:', header ? header.offsetHeight : 'N/A');
    console.log('Navbar Height:', navbar.offsetHeight);
    console.log('Navbar Primary Width:', navbarPrimary ? navbarPrimary.offsetWidth : 'N/A');
    console.log('Body Padding Top:', body.style.paddingTop);
    console.log('Logo Flotante Visible:', floatingLogo && !floatingLogo.classList.contains('d-none'));
    console.log('========================');
}

// Agregar botón de prueba al DOM
document.addEventListener('DOMContentLoaded', function() {
    // Crear botón de prueba
    const testButton = document.createElement('button');
    testButton.textContent = 'Test Navbar';
    testButton.style.cssText = `
        position: fixed;
        top: 10px;
        right: 10px;
        z-index: 9999;
        background: #007bff;
        color: white;
        border: none;
        padding: 10px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 12px;
    `;
    
    testButton.addEventListener('click', showNavbarStatus);
    document.body.appendChild(testButton);
    
    // Mostrar estado inicial
    setTimeout(showNavbarStatus, 1000);
    
    // Mostrar estado cada 2 segundos durante el scroll
    let scrollTimeout;
    window.addEventListener('scroll', function() {
        clearTimeout(scrollTimeout);
        scrollTimeout = setTimeout(showNavbarStatus, 2000);
    });
}); 