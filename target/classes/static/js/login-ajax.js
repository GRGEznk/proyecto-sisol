// login-ajax.js

document.addEventListener('DOMContentLoaded', function() {
  const loginForm = document.getElementById('loginForm');
  if (!loginForm) return;

  // Crear o seleccionar el contenedor de errores
  let errorDiv = document.getElementById('loginErrorMsg');
  if (!errorDiv) {
    errorDiv = document.createElement('div');
    errorDiv.id = 'loginErrorMsg';
    errorDiv.className = 'alert alert-danger d-none';
    errorDiv.style.marginTop = '10px';
    loginForm.insertBefore(errorDiv, loginForm.querySelector('.row.g-2'));
  }

  loginForm.addEventListener('submit', function(e) {
    e.preventDefault();
    errorDiv.classList.add('d-none');
    errorDiv.textContent = '';

    const formData = new FormData(loginForm);
    const data = new URLSearchParams();
    for (const pair of formData) {
      data.append(pair[0], pair[1]);
    }

    fetch('/auth/login', {
      method: 'POST',
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Accept': 'application/json',
      },
      body: data,
      credentials: 'same-origin'
    })
    .then(async response => {
      if (response.ok) {
        // Login exitoso
        // Cerrar el modal (Bootstrap 5)
        const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
        if (modal) modal.hide();
        // Recargar la página para actualizar el navbar
        setTimeout(() => window.location.reload(), 300);
      } else {
        // Error de autenticación
        let msg = 'Error al iniciar sesión';
        try {
          const res = await response.json();
          msg = res.error || msg;
        } catch {}
        errorDiv.textContent = msg;
        errorDiv.classList.remove('d-none');
      }
    })
    .catch(() => {
      errorDiv.textContent = 'Error de red o del servidor';
      errorDiv.classList.remove('d-none');
    });
  });
}); 