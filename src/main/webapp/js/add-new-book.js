const AddBookClass = class {
    // тело класса
    constructor() {
    }

    roleElement = document.querySelector(".add-book-status");

    init() {
        if (this.roleElement.getAttribute('value') === 'added') {
            let myModal = new bootstrap.Modal(document.querySelector('#addBookModalMenu'));
            myModal.show();
        }
    }
}

myApp = new AddBookClass;
myApp.init();