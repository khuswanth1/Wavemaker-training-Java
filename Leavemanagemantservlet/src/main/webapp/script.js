const submit = document.getElementById("submitButton");
const userName = document.getElementById('username');
const password = document.getElementById('password');

submit.addEventListener('click', () => {
console.log("Inside event..")
    event.preventDefault();

    const loginData = {
        userName: userName.value,
        password: password.value
    };
    console.log("Login request..",loginData)
    fetch('http://localhost:8080/Leava/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (!response.ok) {
        console.log("In response not okay..");
                    return response.json().then(errorData => {
                        throw new Error(errorData.error || 'Something went wrong');
                    });
                }
                return response.text();
    })
    .then(data => {
        console.log('Login successful:', data);
        window.location.href = 'index.html';
    })
    .then(response => {
          if(response.ok){
             console.log("In response okay..");
          }
    })
    .catch(error => {
        console.error('Error:', error);
        const errorMessage = document.getElementById('errorMessage');
        errorMessage.style.display = 'block';
        errorMessage.innerText = error.message;
    });
});
