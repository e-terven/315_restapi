

async function currentUserEmail(userId) {
    try {
        const usersPageEd = await fetch(`http://localhost:315/api/admin/` + userId);
        if (usersPageEd.ok) {
            const user = await usersPageEd.json();
            return user.email;

        } else {
            alert(`Error: ${usersPageEd.status}`);
            return null;
        }
    } catch (error) {
        console.error(error);
        return null;
    }
}
async function readModalData(userId) {
    try {
        const usersPageEd = await fetch(`http://localhost:315/api/admin/` + userId);
        if (usersPageEd.ok) {
            const user = await usersPageEd.json();

            document.getElementById('idEdit').value = user.id;
            document.getElementById('firstNameEdit').value = user.firstName;
            document.getElementById('lastNameEdit').value = user.lastName;
            document.getElementById('ageEdit').value = user.age;
            document.getElementById('emailEdit').value = user.email;
            document.getElementById('passwordEdit').value = user.password;

            const options = document.getElementById('rolesEdit').options;
            for (let i = 0; i < options.length; i++) {
                if (user.roles.some(role => role.roleName === options[i].value)) {
                    options[i].selected = true;
                }
            }
        } else {
            alert(`Error: ${usersPageEd.status}`);

        }
    } catch (error) {
        console.error(error);

    }
}

const rolesEd = document.getElementById('rolesEdit');
async function editModalData(userId) {
    let edRoles = [];
    for (let i = 0; i < rolesEd.length; i++) {
        if (rolesEd[i].selected) {
            edRoles.push(rolesEd[i].value);
        }
    }
    let email = await currentUserEmail(userId);
    console.log(email + "done");

    const method = {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            // 'id': document.getElementById('idEdit').value,
            'firstName': document.getElementById('firstNameEdit').value,
            'lastName': document.getElementById('lastNameEdit').value,
            'age': document.getElementById('ageEdit').value,
            'email': document.getElementById('emailEdit').value,
            'password': document.getElementById('passwordEdit').value,
            'roleName': edRoles
        })
    };
    try {
        const id_ed = document.getElementById('idEdit').value;
        const email_ed = document.getElementById('emailEdit').value;

        let urlEd = `http://localhost:315/api/admin/`+ id_ed;

        await fetch(urlEd, method)
            .then(async (response) => {

                if (response.status === 201) {
                    window.location.href = `http://localhost:315/api/admin`;

                } else if (email !== email_ed && response.status === 226) {
                    alert('User with this Email already exists!');

                } else if (email === email_ed) {
                    window.location.href = `http://localhost:315/api/admin`;
                }
                else {
                    alert("There is a problem");
                }

            })
    } catch (error) {
        console.error('Error:', error);
    }
}

// const authUserInfo = document.getElementById('authUserInfo');

const ed_button = document.getElementById('editSubmitBtn');
const ed_close_button = document.getElementById('editCloseBtn');
const form_ed_user = document.getElementById('editForm');

authUserInfo.addEventListener('click', function(event) {
    const target = event.target;

    if (target.matches('#modalEdit')) {
        const userId = target.dataset.userId;
        readModalData(userId);

        ed_button.addEventListener('click', function(e) {
            e.preventDefault();
            editModalData(userId);
        });
    }
});
ed_close_button.addEventListener('click', function() {
    form_ed_user.reset();
})