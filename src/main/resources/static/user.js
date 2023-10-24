const userUrl = 'http://localhost:8080/api/currentUser';


function getUserPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}

function getInformationAboutUser(user) {

    let result = '';
    result =
        `<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.lastName}</td>
    <td>${user.userName}</td>
    <td id=${'role' + user.id}>${user.role.map(r => r.role).join(' ')}</td>
</tr>`
    document.getElementById('userTableBody').innerHTML = result;
}

getUserPage();