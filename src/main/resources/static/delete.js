
const id_del = document.getElementById('idDelete');
const firstName_del = document.getElementById('firstNameDelete');
const lastName_del = document.getElementById('lastNameDelete');
const age_del = document.getElementById('ageDelete');
const email_del = document.getElementById('emailDelete');


window.deleteModalData = deleteModalData;
async function deleteModalData(id) {
    try {
        const response = await fetch(`http://localhost:315/api/admin/` + id);
        if (response.ok) {
            const user = await response.json();
            id_del.value = user.id;
            firstName_del.value = user.firstName;
            lastName_del.value = user.lastName;
            age_del.value = user.age;
            email_del.value = user.email;

            const options = document.getElementById('rolesDelete').options;
            for (let i = 0; i < options.length; i++) {
                if (user.roles.some(role => role.roleName === options[i].value)) {
                    options[i].selected = true;
                    options[i].disabled = true;
                } else {
                    options[i].disabled = true;
                }
            }

        } else {
            alert(`Error, ${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}

async function deleteUser() {
    let urlDel = `http://localhost:315/api/admin/`+ id_del.value;
    let method = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }
    try {
        const response = await fetch(urlDel, method);
        if (response.ok) {
            window.location.href = 'http://localhost:315/api/admin';
        } else {
            alert(`Error, ${response.status}`);
        }
    } catch (error) {
        console.error(error);
    }
}


const authUserInfo = document.getElementById('authUserInfo');

const del_button = document.getElementById('deleteSubmitBtn');
const del_close_button = document.getElementById('deleteCloseBtn');
const form_del_user = document.getElementById('deleteForm');

authUserInfo.addEventListener('click', function(event) {
    const target = event.target;

    // если кликнули на кнопку удаления
    if (target.matches('#modalDelete')) {
        const id = target.dataset.userId;
        deleteModalData(id);
        del_button.addEventListener('click', function() {
            deleteUser();
        });
    }
});
del_close_button.addEventListener('click', function() {
    form_del_user.reset();
})

