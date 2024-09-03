document.addEventListener('DOMContentLoaded', () => {
    const submitButton = document.getElementById('submitButton');
    const cancelButton = document.getElementById('cancelButton');
    const userNameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const errorDiv = document.getElementById('error');

    submitButton.addEventListener('click', (event) => {
        event.preventDefault();

        const loginData = {
            userName: userNameInput.value,
            password: passwordInput.value
        };

        fetch('http://localhost:8080/Leava/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(errorData.error || 'Something went wrong');
                });
            }
            return response.text();
        })
        .then(data => {
            console.log('Login successful:', data);
            window.location.href = 'index.html'; // Change this to the appropriate redirect URL
        })
        .catch(error => {
            console.error('Error:', error);
            errorDiv.style.display = 'block';
            errorDiv.innerText = error.message;
        });
    });

    // Function to clear the form and hide the error message
    window.clearForm = () => {
        userNameInput.value = '';
        passwordInput.value = '';
        errorDiv.style.display = 'none';
    };

    // Event listener for the cancel button to clear the form
    cancelButton.addEventListener('click', () => {
        clearForm();
    });
});
