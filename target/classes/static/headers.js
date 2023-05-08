async function getUserInfo0() {
    const temp = await fetch('http://localhost:315/api/admin/userinfo')
    // {headers: {
    //      'Authorization': 'Bearer ' + token
    //  }
    if (temp.ok) {
        let userAdmin = await temp.json();
        let emailAdmin = userAdmin.email;

        document.getElementById('userHeader1').innerHTML = emailAdmin;
        getUser0(userAdmin);
    } else {
        alert(`Error, ${temp.status}`);
    }
}
function getUser0(userAdmin) {
    let roleTypes = userAdmin.roles.map(role => role.authority.replace('ROLE_', ''));
    let roleString = roleTypes.join(' ');

    document.getElementById('userHeader2').innerHTML = roleString;
}

getUserInfo0().then(() => {
    console.log("getUserInfo1 выполнена успешно");
}).catch((err) => {
    console.error("Произошла ошибка в getUserInfo1: ", err);
});