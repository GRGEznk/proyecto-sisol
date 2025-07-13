// Este archivo solo debe contener lógica adicional para el navbar, no para el offcanvas, ya que ahora usamos el de Bootstrap 5.
// Si necesitas agregar scripts personalizados para el navbar, hazlo aquí. 

// navbar.js - Funcionalidades del navbar y comportamiento dinámico

/**
 * UTILIDADES
 */

// Throttle para optimizar eventos
function throttle(func, limit) {
    let inThrottle;
    return function() {
        const args = arguments;
        const context = this;
        if (!inThrottle) {
            func.apply(context, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    }
}

// Obtener altura del navbar
function getNavbarHeight() {
    const navbar = document.getElementById('primary-header');
    return navbar ? navbar.offsetHeight : 0;
}

// Ajustar scroll cuando el navbar cambia de estado
function adjustScrollForNavbar() {
    const navbar = document.getElementById('primary-header');
    if (navbar && navbar.classList.contains('navbar-fixed')) {
        const currentScroll = window.pageYOffset || document.documentElement.scrollTop;
        if (currentScroll < 100) {
            window.scrollTo(0, 100);
        }
    }
}

/**
 * PERMISOS Y ALERTAS
 */

// Mostrar alerta de acceso denegado
function mostrarAccesoDenegado(mensaje = 'No tienes permisos para acceder a esta sección.') {
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-warning alert-dismissible fade show position-fixed';
    alertDiv.style.cssText = 'top: 80px; right: 20px; z-index: 9999; min-width: 300px;';
    alertDiv.innerHTML = `
        <strong>Acceso Denegado</strong><br>
        ${mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
    `;
    document.body.appendChild(alertDiv);
    setTimeout(() => {
        if (alertDiv.parentNode) {
            alertDiv.remove();
        }
    }, 5000);
}

// Verificar permisos antes de navegar
function verificarPermisos(rolRequerido, urlDestino) {
    const usuarioRol = document.querySelector('[data-usuario-rol]')?.dataset.usuarioRol;
    if (!usuarioRol || usuarioRol !== rolRequerido) {
        mostrarAccesoDenegado();
        return false;
    }
    window.location.href = urlDestino;
    return true;
}

// Exportar funciones globalmente
window.mostrarAccesoDenegado = mostrarAccesoDenegado;
window.verificarPermisos = verificarPermisos;

/**
 * COMPORTAMIENTO DINÁMICO DEL NAVBAR
 */

document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.getElementById('primary-header');
    const headerBottom = navbar.querySelector('.header-bottom');
    const header = document.getElementById('header');
    const body = document.body;
    const scrollThreshold = 250;
    let isNavbarFixed = false;
    let lastScrollTop = 0;
    let scrollDirection = 'down';

    // Manejar scroll para navbar flotante
    function handleScroll() {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        scrollDirection = scrollTop > lastScrollTop ? 'down' : 'up';
        lastScrollTop = scrollTop;
        if (scrollTop > scrollThreshold && !isNavbarFixed) {
            headerBottom.style.animation = 'slideDownFloating 0.5s cubic-bezier(0.4, 0, 0.2, 1)';
            headerBottom.classList.add('navbar-fixed');
            body.classList.add('navbar-fixed-active');
            isNavbarFixed = true;
            const floatingLogo = headerBottom.querySelector('.navbar-brand-floating');
            if (floatingLogo) {
                floatingLogo.style.transition = 'all 0.4s cubic-bezier(0.4, 0, 0.2, 1)';
                floatingLogo.classList.remove('d-none');
                floatingLogo.classList.add('d-block');
            }
        } else if (scrollTop <= scrollThreshold && isNavbarFixed) {
            headerBottom.style.animation = 'slideUp 0.4s cubic-bezier(0.4, 0, 0.2, 1)';
            setTimeout(() => {
                headerBottom.classList.remove('navbar-fixed');
                body.classList.remove('navbar-fixed-active');
                isNavbarFixed = false;
                const floatingLogo = headerBottom.querySelector('.navbar-brand-floating');
                if (floatingLogo) {
                    floatingLogo.classList.add('d-none');
                    floatingLogo.classList.remove('d-block');
                }
            }, 300);
        }
    }

    // Manejar resize de ventana
    function handleResize() {
        if (isNavbarFixed) {
            const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            if (scrollTop <= scrollThreshold) {
                navbar.classList.remove('navbar-fixed');
                body.classList.remove('navbar-fixed-active');
                isNavbarFixed = false;
            }
        }
    }

    // Scroll suave en enlaces internos
    function handleSmoothScroll() {
        const internalLinks = document.querySelectorAll('a[href^="#"]');
        internalLinks.forEach(link => {
            link.addEventListener('click', function(e) {
                const targetId = this.getAttribute('href').trim();
                if (targetId === '#') return;
                e.preventDefault();
                const targetElement = document.querySelector(targetId);
                if (targetElement) {
                    const offsetTop = targetElement.offsetTop;
                    const navbarHeight = isNavbarFixed ? 80 : 0;
                    window.scrollTo({
                        top: offsetTop - navbarHeight,
                        behavior: 'smooth'
                    });
                }
            });
        });
    }

    // Event listeners
    window.addEventListener('scroll', throttle(handleScroll, 16)); // ~60fps
    window.addEventListener('resize', throttle(handleResize, 100));
    handleSmoothScroll();
    handleScroll();
});

/**
 * Navbar Dinámico - SISOL
 * Maneja el comportamiento del navbar al hacer scroll
 */

document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.getElementById('primary-header');
    const body = document.body;
    const scrollThreshold = 100; // Píxeles para activar navbar fijo
    let isNavbarFixed = false;

    // Función para manejar el scroll
    function handleScroll() {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        
        if (scrollTop > scrollThreshold && !isNavbarFixed) {
            // Activar navbar fijo
            navbar.classList.add('navbar-fixed');
            body.classList.add('navbar-fixed-active');
            isNavbarFixed = true;
            
            // Agregar clase para transición suave
            setTimeout(() => {
                navbar.style.transition = 'all 0.3s ease';
            }, 10);
            
        } else if (scrollTop <= scrollThreshold && isNavbarFixed) {
            // Desactivar navbar fijo
            navbar.classList.remove('navbar-fixed');
            body.classList.remove('navbar-fixed-active');
            isNavbarFixed = false;
        }
    }

    // Función para manejar el resize de ventana
    function handleResize() {
        // Recalcular si es necesario cuando cambia el tamaño de la ventana
        if (isNavbarFixed) {
            const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            if (scrollTop <= scrollThreshold) {
                navbar.classList.remove('navbar-fixed');
                body.classList.remove('navbar-fixed-active');
                isNavbarFixed = false;
            }
        }
    }

    // Función para manejar el scroll suave en enlaces internos
    function handleSmoothScroll() {
        const internalLinks = document.querySelectorAll('a[href^="#"]');
        
        internalLinks.forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();
                
                const targetId = this.getAttribute('href').trim();
                const targetElement = document.querySelector(targetId);
                
                if (targetElement) {
                    const offsetTop = targetElement.offsetTop;
                    const navbarHeight = isNavbarFixed ? 80 : 0;
                    
                    window.scrollTo({
                        top: offsetTop - navbarHeight,
                        behavior: 'smooth'
                    });
                }
            });
        });
    }

    // Event listeners
    window.addEventListener('scroll', throttle(handleScroll, 10));
    window.addEventListener('resize', throttle(handleResize, 100));
    
    // Inicializar scroll suave
    handleSmoothScroll();
    
    // Verificar estado inicial
    handleScroll();
});

/**
 * Función para obtener la altura del navbar
 */
function getNavbarHeight() {
    const navbar = document.getElementById('primary-header');
    return navbar ? navbar.offsetHeight : 0;
}

/**
 * Función para ajustar scroll cuando el navbar cambia de estado
 */
function adjustScrollForNavbar() {
    const navbar = document.getElementById('primary-header');
    if (navbar && navbar.classList.contains('navbar-fixed')) {
        const currentScroll = window.pageYOffset || document.documentElement.scrollTop;
        if (currentScroll < 100) {
            window.scrollTo(0, 100);
        }
    }
} 