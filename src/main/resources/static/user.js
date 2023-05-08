async function getUserInfo1() {
    let temp = await fetch('http://localhost:315/api/user/userinfo');
    if (temp.ok) {
        let user1 = await temp.json();
        let email2 = user1.email;

        document.getElementById('userHeader3').innerHTML = email2;
        getUser2(user1);
    } else {
        alert(`Error, ${temp.status}`);
    }
}
function getUser2(user1) {
    let roleTypes = user1.roles.map(role => role.authority.replace('ROLE_', ''));
    let roleString = roleTypes.join(' ');
    let temp2 = '';
    temp2 +=
        `<tr>
          <td>${user1.id}</td>
          <td>${user1.firstName}</td>
          <td>${user1.lastName}</td>
          <td>${user1.age}</td>
          <td>${user1.email}</td>
          <td>${roleString}</td>
         </tr>`;
    temp2;
    document.getElementById('userHeader4').innerHTML = roleString;
    document.getElementById('basicTable').innerHTML = temp2;
};

getUserInfo1().then(() => {
    console.log("getUserInfo1 выполнена успешно");
}).catch((err) => {
    console.error("Произошла ошибка в getUserInfo1: ", err);
});