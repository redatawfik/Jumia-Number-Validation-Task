const table = document.getElementById("customer_table");
const countrSelect = document.getElementById("country");
const stateSelect = document.getElementById("state");


function countrySelected() {
    getCustomers();
}

function validSelected() {
    getCustomers();
}

const getCustomers = () => {
    const xhr = new XMLHttpRequest();
    
    var country = countrSelect.value;
    var state = stateSelect.value;

    if(country === 'none') {
        country = ''
    }

    console.log(state)

    if(state === 'valid') {
        state = true
    }else if(state === 'invalid') {
        state = false
    } else {
        state = ''
    }

    xhr.open('GET', `http://localhost:8080/?country=${country}&valid=${state}`);

    xhr.responseType = 'json'

    xhr.onload = function () {
        var tableHeaderRowCount = 1;
        var rowCount = table.rows.length;
        for (var i = tableHeaderRowCount; i < rowCount; i++) {
            table.deleteRow(tableHeaderRowCount);
        }

        var customerList = xhr.response;
        console.log(customerList);

        for (let index = 0; index < customerList.length; index++) {
            const element = customerList[index];

            var row = table.insertRow(index + 1);

            var country = row.insertCell(0);
            var countryCode = row.insertCell(1);
            var valid = row.insertCell(2);
            var name = row.insertCell(3);
            var number = row.insertCell(4);

            country.innerHTML = element.country;
            countryCode.innerHTML = element.countryCode;
            valid.innerHTML = element.validNumber;
            name.innerHTML = element.name;
            number.innerHTML = element.phone;
        }
    }

    xhr.send();

};

getCustomers();
