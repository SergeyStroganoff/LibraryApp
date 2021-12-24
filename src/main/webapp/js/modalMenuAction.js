const ModalActionClass = class {
    // тело класса
    constructor() {
    }

    roleElement = document.querySelector(".operation-status");

    init() {
        if (this.roleElement.getAttribute('value') === 'executed') {
            let myModal = new bootstrap.Modal(document.querySelector('#resultModalMenu'));
            myModal.show();
        }
    }
}

myApp = new ModalActionClass;
myApp.init();