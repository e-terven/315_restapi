//NEW USER FORM
const form_n = document.getElementById('newUserForm');
const rolesNew = document.querySelectorAll('#rolesNew option');

form_n.addEventListener('submit', newUserEvent);
console.log("preventDefault");

async function newUserEvent(ev) {
    ev.preventDefault();
    let newRoles = [];
    for (let i = 0; i < rolesNew.length; i++) {
        if (rolesNew[i].selected) {
            newRoles.push(rolesNew[i].value);
        }
    }

    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            'firstName': document.getElementById('newFirstName').value,
            'lastName': document.getElementById('newLastName').value,
            'age': document.getElementById('newAge').value,
            'email': document.getElementById('newEmail').value,
            'password': document.getElementById('newPassword').value,
            'roleName': newRoles
        })
    }


    // Create - Add New User (отправляем запрос на сервер)
    // Method: POST
    try {
        await fetch(`http://localhost:315/api/admin`, method)
        .then((response) => {
            // Если запрос выполнен успешно, перенаправляем пользователя на страницу с его профилем
            if (response.status === 201) {
                window.location.href = `http://localhost:315/api/admin`;
            }
            // Если пользователь с таким email уже существует
            else if (response.status === 226) {
                alert('User with this Email already exists!');
            }
        })
    } catch (error) {
        console.error('Error:', error);
    }
}
