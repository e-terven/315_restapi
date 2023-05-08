

// document.addEventListener("DOMContentLoaded", function() {


// TABLE starts
async function getTable() {
    try {
        let page = await fetch(`http://localhost:315/api/admin/table`);
        if (page.ok) {
            let listAllUser = await page.json();
            loadTableData(listAllUser);
        } else {
            console.error("HTTP error: " + page.status);
        }
    } catch (error) {
        console.error(error);
    }
}

async function loadTableData(listAllUser) {
    let tableHtml = '';
    for (let user of listAllUser) {

        let roleTypes = user.roles.map(role => role.authority.replace('ROLE_', ''));
        let roleString = roleTypes.join(' ');
        tableHtml +=
            `<tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td style="text-align: center;">${roleString}</td>
            <td>
              <button type="button" class="btn btn-primary" data-bs-toggle="modal"
              data-bs-target="#contentEditModal" 
              id="modalEdit" data-user-id="${user.id}">Edit</button>
            </td>
    
            <td>
              <button type="button" class="btn btn-danger" data-bs-toggle="modal"
              data-bs-target="#contentDeleteModal"  
              id="modalDelete" data-user-id="${user.id}">Delete</button>
            </td>
              </tr>`;
    }
    document.getElementById('authUserInfo').innerHTML = tableHtml;
};
getTable();
// TABLE ends


// USER TAB starts
    async function getUserInfo3() {
        let temp3 = await fetch('http://localhost:315/api/user/userinfo');
        if (temp3.ok) {
            let user3 = await temp3.json();
            getUser3(user3);
        } else {
            alert(`Error, ${temp3.status}`);
        }
    }

    function getUser3(user3) {
        let roleTypes3 = user3.roles.map(role => role.authority.replace('ROLE_', ''));
        let roleString3 = roleTypes3.join(' ');
        let temp3 = '';
        temp3 +=
            `<tr>
            <td>${user3.id}</td>
            <td>${user3.firstName}</td>
            <td>${user3.lastName}</td>
            <td>${user3.age}</td>
            <td>${user3.email}</td>
            <td>${roleString3}</td>
           </tr>`;

        document.getElementById('usersTable').innerHTML = temp3;
    };
    getUserInfo3().then(() => {
        console.log("getUserInfo3 выполнена успешно");
    }).catch((err) => {
        console.error("Произошла ошибка в getUserInfo3: ", err);
    })
// USER TAB ends
